package com.spring.baseSetting.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Data
public class Com_notice {
	private int com_no;
	private String com_gcode;
	private String mem_id;
	private String com_type;
	private String com_title;
	private String com_cont;
	private String com_filename;
	private int com_visitnum;
	private int com_grpno;
	private int com_repno;
	private int com_indent;
	private Date com_readgt;
	private String com_ip;
	
	//1)�뒪�봽留� �뙆�씪 媛앹껜 硫ㅻ쾭 蹂��닔 �꽑�뼵
	//<input type='file' name='posterMF'>
	//private MultipartFile posterMF;
	//<input type='file' name='filenameMF'>
	private MultipartFile filenameMF;
	//2)getter�� setter�옉�꽦
	//public MultipartFile getPosterMF() {
	 // return posterMF;
	//}
	//public void setPosterMF(MultipartFile posterMF) {
	//  this.posterMF = posterMF;
	//}
	public MultipartFile getFilenameMF() {
	  return filenameMF;
	}
	public void setFilenameMF(MultipartFile filenameMF) {
	  this.filenameMF = filenameMF;
	}
	
} 

