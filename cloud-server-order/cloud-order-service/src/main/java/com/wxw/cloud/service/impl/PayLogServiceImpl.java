package com.wxw.cloud.service.impl;

import cn.hutool.core.convert.Convert;
import com.wxw.cloud.dao.OrderStatusMapper;
import com.wxw.cloud.domain.OrderStatus;
import com.wxw.cloud.domain.PayLog;
import com.wxw.cloud.dao.PayLogMapper;
import com.wxw.cloud.domain.UserInfo;
import com.wxw.cloud.interceptor.LoginOrderInterceptor;
import com.wxw.cloud.service.IOrderService;
import com.wxw.cloud.service.IPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-04-27
 */
@Slf4j
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements IPayLogService {

    @Resource
    private IOrderService orderService;

    @Resource
    private OrderStatusMapper orderStatusMapper;

    @Resource
    private PayLogMapper payLogMapper;

    // 更新订单状态和流水日志
    @Transactional
    @Override
    public Boolean updatePayLog(String out_trade_no, Integer trade_status ,String trade_no, String total_amount) {
         // 获取订单状态
        OrderStatus orderStatus = this.orderStatusMapper.selectById(Convert.toLong(out_trade_no));
        if (orderStatus == null){
            log.info("订单状态信息暂时为空！！");
        }
        PayLog payLog = new PayLog();
        payLog.setCreateTime(orderStatus.getCreateTime());
        payLog.setOrderId(Convert.toLong(out_trade_no));
        payLog.setTotalFee(Convert.toLong(total_amount));
        UserInfo userinfo = LoginOrderInterceptor.getUserinfo();
        payLog.setUserId(userinfo.getId());
        payLog.setTransactionId(trade_no);
        payLog.setStatus(trade_status);
        payLog.setPayType(1);
        payLog.setPayTime(LocalDateTime.now());
        payLog.setBankType("银行名称");
        // 更新支付流水
        int insert = this.payLogMapper.insert(payLog);
        // 更新订单状态
        log.info("订单状态值：{}", orderStatus);
        orderStatus.setStatus(null == payLog.getStatus() ? 1:payLog.getStatus());
        orderStatus.setPaymentTime(null == payLog.getPayTime() ? LocalDateTime.now():payLog.getPayTime());
        this.orderStatusMapper.updateById(orderStatus);
        return insert > 0 ? true : false;
    }
}
