package com.guli.teacher.controller;


import com.guli.common.result.Result;
import com.guli.teacher.entity.EduChapter;
import com.guli.teacher.entity.vo.OneChapter;
import com.guli.teacher.service.EduChapterService;
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
@RequestMapping("/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    /**
     * 根据ID获取章节列表
     */
    @GetMapping("/list/{id}")
    public Result getChapterAndVoById(@PathVariable("id") String id){
        List<OneChapter> list = eduChapterService.getVoById(id);
        return Result.ok().data("list", list);
    }

    /**
     * 保存章节
     * @param chapter
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody EduChapter chapter){
        boolean save = eduChapterService.save(chapter);
        if(save){
            return Result.ok();
        } else{
            return Result.error();
        }
    }

    /**
     * 根据章节ID获取章节信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getChapterById(@PathVariable String id){
        try {
            EduChapter chapter = eduChapterService.getById(id);
            return Result.ok().data("chapter",chapter);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 根据章节的ID修改
     * @param chapter
     * @return
     */
    @PutMapping("update")
    public Result updateChapter(@RequestBody EduChapter chapter){
        boolean update = eduChapterService.updateById(chapter);
        if(update){
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 根据章节ID删除章节
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result deleteChapter(@PathVariable String id){
        Boolean flag = eduChapterService.removeByChapterId(id);
        if(flag){
            return Result.ok();
        }
        return Result.error();
    }



}

