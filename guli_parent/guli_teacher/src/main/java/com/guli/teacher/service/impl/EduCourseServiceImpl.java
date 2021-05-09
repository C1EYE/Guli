package com.guli.teacher.service.impl;

import com.guli.teacher.entity.EduCourse;
import com.guli.teacher.entity.EduCourseDescription;
import com.guli.teacher.entity.vo.CourseVo;
import com.guli.teacher.exception.EduException;
import com.guli.teacher.mapper.EduCourseMapper;
import com.guli.teacher.service.EduCourseDescriptionService;
import com.guli.teacher.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
        return courseId;
    }

    @Override
    public CourseVo getCourseVoById(String id) {
        CourseVo courseVo = new CourseVo();

        EduCourse eduCourse = baseMapper.selectById(id);

        if (eduCourse == null) {
            throw new EduException(20001, "此课程不存在");
        }

        courseVo.setCourse(eduCourse);

        EduCourseDescription description = service.getById(id);

        if (description != null) {
            courseVo.setDescription(description);
        }
        System.out.println("!@#$%^&*(*&^%$#@!#$%^%$#@!@#$%" + courseVo);
        return courseVo;
    }

    @Override
    public boolean updateCourse(CourseVo courseVo) {

        if (StringUtils.isEmpty(courseVo.getCourse().getId())) {
            throw new EduException(20001, "没有课程编号，修改失败");
        }
        int i = baseMapper.updateById(courseVo.getCourse());
        if (i <= 0) {
            throw new EduException(20001, "修改课程信息失败");
        }
        //获取course—ID
        String courseId = courseVo.getCourse().getId();
        //设置课程描述的ID
        courseVo.getDescription().setId(courseId);
        boolean b = service.updateById(courseVo.getDescription());
        return b;

    }
}
