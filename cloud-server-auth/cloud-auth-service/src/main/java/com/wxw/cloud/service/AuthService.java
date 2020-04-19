package com.wxw.cloud.service;

import com.wxw.cloud.config.JwtProperties;
import com.wxw.cloud.domain.User;
import com.wxw.cloud.domain.UserInfo;
import com.wxw.cloud.rpc.UserRpc;
import com.wxw.cloud.tools.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: wxw
 * @create: 2020-04-19-0:18
 */
@Slf4j
@Service
public class AuthService {

    @Resource
    private UserRpc userRpc;

    @Resource
    private JwtProperties jwtProperties;

    public String accredit(String username, String passward) {
        // 1.根据用户名和密码查询
        User user = this.userRpc.volidCode(username, passward);
        // 2.判断user
        if (user == null){
            return null;
        }
        try {
            // 3. JwtUtils生成Jwt类型的token
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setUsername(user.getUsername());
            return JwtUtils.generateToken(userInfo,this.jwtProperties.getPrivateKey(),this.jwtProperties.getExpire()*60);
        } catch (Exception e) {
            log.info("鉴权中心生成token时发生异常：{}", e);
        }
        return null;
    }
}
