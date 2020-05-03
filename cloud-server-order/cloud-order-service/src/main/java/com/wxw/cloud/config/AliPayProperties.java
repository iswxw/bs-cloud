package com.wxw.cloud.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: wxw
 * @create: 2020-04-27-15:36
 */
@Getter
@Setter
@ToString
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

    // 字符类型
    private String format;

    // 网关地址
    private String gatewayUrl;

    private AlipayClient alipayClient;

    public AlipayClient build(){
        this.alipayClient = new DefaultAlipayClient(
                getGatewayUrl(),
                getAppId(),
                getPrivateKey(),
                getFormat(),
                getCharset(),
                getPublicKey(),
                getSignType());
        return alipayClient;
    }

}