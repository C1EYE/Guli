package com.guli.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.guli.teacher.entity.EduSubject;
import com.guli.teacher.entity.vo.OneSubject;
import com.guli.teacher.entity.vo.TwoSubject;
import com.guli.teacher.mapper.EduSubjectMapper;
import com.guli.teacher.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author guli
 * @since 2021-04-26
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public List<String> importEXCL(MultipartFile file) {
        List<String> msg = new ArrayList<>();


        try {
            InputStream is = file.getInputStream();
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();
            if (rowNum <= 1) {
                msg.add("请填写数据！");
                return msg;
            }

            for (int i = 1; i < rowNum; i++) {
                HSSFRow curRow = sheet.getRow(i);
                HSSFCell curCell = curRow.getCell(0);
                if (curCell == null) {
                    msg.add("空列:" + i + "行1列为空");
                    continue;
                }
                String cellValue = curCell.getStringCellValue();
                if (StringUtils.isEmpty(cellValue)) {
                    msg.add("空数据" + i + "行1列数据为空");
                    continue;
                }

                EduSubject subject = this.selectSubjectByName(cellValue);
                String pid = null;
                if (subject == null) {
                    subject = new EduSubject();
                    subject.setTitle(cellValue);
                    subject.setParentId("0");
                    subject.setSort(0);
                    baseMapper.insert(subject);
                } else {
                    pid = subject.getId();
                }

                //获取第二列

                curCell = curRow.getCell(1);
                if (curCell == null) {
                    msg.add("空列:" + i + "行2列为空");
                    continue;
                }
                cellValue = curCell.getStringCellValue();

                if (StringUtils.isEmpty(cellValue)) {
                    msg.add("空数据" + i + "行2列数据为空");
                    continue;
                }
                subject = this.selectSubjectByNameAndId(cellValue, pid);
                if (subject == null) {
                    subject = new EduSubject();
                    subject.setTitle(cellValue);
                    subject.setParentId(pid);
                    subject.setSort(0);
                    baseMapper.insert(subject);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public EduSubject selectSubjectByName(String cellValue) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("title", cellValue);
        queryWrapper.eq("parent_id", 0);
        EduSubject result = baseMapper.selectOne(queryWrapper);
        return result;
    }

    @Override
    public EduSubject selectSubjectByNameAndId(String cellValue, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", pid);
        wrapper.eq("title", cellValue);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<OneSubject> treeSubject() {
        List<OneSubject> oneSubjectList = new ArrayList<>();
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        List<EduSubject> oneList = baseMapper.selectList(wrapper);

        for (EduSubject eduSubject : oneList) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject, oneSubject);

            QueryWrapper<EduSubject> twoWarpper = new QueryWrapper<>();
            twoWarpper.eq("parent_id", oneSubject.getId());
            List<EduSubject> twoSubjectList = baseMapper.selectList(twoWarpper);
            List<TwoSubject> twoList = new ArrayList<>();
            for (EduSubject subject : twoSubjectList) {
                TwoSubject twoSubject = new TwoSubject();
                BeanUtils.copyProperties(subject, twoSubject);
                twoList.add(twoSubject);
            }
            oneSubject.setChildren(twoList);
            oneSubjectList.add(oneSubject);
        }
        return oneSubjectList;
    }

    @Override
    public Boolean saveLevelOne(EduSubject subject) {
        EduSubject eduSubject = this.selectSubjectByName(subject.getTitle());
        if (eduSubject != null) {
            return false;
        }

        subject.setSort(0);
        int insert = baseMapper.insert(subject);

        return insert == 1;
    }

    @Override
    public Boolean saveLevelTwo(EduSubject subject) {
        EduSubject eduSubject = this.selectSubjectByName(subject.getTitle());
        if (eduSubject != null) {
            return false;
        }
        subject.setSort(1);
        int insert = baseMapper.insert(subject);
        return insert == 1;
    }

    @Override
    public boolean removeById(Serializable id) {
//                    QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
//        wrapper.eq("parent_id", 0);
//        List<EduSubject> list = baseMapper.selectList(wrapper);
//        if (list.size() != 0) {
//            return false;
//        }
        int i = baseMapper.deleteById(id);
        return i == 1;
    }

}
