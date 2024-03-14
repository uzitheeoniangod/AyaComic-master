package com.example.aya.demo.service.impl;

import com.example.aya.demo.dao.ImageVo;
import com.example.aya.demo.service.UploadImageServic;
import com.example.aya.demo.util.FileNameUtils;
import com.example.aya.demo.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
@PropertySource("classpath:/file_configuration.properties")
public class UploadImageServiceImpl implements UploadImageServic {
    @Value("${file.localDir}")
    private String localDir;

    @Value("${file.suffixList}")
    private String suffix;
    @Override
    public ImageVo uploadImg(MultipartFile multipartFile) throws IOException {

        //获取原始图片名称
        String originalFilename = multipartFile.getOriginalFilename();
        originalFilename = Objects.requireNonNull(originalFilename).toLowerCase();
        //获取图片类型
        String suffixName = FileNameUtils.getSuffix(originalFilename);

        List<String> strings = Arrays.asList(suffix.split(","));
        //不包含规定的后缀名则直接返回失败
        if (!strings.contains(suffixName)) {
            return ImageVo.fail();
        }

        //创建日期目录--yyyy/MM/dd
        String format = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        //File对象封装文件路径
        File file = new File(localDir + "/" + format);
        //如果目录不存在创建多级目录
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            System.out.println("日期文件夹" + (mkdirs ? "创建成功" : "创建失败"));
        }

        //根据是否存在宽高判断是否为图片而不是恶意文件
        Integer imageWidth = FileUtils.getImageWidth(multipartFile);
        Integer imageHeight = FileUtils.getImageHeight(multipartFile);

        //uuid--文件名
        String fileName = FileNameUtils.getFileName(originalFilename);

        //文件上传
        File newFile = new File(file.getAbsolutePath()+"/"+fileName);
        multipartFile.transferTo(newFile);

        return ImageVo.success(newFile.getAbsolutePath(), imageWidth,imageHeight);
    }

}
