package com.eduapi.service;

import com.eduapi.pojo.bo.CoursePageBo;
import com.eduapi.pojo.po.Course;
import com.eduapi.pojo.vo.CourseVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CourseService {
    PageInfo<CourseVo> getCoursePage(CoursePageBo bo);
}
