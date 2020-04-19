package com.wxw.cloud.filter;

import com.wxw.cloud.config.JwtProperties;
import com.wxw.cloud.tools.CookieUtils;
import com.wxw.cloud.tools.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: wxw
 * @create: 2020-04-19-22:04
 */
@Component
@Slf4j
@EnableConfigurationProperties(JwtProperties.class)
public class LoginFilter implements GlobalFilter, Ordered {

    @Resource
    private JwtProperties jwtProperties;

    /**
     * 执行过滤器中的业务逻辑
     *     对请求参数中的access-token进行判断
     *      如果存在此参数:代表已经认证成功
     *      如果不存在此参数 : 认证失败.
     *  ServerWebExchange : 相当于请求和响应的上下文(zuul中的RequestContext)
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取request对象
        ServerHttpRequest request = exchange.getRequest();
        // 1.获取请求参数access-token
        String token = request.getQueryParams().getFirst(this.jwtProperties.getCookieName());
        log.info("入参token=>{}", token);
        /*if (StringUtils.isBlank(token)){
            //3.如果不存在 : 认证失败
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete(); // 请求结束
        }*/
        try {
            JwtUtils.getInfoFromToken(token,this.jwtProperties.getPublicKey());
        } catch (Exception e) {
            log.info("解析token发生异常：{}",e);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete(); // 请求结束
        }
        //4.如果存在,继续执行
        return chain.filter(exchange); //继续向下执行
    }



    /**
     * 指定过滤器的执行顺序 , 返回值越小,执行优先级越高
     */
    @Override
    public int getOrder() {
        return 10;
    }
}
