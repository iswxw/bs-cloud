package com.wxw.cloud;

import com.wxw.cloud.dao.UserMapper;
import com.wxw.cloud.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author: wxw
 * @create: 2020-04-17-16:20
 */
@SpringBootTest
public class TestDao {

    @Resource
    private UserMapper userMapper;


    @Test
    public void testUser(){
        User user = userMapper.selectById(1);
        System.out.println("user = " + user);
    }
}
