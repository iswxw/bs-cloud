package com.wxw.cloud;

import com.wxw.cloud.dao.UserMapper;
import com.wxw.cloud.domain.User;
import com.wxw.cloud.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wxw
 * @create: 2020-04-17-16:20
 */
@SpringBootTest
public class TestDao {

    @Resource
    private UserMapper userMapper;

    @Resource
    private IUserService userService;

    @Test
    public void testUser(){
        User user = userMapper.selectById(1);
        System.out.println("user = " + user);
    }

    @Test
    public void testList(){
        List<User> userList = this.userService.getUserList();
        System.out.println("user = " + userList);
    }

}
