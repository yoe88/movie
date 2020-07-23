package com.spring.baseSetting.controller;

import com.spring.baseSetting.dto.Member;
import com.spring.baseSetting.service.ComNoticeService;
import com.spring.baseSetting.service.MemberService;

import net.utility.Utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class HomeController {
   
	@Autowired
	   ComNoticeService comNoticeService;
  
    

    
    @GetMapping(path = "/")
    public ModelAndView home() {
       ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("root", Utility.getRoot());
           
        mav.addObject("poplist", comNoticeService.getPopular());
        mav.addObject("newwlist", comNoticeService.getNew());
        
        return mav;
    }

  //index 가져오기
    @RequestMapping(value = "/index.do", method = RequestMethod.GET)
    public ModelAndView movUpdate() {
       ModelAndView mav = new ModelAndView();
       mav.setViewName("index");
       mav.addObject("root", Utility.getRoot());
          
       mav.addObject("poplist", comNoticeService.getPopular());
       mav.addObject("newwlist", comNoticeService.getNew());
       
       return mav;
    }

    /*
    @GetMapping("/member")
    public ResponseEntity<List<Member>> members() {
        List<Member> members = memberService.getAllMemberList();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<String> member(@PathVariable("id") String id){
        logger.info("입력한 id: {}",id);
        Member member = memberService.getMember(id);
        if(member == null){
            return new ResponseEntity<>("0", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("1", HttpStatus.OK);
        }
    }

    @PostMapping("/member/new")
    public String addMember(@ModelAttribute("member") Member member) {
        int result = memberService.addMember(member);
        return "redirect:/";
    }
    */
}
