package com.wxw.cloud.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.wxw.cloud.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author WXW
 * @since 2020-04-17
 */
@Api(tags = "UserController",description = "用户中心")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private IUserService userService;

    @ApiOperation("校验数据是否可用")
    @GetMapping("/check/{data}/{type}")
    public ResponseEntity<Boolean> checkUser(@PathVariable("data")String data,@PathVariable("type")Integer type){
        Boolean bool = this.userService.checkUser(data,type);
        if (bool == null){
           return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    /**
     * 验证码功能
     */
    @ApiOperation("验证码图片生成")
   @GetMapping("validete")
    public void vetifyCode(HttpServletResponse response){
        OutputStream out = null;
       try {
           out = response.getOutputStream();// 取得输出流
           //定义图形验证码的长、宽、验证码字符数、干扰线宽度
           LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100,4, 100);
           String img = lineCaptcha.getImageBase64();
           String code = lineCaptcha.getCode();
           // 图形验证码写出，可以写出到文件，也可以写出到流
           lineCaptcha.write(out);
           //验证图形验证码的有效性，返回boolean值
           boolean verify = lineCaptcha.verify(code);
           log.info("验证码的有效性：{}", verify);
           out.flush(); // 将缓存中的数据立即强制刷新, 将缓冲区的数据输出到客户端浏览器
           out.close(); // 关闭输出流
       } catch (IOException e) {
           log.info("验证码生成异常信息：{}", e);
       }
   }

}

