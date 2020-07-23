package com.spring.baseSetting.dao.impl;

import com.spring.baseSetting.dao.ResDao;
import com.spring.baseSetting.dto.Res;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("resDao")
public class resDaolmpl implements ResDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// 마이바티스 mapper xml에서 설정한 mapper 이름
	private final String MAPPER = "mapper.res.";

	private final SqlSession sqlSession;

	public resDaolmpl(SqlSession sqlSession) {
		logger.info("resDaoImpl Init...");
		this.sqlSession = sqlSession;
	}


	@Override
	public int selectres(String res_code) {
		int cnt = sqlSession.selectOne(MAPPER + "selectres", res_code);
		return cnt;
	}
	
	public Res findshowcode(Map map) {
		Res result = sqlSession.selectOne(MAPPER + "findshowcode", map);
		return result;
	}
	
	public int insertres(Res res) {
		int cnt = sqlSession.insert(MAPPER + "insertres", res);
		return cnt;
	}
	
	@Override
	public int insertseat(Map map) {
		logger.info(map.toString());
		int cnt = sqlSession.insert(MAPPER+ "insertseat", map);
		return cnt;
	}

	@Override
	public int deleteResByResCode(String resCode) {
		return sqlSession.delete(MAPPER + "deleteResByResCode", resCode);
	}

	@Override
	public Map<String, String> selectNames(Map<String, String> map) {
		return sqlSession.selectOne(MAPPER + "selectNames", map);
	}

	@Override
	public String selectLastResCode(String resCode) {
		return sqlSession.selectOne(MAPPER + "selectLastResCode", resCode);
	}

	@Override
	public void insertRes(Res res) {
		sqlSession.insert(MAPPER + "insertRes", res);
	}
	
	
	@Override
	public String selectposter(String mov_title) {
		String mov_img = sqlSession.selectOne(MAPPER + "selectposter", mov_title);
		return mov_img;
	}
	
	
	
}//class end
