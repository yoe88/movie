package com.spring.baseSetting.controller;


import com.spring.baseSetting.dto.KakaoPayApprovalVO;
import com.spring.baseSetting.dto.KakaoPayReadyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class Kakao {
    private static final String HOST = "https://kapi.kakao.com";
    private KakaoPayReadyVO kakaoPayReadyVO;
    //private KakaoPayApprovalVO kakaoPayApprovalVO;

    @PostMapping("/kakao")
    public String kakaoPayReady(@RequestParam HashMap<String,String> map) {
        
        log.info("카카오");

        RestTemplate restTemplate = new RestTemplate();
        log.info(map.toString());
        log.info(map.get("title"));

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "9168f1f991f48cbe7b0eacb29662429e");
        headers.add("Accept", "application/json;charset=UTF-8");
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");                                        //테스트 가맹점코드
        params.add("partner_order_id", "1004");                                 //가맹점 주문번호
        params.add("partner_user_id", "YH");                                    //가맹점 회원 id
        params.add("item_name", map.get("title"));
        params.add("item_code", "영화코드"); // Not Required
        //params.add("quantity", map.get("quantity"));                                            //상품수량
        params.add("quantity", "1");                                            //상품수량
        params.add("total_amount", map.get("total_amount"));                                    //상품 총액
        params.add("tax_free_amount", "0");                                     //상품 비과세 금액
        params.add("vat_amount", "0");     // Not Required                      상품 부가세

        params.add("approval_url", "http://172.16.82.15:9090/movieForest/success?"+map.get("map"));     //결제 성공시
        params.add("cancel_url", "http://172.16.82.15:9090/movieForest");          //결제 취소시
        params.add("fail_url", "http://172.16.82.15:9090/kakaoPaySuccessFail");    //결제 실패시

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        log.info(body.toString());
        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);

            assert kakaoPayReadyVO != null;
            log.info(kakaoPayReadyVO.toString());

            //성공시
            return "redirect:" +kakaoPayReadyVO.getNext_redirect_pc_url();

        } catch (RestClientException | URISyntaxException e) {
            e.printStackTrace();
        }
        return "pay";
    }

    /**
     * @param map 포함 pg_token  결제 승인 요청 인증하는 토큰

     * @return
     */

    @GetMapping("/success")
    public ModelAndView kakaoPaySuccess(@RequestParam Map<String, Object> map) {


        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "9168f1f991f48cbe7b0eacb29662429e");
        headers.add("Accept", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1004");
        params.add("partner_user_id", "YH");
        params.add("pg_token", (String) map.get("pg_token"));
        //params.add("total_amount", "7000");//금액

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            KakaoPayApprovalVO kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);

            ModelAndView mav = new ModelAndView("success");
            mav.addObject("approve", kakaoPayApprovalVO);
            return mav;
            //return "결제성공";

        } catch (RestClientException | URISyntaxException e) {
            e.printStackTrace();
        }
        //ModelAndView mav = new ModelAndView("success");

        return null;
    }

   /* @GetMapping("/success")
    public ModelAndView kakaoPaySuccess() {
        ModelAndView mav = new ModelAndView("success");

        return mav;
    }*/

}
