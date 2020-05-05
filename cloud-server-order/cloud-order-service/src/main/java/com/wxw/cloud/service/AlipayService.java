package com.wxw.cloud.service;

import com.alipay.api.AlipayApiException;
import com.wxw.cloud.domain.Order;
import com.wxw.cloud.domain.OrderDetail;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 支付接口
 */
public interface AlipayService {


    /**
     *
     * @param orderId        订单编号
     * @param refundReason   退款原因
     * @param refundAmount       退款金额
     * @param reqNo 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     * @return
     * @throws AlipayApiException
     */
    String refund(String orderId,String refundReason,String refundAmount,String reqNo) throws AlipayApiException;

    /**
     *  根据订单编号查询交易号
     * @param orderId 订单编号（唯一）
     */
    String query(String orderId) throws AlipayApiException;

    /**
     * 交易关闭
     * orderId  订单编号（唯一）
     */
    String close(String orderId) throws AlipayApiException;

    /**
     * 退款查询
     * @param orderId 订单编号（唯一）
     * @param reqNo 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     */
    String refundQuery(String orderId,String reqNo) throws AlipayApiException;

    /**
     *  web端订单支付
     * @param order
     * @param orderDetail
     * @return
     */
    String getAliPayClient(Order order, OrderDetail orderDetail) throws Exception;

    // 支付宝同步通知页面
    Boolean getReturnUrl(HttpServletRequest request, Map<String, String> params, Map<String, String[]> requestParams);
    // 支付宝 异步通知页面
    Boolean getNotifyUrl(HttpServletRequest request,Map<String, String> params, Map<String, String[]> requestParams)  throws Exception;
}
