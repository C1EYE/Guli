package com.guli.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.teacher.entity.EduCourse;
import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.entity.query.TeacherQuery;
import com.guli.teacher.mapper.EduTeacherMapper;
import com.guli.teacher.service.EduCourseService;
import com.guli.teacher.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author guli
 * @since 2021-04-12
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (teacherQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (level!=null) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        baseMapper.selectPage(pageParam, queryWrapper);

    }

    @Override
    public Map<String, Object> getAllTeacherFront(Page<EduTeacher> pageTeacher) {
        baseMapper.selectPage(pageTeacher, null);
        HashMap<String, Object> map = new HashMap<>();
        //总记录数
        map.put("total", pageTeacher.getTotal());
        //每页数据集合
        map.put("records", pageTeacher.getRecords());
        //当前页
        map.put("current", pageTeacher.getCurrent());
        //每页显示记录数
        map.put("size", pageTeacher.getSize());
        //总页数
        map.put("pageCount", pageTeacher.getPages());
        //是否有下一页
        map.put("hasNext", pageTeacher.hasNext());
        //是否有上一页
        map.put("hasPrevious", pageTeacher.hasPrevious());

        return map;
    }

    @Override
    public Map<String, Object> getTeacherByIdFront(String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        List<EduCourse> list = eduCourseService.selectByTeacherId(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("teacher", teacher);
        map.put("courses", list);
        return map;
    }
}
