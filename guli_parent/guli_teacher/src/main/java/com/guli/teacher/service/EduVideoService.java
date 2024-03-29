package com.guli.teacher.service;

import com.guli.teacher.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author guli
 * @since 2021-05-10
 */
public interface EduVideoService extends IService<EduVideo> {

    Boolean removeByVideoId(String id);

    /**
     * 根据课程ID删除小节
     * @param courseId
     * @return
     */
    Boolean removeVideoByCourseId(String courseId);

}
