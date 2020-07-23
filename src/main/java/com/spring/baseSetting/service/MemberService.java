package com.spring.baseSetting.service;

import com.spring.baseSetting.dto.Member;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface MemberService {
	String login(Member member, HttpSession session);
    void logout(HttpSession session);
    int checkid(String id);
    int checkemail(String Email);
    int insertmember(Member member);
    Member updatemember(Member member);
    int updatememberpro(Member member);
    int deletemember(Member member);
    Member findid(Member member);
    Member findpw(Member member);
	Member memberdetail(Member member);
	int updatemembermapro(Member member);
	
	List<Member> memberpaging(String query, int page);
	int membertotal(String query);
    
	Member getOneMem(String mem_id);


    List<Map<String, Object>> myRes(String id);

    int selectPointById(String id);
    
  //포인트 추가
    int addPoint(Member member);

    //6개월간 예매 금액 가져오기
    int selectResMoney(String mem_id);
    
    //등급 갱신
    int modifyMemLev(Member member);
}
