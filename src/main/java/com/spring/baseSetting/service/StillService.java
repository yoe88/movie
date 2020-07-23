package com.spring.baseSetting.service;

import com.spring.baseSetting.dto.Still;

import java.util.List;

public interface StillService {
	// 영화별 스틸컷 보여주기(영화정보에)
	List<Still> getMovieStills(String mov_code);

	// 스틸컷 하나씩 보여주기
	Still getOneStill(int still_no);

	// 영화 수정 기존파일 지울때 still_no select
	int removeGetStillNo(String still_img);

	// 스틸컷 insert
	int addStill(Still still);

	// 스틸컷 delete
	int removeStill(String mov_code);

	// 스틸컷 한개 delete(관리자용)
	// 혹시 몰라서 만듬 ㅇㅅㅇ)/
	int removeOneStill(int still_no);

	// 영화상세 누르면 그 영화 첫번째 스틸컷 still_no select
	int getStillNo(String mov_code);
}
