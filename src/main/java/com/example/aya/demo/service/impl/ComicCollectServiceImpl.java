package com.example.aya.demo.service.impl;

import com.example.aya.demo.dao.ComicCollect;
import com.example.aya.demo.dao.impl.ComicCollectImpl;
import com.example.aya.demo.service.ComicCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aya
 */
@Service
public class ComicCollectServiceImpl implements ComicCollectService {
    @Autowired
    private ComicCollectImpl comicCollectImpl;
    @Override
    public ComicCollect saveComicCollect(ComicCollect comicCollect) {
        return comicCollectImpl.save(comicCollect);
    }
    @Override
    public ComicCollect findComicCollectByUserIdAndComicId(Long userId,Long comicId){
        return comicCollectImpl.findByComicIdAndUserId(comicId,userId);
    }
    @Override
    public void  deleteComicCollectById(Long id){
        comicCollectImpl.deleteById(id);
        return;
    }
    @Override
    public Page<ComicCollect> findByUserId(Integer currentPage,Long userId){
        Pageable pageable = PageRequest.of(currentPage,10);
        return comicCollectImpl.findByUserId(userId,pageable);
    }
    @Override
    public List<Long> getComicRank(){
        Page<ComicCollect> comicCollect = comicCollectImpl.getComicCollect(PageRequest.of(0, 10));
        List<ComicCollect> content = comicCollect.getContent();
        List<Long> comicId = new ArrayList<>();
        for (ComicCollect collect : content) {
            if(collect.getComicId()!=null){
                comicId.add(collect.getComicId());
            }
        }
        return comicId;
    }
    @Override
    public void deleteComicCollectByComicId(Long comicId){
        comicCollectImpl.deleteByComicId(comicId);
    }

}
