package com.wxw.cloud.controller;

import com.wxw.cloud.domain.Goods;
import com.wxw.cloud.domain.SearchRequest;
import com.wxw.cloud.result.PageResult;
import com.wxw.cloud.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @Author: wxw
 * @create: 2020-04-12-21:38
 */
@Api(tags = "SearchController",description = "搜索微服务")
@Controller
public class SearchController {

    @Resource
    private SearchService searchService;

    @ApiOperation("基本搜索入口")
    @PostMapping("page")
    public ResponseEntity<PageResult<Goods>> search(@RequestBody SearchRequest searchRequest){
        PageResult<Goods> result=this.searchService.search(searchRequest);
        if (result==null|| CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}
