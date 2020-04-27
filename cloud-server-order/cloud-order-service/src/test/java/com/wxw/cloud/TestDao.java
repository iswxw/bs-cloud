package com.wxw.cloud;

import com.wxw.cloud.dao.AddressMapper;
import com.wxw.cloud.domain.Address;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author: wxw
 * @create: 2020-04-27-11:51
 */
@SpringBootTest
public class TestDao {

    @Resource
    private AddressMapper addressMapper;

    @Test
    public void TestSucces(){
        Address add = new Address();
        add.setId(1L);
        add.setAddress("北京市 大兴区");
        addressMapper.updateById(add);
    }

}
