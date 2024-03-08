package com.example.aya.demo.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Aya
 */
@Entity
@Table(name = "t_comic")
public class Comic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(name = "title_img_url")
    private String titleImgUrl;
    @Column
    private String author;
    @Column(name = "publishing_house")
    private String publishingHouse;
    @Column
    private String classfiy;
    @Column
    private String address;
    @Column
    private String progress;
    @Column(columnDefinition ="text")
    private String description;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @OneToMany(mappedBy = "comicId",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<ComicDetail> comicDetailList;

    public List<ComicDetail> getComicDetailList() {
        return comicDetailList;
    }

    public void setComicDetailList(List<ComicDetail> comicDetailList) {
        this.comicDetailList = comicDetailList;
    }

    public Comic() {

    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Comic(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleImgUrl() {
        return titleImgUrl;
    }

    public void setTitleImgUrl(String titleImgUrl) {
        this.titleImgUrl = titleImgUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getClassfiy() {
        return classfiy;
    }

    public void setClassfiy(String classfiy) {
        this.classfiy = classfiy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", titleImgUrl='" + titleImgUrl + '\'' +
                ", author='" + author + '\'' +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", classfiy='" + classfiy + '\'' +
                ", address='" + address + '\'' +
                ", progress='" + progress + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", comicDetailList=" + comicDetailList +
                '}';
    }
}
