package com.wxw.cloud.controller;


import com.wxw.cloud.bo.SpuBO;
import com.wxw.cloud.result.PageResult;
import com.wxw.cloud.service.IGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8 前端控制器
 * spu_detail
 * </p>
 *
 * @author WXW
 * @since 2020-04-04
 */
@Api(tags = "GoodsController",description = "商品管理")
@RestController
@RequestMapping("/spu")
public class GoodsController {

    @Resource
    private IGoodsService goodsService;

    @GetMapping("page")
    @ApiOperation("根据条件分页查询SPU")
    public ResponseEntity<PageResult<SpuBO>> querySpuByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "saleable",required = false)Boolean saleable,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows
    ){
        PageResult<SpuBO> result = this.goodsService.querySpuByPage(key,saleable,page,rows);
        if (result == null || CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


}

