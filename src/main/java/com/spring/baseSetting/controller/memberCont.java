package com.spring.baseSetting.controller;

import com.spring.baseSetting.dto.Member;
import com.spring.baseSetting.service.MemberService;

import net.utility.Utility;
import net.utility.MyAuthenticator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.Session;
import javax.mail.Authenticator;

@Controller
public class memberCont {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final MemberService memberService;

	public memberCont(MemberService memberService) {
		logger.info("memberCont Init...");
		this.memberService = memberService;
	}

	/*// index
	@GetMapping(path = "/")
	public String home() {
		return "/index"; // webapp/index.jsp
	}*/

	// 로그인화면창
	@GetMapping(path = "/member/login.do")
	public ModelAndView login(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());
		mav.setViewName("member/loginForm");

		Cookie[] cookies = req.getCookies();

		String go_cid = "";
		if (cookies != null) {
			for (int idx = 0; idx < cookies.length; idx++) {
				Cookie item = cookies[idx];

				if (item.getName().equals("c_id") == true) {
					go_cid = item.getValue();

					mav.addObject("go_cid", go_cid);

				}
			}
		}

		return mav; // webapp/index.jsp
	}

	// 로그인
	@RequestMapping(value = "/member/login.do")
	public ModelAndView login(Member member, HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());

		String mem_lev = memberService.login(member, session);

		if (mem_lev == null) {
			mav.setViewName("member/msgView");
			mav.addObject("msg1", "<p>로그인 실패</p>");
			mav.addObject("msg1", "<p>아이디/비밀번호를 다시 확인해주세요</p>");
			mav.addObject("link1", "<a href='javascript:history.go(-1)'>[다시시도]</a>");

		} else {
			mav.setViewName("redirect:/");
			session.setAttribute("s_mlev", mem_lev);
			session.setAttribute("s_id", member.getMem_id());
			session.setAttribute("s_passwd", member.getMem_passwd());

			String c_id = Utility.checkNull(req.getParameter("c_id"));

			Cookie cookie = null;
			if (c_id.equals("SAVE")) {
				// 아이디저장 체크를 했다면
				// 쿠키변수 선언 new Cookie("쿠키변수", 값)
				cookie = new Cookie("c_id", member.getMem_id());

				System.out.println("cookie : " + cookie);

				// 쿠키의 생존기간, 1개월
				cookie.setMaxAge(60 * 60 * 24 * 30);

				mav.addObject("c_id", cookie);
			} else {
				cookie = new Cookie("c_id", "");
				cookie.setMaxAge(0);

			} // if end

			// 요청한 사용자 PC에 쿠키값을 저장
			resp.addCookie(cookie);

		} // if end

		return mav;
	}

	// 로그아웃
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		memberService.logout(session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/logout");

		return mav;

	}


	// 회원가입-회원약관동의
	@GetMapping(path = "/member/agreement.do")
	public String agreement() {
		return "member/agreement";
	}

	// 회원가입-회원약관동의
	@GetMapping(path = "/member/memberform.do")
	public String memberForm() {
		return "member/memberForm";
	}

	/**
	 * @return 아이디 체크 페이지
	 */
	@GetMapping(path = "/member/idCheckForm")
	public String idCheckForm() {return  "member/idCheckForm";}

	/**
	 * @return 이메일 체크 페이지
	 */
	@GetMapping(path = "/member/emailCheckForm")
	public String emailCheckForm() {return  "member/emailCheckForm";}


	// id중복확인
	@RequestMapping(value = "/member/idcheckpro.do", method = RequestMethod.POST)
	public ModelAndView idCheck(HttpServletRequest req) throws Exception {
		String id = req.getParameter("mem_id");

		int cnt = memberService.checkid(id);
		ModelAndView mav = new ModelAndView();

		mav.setViewName("member/idCheckPro");
		mav.addObject("cnt", cnt);
		mav.addObject("mem_id", id);

		return mav;
	}

	/*비동기 예제
	@RequestMapping(value = "/member/idcheckpro.do", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String idCheck(HttpServletRequest req, @RequestBody Map map ) {
		String id = (String) map.get("mem_id");
		logger.info(map.toString());

		int cnt = memberService.checkid(id);
		return ""+cnt;
	}*/

	// email중복확인
	@RequestMapping(value = "/member/emailcheckpro.do", method = RequestMethod.POST)
	public ModelAndView emailCheck(HttpServletRequest req) throws Exception {
		String email = req.getParameter("mem_email");

		int cnt = memberService.checkemail(email);
		ModelAndView mav = new ModelAndView();

		mav.setViewName("member/emailCheckPro");
		mav.addObject("cnt", cnt);
		mav.addObject("mem_email", email);

		return mav;

	}

	// 회원가입pro
	@RequestMapping(value = "/member/memberpro.do", method = RequestMethod.POST)
	public ModelAndView memberpro(Member member) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());

		mav.setViewName("member/msgView");

		int cnt = memberService.insertmember(member);

		if (cnt == 0) {
			mav.addObject("msg1", "<p>회원가입이 실패되었습니다.</p>");
			mav.addObject("link1", "<a href='javascript:history.go(-1)'>[다시시도]</a>");

		} else {
			mav.addObject("msg1", "<p>회원가입이 완료되었습니다.</p>");
			mav.addObject("link1", "<a href='/movieForest/member/login.do'>[로그인]</a>");
		} // if end

		return mav;
	}

	// 회원탈퇴창
	@GetMapping(path = "/member/memberdel.do")
	public String deletemember() {
		return "member/memberDel";
	}

	// 회원탈퇴pro
	@RequestMapping(value = "/member/memberdelpro.do", method = RequestMethod.POST)
	public ModelAndView memberdelpro(Member member, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());

		mav.setViewName("member/msgView");

		String id = (String) session.getAttribute("s_id");
		member.setMem_id(id); // 세션에있는 id 가져옴

		// System.out.println("id:" + member.getMem_id()); //id, passwd 콘솔창에 출력해봄
		// System.out.println("passwd:" + member.getMem_passwd());
		int cnt = memberService.deletemember(member);

		// System.out.println("cnt : " + cnt);

		if (cnt == 0) {
			mav.addObject("msg1", "<p>비밀번호가 맞지않습니다.</p>");
			mav.addObject("link1", "<a href='javascript:history.go(-1)'>[다시시도]</a>");

		} else {
			mav.addObject("msg1", "<p>탈퇴가 완료되었습니다.</p>");
			mav.addObject("link1", "<a href='/movieForest/'> HOME </a>");
			session.invalidate();

		} // if end

		return mav;
	}

	// 회원정보수정 가져오기
	@RequestMapping(value = "/member/memberupdateform.do", method = RequestMethod.GET)
	public ModelAndView memberupdate(Member member) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());

		mav.setViewName("member/memberUpdateForm");

		String birth = memberService.updatemember(member).getMem_birth();
		birth = birth.substring(0, 10);
		birth = birth.replace("-", "");

		mav.addObject("mem_birth", birth);
		mav.addObject("member", memberService.updatemember(member));

		// System.out.println("mav : " + mav);

		return mav;
	}

	// 회원정보수정
	@RequestMapping(value = "/member/memberupdatepro.do", method = RequestMethod.POST)
	public ModelAndView memberupdatepro(Member member, HttpServletRequest req, HttpServletResponse resp,
										HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());

		int cnt = memberService.updatememberpro(member);

		mav.setViewName("member/msgView");

		if (cnt == 0) {
			mav.addObject("msg1", "<p>수정 실패</p>");

		} else {
			mav.addObject("msg1", "<p>수정 성공</p>");
			mav.addObject("link1", "<a href='/movieForest/'> HOME </a>");

		} // if end

		return mav;
	}

	// 아이디찾기창
	@GetMapping(path = "/member/findid.do")
	public String findid() {
		return "member/findId";
	}

	// 아이디찾기결과
	@RequestMapping(value = "/member/findid.do")
	public ModelAndView findid(Member member) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());

		Member findid = memberService.findid(member);
		mav.setViewName("member/msgView");
		mav.addObject("member", findid);

		if (findid == null) {
			mav.addObject("msg1", "<p>없는 아이디입니다</p>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");

		} else {

			System.out.println("id:" + findid.getMem_id()); // 콘솔창에서 결과봐보기
			mav.addObject("msg1", findid.getMem_id());
			mav.addObject("link1", "<a href='/movieForest/member/findpw.do'> 비밀번호찾기 </a>");
			mav.addObject("link2", "<a href='/movieForest/member/login.do'> 로그인 </a>");

		}

		return mav;
	}

	// 비밀번호찾기창
	@GetMapping(path = "/member/findpw.do")
	public String findpw() {
		return "member/findPw";
	}

	// 비밀번호찾기결과
	@RequestMapping("/member/findpw.do")
	public ModelAndView sendEmailAction(Member member) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());

		Member findpw = memberService.findpw(member);
		mav.setViewName("member/msgView");

		// 1) 메일서버(POP3/SMTP)지정하기

		String mailServer = "mw-002.cafe24.com";
		Properties props = new Properties();
		props.put("mail.smtp.host", mailServer);
		props.put("mail.smtp.auth", "true");

		// 2) 메일서버에서 인증받은 계정 + 비번
		Authenticator myAuth = new MyAuthenticator();

		// 3) 1번과 2번이 유효한지 검증
		Session sess = Session.getInstance(props, myAuth);
		System.out.print("메일 서버 인증 성공");

		// 4) 메일보내기
		try {

			String to = findpw.getMem_email();
			String from = "movieforest@soldesk.com";
			String subject = "비밀번호찾기";
			String content = findpw.getMem_passwd();

			content = Utility.convertChar(content);

			// 받는사람 이메일주소
			InternetAddress[] address = { new InternetAddress(to) };

			/*
			 * 수신인 여러명 InternetAddress[] address={ new InternetAddress(to1) new
			 * InternetAddress(to2) new InternetAddress(to3) };
			 */

			// Message.RecipientType.TO 받는 이메일 주소 (1명)
			// Message.RecipientType.CC 받는 이메일 주소 여러개 (참조)
			// Message.RecipientType.BCC 숨은참조
			// 메일관련 메세지 작성
			Message msg = new MimeMessage(sess);
			// 받는 사람
			msg.setRecipients(Message.RecipientType.TO, address);

			// 보내는 사람
			msg.setFrom(new InternetAddress(from));

			// 메일 제목
			msg.setSubject(subject);

			// 메일 내용
			msg.setContent(content, "text/html; charset=UTF-8");

			// 보낸 날짜
			msg.setSentDate(new Date());

			// 메일 전송
			// Transport.send(msg);
			mav.addObject("msg1", to + " 메일로 비밀번호가 발송되었습니다.");

		} catch (Exception e) {
			System.out.println("에러메세지 : " + e);
			mav.addObject("msg1", "<p>아이디나 이메일을 다시 확인해주세요.</p>");
			mav.addObject("link1", "<a href='javascript:history.back()'>[다시시도]</a>");
		} // try end

		return mav;

	}

	// 회원관리
	// 회원리스트

	// 회원상세보기
	@RequestMapping(value = "/member/memberdetail.do", method = RequestMethod.GET)
	public ModelAndView memberdetail(Member member) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/memberDetail");

		mav.addObject("root", Utility.getRoot());
		mav.addObject("member", memberService.memberdetail(member));

		return mav;
	}

	// 회원수정폼
	@RequestMapping(value = "/member/memberupdatema.do", method = RequestMethod.GET)
	public ModelAndView memberupdatema(Member member) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/memberUpdate_Ma");

		mav.addObject("root", Utility.getRoot());
		mav.addObject("member", memberService.memberdetail(member));

		return mav;
	}

	// 회원정보마스터수정
	@RequestMapping(value = "/member/updatemembermapro")
	public ModelAndView updatemembermapro(Member member, HttpServletRequest req, HttpServletResponse resp,
										  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());

		int cnt = memberService.updatemembermapro(member);
		System.out.println(member);
		mav.setViewName("member/msgView");

		if (cnt == 0) {
			mav.addObject("msg1", "<p>수정 실패</p>");

		} else {
			mav.addObject("msg1", "<p>수정 성공</p>");
			mav.addObject("link1", "<a href='/movieForest/member/memberlist.do'> [회원목록] </a>");

		} // if end

		return mav;
	}

	//---------------------------------------------
	// 회원리스트페이징
	@GetMapping(path = "/member/memberlist.do")
	public String home(Model model, @RequestParam(defaultValue = "1") int page,
					   @RequestParam(defaultValue = "") String query) {

		// 총 게시물 수
		int totalListCnt = memberService.membertotal(query);


		List<Member> list = memberService.memberpaging(query, page);
		model.addAttribute("query", query);
		model.addAttribute("list", list);


		model.addAttribute("p", page);
		model.addAttribute("total", totalListCnt);

		int pageMaxNum = (int) Math.ceil((totalListCnt/ 5.0));
		
		model.addAttribute("pageMaxNum", pageMaxNum);



		return "member/memberList";
	}

}
