package com.example.aya.demo.util;

import java.util.Random;

public class MyUtil {
    public static String creatCode(int n){
        String code="";
        String date="0123456789";
        Random r=new Random();
        for (int i=0;i<n;i++){
            int index=r.nextInt(date.length());
            code+=date.charAt(index);
        }
        return code;
    }
}
