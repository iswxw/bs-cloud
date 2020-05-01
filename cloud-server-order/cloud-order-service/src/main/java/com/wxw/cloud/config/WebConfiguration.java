package com.wxw.cloud.config;

import com.wxw.cloud.interceptor.LoginOrderInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author: wxw
 * @create: 2020-04-23-1:14
 * Spring MVC 注解配置
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Resource
    private LoginOrderInterceptor loginInterceptor;

    /**
     * 定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
    }
}
