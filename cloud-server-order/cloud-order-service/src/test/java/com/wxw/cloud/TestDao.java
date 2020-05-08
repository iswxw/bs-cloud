package com.wxw.cloud;

import com.wxw.cloud.dao.AddressMapper;
import com.wxw.cloud.dao.OrderStatusMapper;
import com.wxw.cloud.domain.Address;
import com.wxw.cloud.domain.OrderStatus;
import com.wxw.cloud.service.IAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wxw
 * @create: 2020-04-27-11:51
 */
@SpringBootTest
public class TestDao {

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private IAddressService addressService;

    @Resource
    private OrderStatusMapper orderStatusMapper;

    @Test
    public void TestSucces(){
        Address add = new Address();
        add.setId(1L);
        add.setAddress("北京市 大兴区");
        addressMapper.updateById(add);
    }

    // 测试订单状态
    @Test
    public void testStatus(){
        OrderStatus orderStatus = this.orderStatusMapper.selectById(1257642387350622208l);
        System.out.println(orderStatus);
    }

    @Test
    public void TestAddress(){
        List<Address> addresses = addressService.getAddressList();
        System.out.println("addresses = " + addresses);
    }

}
