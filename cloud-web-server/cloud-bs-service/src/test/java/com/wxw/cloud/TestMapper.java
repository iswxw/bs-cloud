package com.wxw.cloud;

import cn.hutool.json.JSONUtil;
import com.wxw.cloud.dao.CategoryMapper;
import com.wxw.cloud.dao.StockMapper;
import com.wxw.cloud.domain.Category;
import com.wxw.cloud.domain.Stock;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wxw
 * @create: 2020-03-27-15:59
 */
@SpringBootTest
public class TestMapper {

    Logger LOGGER = LoggerFactory.getLogger(TestMapper.class);

    @Autowired
    private CategoryMapper categoryMapper;

    @Resource
    private StockMapper stockMapper;
    @Test
    public void findBrand(){
        Category category = categoryMapper.selectById(1);
        LOGGER.info("商品信息=>", JSONUtil.toJsonStr(category));
    }

    @Test
    public void testStock(){
        Stock stock = this.stockMapper.selectById(50);
        System.out.println(stock.getStock());
    }


}
