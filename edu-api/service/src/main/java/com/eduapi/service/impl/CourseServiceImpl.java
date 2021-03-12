package com.eduapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eduapi.mapper.CourseMapper;
import com.eduapi.pojo.bo.CoursePageBo;
import com.eduapi.pojo.po.Course;
import com.eduapi.pojo.vo.CourseVo;
import com.eduapi.service.CourseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper mapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageInfo<CourseVo> getCoursePage(CoursePageBo bo) {
        PageHelper.startPage(bo.getPageNum(), bo.getPageSize());
        List<CourseVo> list = mapper.selectCoursePage(bo);
        PageInfo<CourseVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
