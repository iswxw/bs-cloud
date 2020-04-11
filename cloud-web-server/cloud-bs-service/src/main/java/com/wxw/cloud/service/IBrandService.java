package com.wxw.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxw.cloud.domain.Brand;
import com.wxw.cloud.result.PageResult;

import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务类
 * </p>
 *
 * @author WXW
 * @since 2020-03-28
 */
public interface IBrandService extends IService<Brand> {

    PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    void saveBrand(Brand brand, List<Long> cids);

    void updateBrand(Brand brand, List<Long> cids);

    void deleteBrand(Long brandId);

    List<Brand> queryBrandsByCid(Long cid);

    Brand queryBrandById(Long id);
}
