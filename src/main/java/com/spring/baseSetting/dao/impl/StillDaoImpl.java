package com.spring.baseSetting.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.spring.baseSetting.dao.StillDao;
import com.spring.baseSetting.dto.Still;

@Repository("stillDao")
public class StillDaoImpl implements StillDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//마이바티스 mapper xml에서 설정한 mapper 이름
    private final String MAPPER = "mapper.still.";

    private final SqlSession sqlSession;
    public StillDaoImpl(SqlSession sqlSession) {
        logger.info("StillDaoImpl Init...");
        this.sqlSession = sqlSession;
    }
    
    
	@Override
	public List<Still> selectMovieStills(String mov_code) {
		List<Still> allstill = sqlSession.selectList(MAPPER + "selectMovieStills", mov_code);
		
		return allstill;
	}

	@Override
	public Still selectOneStill(int still_no) {
		Still onestill = sqlSession.selectOne(MAPPER + "selectOneStill", still_no);
		
		return onestill;
	}

	@Override
	public int insertStill(Still still) {
		int result = sqlSession.insert(MAPPER + "insertStill", still);
		
		return result;
	}

	@Override
	public int deleteStill(String mov_code) {
		int result = sqlSession.delete(MAPPER + "deleteStill", mov_code);
		
		return result;
	}

	@Override
	public int deleteOneStill(int still_no) {
		int result = sqlSession.delete(MAPPER + "deleteOneStill", still_no);
		
		return result;
	}

	
	
	@Override 
	public int selectStillNo(String mov_code) { 
		int still_no = sqlSession.selectOne(MAPPER + "selectStillNo", mov_code);
	
		return still_no; 
	}


	@Override
	public int deleteSelectStillNo(String still_img) {
		int still_no = sqlSession.selectOne(MAPPER + "deleteSelectStillNo", still_img);
		
		return still_no; 
	}
	

}//StillDaoImpl class end
