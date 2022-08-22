package com.sales.app.service;

import com.sales.app.domain.entity.Address;

import java.util.List;

public interface AddressService {

    /**
     * 获取地址列表
     *
     * @return 地址列表
     */
    List<Address> getAddressList(Long userId);

    /**
     * 获取地址详情
     *
     * @param addressId 地址ID
     * @return 地址详情
     */
    Address getAddressInfo(String addressId);

    /**
     * 更新地址
     *
     * @param address 地址实体类
     * @return 结果
     */
    Integer updateAddress(Address address);

    /**
     * 新增地址
     *
     * @param address 地址实体类
     * @return 结果
     */
    Integer addAddress(Address address);

    /**
     * 删除地址
     *
     * @param addressId 地址实体类
     * @return 结果
     */
    Integer delAddress(String addressId);



}
