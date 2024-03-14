package com.example.aya.demo.controller;
import com.example.aya.demo.dao.ImageVo;
import com.example.aya.demo.service.UploadImageServic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
public class UploadImgController {
    @Resource
    private UploadImageServic uploadImgService;
    @ResponseBody
    @RequestMapping("/fileUpload")
    public ImageVo uploadImg(MultipartFile fileImg) throws IOException {
        return uploadImgService.uploadImg(fileImg);
    }

    /**
     * 显示图片上传页    url:http://localhost:8080/
     * @return upload-image.html页面
     */
    @RequestMapping("/toUploadImgFile")
    public String index(){
        return "/comicUpload";
    }
}
