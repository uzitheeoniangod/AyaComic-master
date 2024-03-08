package com.example.aya.demo.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_classfiy_info")
public class Classfiy implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true,name = "classfiy_name")
    private String classfiyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassfiyName() {
        return classfiyName;
    }

    public void setClassfiyName(String classfiyName) {
        this.classfiyName = classfiyName;
    }
}
