package com.eduapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eduapi.mapper.BannerMapper;
import com.eduapi.pojo.bo.PageBo;
import com.eduapi.pojo.po.Banner;
import com.eduapi.pojo.vo.CourseVo;
import com.eduapi.service.BannerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<String> getBannerList() {
        return bannerMapper.getBannerList();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageInfo<Banner> getBannerPage(PageBo bo){
        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("status");
        queryWrapper.orderByDesc("update_time");
        queryWrapper.orderByAsc("sort");
        PageHelper.startPage(bo);
        List<Banner> list = bannerMapper.selectList(queryWrapper);
        PageInfo<Banner> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveBannerInfo(Banner banner) {
        Date now = new Date();
        if (null != banner.getId()) {
            banner.setUpdateTime(now);
            bannerMapper.updateById(banner);
        } else {
            banner.setCreateTime(now)
                    .setCreateUser(1L)
                    .setUpdateTime(now)
                    .setUpdateUser(1L);
            bannerMapper.insert(banner);
        }
    }
}
