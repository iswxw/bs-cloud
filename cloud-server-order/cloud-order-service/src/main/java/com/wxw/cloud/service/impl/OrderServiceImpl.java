package com.wxw.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxw.cloud.dao.*;
import com.wxw.cloud.domain.*;
import com.wxw.cloud.interceptor.LoginOrderInterceptor;
import com.wxw.cloud.result.PageResult;
import com.wxw.cloud.service.IOrderDetailService;
import com.wxw.cloud.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WXW
 * @since 2020-04-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StockMapper stockMapper;
    @Resource
    private OrderStatusMapper orderStatusMapper;
    @Resource
    private IOrderDetailService orderDetailService;
    @Resource
    private SeckillOrderMapper seckillOrderMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;


    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createOrder(String tag, Order order) {
        String seck = "seckill";
        boolean create = true;
        // 1,判断订单中商品库存是否充足
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            Stock stock = this.stockMapper.selectById(orderDetail.getSkuId());
            if (stock.getStock()-orderDetail.getNum()<0){
                create = false;
            }else if (tag.equals(seck)&&stock.getSeckillStock()-orderDetail.getNum()<0){
                create = false;
            }
        }
        // 2.创建订单
        if (create){
            // 1.生成订单 orderId   workerId 当前机器 datacenterId 序列号
            long orderId = IdUtil.createSnowflake(1, 1).nextId();
            // 2.获取登录的用户
            UserInfo userinfo = LoginOrderInterceptor.getUserinfo();
            // 3.初始化数据
            order.setBuyerNick(userinfo.getUsername());
            order.setBuyerRate(false);
            order.setCreateTime(LocalDateTime.now());
            order.setOrderId(orderId);
            order.setUserId(userinfo.getId().toString());
            // 4.保存数据
            this.orderMapper.insert(order);
            // 5. 保存订单状态
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setOrderId(orderId);
            orderStatus.setCreateTime(order.getCreateTime());
            // 初始状态未付款
            orderStatus.setStatus(1);
            // 6. 保存订单状态
            this.orderStatusMapper.insert(orderStatus);
            // 7. 在订单详情中添加orderId
            order.getOrderDetails().forEach(orderDetail -> {
                orderDetail.setOrderId(orderId);
            });
            // 8. 保存订单详情，使用批量插入
            this.orderDetailService.saveBatch(order.getOrderDetails());
            // 9.判断是否是秒杀订单
            if (StringUtils.isNotEmpty(tag) && tag.equals(seck)) {
                order.getOrderDetails().forEach(orderDetail -> {
                    Stock stock = this.stockMapper.selectById(orderDetail.getSkuId());
                    stock.setStock(stock.getStock() - orderDetail.getNum());
                    stock.setSeckillStock(stock.getSeckillStock() - orderDetail.getNum());
                    this.stockMapper.updateById(stock);
                    //新建秒杀订单
                    SeckillOrder seckillOrder = new SeckillOrder();
                    seckillOrder.setOrderId(orderId);
                    seckillOrder.setSkuId(orderDetail.getSkuId());
                    seckillOrder.setUserId(userinfo.getId());
                    this.seckillOrderMapper.insert(seckillOrder);
                });
            } else {
                //普通订单
                order.getOrderDetails().forEach(orderDetail -> this.stockMapper.reduceStock(orderDetail.getSkuId(), orderDetail.getNum()));
            }
            return orderId;
        }else {
            return null;
        }
    }

    /**
     * 查询订单下商品的库存，返回库存不足的商品Id
     * @param tag 秒杀库存
     * @param order 订单
     * @return
     */
    @Override
    public List<Long> queryStock(String tag, Order order) {
        String seck = "seckill";
        List<Long> skuId = new ArrayList<>();
        order.getOrderDetails().forEach(orderDetail -> {
            Stock stock = this.stockMapper.selectById(orderDetail.getSkuId());
            if (stock.getStock()-orderDetail.getNum()<0){
                //先判断库存是否充足
                skuId.add(orderDetail.getSkuId());
            }else {
                // 充足的话就判断秒杀库存是否充足
                if (StringUtils.isNotEmpty(tag) && seck.equals(tag)){
                    //检查秒杀库存
                    if (stock.getSeckillStock() - orderDetail.getNum() < 0){
                        //不充足
                        skuId.add(orderDetail.getSkuId());
                    }
                }
            }
        });
        return skuId;
    }

    /**
     *  根据订单号查询订单
     * @param id
     * @return
     */
    @Override
    public Order queryOrderById(Long id) {
        // 1.查询订单
        Order order = this.orderMapper.selectById(id);
        //2. 查询订单详情
        QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", order.getOrderId());
        List<OrderDetail> orderDetails = this.orderDetailMapper.selectList(wrapper);
        // 3. 查询订单状态
        OrderStatus orderStatus = this.orderStatusMapper.selectById(order.getOrderId());
        // 4. order对象填充订单详情
        order.setOrderDetails(orderDetails);
        // 5, 对象设置订单状态
        order.setOrderStatus(orderStatus.getStatus());
        // 6, 返回订单
        return order;
    }

    /**
     *  查询当前登录用户的订单，通过订单状态进行筛选
     * @param page
     * @param rows
     * @param status
     * @return
     */
    @Override
    public PageResult<Order> queryUserOrderList(Integer page, Integer rows, Integer status) {
        try {
            // 1.分页
            Page<Order> currentPage = new Page<>(page,rows);
            // 2. 获取登录用户信息
            UserInfo userinfo = LoginOrderInterceptor.getUserinfo();
            // 3，查询
            QueryWrapper<Order> queryPage = new QueryWrapper<>();

            IPage<Order> orderIPage = this.orderMapper.selectPage(currentPage,queryPage);
            // 4，填充orderdetail
            List<Order> orderList = orderIPage.getRecords();
            orderList.forEach(order -> {
                QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
                wrapper.eq("order_id", order.getOrderId());
                List<OrderDetail> detailList = this.orderDetailMapper.selectList(wrapper);
                order.setOrderDetails(detailList);
            });
            return new PageResult<>(orderIPage.getTotal(),orderIPage.getPages(),orderList);
        } catch (Exception e) {
            LOGGER.error("查询订单出错=>{}",e);
            return null;
        }
    }

    /**
     *  更新订单状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public Boolean updateOrderStatus(Long id, Integer status) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(id);
        orderStatus.setStatus(status);
        //1.根据状态判断要修改的时间
        switch (status){
            case 2:
                //2.付款时间
                orderStatus.setPaymentTime(LocalDateTime.now());
                break;
            case 3:
                //3.发货时间
                orderStatus.setConsignTime(LocalDateTime.now());
                break;
            case 4:
                //4.确认收货，订单结束
                orderStatus.setEndTime(LocalDateTime.now());
                break;
            case 5:
                //5.交易失败，订单关闭
                orderStatus.setCloseTime(LocalDateTime.now());
                break;
            case 6:
                //6.评价时间
                orderStatus.setCommentTime(LocalDateTime.now());
                break;
            default:
                return null;
        }
        int count = this.orderStatusMapper.updateById(orderStatus);
        return count > 0 ? true : false;
    }

    /**
     * 根据订单号查询商品id
     * @param id
     * @return
     */
    @Override
    public List<Long> querySkuIdByOrderId(Long id) {
        QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", id);
        List<OrderDetail> details = this.orderDetailMapper.selectList(wrapper);
        List<Long> ids = new ArrayList<>();
        details.forEach(detail -> ids.add(detail.getSkuId()));
        return ids;
    }

    /**
     *  根据订单号查询订单状态
     * @param id
     * @return
     */
    @Override
    public OrderStatus queryOrderStatusById(Long id) {
        return this.orderStatusMapper.selectById(id);
    }

}
