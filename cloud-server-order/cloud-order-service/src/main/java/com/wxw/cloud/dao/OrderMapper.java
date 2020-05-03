package com.wxw.cloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxw.cloud.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author WXW
 * @since 2020-04-27
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 分页查询订单
     * @param userId
     * @param status
     * @return
     */
    IPage<Order> queryOrderPage(Page<Order> page, @Param("userId") Long userId, @Param("status") Integer status);
}
