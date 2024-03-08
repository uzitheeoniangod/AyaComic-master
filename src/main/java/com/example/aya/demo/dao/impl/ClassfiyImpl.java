package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.Classfiy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Aya
 */
public interface ClassfiyImpl extends JpaRepository<Classfiy,Long> {
    /**
     * 查询所用类别
     * @return
     */
    @Override
    List<Classfiy> findAll();

    @Override
    Optional<Classfiy> findById(Long id);


}
