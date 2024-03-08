package com.example.aya.demo.service.impl;

import com.example.aya.demo.dao.Comic;
import com.example.aya.demo.dao.impl.ComicImpl;
import com.example.aya.demo.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Aya
 */
@Service
public class ComicServiceImpl implements ComicService {
    @Autowired
    private ComicImpl comicImpl;

    @Override
    public Comic saveComic(Comic comic) {
        return comicImpl.save(comic);
    }

    @Override
    public Comic findComicById(Long id){
        Optional<Comic> comic = comicImpl.findById(id);
        return comic.isPresent()?comic.get():null;
    }

    @Override
    public Page<Comic> findAll(Pageable pageable){
        return comicImpl.findAll(pageable);
    }

    @Override
    public Page<Comic> findByCondition(Integer page, Comic comic) {
        Pageable pageable = PageRequest.of(page, 2);
        return comicImpl.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            if(comic.getId()!=null){
                predicates.add(criteriaBuilder.equal(root.get("id"),comic.getId()));
            }
            if (comic.getClassfiy()!=null && !"0".equals(comic.getClassfiy())){
                predicates.add(criteriaBuilder.equal(root.get("classfiy"),comic.getClassfiy()));
            }
            if (comic.getAddress()!=null && !"0".equals(comic.getAddress())){
                predicates.add(criteriaBuilder.equal(root.get("address"),comic.getAddress()));
            }
            if (comic.getProgress()!=null && !"0".equals(comic.getProgress())){
                predicates.add(criteriaBuilder.equal(root.get("progress"),comic.getProgress()));
            }
            if (comic.getTitle() != null && !"".equals(comic.getTitle())){
                predicates.add(criteriaBuilder.like(root.get("title"),"%"+comic.getTitle()+"%"));
            }
            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        },pageable);
    }
    @Override
    public void deleteComicById(Long id){
        comicImpl.deleteById(id);
        return;
    }
}
