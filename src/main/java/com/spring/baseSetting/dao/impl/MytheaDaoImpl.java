package com.spring.baseSetting.dao.impl;

import com.spring.baseSetting.dao.MytheaDao;
import com.spring.baseSetting.dto.Mythea;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mytheaDao")
public class MytheaDaoImpl implements MytheaDao {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//마이바티스 mapper xml에서 설정한 mapper 이름
    private final String MAPPER = "mapper.mythea.";

    private final SqlSession sqlSession;
    public MytheaDaoImpl(SqlSession sqlSession) {
        logger.info("MytheaDaoImpl Init...");
        this.sqlSession = sqlSession;
    }
    
    
    
	@Override
	public List<Mythea> selectMythea(String mem_id) {
		List<Mythea> mythea = sqlSession.selectList(MAPPER + "selectMythea", mem_id);
		
		return mythea;
	}
	
	@Override
	public int insertMythea(Mythea mythea) {
		int result = sqlSession.insert(MAPPER + "insertMythea", mythea);
		
		return result;
	}
	
	@Override
	public int deleteMythea(Mythea mythea) {
		int result = sqlSession.delete(MAPPER + "deleteMythea", mythea);
		
		return result;
	}



	@Override
	public List<Mythea> selectAllthea() {
		List<Mythea> list = sqlSession.selectList(MAPPER + "selectAllthea");
		
		return list;
	}



	@Override
	public List<Mythea> selectnotMythea(String mem_id) {
		List<Mythea> list = sqlSession.selectList(MAPPER + "selectnotMythea", mem_id);
		
		return list;
	}




	
}//MytheaDaoImpl class end
