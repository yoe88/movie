package com.spring.baseSetting.service;

import com.spring.baseSetting.dto.Com_notice;
import com.spring.baseSetting.dto.SearchCriteria;

import java.util.List;


public interface ComNoticeService {

    //1.5 게시물 목록 조회 comAllList
    List<Com_notice> comAllList(SearchCriteria scri);
    
    // 게시물 총 갯수
    int listCount(SearchCriteria srci);
    
    
    //movtalk
    List<Com_notice> getMovtalk(SearchCriteria scri);
    int getmovtalkCount(SearchCriteria scri);
    
    //que
    List<Com_notice> getQue(SearchCriteria scri);
    int getqueCount(SearchCriteria scri);
    
    
  //myque
    List<Com_notice> getmyQue(SearchCriteria scri);

    
    
    //1.7 조회수 증가
    int upHit(int com_no);
    
    //3게시물하나보기
    Com_notice getOneComNotice(int i);
    
    
    //4com_notice insert
    int putComNotice(Com_notice comNotice);
    
    //5com_notice delete	
    int cutComNotice(int com_no);
    
    //6com_notice update
    int goComNotice(Com_notice comNotice);

    
    //인기글 가져오기
    List<Com_notice> getPopular();
    
    //최신글 가져오기
    List<Com_notice> getNew();
}
