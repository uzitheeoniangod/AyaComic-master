package com.example.aya.demo.dao;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Aya
 */
@Entity
@Table(name = "t_Address_info")
public class Address implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "address_name")
    private String addressName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

}
