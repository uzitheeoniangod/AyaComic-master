package com.example.aya.demo.service;

import com.example.aya.demo.dao.UpComic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Aya
 */
public interface UpComicService {
    UpComic saveUpComicService(UpComic upComic);
    Page<UpComic> findUpComicByUserId(Long userId, Pageable pageable);
    void deleteUpComic(Long comicId,Long userId);
}
