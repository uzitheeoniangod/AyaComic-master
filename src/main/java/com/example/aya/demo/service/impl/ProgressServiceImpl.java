package com.example.aya.demo.service.impl;

import com.example.aya.demo.dao.Progress;
import com.example.aya.demo.dao.impl.ProgressImpl;
import com.example.aya.demo.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Aya
 */
@Service
public class ProgressServiceImpl implements ProgressService {
    @Autowired
    private ProgressImpl progressImpl;


    @Override
    public List<Progress> findAll() {
        List<Sort.Order> orderList = new ArrayList<>();
        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"id");
        orderList.add(order);
        Sort sort = Sort.by(orderList);
        return progressImpl.findAll(sort);
    }

    @Override
    public String findByIdReturnName(Long id){
        Optional<Progress> result = progressImpl.findById(id);
        Progress progress = result.isPresent()? result.get():null;
        return progress.getProgressName();
    }
}
