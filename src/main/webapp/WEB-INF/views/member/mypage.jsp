<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>
<br>
<br>
<div class="container">
	<div class="row">
		<table style="margin: auto; float: left;">
			<c:if test="${s_id == null }">
				<tr>
					<td>Guest님 로그인 후 이용해주세요 :)
				</tr>
			</c:if>
			<c:if test="${s_id != null }">
				<tr>
					<td style="width: 120px">
					<img
						src="${contextPath}/resources/images/mymember.png"
						style="width: 100px">
					</td>
				
					<td>
						<p style="margin-bottom: 3px;color: black">${s_id }님은</p>
						<p style="margin-bottom: 3px;color: black">현재 ${mem_lev }등급 회원 입니다.<br></p>
						<p style="margin-bottom: 3px;color: black">보유포인트 : ${memdto.mem_point } 포인트</p>
					</td>
					<td style="padding-left: 30px">
						<c:if test="${fn:length(mythealist) > 0 }">
							자주 찾는 상영관
							<br>
							<div class="btn-group btn-group-lg">
									<c:forEach var="mythea" items="${mythealist }">
										<input type="button" value="${mythea.thea_name }"
											onclick="location.href='../mov_show/theater.do?thea_code=${mythea.thea_code }'"
											class="btn btn-outline-secondary">
									</c:forEach>
								</div>
						</c:if>
						<c:if test="${fn:length(mythealist) < 1 }">
								<p class="text-center">자주 찾는 상영관을 등록해 주세요</p>
						</c:if>
					</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<!-- <td>${mem_lev }등급</td>  -->
					<td style="padding-left: 30px;float: right;" colspan="2">
						
					</td>
				</tr>

			</c:if>
		</table>
	</div>
</div>

<div class="container" style="margin: auto">
	<br> <br>
	<hr style="width: 1000px">
	<br> <br>
	<!-- <p class="text-center">내정보관리</p> -->
	<div class="row">
		<div style="width: 80%; margin: auto; text-align: center">

			<span> <a href="${contextPath}/member/res"> <img
					src="${contextPath}/resources/images/myticket.png"
					style="width: 120px">
			</a>
			</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span> <a
				href="${contextPath}/board/myque"> <img
					src="${contextPath}/resources/images/question.png"
					style="width: 120px">
			</a>
			</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span> <a
				href="${contextPath}/member/memberupdateform.do?mem_id=${s_id}">
					<img src="${contextPath}/resources/images/memberup.png"
					style="width: 120px">
			</a>
			</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span> <a
				href="${contextPath}/member/memberdel.do"> <img
					src="${contextPath}/resources/images/memberdel.png"
					style="width: 120px">
			</a>
			</span>

			<!-- <div onclick="location.href='./memberupdateform.do?mem_id=${s_id}'" class="text-center" style="float:left; border:solid 1px black; background-color:#D3D3D3; margin-top:50px; margin-bottom:50px; width:150px; height:80px; cursor:pointer;"> 회원정보 변경 </div>
			<div onclick="location.href='./memberdel.do'" class="text-center" style="float:left; border:solid 1px black; background-color:#D3D3D3; margin-top:50px; margin-bottom:50px; margin-left:100px; width:150px; height:80px; cursor:pointer;"> 회원 탈퇴 </div> -->
		</div>
	</div>
</div>

<br>
<br>
<br>
<br>
<br>
<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>