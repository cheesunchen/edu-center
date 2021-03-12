package com.eduapi.pojo.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageBo {
    @ApiModelProperty(value = "页码", name = "pageNum", example = "1")
    private Integer pageNum;

    @ApiModelProperty(value = "每页条数", name = "pageSize", example = "10")
    private Integer pageSize;
}
