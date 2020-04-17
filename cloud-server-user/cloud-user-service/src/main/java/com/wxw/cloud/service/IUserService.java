package com.wxw.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxw.cloud.domain.User;

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
}
