package com.spring.baseSetting.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
public class Mov_show {
  private String show_code;//'상영코드',
  private String  mov_code;//'영화코드',
  private String  room_code;//'상영관코드',
  
  @DateTimeFormat(pattern="yy-mm-dd")
  private Date  show_date;// '상영 날짜',
  private String  show_time;//'상영 시작 시간'
}
