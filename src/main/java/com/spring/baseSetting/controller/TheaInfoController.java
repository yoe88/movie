package com.spring.baseSetting.controller;

import com.spring.baseSetting.dto.Theainfo;
import com.spring.baseSetting.service.FileService;
import com.spring.baseSetting.service.TheaInfoService;
import com.spring.baseSetting.service.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/theainfo")
public class TheaInfoController {
    private final TheaInfoService theaInfoService;
    private final FileService fileService;

    private final String notAllow =
                        "<script>" +
                    "        alert('올바른 접근이 아닙니다.');" +
                    "        location.href= ' " + Utils.getRoot() + "/theainfo';" +
                    "    </script>";

    @Autowired
    public TheaInfoController(TheaInfoService theaInfoService, FileService fileService) {
        log.info("TheaInfoController Init...");
        this.theaInfoService = theaInfoService;
        this.fileService = fileService;
    }
    /**
     * @return 영화관 리스트 페이지
     */
    @GetMapping("")
    public ModelAndView theaInfoList(@RequestParam(name = "f",required = false,defaultValue = "theaCode") String field
                                    ,@RequestParam(name = "q",required = false,defaultValue = "") String query
                                    ,@RequestParam(name = "p",required = false,defaultValue = "1") String page_){
        int page;
        try{
            page = Integer.parseInt(page_);
            if(page < 1) page = 1;
        } catch (NumberFormatException e){
            page = 1;
        }

        Set<String> fieldOption = new HashSet<>(Arrays.asList("theaCode", "theaName"));
        if(!fieldOption.contains(field)){
            field = "theaCode";
        }
        

        if(field.equals("theaCode")) field = "thea_code";
        if(field.equals("theaName")) field = "thea_name";
        
        ModelAndView mav = new ModelAndView("theaInfo/theaInfoList");
        Map<String, Object> resultMap = theaInfoService.getAllTheaInfoList(field, query, page);
        List<Theainfo> list = (List<Theainfo>) resultMap.get("list");
        int listTotalCount  = (int) resultMap.get("count");
        int pageMaxNum =  (int) Math.ceil((listTotalCount/10.0)); 	//67개일경우 7
        pageMaxNum = (pageMaxNum ==0) ? 1 : pageMaxNum;

        mav.addObject("f",field);
        mav.addObject("p",page);
        mav.addObject("list",list);
        mav.addObject("listTotalCount",listTotalCount);
        mav.addObject("pageMaxNum",pageMaxNum);

        return mav;
    }

    /**
     * @return 영화관 작성 페이지
     */
    @GetMapping("/new")
    public String theaInfoForm(){
        return "theaInfo/theaInfoForm";
    }

    /** 영화관 추가
     * @param theaInfo 영화관 정보
     * @param mf        파일
     */
    @PostMapping("/new")
    public void addTheaInfo(@ModelAttribute Theainfo theaInfo
                            , @RequestParam(value = "file") MultipartFile mf
                            , HttpServletResponse response) throws IOException {
        String folderName = FileService.theaterPath;

        if(mf.getSize() != 0) {
            log.info("file: {}, size: {}", mf.getOriginalFilename(), mf.getSize());
            String fileName =  fileService.upload(mf, folderName);
            if(fileName != null)
                theaInfo.setThea_img(fileName);
        }

        int result = theaInfoService.addTheaInfo(theaInfo);
        if(result != 0)
            response.sendRedirect(Utils.getRoot() + "/theainfo");
        else{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String msg = "<script>" +
                    "        alert('영화관 정보 추가에 실패하였습니다.');" +
                    "        location.href= ' " + Utils.getRoot() + "/theainfo';" +
                    "    </script>";
            out.write(msg);
        }
    }

    /**
     * @param theaCode  영화관 코드
     * @return   영화관 코드에 해당하는 상세페이지
     */
    @GetMapping("/{theaCode}")
    public ModelAndView theaInfoDetail(@PathVariable("theaCode") String theaCode,
                                       HttpServletRequest request){

        ModelAndView mav = new ModelAndView();
        Theainfo ti = theaInfoService.getTheaInfo(theaCode);
        if(ti == null){
            mav.setViewName("empty");
            mav.addObject("error",notAllow);
        }else{
            mav.setViewName("theaInfo/theaInfoDetail");
            mav.addObject("ti",ti);

            String referer = request.getHeader("REFERER");
            log.info(referer);
            if(referer != null){
                String qs = null;
                int index = referer.lastIndexOf("?");
                if(index != -1){
                    qs = referer.substring(referer.lastIndexOf("?"));
                }
                mav.addObject("qs",qs);
            }
        }
        return mav;
    }

    /**
     * @param theaCode 영화관 코드
     * @return 영화관 코드에 해당하는 수정페이지
     */
    @GetMapping("/{theaCode}/edit")
    public ModelAndView theaInfoEdit(@PathVariable("theaCode") String theaCode){
        ModelAndView mav = new ModelAndView();
        Theainfo ti = theaInfoService.getTheaInfo(theaCode);
        if(ti == null){
            mav.setViewName("empty");
            mav.addObject("error",notAllow);
        }else{
            mav.setViewName("theaInfo/theaInfoModify");
            log.info(ti.toString());

            //주소 분리작업
            String[] loc = ti.getThea_loc().split(",");
            String zoneCode = loc[0];
            String address = loc[1];
            String extraAddress = loc[2];
            String detailAddress = loc[3];

            //시간 분리작업
            String[] time = ti.getThea_time().split("~");
            String startTime = time[0].trim();
            String endTime = time[1].trim();

            //Map에 전부 넣기
            Map<String,String> map = new HashMap<>();
            map.put("zoneCode",zoneCode);
            map.put("address",address);
            map.put("extraAddress",extraAddress);
            map.put("detailAddress",detailAddress);
            map.put("startTime",startTime);
            map.put("endTime",endTime);

            mav.addObject("ti",ti);
            mav.addObject("info",map);
        }

        return mav;
    }

    /** 영화관 수정
     * @param theaInfo          수정할 영화관 정보
     * @param mf                단일 파일
     */
    @PostMapping("/{thea_code}/edit")
    public void modifyTheaInfo(@ModelAttribute Theainfo theaInfo
                              ,@RequestParam(value = "file", required = false) MultipartFile mf
                              ,HttpServletResponse response) throws IOException {

        log.info(theaInfo.toString());

        int result = theaInfoService.modifyTheaInfo(theaInfo ,mf);
        if(result != 0)
            response.sendRedirect(Utils.getRoot() + "/theainfo");
        else{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String msg = "<script>" +
                    "        alert('영화관 정보 수정에 실패하였습니다.');" +
                    "        location.href= ' " + Utils.getRoot() + "/theainfo';" +
                    "    </script>";
            out.write(msg);
        }
    }

    @DeleteMapping("/{theaCode}")
    public ResponseEntity<Integer> deleteTheaInfo(@PathVariable("theaCode") String theaCode){
        int result = theaInfoService.deleteTheaInfo(theaCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
