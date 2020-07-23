package com.spring.baseSetting.service.impl;

import com.spring.baseSetting.dto.Mov_show;
import com.spring.baseSetting.service.Mov_showService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("Mov_showService")
public class Mov_showServiceImpl implements Mov_showService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final com.spring.baseSetting.dao.Mov_showDao Mov_showDao;
  
  
  public Mov_showServiceImpl(com.spring.baseSetting.dao.Mov_showDao Mov_showDao) {
    logger.info("MemberServiceImpl Init...");
    this.Mov_showDao = Mov_showDao;
  }

  @Override
  public List<Mov_show> getMovByThea(Map m) {
    List<Mov_show> mov_shows = Mov_showDao.selectMovByThea(m);
    return mov_shows;
  }

  @Override
  public List<Mov_show> getMov_title(Map m) {
    List<Mov_show> mov_shows = Mov_showDao.selectMov_title(m);
    return mov_shows;
  }

  @Override
  public List<Mov_show> getmovAll() {
    List<Mov_show> mov_shows = Mov_showDao.selectmovAll();
    return mov_shows;
  }

  @Override
  public List<Mov_show> getmovAllMaster(Map m) {
    List<Mov_show> mov_shows = Mov_showDao.selectmovAllMaster(m);
    return mov_shows;
  }

  @Override
  public Mov_show getMov_show(String show_code) {
    Mov_show mov_show = Mov_showDao.readMov_show(show_code);
    return mov_show;
  }

  @Override
  public int addMov_show(Mov_show mov_show) {
    int result = Mov_showDao.insertMov_show(mov_show);
    return result;
  }

  @Override
  public int modifyMov_show(Mov_show mov_show) {
    int result = Mov_showDao.updateMov_show(mov_show);
    return result;
  }

  @Override
  public int removeMov_show(Mov_show mov_show) {
    int result = Mov_showDao.deleteMov_show(mov_show);
    return result;
  }
   
}
