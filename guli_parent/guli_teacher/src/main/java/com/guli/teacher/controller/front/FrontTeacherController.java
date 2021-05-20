package com.guli.teacher.controller.front;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.result.Result;
import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController

@CrossOrigin
@RequestMapping("/FrontTeacher")
public class FrontTeacherController {

    @Autowired
    EduTeacherService teacherService;

    /**
     * 获取分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getTeacherList/{page}/{limit}")
    public Result getAllTeacherFront(@PathVariable("page") Long page,
                                     @PathVariable("limit") Long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        Map<String, Object> result = teacherService.getAllTeacherFront(pageTeacher);
        return Result.ok().data("map", result);
    }

    @GetMapping("/getTeacherById/{id}")
    public Result getTeacherByIdFront(@PathVariable("id") String id){
        Map<String, Object> map = teacherService.getTeacherByIdFront(id);
        return Result.ok().data("map", map);
    }
}
