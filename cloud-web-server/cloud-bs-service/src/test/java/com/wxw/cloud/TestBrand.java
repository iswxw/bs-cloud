package com.wxw.cloud;

import com.wxw.cloud.domain.Brand;
import com.wxw.cloud.result.PageResult;
import com.wxw.cloud.service.IBrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: wxw
 * @create: 2020-03-30-0:26
 */
@SpringBootTest
public class TestBrand {

    @Autowired
    private IBrandService brandService;

    @Test
    public void testBrandPage(){
        PageResult<Brand> result = brandService.queryBrandsByPage("苹", 1, 5,"id", true);
        System.out.println("result.getItems() = " + result.getItems());
        System.out.println("result.getTotal() = " + result.getTotal());
        System.out.println("result.getTotalPage() = " + result.getTotalPage());
    }
}
