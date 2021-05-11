package com.guli.teacher.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.teacher.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.teacher.entity.query.CourseQuery;
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

    CourseVo getCourseVoById(String id);

    boolean updateCourse(CourseVo courseVo);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String id);
}
