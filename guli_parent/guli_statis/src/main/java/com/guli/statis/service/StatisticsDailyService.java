package com.guli.statis.service;

import com.guli.statis.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author guli
 * @since 2021-05-18
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    boolean getDataAdd(String day);

    Map<String, Object> getCountData(String begin, String end, String type);
}
