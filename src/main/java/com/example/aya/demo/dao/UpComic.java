package com.example.aya.demo.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_up_comic")
public class UpComic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "comic_id")
    private Long comicId;

    public UpComic() {
    }
    public UpComic(Long userId,Long comicId){
        this.userId = userId;
        this.comicId = comicId;
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
