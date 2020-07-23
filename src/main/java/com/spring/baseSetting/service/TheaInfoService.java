package com.spring.baseSetting.service;

import com.spring.baseSetting.dto.Theainfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface TheaInfoService {

    Map<String, Object> getAllTheaInfoList(String field, String query, int page);
    int addTheaInfo(Theainfo theaInfo);

    Theainfo getTheaInfo(String theaCode);

    int modifyTheaInfo(Theainfo theaInfo, MultipartFile mf);

    int deleteTheaInfo(String theaCode);
    
    List<Theainfo> getThea();
    
    String theacodeFind(String thea_name);
}
