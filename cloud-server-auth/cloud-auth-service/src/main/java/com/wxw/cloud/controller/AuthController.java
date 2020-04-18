package com.wxw.cloud.controller;

import com.wxw.cloud.service.AuthService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @Author: wxw
 * @create: 2020-04-19-0:16
 */
@Controller
public class AuthController {

    @Resource
    private AuthService authService;


}
