package com.spring.baseSetting.dao;

import com.spring.baseSetting.dto.Seatinfo;

import java.util.List;
import java.util.Map;

public interface SeatinfoDao {
	
	
	Seatinfo selectseatinfo(Seatinfo seatinfo);
	int insertseatinfo(Seatinfo seatinfo);
	int deleteseatinfo(String room_code); 
	int updateseatinfo(Seatinfo seatinfo); 
	List<Seatinfo> selectpaging(Map<String, Object> map); 
	int totalpage(String query);
	List<Seatinfo> selectseats(String room_code);

}
