package com.wxw.cloud.service.impl;

import com.wxw.cloud.domain.OrderStatus;
import com.wxw.cloud.dao.OrderStatusMapper;
import com.wxw.cloud.service.IOrderStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单状态表 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-04-27
 */
@Service
public class OrderStatusServiceImpl extends ServiceImpl<OrderStatusMapper, OrderStatus> implements IOrderStatusService {

}
