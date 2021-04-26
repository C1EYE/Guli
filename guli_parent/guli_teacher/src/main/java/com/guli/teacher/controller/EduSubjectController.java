package com.guli.teacher.controller;


import com.guli.common.result.Result;
import com.guli.teacher.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
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
}

