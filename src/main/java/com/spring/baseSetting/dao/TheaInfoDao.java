package com.spring.baseSetting.dao;

import com.spring.baseSetting.dto.Theainfo;

import java.util.List;
import java.util.Map;

public interface TheaInfoDao {
    List<Theainfo> selectAllThea(Map<String, Object> map);
    int insertTheaInfo(Theainfo theaInfo);

    Theainfo selectTheaInfoByTheaCode(String theaCode);

    int updateTheaInfo(Theainfo theaInfo);

    String selectTheaInfoImgByTheaCode(String theaCode);

    int selectAllTheaCount(Map<String, Object> map);

    int deleteTheaInfo(String theaCode);

    String selectLastTheaCode(String regionName);
    
    List<Theainfo> selectThea();
    
    String theacodeFind(String thea_name);
}
