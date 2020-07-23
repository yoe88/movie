package com.spring.baseSetting.dao;

import com.spring.baseSetting.dto.Mov_show;

import java.util.List;
import java.util.Map;


public interface Mov_showDao {
  
  //예매할 수 있는 시간이 지나면 버튼 비활성화하기(예매불가시간 : 영화시작 10분전)
  List<Mov_show> selectMovByThea(Map m);//영화관선택-그 영화관에서 볼 수 있는 영화 보여주기(중복삭제)(예매페이지에서 보여줄 자료)
  
  //선택 날짜와 영화관에서 볼 수 있는 영화 리스트 가져오기. 정렬은 상영날짜 내림차순
  List<Mov_show> selectMov_title(Map m);

  //오늘 이후부터 4일 간의 모든 상영정보 가져오기
  List<Mov_show> selectmovAll();
  
  //관리자용 상영표 보여주기
  List<Mov_show> selectmovAllMaster(Map m);
  
  //하나의 상영정보 가져오기(수정용)
  Mov_show readMov_show(String show_code);
  
  //상영정보 추가
  int insertMov_show(Mov_show mov_show);
  //수정
  int updateMov_show(Mov_show mov_show);
  //상영정보 삭제
  int deleteMov_show(Mov_show mov_show);
}
