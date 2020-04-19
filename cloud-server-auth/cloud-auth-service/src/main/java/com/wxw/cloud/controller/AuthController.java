package com.wxw.cloud.controller;

import com.wxw.cloud.config.JwtProperties;
import com.wxw.cloud.domain.UserInfo;
import com.wxw.cloud.service.AuthService;
import com.wxw.cloud.tools.CookieUtils;
import com.wxw.cloud.tools.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wxw
 * @create: 2020-04-19-0:16
 */
@Slf4j
@Api(tags = "AuthController",description = "鉴权中心微服务")
@Controller
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

    @Resource
    private AuthService authService;

    @Resource
    private JwtProperties jwtProperties;

    @ApiOperation("获取授权token")
    @PostMapping("accredit")
    public ResponseEntity<Void> accredit(
            @RequestParam("username")String username,
            @RequestParam("passward")String passward,
            HttpServletRequest request,
            HttpServletResponse response){
        String token = this.authService.accredit(username,passward);
        if (StringUtils.isBlank(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        CookieUtils.setCookie(request,response,this.jwtProperties.getCookieName(), token,this.jwtProperties.getExpire()*60);
        return ResponseEntity.ok(null);
    }

    @ApiOperation("登录校验接口")
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verify(
            @CookieValue("BS_TOKEN")String token,
            HttpServletRequest request,
            HttpServletResponse response){
        try {
            // 通过JWT的工具类 使用公钥解析token
            UserInfo user = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
            if(user == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            // 刷新 JWT的有效时间
            token = JwtUtils.generateToken(user,this.jwtProperties.getPrivateKey(),this.jwtProperties.getExpire());
             // 刷新cookie的有效时间
            CookieUtils.setCookie(request, response, this.jwtProperties.getCookieName(),token,this.jwtProperties.getExpire()*60, null);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
          log.info("校验身份过程中发生异常：{}",e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
