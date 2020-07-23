package com.spring.baseSetting.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.spring.baseSetting.dao.Mov_gradeDao;
import com.spring.baseSetting.dto.Mov_grade;
import com.spring.baseSetting.service.Mov_gradeService;

@Service("Mov_gradeService")
public class Mov_gradeServiceImpl implements Mov_gradeService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final Mov_gradeDao mov_gradeDao;

  public Mov_gradeServiceImpl(Mov_gradeDao mov_gradeDao) {
    logger.info("MemberServiceImpl Init...");
    this.mov_gradeDao = mov_gradeDao;
  }
  

  @Override
  public float getAvgGrade(String mov_code) {
    float grade = mov_gradeDao.selectAvgGrade(mov_code);
    return grade;
  }

  @Override
  public List<Mov_grade> getMovAllgrade(String mov_code) {
    List<Mov_grade> grades = mov_gradeDao.selectMovAllgrade(mov_code);
    return grades;
  }

  @Override
  public List<Mov_grade> getAllgrade() {
    List<Mov_grade> grades = mov_gradeDao.selectAllgrade();
    return grades;
  }


  @Override
  public int addMov_grade(Mov_grade mov_grade) {
    int result = mov_gradeDao.insertMov_grade(mov_grade);
    return result;
  }

  @Override
  public int modifyMov_grade(Mov_grade mov_grade) {
    int result = mov_gradeDao.updateMov_grade(mov_grade);
    return result;
  }

  @Override
  public int removeMov_grade(Mov_grade mov_grade) {
    int result = mov_gradeDao.deleteMov_grade(mov_grade);
    return result;
  }
}
