package com.spring.baseSetting.dao;

import com.spring.baseSetting.dto.Mythea;

import java.util.List;

public interface MytheaDao {
	
	//영화관 페이지, 마이페이지, 예매페이지에 즐찾 영화관 select
	List<Mythea> selectMythea(String mem_id);
	
	//영화관 페이지, 마이페이지, 예매페이지에 
	//즐찾 영화관 아닌거 select
	List<Mythea> selectnotMythea(String mem_id);
	
	//즐찾 영화관 insert
	int insertMythea(Mythea mythea);
	
	//즐찾 영화관 delete
	int deleteMythea(Mythea mythea);
	
	
	//영화관 다 가져오기
	List<Mythea> selectAllthea();
	
}//MytheaDao class end
