package com.example.aya.demo.dao;

import com.example.aya.demo.common.GlobalConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Aya
 */
@Entity
@Table
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String realName;
    @Column
    private Integer gender;
    @Column(nullable = false,unique = true)
    private String idCard;
    @Column
    private Date birthday;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private Integer isUse;

    @Column(nullable = false)
    private Integer isAdm;
    @Column
    private Date createTime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

    public User(String userName, String password, String realName, String idCard, String phone,String email,Integer isAdm) {
        this.userName=userName;
        this.password=password;
        this.realName=realName;
        this.phone=phone;
        setIdCard(idCard);
        transBirth(idCard);
        transGender(idCard);
        isUse=1;
        this.isAdm = isAdm;
        this.email = email;
        createTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getGender() {
        if (gender == GlobalConstants.PEOPLE_SEX_MALE) {
            return "男";
        } else if (gender == GlobalConstants.PEOPLE_SEX_FEMALE) {
            return "女";
        }
        return "未知";
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBirthday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String birth = sdf.format(birthday);
        return birth;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Integer getIsAdm() {
        return isAdm;
    }

    public void setIsAdm(Integer isAdm) {
        this.isAdm = isAdm;
    }

    public String getCreateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String creat = sdf.format(createTime);
        return creat;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void transBirth(String idCard) {
        String birth = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dBirth = null;
        if (!idCard.isEmpty()&&idCard.length()==GlobalConstants.ID_CARD_LENGTH_18) {
            birth = idCard.substring(6, 10) + "-" + idCard.substring(10, 12) + "-" + idCard.substring(12, 14);
            try {
                dBirth = sdf.parse(birth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        this.birthday = dBirth;
    }

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public void transGender(String idCard) {
        Integer gd=0;
        if (!idCard.isEmpty() && idCard.length() == GlobalConstants.ID_CARD_LENGTH_18) {
            //noinspection AlibabaUndefineMagicConstant
            if (Integer.parseInt(idCard.substring(16, 17)) % 2 == 0) {
                gd = 2;
            } else {
                gd = 1;
            }
        }
        this.gender = gd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", gender=" + gender +
                ", idCard='" + idCard + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", isUse=" + isUse +
                ", isAdm=" + isAdm +
                ", createTime=" + createTime +
                '}';
    }
}
