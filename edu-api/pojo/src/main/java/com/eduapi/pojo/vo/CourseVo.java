package com.eduapi.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CourseVo {
    private Long id;

    private String name;

    private String cover;

    private Integer CourseAmount;

    private String teacherName;

    private BigDecimal price;

    private String description;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private String createUser;
}
