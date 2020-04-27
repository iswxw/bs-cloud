package com.wxw.cloud.service;

import com.alipay.api.AlipayApiException;

/**
 * 支付接口
 */
public interface AlipayService {

    /**
     *  web端订单支付
     * @param orderId    订单编号（唯一）
     * @param totalPay   订单价格
     * @param title      商品标题
     * @return
     * @throws Exception
     */
    String webPagePay(String orderId,Integer totalPay,String title) throws Exception;

    /**
     *
     * @param orderId        订单编号
     * @param refundReason   退款原因
     * @param totalPay       退款金额
     * @param reqNo 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     * @return
     * @throws AlipayApiException
     */
    String refund(String orderId,String refundReason,Integer totalPay,String reqNo) throws AlipayApiException;

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

}
