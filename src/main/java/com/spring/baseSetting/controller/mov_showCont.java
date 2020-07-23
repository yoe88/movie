package com.spring.baseSetting.controller;

import com.spring.baseSetting.dto.Mov_show;
import com.spring.baseSetting.dto.Mythea;
import com.spring.baseSetting.service.Mov_showService;
import com.spring.baseSetting.service.MovieService;
import com.spring.baseSetting.service.MytheaService;
import com.spring.baseSetting.service.RoominfoService;
import com.spring.baseSetting.service.TheaInfoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class mov_showCont {
  @Autowired
  private Mov_showService dao;
  @Autowired
  private MovieService mdao;
  @Autowired
  MytheaService mytheaService;
  @Autowired
  private TheaInfoService tdao;
  @Autowired
  private RoominfoService rdao;
  
  public mov_showCont() {
    System.out.println("---mov_showCont()객체생성됨");
  }
  
  //http://localhost:9090/movieForest/mov_show/res_mov.do
  //(예매를 눌렀을 때)
  @RequestMapping(value="mov_show/res_mov.do")
  public ModelAndView res_mov(Mov_show dto) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("mov_show/res_mov");
    mav.addObject("list", dao.getmovAll());
    mav.addObject("nowMovie",mdao.getNowMovieCard());
    System.out.println(mdao.getNowMovieCard());
    mav.addObject("thea",tdao.getThea());
    
    System.out.println("movie list : " +  dao.getmovAll());
    
    return mav;
  }
  
  //http://localhost:9090/movieForest/mov_show/theaer.do
  //(영화관을 선택했을 때)
  @RequestMapping(value="mov_show/theater.do")
  public ModelAndView theater(String thea_code, String date, HttpSession session) {
    ModelAndView mav = new ModelAndView("mov_show/theater");
    if(thea_code == null) { //영화관 코드가 null이면 빈 값을 넘긴다
      thea_code = "SU01";
    }

    if(date == null) { //날짜가 null이 나오면 오늘의 날짜로 지정
      Date default_date  = new Date();
      SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
      date =  fm.format(default_date);
    }
    
    Map<String, String> map = new HashMap<String, String>();
    map.put( "parameter1", thea_code );
    map.put( "parameter2", date.replace("-", "") );
    mav.addObject("mov", dao.getMov_title(map));  //  title, rate
    mav.addObject("list", dao.getMovByThea(map));
    mav.addObject("date", date);
    mav.addObject("thea_code", thea_code);
    
    String mem_id = (String) session.getAttribute("s_id");
	
    mav.addObject("mthealist", mytheaService.getAllthea());
    mav.addObject("yoyo", mytheaService.getMythea(mem_id));
    mav.addObject("noyoyo", mytheaService.getnotMythea(mem_id));
    
    
    
    mav.addObject("thea", tdao.getThea());  
    mav.addObject("ti",tdao.getTheaInfo(thea_code));  //코드에 해당하는 영화관 정보
    mav.addObject("msg", "해당 영화관에서 예매할 수 있는 영화가 없습니다.");
    
    return mav;
  }

  
  
  
  
  @RequestMapping(value = "/mov_show/mytheaInsert.do", method = RequestMethod.GET)
	public String mytheaIns(Mythea mytheaDto, HttpServletRequest req, HttpSession session) {
		
		String thea_code = req.getParameter("thea_code");
		String mem_id = (String) session.getAttribute("s_id");
		String click_thea = req.getParameter("click_thea");
		
		mytheaDto.setThea_code(thea_code);
		mytheaDto.setMem_id(mem_id);
		
		System.out.println("thea_code : " + thea_code);
		System.out.println("mem_id : " + mem_id);
    
		int mytheaCnt = mytheaService.addMythea(mytheaDto);
		
		if ( mytheaCnt == 1 ) {
			System.out.println("즐찾 영화관 등록 성공");
		}else {
			System.out.println("즐찾 영화관 등록 실패");
		}//if end
		
		
		return "redirect:./theater.do?thea_code="+click_thea;
	}
	
	
	
	
	@RequestMapping(value = "/mov_show/mytheaDel.do", method = RequestMethod.GET)
	public String mytheaDel(Mythea mytheaDto, HttpServletRequest req, HttpSession session) {
		
		String thea_code = req.getParameter("thea_code");
		String mem_id = (String) session.getAttribute("s_id");
    String click_thea = req.getParameter("click_thea");
		
		mytheaDto.setThea_code(thea_code);
		mytheaDto.setMem_id(mem_id);
		
		System.out.println("thea_code : " + thea_code);
		System.out.println("mem_id : " + mem_id);
		
		int mytheaCnt = mytheaService.removeMythea(mytheaDto);
		
		if ( mytheaCnt == 1 ) {
			System.out.println("즐찾 영화관 삭제 성공");
		}else {
			System.out.println("즐찾 영화관 삭제 실패");
		}//if end
		
		
		return "redirect:./theater.do?thea_code="+click_thea;
	}
  
  
  
  
  
  
  
  
  //http://localhost:9090/movieForest/mov_show/show_list.do
  //(관리자용 상영표 보기)
  @RequestMapping(value="mov_show/show_list.do")
  public ModelAndView show_list(String thea_code, String date) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("mov_show/show_list");
    
    //영화관을 따로 선택하지 않았을 때는 강남점을 보여주기
    if(thea_code == null) {
      thea_code = "SU01";
    }
    //날짜를 선택하지 않았을 때는 오늘의 날짜를 보여주기
    if(date == null) {
      Date default_date  = new Date();
      SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
      date =  fm.format(default_date);
    }
    
    Map<String, String> map = new HashMap<String, String>();
    map.put( "parameter1", thea_code );
    map.put( "parameter2", date.replace("-", "") );
    
    mav.addObject("list", dao.getmovAllMaster(map));
    mav.addObject("thea_code", thea_code);
    mav.addObject("date", date);
    mav.addObject("thea",tdao.getThea());
    return mav;
  }
  
  //http://localhost:9090/movieForest/mov_show/show_create.do
  //(상영시간 추가 폼)
  @RequestMapping(value="mov_show/show_create.do", method = RequestMethod.GET)
  public ModelAndView show_create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("mov_show/show_create");
    mav.addObject("nowMovie",mdao.getNowMovieCard());
    mav.addObject("thea",tdao.getThea());
    mav.addObject("rooms",rdao.getAllRoominfo());
    
    return mav;
  }
  
  @RequestMapping(value="mov_show/show_create.do", method = RequestMethod.POST)
  public ModelAndView show_createProc(Mov_show dto, HttpServletRequest req) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("mov_show/msgView");
    
    if(dto.getRoom_code().length() == 1) {
      dto.setRoom_code("0" + dto.getRoom_code());
    }
    dto.setRoom_code(dto.getShow_code()+dto.getRoom_code());
    
    try {
      java.sql.Date date = java.sql.Date.valueOf(req.getParameter("date"));
      //mav.addObject("date",date);
      dto.setShow_date(date);
      
      dto.setShow_time(req.getParameter("time"));
      
    }catch (Exception e) {
      System.out.println(e);
    }
    
    String thea_code = dto.getShow_code();
    String show_code = dto.getRoom_code() + String.valueOf(dto.getShow_date()).substring(5).replace("-", "") + dto.getShow_time().replace(":", "");
    
    dto.setShow_code(show_code);
    
    int cnt = dao.addMov_show(dto);
    if( cnt==0 ) {
      mav.addObject("msg1",  "<p> 상영 시간 추가 실패 </p>");
      mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()' class=\"btn btn-outline-danger\">");
      mav.addObject("link2", "<input type='button' value='목록보기' onclick='location.href=\"show_list.do\"'  class=\"btn btn-outline-info\">");
      
    }else {
      
      mav.addObject("msg1",  "<p> 상영 시간 추가 성공 </p>");
      mav.addObject("link1", "<input type='button' value='추가등록' onclick='location.href=\"show_create.do\"' class=\"btn btn-outline-secondary\">");
      mav.addObject("link2", "<input type='button' value='목록보기' onclick='location.href=\"show_list.do?thea_code="+thea_code+"&date="+dto.getShow_date()+"\"'  class=\"btn btn-outline-info\">");
    
    }
    return mav;
  }
  
  @RequestMapping("mov_show/show_delete.do")
  public ModelAndView show_delete(Mov_show dto) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("mov_show/msgView");
    
    int cnt = dao.removeMov_show(dto);
    if( cnt==0 ) {
      mav.addObject("msg3",  "<p> 상영 시간 삭제 실패 </p>");
      mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()' class=\"btn btn-outline-danger\">");
      mav.addObject("link2", "<input type='button' value='목록보기' onclick='location.href=\"show_list.do'  class=\"btn btn-outline-info\">");
      
    }else {
      mav.addObject("msg3",  "<p> 상영 시간 삭제 성공 </p>");
      mav.addObject("link2", "<input type='button' value='목록보기' onclick='location.href=\"show_list.do\"'  class=\"btn btn-outline-info\">");
    
    }//if end
    
    return mav;
    
  }
  
  @RequestMapping(value="mov_show/show_update.do", method=RequestMethod.GET)
  public ModelAndView updateForm(String show_code) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("mov_show/show_updateForm");
    
    Mov_show dto = dao.getMov_show(show_code);
    String thea_code = dto.getRoom_code().substring(0, 4);
    dto.setRoom_code(dto.getRoom_code().substring(4));
    
    mav.addObject("dto", dto);
    mav.addObject("thea_code", thea_code);
    mav.addObject("thea",tdao.getThea());
    mav.addObject("nowMovie",mdao.getNowMovieCard());
    
    return mav;
  }
  
  @RequestMapping(value="mov_show/show_update.do", method=RequestMethod.POST)
  public ModelAndView updateProc(Mov_show dto, HttpServletRequest req) {
    ModelAndView mav = new ModelAndView();  
    mav.setViewName("mov_show/msgView");
    
    if(dto.getRoom_code().length() == 1) {
      dto.setRoom_code("0" + dto.getRoom_code());
    }
    dto.setRoom_code(req.getParameter("thea_code") + dto.getRoom_code());
    
    try {
      java.sql.Date date = java.sql.Date.valueOf(req.getParameter("date"));
      dto.setShow_date(date);
      
      dto.setShow_time(req.getParameter("time"));
      
    }catch (Exception e) {
      System.out.println(e);
    }
    
    int cnt = dao.modifyMov_show(dto);
    if( cnt==0 ) {
      mav.addObject("msg2",  "<p> 미디어 그룹 수정 실패 </p>");
      mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'  class=\"btn btn-outline-danger\">");
      mav.addObject("link2", "<input type='button' value='목록보기' onclick='location.href=\"show_list.do\"'  class=\"btn btn-outline-info\">");
      
    }else {
      mav.addObject("msg2",  "<p> 미디어 그룹 수정 성공 </p>");
      mav.addObject("link1", "<input type='button' value='목록보기' onclick='location.href=\"show_list.do?thea_code="+req.getParameter("thea_code")+"&date="+dto.getShow_date()+"\"'  class=\"btn btn-outline-info\">");
    
    }//if end
    
    return mav;
      
  }
}
