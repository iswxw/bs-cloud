package com.wxw.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: wxw
 * @create: 2020-04-23-0:20
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class CartMain8088 {
    public static void main(String[] args) {
        SpringApplication.run(CartMain8088.class, args);
    }
}
