package com.spring.baseSetting.dao;

import com.spring.baseSetting.dto.Com_notice;
import com.spring.baseSetting.dto.SearchCriteria;

import java.util.List;

public interface Com_noticeDao {

    //1.5 게시물 목록 조회
    List<Com_notice> comNoticeList(SearchCriteria scri);
    
    //게시물 총 갯수
    int listCount(SearchCriteria scri);
    
    
    //movtalk
    List<Com_notice> selectMovtalk(SearchCriteria scri);
    int movtalkCount(SearchCriteria scri);
    
    //que
    List<Com_notice> selectQue(SearchCriteria scri);
    int queCount(SearchCriteria scri);
    
    //myque
    List<Com_notice> selectmyQue(SearchCriteria scri);
    
    
    //1.7 조회수 증가
    int upVisitnum(int com_no);
    
    //2제목만 보여주기
    Com_notice selectComNoticeById(String com_title);
    
    
    //3하나의게시물만보기
    Com_notice selectOneComNotice(int com_no);
    //3.5 답글
    int replyOne(Com_notice comNotice);
    //3.7 답글수
    int repnoCount(int com_no);    
    
    
    
    //4 게시물 작성 com_notice insert
    int insertComNotice(Com_notice comNotice);
    //4.5 com_filenameMF
    
    
	//5com_notice delete
	int deleteComNotice(int com_no);
	
	//6com_notice update
	int updateComNotice(Com_notice comNotice);
	
	
	//인기글 가져오는거
	List<Com_notice> selectPopular();
	
	//최신글 가져오기
	List<Com_notice> selectNew();
}
