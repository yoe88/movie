package com.spring.baseSetting.controller;

import com.spring.baseSetting.dto.Roominfo;
import com.spring.baseSetting.dto.Seatinfo;
import com.spring.baseSetting.service.MytheaService;
import com.spring.baseSetting.service.RoominfoService;
import com.spring.baseSetting.service.SeatinfoService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class SeatinfoController {
	 @Autowired
	 RoominfoService roominfoService;
	  

	private final SeatinfoService seatinfoService;

	public SeatinfoController(SeatinfoService seatinfoService) {
		log.info("SeatinfoController Init...");
		this.seatinfoService = seatinfoService;
	}
	
    @GetMapping(path = "/seat/forcreate.do")
    public ModelAndView forcreate(String room_code) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/seat/seat_for_create");
    	mav.addObject("room_code", room_code);
        return mav;
    }

	// 상영관별 좌석정보 삭제하기
	@RequestMapping(path = { "/seat/seatdelete.do" })
	public ModelAndView delseat(String room_code) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("seat/result");
		System.out.println("roomcode:"+room_code);
		int result = seatinfoService.delSeatinfo(room_code);
		Roominfo roominfo = new Roominfo();
		roominfo.setRoom_maximum(0);
		roominfo.setRoom_code(room_code);
		roominfoService.updateroom(roominfo);
		
		if (result == 0) {
			mav.addObject("title", "삭제 결과");
			mav.addObject("msg", "좌석정보 삭제 실패");
			mav.addObject("msg1","<input type='button' value='리스트' onclick=\"location.href='../room/roompagelist.do\'\">");
		} else {
			mav.addObject("title", "삭제 결과");
			mav.addObject("msg", "좌석정보 삭제 성공");
			mav.addObject("msg1","<input type='button' value='리스트' onclick=\"location.href='../room/roompagelist.do\'\">");
		}
		return mav;
	}



	//페이징 된 리스트
	@GetMapping("/seat/pagelist")
	public String home(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String query,String thea_name,String room_name) {

		// 총 게시물 수
		int totalListCnt = seatinfoService.total(query);
		//log.info("{}", page);
		//log.info("{}", totalListCnt);
		model.addAttribute("room_name", room_name);
		model.addAttribute("thea_name", thea_name);
		List<Seatinfo> list = seatinfoService.paging(query, page);
		model.addAttribute("query", query);
		model.addAttribute("List", list);
		model.addAttribute("p", page);
		model.addAttribute("total", totalListCnt);
		int pageMaxNum = (int) Math.ceil((totalListCnt / 10.0));
		model.addAttribute("pageMaxNum", pageMaxNum);

		//log.info("total{}, max{}", totalListCnt, pageMaxNum);
	
		return "seat/pagelist";
	} 

	
	
	
	// 좌석정보수정
	@RequestMapping(value = "/seat/seatforcreateresult.do")
	public ModelAndView setSeatinfolist(String room_code,int room_maximum) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("seat/result");
		Seatinfo seatinfo = new Seatinfo();

		Roominfo roominfo = new Roominfo();
		roominfo.setRoom_maximum(room_maximum);
		roominfo.setRoom_code(room_code);
		roominfoService.updateroom(roominfo);	

		
		//상영관코드 대소문자변환 
		String one = room_code.substring(0,1);
		char in = one.charAt(0);
		if(in >= 97) {
			int f = ((int)in)-32;
			in = (char) f;
		}
		String two = room_code.substring(1,2);
		char on = two.charAt(0);
		if(on >= 97) {
			int g = ((int)in)-32;
			on = (char) g;
		}
		
		String hap = one+two+room_code.substring(2);
		room_code = hap;
		
		
		if(room_maximum==100) {
			// for문 insert
			for (int a = 1; a <= 10; a++) {
				for (char b = 65; b <= 'J'; b++) {
					String change = "";
					change = String.valueOf(b);

					if (a < 10) {
						String cn = "0" + a;
						String seatcode = room_code + change + cn;
						seatinfo.setSeat_code(seatcode); // 좌석일련번호 01~09
					} else {
						String seatcode = room_code + change + a;
						seatinfo.setSeat_code(seatcode); // 좌석일련번호 10
					} // if end
					
					seatinfo.setRoom_code(room_code); // 상영관코드
					seatinfo.setSeat_column(change); // 좌석행
					seatinfo.setSeat_row(a); // 좌석열

					int cnt = seatinfoService.addSeatinfo(seatinfo);
					if (cnt == 1) {
						mav.addObject("title", "좌석정보등록결과");
						mav.addObject("msg", "등록완료");
						mav.addObject("msg1","<input type='button' value='리스트'   onclick=\"location.href='../room/roompagelist.do'\">");					
					} else {
						mav.addObject("msg", "등록실패");
						mav.addObject("msg1","<input type='button' value='리스트'   onclick=\"location.href='../room/roompagelist.do'\">");					
					}
				}
			}
		}else if(room_maximum==80) {
			// for문 insert
			for (int a = 1; a <= 10; a++) {
				for (char b = 65; b <= 'H'; b++) {
					String change = "";
					change = String.valueOf(b);

					if (a < 10) {
						String cn = "0" + a;
						String seatcode = room_code + change + cn;
						seatinfo.setSeat_code(seatcode); // 좌석일련번호 01~09
					} else {
						String seatcode = room_code + change + a;
						seatinfo.setSeat_code(seatcode); // 좌석일련번호 10
					} // if end

					seatinfo.setRoom_code(room_code); // 상영관코드
					seatinfo.setSeat_column(change); // 좌석행
					seatinfo.setSeat_row(a); // 좌석열

					int cnt = seatinfoService.addSeatinfo(seatinfo);
					if (cnt == 1) {
						mav.addObject("title", "좌석정보등록결과");
						mav.addObject("msg", "등록완료");
						mav.addObject("msg1","<input type='button' value='리스트'   onclick=\"location.href='../room/roompagelist.do'\">");					
					} else {
						mav.addObject("msg", "등록실패");
						mav.addObject("msg1","<input type='button' value='리스트'   onclick=\"location.href='../room/roompagelist.do'\">");					
					}
				}
			}
		}
		


		return mav;
	}

}

