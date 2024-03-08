package com.example.aya.demo.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Aya
 */
@Entity
@Table(name = "t_comic_history")
public class ComicHistory implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "user_id",nullable = false)
    private Long userId;
    @Column(name = "comic_id",nullable = false)
    private Long comicId;
    @Column(name = "comic_detail_id")
    private Long comicDetailId;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    public ComicHistory() {
    }
    public ComicHistory(Long userId,Long comicId,Date createTime){
        this.userId = userId;
        this.comicId = comicId;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ComicHistory{" +
                "id=" + id +
                ", userId=" + userId +
                ", comicId=" + comicId +
                ", comicDetailId=" + comicDetailId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Long getComicDetailId() {
        return comicDetailId;
    }

    public void setComicDetailId(Long comicDetailId) {
        this.comicDetailId = comicDetailId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
