package com.guli.teacher.client;

import com.guli.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient("guli-vod")
public interface VodClient {
    @DeleteMapping("/vod/{videoSourceId}")
    public Result removeVideo(@PathVariable("videoSourceId") String videoSourceId);

    @DeleteMapping(value = "/vod/deleteIds")
    public Result removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);

    @GetMapping("/vod/getAuth/{vid}")
    public Result getPlayAuthFront(@PathVariable("vid") String vid) ;

}
