package com.example.aya.demo.service.impl;

import com.example.aya.demo.dao.Address;
import com.example.aya.demo.dao.Progress;
import com.example.aya.demo.dao.impl.AddressImpl;
import com.example.aya.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Aya
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressImpl addressImpl;

    @Override
    public List<Address> findAll() {
        return addressImpl.findAll();
    }

    @Override
    public String findByIdReturnName(Long id){
        Optional<Address> result = addressImpl.findById(id);
        Address address = result.isPresent()? result.get():null;
        return address.getAddressName();
    }
}
