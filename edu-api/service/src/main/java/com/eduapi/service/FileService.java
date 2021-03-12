package com.eduapi.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface FileService {

    String uploadFile2Local(MultipartFile file, HttpServletRequest request) throws IOException;
}
