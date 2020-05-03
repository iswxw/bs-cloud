package com.wxw.cloud.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("tb_seckill_order")
public class SeckillOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 秒杀编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 秒杀用户
     */
    @TableField("userId")
    private Long userId;

    /**
     * 商品ID
     */
    @TableField("skuId")
    private Long skuId;

    /**
     * 订单ID
     */
    @TableField("orderId")
    private Long orderId;


}
