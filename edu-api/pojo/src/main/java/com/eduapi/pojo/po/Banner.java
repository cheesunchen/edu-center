package com.eduapi.pojo.po;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Banner extends BasePo {
    private Long id;

    private String url;

    private Integer sort;

    private Integer status;
}
