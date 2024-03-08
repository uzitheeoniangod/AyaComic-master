package com.example.aya.demo.service;

import com.example.aya.demo.dao.ComicHistory;
import org.springframework.data.domain.Page;

/**
 * @author Aya
 */
public interface ComicHistoryService {
    ComicHistory findComicHistoryByUserIdAndComicId(Long userId, Long comicId);
    ComicHistory saveComicHistory(ComicHistory comicHistory);
    void  deleteComicHistoryById(Long id);
    Page<ComicHistory> findByUserId(Long userId, Integer currentPage);
    void deleteCOmicHistoryByComicId(Long comicId);

}
