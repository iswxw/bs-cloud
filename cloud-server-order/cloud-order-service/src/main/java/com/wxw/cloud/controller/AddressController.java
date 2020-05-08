package com.wxw.cloud.controller;


import com.wxw.cloud.domain.Address;
import com.wxw.cloud.service.IAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author WXW
 * @since 2020-04-27
 */
@RestController
@Api(tags = "AddressController",description = "地址管理")
@RequestMapping("address")
public class AddressController {

    @Resource
    private IAddressService addressService;

    @ApiOperation("创建收获地址")
    @PostMapping
    public ResponseEntity<Void> addAddressByUserId(@RequestBody Address address){
        System.out.println(address.getDefaultAddress());
        this.addressService.addAddressByUserId(address);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation("根据用户ID查询地址列表")
    @GetMapping
    public ResponseEntity<List<Address>> queryAddressByUserId(){
        List<Address> addresses = this.addressService.queryAddressByUserId();
        if (addresses == null || addresses.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(addresses);
    }

    @ApiOperation("修改收货地址")
    @PutMapping
    public ResponseEntity<Void> updateAddressByUserId(@RequestBody Address address){
        this.addressService.updateAddressByUserId(address);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation("删除收货地址")
    @Delete("{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("addressId") Long addressId){
        this.addressService.deleteAddress(addressId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation("根据地址ID查询收货地址")
    @GetMapping("{addressId}")
    public ResponseEntity<Address> queryAddressById(@PathVariable("addressId") Long addressId){
        Address address = this.addressService.queryAddressById(addressId);
        if (address == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(address);
    }

    @ApiOperation("获取地址列表")
    @GetMapping("list")
    public ResponseEntity<List<Address>> getAddressList(){
        List<Address> addressList = this.addressService.getAddressList();
        if (CollectionUtils.isEmpty(addressList)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(addressList);
    }
}

