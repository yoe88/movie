package com.spring.baseSetting.service.impl;

import com.spring.baseSetting.dao.StillDao;
import com.spring.baseSetting.dto.Still;
import com.spring.baseSetting.service.StillService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stillService")
public class StillServiceImpl implements StillService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StillDao stillDao;

    public StillServiceImpl(StillDao stillDao) {
        logger.info("StillServiceImpl Init...");
        this.stillDao = stillDao;
    }

    
	@Override
	public List<Still> getMovieStills(String mov_code) {
		List<Still> allstill = stillDao.selectMovieStills(mov_code);
		
		return allstill;
	}

	@Override
	public Still getOneStill(int still_no) {
		Still onestill = stillDao.selectOneStill(still_no);
		
		return onestill;
	}

	@Override
	public int removeGetStillNo(String still_img) {
		int result = stillDao.deleteSelectStillNo(still_img);
		
		return result;
	}

	@Override
	public int addStill(Still still) {
		int result = stillDao.insertStill(still);
		
		return result;
	}

	@Override
	public int removeStill(String mov_code) {
		int result = stillDao.deleteStill(mov_code);
		
		return result;
	}

	@Override
	public int removeOneStill(int still_no) {
		int still_num = stillDao.deleteOneStill(still_no);
		
		return still_num;
	}

	@Override
	public int getStillNo(String mov_code) {
		int still_num = stillDao.selectStillNo(mov_code);
		
		return still_num;
	}

    
}
