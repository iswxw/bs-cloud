package com.wxw.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: wxw
 * @create: 2020-03-31-16:26
 */
@EnableEurekaClient
@SpringBootApplication
public class FileLoadMiain8082 {
    public static void main(String[] args) {
        SpringApplication.run(FileLoadMiain8082.class, args);
    }
}
