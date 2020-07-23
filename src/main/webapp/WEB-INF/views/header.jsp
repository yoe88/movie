<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>

   
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> MovieForest </title>
<link   rel="stylesheet" href="${contextPath}/resources/css/animate.min.css">
<link   rel="stylesheet" href="${contextPath}/resources/css/font-awesome.min.css">
<link   rel="stylesheet" href="${contextPath}/resources/css/ionicons.min.css">
<link   rel="stylesheet" href="${contextPath}/resources/css/style.css">
<link   href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,300' rel='stylesheet' type='text/css'>
<script src="${contextPath}/resources/js/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<link   rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/isotope.js"></script>

<!-- 추가한거 -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link   rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${contextPath}/resources/ckeditor/ckeditor.js"></script>
<link   rel="stylesheet" href="${contextPath}/resources/css/owl.carousel.min.css">
<link   rel="stylesheet" href="${contextPath}/resources/css/owl.theme.default.min.css">
<script src="${contextPath}/resources/js/owl.carousel.js"></script>
<script src="${contextPath}/resources/js/sangik.js"></script>
<script src="${contextPath}/resources/js/myscript.js"></script>




<style>
   a{
      text-decoration: none;
      color:black;
   }
   a:hover{
      text-decoration: none;
      color:black;
   }
   .carousel-indicators li {
      border: none;
      width: 30px;
      padding-top: 0;
      margin: 0;
      margin: 0 5px 25px 5px;
   }
   
   .carousel-indicators li.active {
      border: none;
      width: 30px;
      height: 3px;
      padding: 0;
      margin: 0 5px 25px 5px;
   }
   
   .menu li a {
      font-size: 20px;
   }
   
   .brand a {
      font-size: 40px;
   }
   
   .brand a:hover {
      text-decoration: none;
      color: black;
   }
   
   
   .yoyo {
       width: 100%;
       margin-bottom: 32px;
   }
   
   .yoyo li {
       display: inline-block;
       margin: 4px;
   }
   
   .yoyo li a {
       color: #666;
       font-size: 13px;
       font-weight: 400;
       letter-spacing: 2px;
       padding: 8px 17px;
       margin-right: 2px;
       margin-left: 2px;
       text-transform: uppercase;
       display: block;
       text-decoration: none;
       transition: all 0.4s ease-in-out;
   }
   .yoyo li a:active {
     background: #111112 !important;
   }
   .yoyo li a:hover,
   .yoyo li a:focus {
     background: #111112;
     color: #ffffff;
   }
</style>

</head>
<body>


<!-- Navigation section
================================================== -->
   <div style="width: 100%; background: black;">
      <div
         style="text-align: right; width: 100%; padding: 1% 2% 0 0; background: black;">
         <c:choose>
            <c:when test="${sessionScope.s_id ==null}">
               <span style="font-size: 12px;"> <a
                  href="${contextPath}/member/login.do" style="color: white;"> <img src="${contextPath}/resources/images/login.png" style="width: 24px"> </a>
               </span> &nbsp;
            
            </c:when>
            <c:otherwise>
               <span style="font-size: 12px;"> <a
                  href="${contextPath}/member/logout.do" style="color: white;"> <img src="${contextPath}/resources/images/logout_2.png" style="width: 27px"> </a>
               </span> &nbsp;
            <span style="font-size: 12px;"> <a
                  href="${contextPath}/member/mypage.do" style="color: white;"><img alt="마이페이지" src="${contextPath}/resources/images/memberpage.png" style="width: 30px"> </a>   
            
               </span>
               
            </c:otherwise>
         </c:choose>
      </div>

      <div class="brand" style="text-align: center; width: 100%;">
         <a href="${contextPath}" style="color:white; font-size:50px;"> Movie Forest </a>
      </div>



   <!-- TopMenu -->
   <div class="container" style="padding-top: 25px; padding-bottom: 20px;">
      <div class="row">
         <ul class="yoyo"
            style="text-align: center; margin: 0; width: 100%; padding: 0px;">
            <li><a href="${contextPath}/movie/movhome.do" style="color:white; font-size:17px;"> 영화 </a></li>
            <li><a href="${contextPath}/mov_show/res_mov.do" style="color:white; font-size:17px;"> 예매 </a></li>
            <li><a href="${contextPath}/mov_show/theater.do" style="color:white; font-size:17px;"> 영화관 </a></li>
            <li><a href="${contextPath}/board/movtalk" style="color:white; font-size:17px;"> 영화 잡담 </a></li>
            <li class="dropdown">
               <c:if test="${s_mlev != null &&  s_mlev eq 'MASTER' }">
                        
                  <a class="dropdown-toggle" data-toggle="dropdown" style="color:white; font-size:17px;"style="color:white; font-size:17px;">
                  	관리자메뉴 <span class="caret"></span>
                  </a>
                  <ul class="dropdown-menu" >
                     <li class="dropdown-item"><a href="${contextPath}/movie/masterMov.do"> 영화 관리 </a></li>         <!-- 영화 & 스틸컷 -->
                     <li class="dropdown-item"><a href="${contextPath}/theainfo"> 영화관 관리 </a></li>
                     <li class="dropdown-item"><a href="${contextPath}/room/roompagelist.do"> 상영관 관리 </a></li>                  <!-- 상영관 & 좌석 -->
                     <li class="dropdown-item"><a href="${contextPath}/mov_show/show_list.do"> 상영정보 관리 </a></li>
                     <li class="dropdown-item"><a href="${contextPath}/mov_grade/master_grade.do"> 영화평점 관리 </a></li>
                     <li class="dropdown-item"><a href="${contextPath}/member/memberlist.do"> 회원 관리 </a></li>
                     <li class="dropdown-item"><a href="${contextPath}/board/que"> Q&A 관리 </a></li>
                     
                  </ul>
               
               </c:if>
            </li>
         </ul>
      </div>
      </div>
   </div>