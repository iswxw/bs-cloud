package com.wxw.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: wxw
 * @create: 2020-04-18-23:15
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class}) // 排除启动默认数据源
@EnableDiscoveryClient
@EnableFeignClients
public class AuthMain8087 {
    public static void main(String[] args) {
        SpringApplication.run(AuthMain8087.class, args);
    }
}
