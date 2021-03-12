package com.eduapi.pojo.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class BasePo {
    private Date createTime;

    private Long createUser;

    private Date updateTime;

    private Long updateUser;
}
