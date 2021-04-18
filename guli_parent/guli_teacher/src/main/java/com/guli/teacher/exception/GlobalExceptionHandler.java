package com.guli.teacher.exception;

import com.guli.common.result.ExceptionUtil;
import com.guli.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();

        return Result.error().message("未处理的错误");
    }

    @ExceptionHandler(EduException.class)
    public Result EduException(EduException e){
        e.printStackTrace();
        return Result.error().code(e.getCode()).message("查无此讲师");
    }



}
