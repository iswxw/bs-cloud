package com.wxw.cloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxw.cloud.domain.Stock;

/**
 * <p>
 * 库存表，代表库存，秒杀库存等信息 Mapper 接口
 * </p>
 *
 * @author WXW
 * @since 2020-04-05
 */
public interface StockMapper extends BaseMapper<Stock> {

    void reduceStock(Long skuId, Integer num);
}
