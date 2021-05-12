package com.guli.teacher.mapper;

import com.guli.teacher.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.teacher.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author guli
 * @since 2021-04-30
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     *
     * @param id 用户id
     * @return 封装前端数据
     */
    CoursePublishVo getCoursePublishVoById(String id);

    Map<String, Object> getCoursePublishMapById(String id);
}
