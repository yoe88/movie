package com.spring.baseSetting.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.baseSetting.dto.Seat;
import com.spring.baseSetting.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.baseSetting.dto.Seatinfo;
import com.spring.baseSetting.dto.Member;
import com.spring.baseSetting.dto.Res;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ResCont {
	public ResCont() {
		System.out.println("---ResCont()객체생성됨");
	}

	@Autowired
	SeatinfoService seatinfoService;

	@Autowired
	ResService resService;

	@Autowired
	SeatService seatService;

	@Autowired
	MemberService memberService;

	/*
	 * //http://localhost:9090/movieForest/res/sheatChoose.do //(예매를 눌렀을 때)
	 * 
	 * @RequestMapping(value = "res/seatChoose.do") public ModelAndView
	 * res_mov(HttpServletRequest req, String thea_name ,@RequestParam("show_code")
	 * String show_code) { ModelAndView mav = new ModelAndView();
	 * mav.setViewName("res/seatChoose");
	 * 
	 * String room_name = req.getParameter("room_name"); int roomnum_ =
	 * Integer.parseInt(room_name.substring(0, room_name.length() - 1)); String
	 * roomnum = roomnum_ < 10 ? "0" + roomnum_ : "" + roomnum_; log.info("룸번호 {}",
	 * roomnum); log.info("영화관이름{}", thea_name);
	 * 
	 * 
	 * String thea_code = theaService.theacodeFind(thea_name);
	 * 
	 * 
	 * List<String> seat_code = seatService.getSeat(show_code);
	 * System.out.println("seatlist  : " + seat_code);
	 * 
	 * //이미 예약된 좌석정보 SET에 넣기 Set<String> set = new HashSet<>(); for (int i = 0; i <
	 * seat_code.size(); i++) { set.add(seat_code.get(i).substring(6, 7) +
	 * Integer.parseInt(seat_code.get(i).substring(7))); } log.info(set.toString());
	 * 
	 * //이미 예약된 좌석정보 true, false List<Boolean> isExists = new ArrayList<>();
	 * 
	 * //String room_code = thea_code + Integer.toString(roomnum); String room_code
	 * = thea_code + roomnum; log.info("roomCode{}", room_code); List<Seatinfo> info
	 * = seatinfoService.selectseats(room_code); log.info(info.toString());
	 * 
	 * for (int i = 0; i < info.size(); i++) { String tmp =
	 * info.get(i).getSeat_column() + info.get(i).getSeat_row(); if
	 * (set.contains(tmp)) { isExists.add(true); } else { isExists.add(false); } }
	 * log.info(isExists.toString());
	 * 
	 * mav.addObject("isExists", isExists); mav.addObject("show_time",
	 * req.getParameter("show_time")); mav.addObject("date",
	 * req.getParameter("date")); //log.info("date",req.getParameter("date"));
	 * mav.addObject("mov_title", req.getParameter("mov_title"));
	 * mav.addObject("room_code", room_code); mav.addObject("thea_name", thea_name);
	 * mav.addObject("room_name", room_name); mav.addObject("thea_code", thea_code);
	 * 
	 * mav.addObject("seat_list", seatinfoService.selectseats(room_code));
	 * 
	 * return mav; }
	 */

	/************************ 최대한 똑같이 다시 만들기.. ************************/

	/**
	 * 예매 누르면 좌석 선택 하는 페이지 http://localhost:9090/movieForest/res/sheatChoose.do
	 * 
	 * @param showCode 상영코드 ex) SU010107172250
	 */
	@GetMapping(value = "res/seatChoose.do")
	public ModelAndView res_mov(@RequestParam("show_code") String showCode) {
		ModelAndView mav = new ModelAndView("res/seatChoose");

		// 상영관 코드 SU0101
		String roomCode = showCode.substring(0, 6);
		// 상영관 이름(번호) 1관, 10관
		String roomName = Integer.parseInt(roomCode.substring(4, 6)) + "관";

		// 예약 된 좌석 리스트 가져오기
		List<String> seatCode = seatService.getSeat(showCode);
		// 예약된 좌석 리스트 SET에 넣기
		Set<String> set = new HashSet<>();
		for (String s : seatCode) {
			set.add(s.substring(6, 7) + Integer.parseInt(s.substring(7)));
		}
		// 이미 예약된 좌석 체크 리스트
		List<Boolean> isExists = new ArrayList<>();

		// 상영관에 해당하는 전체 좌석 리스트 가져오기
		List<Seatinfo> seatInfo = seatinfoService.selectseats(roomCode);

		// 전체 좌석 리스트 돌면서 예약 된 좌석 true 설정
		for (Seatinfo seatinfo : seatInfo) {
			String tmp = seatinfo.getSeat_column() + seatinfo.getSeat_row();
			isExists.add(set.contains(tmp));
		}

		/** 상영 코드 SU010107172250 **/
		// 상영시간 22:50 만들기
		String showTime = showCode.substring(10, 12) + ":" + showCode.substring(12);
		// 상영날짜 2020-07-17 만들기
		LocalDate now = LocalDate.now();
		String year = "" + now.getYear();
		String showDateTime = year + "-" + showCode.substring(6, 8) + "-" + showCode.substring(8, 10);

		// 상영코드로 영화제목이랑 영화관이름 불러오기
		Map<String, String> map = resService.selectNames(showCode);

		mav.addObject("isExists", isExists);
		mav.addObject("show_time", showTime);
		mav.addObject("date", showDateTime);
		mav.addObject("mov_title", map.get("movTitle"));
		mav.addObject("room_code", roomCode);
		mav.addObject("thea_name", map.get("theaName"));
		mav.addObject("room_name", roomName);
		mav.addObject("seat_list", seatInfo);
		return mav;
	}

	/*@RequestMapping(path = "res/resseat.do")
	public ModelAndView resseat(HttpSession session, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("res/seat");

		int adult = Integer.parseInt(req.getParameter("adult")); // 성인인원
		int youth = Integer.parseInt(req.getParameter("youth")); // 청소년인원
		// 인원수 res_count
		int res_count = adult + youth;

		// 예매날짜 res_date
		SimpleDateFormat format1 = new SimpleDateFormat("MMdd");
		Date nowdate = new Date();
		String today = format1.format(nowdate);

		// res_point (insert문에서 0으로 지정)

		// show_code 상영코드
		Map<String, Object> map = new HashMap<>();
		map.put("room_code", req.getParameter("room_code"));
		map.put("show_date", req.getParameter("date"));
		map.put("show_time", req.getParameter("show_time"));
		System.out.println(":::" + resService.findshowcode(map).getShow_code());

		// res_sum 결제금액
		int res_sum = adult * 10000 + youth * 8000;

		// 시퀀스
		int seq = 1;
		String num = Integer.toString(seq);
		// 예매코드
		String res_code = req.getParameter("room_code") + "-" + today + "-" + num;
		while (resService.selectres(res_code) != 0) {
			num = Integer.toString(seq);
			res_code = req.getParameter("room_code") + "-" + today + "-" + num;
			seq = seq + 1;
		}

		// insert문으로 예매정보넣기

		// 예매정보 넣기
		res res = new res();
		res.setMem_id(req.getParameter("mem_id"));
		res.setRes_code(res_code);
		res.setRes_count(res_count);
		res.setRes_sum(res_sum);
		*//*****************//*
		String show_code = resService.findshowcode(map).getShow_code();
		*//*****************//*
		res.setShow_code(show_code);

		resService.insertres(res);

		// 값나눠서 리스트에 넣기 split
		// 선택한 좌석정보 넣기
		String seat = req.getParameter("seat");
		List<String> list = Arrays.asList(seat.split(","));
		for (String i : list) {
			if (Integer.parseInt(i.substring(1)) < 10) {
				i = i.substring(0, 1) + "0" + i.substring(1);
			}
			// 좌석일련번호
			String seat_code = req.getParameter("room_code") + i;

			System.out.println("seat::" + seat_code);
			map.put("seat_code", seat_code);
			map.put("res_code", res_code);
			map.put("show_code", show_code);
			resService.insertseat(map);
		}

		mav.addObject("seat", list);
		mav.addObject("room_name", req.getParameter("room_name"));
		mav.addObject("thea_name", req.getParameter("thea_name"));
		mav.addObject("room_code", req.getParameter("room_code"));
		mav.addObject("thea_code", req.getParameter("thea_code"));
		mav.addObject("date", req.getParameter("date"));
		mav.addObject("show_time", req.getParameter("show_time"));

		return mav;
	}*/


	/************************ 똑같이 만들수 없음.. ************************/
	/** 결제가 완료되면 DB에 추가하는 작업
	 * 총 3가지 작업을 해야한다.
	 * RES(예매정보) 추가, SEAT(선택한 좌석정보) 추가, MEMBER(회원) 포인트 수정
	 */
	//show_code, res_sum, res_point, seatList
	@PostMapping(path = "res/resseat.do")
	public ModelAndView newRes(HttpServletRequest request, @RequestParam Map<String,String> model) {
		ModelAndView mav = new ModelAndView("res/seat");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("s_id"); 	//회원 아이디
		String mem_id = (String) session.getAttribute("s_id");
		String s_mlev = (String) session.getAttribute("s_mlev");
		String showCode = model.get("show_code"); 				//상영코드
		int price = Integer.parseInt(model.get("res_sum"));     //지불 금액
		int usePoint = Integer.parseInt(model.get("res_point")); //사용한 포인트

		//상영코드 SE010206181230 이라고 가정 하고 작업
		//상영관코드 SE0102
		String roomCode = showCode.substring(0,6);
		//상영날짜 0618
		String date = showCode.substring(6,10);

		//예매코드 만들기 상영관코드 + 날짜 +  시퀀스
		
		//상영관코드-날짜- 로 마지막 값 가져오기
		String resCode = roomCode + "-" + date + "-";
		String lastResCode = resService.selectLastResCode(resCode);

		log.info("lastResCode{}", lastResCode);

		if(lastResCode == null){
			resCode += "1";
		}else{
			String sequence_ = lastResCode.substring(12);
			log.info("sequence_{}", sequence_);
			int sequence = Integer.parseInt(sequence_);
			sequence++;
			log.info("sequence{}", sequence);
			resCode = resCode + sequence;
			log.info("resCode{}", resCode);

		}
		//예매코드 완성

		//선택된 좌석 문자열  ex) A1,E8,H3
		String selectSeatList_ = model.get("seatList");
		//문자열 배열로 바꾸고 리스트로 옮기기
		List<String> selectSeatList = Arrays.asList(selectSeatList_.split(","));
		//SEAT 테이블에 추가할 seat 리스트
		List<Seat> seatList = new ArrayList<>();

		for (String i : selectSeatList) {
			//한자리 숫자를 두 자리로 만드는 작업 ex 1, 2 => 01, 02
			if (Integer.parseInt(i.substring(1)) < 10) {
				i = i.substring(0, 1) + "0" + i.substring(1);
			}
			//좌석 번호
			String seatCode = roomCode + i;

			Seat seat = new Seat(resCode, seatCode, showCode);
			seatList.add(seat);
		}

		Res res = new Res();
		res.setRes_code(resCode);
		res.setShow_code(showCode);
		res.setMem_id(id);
		res.setRes_count(selectSeatList.size());
		res.setRes_sum(price);
		res.setRes_point(usePoint);

		Map<String,Object> map = new HashMap<>();
		map.put("res", res);
		map.put("seatList", seatList);

		//DB INSERT
		boolean isSuccess = resService.insertRes(map);
		
		//포인트적립
		Member member = new Member();
		member.setMem_id(id);		
		member.setMem_point((int)(price*0.05));
		int result1 = memberService.addPoint(member);
		System.out.println("포인트 적립 여부 : " + result1);
		/////////////
		System.out.println(mem_id);
		
		//등급갱신
		int money = memberService.selectResMoney(mem_id);
		System.out.println("6개월 동안 돈 : " + money);
		
		System.out.println(s_mlev);
			if( money > 200000  ){
				member.setMem_lev("V");
			}else if( money > 120000 ){
				member.setMem_lev("G");
			}else if( money > 60000  ){
				member.setMem_lev("S");
			}else if( money > 0  ){
				member.setMem_lev("B");
			}else if(s_mlev.equals("MASTER")) {
			member.setMem_lev("MASTER");
		}
		
		
		int result2 = memberService.modifyMemLev(member);
		System.out.println("등급갱신 : " + result2);
		
		
		
		
		//영화제목, 몇관, 상영날짜, 좌석, 결제금액
		mav.addObject("title",model.get("title"));
		mav.addObject("theaName",model.get("theaName"));
		mav.addObject("roomName",model.get("roomName"));

		int year = LocalDate.now().getYear();
		String showTime = String.format("%d/%s/%s %s:%s",year,date.substring(0,2),date.substring(2,4),showCode.substring(10,12),showCode.substring(12));
		mav.addObject("date",showTime);
		mav.addObject("list",selectSeatList_);
		mav.addObject("price",price);

		return mav;
	}

	@PostMapping("/res/pay")
	public ModelAndView payment(@RequestParam Map<String,Object> model,
								HttpServletRequest request){
		ModelAndView mav = new ModelAndView("res/pay");

		//요금 정산 하고 모델에 추가
		int adult = Integer.parseInt((String) model.get("adult")); //어른
		int youth = Integer.parseInt((String) model.get("youth")); //청소년
		int account = adult * 10000 + youth * 8000; //총 요금
		model.put("account",account);

		//사용자 포인트 정보 불러와 모델에 추가
		HttpSession session = request.getSession();
		//세션에 저장된 아이디 가져오기
		String id = (String) session.getAttribute("s_id");
		int point = memberService.selectPointById(id);
		model.put("point",point);

		mav.addObject("m",model);
		mav.addObject("poster", resService.selectposter(request.getParameter("mov_title")));
		return mav;
	}


//예약취소 -> 선택한좌석정보삭제 > 예매정보취소

}
