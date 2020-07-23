package com.spring.baseSetting.dao;

import com.spring.baseSetting.dto.Member;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface MemberDao {
    //List<Member> selectMember();

    
	String login(Member member);
	public void logout(HttpSession session);
    int checkID(String id);
    int checkEmail(String Email);
    int insertmember(Member member);
    Member updatemember(Member member);
    int updatememberpro(Member member);
    int deletemember(Member member);
    Member findId(Member member);
    Member findPw(Member member);
	Member memberdetail(Member member);
	int updatemembermapro(Member member);
	
	
	List<Member> memberselectpaging(Map<String, Object> map);
	
	int membertotalpage(String query);
	
	
	Member selectOneMem(String mem_id);

    List<Map<String, Object>> myRes(String id);

    int selectPointById(String id);


    void updatePointById(Map<String, Object> member);
    
  //포인트 추가
    int plusPoint(Member member);
    
    //6개월간 예매 금액 가져오기
    int selectResMoney(String mem_id);
    
    //등급 갱신
    int updateMemLev(Member member);
}



