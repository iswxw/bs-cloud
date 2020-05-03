package com.wxw.cloud;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alipay.api.AlipayClient;
import com.wxw.cloud.config.AliPayProperties;
import com.wxw.cloud.config.JwtProperties;
import com.wxw.cloud.tools.IdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author: wxw
 * @create: 2020-04-27-16:20
 */
@EnableConfigurationProperties(value = {AliPayProperties.class, JwtProperties.class})
@SpringBootTest
public class TestProperties {

    @Resource
    private AliPayProperties aliPayProperties;

    @Resource
    private JwtProperties jwtProperties;

    /**
     *  测试 属性配置
     */
    @Test
    public void TestProp(){
        System.out.println(aliPayProperties.getAppId());
        AlipayClient build = aliPayProperties.build();
        System.out.println(build.toString());

        String cookieName = jwtProperties.getCookieName();
        System.out.println("cookieName = " + cookieName);
        System.out.println("jwtProperties = " + jwtProperties.getPubKeyPath());


    }



    @Test
    public void TestUUID(){
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        long nextId = snowflake.nextId();
        System.out.println("nextId = " + nextId);
        System.out.println("nextId = " +"1254703554690682880".length());
        System.out.println("nextId = " +"1048108312895295488".length());
    }



}
