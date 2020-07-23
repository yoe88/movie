package com.spring.baseSetting.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Still {	//DTO!!!!!!!!
	private int still_no;		//번호
    private String mov_code;	//영화코드(fk)
    private String still_img;	//스틸컷 이미지 명
//------------------------------------------
  	//1) 스프링 파일 객체 멤버 변수 선언
  	//이건 실제 파일 기억
    
  	//<input type='file' name='stillMF'>
  	private MultipartFile stillMF;
  	
  	//2) servlet-context.xml에 스프링빈 등록
  	
//------------------------------------------
}//Still class end
