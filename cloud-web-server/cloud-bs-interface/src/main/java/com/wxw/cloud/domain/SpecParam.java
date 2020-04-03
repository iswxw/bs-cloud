package com.wxw.cloud.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 规格参数组下的参数名
 * </p>
 *
 * @author WXW
 * @since 2020-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_spec_param")
public class SpecParam implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品分类id
     */
    private Long cid;

    private Long groupId;

    /**
     * 参数名
     */
    private String name;

    /**
     * 是否是数字类型参数，true或false
     * 关键字处理办法如下
     */
    @TableField(value = "`numeric`")
    private Boolean numeric;

    /**
     * 数字类型参数的单位，非数字类型可以为空
     */
    private String unit;

    /**
     * 是否是sku通用属性，true或false
     */
    private Boolean generic;

    /**
     * 是否用于搜索过滤，true或false
     */
    private Boolean searching;

    /**
     * 数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0
     */
    private String segments;


}
