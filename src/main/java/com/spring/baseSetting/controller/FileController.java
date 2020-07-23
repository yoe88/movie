package com.spring.baseSetting.controller;

import com.spring.baseSetting.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String SE = File.separator;
    private final String uploadPath = "C:" + SE + "upload";
    private final FileService fileService;

    public FileController(FileService fileService) {
        logger.info("FileController Init...");
        this.fileService = fileService;
    }
    /**
     * 이미지 이름 받아서 경로 지정 해주고 다운로드
     */
    @GetMapping("/theainfo/img/{imageFileName:.+}")
    public ResponseEntity<String> printImage(HttpServletResponse response
            , @PathVariable(value = "imageFileName") String imageFileName) throws IOException {

        String filePath;
        filePath = uploadPath + SE + FileService.theaterPath + SE + imageFileName;
        logger.info(filePath);

        File file = new File(filePath);
        fileService.download(response,file,imageFileName,true,false);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
