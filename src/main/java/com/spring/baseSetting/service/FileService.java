package com.spring.baseSetting.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public interface FileService {
    String SE = File.separator;
    String uploadPath = "C:" + SE + "upload";
    String theaterPath = "theater";

    void download(HttpServletResponse response
            , File file
            , String originFileName
            , boolean isImage
            , boolean isDownload) throws IOException;

    String upload(MultipartFile mf, String folderName);
    boolean deleteFile(String folderName, String fileName);
}
