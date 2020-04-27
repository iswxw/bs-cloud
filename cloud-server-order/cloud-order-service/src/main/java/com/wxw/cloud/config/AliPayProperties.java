package com.wxw.cloud.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: wxw
 * @create: 2020-04-27-15:36
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "wxw.pay")
public class AliPayProperties {

    // appID
    private String appId;

    //商户公钥
    private String privateKey;

    // 支付宝公钥
    private String publicKey;

    // 支付宝异步通知结果
    private String notifyUrl;

    // 返回结果集
    private String returnUrl;

    // 签名类型
    private String signType;

    // 字符编码
    private String charset;

    // 网关地址
    private String gatewayUrl;


}