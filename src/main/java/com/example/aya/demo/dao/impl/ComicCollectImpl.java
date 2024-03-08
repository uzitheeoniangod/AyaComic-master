package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.ComicCollect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Aya
 */
public interface ComicCollectImpl extends JpaRepository<ComicCollect, Long> {
    @Override
    ComicCollect save(ComicCollect comicCollect);
    @Override
    Optional<ComicCollect> findById(Long id);

    ComicCollect findByComicIdAndUserId(Long comicId, Long userId);

    Page<ComicCollect> findByUserId(Long userId, Pageable pageable);

    @Query("select t from ComicCollect t \n" +
            "GROUP BY t.comicId\n" +
            "ORDER BY count(t.comicId) desc ")
    Page<ComicCollect> getComicCollect(Pageable pageable);

    @Override
    void deleteById(Long id);
    @Transactional
    void deleteByComicId(Long comicId);
}
