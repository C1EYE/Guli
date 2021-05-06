package com.guli.teacher.controller;


import com.guli.common.result.Result;
import com.guli.teacher.entity.vo.CourseVo;
import com.guli.teacher.service.EduCourseDescriptionService;
import com.guli.teacher.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author guli
 * @since 2021-04-30
 */
@RestController
@CrossOrigin
@RequestMapping("/edu-course")
public class EduCourseController {


    @Autowired
    EduCourseService courseService;

    @PostMapping("/saveVo")
    @ApiOperation(value = "保存课程信息")
    public Result save(@RequestBody CourseVo vo) {
        String courseId = courseService.saveVo(vo);
        return Result.ok().data("id", courseId);
    }

}

