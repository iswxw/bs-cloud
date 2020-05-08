package com.wxw.cloud.controller;


import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.img.ImgUtil;
import com.wxw.cloud.domain.User;
import com.wxw.cloud.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author WXW
 * @since 2020-04-17
 */
@Api(tags = "UserController", description = "用户中心")
@RestController
@Slf4j
public class UserController {

    @Resource
    private IUserService userService;

    @ApiOperation("校验数据是否可用")
    @GetMapping("/check/{data}/{type}")
    public ResponseEntity<Boolean> checkUser(@PathVariable("data") String data, @PathVariable("type") Integer type) {
        Boolean bool = this.userService.checkUser(data, type);
        if (bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    /**
     * 验证码生成
     */
    @ApiOperation("验证码图像生成")
    @GetMapping("validete")
    public void vetifyCode(HttpServletResponse response) {
        OutputStream out = null;
        try {
            // 取得输出流
            out = response.getOutputStream();
            // 生成验证码图像
            LineCaptcha lineCaptcha = this.userService.saveVerifyCode();
            // 图形验证码写出到流
            //lineCaptcha.write(out);
            BufferedImage bufferedImage = lineCaptcha.getImage();
            ImageIO.write(bufferedImage,"PNG",out);
            out.flush(); // 将缓存中的数据立即强制刷新, 将缓冲区的数据输出到客户端浏览器
            out.close(); // 关闭输出流
        } catch (IOException e) {
            log.info("验证码生成异常信息：{}", e);
        }
    }

    @ApiOperation("用户注册")
    @PostMapping(value = "register",consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public ResponseEntity<Void> register(@Valid User user, @RequestParam("code")String code){
           log.info("入参1 user:{},入参2 code:{}",user,code);
           if (user ==null|| StringUtils.isBlank(code)){
               return ResponseEntity.badRequest().build();
           }
           this.userService.register(user,code);
           return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation("根据用户名和密码查询指定用户")
    @GetMapping("/user/query")
    public ResponseEntity<User> volidCode(@RequestParam("username") String username, @RequestParam("passward") String password) {
        User user = this.userService.queryUser(username, password);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    // 后台管理
    @ApiOperation("获取用户列表")
    @GetMapping("list")
    public ResponseEntity<List<User>> getUserList(){
        List<User> userList = this.userService.getUserList();
        if (CollectionUtils.isEmpty(userList)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userList);
    }

    @ApiOperation("获取用户量")
    @GetMapping("count")
    public ResponseEntity<Integer> getCount(){
        List<User> list = this.userService.list();
        return ResponseEntity.ok(list.size());
    }


}

