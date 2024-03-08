package com.example.aya.demo.util;

import com.example.aya.demo.common.GlobalConstants;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.io.UnsupportedEncodingException;


public class QiNiuUtil {


    public static String  upload(byte[] flieBytes) {
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = GlobalConstants.ACCESS_KEY;
        String secretKey = GlobalConstants.SECRET_KEY;
        String bucket = GlobalConstants.BUCKET_NAME;
        String domain = GlobalConstants.DOMAIN;
        StringBuffer fileUrl=new StringBuffer(domain);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        byte[] uploadBytes = flieBytes;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            fileUrl.append(putRet.key);
            fileUrl.append(GlobalConstants.IMG_STYLE);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            fileUrl=null;
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return fileUrl.toString();
    }

}
