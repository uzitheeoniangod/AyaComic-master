package com.example.aya.demo.service.impl;

import com.example.aya.demo.dao.Comic;
import com.example.aya.demo.dao.ComicHistory;
import com.example.aya.demo.dao.impl.ComicHistoryImpl;
import com.example.aya.demo.service.ComicHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Aya
 */
@Service
public class ComicHistoryServiceImpl implements ComicHistoryService {
    @Autowired
    private ComicHistoryImpl comicHistoryImpl;

    @Override
    public ComicHistory findComicHistoryByUserIdAndComicId(Long userId, Long comicId) {
        return comicHistoryImpl.findByUserIdAndComicId(userId,comicId);
    }
    @Override
    public ComicHistory saveComicHistory(ComicHistory comicHistory){
        return comicHistoryImpl.save(comicHistory);
    }
    @Override
    public void  deleteComicHistoryById(Long id){
        comicHistoryImpl.deleteById(id);
        return;
    }

    @Override
    public Page<ComicHistory> findByUserId(Long userId, Integer currentPage){
        Pageable pageable = PageRequest.of(currentPage,10);
        return comicHistoryImpl.findByUserId(userId,pageable);
    }
    @Override
    public void deleteCOmicHistoryByComicId(Long comicId){
        comicHistoryImpl.deleteByComicId(comicId);
        return;
    }

}
