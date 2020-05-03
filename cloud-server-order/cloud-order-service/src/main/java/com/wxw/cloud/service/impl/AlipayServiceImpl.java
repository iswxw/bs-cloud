package com.wxw.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.wxw.cloud.config.AliPayProperties;
import com.wxw.cloud.domain.Order;
import com.wxw.cloud.domain.OrderDetail;
import com.wxw.cloud.service.AlipayService;
import com.wxw.cloud.tools.PayReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 支付实现类
 * @Author: wxw
 * @create: 2020-04-28-1:24
 */
@Slf4j
@Service
@EnableConfigurationProperties(AliPayProperties.class)
public class AlipayServiceImpl implements AlipayService {

    @Resource
    private AliPayProperties aliPayProperties;

    @Override
    public String refund(String orderId, String refundReason, Integer totalPay, String reqNo) throws AlipayApiException {
        return null;
    }

    @Override
    public String query(String orderId) throws AlipayApiException {
        return null;
    }

    @Override
    public String close(String orderId) throws AlipayApiException {
        return null;
    }

    @Override
    public String refundQuery(String orderId, String reqNo) throws AlipayApiException {
        return null;
    }

    // 支付第三方
    @Override
    public String getAliPayClient(Order order, OrderDetail orderDetail) {
        try {
            // 1.初始化支付客户端
            AlipayClient alipayClient = aliPayProperties.build();
            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            /** 异步通知，支付完成后，需要进行的异步处理*/
            alipayRequest.setReturnUrl(aliPayProperties.getReturnUrl());
            /** 同步通知，支付完成后，支付成功页面*/
            alipayRequest.setNotifyUrl(aliPayProperties.getNotifyUrl());
            PayReq payReq = new PayReq();
            payReq.setProduct_code("FAST_INSTANT_TRADE_PAY");
            // 1c 表示当天有效
            payReq.setTimeout_express("1c");
            payReq.setBody("用户订购商品个数"+orderDetail.getNum());
            payReq.setTotal_amount(order.getActualPay().toString());
            payReq.setOut_trade_no(order.getOrderId().toString());
            payReq.setSubject(orderDetail.getTitle());
            String reqJSON = JSON.toJSONString(payReq);
            alipayRequest.setBizContent(reqJSON);
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            return result;
        } catch (AlipayApiException e) {
           log.error("第三方支付发生异常：=>{}", e);
           return null;
        }
    }
}
