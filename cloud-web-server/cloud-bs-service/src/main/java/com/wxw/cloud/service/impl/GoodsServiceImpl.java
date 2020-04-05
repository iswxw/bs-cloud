package com.wxw.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxw.cloud.bo.SpuBO;
import com.wxw.cloud.dao.*;
import com.wxw.cloud.domain.*;
import com.wxw.cloud.result.PageResult;
import com.wxw.cloud.service.ICategoryService;
import com.wxw.cloud.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private StockMapper stockMapper;

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

    /**
     *  新增商品
     * @param spuBO
     */
    @Transactional
    @Override
    public void saveGoods(SpuBO spuBO) {

        // 新增spu
        spuBO.setId(null);
        spuBO.setSaleable(true);
        spuBO.setValid(true);
        spuBO.setCreateTime(LocalDateTime.now());
        spuBO.setLastUpdateTime(spuBO.getCreateTime());
        this.spuMapper.insert(spuBO);

        // 新增spuDetail
        SpuDetail spuDetail = spuBO.getSpuDetail();
        spuDetail.setSpuId(spuBO.getId());
        this.spuDetailMapper.insert(spuDetail);
        // 新增sku and stock
        this.saveSkuAndStock(spuBO);
    }
    // 新增sku and stock
    private void saveSkuAndStock(SpuBO spuBO) {
        spuBO.getSkus().forEach(sku -> {
            // 新增sku
            sku.setId(null);
            sku.setSpuId(spuBO.getId());
            sku.setCreateTime(LocalDateTime.now());
            sku.setLastUpdateTime(sku.getCreateTime());
            this.skuMapper.insert(sku);
            // 新增stock
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            this.stockMapper.insert(stock);

        });
    }

    /**
     * 根据spuId查询SpuDetail信息
     */
    @Override
    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        return this.spuDetailMapper.selectById(spuId);
    }

    @Override
    public List<Sku> querySkusBySpuId(Long spuId) {
       QueryWrapper<Sku> wrapper = new QueryWrapper<>();
       wrapper.eq("spu_id", spuId);
        List<Sku> skus = this.skuMapper.selectList(wrapper);
        skus.forEach(sku -> {
            Stock stock = this.stockMapper.selectById(sku.getId());
            sku.setStock(stock.getStock());
        });
        return skus;
    }

    /**
     * 修改商品信息
     * @param spuBO
     */
    @Transactional
    @Override
    public void updateGoods(SpuBO spuBO) {

        // 根据spuId 查询要删除的sku
        QueryWrapper<Sku> wrapper = new QueryWrapper<>();
        wrapper.eq("spu_id", spuBO.getId());
        List<Sku> skus = this.skuMapper.selectList(wrapper);
        skus.forEach(sku -> {
            // 删除 stock
            this.stockMapper.deleteById(sku.getId());
        });

        // 删除sku
        QueryWrapper<Sku> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("spu_id", spuBO.getId());
        this.skuMapper.delete(wrapper1);

        // 新增sku and stock
        this.saveSkuAndStock(spuBO);
        // 更新 spu和spuDetail
        spuBO.setCreateTime(null);
        spuBO.setLastUpdateTime(LocalDateTime.now());
        spuBO.setValid(null);
        spuBO.setSaleable(null);
        this.spuMapper.updateById(spuBO);
        this.spuDetailMapper.updateById(spuBO.getSpuDetail());
    }

}
