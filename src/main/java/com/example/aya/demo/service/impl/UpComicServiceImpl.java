package com.example.aya.demo.service.impl;

import com.example.aya.demo.dao.UpComic;
import com.example.aya.demo.dao.impl.UpComicImpl;
import com.example.aya.demo.service.UpComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aya
 */
@Service
public class UpComicServiceImpl implements UpComicService {
    @Autowired
    private UpComicImpl upComicImpl;
    @Override
    public UpComic saveUpComicService(UpComic upComic) {
        return upComicImpl.save(upComic);
    }
    @Override
    public Page<UpComic> findUpComicByUserId(Long userId, Pageable pageable){
        return upComicImpl.findByUserId(userId,pageable);
    }

    @Override
    public void deleteUpComic(Long comicId, Long userId){
        upComicImpl.deleteByComicIdAndUserId(comicId,userId);
    }
}
