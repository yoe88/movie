package com.spring.baseSetting.service;

import com.spring.baseSetting.dto.Com_reply;

import java.util.List;


public interface ComReplyService {

    // 댓글 조회
    List<Com_reply> ListreadReply2(int com_no);
    
    // 댓글 작성
    void writeReply (Com_reply comReply);
    
    // 댓글 수정
    int updateReply (Com_reply comReply);
    
    // 댓글 삭제
    int deleteReply (Com_reply comReply);
    
    // 선택한 댓글 조회
    Com_reply selectReply(int rno);
    
    
}
