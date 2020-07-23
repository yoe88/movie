package com.spring.baseSetting.dao;

import java.util.List;

import com.spring.baseSetting.dto.Still;

public interface StillDao {
	
	//영화별 스틸컷 보여주기(영화정보에)
	List<Still> selectMovieStills(String mov_code) ;
	
	//스틸컷 하나씩 보여주기
	Still selectOneStill(int still_no);
	
	//영화 수정 기존파일 지울때 still_no select
	int deleteSelectStillNo(String still_img);
	
	//스틸컷 insert
	int insertStill(Still still);
	
	//스틸컷 delete
	int deleteStill(String mov_code);
	
	//스틸컷 한개 delete(관리자용)
	//혹시 몰라서 만듬 ㅇㅅㅇ)/
	int deleteOneStill(int still_no);
	

	//영화상세 누르면 그 영화 첫번째 스틸컷 still_no select 
	int selectStillNo(String mov_code);

	
	
}//StillDao class end
