package com.spring.baseSetting.dao.impl;

import com.spring.baseSetting.dao.RoominfoDao;
import com.spring.baseSetting.dto.Roominfo;
import com.spring.baseSetting.dto.Theainfo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("roominfoDao")
public class RoominfoDaolmpl implements RoominfoDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// 마이바티스 mapper xml에서 설정한 mapper 이름
	private final String MAPPER = "mapper.roominfo.";

	private final SqlSession sqlSession;

	public RoominfoDaolmpl(SqlSession sqlSession) {
		logger.info("RoominfoDaoImpl Init...");
		this.sqlSession = sqlSession;
	}

	// 화면에 select할때 (선택한상영관정보불러오기)
	public Roominfo selectRoominfo(Roominfo roominfo) {
		Roominfo roomdata = sqlSession.selectOne(MAPPER + "selectRoominfo", roominfo);
		return roomdata;
	}
	
	//모든 상영관 정보불러오기
	@Override
	public List<Roominfo> selectAllroominfo() {
        List<Roominfo> roominfos = sqlSession.selectList(MAPPER + "selectAllroominfo");
		return roominfos;
	}
	
	
	// 상영관 추가
	@Override
	public int insertRoominfo(Roominfo roominfo) {
		int result = sqlSession.insert(MAPPER + "insertroominfo", roominfo);

		return result;
	}
	
	//상영관삭제
	@Override
	public int deleteRoominfo(Roominfo roominfo) {
		int result = sqlSession.delete(MAPPER+"deleteroominfo", roominfo);
		return result;
	}
	
	//상영관수정
	@Override
	public int updateRoominfo(Roominfo roominfo) {
		int result = sqlSession.update(MAPPER+"updateroominfo", roominfo);
		return result;
	}
	//상영관 인원수정
	@Override
	public int updateroom(Roominfo roominfo) {
		int result = sqlSession.update(MAPPER+"updateroom", roominfo);
		return result;
	}
	
	//영화관코드별 상영관리스트
@Override
public List<Theainfo> selecttheaRoominfo(Theainfo theainfo) {
	List<Theainfo> theainfos = sqlSession.selectList(MAPPER + "selecttheaRoominfo", theainfo);
	return theainfos;
}
	//모든 영화관코드,영화관이름불러오기
	@Override
	public List<Theainfo> selectTheaname() {
		List<Theainfo> Theainfos = sqlSession.selectList(MAPPER + "selectTheaname");
		return Theainfos;
	}
	
	//페이징처리 리스트 불러오기
	@Override
	public List<Roominfo> theainfoselectpaging(Map map) {
		List<Roominfo> Roominfos = sqlSession.selectList(MAPPER + "theainfoselectpaging", map);
		return Roominfos;
		
	}
	
	
	@Override
	public int theatotalpage(String query) {
		int cnt = sqlSession.selectOne(MAPPER+"theatotalpage", query);
		return cnt;
	}
	
	//영화관이름,코드 불러오기 (영화관테이블)
	@Override
	public Theainfo selectthea(String thea_name) {
		Theainfo theainfo = sqlSession.selectOne(MAPPER + "selectthea", thea_name);
		return theainfo;
	}

}//class end
