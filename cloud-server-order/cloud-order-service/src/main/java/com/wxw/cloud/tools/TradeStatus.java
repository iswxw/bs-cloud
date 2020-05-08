package com.wxw.cloud.tools;

import lombok.Data;

/**
 *
 * @Author: wxw
 * @create: 2020-05-05-11:19
 */
public enum  TradeStatus {

    /**
     * 1、WAIT_BUYER_PAY（交易创建，等待买家付款）；
     * 2、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）；
     * 3、TRADE_SUCCESS（交易支付成功）；
     * 4、TRADE_FINISHED（交易结束，不可退款）
     */
    WAIT_BUYER_PAY(1, "WAIT_BUYER_PAY"),
    TRADE_CLOSED(2, "TRADE_CLOSED"),
    TRADE_SUCCESS(3, "TRADE_SUCCESS"),
    TRADE_FINISHED(4, "TRADE_FINISHED");


    // 通过value 得到key
    public static Integer getkey(String value){
        TradeStatus tradeStatus = TradeStatus.valueOf(value);
        return tradeStatus.key;
    }

    Integer key;
    String value;

    TradeStatus() {
    }

    TradeStatus(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

  /*  public static void main(String[] args) {
        Integer getkey = getkey("TRADE_SUCCESS");
        System.out.println("getkey = " + getkey);
    }*/
}
