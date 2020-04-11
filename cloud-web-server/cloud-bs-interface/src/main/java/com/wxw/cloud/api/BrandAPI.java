package com.wxw.cloud.api;


import com.wxw.cloud.domain.Brand;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WXW
 * @since 2020-03-28
 */
@RequestMapping("brand")
public interface BrandAPI {

    /**
     *  搜索微服务使用
     */
    @GetMapping("{id}")
    public Brand queryBrandById(@PathVariable("id")Long id);






}

