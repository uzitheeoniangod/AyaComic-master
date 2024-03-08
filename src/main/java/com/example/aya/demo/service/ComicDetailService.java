package com.example.aya.demo.service;

import com.example.aya.demo.dao.Comic;
import com.example.aya.demo.dao.ComicDetail;

/**
 * @author Aya
 */
public interface ComicDetailService {
    ComicDetail saveComicDetail(ComicDetail comicDetail);
    ComicDetail findComicDetailByIdAndComicId(Long id, Comic comicId);
    void deleteComicDetailById(Long id);
    void deleteComicDetailByComicId(Comic comicId);
}
