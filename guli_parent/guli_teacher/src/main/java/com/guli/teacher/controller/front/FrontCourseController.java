package com.guli.teacher.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.result.Result;
import com.guli.teacher.entity.EduCourse;
import com.guli.teacher.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author c1eye
 */
@RestController
@CrossOrigin
@RequestMapping("/FrontCourse")
public class FrontCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    //课程分页列表
    @GetMapping("/list/{page}/{limit}")
    public Result getCourseListFront(@PathVariable("page") Long page, @PathVariable("limit") Long limit) {
        Page<EduCourse> eduCoursePage = new Page<>(page, limit);
        Map<String, Object> map = eduCourseService.getCourseListFront(eduCoursePage);
        return Result.ok().data("map", map);
    }
}
