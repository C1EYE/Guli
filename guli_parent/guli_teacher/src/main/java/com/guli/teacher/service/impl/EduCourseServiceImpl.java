package com.guli.teacher.service.impl;

import com.guli.teacher.entity.EduCourse;
import com.guli.teacher.entity.EduCourseDescription;
import com.guli.teacher.entity.vo.CourseVo;
import com.guli.teacher.mapper.EduCourseMapper;
import com.guli.teacher.service.EduCourseDescriptionService;
import com.guli.teacher.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author guli
 * @since 2021-04-30
 */
@Service
@Transactional
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService service;

    /**
     * 保存课程基本信息
     *
     * @param vo
     * @return
     */
    @Override
    public String saveVo(CourseVo vo) {
        baseMapper.insert(vo.getCourse());

        String courseId = vo.getCourse().getId();

        EduCourseDescription eduCourseDescription = vo.getDescription().setId(courseId);

        service.save(eduCourseDescription);
        return null;
    }
}
