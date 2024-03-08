package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Aya
 */
public interface UserImpl extends JpaRepository<User,Long> {
    /**
     * 查找用户 通过用户名
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 查找用户 通过用户名或者身份证
     * @param username
     * @param idCard
     * @return
     */
    User findByUserNameOrIdCard(String username, String idCard);

    @Override
    Optional<User> findById(Long id);


}
