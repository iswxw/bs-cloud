package com.wxw.cloud.service;

import com.wxw.cloud.domain.SpecGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 规格参数的分组表，每个商品分类下有多个规格参数组 服务类
 * </p>
 *
 * @author WXW
 * @since 2020-04-03
 */
public interface ISpecGroupService extends IService<SpecGroup> {

    List<SpecGroup> queryGroupsByCid(Long cid);

    void saveGroup(SpecGroup specGroup);

    void updateGroup(SpecGroup specGroup);

    List<SpecGroup> queryGroupswithParam(Long cid);
}
