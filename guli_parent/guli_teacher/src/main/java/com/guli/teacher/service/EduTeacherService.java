package com.guli.teacher.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.teacher.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.teacher.entity.query.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author guli
 * @since 2021-04-12
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     *  根据多个条件查询讲师列表
     * @param pageParam
     * @param teacherQuery
     */
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    Map<String, Object> getAllTeacherFront(Page<EduTeacher> pageTeacher);

     Map<String,Object> getTeacherByIdFront(String id);
}
