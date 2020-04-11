package com.wxw.cloud;

import com.wxw.cloud.bo.SpuBO;
import com.wxw.cloud.domain.Goods;
import com.wxw.cloud.repository.GoodsRepository;
import com.wxw.cloud.result.PageResult;
import com.wxw.cloud.rpc.GoodsClient;
import com.wxw.cloud.service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wxw
 * @create: 2020-04-11-22:26
 */
@SpringBootTest
public class ImportESDataTest {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Resource
    private GoodsRepository goodsRepository;

    @Resource
    private SearchService searchService;

    @Resource
    private GoodsClient goodsClient;

    @Test
    public void test() {

        this.elasticsearchTemplate.createIndex(Goods.class);
        this.elasticsearchTemplate.putMapping(Goods.class);
        Integer page = 1;
        Integer rows = 10;

        do {
            // 分页查询 获取分页结果集
            PageResult<SpuBO> result = this.goodsClient.querySpuByPage(null, null, page, rows);
            // 获取当前分页数据
            List<SpuBO> items = result.getItems();
            // 处理 List<SpuBO> => goodsList
            List<Goods> goodsList = items.stream().map(spuBO -> {
                try {
                    return this.searchService.buildGoods(spuBO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());

            //执行新增数据的方法
            this.goodsRepository.saveAll(goodsList);

            // 获取当前页的数据条数，如果是最后一页，没有100条
            rows = result.getItems().size();
            // 每次循环页码加1
            page++;
        } while (rows == 100);

    }


}
