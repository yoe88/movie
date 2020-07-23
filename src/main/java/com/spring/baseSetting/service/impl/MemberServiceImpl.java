package com.spring.baseSetting.service.impl;

import com.spring.baseSetting.dao.MemberDao;
import com.spring.baseSetting.dto.Member;
import com.spring.baseSetting.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final MemberDao memberDao;

	public MemberServiceImpl(MemberDao memberDao) {
		logger.info("MemberServiceImpl Init...");
		this.memberDao = memberDao;
	}
	
	
	//濡쒓렇�씤
	@Override
	public String login(Member member, HttpSession session) {
		String mem_lev = memberDao.login(member);
		if (mem_lev != null) {
			session.setAttribute("s_mid", member.getMem_id());
			session.setAttribute("s_mpasswd", member.getMem_passwd());
		}
		return mem_lev;
	}
	
	
	//濡쒓렇�븘�썐
	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}
	

	
	//�븘�씠�뵒以묐났�솗�씤
	@Override
	public int checkid(String ID) {
		int cnt = memberDao.checkID(ID);
		return cnt;
	}
	
	
	//�씠硫붿씪以묐났�솗�씤
	@Override
	public int checkemail(String Email) {
		int cnt = memberDao.checkEmail(Email);
		return cnt;
	}
	
	//�쉶�썝媛��엯
	@Override
	public int insertmember(Member member) {
		int cnt = memberDao.insertmember(member);
		return cnt;
	}
	

	//�쉶�썝�젙蹂댁닔�젙 媛��졇�삤湲�
	@Override
	public Member updatemember(Member member) {
		
		Member memupdate = memberDao.updatemember(member);
		// TODO Auto-generated method stub
		
		return memupdate;
	}
	
	
	//�쉶�썝�젙蹂댁닔�젙
	@Override
	public int updatememberpro(Member member) {
		int cnt = memberDao.updatememberpro(member);
		return cnt;
	}
	
	//�쉶�썝�깉�눜
	@Override
	public int deletemember(Member member) {
		int cnt = memberDao.deletemember(member);
		return cnt;
	}

	//�븘�씠�뵒李얘린
	@Override
	public Member findid(Member member) {
		Member findid = memberDao.findId(member);
		return findid;
	}

	//鍮꾨�踰덊샇李얘린
	@Override
	public Member findpw(Member member) {
		Member findpw = memberDao.findPw(member);
		return findpw;
	}

	
	
	//�쉶�썝�긽�꽭蹂닿린
	@Override
	public Member memberdetail(Member member) {
		Member memberdetail = memberDao.memberdetail(member);
		return memberdetail;
	}

	
	//�쉶�썝�닔�젙�벑濡�_master
	@Override
	public int updatemembermapro(Member member) {
		int cnt = memberDao.updatemembermapro(member);
		return cnt;
	}


	
	//�쉶�썝由ъ뒪�듃�럹�씠吏�
	@Override
	public List<Member> memberpaging(String query, int page) {
		Map<String,Object> map = new HashMap<>();
		map.put("query", query);
		int start = (page-1) * 5;
		int count = 5;
		map.put("start",start);
		map.put("count", count);
			
		List<Member> members = memberDao.memberselectpaging(map);
		return members;
	}


	@Override
	public int membertotal(String query) {
		return memberDao.membertotalpage(query);
	}
	
	
	
	@Override
	public Member getOneMem(String mem_id) {
		Member onemem = memberDao.selectOneMem(mem_id);
		
		return onemem;
	}

	//나의 예매 내역 가져오기 그냥 여기에 넣었음
	@Override
	public List<Map<String, Object>> myRes(String id) {
		return memberDao.myRes(id);
	}

	/** 아이디로 포인트 정보 얻기
	 * @return
	 */
	@Override
	public int selectPointById(String id) {
		return memberDao.selectPointById(id);
	}
	
	@Override
	public int addPoint(Member member) {
		int result = memberDao.plusPoint(member);
		
		return result;
	}


	@Override
	public int selectResMoney(String mem_id) {
		int money = memberDao.selectResMoney(mem_id);
		
		return money;
	}


	@Override
	public int modifyMemLev(Member member) {
		int result = memberDao.updateMemLev(member);
		
		return result;
	}



}
