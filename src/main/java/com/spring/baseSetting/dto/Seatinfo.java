package com.spring.baseSetting.dto;

import lombok.Data;


@Data
public class Seatinfo {
private String seat_code;    //좌석일련번호
private String room_code;    //상영관코드
private String seat_column;  //좌석행
private int seat_row;        //좌석열
}
