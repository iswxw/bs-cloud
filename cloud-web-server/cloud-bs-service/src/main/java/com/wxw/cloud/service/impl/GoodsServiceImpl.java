package com.wxw.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxw.cloud.bo.SpuBO;
import com.wxw.cloud.dao.BrandMapper;
import com.wxw.cloud.dao.CategoryMapper;
import com.wxw.cloud.dao.SpuMapper;
import com.wxw.cloud.domain.Brand;
import com.wxw.cloud.domain.Spu;
import com.wxw.cloud.domain.SpuDetail;
import com.wxw.cloud.dao.SpuDetailMapper;
import com.wxw.cloud.result.PageResult;
import com.wxw.cloud.service.ICategoryService;
import com.wxw.cloud.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  商品管理 实现类
 * </p>
 *
 * @author WXW
 * @since 2020-04-04
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Resource
    private SpuMapper spuMapper;

    @Resource
    private SpuDetailMapper spuDetailMapper;

    @Resource
    private BrandMapper brandMapper;

    @Resource
    private ICategoryService categoryService;

    /**
     * 根据条件分页查询SPU
     */
    @Override
    public PageResult<SpuBO> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows) {

        QueryWrapper<Spu> wrapper = new QueryWrapper<>();
        // 添加查询条件
        if (StringUtils.isNotBlank(key)){
            wrapper.like("title", key);
        }
        // 添加上下架的过滤条件
        if(saleable != null){
            wrapper.eq("saleable", saleable);
        }
        // 添加分页条件
        Page<Spu> pageParam = new Page<>(page,rows);
        // 执行查询获取SPU集合
        Page<Spu> spuPage = this.spuMapper.selectPage(pageParam, wrapper);
        List<Spu> spus = spuPage.getRecords();
        // SPU集合转化为SpuBO的集合
        List<SpuBO> spuBOs = spus.stream().map(spu -> {
            SpuBO spuBO = new SpuBO();
            BeanUtils.copyProperties(spu, spuBO);
            // 查询分类名称
            List<String> names = this.categoryService.queryNamesByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBO.setCname(StringUtils.join(names, "-"));
            Brand brand = this.brandMapper.selectById(spu.getBrandId());
            // 查询品牌名称
            spuBO.setBname(brand.getName());
            return spuBO;
        }).collect(Collectors.toList());
        // 返回PageResult<SpuBO>
        return new PageResult<>(spuPage.getTotal(),spuBOs);
    }
















}
