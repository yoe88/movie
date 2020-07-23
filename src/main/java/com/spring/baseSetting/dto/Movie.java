package com.spring.baseSetting.dto;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Movie {	//DTO!!!!!!!!!
	private String mov_code; 				//영화코드
	private String mov_title; 				//영화제목
	private String mov_content; 			//영화소개
	private String mov_director; 			//감독
	private String mov_genre; 				//장르
    private int mov_rate; 					//영화등급
    private int mov_time; 					//러닝타임
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date mov_opening; 				//개봉일
    private Date mov_regdate; 				//영화등록일
    private String mov_img; 				//영화포스터
    private String mov_mainactor; 			//주연배우
    private String mov_supportactor; 		//조연배우

    private String mov_end;					//영화 상영 종료 여부
    
    private float mov_per;	//영화 예매 개수
    

//------------------------------------------
  	//1) 스프링 파일 객체 멤버 변수 선언
  	//이건 실제 파일 기억
  	//<input type='file' name='posterMF'>
  	private MultipartFile posterMF;
  	
  	//2) servlet-context.xml에 스프링빈 등록
  	
//------------------------------------------
	
}//Movie class end
