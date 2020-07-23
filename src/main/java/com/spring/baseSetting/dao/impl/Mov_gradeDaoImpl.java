package com.spring.baseSetting.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.spring.baseSetting.dao.Mov_gradeDao;
import com.spring.baseSetting.dto.Mov_grade;

@Repository("Mov_gradeDao")
public class Mov_gradeDaoImpl implements Mov_gradeDao {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  // 마이바티스 mapper xml에서 설정한 mapper 이름
  private final String MAPPER = "mapper.mov_grade.";

  private final SqlSession sqlSession;

  public Mov_gradeDaoImpl(SqlSession sqlSession) {
    logger.info("Mov_gradeDaoImpl Init...");
    this.sqlSession = sqlSession;
  }

  @Override
  public float selectAvgGrade(String mov_code) {
	float grade = sqlSession.selectOne(MAPPER + "selectAvgGrade", mov_code);
    return grade;
  }

  @Override
  public List<Mov_grade> selectMovAllgrade(String mov_code) {
    List<Mov_grade> grades = sqlSession.selectList(MAPPER + "selectMovAllgrade", mov_code);
    return grades;
  }

  @Override
  public List<Mov_grade> selectAllgrade() {
    List<Mov_grade> grades = sqlSession.selectList(MAPPER + "selectAllgrade");
    return grades;
  }

  @Override
  public int insertMov_grade(Mov_grade mov_grade) {
    int result = sqlSession.insert(MAPPER + "insertMov_grade", mov_grade);
    return result;
  }

  @Override
  public int updateMov_grade(Mov_grade mov_grade) {
    int result = sqlSession.insert(MAPPER + "updateMov_grade", mov_grade);
    return result;
  }

  @Override
  public int deleteMov_grade(Mov_grade mov_grade) {
    int result = sqlSession.insert(MAPPER + "deleteMov_grade", mov_grade);
    return result;
  }

  
}
