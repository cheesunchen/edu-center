package com.eduapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eduapi.pojo.bo.CoursePageBo;
import com.eduapi.pojo.po.Course;
import com.eduapi.pojo.vo.CourseVo;

import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {
    List<CourseVo> selectCoursePage(CoursePageBo bo);
}
