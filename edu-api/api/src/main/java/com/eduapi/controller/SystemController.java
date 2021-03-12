package com.eduapi.controller;

import com.eduapi.pojo.bo.PageBo;
import com.eduapi.pojo.po.Banner;
import com.eduapi.service.BannerService;
import com.eduapi.service.FileService;
import com.eduapi.utils.JSONResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Api(value = "系统管理controller", tags = {"系统管理接口相关的api"})
@RestController
@RequestMapping("/system/")
public class SystemController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "小程序获取banner列表", notes = "小程序获取banner列表", httpMethod = "GET")
    @GetMapping("getBannerList")
    public JSONResult getBannerList() {
        List<String> bannerList = bannerService.getBannerList();
        return JSONResult.ok(bannerList);
    }

    @ApiOperation(value = "管理后台获取banner列表", notes = "管理后台获取banner列表", httpMethod = "POST")
    @PostMapping("getBannerPage")
    public JSONResult getBannerPage(@RequestBody PageBo bo) {
        PageInfo<Banner> page = bannerService.getBannerPage(bo);
        return JSONResult.ok(page);
    }

    @ApiOperation(value = "保存banner信息", notes = "返回图片地址", httpMethod = "POST")
    @PostMapping("saveBannerInfo")
    public JSONResult saveBannerInfo(@RequestBody Banner banner) {
        bannerService.saveBannerInfo(banner);
        return JSONResult.ok();
    }

    @ApiOperation(value = "上传图片", notes = "返回图片地址", httpMethod = "POST")
    @PostMapping("uploadFile")
    public JSONResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        return JSONResult.ok(fileService.uploadFile2Local(file, request));
    }
}
