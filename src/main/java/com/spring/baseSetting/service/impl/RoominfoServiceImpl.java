package com.spring.baseSetting.service.impl;


import com.spring.baseSetting.dao.RoominfoDao;
import com.spring.baseSetting.dto.Roominfo;
import com.spring.baseSetting.dto.Theainfo;
import com.spring.baseSetting.service.RoominfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("roominfoService")
public class RoominfoServiceImpl implements RoominfoService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RoominfoDao roominfoDao;

    public RoominfoServiceImpl(RoominfoDao roominfoDao) {
        logger.info("RoominfoServiceImpl Init...");
        this.roominfoDao = roominfoDao;
    }


    //선택한 상영관 정보 불러오기
    @Override
    public Roominfo getRoominfo(Roominfo roominfo) {
    	Roominfo roomdata = roominfoDao.selectRoominfo(roominfo);
        return roomdata;
    }
    //모든 상영관 리스트 불러오기
    @Override
    public List<Roominfo> getAllRoominfo() {
    	 List<Roominfo> roominfos = roominfoDao.selectAllroominfo();
         return roominfos;
    }
    //상영관 추가하기
	@Override
	public int addRoominfo(Roominfo roominfo) {
		 int result = roominfoDao.insertRoominfo(roominfo);
		return result;
	}
	//상영관 삭제하기
	@Override
	public int delRoominfo(Roominfo roominfo) {
		int result = roominfoDao.deleteRoominfo(roominfo);
		return result;
	}
	
	//상영관 수정하기
	@Override
	public int upRoominfo(Roominfo roominfo) {
		int result = roominfoDao.updateRoominfo(roominfo);
		return result;
	}
	
	@Override
	public int updateroom(Roominfo roominfo) {
		int result = roominfoDao.updateroom(roominfo);
		return result;
	}
	
	
	//영화관별 상영관리스트
	@Override
	public List<Theainfo> getlist(Theainfo theainfo) {
		List<Theainfo> theainfos = roominfoDao.selecttheaRoominfo(theainfo);
		return theainfos;
	}
	
	//모든 영화관코드,영화관이름 불러오기
	@Override
	public List<Theainfo> gettheaname() {
		// TODO Auto-generated method stub
		List<Theainfo> theainfos = roominfoDao.selectTheaname();
		return theainfos;
	}
	
	@Override
	public List<Roominfo> theapaging(String query, int page) {
		Map<String,Object> map = new HashMap<>();
		map.put("query", query);
		int start = (page-1) * 10;
		int count = 10;
		map.put("start",start);
		map.put("count", count);
			
		List<Roominfo> roominfos = roominfoDao.theainfoselectpaging(map);
		return roominfos;
	}
	
	@Override
	public int theatotal(String query) {
		return roominfoDao.theatotalpage(query);
		
	}
	
	@Override
	public Theainfo selectthea(String thea_name) {
		Theainfo theainfo = roominfoDao.selectthea(thea_name);
		return theainfo;
	}
	
}
