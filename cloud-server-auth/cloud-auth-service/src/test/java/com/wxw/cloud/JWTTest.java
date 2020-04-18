package com.wxw.cloud;

import com.wxw.cloud.domain.UserInfo;
import com.wxw.cloud.tools.JwtUtils;
import com.wxw.cloud.tools.RsaUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Author: wxw
 * @create: 2020-04-18-23:33
 */
@SpringBootTest
public class JWTTest {
    private static final String pubKeyPath = "F:\\Study_GO\\BS2020\\rsa\\rsa.pub";

    private static final String priKeyPath = "F:\\Study_GO\\BS2020\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    // 生成 公钥和私钥
    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    // 生成之前读取公钥和私钥  在每个测试方法之前执行。
    @BeforeEach
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU4NzIyNTQ3M30.XRyKDL-l7XGf2NssJAVWk3LX-zQPrvTMhXfrs1P6RBgK59N-beMaFRdytxJxwUQuLbcmWB7mbsuEJOvW4b3yw25GedxvJwHmudWFiNdrUs6SXPXPEvAb8ZnaK8VVqzPlhlewDRM1gCh3TfjkXCJLn5uHClDbiRVHIur0eRw8awA";
        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }



}
