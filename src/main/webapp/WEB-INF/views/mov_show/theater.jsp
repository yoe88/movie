<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- ================================Header section================================ -->
<style>
	.overlay_info{
		background: white;
		border: 1px solid black;

		padding: 2px 5px;
	}
	.overlay_info .text::after {
		content: "";
		position: absolute;
		top: 100%;
		left: 50%;
		margin-left: -5px;
		border-width: 5px;
		border-style: solid;
		border-color: black transparent transparent transparent;
	}
</style>
<div class="container">	
	<!-- <h3>영화관 리스트</h3>  -->
	<br><br>
	<!-- 회원 로그인 안했을때 화면 -->
	<c:if test="${s_id == null }">
		<span> 영화관 </span><br>
		<c:forEach var="mydto" items="${mthealist }">
			<div style="float:left;padding-right:20px;">
				<img class="nologin" src="${contextPath}/resources/images/basicStar.png" style="width:20px; height:20px; cursor:pointer;" >
				<a href='./theater.do?thea_code=${mydto.thea_code}' style="text-decoration:none;" >${mydto.thea_name }</a> |
				
			</div>
		</c:forEach>
	</c:if>


	<!-- 로그인 했을때 화면 -->
	<c:if test="${s_id != null }">
		<!-- 로그인 한 회원 즐찾 있을때 없을때 화면 다르게(choose, otherwise) -->
		<c:choose>
			<c:when test="${fn:length(yoyo) > 0 }">
				<!-- 즐찾 영화관 블럭 -->
				<span> 즐겨찾는 영화관 </span><br>
				<c:forEach var="mydto" items="${yoyo }">
					<div style="float:left;padding-right:20px;">
						<a href="./mytheaDel.do?thea_code=${mydto.thea_code }&click_thea=${thea_code}" style="text-decoration:none;">
							<img src="${contextPath}/resources/images/myStar.png" style="width:20px; height:20px;"  >
						</a>
						<a href='./theater.do?thea_code=${mydto.thea_code}' >${mydto.thea_name }</a> |
					</div>
					
				</c:forEach>
				<br>
				<hr>
				
				<!-- 즐찾 영화관 아닌거 블럭 -->
				<span> 영화관 </span><br>
				<c:forEach var="mydto" items="${noyoyo }">
					<div style="float:left;padding-right:20px;">
						<a href="./mytheaInsert.do?thea_code=${mydto.thea_code }&click_thea=${thea_code}" style="text-decoration:none;">
							<img src="${contextPath}/resources/images/basicStar.png" style="width:20px; height:20px;"  >
						</a>
						<a href='./theater.do?thea_code=${mydto.thea_code}' >${mydto.thea_name }</a> |
					</div>
				</c:forEach>
			</c:when>
			
			<c:otherwise>
				<span> 영화관 </span><br>
				<c:forEach var="mydto" items="${noyoyo }">
					<div style="float:left;padding-right:20px;">
						<a href="./mytheaInsert.do?thea_code=${mydto.thea_code }&click_thea=${thea_code}" style="text-decoration:none;">
							<img src="${contextPath}/resources/images/basicStar.png" style="width:20px; height:20px;" >
						</a>
						<a href='./theater.do?thea_code=${mydto.thea_code}' >${mydto.thea_name }</a> |
					</div>
				</c:forEach>
			</c:otherwise>
			
		</c:choose>
	</c:if>
	
	
	
	
	
	
	<!-- 지점 불러오기도 다른 테이블을 이용해야 하기 때문에 그냥 수동 처리함 -->
	<%-- 
	<div class="btn-group btn-group-lg">
		<c:forEach var="thea" items="${thea}">
	  		<button type="button" onclick="location.href='res_thea.do?thea_code=${thea.thea_code}'" class="btn btn-primary">${thea.thea_name }</button>
		</c:forEach>
	</div>
	<br> 
	--%>
	
	<c:set var="currTime" value='<%=new java.util.Date(new java.util.Date().getTime() + 10*60*1000)%>'></c:set>
	<c:set var="today"><fmt:formatDate value="${currTime}" pattern='yyyy-MM-dd' /> </c:set>
	<c:set var="time"><fmt:formatDate value="${currTime}" pattern='HH:mm' /> </c:set>
	
	
	<c:if test="${!empty thea_code }">
		<!-- <br><hr><a style="font-weight: bold;">영화관</a><br><br>  -->
		<br><br><br><br>
	
		<table class="table" style="width: 50%;float: left;">
            <tr class="row">
                <th class="col-md-2">지점명</th>
                <td class="col-md-10">
                    <p id="theaName">${ti.thea_name}</p>
                </td>
            </tr>
            <tr class="row">
                <th class="col-md-2">위치</th>
                <td class="col-md-10">
                    <input type="hidden" id="theaLoc_" value="${ti.thea_loc}">
                    <p id="theaLoc">${fn:replace(fn:substring(ti.thea_loc,fn:indexOf(ti.thea_loc, ","), fn:length(ti.thea_loc)),"," ,"")}</p>
                </td>
            </tr>

            <tr class="row">
                <th class="col-md-2">운영 시간</th>
                <td class="col-md-10">
                    <p>${ti.thea_time}</p>
                </td>
            </tr>
            
            <tr class="row">
                <th class="col-md-2">전화번호</th>
                <td class="col-md-10">
                    <p>${ti.thea_tel}</p>
                </td>
            </tr>
            </table>
		
		<input type="hidden" id="theaLoc_" value="${ti.thea_loc}">   <%--장소--%>
		<input type="hidden" id="theaName" value="${ti.thea_name}">  <%--영화관이름--%>
		
		<span style="font-weight: bold;float: right;width: 40%;text-align: center;"> 주변 맛집 </span><br>
		<div style="width: 40%;float: right;">
			<div id="map" style="height:230px;width: 100%;"></div>
		</div>
		<div style="clear: both;"></div>
		<p style="font-size: 20px; font-weight: 5px;color:#333333">상영시간표</p>
		<div style="width:55%;margin-bottom:10px" class="btn-group btn-group-md">	
			<c:forEach var="i" begin="0" end="3">
				<c:set var="now" value='<%=new java.util.Date(new java.util.Date().getTime() + 60*60*24*1000*(int)pageContext.getAttribute("i"))%>'/>
				<c:set var="next_date"><fmt:formatDate value="${now}" pattern='yyyy-MM-dd' /> </c:set>
				<c:set var="md"><fmt:formatDate value="${now}" pattern='MM/dd' /> </c:set>
				<button onclick="location.href='theater.do?thea_code=${thea_code }&date=${next_date}'" class="btn btn-dark">${md }</button>
				
			</c:forEach>
		</div>
		<br>
	</c:if>
	
	<br>
	
	<c:if test="${empty list && !empty thea_code }">
		${msg }
	</c:if>
	
	<c:if test="${!empty list}">
		<c:forEach var="mov" items="${mov}">
			${mov.mov_title} 
			
			
			<c:if test="${mov.mov_rate eq 12 }"> <img src="${contextPath}/resources/images/mov_rate_12.png" style="width:23px;"> </c:if>
            <c:if test="${mov.mov_rate eq 15 }"> <img src="${contextPath}/resources/images/mov_rate_15.png" style="width:23px;"> </c:if>
            <c:if test="${mov.mov_rate eq 19 }"> <img src="${contextPath}/resources/images/mov_rate_19.png" style="width:23px;"> </c:if>
            <c:if test="${mov.mov_rate eq 0 }">  <img src="${contextPath}/resources/images/mov_rate_all.png" style="width:23px;"> </c:if>
			<p></p>
			<div style="width: 56%">
				<c:forEach var="dto" items="${list}">			
					<c:if test="${mov.mov_title eq dto.mov_title && fn:substring(dto.show_date,0,10) eq date}">
						<!-- 상영시간 10분 남았으면 예매 불가능 -->
						<c:if test="${date eq today && time > dto.show_time}">
							<button style='width:80px;margin: 0 5px 30px 0' disabled='disabled' class='btn btn-outline-dark'>
								${dto.show_time }<br>
								<span style='font-size: 8px;margin:0;height: 15px;color:gray'>${dto.room_name }</span>
							</button>
						</c:if>
						<c:if test="${date != today || time <= dto.show_time}">
							<button style='width:80px;margin: 0 5px 30px 0' onclick="resCheck(this,'${s_id}','${dto.show_code }')"class='btn btn-outline-dark'>
								${dto.show_time }<br>
								<span style='font-size: 8px;margin:0;height: 15px;color:gray'>${dto.room_name }</span>
							</button>
						</c:if>
					</c:if>
					
				</c:forEach>
				
			</div>
		</c:forEach>
		
	</c:if>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<!-- ================================Footer section================================ -->
<%@ include file="../footer.jsp"%>




<script>
	$('.nologin').on('click', function(event){
		alert("즐겨찾는 영화관은 \n로그인 하신 후 이용 가능합니다.");
	});
	
	function resCheck(button, s_id, show_code) {
		if(s_id.length < 1){
			var message="로그인이 필요한 서비스입니다. \n로그인 하시겠습니까?";
			  if(confirm(message)){//확인 true, 취소 false
				  location.href="../member/login.do";
			  }
		}else{
			console.dir(button);
			//const date = button.firstElementChild.
			location.href="../res/seatChoose.do?show_code="+show_code;
		}
	}
</script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=85d1a0d5c859005e21dabd502d6c30b4&libraries=services,clusterer,drawing"></script>
<script src="<c:url value="/resources/js/SearchKakaoAddressAPI.js" />"></script>