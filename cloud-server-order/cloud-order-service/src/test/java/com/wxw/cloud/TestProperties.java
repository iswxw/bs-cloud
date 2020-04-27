package com.wxw.cloud;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.wxw.cloud.config.AliPayProperties;
import com.wxw.cloud.tools.IdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author: wxw
 * @create: 2020-04-27-16:20
 */
@EnableConfigurationProperties(AliPayProperties.class)
@SpringBootTest
public class TestProperties {

    @Resource
    private AliPayProperties aliPayProperties;

    /**
     *  测试 属性配置
     */
    @Test
    public void TestProp(){
        System.out.println(aliPayProperties.getAppId());
        AliPayProperties build = aliPayProperties.build();
        System.out.println(build);
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
