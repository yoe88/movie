package com.spring.baseSetting.service.impl;

import com.spring.baseSetting.dao.SeatinfoDao;
import com.spring.baseSetting.dto.Seatinfo;
import com.spring.baseSetting.service.SeatinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("seatinfoService")
public class SeatinfoServiceImpl implements SeatinfoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final SeatinfoDao seatinfoDao;

	public SeatinfoServiceImpl(SeatinfoDao seatinfoDao) {
		logger.info("RoominfoServiceImpl Init...");
		this.seatinfoDao = seatinfoDao;
	}
	//선택좌석불러오기(예매)
	@Override
	public List<Seatinfo> selectseats(String room_code) {
		List<Seatinfo> seatdate = seatinfoDao.selectseats(room_code);
		return seatdate;
	}
	
	
	// 선택한 좌석정보 불러오기
	@Override
	public Seatinfo getSeatinfo(Seatinfo seatinfo) {
		Seatinfo seatdata = seatinfoDao.selectseatinfo(seatinfo);
		return seatdata;
	}

	// 좌석정보 추가하기
	@Override
	public int addSeatinfo(Seatinfo seatinfo) {
		int result = seatinfoDao.insertseatinfo(seatinfo);

		return result;
	}

	// 모든 좌석정보 리시트 가져오기

	// 선택한 좌석정보 삭제
	@Override
	public int delSeatinfo(String room_code) {
		int result = seatinfoDao.deleteseatinfo(room_code);
		return result;
	}

	// 좌석정보 수정
	@Override
	public int upSeatinfo(Seatinfo seatinfo) {
		int result = seatinfoDao.updateseatinfo(seatinfo);
		return result;
	}

	@Override
	public List<Seatinfo> paging(String query, int page) {
		Map<String, Object> map = new HashMap<>();
		map.put("query", query);
		int start = (page - 1) * 10;
		int count = 10;
		map.put("start", start);
		map.put("count", count);

		List<Seatinfo> seatinfos = seatinfoDao.selectpaging(map);
		return seatinfos;
	}

	@Override
	public int total(String query) {

		return seatinfoDao.totalpage(query);
	}



}
