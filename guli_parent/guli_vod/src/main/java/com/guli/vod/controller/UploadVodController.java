package com.guli.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.guli.common.result.Result;
import com.guli.vod.service.VodService;
import com.guli.vod.util.AliyunVODSDKUtils;
import com.guli.vod.util.ConstantPropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vod")
@Api(description = "阿里云视频点播微服务")
public class UploadVodController {


    @Autowired
    private VodService vodService;


    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        String videoSourceId = vodService.uploadVideo(file);
        return Result.ok().data("videoSourceId", videoSourceId);
    }

    /**
     * 根据视频ID删除
     *
     * @return
     */
    @DeleteMapping("/{videoSourceId}")
    public Result getVideoPlayAuth(@PathVariable String videoSourceId) {
        Boolean flag = vodService.deleteVodById(videoSourceId);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 批量删除视频
     *
     * @param videoIdList
     * @return
     */
    @DeleteMapping("/deleteIds")
    public Result removeVideoList(
            @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
            @RequestParam("videoIdList") List videoIdList) {

        vodService.removeVideoList(videoIdList);
        return Result.ok().message("视频删除成功");
    }

    @GetMapping("/getAuth/{vid}")
    public Result getPlayAuthFront(@PathVariable("vid") String vid) {
        String playAuth;
        try {
            DefaultAcsClient defaultAcsClient = AliyunVODSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //获取播放凭证的request对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(vid);
            GetVideoPlayAuthResponse acsResponse = defaultAcsClient.getAcsResponse(request);
            playAuth = acsResponse.getPlayAuth();
        } catch (ClientException e) {
            return Result.error();
        }

        return Result.ok().data("playAuth", playAuth);
    }


}
