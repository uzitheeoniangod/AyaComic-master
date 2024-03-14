package com.example.aya.demo.util;

import java.util.UUID;

public class FileNameUtils {
    /**
     * 根据文件全名获取后缀名
     * @param fileName 文件名
     * @return 后缀名字符串
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 更改文件名(UUID防止上传文件重名)
     * @param fileName 原文件名
     * @return uuid处理后的新文件名
     */
    public static String getFileName(String fileName){
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid + getSuffix(fileName);
    }


}
