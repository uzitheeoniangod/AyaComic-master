package com.example.aya.demo.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Aya
 */
@Entity
@Table(name = "t_comic_detail")
public class ComicDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "comic_id")
    private Comic comicId;
    @Column
    private String name;
    @Column(columnDefinition ="text")
    private String urls;
    @Column(name = "create_time")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comic getComicId() {
        return comicId;
    }

    public void setComicId(Comic comicId) {
        this.comicId = comicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
