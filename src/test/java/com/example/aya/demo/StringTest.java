package com.example.aya.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.aya.demo.dao.Classfiy;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StringTest {

    public static void main(String[] args) {
        List<Classfiy> list = new ArrayList();
        Classfiy classfiy1 = new Classfiy();
        Classfiy classfiy2 = new Classfiy();
        Classfiy classfiy3 = new Classfiy();
        classfiy1.setId(1l);
        classfiy1.setClassfiyName("冒险");
        classfiy2.setId(2l);
        classfiy2.setClassfiyName("校园");
        classfiy3.setId(3l);
        classfiy3.setClassfiyName("ntr");
        list.add(classfiy1);
        list.add(classfiy2);
        list.add(classfiy3);
        String s = JSONObject.toJSONString(list);
        List<Classfiy> list1 = JSON.parseArray(s, Classfiy.class);

        System.out.println(list1);
        //System.out.println(jsonObject);


    }
}
