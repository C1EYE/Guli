package com.guli.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 上传图片返回路径
     * @param file
     * @return
     */
    String upload(MultipartFile file);

}
