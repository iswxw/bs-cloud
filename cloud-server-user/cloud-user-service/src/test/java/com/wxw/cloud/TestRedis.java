package com.wxw.cloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wxw
 * @create: 2020-04-17-22:11
 */
@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void TestRedisString() {
        //存储数据
        this.redisTemplate.opsForValue().set("K1", "V1");
        // 获取数据
        String V1 = this.redisTemplate.opsForValue().get("K1").toString();
        System.out.println("V1 = " + V1);
    }

    @Test
    public void TestRedisTime() {
        // 存储数据 并指定剩余生命时间5小时
        this.redisTemplate.opsForValue().set("K2", "V2", 50, TimeUnit.SECONDS);
    }

    /**
     * 清空表
     */
    @Test
    public void Testfulsh() {
        RedisConnectionFactory factory = this.redisTemplate.getConnectionFactory();
        RedisConnection connection = factory.getConnection();
        connection.flushDb();
    }


    /**
     * redis 操作对象
     */
    @Test
    public void testHash() {
        BoundHashOperations<String, Object, Object> hashOps =
                this.redisTemplate.boundHashOps("user");
        // 操作hash数据
        hashOps.put("name", "jack");
        hashOps.put("age", "21");

        // 获取单个数据
        Object name = hashOps.get("name");
        System.out.println("name = " + name);

        // 获取所有数据
        Map<Object, Object> map = hashOps.entries();
        for (Map.Entry<Object, Object> me : map.entrySet()) {
            System.out.println(me.getKey() + " : " + me.getValue());
        }
    }
}