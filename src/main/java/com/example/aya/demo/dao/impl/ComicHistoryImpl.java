package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.ComicHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aya
 */
public interface ComicHistoryImpl extends JpaRepository<ComicHistory,Long> {
    @Override
    ComicHistory save(ComicHistory comicHistory);
    ComicHistory findByUserIdAndComicId(Long userId,Long comicId);
    @Override
    void deleteById(Long id);
    Page<ComicHistory> findByUserId(Long userId, Pageable pageable);
    @Transactional
    void deleteByComicId(Long comicId);
}
