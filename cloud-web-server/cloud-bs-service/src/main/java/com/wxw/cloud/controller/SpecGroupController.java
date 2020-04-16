package com.wxw.cloud.controller;


import com.wxw.cloud.domain.SpecGroup;
import com.wxw.cloud.service.ISpecGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 规格参数的分组表，每个商品分类下有多个规格参数组 前端控制器
 * </p>
 *
 * @author WXW
 * @since 2020-04-03
 */
@Api(tags = "SpecGroupController",description = "规格参数组管理")
@RestController
@RequestMapping("/spec")
@Slf4j
public class SpecGroupController {

    @Autowired
    private ISpecGroupService specGroupService;

    @ApiOperation("根据分类cid 查询参数组")
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid")Long cid){
        List<SpecGroup> groups = this.specGroupService.queryGroupsByCid(cid);
        if(CollectionUtils.isEmpty(groups)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }

    @ApiOperation("规格参数分组新增")
    @PostMapping("group")
    public ResponseEntity<Void> saveGroup(@RequestBody SpecGroup specGroup){
        log.info("category入参=>{}",specGroup);
        this.specGroupService.saveGroup(specGroup);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @ApiOperation("规格参数分组更新")
    @PutMapping("group")
    public ResponseEntity<Void> updateGroup(@RequestBody SpecGroup specGroup){
        log.info("category入参=>{}",specGroup);
        this.specGroupService.updateGroup(specGroup);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    @ApiOperation("根据组ID删除规格参数分组")
    @DeleteMapping("group/{gid}")
    public ResponseEntity<Void> deleteByGid(@PathVariable("gid")Long gid){
        this.specGroupService.removeById(gid);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    @ApiOperation("根据分类Id查询组下规格参数")
    @GetMapping("group/param/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupswithParam(@PathVariable("cid")Long cid){
        List<SpecGroup> groups = this.specGroupService.queryGroupswithParam(cid);
        if (CollectionUtils.isEmpty(groups)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }

}

