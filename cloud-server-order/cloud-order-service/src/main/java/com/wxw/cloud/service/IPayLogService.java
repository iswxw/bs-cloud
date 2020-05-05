package com.wxw.cloud.service;

import com.wxw.cloud.domain.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WXW
 * @since 2020-04-27
 */
public interface IPayLogService extends IService<PayLog> {

    // 更新订单状态和流水日志
    Boolean updatePayLog(String out_trade_no,Integer trade_status, String trade_no, String total_amount);
}
