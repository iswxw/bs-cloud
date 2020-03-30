package com.wxw.cloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxw.cloud.domain.Category;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 Mapper 接口
 * </p>
 *
 * @author WXW
 * @since 2020-03-27
 */
public interface CategoryMapper extends BaseMapper<Category> {

}
