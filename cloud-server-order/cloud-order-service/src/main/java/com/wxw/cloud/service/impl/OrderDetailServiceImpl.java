package com.wxw.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxw.cloud.dao.OrderDetailMapper;
import com.wxw.cloud.domain.OrderDetail;
import com.wxw.cloud.service.IOrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-04-27
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
