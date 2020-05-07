package com.wxw.cloud.filter;

import cn.hutool.json.JSONUtil;
import com.wxw.cloud.config.FilterProperties;
import com.wxw.cloud.config.JwtProperties;
import com.wxw.cloud.tools.CookieUtils;
import com.wxw.cloud.tools.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

/**
 * @Author: wxw
 * @create: 2020-04-19-22:04
 */
@Component
@Slf4j
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class LoginFilter implements GlobalFilter, Ordered {

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private FilterProperties filterProperties;

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
        // 获取白名单
        List<String> allowsPaths = this.filterProperties.getAllowPaths();
        // 获取请求路径
        Map<String, Object> attributes = exchange.getAttributes();
        //PreserveHostHeaderGatewayFilter就是在gateway转发请求的时候把原始请求的host头部带上，
        // 转发给目标服务。默认该filter是启用的。
        attributes.put(PRESERVE_HOST_HEADER_ATTRIBUTE,true);
        String requestUrl = attributes.get(GATEWAY_ORIGINAL_REQUEST_URL_ATTR).toString();
        log.info("请求URI地址：{},白名单信息：{}", requestUrl, JSONUtil.toJsonStr(allowsPaths));
        for (String allowsPath : allowsPaths) {
            if (StringUtils.contains(requestUrl,allowsPath)){
                log.info("绿色通道",requestUrl);
                return chain.filter(exchange);
            }
        }
        // 1.获取请求参数access-token
        String token = request.getQueryParams().getFirst(this.jwtProperties.getCookieName());
        log.info("入参token=>{}", token);
        if (StringUtils.isBlank(token)){
            //3.如果不存在 : 认证失败
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete(); // 请求结束
        }
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
        return 2;
    }
}
