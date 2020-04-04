package com.wxw.cloud.service;

import com.wxw.cloud.bo.SpuBO;
import com.wxw.cloud.domain.SpuDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxw.cloud.result.PageResult;

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
}
