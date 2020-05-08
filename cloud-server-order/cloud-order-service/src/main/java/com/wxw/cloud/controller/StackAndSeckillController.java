package com.wxw.cloud.controller;


import com.wxw.cloud.domain.Stock;
import com.wxw.cloud.service.IStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WXW
 * @since 2020-04-27
 */
@RestController
@Api(tags = "StackAndSeckillController",description = "秒杀及仓库微服务")
public class StackAndSeckillController {

    @Resource
    private IStockService stockService;

    @ApiOperation("获取库存列表")
    @GetMapping("/stock/list")
    public ResponseEntity<List<Stock>> getStockList(){
        List<Stock> stockList = this.stockService.list();
        if (CollectionUtils.isEmpty(stockList)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stockList);
    }
}

