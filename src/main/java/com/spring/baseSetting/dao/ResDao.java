package com.spring.baseSetting.dao;

import com.spring.baseSetting.dto.Res;

import java.util.Map;

public interface ResDao {
	int selectres(String res_code);   
	Res findshowcode(Map map);
	int insertres(Res res);
	int insertseat(Map map);

    int deleteResByResCode(String resCode);

    Map<String, String> selectNames(Map<String, String> map);

	String selectLastResCode(String resCode);

    void insertRes(Res res);
    
    String selectposter(String mov_title);
}
