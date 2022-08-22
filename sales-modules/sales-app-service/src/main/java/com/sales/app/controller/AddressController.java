package com.sales.app.controller;

import com.sales.app.domain.entity.Address;
import com.sales.app.domain.entity.Address;
import com.sales.app.domain.request.MallProductReq;
import com.sales.app.service.AddressService;
import com.sales.common.core.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 收获地址控制层
 * @author: wuyingqiang
 * @date: 2022-08-05 15:26
 */

@Api(value = "地址相关接口")
@RestController
@RequestMapping(value = "/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "地址列表")
    @GetMapping("/getAddressList")
    public BaseResult<List<Address>> getAddressList(Long userId){
        return BaseResult.ok(addressService.getAddressList(userId));
    }

    @ApiOperation(value = "地址详情")
    @GetMapping("/getAddressInfo")
    public BaseResult<Address> getAddressInfo(String addressId){
        return BaseResult.ok(addressService.getAddressInfo(addressId));
    }

    @ApiOperation(value = "新增地址")
    @PostMapping("/addAddress")
    public BaseResult<Integer> addAddress(@RequestBody Address address){
        return BaseResult.ok(addressService.addAddress(address));
    }

    @ApiOperation(value = "更新地址")
    @PostMapping("/updateAddress")
    public BaseResult<Integer> updateAddress(@RequestBody Address address){
        return BaseResult.ok(addressService.updateAddress(address));
    }

    @ApiOperation(value = "删除地址")
    @PostMapping("/delAddress")
    public BaseResult<Integer> delAddress(String addressId){
        return BaseResult.ok(addressService.delAddress(addressId));
    }

}
