package com.guli.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.teacher.entity.EduCourse;
import com.guli.teacher.entity.EduCourseDescription;
import com.guli.teacher.entity.query.CourseQuery;
import com.guli.teacher.entity.vo.CoursePublishVo;
import com.guli.teacher.entity.vo.CourseVo;
import com.guli.teacher.exception.EduException;
import com.guli.teacher.mapper.EduCourseMapper;
import com.guli.teacher.service.EduChapterService;
import com.guli.teacher.service.EduCourseDescriptionService;
import com.guli.teacher.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.teacher.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;


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

        eduCourseDescriptionService.save(eduCourseDescription);
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

        EduCourseDescription description = eduCourseDescriptionService.getById(id);

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
        boolean b = eduCourseDescriptionService.updateById(courseVo.getDescription());
        return b;

    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        if (courseQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }
        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            queryWrapper.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("subject_id", subjectId);
        }
        baseMapper.selectPage(pageParam, queryWrapper);

    }

    @Override
    public boolean removeCourseById(String id) {
//        //TODO 根据id删除所有视频
//         eduVideoService.removeVideoByCourseId(id);
//        //TODO 根据id删除所有章节
//        eduChapterService.removeByCourseId(id);
//        //FIXME 逻辑删除课程描述
//        boolean b = eduCourseDescriptionService.removeById(id);
//        if (!b) {
//            // 如果描述没有删除成功直接返回
//            return false;
//        }
//        Integer result = baseMapper.deleteById(id);
//
//        return result == 1;
        //根据id删除所有视频
        eduVideoService.removeVideoByCourseId(id);

        //根据id删除所有章节
        eduChapterService.removeByCourseId(id);

        // 删除描述
        boolean b = eduCourseDescriptionService.removeById(id);
        if (!b) {
            return false;
        }
        // 删除基本信息
        int i = baseMapper.deleteById(id);
        return i == 1;

    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        CoursePublishVo coursePublishVo = baseMapper.getCoursePublishVoById(id);
        return coursePublishVo;
    }

    @Override
    public Boolean updateStatusById(String id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus("Normal");
        int update = baseMapper.updateById(course);
        return update > 0;

    }

    //使用Map封装的方式
    @Override
    public Map<String, Object> getCoursePublishMapById(String id) {
        return baseMapper.getCoursePublishMapById(id);
    }

    /**
     * 根据讲师id查询当前讲师的课程列表
     *
     * @param teacherId
     * @return
     */
    @Override
    public List<EduCourse> selectByTeacherId(String teacherId) {

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("teacher_id", teacherId);
        //按照最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");

        List<EduCourse> courses = baseMapper.selectList(queryWrapper);
        return courses;
    }

    @Override
    public Map<String, Object> getCourseListFront(Page<EduCourse> eduCoursePage) {
        baseMapper.selectPage(eduCoursePage, null);
        HashMap<String, Object> map = new HashMap<>();
        //总记录数
        map.put("total", eduCoursePage.getTotal());
        //每页数据集合
        map.put("records", eduCoursePage.getRecords());
        //当前页
        map.put("current", eduCoursePage.getCurrent());
        //每页显示记录数
        map.put("size", eduCoursePage.getSize());
        //总页数
        map.put("pageCount", eduCoursePage.getPages());
        //是否有下一页
        map.put("hasNext", eduCoursePage.hasNext());
        //是否有上一页
        map.put("hasPrevious", eduCoursePage.hasPrevious());

        return map;

    }


}
