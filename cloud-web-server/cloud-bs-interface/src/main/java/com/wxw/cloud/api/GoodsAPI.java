package com.wxw.cloud.api;

import com.wxw.cloud.bo.SpuBO;
import com.wxw.cloud.domain.Sku;
import com.wxw.cloud.domain.Spu;
import com.wxw.cloud.domain.SpuDetail;
import com.wxw.cloud.result.PageResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GoodsAPI {

    /**
     * 根据 spuId查询SKU集合
     */
    @GetMapping("sku/list")
    public List<Sku> querySkusBySpuId(@RequestParam("id")Long spuId);

    /**
     * 根据条件分页查询SPU
     */
    @GetMapping("spu/page")
    public PageResult<SpuBO> querySpuByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "saleable",required = false)Boolean saleable,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows
    );

    /**
     * 根据spuId查询SpuDetail信息
     */
    @GetMapping("spu/detail/{spuId}")
    public SpuDetail querySpuDetailBySpuId(@PathVariable("spuId")Long spuId);

    @GetMapping("{id}")
    public Spu querySpuById(@PathVariable("id")Long id);


}
