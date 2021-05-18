package com.guli.teacher.service;

import com.guli.teacher.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.teacher.entity.vo.OneChapter;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author guli
 * @since 2021-04-30
 */
public interface EduChapterService extends IService<EduChapter> {

    List<OneChapter> getVoById(String id);

    Boolean removeByChapterId(String id);

    void removeByCourseId(String courseId);
}
