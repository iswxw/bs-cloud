package com.wxw.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxw.cloud.domain.Category;

import java.util.List;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 服务类
 * </p>
 *
 * @author WXW
 * @since 2020-03-27
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 根据parentId查询子类目
     * @param pid
     * @return
     */
    List<Category> queryCategoriesById(Long pid);

    /**
     * 根据品牌ID 查询商品分类列表
     * @param bid
     * @return
     */
    List<Category> queryByBrandId(Long bid);

    /**
     * 根据多个分类ID查询 分类名称
     * @param ids
     * @return
     */
    public List<String> queryNamesByIds(List<Long> ids);

}
