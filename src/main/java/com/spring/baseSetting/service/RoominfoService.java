package com.spring.baseSetting.service;

import com.spring.baseSetting.dto.Roominfo;
import com.spring.baseSetting.dto.Theainfo;

import java.util.List;


public interface RoominfoService {
	Roominfo getRoominfo(Roominfo roominfo);
	int addRoominfo(Roominfo roominfo);
	int delRoominfo(Roominfo roominfo);
	int upRoominfo(Roominfo roominfo);
	int updateroom(Roominfo roominfo); 
	List<Roominfo> getAllRoominfo();
	List<Theainfo> getlist(Theainfo theainfo);
	List<Theainfo> gettheaname();
	List<Roominfo> theapaging(String query, int page);
	int theatotal(String query);
	Theainfo selectthea(String thea_name);
}
