package com.spring.baseSetting.controller;

import com.spring.baseSetting.dto.Member;
import com.spring.baseSetting.dto.Mythea;
import com.spring.baseSetting.service.MemberService;
import com.spring.baseSetting.service.MytheaService;
import com.spring.baseSetting.service.ResService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class MypageCont {
	
	@Autowired
	MytheaService mytheaService;
	
	@Autowired
	MemberService memService;

	@Autowired
    ResService resService;

	
	public MypageCont() {
		System.out.println("--- MypageCont() 객체 생성됨");
	}

	
	
	@RequestMapping(value = "/member/mypage.do", method = RequestMethod.GET)
	public ModelAndView mypage(Member memDto, Mythea mytheaDto, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/mypage");
		
		String mem_id = (String) session.getAttribute("s_id");
		
		//System.out.println(memService.getOneMem(mem_id));
		
		String mem_lev = "";

		mem_lev = memService.getOneMem(mem_id).getMem_lev();
		
		if( mem_lev == "V" ) {
			mem_lev = "VIP";
		}else if( mem_lev == "G" ) {
			mem_lev = "골드";
		}else if( mem_lev == "S" ) {
			mem_lev = "실버";
		}else if( mem_lev == "B" ) {
			mem_lev = "브론즈";
		}
		
		
		mav.addObject("memdto", memService.getOneMem(mem_id));
		mav.addObject("mem_lev", mem_lev);
		mav.addObject("mythealist", mytheaService.getMythea(mem_id));
		
		return mav;
	}

	/**
	 * 나의 예매 정보 페이지
	 * @param request  세션 얻어서 아이디 정보 얻어야 함
	 */
	@GetMapping(value = "/member/res")
	public ModelAndView res(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("member/myRes");	//페이지 정보
		HttpSession session = request.getSession();						//세션 얻기
		String id = (String) session.getAttribute("s_id");			//세션에서 아이디 얻기

		List<Map<String, Object>> list =  memService.myRes(id); //예매 정보 리스트
		List<Boolean> isNow = new ArrayList<>();				//영화 관람시간과 현재시간을 비교할것
																//현재시간이 관람시간보다 전이면 예매취소를 할수 있고 아니면 못하게
		LocalDateTime dateTime = LocalDateTime.now();			//현재시간 저장
		for (Map<String, Object> map : list) {					//리스트에서 map 꺼내기
			LocalDateTime db = LocalDateTime.parse((String) map.get("showTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));  //map에서 관람시간 꺼내기
			//현재시간 관람시간 비교
			isNow.add(dateTime.isBefore(db));
		}
		mav.addObject("list", list);
		mav.addObject("isNow",isNow);
		
		return mav;
	}

	/** 예매 삭제 포인트 복구
	 * @param map  클라이언트에서 전달된 정보 - 예매 코드(res)
	 * @return  성공하면 1 실패 0
	 */
	@DeleteMapping(value = "/member/res")
	public ResponseEntity<Integer> res(@RequestBody Map<String,String> map, HttpServletRequest request){
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("s_id");

		String resCode = map.get("res");
		int point = Integer.parseInt(map.get("point"));
		int result = resService.deleteRes(resCode, id, point);
		return ResponseEntity.ok().body(result);
	}
}//MypageCont class end


