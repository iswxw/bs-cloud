package com.wxw.cloud.tools;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileInputStream;

/**
 * @Author: wxw
 * @create: 2020-03-27-0:14
 */
public class TestPath {
    public static void main(String[] args) {
        File file = FileUtil.newFile("a.txt");
        // F:\Study_GO\Study_Project\bs-cloud\a.txt
        System.out.println(file.getAbsolutePath());
    }
}
