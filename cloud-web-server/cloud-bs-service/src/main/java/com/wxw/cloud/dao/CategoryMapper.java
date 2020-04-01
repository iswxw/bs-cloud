package com.wxw.cloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxw.cloud.domain.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 Mapper 接口
 * </p>
 *
 * @author WXW
 * @since 2020-03-27
 */
public interface CategoryMapper extends BaseMapper<Category> {


    /**
     * 根据品牌id查询商品分类集
     * @param bid
     * @return
     */
    @Select("SELECT * FROM tb_category WHERE id IN (SELECT category_id FROM tb_category_brand WHERE brand_id=#{bid})")
    List<Category> queryByBrandId(Long bid);
}
