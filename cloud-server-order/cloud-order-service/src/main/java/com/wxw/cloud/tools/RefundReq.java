package com.wxw.cloud.tools;

import lombok.Data;

/**
 * @Author: wxw
 * @create: 2020-05-03-17:51
 */
@Data
public class RefundReq {

    //订单编号
    private String out_trade_no;
    //支付宝交易号 || 订单编号 二选一设置
    private String trade_no;
    //退款原因
    private String refund_reason;
    //退款金额
    private String refund_amount;
    //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
    private String out_request_no;
}
