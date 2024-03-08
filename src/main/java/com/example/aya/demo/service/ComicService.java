package com.example.aya.demo.service;

import com.example.aya.demo.dao.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Aya
 */
public interface ComicService {
    /**
     * 保存comic
     * @param comic
     * @return
     */
    Comic saveComic(Comic comic);
    Comic findComicById(Long id);
    Page<Comic> findAll(Pageable pageable);
    Page<Comic> findByCondition(Integer page, Comic comic);
    void deleteComicById(Long id);
}
