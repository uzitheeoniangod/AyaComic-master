package com.example.aya.demo.util;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class FileUtils {
    /**
     * 获取图片宽度
     * @param multipartFile org.springframework.web.multipart.MultipartFile
     * @return Integer宽度 or null
     */
    public static Integer getImageWidth(MultipartFile multipartFile){
        try {
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            return bufferedImage.getWidth();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取图片高度
     * @param multipartFile org.springframework.web.multipart.MultipartFile
     * @return Integer高度 or null
     */
    public static Integer getImageHeight(MultipartFile multipartFile){
        try {
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            return bufferedImage.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
