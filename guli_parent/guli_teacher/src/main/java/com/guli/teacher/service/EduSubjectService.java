package com.guli.teacher.service;

import com.guli.teacher.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author guli
 * @since 2021-04-26
 */
public interface EduSubjectService extends IService<EduSubject> {

    List<String> importEXCL(MultipartFile file);

    EduSubject selectSubjectByName(String cellValue);

    EduSubject selectSubjectByNameAndId(String cellValue, String pid);
}
