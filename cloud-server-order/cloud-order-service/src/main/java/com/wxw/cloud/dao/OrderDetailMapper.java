package com.wxw.cloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxw.cloud.domain.Order;
import com.wxw.cloud.domain.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单详情表 Mapper 接口
 * </p>
 *
 * @author WXW
 * @since 2020-04-27
 */
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    /**
     * 分页查询订单
     * @param userId
     * @param status
     * @return
     */
    List<Order> queryOrderList(@Param("userId") Long userId, @Param("status") Integer status);
}
