package com.spring.baseSetting.dao.impl;

import com.spring.baseSetting.dao.SeatinfoDao;
import com.spring.baseSetting.dto.Seatinfo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("seatinfoDao")
public class SeatinfoDaolmpl implements SeatinfoDao {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// 마이바티스 mapper xml에서 설정한 mapper 이름
	private final String MAPPER = "mapper.seatinfo.";

	private final SqlSession sqlSession;

	public SeatinfoDaolmpl(SqlSession sqlSession) {
		logger.info("SeatinfoDaoImpl Init...");
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Seatinfo> selectseats(String room_code) {
		 List<Seatinfo> seatdate = sqlSession.selectList(MAPPER+"selectseats" , room_code);
		 System.out.println(seatdate.toString());
		return seatdate;
	}
	
	// 화면에 select할때
	@Override
	public Seatinfo selectseatinfo(Seatinfo seatinfo) {
		Seatinfo seatdata = sqlSession.selectOne(MAPPER + "selectseatinfo", seatinfo);
		return seatdata;
	}

	// 좌석정보 추가
	@Override
	public int insertseatinfo(Seatinfo seatinfo) {
		int result = sqlSession.insert(MAPPER + "insertseatinfo", seatinfo);

		return result;
	}

	
	//선택한 좌석정보 삭제
		@Override
		public int deleteseatinfo(String room_code) {
			int result = sqlSession.delete(MAPPER + "deleteseatinfo", room_code);
			
			return result;
		}
		
		//좌석정보 수정
		@Override
		public int updateseatinfo(Seatinfo seatinfo) {
			int result = sqlSession.update(MAPPER + "updateseatinfo", seatinfo);
			
			return result;
		}
		

		
		@Override
		public List<Seatinfo> selectpaging(Map map) {
			List<Seatinfo> seatinfos = sqlSession.selectList(MAPPER + "selectpaging", map);
			return seatinfos;
		}
		
		@Override
		public int totalpage(String query) {
			int cnt = sqlSession.selectOne(MAPPER+"totalpage", query);
			return cnt;
		}

	

	

	
}
