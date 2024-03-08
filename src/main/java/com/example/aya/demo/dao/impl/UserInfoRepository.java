package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserName(String userName);

}
