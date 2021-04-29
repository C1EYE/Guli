package com.guli.teacher.controller;


import com.guli.common.result.Result;
import com.guli.teacher.entity.EduSubject;
import com.guli.teacher.entity.vo.OneSubject;
import com.guli.teacher.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author guli
 * @since 2021-04-26
 */
@RestController
@CrossOrigin
@RequestMapping("/subject")
public class EduSubjectController {

    @Autowired
    EduSubjectService service;

    @PostMapping("/import")
    public Result importSubject(MultipartFile file) {
        List<String> mesList = service.importEXCL(file);
        if (mesList.size() == 0) {
            return Result.ok();
        } else {
            return Result.ok().data("messageList", mesList);
        }
    }

    @GetMapping("/tree")
    public Result TreeSubject() {
        List<OneSubject> result = service.treeSubject();
        return Result.ok().data("node", result);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(name = "id") String id) {
        boolean flag = service.removeById(id);
        return flag ? Result.ok() : Result.error();
    }

    @PostMapping("/saveLevelOne")
    public Result saveLevelOne(@RequestBody EduSubject subject) {
        Boolean flag = service.saveLevelOne(subject);
        return flag ? Result.ok() : Result.error();
    }

    @PostMapping("/saveLevelTwo")
    public Result saveLevelTwo(@RequestBody EduSubject subject) {
        Boolean flag = service.saveLevelTwo(subject);
        return flag ? Result.ok() : Result.error();
    }

}

