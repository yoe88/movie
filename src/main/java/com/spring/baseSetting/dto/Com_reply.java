package com.spring.baseSetting.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Com_reply {
	private int com_no;
	private int rno;
	private String content;
	private String mem_id;
	private Date regdate;
	
} 

