package com.example.aya.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.aya.demo.common.GlobalConstants;
import com.example.aya.demo.dao.*;
import com.example.aya.demo.dao.impl.*;
import com.example.aya.demo.service.ComicService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserImplTest {
    @Autowired
    private UserImpl userImpl;
    @Autowired
    private ComicImpl comicImpl;
    @Autowired
    private ComicCollectImpl comicCollect;
    @Autowired
    private ComicHistoryImpl comicHistoryImpl;
    @Autowired
    private ComicDetailImpl comicDetailImpl;
    @Autowired
    private UpComicImpl upComicImpl;
    @Autowired
    private ComicService comicService;
    @Autowired
    private ClassfiyImpl classfiyImpl;
    @Test
    public void test() throws Exception{

        Page<ComicCollect> page = this.comicCollect.getComicCollect(PageRequest.of(0, 10));
        List<ComicCollect> content = page.getContent();
        System.out.println(content );


        /*userImpl.save(new User("aa1", "aa@126.com", "aa", "aa123456",formattedDate));
        userImpl.save(new User("bb2", "bb@126.com", "bb", "bb123456",formattedDate));
        userImpl.save(new User("cc3", "cc@126.com", "cc", "cc123456",formattedDate));
        Assert.assertEquals("bb2",userImpl.findByUserNameOrEmail("bb","xxx126.com").getNickName());
        userImpl.delete(userImpl.findByUserName("aa"));*/

    }

    @Test
    public void GenderTest() {
        User user = new User("tyk", "111111", "jxw", "310105192401151248", "18930378979", "xiaowen@qq.com", 0);
        System.out.println(user);
    }
}
