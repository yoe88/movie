package com.spring.baseSetting.service;

import com.spring.baseSetting.dto.Seatinfo;

import java.util.List;


public interface SeatinfoService {
	Seatinfo getSeatinfo(Seatinfo seatinfo);
	int addSeatinfo(Seatinfo seatinfo);
	int delSeatinfo(String room_code);
	int upSeatinfo(Seatinfo seatinfo);
	List<Seatinfo> paging(String query, int page);
	int total(String query);
	List<Seatinfo> selectseats(String room_code);

}
