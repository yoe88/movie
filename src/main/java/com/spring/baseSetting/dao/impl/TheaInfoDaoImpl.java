package com.spring.baseSetting.dao.impl;

import com.spring.baseSetting.dao.TheaInfoDao;
import com.spring.baseSetting.dto.Theainfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class TheaInfoDaoImpl implements TheaInfoDao {

    //마이바티스 mapper xml에서 설정한 mapper 이름
    private final String MAPPER = "mapper.theainfo.";

    private final SqlSession sqlSession;
    public TheaInfoDaoImpl(SqlSession sqlSession) {
        log.info("TheaInfoDaoImpl Init...");
        this.sqlSession = sqlSession;
    }
	@Override
	public String theacodeFind(String thea_name) {

		
		return sqlSession.selectOne(MAPPER+"theacodeFind", thea_name);
	}

	@Override
    public List<Theainfo> selectAllThea(Map<String, Object> map) {
        return sqlSession.selectList(MAPPER + "selectAllThea", map);
    }

    @Override
    public int selectAllTheaCount(Map<String, Object> map) {
        return sqlSession.selectOne(MAPPER + "selectAllTheaCount", map);
    }

    @Override
    public String selectLastTheaCode(String regionName) {
        return sqlSession.selectOne(MAPPER + "selectLastTheaCode" ,regionName);
    }

    @Override
    public int insertTheaInfo(Theainfo theainfo) {
        return sqlSession.insert(MAPPER + "insertTheaInfo", theainfo);
    }

    @Override
    public Theainfo selectTheaInfoByTheaCode(String thea_code) {
        return sqlSession.selectOne(MAPPER + "selectTheaInfoByTheaCode", thea_code);
    }

    @Override
    public int updateTheaInfo(Theainfo theainfo) {
        return sqlSession.update(MAPPER + "updateTheaInfo", theainfo);
    }
    

    @Override
	public List<Theainfo> selectThea() {
    	 return sqlSession.selectList(MAPPER + "selectThea");
	}

	@Override
    public String selectTheaInfoImgByTheaCode(String thea_code) {
        return sqlSession.selectOne(MAPPER + "selectTheaInfoImgByTheaCode", thea_code);
    }

    @Override
    public int deleteTheaInfo(String thea_code) {
    	
    	
    	
        return sqlSession.delete(MAPPER + "deleteTheaInfo", thea_code);
    }
}
