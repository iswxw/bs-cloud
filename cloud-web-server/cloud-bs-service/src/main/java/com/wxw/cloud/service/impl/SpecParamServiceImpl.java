package com.wxw.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxw.cloud.domain.SpecGroup;
import com.wxw.cloud.domain.SpecParam;
import com.wxw.cloud.dao.SpecParamMapper;
import com.wxw.cloud.service.ISpecParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 规格参数组下的参数名 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-04-03
 */
@Service
public class SpecParamServiceImpl extends ServiceImpl<SpecParamMapper, SpecParam> implements ISpecParamService {

    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public List<SpecParam> queryParams(Long gid) {
        QueryWrapper<SpecParam> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id",gid);
        return this.specParamMapper.selectList(wrapper);
    }

}
