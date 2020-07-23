package com.spring.baseSetting.dto;

import lombok.Data;

@Data
public class Roominfo {
private String room_code;    //상영관코드
private String thea_code;    //영화관코드
private String room_name;    //상영관이름
private int room_maximum;    //수용인원
private String room_type;    //상영관정보
private String thea_name;    //영화관이름
}
