package com.guli.teacher.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.result.Result;
import com.guli.teacher.entity.EduCourse;
import com.guli.teacher.entity.query.FrontCourseQuery;
import com.guli.teacher.entity.vo.CourseWebVo;
import com.guli.teacher.entity.vo.OneChapter;
import com.guli.teacher.service.EduChapterService;
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

    @Autowired
    private EduChapterService eduChapterService;


    //课程分页列表
    @GetMapping("/list/{page}/{limit}")
    public Result getCourseListFront(@PathVariable("page") Long page, @PathVariable("limit") Long limit) {
        Page<EduCourse> eduCoursePage = new Page<>(page, limit);
        Map<String, Object> map = eduCourseService.getCourseListFront(eduCoursePage);
        return Result.ok().data("map", map);
    }

    //课程分页列表条件
    @PostMapping("/listWrapper")
    public Result getCourseListWrapper(@RequestBody() FrontCourseQuery query) {
        Page<EduCourse> eduCoursePage = new Page<>(query.getPage(), query.getLimit());
        Map<String, Object> map = eduCourseService.getCourseListFrontWrapper(eduCoursePage, query);
        return Result.ok().data("map", map);
    }

    @GetMapping("/getFrontCourseInfo/{id}")
    public Result getFrontCourseInfo(@PathVariable() String id) {
        CourseWebVo result = eduCourseService.getCourseInfoFrontById(id);
        return Result.ok().data("vo", result);
    }

    @GetMapping("/chapterList/{id}")
    public Result getFrontChapterList(@PathVariable("id") String id) {
        List<OneChapter> vo = eduChapterService.getVoById(id);
        return Result.ok().data("vo", vo);
    }

    /**
     * 课程详情页前端数据
     *
     * @return
     */
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable("id") String id) {
        CourseWebVo result = eduCourseService.getCourseInfoFrontById(id);
        List<OneChapter> vo = eduChapterService.getVoById(id);
        return Result.ok().data("course", result).data("chapterVoList", vo);
    }

}
