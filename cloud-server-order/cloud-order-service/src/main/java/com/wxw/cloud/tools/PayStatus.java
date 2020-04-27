package com.wxw.cloud.tools;

import lombok.Data;

/**
 * @Author: wxw
 * @create: 2020-04-27-15:49
 */
public enum PayStatus {
    WAIT_PAY(1, "待付款"),
    PAID(2, "已付款，未发货"),
    CONFIRMED(3, "已发货，未确认"),
    SUCCESS(4, "交易成功"),
    CLOSED(5, "交易关闭"),
    EVALUATED(6, "已评价");

    Integer key;
    String value;

    public static String getName(String key) {
        for (PayStatus status : PayStatus.values()) {
            if (status.getKey().equals(key)) {
                return status.value;
            }
        }
        return null;
    }

    PayStatus() {
    }

    PayStatus(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
