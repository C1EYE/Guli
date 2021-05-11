package com.guli.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.teacher.entity.EduChapter;
import com.guli.teacher.entity.EduVideo;
import com.guli.teacher.entity.vo.OneChapter;
import com.guli.teacher.entity.vo.TwoVideo;
import com.guli.teacher.exception.EduException;
import com.guli.teacher.mapper.EduChapterMapper;
import com.guli.teacher.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.teacher.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author guli
 * @since 2021-04-30
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<OneChapter> getVoById(String id) {
        ArrayList<OneChapter> result = new ArrayList<>();

        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id ", id);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);
        for (EduChapter eduChapter : eduChapters) {
            OneChapter oneChapter = new OneChapter();
            BeanUtils.copyProperties(eduChapter, oneChapter);
            QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
            videoWrapper.eq("chapter_id", oneChapter.getId());
            videoWrapper.orderByAsc("sort");
            List<EduVideo> eduVideos = eduVideoService.list(videoWrapper);

            for (EduVideo eduVideo : eduVideos) {
                TwoVideo twoVideo = new TwoVideo();
                BeanUtils.copyProperties(eduVideo,twoVideo);
                oneChapter.getChildren().add(twoVideo);
            }
            result.add(oneChapter);
        }


        return result;
    }

    @Override
    public Boolean removeByChapterId(String id) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",id);
        List<EduVideo> list = eduVideoService.list(wrapper);
        if(list.size() != 0){
            throw new EduException(20001,"该分章节下存在视频课程，请先删除视频课程");
        }
        //删除章节
        int delete = baseMapper.deleteById(id);
        return delete > 0;

    }
}
