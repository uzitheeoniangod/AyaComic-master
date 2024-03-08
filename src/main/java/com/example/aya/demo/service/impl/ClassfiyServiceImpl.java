package com.example.aya.demo.service.impl;

import com.example.aya.demo.dao.Classfiy;
import com.example.aya.demo.dao.Progress;
import com.example.aya.demo.dao.impl.ClassfiyImpl;
import com.example.aya.demo.service.ClassfiyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Aya
 */
@Service("classfiyService")
public class ClassfiyServiceImpl implements ClassfiyService {
    @Autowired
    private ClassfiyImpl classfiy;

    @Override
    public List<Classfiy> findAll() {
        return classfiy.findAll();
    }

    @Override
    public String findByIdReturnName(Long id){
        Optional<Classfiy> result = classfiy.findById(id);
        Classfiy classfiy = result.isPresent()? result.get():null;
        return classfiy.getClassfiyName();
    }
}
