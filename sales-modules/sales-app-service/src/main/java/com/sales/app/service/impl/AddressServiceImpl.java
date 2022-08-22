package com.sales.app.service.impl;

import com.sales.app.domain.entity.Address;
import com.sales.app.mapper.AddressMapper;
import com.sales.app.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 地址业务层
 * @author: wuyingqiang
 * @date: 2022-08-05 15:29
 */

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> getAddressList(Long userId) {
        return addressMapper.selectAll(userId);
    }

    @Override
    public Address getAddressInfo(String addressId) {
        return addressMapper.selectByPrimaryKey(addressId);
    }

    @Override
    public Integer updateAddress(Address address) {
        return addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public Integer addAddress(Address address) {
        return addressMapper.insert(address);
    }

    @Override
    public Integer delAddress(String addressId) {
        Address address = addressMapper.selectByPrimaryKey(addressId);
        address.setDelFlag(1);
        return addressMapper.updateByPrimaryKeySelective(address);
    }
}
