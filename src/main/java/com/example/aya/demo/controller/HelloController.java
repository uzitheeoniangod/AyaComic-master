package com.example.aya.demo.controller;

import com.example.aya.demo.dao.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aya
 */
@Controller
public class HelloController {
    @RequestMapping("/getUser")
    public String getUer(Model model) {
        User user = new User();
        user.setUserName("aya");
        user.setPassword("123");
        model.addAttribute("user", user);
        return "test";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello, world";
    }
}
