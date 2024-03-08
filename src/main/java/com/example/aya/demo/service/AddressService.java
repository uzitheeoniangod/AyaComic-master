package com.example.aya.demo.service;

import com.example.aya.demo.dao.Address;

import java.util.List;

/**
 * @author Aya
 */
public interface AddressService {
    /**
     * 查找所有地区
     * @return
     */
    List<Address> findAll();

    String findByIdReturnName(Long id);
}
