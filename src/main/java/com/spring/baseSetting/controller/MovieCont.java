package com.spring.baseSetting.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.baseSetting.dto.Movie;
import com.spring.baseSetting.dto.Still;
import com.spring.baseSetting.service.Mov_gradeService;
import com.spring.baseSetting.service.MovieService;
import com.spring.baseSetting.service.StillService;

import lombok.extern.slf4j.Slf4j;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Slf4j
@Controller
public class MovieCont {

	@Autowired
	MovieService mService;

	@Autowired
	StillService sService;
	
	@Autowired
	Mov_gradeService gService;

	public MovieCont() {
		System.out.println("--- MovieCont() 객체 생성됨");
	}

	
	
	
	
	// http://localhost:9090/movieForest/movie/movInsert.do
	@RequestMapping(value = "/movie/movInsert.do", method = RequestMethod.GET)
	public ModelAndView movieForm(Movie dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/movieForm");
		mav.addObject("root", Utility.getRoot());
		// compare_code 만들기
		SimpleDateFormat format1 = new SimpleDateFormat("yyMMdd");
		Date date = new Date();

		String today = format1.format(date);
		// System.out.println("today : " + today);

		String compare_code = "M" + today;
		// System.out.println("compare_code : " + compare_code);
		
		
		// countmov랑 합칠거 string 형식
		String scountmov = "";
				
		// insert할때마다 그날 등록된 mov_code 중 제일 마지막꺼 가져와서  return
		// + 1 해야되서 int로 함
		int countmov = 0;
		
		scountmov = mService.getCountMovCode(compare_code);
		System.out.println("scountmov : " + scountmov);
		
		if(scountmov == null) {
			countmov = 0;
		}else {
			scountmov = scountmov.substring(8);
			System.out.println("sub_scountmov : " + scountmov);
			countmov = Integer.parseInt(scountmov);
		}
		
		// countmov로 01, 02, 03, 10, 11 형식 만들기
		if (countmov < 10) {
			scountmov = "0" + (countmov + 1);
		} else if (countmov >= 10) {
			scountmov = Integer.toString(countmov + 1);
		}
		// System.out.println("scountmov : " + scountmov);

		String mov_code = compare_code + scountmov;
		// System.out.println("mov_code : " + mov_code);

		mav.addObject("mov_code", mov_code);

		return mav;
	}

	
	
	
	
	// http://localhost:9090/movieForest/movie/movieForm.do
	@RequestMapping(value = "/movie/movInsert.do", method = RequestMethod.POST)
	public ModelAndView insert(Movie mdto, Still sdto, HttpServletRequest req, HttpServletResponse resp,
			MultipartHttpServletRequest mulreq) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/msgView");
		mav.addObject("root", Utility.getRoot());
		log.info(mdto.toString());

		// System.out.println(mdto.getMov_code());
		// System.out.println(req.getParameter("mov_code"));

		int scnt = 0;
		int mcnt = 0;
		sdto.setMov_code(mdto.getMov_code());

// -----------------------------------------------------------------------------------
		// 전송된 파일 처리
		// -> 실제 파일은 /movie/poster, /movie/still_imgs 폴더에 저장
		// -> 저장된 파일관련 정보는 movie테이블

		// 실제 물리적인 경로
		String posterPath = req.getRealPath("/resources/poster");
		String stillPath = req.getRealPath("/resources/still_imgs");

		// 1) <input type='file' name='posterMF'> // -> 파일 가져오기
		MultipartFile posterMF = mdto.getPosterMF(); // -> 파일을 저장하고 리네임된 파일명 반환
		String poster = UploadSaveManager.saveFileSpring30(posterMF, posterPath); // -> 파일명 dto 객체에담기
		mdto.setMov_img(poster);

		// insert movie====================================
		mcnt = mService.addMovie(mdto);
		// =================================================

		// 2) <input type='file' name='stillMF'>
		List<MultipartFile> fileList = mulreq.getFiles("stillMF");

		for (MultipartFile mf : fileList) {
			// String still_img = mf.getOriginalFilename();
			// System.out.println(still_img);

			String still_img = UploadSaveManager.saveFileSpring30(mf, stillPath);
			//System.out.println(still_img);

			sdto.setStill_img(still_img);
			scnt = sService.addStill(sdto);

		}

