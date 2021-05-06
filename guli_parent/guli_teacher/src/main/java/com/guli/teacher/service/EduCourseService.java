package com.guli.teacher.service;

import com.guli.teacher.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.teacher.entity.vo.CourseVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author guli
 * @since 2021-04-30
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveVo(CourseVo vo);
}
