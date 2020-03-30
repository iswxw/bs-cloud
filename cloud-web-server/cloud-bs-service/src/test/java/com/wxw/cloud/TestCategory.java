package com.wxw.cloud;

import com.wxw.cloud.domain.Category;
import com.wxw.cloud.service.ICategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;

/**
 * @Author: wxw
 * @create: 2020-03-30-14:33
 */
@SpringBootTest
public class TestCategory {

    @Resource
    private ICategoryService categoryService;


   // 根据ID判断，已经存在就更新节点负责新增节点
    @Test
    public void TestsoavOrUpdate(){

        Category category = new Category(0L,"农机",0L,true,3);
        boolean flag = categoryService.saveOrUpdate(category);
        System.out.println("flag = " + flag);
    }

    // 测试根据ID删除节点
    @Test
    public void testDeleteById(){
        boolean b = categoryService.removeById(2);
        System.out.println(b);
    }

    @Test
    public void TestResponstity(){
        ResponseEntity<String> entity = new ResponseEntity<>("result[]",HttpStatus.CREATED);
        System.out.println("entity = " + entity);
        System.out.println("ResponseEntity.ok(\"code\") = " + ResponseEntity.ok("code"));
    }
}
