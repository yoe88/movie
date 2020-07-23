package com.spring.baseSetting.service;

import java.util.List;

import com.spring.baseSetting.dto.Mov_grade;

public interface Mov_gradeService {
  //한 영화의 평균평점 가져오기
  float getAvgGrade(String mov_code);
  //한 영화의 전체 평점 가져오기(한 페이지에 10개?)
  List<Mov_grade> getMovAllgrade(String mov_code);

  //모든 평점 가져오기
  List<Mov_grade> getAllgrade();
  
  //평점작성
  int addMov_grade(Mov_grade mov_grade);
  //평점수정
  int modifyMov_grade(Mov_grade mov_grade);
  //평점삭제(관리자도 가능)
  int removeMov_grade(Mov_grade mov_grade);
}
