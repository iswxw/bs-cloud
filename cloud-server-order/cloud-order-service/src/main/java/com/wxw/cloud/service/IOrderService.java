package com.wxw.cloud.service;

import com.wxw.cloud.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxw.cloud.domain.OrderStatus;
import com.wxw.cloud.result.PageResult;

import java.util.List;

/**
 * @author WXW
 * @since 2020-04-27
 */
public interface IOrderService extends IService<Order> {

    // 创建订单
    Long createOrder(String seck, Order order);

    // 查询库存
    List<Long> queryStock(String seck, Order order);

    // 根据订单编号查询订单
    Order queryOrderById(Long id);

    //分页查询当前用户订单
    PageResult<Order> queryUserOrderList(Integer page, Integer rows, Integer status);

    // 更新订单状态
    Boolean updateOrderStatus(Long id, Integer status);

    // 根据订单id查询其包含的skuId
    List<Long> querySkuIdByOrderId(Long id);

    // 根据订单id查询订单状态
    OrderStatus queryOrderStatusById(Long id);
}
