package com.example.aya.demo.service.impl;

import com.example.aya.demo.dao.User;
import com.example.aya.demo.dao.impl.UserImpl;
import com.example.aya.demo.service.UserService;
import com.example.aya.demo.util.CheckUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;

import java.util.Optional;

/**
 * @author aya
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserImpl userimpl;

    @Override
    public User regist(Model model, String userName, String password, String realName, String idCard, String phone, String email) {
        //验证表单是否有空值
        if (StringUtils.isBlank(userName)||StringUtils.isBlank(password)||
            StringUtils.isBlank(realName)||StringUtils.isBlank(idCard)||
                StringUtils.isBlank(phone)||StringUtils.isBlank(email)) {
            model.addAttribute("errorMsg","值为空");
            return null;
        }
        //验证身份证是否合法
        String idCardValidate = CheckUtil.IDCardValidate(idCard);
        if (!idCardValidate.equals("YES")) {
            model.addAttribute("errorMsg", idCardValidate);
            return null;
        }
        //验证手机号是否合法
        if (!CheckUtil.isMobileNO(phone)) {
            model.addAttribute("errorMsg", "手机号不合法");
            return null;
        }
        //验证用户名是否存在
        User isUser = userimpl.findByUserNameOrIdCard(userName, idCard);
        if (isUser != null) {
            model.addAttribute("errorMsg", "用户名或身份证号已存在");
            return null;
        }
        //密码加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //String encodePwd;
        User user = new User(userName, password, realName, idCard, phone, email, 0);
        try {
            userimpl.save(user);
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
        }
        return user;
    }

    @Override
    public Boolean login(String userName, String password) {
        User user = userimpl.findByUserName(userName);
        //密码md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //与md5加密后的密码判断
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public User findUserByUserName(String userName){
        return userimpl.findByUserName(userName);
    }
    @Override
    public User findUserById(Long id){
        Optional<User> user = userimpl.findById(id);
        return user.isPresent()?user.get():null;
    }

    @Override
    public User modify(User user){
        return userimpl.save(user);
    }

}
