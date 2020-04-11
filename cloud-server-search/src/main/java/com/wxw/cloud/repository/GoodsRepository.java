package com.wxw.cloud.repository;

import com.wxw.cloud.domain.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {


}
