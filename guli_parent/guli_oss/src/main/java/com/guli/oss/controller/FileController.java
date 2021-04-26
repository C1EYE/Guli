package com.guli.oss.controller;

import com.guli.common.result.Result;
import com.guli.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api("阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/oss")
public class FileController {

    @Autowired
    FileService service;

    @ApiOperation(value = "文件上传")
    @PostMapping("/file/upload")
    public Result upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        return Result.ok().message("文件上传成功").data("url", service.upload(file));
    }

}
