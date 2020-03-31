package com.wxw.cloud.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: wxw
 * @create: 2020-03-31-13:54
 */
public interface UploadService {

    String uploadImage(MultipartFile file);
}
