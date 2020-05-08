package com.wxw.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxw.cloud.domain.Address;
import com.wxw.cloud.dao.AddressMapper;
import com.wxw.cloud.domain.UserInfo;
import com.wxw.cloud.interceptor.LoginOrderInterceptor;
import com.wxw.cloud.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-04-27
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public void addAddressByUserId(Address address) {
        UserInfo userinfo = LoginOrderInterceptor.getUserinfo();
        address.setUserId(userinfo.getId());
        setDefaultAddress(address);
        this.addressMapper.insert(address);
    }

    @Override
    public List<Address> queryAddressByUserId() {
        UserInfo userinfo = LoginOrderInterceptor.getUserinfo();
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userinfo.getId());
        return this.addressMapper.selectList(wrapper);
    }

    @Override
    public void updateAddressByUserId(Address address) {
        UserInfo userInfo = LoginOrderInterceptor.getUserinfo();
        address.setUserId(userInfo.getId());
        setDefaultAddress(address);
        this.addressMapper.updateById(address);
    }

    @Override
    public void deleteAddress(Long addressId) {
        UserInfo userInfo = LoginOrderInterceptor.getUserinfo();
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userInfo.getId())
                .eq("id", addressId);
        this.addressMapper.delete(wrapper);
    }

    @Override
    public Address queryAddressById(Long addressId) {
        UserInfo userInfo = LoginOrderInterceptor.getUserinfo();
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userInfo.getId())
                .eq("id", addressId);
        return this.addressMapper.selectOne(wrapper);
    }

    @Override
    public List<Address> getAddressList() {
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        return this.addressMapper.selectList(wrapper);
    }

    public void setDefaultAddress(Address address){
        if (address.getDefaultAddress()){
            //如果将本地址设置为默认地址，那么该用户下的其他地址都应该是非默认地址
            List<Address> addressList = this.queryAddressByUserId();
            addressList.forEach(addressTemp -> {
                if (addressTemp.getDefaultAddress()){
                    addressTemp.setDefaultAddress(false);
                    this.addressMapper.updateById(addressTemp);
                }
            } );
        }
    }
}
