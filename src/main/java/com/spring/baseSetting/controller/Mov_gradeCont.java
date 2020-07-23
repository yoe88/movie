package com.spring.baseSetting.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.baseSetting.dto.Mov_grade;
import com.spring.baseSetting.service.Mov_gradeService;

@Controller
public class Mov_gradeCont {
  @Autowired
  private Mov_gradeService dao;
  
  public Mov_gradeCont() {
    System.out.println("---Mov_gradeCont()객체생성됨");
  }
  
  //http://localhost:9090/default/mov_grade/grade_list.do
  @RequestMapping(value="mov_grade/grade_list.do")
  public ModelAndView grade_list(Mov_grade dto, HttpServletRequest req) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("mov_grade/grade_list");
    
    String mov_code = req.getParameter("mov_code");
    List<Mov_grade> list = dao.getMovAllgrade(mov_code);
    
    if(list == null) {
      mav.addObject("list", null);
    }else {
      mav.addObject("list", list);
    }
    
    if(req.getParameter("update_no") == null) {
      mav.addObject("update_no", 0);
    }else {
      mav.addObject("update_no", req.getParameter("update_no"));
    }
    
    mav.addObject("mov_code",mov_code);
    
    return mav;
  }
  
  @RequestMapping(value="mov_grade/grade_create.do")
  public ModelAndView grade_create(Mov_grade dto) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("mov_grade/msgView");
    
    int cnt = dao.addMov_grade(dto);
    if( cnt==0 ) {
      mav.addObject("msg1",  "<script>alert('평점 작성 실패! 다시 시도해주세요');</script>");
    }else {
      mav.addObject("msg1",  "<script>alert('평점이 작성되었습니다.');</script>");
    }
    
    mav.addObject("msg2",  "<script>location.href='grade_list.do?mov_code="+dto.getMov_code()+"'</script>");
    
    return mav;
  }
  
  @RequestMapping("mov_grade/grade_delete.do")
  public ModelAndView grade_delete(Mov_grade dto) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("mov_grade/msgView");
    
    int cnt = dao.removeMov_grade(dto);
    if( cnt==0 ) {
      mav.addObject("msg1",  "<script>alert('평점 삭제 실패! 다시 시도해주세요');</script>");
    }else {
      mav.addObject("msg1",  "<script>alert('평점이 삭제되었습니다.');</script>");
    }
    
    if(dto.getMov_code() == null) {
      mav.addObject("msg2",  "<script>location.href='master_grade.do';</script>");
    }else {
      mav.addObject("msg2",  "<script>location.href='grade_list.do?mov_code="+dto.getMov_code()+"';</script>");
    }
    
    return mav;
  }
  
  @RequestMapping("mov_grade/grade_update.do")
  public ModelAndView grade_update(Mov_grade dto) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("mov_grade/msgView");
    
    int cnt = dao.modifyMov_grade(dto);
    if( cnt==0 ) {
      mav.addObject("msg1",  "<script>alert('평점 수정 실패! 다시 시도해주세요');</script>");
    }else {
      mav.addObject("msg1",  "<script>alert('평점이 수정되었습니다.');</script>");
    }
    
    mav.addObject("msg2",  "<script>location.href='grade_list.do?mov_code="+dto.getMov_code()+"';</script>");
    
    return mav;
  }
  
  
  @RequestMapping("mov_grade/master_grade.do")
  public ModelAndView master_grade(Mov_grade dto) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("mov_grade/master_grade");
    
    mav.addObject("list", dao.getAllgrade());
    
    return mav;
  }
}
