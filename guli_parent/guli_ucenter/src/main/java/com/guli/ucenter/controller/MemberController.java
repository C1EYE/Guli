package com.guli.ucenter.controller;


import com.guli.common.result.Result;
import com.guli.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author guli
 * @since 2021-05-18
 */
@RestController
@CrossOrigin
@RequestMapping("/ucenter/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/countRegister/{day}")
    public Result countRegusterNum(@PathVariable("day") String day){
        Integer count = memberService.registerCount(day);
        return Result.ok().data("registerNum", count);
    }
}

