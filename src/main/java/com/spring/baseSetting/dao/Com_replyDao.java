package com.spring.baseSetting.dao;

import com.spring.baseSetting.dto.Com_reply;

import java.util.List;

public interface Com_replyDao {

	//댓글조회
	List<Com_reply> ListreadReply(int com_no);
  
	//댓글작성
    void writeReply(Com_reply comReply);
    
    //댓글 수정
    int updateReply(Com_reply comReply);
    
    //댓글 삭제
    int deleteReply(Com_reply comReply);
    
    //선택 댓글 조회
    Com_reply selectReply(int rno);
    
    
}
