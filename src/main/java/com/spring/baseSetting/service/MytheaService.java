package com.spring.baseSetting.service;

import com.spring.baseSetting.dto.Mythea;

import java.util.List;

public interface MytheaService {
	
	//영화관 페이지, 마이페이지, 예매페이지에 즐찾 영화관 select(영화관 이름만)
	List<Mythea> getMythea(String mem_id);
	
	//영화관 페이지, 마이페이지, 예매페이지에 
	//즐찾 영화관 아닌거 select
	List<Mythea> getnotMythea(String mem_id);
	
	//즐찾 영화관 insert
	int addMythea(Mythea mythea);
	
	//즐찾 영화관 delete
	int removeMythea(Mythea mythea);
	
	//영화관 다 가져오기 ㅇㅇ..
	List<Mythea> getAllthea();
	
}//MytheaService interface end
