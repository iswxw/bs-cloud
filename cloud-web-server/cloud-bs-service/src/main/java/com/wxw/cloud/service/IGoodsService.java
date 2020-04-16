package com.wxw.cloud.service;

import com.wxw.cloud.bo.SpuBO;
import com.wxw.cloud.domain.Sku;
import com.wxw.cloud.domain.Spu;
import com.wxw.cloud.domain.SpuDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxw.cloud.result.PageResult;

import java.util.List;

/**
 * <p>
 *  商品管理接口
 * </p>
 *
 * @author WXW
 * @since 2020-04-04
 */
public interface IGoodsService {

    /**
     * 根据条件分页查询SPU
     */
    PageResult<SpuBO> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows);

    /**
     * 新增商品
     * @param spuBO
     */
    void saveGoods(SpuBO spuBO);

    /**
     * 根据spuId查询SpuDetail信息
     * @param spuId
     * @return
     */
    SpuDetail querySpuDetailBySpuId(Long spuId);

    /**
     * 根据 spuId查询SKU集合
     * @param spuId
     * @return
     */
    List<Sku> querySkusBySpuId(Long spuId);

    /**
     *  修改商品信息
     * @param spuBO
     */
    void updateGoods(SpuBO spuBO);

    /**
     *  根据spuId查询spu
     * @param id
     * @return
     */
    Spu querySpuById(Long id);
}
