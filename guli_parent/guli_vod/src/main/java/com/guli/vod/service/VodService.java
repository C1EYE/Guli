package com.guli.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {

    String uploadVideo(MultipartFile file);

    /**
     * 根据视频资源ID删除云端视频
     * @param videoSourceId
     * @return
     */
    Boolean deleteVodById(String videoSourceId);

    void removeVideoList(List videoIdList);
}
