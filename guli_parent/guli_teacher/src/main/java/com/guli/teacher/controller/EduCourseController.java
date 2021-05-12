package com.guli.teacher.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.result.Result;
import com.guli.teacher.entity.EduCourse;
import com.guli.teacher.entity.query.CourseQuery;
import com.guli.teacher.entity.vo.CoursePublishVo;
import com.guli.teacher.entity.vo.CourseVo;
import com.guli.teacher.service.EduCourseDescriptionService;
import com.guli.teacher.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public Result getCourseById(@PathVariable("id") String id) {
        CourseVo vo = courseService.getCourseVoById(id);
        return Result.ok().data("courseInfo", vo);
    }

    @PutMapping("/update")
    public Result updateCourse(@RequestBody CourseVo courseVo) {
        boolean result = courseService.updateCourse(courseVo);
        return result ? Result.ok().data("courseId", courseVo.getCourse().getId()) : Result.error();
    }

    @ApiOperation(value = "分页课程列表")
    @PostMapping("{page}/{limit}")
    public Result pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody CourseQuery courseQuery) {

        Page<EduCourse> pageParam = new Page<>(page, limit);
        courseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return Result.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{id}")
    public Result removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        boolean result = courseService.removeCourseById(id);
        if(result){
            return Result.ok();
        }else{
            return Result.error().message("删除失败");
        }
    }

    @GetMapping("/vo/{id}")
    public Result getCoursePublishVoById(@PathVariable String id){
        CoursePublishVo voursePublishVo = courseService.getCoursePublishVoById(id);
        if(voursePublishVo != null){
            return Result.ok().data("item",voursePublishVo);
        } else {
            return Result.error();
        }
    }

    @PutMapping("/status/{id}")
    public Result updateByStatusById(@PathVariable String id){
        Boolean flag = courseService.updateStatusById(id);
        if(flag){
            return Result.ok();
        } else{
            return Result.error();
        }
    }



}