		/*
		 * Iterator<String> files = mulreq.getFileNames(); while( files.hasNext() ) {
		 * String uploadFile = files.next();
		 * 
		 * MultipartFile stillMF = mulreq.getFile(uploadFile); still_img =
		 * stillMF.getOriginalFilename(); System.out.println("실제 파일 이름 : " + still_img);
		 * 
		 * }
		 */

		// MultipartFile stillMF = sdto.getStillMF();
		// String still_img = UploadSaveManager.saveFileSpring30(stillMF, stillPath);
		// sdto.setStill_img(still_img);

// -----------------------------------------------------------------------------------

		if (mcnt == 1 && scnt == 1) {
			mav.addObject("msg1", "<p style='font-size:35px; font-weight:bold;'> 영화 등록 성공 </p>");
			mav.addObject("img", "<img src='" + req.getContextPath() + "/resources/images/success2.png' style='width:550px; height:auto;' >");
			mav.addObject("link1", "<input type='button' value='영화 관리자 목록' onclick='location.href=\"./masterMov.do\"' class='btn btn-success' >");

		} else {
			mav.addObject("msg1", "<p style='font-size:35px; font-weight:bold;'> 영화 등록 실패 </p>");
			mav.addObject("img", "<img src='" + req.getContextPath() + "/resources/images/fail2.png' style='width:550px; height:auto;' >");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()' class='btn btn-warning' >");
		} // if end

