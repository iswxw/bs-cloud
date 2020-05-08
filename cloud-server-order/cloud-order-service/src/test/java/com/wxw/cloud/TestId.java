package com.wxw.cloud;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: wxw
 * @create: 2020-05-05-9:51
 */
@SpringBootTest
public class TestId {

    @Test
    public void testId(){
        String idStr = IdUtil.objectId().toUpperCase();
        System.out.println("id = " + idStr);
    }
}
