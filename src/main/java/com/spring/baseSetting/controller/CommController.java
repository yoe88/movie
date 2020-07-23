package com.spring.baseSetting.controller;

import com.spring.baseSetting.dto.Com_notice;
import com.spring.baseSetting.dto.Com_reply;
import com.spring.baseSetting.dto.PageMaker;
import com.spring.baseSetting.dto.SearchCriteria;
import com.spring.baseSetting.service.ComNoticeService;
import com.spring.baseSetting.service.ComReplyService;

import net.utility.UploadSaveManager;
import net.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class CommController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final ComNoticeService comNoticeService;
	/*
	 * private final ComReplyService comReplyService;
	 * 
	 * public public CommController(ComReplyService comReplyService) { // TODO
	 * Auto-generated constructor stub logger.info("CommController Init...");
	 * this.comReplyService = comReplyService; }
	 */

	public CommController(ComNoticeService comNoticeService) {
	    logger.info("CommController Init...");
	    this.comNoticeService = comNoticeService;
	}
	
	@Inject
	ComReplyService comReplyService;


	@GetMapping(path = "/home.do")
	  public String home() {
	    return "basicweb/index";
	}
	    
	//1.5list 게시판 목록 조회
	//list.do로 하니까 페이지가 안나옴ㅜㅠ
	@RequestMapping(value = "/board/list", method=RequestMethod.GET)
	public ModelAndView list (SearchCriteria scri) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		System.out.println(pageMaker);
		pageMaker.setTotalCount(comNoticeService.listCount(scri));
		
		mav.addObject("pageMaker", pageMaker);
		
		//댓글 개수 select count->댓글수표시
		
		
		mav.addObject("list", comNoticeService.comAllList(scri));
		System.out.println(comNoticeService.comAllList(scri));
		return mav;
	}
	
	
	@RequestMapping(value = "/board/movtalk", method=RequestMethod.GET)
	public ModelAndView movtalk (SearchCriteria scri) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/movtalk");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		System.out.println(pageMaker);
		pageMaker.setTotalCount(comNoticeService.getmovtalkCount(scri));
		
		mav.addObject("pageMaker", pageMaker);
		
		//댓글 개수 select count->댓글수표시
		
		
		mav.addObject("list", comNoticeService.getMovtalk(scri));
		return mav;
	}
	
	
	@RequestMapping(value = "/board/que", method=RequestMethod.GET)
	public ModelAndView que (SearchCriteria scri) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/que");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		System.out.println(pageMaker);
		pageMaker.setTotalCount(comNoticeService.getqueCount(scri));
		
		mav.addObject("pageMaker", pageMaker);
		
		//댓글 개수 select count->댓글수표시
		
		
		mav.addObject("list", comNoticeService.getQue(scri));
		return mav;
	}
	
	
	@RequestMapping(value = "/board/myque", method=RequestMethod.GET)
  public ModelAndView myque (SearchCriteria scri, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("board/myque");
    
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(scri);
    System.out.println(pageMaker);
    pageMaker.setTotalCount(comNoticeService.getqueCount(scri));
    
    mav.addObject("pageMaker", pageMaker);
    
    //댓글 개수 select count->댓글수표시
    
    scri.setMem_id((String) session.getAttribute("s_id"));
    mav.addObject("list", comNoticeService.getmyQue(scri));
    return mav;
  }
	
	
	
	//3 상세보기 getOneComNotice
	@RequestMapping(value = "/board/onelist.do", method=RequestMethod.GET)
	public ModelAndView detail (int com_no, HttpServletRequest req, 
			@RequestParam(value="page", defaultValue = "1")int page,
			@RequestParam(value="perPageNum", defaultValue ="1")int perPageNum) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/onelist");
		
		mav.addObject("page", page);
		mav.addObject("perPageNum", perPageNum);
		String referer = (String)req.getHeader("REFERER");
    referer = referer.substring(referer.lastIndexOf("/")+1);
    System.out.println("prev_url : " + referer);
    mav.addObject("prev_url", referer);
		
		int comNo = Integer.parseInt(req.getParameter("com_no"));
		//System.out.println("com_no : " + com_no);
		

		//List<Com_reply> ListreplyList = comReplyService.ListreadReply2(com_no); =>답글 보여주는 라인
		logger.info("답글" + comReplyService.ListreadReply2(com_no).toString());
		mav.addObject("reply2", comReplyService.ListreadReply2(com_no));

		
		mav.addObject("hit", comNoticeService.upHit(com_no));
		mav.addObject("detail", comNoticeService.getOneComNotice(com_no));
		//System.out.println(comNoticeService.getOneComNotice(comNotice));
		return mav;
	}	
	
	//replywrite댓글작성
	@RequestMapping(value = "/board/replyWrite.do", method = RequestMethod.POST)
	public String replyWrite(Com_reply comReply, SearchCriteria scri, RedirectAttributes rttr,HttpSession session) {
		logger.info("reply Write");
		comReplyService.writeReply(comReply);
		
		  rttr.addAttribute("com_no", comReply.getCom_no()); 
		  rttr.addAttribute("page", scri.getPage()); 
		  rttr.addAttribute("perPageNum", scri.getPerPageNum());
		  rttr.addAttribute("searchType", scri.getSearchType());
		  rttr.addAttribute("keyword", scri.getKeyword());
		  scri.setMem_id((String) session.getAttribute("s_id"));
		 
		return "redirect:onelist.do";
	}
	

	// 댓글 수정 GET
	@RequestMapping(value="/board/replyUpdateView", method = RequestMethod.GET)
	public ModelAndView replyUpdateView (Com_reply comReply, SearchCriteria scri,
			@RequestParam(value="page", defaultValue = "1")int page,
			@RequestParam(value="perPageNum", defaultValue ="1")int perPageNum) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/replyUpdateView");
		
		mav.addObject("page", page);
		mav.addObject("perPageNum", perPageNum);
		mav.addObject("replyUpdate", comReplyService.selectReply(comReply.getRno()));
		mav.addObject("scri", scri);
		
		return mav;
	}
	
	// 댓글 수정 POST
	@RequestMapping(value="/board/replyUpdate", method = RequestMethod.POST)
	public String replyUpdate(Com_reply comReply, SearchCriteria scri, RedirectAttributes rttr) {
		logger.info("reply Write");
		
		int cnt = comReplyService.updateReply(comReply);
		rttr.addAttribute("com_no", comReply.getCom_no());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/onelist.do";
	}
		
	// 댓글 삭제 POST
	@RequestMapping(value="/board/replyDeleteView")
	public ModelAndView replyDelete(Com_reply comReply, SearchCriteria scri, RedirectAttributes rttr) {
		logger.info("reply Write");
		ModelAndView mav = new ModelAndView();
	    
	    
		
		int cnt = comReplyService.deleteReply(comReply);
		if(cnt==0) {
			mav.addObject("msg1", "<p>댓글 삭제 실패</p>");
			mav.addObject("img", "<img src='../images/fail.png'>"); 
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()' class=\"btn btn-outline-success\">"); 
			mav.addObject("link2", "<input type='button' value='전체 목록' onclick='location.href=\"list.do\"' class=\"btn btn-outline-info\">"); 
			
			mav.setViewName("board/msgView2");
		}else {
			mav.setViewName("redirect:onelist.do?com_no="+comReply.getCom_no());
		}
		
		return mav;
	}
	
	//4 게시물 등록 putComNotice
	@RequestMapping(path = { "/board/create.do" })
	public ModelAndView create(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/createForm");
		
		String referer = (String)req.getHeader("REFERER");
		referer = referer.substring(referer.lastIndexOf("/")+1);
		System.out.println("prev_url : " + referer);
		mav.addObject("prev_url", referer);
		
		
		return mav;
	}
	
	//4.5 게시물 등록putComNotice
	@RequestMapping(value = "/board/createResult.do")
	public ModelAndView setNoticeinfo(Com_notice comNotice, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/result");

		System.out.println(comNotice);
		
		String quePath = req.getRealPath("/resources/que");
		
		
		// 1) <input type='file' name='com_filenameMF'> // -> 파일 가져오기
		MultipartFile queMF = comNotice.getFilenameMF(); // -> 파일을 저장하고 리네임된 파일명 반환 
		String que = UploadSaveManager.saveFileSpring30(queMF, quePath); 
		// -> 파일명 dto 객체에담기 
		comNotice.setCom_filename(que);
		
		
		int cnt = comNoticeService.putComNotice(comNotice);
		
		String gcode = req.getParameter("prev_url");
		System.out.println("gcode : " + gcode);
		
		if (cnt == 1) {
			mav.addObject("title", "게시물등록결과");
			mav.addObject("msg", "등록완료<br>");
		
			if( gcode.equals("movtalk")) {
		    	  mav.addObject("link1", "<input type='button' value='게시물목록' onclick='location.href=\"movtalk\"'>");
		    }else if( gcode.equals("que")) {
		    	  mav.addObject("link1", "<input type='button' value='게시물목록' onclick='location.href=\"que\"'>");
		    }else if( gcode.equals("myque")) {
          mav.addObject("link1", "<input type='button' value='게시물목록' onclick='location.href=\"myque\"'>");
      }			
		} else {
			mav.addObject("msg", "등록실패");
		}
		return mav;
	}

	
	// 5 게시물삭제
	@RequestMapping(path = { "/board/delete.do" }, method=RequestMethod.GET)
	public ModelAndView cutComNotice(int com_no, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("board/deleteForm");
	    mav.addObject("root", Utility.getRoot());
	    mav.addObject("prev_url", req.getParameter("prev_url"));
	    System.out.println("prev_url : " + req.getParameter("prev_url"));
	    
	    //삭제관련 정보 가져오기
	    mav.addObject("detail", comNoticeService.getOneComNotice(com_no));
	    return mav;	    
	}//deleteForm()end

	// 5.5 게시물 삭제
	@RequestMapping(path = { "/board/delete.do" }, method=RequestMethod.POST)
	public ModelAndView cutComNotice(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("board/msgView2");
	    mav.addObject("root", Utility.getRoot());
	    
	    //삭제하고자 하는 정보 가져오기
	    int com_no = Integer.parseInt(req.getParameter("com_no"));
	    System.out.println("com_no" + com_no);
	    
	    //실제 물리 경로
	  	String quePath = req.getRealPath("/resources/que");
	  	
	  	//물리적 경로에서 삭제
	  	UploadSaveManager.deleteFile(quePath, comNoticeService.getOneComNotice(com_no).getCom_filename());
	    
	    int cnt = comNoticeService.cutComNotice(com_no);
	    System.out.println("cnt : " + cnt );
	    if (cnt==0) {
			mav.addObject("msg1", "<p>게시물 삭제 실패</p>");
			mav.addObject("img", "<img src='../images/fail.png'>"); 
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'> class=\"btn btn-outline-success\""); 
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"" + req.getParameter("prev_url") + "\"' class=\"btn btn-outline-info\">"); 
	    }else{
	    	mav.addObject("msg1", "<p>삭제되었습니다.</p>");
  			mav.addObject("link1", "<input type='button' value='전체 목록' onclick='location.href=\"" + req.getParameter("prev_url") + "\"' class=\"btn btn-outline-info\">"); 
  			mav.setViewName("board/msgView2");
	    }
	    	return mav;	    
	}//delete()end
		
	
	//6게시판수정뷰
	@RequestMapping(path = { "/board/updateView.do" }, method=RequestMethod.GET)
	public ModelAndView goComNotice (int com_no) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/updateView");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("detail", comNoticeService.getOneComNotice(com_no));
		return mav;
	}
	
	//저장하기 누르면 수정 정보 저장하기!
	@RequestMapping(path = { "/board/updateView.do" }, method=RequestMethod.POST)
	public ModelAndView goCumNoticeProc (Com_notice comNotice, HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/msgView2");
		
		int cnt = 0;
		int com_grpno = 0;
		comNotice.setCom_grpno(com_grpno);
		String com_ip = req.getRemoteAddr();
		comNotice.setCom_ip(com_ip);
		
		
		//실제 물리 경로
		String quePath = req.getRealPath("/resources/que");
		
		//옛날 dto 저장
		Com_notice oldDto = comNoticeService.getOneComNotice(comNotice.getCom_no());
		
		
		// 1) <input type='file' name='com_filenameMF'> // -> 파일 가져오기
		MultipartFile queMF = comNotice.getFilenameMF(); // -> 파일을 저장하고 리네임된 파일명 반환 
		if( queMF.getSize()>0 ) {
			//새로운 포스터 파일이 전송된 경우
			
			//기존 파일 삭제
			UploadSaveManager.deleteFile(quePath, oldDto.getCom_filename());
			
			//신규 파일 저장
			String que = UploadSaveManager.saveFileSpring30(queMF, quePath); // -> 파일명 dto 객체에담기
			comNotice.setCom_filename(que);;
			
		}else {
			//포스터 파일을 수정하지 않는 경우
			comNotice.setCom_filename(oldDto.getCom_filename());
		}		
		cnt = comNoticeService.goComNotice(comNotice);
		
		
		String gcode = oldDto.getCom_gcode();
		//System.out.println("gcode : " + gcode);
		
		if(cnt==0) {
			  mav.addObject("msg1",  "<p>수정 실패</p>");
		      mav.addObject("img",   "<p><img src='../images/fail.png'></p>");
		      mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
		      
		    	mav.addObject("link2", "<input type='button' value='확인' onclick='location.href=\"onelist.do?com_no=" + comNotice.getCom_no() + "\"'>");
		} else {
		      mav.addObject("msg1",  "<p>게시물 수정 성공</p>");
		      mav.addObject("img",   "<p><img src='" + req.getContextPath() + "/resources/images/success2.png' style='width:550px; height:auto;' ></p>");
		       mav.addObject("link2", "<input type='button' value='확인' onclick='location.href=\"onelist.do?com_no=" + comNotice.getCom_no() + "\"'>");
		}//if end
		return mav;
	}
	
	  
	}//class end