package com.guli.teacher.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.result.Result;
import com.guli.common.result.ResultCode;
import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.entity.query.TeacherQuery;
import com.guli.teacher.exception.EduException;
import com.guli.teacher.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author guli
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 所有讲师
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "所有讲师列表")
    public Result list() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("items", list);

    }

    /**
     * 删除讲师
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "讲师删除")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") @ApiParam(name = "id", value = "讲师id", required = true) String id) {
        eduTeacherService.removeById(id);
        return Result.ok();
    }


    @ApiOperation(value = "分页讲师列表")
    @GetMapping("/{page}/{limit}")
    public Result selectTeacherByPageAndWrapper(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                    TeacherQuery teacherQuery) {

        Page<EduTeacher> pageParam = new Page<>(page, limit);

        eduTeacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return Result.ok().data("total", total).data("rows", records);
    }


    @ApiOperation(value = "新增讲师")
    @PostMapping("/save")
    public Result saveTeacher(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher
    ) {
        System.err.println(eduTeacher);
        System.err.println("-------------------------------------------------------------");
        eduTeacherService.save(eduTeacher);
        return Result.ok();
    }


    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("/{id}")
    public Result getById(
            @PathVariable("id")
            @ApiParam(name = "id", value = "讲师id", required = true)
                    String id
    ) {
        EduTeacher teacher = eduTeacherService.getById(id);
        if (teacher == null) {
            throw new EduException(ResultCode.EDU_ID_ERROR, "没找到此ID");
        }
        return Result.ok().data("item", teacher);

    }


    //    @ApiOperation(value = "根据ID更新讲师信息")
//    @PutMapping("/update")
//    public Result updateById(
//        @ApiParam(name = "teacher",value = "讲师对象",required = true)
//        EduTeacher eduTeacher
//    ){
//        try {
//            System.err.println(eduTeacher);
//            System.out.println("ALG");
//            eduTeacherService.updateById(eduTeacher);
//            return Result.ok();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.error();
//        }
//    }
    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public Result updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {

        teacher.setId(id);
        eduTeacherService.updateById(teacher);
        return Result.ok();
    }

}

