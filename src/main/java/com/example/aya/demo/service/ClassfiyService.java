package com.example.aya.demo.service;


import com.example.aya.demo.dao.Classfiy;

import java.util.List;

/**
 * @author Aya
 */
public interface ClassfiyService {

    List<Classfiy> findAll();

    String findByIdReturnName(Long id);

}
