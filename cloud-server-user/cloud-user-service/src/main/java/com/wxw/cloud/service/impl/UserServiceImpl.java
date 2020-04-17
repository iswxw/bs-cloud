package com.wxw.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxw.cloud.dao.UserMapper;
import com.wxw.cloud.domain.User;
import com.wxw.cloud.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-04-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Boolean checkUser(String data, Integer type) {
        QueryWrapper user = new QueryWrapper();
        if (type == 1){
            user.eq("username",data);
        }else if (type == 2){
            user.eq("phone", data);
        }else {
            return null;
        }
        return this.userMapper.selectCount(user) == 0;
    }
}
