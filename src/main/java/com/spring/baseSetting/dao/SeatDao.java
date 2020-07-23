package com.spring.baseSetting.dao;

import java.util.List;

import com.spring.baseSetting.dto.Seat;

public interface SeatDao {

	List<String> selectSeat(String show_code);

    void insertSeat(Seat seat);
}
