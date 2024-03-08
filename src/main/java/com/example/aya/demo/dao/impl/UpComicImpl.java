package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.UpComic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Aya
 */
public interface UpComicImpl extends JpaRepository<UpComic, Long> {
    @Override
    UpComic save(UpComic upComic);

    Page<UpComic> findByUserId(Long userId, Pageable pageable);
    @Transactional
    void deleteByComicIdAndUserId(Long comicId,Long userId);
}
