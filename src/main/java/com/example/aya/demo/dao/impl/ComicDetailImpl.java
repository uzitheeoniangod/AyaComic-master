package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.Comic;
import com.example.aya.demo.dao.ComicDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aya
 */
public interface ComicDetailImpl extends JpaRepository<ComicDetail, Long> {

    @Override
    ComicDetail save(ComicDetail comicDetail);
    ComicDetail findByIdAndComicId(Long id, Comic comicId);
    @Override
    void deleteById(Long id);
    @Transactional
    void deleteByComicId(Comic comicId);
}
