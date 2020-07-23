package com.spring.baseSetting.service;

import com.spring.baseSetting.dto.Mov_show;

import java.util.List;
import java.util.Map;

public interface Mov_showService {
  List<Mov_show> getMovByThea(Map m);
  
  List<Mov_show> getMov_title(Map m);

  List<Mov_show> getmovAll();
  
  List<Mov_show> getmovAllMaster(Map m);
  
  Mov_show getMov_show(String show_code);
  
  int addMov_show(Mov_show mov_show);
  
  int modifyMov_show(Mov_show mov_show);
  
  int removeMov_show(Mov_show mov_show);
}
