package com.wxw.cloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxw.cloud.domain.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 库存表，代表库存，秒杀库存等信息 Mapper 接口
 * </p>
 *
 * @author WXW
 * @since 2020-04-05
 */
public interface StockMapper extends BaseMapper<Stock> {

    /**
     * 更新对应商品的库存,且库存必须大于0，否则回滚。
     * @param skuId
     * @param num
     */
    @Update("update tb_stock set stock = stock - #{num} where sku_id = #{skuId} and stock > 0")
    void reduceStock(@Param("skuId") Long skuId, @Param("num") Integer num);

}
