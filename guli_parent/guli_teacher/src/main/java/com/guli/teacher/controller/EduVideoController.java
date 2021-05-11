package com.guli.teacher.controller;


import com.guli.common.result.Result;
import com.guli.teacher.entity.EduVideo;
import com.guli.teacher.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author guli
 * @since 2021-05-10
 */
@RestController
@CrossOrigin
@RequestMapping("/video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    //保存小节
    @PostMapping("/save")
    public Result save(@RequestBody EduVideo video) {
        boolean save = eduVideoService.save(video);
        if (save) {
            return Result.ok();
        }
        return Result.error();
    }

    //查询小节
    @GetMapping("{id}")
    public Result getVideoById(@PathVariable String id) {
        try {
            EduVideo eduVideo = eduVideoService.getById(id);
            return Result.ok().data("eduVideo", eduVideo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    //修改小节
    @PutMapping("update")
    public Result updateVideo(@RequestBody EduVideo video) {
        boolean update = eduVideoService.updateById(video);
        if (update) {
            return Result.ok();
        }
        return Result.error();
    }

    //删除小节
    @DeleteMapping("{id}")
    public Result deleteVideo(@PathVariable String id) {
        Boolean flag = eduVideoService.removeByVideoId(id);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }
}


