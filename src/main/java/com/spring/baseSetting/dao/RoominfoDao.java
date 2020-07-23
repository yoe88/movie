package com.spring.baseSetting.dao;
import com.spring.baseSetting.dto.Roominfo;
import com.spring.baseSetting.dto.Theainfo;

import java.util.List;
import java.util.Map;

public interface RoominfoDao {//상영관정보테이블
	
	//선택한 상영관 정보불러오기 
	Roominfo selectRoominfo(Roominfo roominfo);
	//모든 상영관 정보 불러오기
	List<Roominfo> selectAllroominfo();
	//상영관 추가
	int insertRoominfo(Roominfo roominfo);
	//상영관삭제
	int deleteRoominfo(Roominfo roominfo);
	//상영관수정
	int updateRoominfo(Roominfo roominfo);
	//상영관 인원수정
	int updateroom(Roominfo roominfo);
	//영화관별 상영관리스트
	List<Theainfo> selecttheaRoominfo(Theainfo theainfo);
	//모든 영화관코드,영화관이름 불러오기
	List<Theainfo> selectTheaname();
	//페이징 리스트	
	List<Roominfo> theainfoselectpaging(Map<String, Object> map);
	//총개수
	int theatotalpage(String query);
	//영화관,영화관코드 불러오기(thea_info 테이블)
	Theainfo selectthea(String thea_name);

}
