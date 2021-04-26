package com.guli.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.guli.oss.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final static String[] TYPESTR= {""};


    @Override
    public String upload(MultipartFile file) {
        //检查文件格式

        boolean flag = false;

        for(String type : TYPESTR){
            if(StringUtils.endsWithIgnoreCase(file.getOriginalFilename(),type)){
                flag = true;
                break;
            }
        }
        if(!flag){
            return "图片格式不正确";
        }

        //判断文件内容
        BufferedImage image = null;
        try {
            image = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(image != null){
            System.err.println(String.valueOf(image.getHeight()));
            System.err.println(String.valueOf(image.getWidth()));
        } else{
            return "文件内容不正确";
        }




        OSS ossClient = new OSSClientBuilder().build(ConstantPropertiesUtil.END_POINT,
                ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        String filename = file.getOriginalFilename();
        String form = filename.substring(filename.lastIndexOf("."));
        String newName = UUID.randomUUID().toString() + form;
        String time = new DateTime().toString("yyyy/MM/dd");
        String urlPath = ConstantPropertiesUtil.FILE_HOST + "/" + time + "/" + newName;

        String result =null;

        try {
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(ConstantPropertiesUtil.BUCKET_NAME, urlPath, inputStream);
            result = ConstantPropertiesUtil.BUCKET_NAME + "." + ConstantPropertiesUtil.END_POINT + "/" + urlPath;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }

        return "http://"+result;
    }
}
