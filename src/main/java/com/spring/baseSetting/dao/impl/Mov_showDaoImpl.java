package com.spring.baseSetting.dao.impl;

import com.spring.baseSetting.dao.Mov_showDao;
import com.spring.baseSetting.dto.Mov_show;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("Mov_showDao")
public class Mov_showDaoImpl implements Mov_showDao {
  
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  //마이바티스 mapper xml에서 설정한 mapper 이름
  private final String MAPPER = "mapper.mov_show.";

  private final SqlSession sqlSession;
  public Mov_showDaoImpl(SqlSession sqlSession) {
      logger.info("Mov_showDaoImpl Init...");
      this.sqlSession = sqlSession;
  }
  
  //영화관선택-그 영화관에서 볼 수 있는 영화 보여주기(중복삭제)
  @Override
  public List<Mov_show> selectMovByThea(Map m) {
    List<Mov_show> mov_shows = sqlSession.selectList(MAPPER + "selectMovByThea", m);
    return mov_shows;
  }


  @Override
  public List<Mov_show> selectmovAll() {
    List<Mov_show> mov_shows = sqlSession.selectList(MAPPER + "selectmovAll");
    return mov_shows;
  }
  
  @Override
  public List<Mov_show> selectMov_title(Map m) {
    List<Mov_show> mov_shows = sqlSession.selectList(MAPPER + "selectMov_title", m);
    return mov_shows;
  }

  @Override
  public List<Mov_show> selectmovAllMaster(Map m) {
    List<Mov_show> mov_shows = sqlSession.selectList(MAPPER + "selectmovAllMaster" , m);
    return mov_shows;
  }
  
  @Override
  public Mov_show readMov_show(String show_code) {
    Mov_show mov_show = sqlSession.selectOne(MAPPER + "readMov_show" , show_code);
    return mov_show;
  }

  @Override
  public int insertMov_show(Mov_show mov_show) {
    int result = sqlSession.insert(MAPPER + "insertMov_show", mov_show);
    return result;
  }

  @Override
  public int updateMov_show(Mov_show mov_show) {
    int result = sqlSession.insert(MAPPER + "updateMov_show", mov_show);
    return result;
  }

  @Override
  public int deleteMov_show(Mov_show mov_show) {
    int result = sqlSession.insert(MAPPER + "deleteMov_show", mov_show);
    return result;
  }
  
}
