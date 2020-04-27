package com.wxw.cloud.service.impl;

import com.alipay.api.AlipayApiException;
import com.wxw.cloud.service.AlipayService;
import org.springframework.stereotype.Service;

/**
 * 支付实现类
 * @Author: wxw
 * @create: 2020-04-28-1:24
 */
@Service
public class AlipayServiceImpl implements AlipayService {

    /** 调取支付宝接口 web端支付*/


    @Override
    public String webPagePay(String orderId, Integer totalPay, String title) throws Exception {
        return null;
    }

    @Override
    public String refund(String orderId, String refundReason, Integer totalPay, String reqNo) throws AlipayApiException {
        return null;
    }

    @Override
    public String query(String orderId) throws AlipayApiException {
        return null;
    }

    @Override
    public String close(String orderId) throws AlipayApiException {
        return null;
    }

    @Override
    public String refundQuery(String orderId, String reqNo) throws AlipayApiException {
        return null;
    }
}
