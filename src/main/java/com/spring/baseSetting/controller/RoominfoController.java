package com.spring.baseSetting.controller;

import com.spring.baseSetting.dto.Roominfo;
import com.spring.baseSetting.dto.Seatinfo;
import com.spring.baseSetting.dto.Theainfo;
import com.spring.baseSetting.service.RoominfoService;
import com.spring.baseSetting.service.SeatinfoService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RoominfoController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final RoominfoService roominfoService;
	private final SeatinfoService seatinfoService;

	public RoominfoController(RoominfoService roominfoService, SeatinfoService seatinfoService) {
		logger.info("RoominfoController Init...");
		logger.info("SeatinfoController Init...");
		this.roominfoService = roominfoService;
		this.seatinfoService = seatinfoService;
	}




	@RequestMapping(path = { "/room/updateform.do" })
	public String update() {
		return "room/updateform";
	}

	//상영관정보등록창
	@RequestMapping(path = { "/room/room.do" })
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("room/room_create");;

		mav.addObject("thealist", roominfoService.gettheaname());

		return mav;
	}



	//등록결과창
	@RequestMapping(value = "/room/createresult.do")
	public ModelAndView setRoominfo(Roominfo roominfo,String thea_name,int room_maximum) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("room/result");		
		Seatinfo seatinfo = new Seatinfo();
		roominfo.setThea_code(roominfoService.selectthea(thea_name).getThea_code());  //영화관명 받아오기





		String a = roominfo.getRoom_name().substring(0); //ex)상영관 = "1관" 에서 1을 받아옴
		roominfo.setRoom_name(roominfo.getRoom_name()+"관"); //room_name insert
		int num = Integer.parseInt(a); //1관에서 1 받아옴
		String c = "";
		if(num<10) {
			c = roominfoService.selectthea(thea_name).getThea_code()+"0"+a;
		} //1관~9관 01~09 로 출력
		else {
			c = roominfoService.selectthea(thea_name).getThea_code()+a;
		}
		roominfo.setRoom_code(c); //room_code 생성
		roominfo.setRoom_maximum(room_maximum);
		int cnt = roominfoService.addRoominfo(roominfo);//상영관정보추가
		
		if(room_maximum==100) {
			for(char b =65; b<='J';b++) {
				for(int count = 1; count <=10; count++) {
				String change = "";
				change = String.valueOf(b);
				if (count < 10) {
					String cn = "0" + count;
					String seatcode = roominfo.getRoom_code() + change + cn;    //saet_code 저장
					seatinfo.setSeat_code(seatcode); // 좌석일련번호 01~09
				} else {
					String seatcode = roominfo.getRoom_code() + change + count;
					seatinfo.setSeat_code(seatcode); // 좌석일련번호 10
				} // if end
				seatinfo.setRoom_code(roominfo.getRoom_code()); // 상영관코드
				
				seatinfo.setSeat_column(change); // 좌석행
				seatinfo.setSeat_row(count); // 좌석열
				seatinfoService.addSeatinfo(seatinfo);
				}
			}
		}else if(room_maximum == 80){
			for(char b =65; b<='H';b++) {
				for(int count = 1; count <=10; count++) {
				String change = "";
				change = String.valueOf(b);
				if (count < 10) {
					String cn = "0" + count;
					String seatcode = roominfo.getRoom_code() + change + cn;    //saet_code 저장
					seatinfo.setSeat_code(seatcode); // 좌석일련번호 01~09
				} else {
					String seatcode = roominfo.getRoom_code() + change + count;
					seatinfo.setSeat_code(seatcode); // 좌석일련번호 10
				} // if end
				seatinfo.setRoom_code(roominfo.getRoom_code()); // 상영관코드
				
				seatinfo.setSeat_column(change); // 좌석행
				seatinfo.setSeat_row(count); // 좌석열
				seatinfoService.addSeatinfo(seatinfo);
				}
		}
		}
		
		
		




		if (cnt == 1) {
			mav.addObject("title", "상영관등록결과");
			mav.addObject("msg", "등록완료");
			mav.addObject("msg1", " <input type='button' value='상영관추가하기'   onclick=\"location.href='../room/room.do'\">");
			mav.addObject("msg2", "<input type='button' value='리스트'   onclick=\"location.href='../room/roompagelist.do'\">");
		} else {
			mav.addObject("msg", "등록실패");
		}
		return mav;
	}



	//수정할데이터불러오기
	@RequestMapping(path = {"/room/updateproc.do"})
	public ModelAndView updateproc(Roominfo roominfo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("room/updateproc");

		Roominfo roomdate = roominfoService.getRoominfo(roominfo);
		if(roomdate!=null) {
			mav.addObject("roominfo", roomdate);
		}else {
			mav.addObject("roominfo", null);
		}
		return mav;
	}

	//수정하기
	@RequestMapping(path = {"/room/roomupdatesuc.do"})
	public ModelAndView updatesuc(Roominfo roominfo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("room/result");


		int cnt = roominfoService.upRoominfo(roominfo);



		if(cnt==1) {
			mav.addObject("title", "상영관수정결과");
			mav.addObject("msg", "수정 완료되었습니다.");
			mav.addObject("msg1", " <input type='button' value='home'   onclick=\"location.href='../'\">");
			mav.addObject("msg2", "<input type='button' value='리스트'   onclick=\"location.href='../room/roompagelist.do'\">");
		}else{
			mav.addObject("msg", "수정 실패하였습니다");
		}
		return mav;
	}


	//상영관정보삭제
	@RequestMapping(path = {"/room/deleteroom.do"})
	public ModelAndView roominfodel(Roominfo roominfo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("room/result");

		//좌석정보도 같이삭제시키기
		int seatresult = seatinfoService.delSeatinfo(roominfo.getRoom_code());
		//영화관삭제
		int result = roominfoService.delRoominfo(roominfo);


		if(result==0 & seatresult==0) {
			mav.addObject("msg", "삭제실패");
			mav.addObject("msg1", "<input type='button' value='리스트'   onclick=\"location.href='../room/roompagelist.do'\">");
		}else {
			mav.addObject("msg", "삭제성공");
			mav.addObject("msg1", "<input type='button' value='리스트'   onclick=\"location.href='../room/roompagelist.do'\">");
		}
		return mav;
	}



	//페이징 된 리스트
	@GetMapping("/room/roompagelist.do")
	public String home(Model model, @RequestParam(defaultValue = "1") int page,
					   @RequestParam(defaultValue = "") String query) {

		// 총 게시물 수
		int totalListCnt = roominfoService.theatotal(query);
		//log.info("{}", page);
		//log.info("{}", totalListCnt);


		List<Roominfo> list = roominfoService.theapaging(query, page);
		model.addAttribute("query", query);
		model.addAttribute("list", list);
		




		model.addAttribute("p", page);
		model.addAttribute("total", totalListCnt);
		int pageMaxNum = (int) Math.ceil((totalListCnt / 10.0));
		model.addAttribute("pageMaxNum", pageMaxNum);
		



		return "room/roompagelist";
	}

}

