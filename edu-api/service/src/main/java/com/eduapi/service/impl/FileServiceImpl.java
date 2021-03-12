package com.eduapi.service.impl;

import com.eduapi.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    private final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${file.upload-address}")
    private String uploadAddress;

    @Value("${file.show.prefix}")
    private String prefix;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String uploadFile2Local(MultipartFile file, HttpServletRequest request) throws IOException {
        String realpath="";
        //获取文件名
        String name="";
        if (file != null) {
//            long size = file.getSize();
//            if (size > 5242880) {//文件设置大小，我这里设置5M。
//                throw new KingException(BizExceptionEnum.FILE_UPLOAD_ERROR);
//            }

            //直接返回文件的名字
            name = file.getOriginalFilename();
            String suffix = name.substring(name.lastIndexOf(".") + 1, name.length());
            String fileName = System.currentTimeMillis() + "." + suffix;
            log.info("==============================================");
            log.info("=============uploadAddress :" + uploadAddress);
            log.info("==============================================");
            File filePath = new File(uploadAddress);
            if (!filePath.exists()) {//目录不存在就创建
                filePath.mkdirs();
            }
            File tempFile = new File(uploadAddress + fileName);
            FileOutputStream output = null;
            try {
                output = new FileOutputStream(tempFile);
                output.write(file.getBytes());
                output.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            realpath = prefix + fileName;
        }
        return realpath;
    }
}