		return mav;
	}

	
	
	
	
	// http://localhost:9090/movieForest/movie/movhome.do
	@RequestMapping(value = "/movie/movhome.do", method = RequestMethod.GET)
	public ModelAndView allmovcard() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/movhome");
		mav.addObject("root", Utility.getRoot());
		
		int allMovCnt = mService.getCountAllMovResv();
		
		mav.addObject("allMovCnt", allMovCnt);
		mav.addObject("list", mService.getNowMovieCard());
		

		return mav;
	}

	
	
	
	
	// http://localhost:9090/movieForest/movie/pre_movies.do
	@RequestMapping(value = "/movie/pre_movies.do", method = RequestMethod.GET)
	public ModelAndView commovcard() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/pre_movies");
		mav.addObject("root", Utility.getRoot());

		mav.addObject("list", mService.getComeMovieCard());

		return mav;
	}

	
	
	
	
	@RequestMapping(value = "/movie/movInfo.do", method = RequestMethod.GET)
	public ModelAndView movInfo(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/movInfo");
		mav.addObject("root", Utility.getRoot());

		String mov_code = req.getParameter("mov_code");
		//System.out.println(mov_code);

		mav.addObject("mov_code", mov_code);
		mav.addObject("mdto", mService.getOneMovieInfo(mov_code));
		
		
		
		//예매율
		int oneMovCnt = mService.getCountOneMovResv(mov_code);
		//System.out.println(oneMovCnt);
		int allMovCnt = mService.getCountAllMovResv();
		//System.out.println(allMovCnt);
		float resvPer = (float)oneMovCnt/(float)allMovCnt*100;
		resvPer = Math.round(resvPer);
		//System.out.println(resvPer);
		
		
		//날짜 형식
		//이거 지우면 이미지들만 나옴 ..
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		String opend = format1.format(mService.getOneMovieInfo(mov_code).getMov_opening());
		System.out.println(mService.getOneMovieInfo(mov_code).getMov_opening());
		mav.addObject("mov_opening", opend);
		
		mav.addObject("stills", sService.getMovieStills(mov_code));

		int still_no = 0;
		String check = req.getParameter("still_no");
		// System.out.println("still_no 1 : " + still_no);

		if (check == null) {
			still_no = sService.getStillNo(mov_code);
			
			// System.out.println("still_no 2 : " + still_no);
		} else {
			still_no = Integer.parseInt(req.getParameter("still_no"));
			// System.out.println("still_no 3 : " + still_no);
		}
		
		mav.addObject("still_no", still_no);
		mav.addObject("onestill", sService.getOneStill(still_no));
		
		mav.addObject("avgGrade", gService.getAvgGrade(mov_code));
		mav.addObject("resvPer", resvPer);
		
		//System.out.println(sService.getOneStill(still_no));

		return mav;
	}

	
	
	
	
	// http://localhost:9090/movieForest/movie/masterMov.do
	@RequestMapping(value = "/movie/masterMov.do", method = RequestMethod.GET)
	public ModelAndView masterMov() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/masterMov");
		mav.addObject("root", Utility.getRoot());
		
		mav.addObject("list", mService.getAllMovie());

		return mav;
	}
	
	
	
	
	//관리자페이지에서 스틸컷 보기
	@RequestMapping(value = "/movie/masterStill.do", method = RequestMethod.GET)
	public ModelAndView masterStill(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/masterStill");
		mav.addObject("root", Utility.getRoot());
		
		String mov_code = req.getParameter("mov_code");
		mav.addObject("list", sService.getMovieStills(mov_code));
		mav.addObject("scnt", sService.getMovieStills(mov_code).size());

		return mav;
	}
	
	
	
	//스틸컷 삭제
	@RequestMapping(value = "/movie/stillDelete.do", method = RequestMethod.GET)
	public ModelAndView stillDelete(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/msgView");
		mav.addObject("root", Utility.getRoot());
		
		int still_no = Integer.parseInt(req.getParameter("still_no"));
		String mov_code = req.getParameter("mov_code");
		
		// 실제 물리적인 경로
		String stillPath = req.getRealPath("/resources/still_imgs");
		
		
		//System.out.println("still_img명 : " + sdao.selectOneStill(still_no).getStill_img());
		
		//물리적 경로에서 삭제
		UploadSaveManager.deleteFile(stillPath, sService.getOneStill(still_no).getStill_img());
		
		//DB에서 삭제
		int cnt = sService.removeOneStill(still_no);
		

		if ( cnt == 1 )  {
			mav.addObject("msg1", "<script> alert('스틸컷 삭제 성공'); </script>");
			

		} else {
			mav.addObject("msg1", "<script> alert('스틸컷 삭제 실패'); </script>");
			
		} // if end

		mav.addObject("msg2", "<script> location.href='./masterStill.do?mov_code="+ mov_code +"' </script>");
		
		return mav;
	}
	
	
	

	
	//삭제 
	@RequestMapping(value = "/movie/movDelete.do", method = RequestMethod.GET)
	public ModelAndView movDelete(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/msgView");
		mav.addObject("root", Utility.getRoot());
		
		String mov_code = req.getParameter("mov_code");
		//System.out.println(mov_code);
		
		// 실제 물리적인 경로
		String posterPath = req.getRealPath("/resources/poster");
		String stillPath  = req.getRealPath("/resources/still_imgs");
		
		//dto 담기
		Movie oldMdto = mService.getOneMovieInfo(mov_code);
		List<Still> oldSdto = sService.getMovieStills(mov_code);
		
		int scnt = sService.removeStill(mov_code);
		int mcnt = mService.removeMovie(mov_code);
			
		//System.out.println("scnt : " + scnt);
		//System.out.println("mcnt : " + mcnt);
		
		if (mcnt == 1 && scnt == oldSdto.size()) {
			mav.addObject("msg1", "<p style='font-size:35px; font-weight:bold;'> 영화 삭제 성공 </p>");
			mav.addObject("img", "<img src='" + req.getContextPath() + "/resources/images/success2.png' style='width:550px; height:auto;' >");
			mav.addObject("link1", "<input type='button' value='영화 관리자 목록' onclick='location.href=\"./masterMov.do\"' class='btn btn-success' >");
			
			//기존 파일 삭제
			UploadSaveManager.deleteFile(posterPath, oldMdto.getMov_img());
			
			for(int j=0; j<oldSdto.size(); j++) {
				UploadSaveManager.deleteFile(stillPath, oldSdto.get(j).getStill_img());
			}

		} else {
			mav.addObject("msg1", "<p style='font-size:35px; font-weight:bold;'> 영화 삭제 실패 </p>");
			mav.addObject("img", "<img src='" + req.getContextPath() + "/resources/images/fail2.png' style='width:550px; height:auto;' >");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()' class='btn btn-warning' >");
			
		} // if end
			
		return mav;
	}
	
	
	
	
	
	
	//업데이트폼 가져오기
	@RequestMapping(value = "/movie/movUpdate.do", method = RequestMethod.GET)
	public ModelAndView movUpdate(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/movUpdate");
		mav.addObject("root", Utility.getRoot());
			
		String mov_code = req.getParameter("mov_code");
		//System.out.println("mov_code(cont) : " + mov_code);
			
		mav.addObject("list", mService.getOneMovieInfo(mov_code));
		//System.out.println("getmovieinfo(cont) : " + mService.getOneMovieInfo(mov_code));
			
		return mav;
	}
	
	
	
	//업데이트 처리
	@RequestMapping(value = "/movie/movUpdate.do", method = RequestMethod.POST)
	public ModelAndView movUpdate(Movie mdto, Still sdto
								, HttpServletRequest req, MultipartHttpServletRequest mulreq) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("movie/msgView");
		mav.addObject("root", Utility.getRoot());
		
		int scnt = 0;
		int mcnt = 0;
		String mov_code = mdto.getMov_code();

