package com.example.aya.demo.dao;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Administrator
 */
@Entity
@Table(name = "t_comic_collect")
public class ComicCollect implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "user_id",nullable = false)
    private Long userId;
    @Column(name = "comic_id",nullable = false)
    private Long comicId;

    @Override
    public String toString() {
        return "ComicCollect{" +
                "id=" + id +
                ", userId=" + userId +
                ", comicId=" + comicId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getComicId() {
        return comicId;
    }

    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }

}
