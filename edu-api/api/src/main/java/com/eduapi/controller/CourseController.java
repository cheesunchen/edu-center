package com.eduapi.controller;

import com.eduapi.pojo.bo.CoursePageBo;
import com.eduapi.service.CourseService;
import com.eduapi.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "课程controller", tags = {"课程接口相关的api"})
@RestController
@RequestMapping("/course/")
public class CourseController {

    @Autowired
    private CourseService service;

    @ApiOperation(value = "获取课程分页列表", notes = "获取课程分页列表", httpMethod = "POST")
    @PostMapping("getCoursePage")
    public JSONResult getCoursePage(@RequestBody CoursePageBo bo) {
        return JSONResult.ok(service.getCoursePage(bo));
    }
}
