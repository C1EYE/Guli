package com.guli.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.guli.teacher.client.VodClient;
import com.guli.teacher.entity.EduChapter;
import com.guli.teacher.entity.EduCourse;
import com.guli.teacher.entity.EduVideo;
import com.guli.teacher.mapper.EduVideoMapper;
import com.guli.teacher.service.EduChapterService;
import com.guli.teacher.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author guli
 * @since 2021-05-10
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Autowired
    private EduChapterService eduChapterService;

    @Override
    public Boolean removeByVideoId(String id) {
        EduVideo eduVideo = baseMapper.selectById(id);
        String videoSourceId = eduVideo.getVideoSourceId();

        if (StringUtils.isNotEmpty(videoSourceId)) {
            vodClient.removeVideo(videoSourceId);
        }

        int deleted = baseMapper.deleteById(id);
        return deleted == 1;
    }

    @Override
    public Boolean removeVideoByCourseId(String courseId) {
        //根据课程id查询所有视频列表
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.select("video_source_id");
        List<EduVideo> videoList = baseMapper.selectList(queryWrapper);

        //得到所有视频列表的云端原始视频id
        List<String> videoSourceIdList = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            EduVideo video = videoList.get(i);
            String videoSourceId = video.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                videoSourceIdList.add(videoSourceId);
            }
        }

        //调用vod服务删除远程视频
        if(videoSourceIdList.size() > 0){
            vodClient.removeVideoList(videoSourceIdList);
        }

        //删除video表示的记录
        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", courseId);
        Integer count = baseMapper.delete(queryWrapper2);
        return null != count && count > 0;

    }


}
