package com.wxw.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxw.cloud.domain.SpecGroup;
import com.wxw.cloud.dao.SpecGroupMapper;
import com.wxw.cloud.service.ISpecGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 规格参数的分组表，每个商品分类下有多个规格参数组 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-04-03
 */
@Service
public class SpecGroupServiceImpl extends ServiceImpl<SpecGroupMapper, SpecGroup> implements ISpecGroupService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Override
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        QueryWrapper<SpecGroup> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("cid", cid);
        return this.specGroupMapper.selectList(queryWrapper);
    }
    @Transactional
    @Override
    public void saveGroup(SpecGroup specGroup) {
        this.specGroupMapper.insert(specGroup);
    }

    @Transactional
    @Override
    public void updateGroup(SpecGroup specGroup) {
        this.specGroupMapper.updateById(specGroup);
    }


}
