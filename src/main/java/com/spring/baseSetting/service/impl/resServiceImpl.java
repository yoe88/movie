package com.spring.baseSetting.service.impl;


import com.spring.baseSetting.dao.MemberDao;
import com.spring.baseSetting.dao.SeatDao;
import com.spring.baseSetting.dao.ResDao;
import com.spring.baseSetting.dto.Res;
import com.spring.baseSetting.dto.Seat;
import com.spring.baseSetting.service.ResService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service("resService")
public class resServiceImpl implements ResService {

    private final ResDao resDao;
    private final SeatDao seatDao;
    private final MemberDao memberDao;

    public resServiceImpl(ResDao resDao, SeatDao seatDao, MemberDao memberDao) {
        log.info("resServiceImpl Init...");
        this.resDao = resDao;
        this.seatDao = seatDao;
        this.memberDao = memberDao;
    }

   
    @Override
    public int selectres(String res_code) {
    	int cnt = resDao.selectres(res_code);
    	return cnt;
    }
    
    
    @Override
    public Res findshowcode(Map map) {
    	Res result = resDao.findshowcode(map);
    	return result;
    }
    
    @Override
    public int insertres(Res res) {
    	int cnt = resDao.insertres(res);
    	return cnt;
    }
    
    @Override
    public int insertseat(Map map) {
    	int cnt = resDao.insertseat(map);
    	return cnt;
    }

    @Transactional
    @Override
    public int deleteRes(String resCode, String id, int point) {
        int result = 0;
        result += resDao.deleteResByResCode(resCode);
        if(point != 0){
            Map<String, Object> map = new HashMap<>();
            map.put("id",id);
            map.put("point",point);
            memberDao.updatePointById(map);
        }
        return result;
    }

    @Override
    public Map<String, String> selectNames(String showCode) {
        Map<String,String>  map = new HashMap<>();
        map.put("showCode", showCode);
        map.put("theaCode", showCode.substring(0,4));

        return resDao.selectNames(map);
    }

    @Override
    public String selectLastResCode(String resCode) {
        return resDao.selectLastResCode(resCode);
    }


    @Transactional
    @Override
    public boolean insertRes(Map<String, Object> map) {

        try{
            Res res = (Res) map.get("res");
            //예매정보 추가
            resDao.insertRes(res);

            //선택한 좌석 추가
            List<Seat> seatList = (List<Seat>) map.get("seatList");
            for(Seat seat : seatList){
                seatDao.insertSeat(seat);
            }

            int point = res.getRes_point() * -1;
            if(point != 0){ //사용한 포인트가 0이면 할 필요 없음
                String id = res.getMem_id();
                Map<String,Object> member = new HashMap<>();
                member.put("id",id);
                member.put("point",point);
                memberDao.updatePointById(member);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    @Override
	public String selectposter(String mov_title) {
		String mov_img = resDao.selectposter(mov_title);
		return mov_img;
	}
    
}
