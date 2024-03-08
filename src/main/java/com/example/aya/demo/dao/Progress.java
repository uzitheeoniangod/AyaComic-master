package com.example.aya.demo.dao;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Aya
 */
@Entity
@Table(name = "t_progress_info")
public class Progress implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true,name = "progress_name")
    private String ProgressName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgressName() {
        return ProgressName;
    }

    public void setProgressName(String progressName) {
        ProgressName = progressName;
    }
}
