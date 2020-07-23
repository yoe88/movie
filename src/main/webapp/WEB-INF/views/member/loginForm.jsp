<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MovieForest</title>
<link rel="stylesheet" href="${contextPath}/resources/css/animate.min.css">
<link rel="stylesheet" href="${contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="${contextPath}/resources/css/ionicons.min.css">
<link rel="stylesheet" href="${contextPath}/resources/css/style.css">
<link
	href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,300'
	rel='stylesheet' type='text/css'>
	
<script src="${contextPath}/resources/js/jquery.js"></script>
<script src="${contextPath}/resources/js/myscript.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/isotope.js"></script>
<script src="${contextPath}/resources/js/imagesloaded.min.js"></script>
<script src="${contextPath}/resources/js/custom.js"></script>

<style>
.bar{margin:0 4px}
.bar{display:inline-block;width:1px;height:18px;text-indent:-999em;background:#e4e4e5}
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
</style>

</head>
<body>


<!-- Navigation section
================================================== -->
	<div style="text-align: right; width: 100%; padding: 1% 2% 0 0; background: black;" >
		<c:choose>
			<c:when test="${sessionScope.s_id ==null}">
				<span style="font-size: 12px; "> <a href="${contextPath}/member/login.do" style="color:white;"> <img src="${contextPath}/resources/images/login.png" style="width: 24px"> </a>
				
				</span>
			</c:when>
			<c:otherwise>
				<span style="font-size: 12px;"> <a href="${contextPath}/member/logout.do"> <img src="${contextPath}/resources/images/logout.png" style="width: 24px" >  </a>
				<span style="font-size: 12px;"> <a href="${contextPath}/member/mypage.do"> 마이페이지 </a> </span>
				
				</span>
			</c:otherwise>
		</c:choose>
	</div>
<div style="background: black;">
	<div class="container">
		<div class="row">
			<div class="brand" style="text-align: center; width: 100%;">
				<a href="../" style="color:white;"> Movie Forest </a>
			</div>
		</div>
	</div>
	</div>

<!-- 본문시작 loginForm.jsp -->

<br><br><br><br>




<c:if test="${empty s_id }">
	<form name="loginForm" method="post" action="login.do"
		onsubmit="return loginCheck(this)">

		<div class="container">
				<div class="form-group" style="width: 30%; margin: auto">
					<input type="text" class="form-control" name="mem_id"
						value='${go_cid }' placeholder="아이디" required>
				</div>
				<br>
				<div class="form-group" style="width: 30%; margin: auto">
					<input type="password" class="form-control" name="mem_passwd"
						placeholder="비밀번호" required>
				</div>
				<br>
				<button type="submit" class="btn btn-primary btn-block btn-lg"
					style="width: 30%; margin: auto">로 그 인</button>

					
		</div>
		<br>
		<div style="text-align: center ; font-size: 13px">
			<input type="checkbox" name="c_id" value="SAVE"
				<c:if test="${ !(empty go_cid) }"> checked </c:if> >아이디저장	
				</div>
				
				<div style="text-align: center ; font-size: 13px ; width: 20%; margin: auto;" ><hr> </div>
				<div style="text-align: center ; font-size: 13px">
<span class="bar" aria-hidden="true">|</span>
					<a href="agreement.do">회원가입</a>
<span class="bar" aria-hidden="true">|</span>
					<a href="findid.do">아이디찾기</a> 
				<span class="bar" aria-hidden="true">|</span>
					 <a href="findpw.do">비밀번호찾기</a>
			<br><br><br><br><br><br><br><br><br><br><br><br><br>
		</div>
	</form>
</c:if>


<!-- 로그인 성공했다면 -->
<%-- <div style="text-align: center">
	<c:if test="${sessionScope.s_id !=null }">

		<strong>${sessionScope.s_id }</strong>님
		<a href="./logout.do">[로그아웃]</a>
		<br>
		<br>
		<input type="hidden" name="mem_id" id="mem_id"
			value="${sessionScope.s_id}">
		<a href="./memberupdateform.do?mem_id=${sessionScope.s_id}">[회원정보수정]</a>
		<a href="./memberdel.do">[회원탈퇴]</a>
		<br>
		<br>

	</c:if>
</div> --%>


<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>