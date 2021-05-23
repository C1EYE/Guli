package com.guli.teacher.entity.query;

import lombok.Data;

@Data
public class FrontCourseQuery {
    private String subjectId;
    private String keyWord;
    private Integer isNew;
    private Integer priceOrder;
    private Integer hot;
    private Long limit;
    private Long page;

}
