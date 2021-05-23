package com.guli.statis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.common.result.Result;
import com.guli.statis.client.UcenterClient;
import com.guli.statis.entity.StatisticsDaily;
import com.guli.statis.mapper.StatisticsDailyMapper;
import com.guli.statis.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author guli
 * @since 2021-05-18
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    UcenterClient ucenterClient;

    @Override
    public boolean getDataAdd(String day) {
        Result result = ucenterClient.countRegisterNum(day);
        Integer registerNum = (Integer) result.getData().get("registerNum");
        //删除相同日期
        QueryWrapper<StatisticsDaily> statisticsDailyQueryWrapper = new QueryWrapper<>();
        statisticsDailyQueryWrapper.eq("date_calculated", day);
        try {
            baseMapper.delete(statisticsDailyQueryWrapper);
        } catch (Exception e) {

        }
        //将统计数据添加到数据库
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setRegisterNum(registerNum);
        //TODO 查询剩余字段填充
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100, 200));
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100, 200));
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100, 200));



        int insert = baseMapper.insert(statisticsDaily);
        return insert > 0;
    }

    @Override
    public Map<String, Object> getCountData(String begin, String end, String type) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated", begin, end);
        wrapper.select("date_calculated", type);
        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(wrapper);
        //日期集合
        List<String> calculatedList = new ArrayList<>();
        //数据集合
        List<Integer> dataList = new ArrayList<>();

        for (StatisticsDaily sta : statisticsDailies) {
            String dateCalculated = sta.getDateCalculated();
            calculatedList.add(dateCalculated);
            switch (type) {
                case "register_num":
                    dataList.add(sta.getRegisterNum());
                    break;
                case "login_num":
                    dataList.add(sta.getLoginNum());
                    break;
                case "video_view_num":
                    dataList.add(sta.getVideoViewNum());
                    break;
                case "course_num":
                    dataList.add(sta.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("calculatedList", calculatedList);
        map.put("dataList", dataList);
        return map;
    }
}
