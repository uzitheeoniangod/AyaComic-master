package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Aya
 */
public interface AddressImpl extends JpaRepository<Address, Long> {
    /**
     * 查找所有地区
     * @return
     */
    @Override
    List<Address> findAll();

    @Override
    Optional<Address> findById(Long id);
}
