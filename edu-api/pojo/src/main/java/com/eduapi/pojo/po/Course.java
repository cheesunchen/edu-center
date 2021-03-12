package com.eduapi.pojo.po;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Course extends BasePo {
    private Long id;

    private String name;

    private Integer courseAmount;

    private Long teacherId;

    private String description;

    private BigDecimal price;

    private String cover;

    private Integer status;
}