// -----------------------------------------------------------------------------------
		// 전송된 파일 처리
		// -> 실제 파일은 /movie/poster, /movie/still_imgs 폴더에 저장
		// -> 저장된 파일관련 정보는 movie테이블

		// 실제 물리적인 경로
		String posterPath = req.getRealPath("/resources/poster");
		String stillPath = req.getRealPath("/resources/still_imgs");
		
		
		
		//옛날 dto 저장
		Movie oldMdto = mService.getOneMovieInfo(mov_code);
		List<Still> oldSdto = sService.getMovieStills(mov_code);
		
//---------------------------------------------------------------------------------------------------------		
		//파일을 수정할 것인지?
		//1) <input type='file' name='posterMF'> // -> 파일 가져오기
		MultipartFile posterMF = mdto.getPosterMF(); // -> 파일을 저장하고 리네임된 파일명 반환
		
		if( posterMF.getSize()>0 ) {
			//새로운 포스터 파일이 전송된 경우
			
			//기존 파일 삭제
			UploadSaveManager.deleteFile(posterPath, oldMdto.getMov_img());
			
			//신규 파일 저장
			String poster = UploadSaveManager.saveFileSpring30(posterMF, posterPath); // -> 파일명 dto 객체에담기
			mdto.setMov_img(poster);
			
		}else {
			//포스터 파일을 수정하지 않는 경우
			mdto.setMov_img(oldMdto.getMov_img());
		}
		
		//무비 업데이트
		mcnt = mService.modifyMovie(mdto);
		
		
		
		
		// 2) <input type='file' name='stillMF'>
		List<MultipartFile> fileList = mulreq.getFiles("stillMF");
		
		if( fileList.size()>1 ) {
			for(int j=0; j<oldSdto.size(); j++) {
				UploadSaveManager.deleteFile(stillPath, oldSdto.get(j).getStill_img());
				
			}
			sService.removeStill(mov_code);
			
			for (MultipartFile mf : fileList) {
			
				String still_img = UploadSaveManager.saveFileSpring30(mf, stillPath);
				//System.out.println(still_img);
				
				sdto.setStill_img(still_img);
				scnt = sService.addStill(sdto);
				
			}
		}
		
		
		
		if ( (mcnt == 1 && scnt == 1) || mcnt == 1 ) {
			mav.addObject("msg1", "<p style='font-size:35px; font-weight:bold;'> 영화 수정 성공 </p>");
			mav.addObject("img", "<img src='" + req.getContextPath() + "/resources/images/success2.png' style='width:550px; height:auto;' >");
			mav.addObject("link1", "<input type='button' value='영화 관리자 목록' onclick='location.href=\"./masterMov.do\"' class='btn btn-success' >");

		} else {
			mav.addObject("msg1", "<p style='font-size:35px; font-weight:bold;'> 영화 수정 실패 </p>");
			mav.addObject("img", "<img src='" + req.getContextPath() + "/resources/images/fail2.png' style='width:550px; height:auto;' >");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()' class='btn btn-warning' >");
			
		} // if end

		return mav;
	}

}// MovieCont class end
