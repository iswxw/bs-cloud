package com.wxw.cloud.interceptor;

import com.wxw.cloud.config.JwtProperties;
import com.wxw.cloud.domain.UserInfo;
import com.wxw.cloud.tools.CookieUtils;
import com.wxw.cloud.tools.JwtUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wxw
 * @create: 2020-04-27-17:37
 */
@Component
@EnableConfigurationProperties({JwtProperties.class})
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private JwtProperties jwtProperties;

    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取cookie 中的token
        String token = CookieUtils.getCookieValue(request, this.jwtProperties.getCookieName());
        // 解析token, 获取用户信息
        UserInfo userInfo = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
        if (userInfo == null){
            return false;
        }
        // 将userinfo 存入线程本地变量中
        THREAD_LOCAL.set(userInfo);
        return true;
    }

    public static UserInfo getUserinfo(){
        return THREAD_LOCAL.get();
    }

    // 完成方法中释放资源
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空线程的局部变量，因为使用的是tomcat的线程池，线程不会结束，资源也就不会释放||GC 线程的局部变量
        THREAD_LOCAL.remove();
    }
}
