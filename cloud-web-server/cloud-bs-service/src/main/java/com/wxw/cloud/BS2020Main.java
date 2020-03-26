package com.wxw.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: wxw
 * @create: 2020-03-26-23:35
 */
@EnableEurekaClient
@SpringBootApplication
public class BS2020Main {
    public static void main(String[] args) {
        SpringApplication.run(BS2020Main.class, args);
    }
}
