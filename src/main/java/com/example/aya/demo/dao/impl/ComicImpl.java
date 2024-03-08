package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Aya
 */
public interface ComicImpl extends JpaRepository<Comic, Long>, JpaSpecificationExecutor<Comic> {
    /**
     * 添加comic
     * @param comic
     * @return
     */
    @Override
    Comic save(Comic comic);

    @Override
    Optional<Comic> findById(Long id);

    @Override
    Page<Comic> findAll(Pageable pageable);

    @Override
    @Transactional
    void deleteById(Long id);


    Page<Comic> findByClassfiyAndAddressAndProgress(Long classfiy,Long address,Long progress,Pageable pageable);
}
