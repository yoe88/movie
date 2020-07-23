package com.spring.baseSetting.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.baseSetting.dao.SeatDao;
import com.spring.baseSetting.dto.Seat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SeatDaoImpl implements SeatDao{
	
	private final String MAPPER = "mapper.seat.";

	private final SqlSession sqlSession;

	public SeatDaoImpl(SqlSession sqlSession) {
		log.info("SeatDaoImpl Init...");
		this.sqlSession = sqlSession;
	}

	@Override
	public List<String> selectSeat(String show_code) {
		List<String> list = sqlSession.selectList(MAPPER + "selectSeat", show_code);
		return list;
	}

	@Override
	public void insertSeat(Seat seat) {
		sqlSession.insert(MAPPER + "insertSeat", seat);
	}
}
