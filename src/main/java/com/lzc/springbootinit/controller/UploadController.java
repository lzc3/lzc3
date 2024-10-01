package com.lzc.springbootinit.controller;

import com.lzc.springbootinit.pojo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {

    @PostMapping("/upload")
    public ResponseVo upload(@RequestParam("username") String username,
                             @RequestParam("age") Integer age,
                             @RequestParam("image") MultipartFile images) throws IOException {
        String originalFilename = images.getOriginalFilename();
        log.info("文件上传, {}, {}, {}", username, age, originalFilename);

        String uploadPathPrefix = "C:\\lzc\\app\\workplace\\Intellij\\owner\\init\\spring-boot-init\\src\\main\\resources\\upload\\";
        String uuid = UUID.randomUUID().toString();
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String storeName = uuid + extname;
        log.info("存储文件名：{}", storeName);

        images.transferTo(new File(uploadPathPrefix + storeName));
        return ResponseVo.success();
    }

}
