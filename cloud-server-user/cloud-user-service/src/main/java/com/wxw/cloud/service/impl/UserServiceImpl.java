package com.wxw.cloud.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxw.cloud.dao.UserMapper;
import com.wxw.cloud.domain.User;
import com.wxw.cloud.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxw.cloud.tools.CodecUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-04-17
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AmqpTemplate amqpTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String KEY_PREFIX="user:verify:";

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

    @Override
    public LineCaptcha saveVerifyCode() {
        // 1.生成图像
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, 4, 100);
        // 2.取得验证码
        String code = lineCaptcha.getCode();
        // 3，发送消息到mq
        Map<String,String> msg = new HashMap<>();
        msg.put("codeMQ",code);
        this.amqpTemplate.convertAndSend("WXW.SMS.EXCHANGE","verifycode.mq",msg);
        // 4，保存验证码到redis中 过期时间 5分钟
        this.stringRedisTemplate.opsForValue().set(KEY_PREFIX,code,5, TimeUnit.MINUTES);
        return lineCaptcha;
    }

    @Override
    public void register(User user, String code) {
        log.info("入参1 user =>{}", JSONUtil.toJsonStr(user));
        // 查询redis中的验证码
        String redisCode = this.stringRedisTemplate.opsForValue().get(KEY_PREFIX);
        // 1.校验验证码
         if (!StringUtils.equals(code, redisCode)){
             return;
         }
        // 2，生成盐
        String salt = CodecUtils.generateSalt();
         user.setSalt(salt);
        // 3,加盐加密
        user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));
        // 4,新增用户
        user.setId(null);
        user.setCreated(LocalDateTime.now());
        this.userMapper.insert(user);
        // 删除验证码
    }

    @Override
    public User queryUser(String username, String password) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        User user = this.userMapper.selectOne(wrapper);
        // 判断user是否为空
        if (user == null) {
            return null;
        }
        // 获取盐，对用户输入的密码加盐加密
        password = CodecUtils.md5Hex(password, user.getSalt());
        // 数据库中的密码比较
        if (StringUtils.equals(password, user.getPassword())) {
            return user;
        }
        return null;
    }

}
