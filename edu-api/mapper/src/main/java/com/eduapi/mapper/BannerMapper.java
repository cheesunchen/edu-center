package com.eduapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eduapi.pojo.po.Banner;
import com.eduapi.pojo.po.Course;

import java.util.List;

public interface BannerMapper extends BaseMapper<Banner> {
    List<String> getBannerList();
}
