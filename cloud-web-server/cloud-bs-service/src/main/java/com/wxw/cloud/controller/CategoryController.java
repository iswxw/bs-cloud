package com.wxw.cloud.controller;


import com.wxw.cloud.domain.Category;
import com.wxw.cloud.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 前端控制器
 * </p>
 *
 * @author WXW
 * @since 2020-03-27
 */
@Api(tags = "CategoryController", description = "商品分类管理")
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Resource
    private ICategoryService categoryService;

    @ApiOperation("根据父节点的pid查询子节点类目信息")
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategorisById(@RequestParam(value = "pid",defaultValue = "0")Long pid){

            if (null == pid || pid < 0){
                // 400：参数不合法
                return ResponseEntity.badRequest().build();
            }
            List<Category> categories=this.categoryService.queryCategoriesById(pid);
            if (CollectionUtils.isEmpty(categories)){
                // 404 资源服务器未找到
               return ResponseEntity.notFound().build();
            }
            log.info("queryCategoriesById—result=>{}",categories);
            // 200 查询成功
            return ResponseEntity.ok(categories);
    }

    @ApiOperation("根据ID是否存在？是：更新节点   否：新增节点")
    @PostMapping("saveOrUpdate")
    public ResponseEntity<Void> saveOrUpdateCategory(@RequestBody Category category){
        log.info("category入参=>{}",category);
        boolean b = this.categoryService.updateById(category);
        if (!b){
            // 更新失败 说明不存在就新增
            this.categoryService.save(category);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    @ApiOperation("根据ID删除指定节点")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        log.info("节点ID入参=>{}", id);
        boolean flag = this.categoryService.removeById(id);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @ApiOperation("根据品牌信息查询商品分类列表")
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid")Long bid){
        List<Category> list=this.categoryService.queryByBrandId(bid);
        if (CollectionUtils.isEmpty(list)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

}

