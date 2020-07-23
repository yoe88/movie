package com.spring.baseSetting.service.impl;

import com.spring.baseSetting.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@Slf4j
@Service
public class FileServiceImpl implements FileService {


    /**
     * @param response       응답객체
     * @param file           파일
     * @param originFileName 원본 파일명
     */
    @Override
    public void download(HttpServletResponse response
            , File file
            , String originFileName
            , boolean isImage
            , boolean isDownload) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        if (isDownload) {
            response.addHeader("Content-disposition", "attachment; fileName=" + URLEncoder.encode(originFileName, "UTF-8"));
        } else {
            response.addHeader("Content-disposition", "inline; fileName=" + URLEncoder.encode(originFileName, "UTF-8"));
        }
        if (isImage) {
            String fileName = file.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
            response.setContentType(String.format("image/%s", fileExtension));
        }
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[1024 * 8];
        while (true) {
            int count = in.read(buffer); // 버퍼에 읽어들인 문자개수
            if (count == -1) // 버퍼의 마지막에 도달했는지 체크
                break;
            out.write(buffer, 0, count);
        }
        in.close();
        out.close();
    }

    /**
     * @param mf         단일 파일
     * @param folderName 파일이 들어갈 폴더
     * @return 파일이 성공적으로 업로드 되었으면 파일이름, 실패 했으면 null
     */
    @Override
    public String upload(MultipartFile mf, String folderName) {
        log.info("업로드 시작");
        String folderPath = uploadPath + SE + folderName;  //파일이 들어갈 폴더경로
        log.info(folderPath);
        File folderFile = new File(folderPath);
        if (!folderFile.exists())
            folderFile.mkdirs();
        String originalFileName = mf.getOriginalFilename(); // 원본 파일 명
        String safeFile = System.currentTimeMillis() + originalFileName; // 수정된 파일이름
        String safeFilePath = folderPath + SE + safeFile; // 파일경로
        try {
            mf.transferTo(new File(safeFilePath));
            return safeFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @param folderName 삭제할 파일이 들어 있는 폴더
     * @param fileName   삭제할 파일 이름
     * @return 삭제가 정상적으로 됐으면  true 실패 했으면 false
     */
    @Override
    public boolean deleteFile(String folderName, String fileName) {
        String filePath = uploadPath + SE + folderName + SE + fileName;//파일경로
        log.info(filePath);
        try {
            File file = new File(filePath);
            if (file.exists())
                file.delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




}
