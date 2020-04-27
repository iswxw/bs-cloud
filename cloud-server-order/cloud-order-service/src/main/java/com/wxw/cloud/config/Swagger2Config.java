package com.wxw.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * @Author: wxw
 * @create: 2020-03-15-0:24
 * Swagger2API文档的配置
 *  bs.wxw.com:8083/swagger-ui.html
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

    private static final String somePath = "com.wxw.cloud.controller";
    /**
     * 创建API应用
     * api() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     * @return
     */
    @Bean
    public Docket createRstApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(somePath))
                .paths(PathSelectors.any())
                .build();
    }

    // 构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("订单系统API接口")
                .description("Welcome in http://bs.wxw.com:8089/swagger-ui.html")
                .contact("订单系统微服务")
                .version("2.0")
                .build();
    }
}
