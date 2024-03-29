package com.guli.teacher.controller;

import com.guli.common.result.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @RequestMapping("/login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    @RequestMapping("/info")
    public Result info() {
        return Result.ok().data("roles", "[\"admin\"]")
                .data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
