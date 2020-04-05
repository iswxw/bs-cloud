package com.wxw.cloud.controller;


import com.wxw.cloud.domain.SpecGroup;
import com.wxw.cloud.domain.SpecParam;
import com.wxw.cloud.service.ISpecParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 规格参数组下的参数名 前端控制器
 * </p>
 *
 * @author WXW
 * @since 2020-04-03
 */
@Api(tags = "SpecParamController",description = "规格参数管理")
@RestController
@RequestMapping("/spec")
public class SpecParamController {

    @Autowired
    private ISpecParamService specParamService;


    @ApiOperation("根据条件查询规格参数")
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(
            @RequestParam(value = "gid",required = false)Long gid,
            @RequestParam(value = "cid",required = false)Long cid,
            @RequestParam(value = "generic",required = false)Boolean generic,
            @RequestParam(value = "searching",required = false)Boolean searching
    ){
        List<SpecParam> params = this.specParamService.queryParams(gid,cid,generic,searching);
        if (CollectionUtils.isEmpty(params)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }

    @Transactional
    @ApiOperation("规格参数新增")
    @PostMapping("param")
    public ResponseEntity<Void> saveParam(@RequestBody SpecParam specParam){
        this.specParamService.save(specParam);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @ApiOperation("规格参数更新")
    @PutMapping("param")
    public ResponseEntity<Void> updateParam(@RequestBody SpecParam specParam){
        this.specParamService.updateById(specParam);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @ApiOperation("根据id删除规格参数")
    @DeleteMapping("param/{id}")
    public ResponseEntity<Void> deleteParam(@PathVariable("id")Long id){
        this.specParamService.removeById(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }






}

