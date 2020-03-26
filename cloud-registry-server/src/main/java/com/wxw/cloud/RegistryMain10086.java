package com.wxw.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: wxw
 * @create: 2020-03-26-22:06
 */
@EnableEurekaServer
@SpringBootApplication
public class RegistryMain10086 {
    public static void main(String[] args) {
        SpringApplication.run(RegistryMain10086.class, args);
    }
}
