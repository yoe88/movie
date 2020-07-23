package com.spring.baseSetting.dao.impl;

import com.spring.baseSetting.dao.MemberDao;
import com.spring.baseSetting.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {


	
	private final String MAPPER = "mapper.member.";

	private final SqlSession sqlSession;

	public MemberDaoImpl(SqlSession sqlSession) {
		log.info("MemberDaoImpl Init...");
		this.sqlSession = sqlSession;
	}
	

	@Override
	public String login(Member member) {
		String meminfo = sqlSession.selectOne(MAPPER + "login", member);
		// Member member = sqlSession.selectOne(MAPPER+"selectMember", member);
		return meminfo;
	}
	

	@Override
	public void logout(HttpSession session) {
	
	}
	
	

	@Override
	public int checkID(String id) {
		int cnt = sqlSession.selectOne(MAPPER + "checkID", id); // ID=duplecateID(String ID)
		return cnt;
	}
	
	

	@Override
	public int checkEmail(String Email) {
		int cnt = sqlSession.selectOne(MAPPER + "checkEmail", Email);
		return cnt;
	}

	

	@Override
	public int insertmember(Member member) {
		int cnt = sqlSession.insert(MAPPER + "insertmember", member);
		return cnt;
	}
	

		@Override
		public Member updatemember(Member member) {
			Member memupdate = sqlSession.selectOne(MAPPER + "updatemember", member);
			return memupdate;
		}
	

	@Override
	public int updatememberpro(Member member) {
		int cnt = sqlSession.update(MAPPER + "updatememberpro", member);
		return cnt;
	}
	
	@Override
	public int deletemember(Member member) {
		int cnt = sqlSession.update(MAPPER + "deletemember", member);
		return cnt;
	}
	
	@Override
	public Member findId(Member member) {
		Member findid = sqlSession.selectOne(MAPPER + "findId", member);
		return findid;
	}
	
	@Override
	public Member findPw(Member member) {
		Member findpw = sqlSession.selectOne(MAPPER + "findPw", member);
		return findpw;
	}
	

	@Override
	public Member memberdetail(Member member) {
		Member memberdetail = sqlSession.selectOne(MAPPER + "memberdetail" , member);
		return memberdetail;
	}
	
	
		@Override
	public int updatemembermapro(Member member) {
		int cnt = sqlSession.update(MAPPER + "updatemembermapro", member);
		return cnt;
	}

	
	

	@Override
	public List<Member> memberselectpaging(Map map) {
		List<Member> members = sqlSession.selectList(MAPPER + "memberselectpaging", map);
		return members;
	}

	@Override
	public int membertotalpage(String query) {
		int cnt = sqlSession.selectOne(MAPPER+"membertotalpage", query);
		return cnt;
	}
	
	
	@Override
	public Member selectOneMem(String mem_id) {
		Member onemem = sqlSession.selectOne(MAPPER + "selectOneMem", mem_id);
		
		return onemem;
	}

	@Override
	public List<Map<String, Object>> myRes(String id) {
		List<Map<String, Object>> list = sqlSession.selectList("mapper.res.myRes", id);
		//log.info(list.toString());
		return list;
	}

	@Override
	public int selectPointById(String id) {
		return sqlSession.selectOne(MAPPER + "selectPointById", id);
	}

	@Override
	public void updatePointById(Map<String, Object> member) {
		sqlSession.update(MAPPER + "updatePointById", member);
	}
	
	
	@Override
	public int plusPoint(Member member) {
		int result = sqlSession.update(MAPPER + "plusPoint", member);
		
		return result;
	}

	@Override
	public int selectResMoney(String mem_id) {
		int money = sqlSession.selectOne(MAPPER + "selectResMoney", mem_id);
		
		return money;
	}

	@Override
	public int updateMemLev(Member member) {
		int result = sqlSession.update(MAPPER + "updateMemLev", member);
		
		return result;
	}
}
