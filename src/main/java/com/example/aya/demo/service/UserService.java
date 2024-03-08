package com.example.aya.demo.service;

import com.example.aya.demo.dao.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;

/**
 * @author aya
 */
@Resource(name = "userSerivce")
public interface UserService {
    /**
     * 注册
     * @param model
     * @param userName
     * @param password
     * @param realName
     * @param idCard
     * @param phone
     * @param email
     * @return
     */
    User regist(Model model, String userName, String password, String realName, String idCard, String phone, String email);

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    Boolean login(String userName,String password);

    /**
     * 查找用户通过用户名
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);
    User findUserById(Long id);
    User modify(User user);
}
