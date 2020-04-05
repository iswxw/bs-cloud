package com.wxw.cloud.service.impl;

import com.wxw.cloud.domain.Stock;
import com.wxw.cloud.dao.StockMapper;
import com.wxw.cloud.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存表，代表库存，秒杀库存等信息 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-04-05
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

}
