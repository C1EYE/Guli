package com.guli.teacher.controller;


import com.guli.common.result.Result;
import com.guli.teacher.entity.vo.CourseVo;
import com.guli.teacher.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程简介 前端控制器
 * </p>
 *
 * @author guli
 * @since 2021-04-30
 */
@RestController
@CrossOrigin
@RequestMapping("/desc")
public class EduCourseDescriptionController {
    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("save")
    public Result saveCourse(@RequestBody CourseVo courseDesc) { //接收课程和描述对象

        try {
            //接收课程和描述对象
            String courseId = eduCourseService.saveVo(courseDesc);
            return Result.ok().data("courseId", courseId);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

    }
}
