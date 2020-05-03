package com.wxw.cloud.service;

import com.wxw.cloud.domain.Address;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WXW
 * @since 2020-04-27
 */
public interface IAddressService extends IService<Address> {

    void addAddressByUserId(Address address);

    List<Address> queryAddressByUserId();

    void updateAddressByUserId(Address address);

    void deleteAddress(Long addressId);

    Address queryAddressById(Long addressId);
}
