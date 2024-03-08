package com.example.aya.demo.service;

import com.example.aya.demo.dao.ComicCollect;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Aya
 */
public interface ComicCollectService {
    ComicCollect saveComicCollect(ComicCollect comicCollect);
    ComicCollect findComicCollectByUserIdAndComicId(Long userId,Long comicId);
    Page<ComicCollect> findByUserId(Integer currentPage, Long userId);
    void  deleteComicCollectById(Long id);
    List<Long> getComicRank();
    void deleteComicCollectByComicId(Long comicId);
}
