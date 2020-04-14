package com.wxw.cloud.domain;

import com.wxw.cloud.result.PageResult;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: wxw
 * @create: 2020-04-14-23:11
 */

@Data
public class SearchResult extends PageResult<Goods> {

    private List<Map<String,Object>> categories;

    private List<Brand> brands;

    public SearchResult(Long total, List<Goods> items, List<Map<String, Object>> categories, List<Brand> brands) {
        super(total, items);
        this.categories = categories;
        this.brands = brands;
    }

    public SearchResult(Long total, Long totalPage, List<Goods> items, List<Map<String, Object>> categories, List<Brand> brands) {
        super(total, totalPage, items);
        this.categories = categories;
        this.brands = brands;
    }

    public SearchResult(List<Map<String, Object>> categories, List<Brand> brands) {
        this.categories = categories;
        this.brands = brands;
    }
}
