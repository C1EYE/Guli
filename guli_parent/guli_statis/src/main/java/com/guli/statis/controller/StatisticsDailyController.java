package com.guli.statis.controller;


import com.guli.common.result.Result;
import com.guli.statis.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author guli
 * @since 2021-05-18
 */
@RestController
@CrossOrigin
@RequestMapping("/sta")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @GetMapping("/getCountData/{begin}/{end}/{type}")
    public Result getDataCount(@PathVariable("begin") String begin,@PathVariable("end")String end,@PathVariable("type") String type){
        Map<String,Object> map =  statisticsDailyService.getCountData(begin, end, type);
        return Result.ok().data(map);
    }

    @PostMapping("/createData/{day}")
    public Result createData(@PathVariable("day") String day) {
        //获取统计数据，添加到数据库中
        boolean flag = statisticsDailyService.getDataAdd(day);
        return flag ? Result.ok() : Result.error();
    }


}

