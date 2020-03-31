package com.wxw.cloud.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.wxw.cloud.service.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: wxw
 * @create: 2020-03-31-13:54
 */
@Service
public class UploadServiceImpl implements UploadService {

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif","image/png");

    public static final Logger LOGGER= LoggerFactory.getLogger(UploadServiceImpl.class);

    //DFS 存储服务器
    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public String uploadImage(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        try {
            // 校验文件媒体类型
            String contentType = file.getContentType();
            if (!CONTENT_TYPES.contains(contentType)){
                LOGGER.info("文件类型不合法=>{}", originalFilename);
                return null;
            }
            // 校验文件内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null){
                LOGGER.info("文件内容不合法=>{}", originalFilename);
                return null;
            }
            // 1.保存到服务器
            // file.transferTo(new File("F:\\Study_GO\\BS2020\\images\\"+originalFilename));

            //2. 保存到FastDFS
             String ext = StringUtils.substringAfterLast(originalFilename, ".");
             StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);

            // 1.返回url,进行回显
            //return "http://image.wxw.com/" + originalFilename;
            return "http://image.wxw.com/"+storePath.getFullPath();
        } catch (IOException e) {
            LOGGER.info("服务器内部错误: {},异常信息：{}", originalFilename,e);
        }
        return null;
    }
}
