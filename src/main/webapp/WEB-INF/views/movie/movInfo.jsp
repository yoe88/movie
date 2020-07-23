<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<div id="stillchange">
<%@ include file="../header.jsp"%>


<h2>영화상세</h2>
<br>
<br>
<table style="width:900px; height:550px; margin:auto" >
<tr>
	<th style="font-size: 28px">${mdto.mov_title}</th>
	<th rowspan="8"><img src="${contextPath}/resources/poster/${mdto.mov_img}" style="margin-left: 30%;height: 400px"></th>
</tr>
<tr>
	<td>평점 : ${avgGrade }점 &nbsp;&nbsp; <button type="button" class="btn btn-outline-secondary btn-sm" onclick="location.href='../mov_grade/grade_list.do?mov_code=${mdto.mov_code}'">평점 전체보기</button></td>
</tr>
<tr>
<td>개봉일 : ${mov_opening}</td>
	
</tr>
<tr>
	<td>예매율 : ${resvPer }%</td>
</tr>
<tr>
	<td>감독 : ${mdto.mov_director }</td>
</tr>
<tr>
	<td>배우 : ${mdto.mov_mainactor } / ${mdto.mov_supportactor }</td>
</tr>
<tr>
	<td>장르 : ${mdto.mov_genre }</td>
</tr>
<tr>
	<td>등급 : ${mdto.mov_rate }</td>
</tr>
<tr>
	<td>러닝타임 : ${mdto.mov_time }</td>
</tr>

<tr>

	<td colspan="2"><br>줄거리: ${mdto.mov_content }</td>
</tr>
</table>

<!-- 
${mdto.mov_title}
<br>
${mdto.mov_content }
<br>
${mdto.mov_director }
<br>
${mdto.mov_genre }
<br>
${mdto.mov_rate }
<br>
${mdto.mov_time }
<br>
${mov_opening }
<br>
${mdto.mov_img }
<br>
${mdto.mov_mainactor }
<br>
${mdto.mov_supportactor }
<br>
 -->

<br>
<br>
<br>

<div class="row">
   <div id="stills" class="owl-carousel owl-theme" style="width:600px; margin:auto;">
      <c:forEach var="allstill" items="${stills }">
         <div id="${allstill.still_no }" class="item">
               <img src="${pageContext.request.contextPath}/resources/still_imgs/${allstill.still_img }" alt="${allstill.still_img }"
                  style="height: 133px;width:auto; margin:auto;" onclick="refresh('movInfo.do?mov_code=${allstill.mov_code }&still_no=${allstill.still_no }')">
                  <!-- 133 233 -->
         </div>
      </c:forEach>
      
   </div>
</div>

<script>
   $("#stills").owlCarousel({
      loop:true,
      //margin : 10,
      nav : true,
      center: true,
      navigation : true,
      startPosition : "${still_no}",
      
      responsive : {
         100 : {
            items : 2,
         },
         600 : {
            items : 3,
         }
      }
   });
   $(document).ready(function() {
      $(".owl-carousel").owlCarousel();
   });
</script>

<br>
<br>

   <c:set var="onestill" value="${onestill }"></c:set>
   <div id="${onestill.still_no }" style="text-align:center; width:100%;">
      <img src="${pageContext.request.contextPath}/resources/still_imgs/${onestill.still_img }" style="width:auto; height:500px"
         alt="${onestill.still_img }">
   </div>
<br>
<br>
<br>


<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>
</div>
<script>
   function refresh(url){
      //ajax option
      var ajaxOption = {
            url:url,
            async:true,
            type:"get",
            dataType:"html",
            cache:true
      };
      
      $.ajax(ajaxOption).done(function(data){
         //stillchange 영역 삭제
         //$('#stillchange').empty();
         
         //stillchange 영역 교체
         $('#stillchange').html(data);
      });
   }
</script>