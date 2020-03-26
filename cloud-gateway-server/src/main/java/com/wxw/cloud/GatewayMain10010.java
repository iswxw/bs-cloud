package com.wxw.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: wxw
 * @create: 2020-03-26-22:26
 */
@EnableEurekaClient
@SpringBootApplication
public class GatewayMain10010 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain10010.class, args);
    }
}
