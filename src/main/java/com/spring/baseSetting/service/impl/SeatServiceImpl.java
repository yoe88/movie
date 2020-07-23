package com.spring.baseSetting.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.baseSetting.dao.SeatDao;
import com.spring.baseSetting.dto.Seat;

import com.spring.baseSetting.service.SeatService;
import com.spring.baseSetting.service.SeatinfoService;

@Service
public class SeatServiceImpl implements SeatService{
	
	@Autowired
	SeatDao seatDao;

	@Override
	public List<String> getSeat(String show_code) {
		return seatDao.selectSeat(show_code);
	}
	
	

}
