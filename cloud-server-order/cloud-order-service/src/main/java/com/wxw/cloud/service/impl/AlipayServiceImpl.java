package com.wxw.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.wxw.cloud.config.AliPayProperties;
import com.wxw.cloud.domain.Order;
import com.wxw.cloud.domain.OrderDetail;
import com.wxw.cloud.service.AlipayService;
import com.wxw.cloud.service.IOrderService;
import com.wxw.cloud.service.IPayLogService;
import com.wxw.cloud.tools.PayReq;
import com.wxw.cloud.tools.RefundReq;
import com.wxw.cloud.tools.TradeStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

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

    @Resource
    private IPayLogService payLogService;

    @Resource
    private IOrderService orderService;

    @Override
    public String refund(String orderId, String refundReason, String refundAmount, String reqNo) throws AlipayApiException {
        /** 调取支付宝接口 web端支付*/
        AlipayClient alipayClient = aliPayProperties.build();
        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        RefundReq refundReq = new RefundReq();
        refundReq.setOut_trade_no(orderId);
        refundReq.setRefund_reason(refundReason);
        refundReq.setRefund_amount(refundAmount);
        refundReq.setOut_request_no(reqNo);
        alipayRequest.setBizContent(JSON.toJSONString(refundReq));
        //请求
        AlipayTradeRefundResponse tradeRefund = alipayClient.execute(alipayRequest);
        if (!tradeRefund.isSuccess()){
            throw new AlipayApiException(tradeRefund.getSubMsg());
        }
        return tradeRefund.getBody();
    }

    // 查询交易
    @Override
    public String query(String orderId) throws AlipayApiException {
        /** 调取支付宝接口 web端支付*/
        AlipayClient alipayClient = aliPayProperties.build();
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        PayReq payReq = new PayReq();
        payReq.setOut_trade_no(orderId);
        /**请求接口*/
        alipayRequest.setBizContent(JSON.toJSONString(payReq));
        /**转换格式*/
        AlipayTradeQueryResponse tradeQuery = alipayClient.execute(alipayRequest);
        if (!tradeQuery.isSuccess()){
            throw new AlipayApiException(tradeQuery.getSubMsg());
        }
        return tradeQuery.getBody();
    }

    @Override
    public String close(String orderId) throws AlipayApiException {
        /** 调取支付宝接口 web端支付*/
        AlipayClient alipayClient = aliPayProperties.build();
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        PayReq payReq = new PayReq();
        payReq.setOut_trade_no(orderId);
        alipayRequest.setBizContent(JSON.toJSONString(payReq));
        AlipayTradeCloseResponse execute = alipayClient.execute(alipayRequest);
        if (!execute.isSuccess()){
             throw new AlipayApiException(execute.getSubMsg());
        }
        return execute.getBody();
    }

    @Override
    public String refundQuery(String orderId, String reqNo) throws AlipayApiException {
        /** 调取支付宝接口 web端支付*/
        AlipayClient alipayClient = aliPayProperties.build();
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
        RefundReq refundReq = new RefundReq();
        refundReq.setOut_trade_no(orderId);
        refundReq.setOut_request_no(reqNo);
        alipayRequest.setBizContent(JSON.toJSONString(refundReq));
        AlipayTradeFastpayRefundQueryResponse refundQuery = alipayClient.execute(alipayRequest);
        if (!refundQuery.isSuccess()){
            throw new AlipayApiException("退款查询发生异常");
        }
        return refundQuery.getBody();
    }

    // 支付第三方
    @Transactional
    @Override
    public String getAliPayClient(Order order, OrderDetail orderDetail) throws Exception {
        // 1.初始化支付客户端
        AlipayClient alipayClient = aliPayProperties.build();

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            /** 同步通知，支付完成后，支付成功页面*/
            alipayRequest.setReturnUrl(aliPayProperties.getReturnUrl());
            /** 异步通知，支付完成后，需要进行的异步处理*/
            alipayRequest.setNotifyUrl(aliPayProperties.getNotifyUrl());
            PayReq payReq = new PayReq();
            payReq.setProduct_code("FAST_INSTANT_TRADE_PAY");
            // 1c 表示当天有效
            payReq.setTimeout_express("1c");
            payReq.setBody("用户订购商品个数: "+orderDetail.getNum());
            payReq.setTotal_amount(order.getActualPay().toString());
            payReq.setOut_trade_no(order.getOrderId().toString());
            payReq.setSubject(orderDetail.getTitle());
            String reqJSON = JSON.toJSONString(payReq);
            alipayRequest.setBizContent(reqJSON);
            AlipayTradePagePayResponse pagePay = alipayClient.pageExecute(alipayRequest);
            log.info("支付出参参数：{}", pagePay.isSuccess());
            if (!pagePay.isSuccess()){
                throw new AlipayApiException(pagePay.getSubMsg());
            }
            return pagePay.getBody();
    }

    @Override
    public Boolean getReturnUrl(HttpServletRequest request, Map<String, String> params, Map<String, String[]> requestParams) {
        try {
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
                log.info("返回支付链接：{}",values.toString());
            }
            boolean signVerified = AlipaySignature.rsaCheckV1(params,aliPayProperties.getPublicKey(),aliPayProperties.getCharset(),aliPayProperties.getSignType());
            if (signVerified){
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
                //付款金额
                String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

                Integer trade_status = 1;
                //修改订单状态 改为 支付成功，已付款; 同时新增支付流水
                this.payLogService.updatePayLog(out_trade_no,trade_status, trade_no, total_amount);
                log.info("********************** 支付成功(支付宝同步通知) **********************");
                log.info("* 订单号: {}", out_trade_no);
                log.info("* 支付宝交易号: {}", trade_no);
                log.info("* 实付金额: {}", total_amount);
                log.info("***************************************************************");
                return true;
            }else {
                log.info("支付, 验签失败...");
                return false;
            }
        } catch (Exception e) {
            log.info("支付, 验签发生异常：{}",e);
            return false;
        }
    }

    @Override
    public Boolean getNotifyUrl(HttpServletRequest request,Map<String, String> params, Map<String, String[]> requestParams) throws Exception{
        try {
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            boolean signVerified = AlipaySignature.rsaCheckV1(params,aliPayProperties.getPublicKey(),aliPayProperties.getCharset(),aliPayProperties.getSignType());
            if (signVerified){ // 如果验签成功
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

                //交易状态
                String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

                //付款金额
                String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

                if(trade_status.equals("TRADE_FINISHED")){
                  return false;
                }else if (trade_status.equals("TRADE_SUCCESS")){
                    Integer status = TradeStatus.getkey(trade_status);
                    this.payLogService.updatePayLog(out_trade_no,status, trade_no, total_amount);
                    log.info("********************** 支付成功(支付宝异步通知) **********************");
                    log.info("* 订单号: {}", out_trade_no);
                    log.info("* 支付宝交易号: {}", trade_no);
                    log.info("* 实付金额: {}", total_amount);
                    log.info("* 订单状态: {}", trade_status);
                    log.info("***************************************************************");
                }
                log.info("支付成功...");
                return true;
            }else {
                log.info("支付, 验签失败...");
                return false;
            }
        } catch (Exception e) {
            log.info("支付, 验签发生异常：{}",e);
            return false;
        }
    }
}
