package com.wxw.cloud.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author WXW
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_pay_log")
public class PayLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 订单号
     */
    @TableId(value = "order_id")
    private Long orderId;

    /**
     * 支付金额（分）
     */
    private Long totalFee;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 支付交易号码
     */
    private String transactionId;

    /**
     * 交易状态，1 未支付, 2已支付, 3 已退款, 4 支付错误, 5 已关闭
     */
    private Integer status;

    /**
     * 支付方式，1 支付宝支付, 2 货到付款
     */
    private Integer payType;

    /**
     * 银行类型
     */
    private String bankType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 关闭时间
     */
    private LocalDateTime closedTime;

    /**
     * 退款时间
     */
    private LocalDateTime refundTime;


}
