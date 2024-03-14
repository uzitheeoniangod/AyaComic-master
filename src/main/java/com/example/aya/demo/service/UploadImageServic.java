package com.example.aya.demo.service;

import com.example.aya.demo.dao.ImageVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadImageServic {
    /**
     * 上传图片
     * @param multipartFile MultipartFile
     * @return ImageVo对象
     */
    ImageVo uploadImg(MultipartFile multipartFile) throws IOException;

}
