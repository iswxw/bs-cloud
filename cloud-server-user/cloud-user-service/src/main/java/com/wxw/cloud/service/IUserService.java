package com.wxw.cloud.service;

import cn.hutool.captcha.LineCaptcha;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxw.cloud.domain.User;

import java.io.OutputStream;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author WXW
 * @since 2020-04-17
 */
public interface IUserService extends IService<User> {

    Boolean checkUser(String data, Integer type);

    LineCaptcha saveVerifyCode();

    void register(User user, String code);

    User queryUser(String username, String password);

    List<User> getUserList();
}
