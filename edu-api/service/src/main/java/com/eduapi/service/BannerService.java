package com.eduapi.service;

import com.eduapi.pojo.bo.PageBo;
import com.eduapi.pojo.po.Banner;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BannerService {
    List<String> getBannerList();

    PageInfo<Banner> getBannerPage(PageBo bo);

    void saveBannerInfo(Banner banner);
}
