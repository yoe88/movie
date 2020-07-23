package com.spring.baseSetting.service;


import com.spring.baseSetting.dto.Res;

import java.util.Map;


public interface ResService {
	int selectres(String res_code);
	Res findshowcode(Map map);
	int insertres(Res res);
	int insertseat(Map map);

    int deleteRes(String resCode, String id, int point);

    Map<String, String> selectNames(String showCode);

    String selectLastResCode(String resCode);

    boolean insertRes(Map<String, Object> map);
    
    
    String selectposter(String mov_title);
}
