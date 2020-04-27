package com.wxw.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: wxw
 * @create: 2020-04-24-16:56
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OrderMian8089 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMian8089.class,args);
    }
}
