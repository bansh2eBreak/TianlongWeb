package com.nvyao.controller;

import com.nvyao.anno.Log;
import com.nvyao.pojo.Result;
import com.nvyao.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @Log
    @PostMapping("/upload")
    public Result fileUpload(MultipartFile image) throws Exception {
        log.info("文件上传接口：{}", image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("文件上传成功，文件url是：{}", url);
        return Result.success(url);
    }

}