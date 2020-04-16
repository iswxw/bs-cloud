package com.wxw.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: wxw
 * @create: 2020-04-16-15:37
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ClientMain8084 {
    public static void main(String[] args) {
        SpringApplication.run(ClientMain8084.class,args);
    }
}
