package com.wxw.cloud.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 库存表，代表库存，秒杀库存等信息
 * </p>
 *
 * @author WXW
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_stock")
public class Stock implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 库存对应的商品sku id
     */
    @TableId(value = "sku_id", type = IdType.NONE)
    private Long skuId;

    /**
     * 可秒杀库存
     */
    private Integer seckillStock;

    /**
     * 秒杀总数量
     */
    private Integer seckillTotal;

    /**
     * 库存数量
     */
    private Integer stock;


}
