<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 본문시작 loginForm.jsp -->

<h5 style="text-align:center"> 비밀번호 찾기 </h5>


<form name="findPw" method="post" action="findpw.do" style="text-align:center">

	<div style='padding: 30px;'>
		<input type="text" name="mem_id" placeholder="아이디" required> <br><br> 
		<input type="text" name="mem_email" placeholder="이메일" required><br><br>
		<button type="submit" class="btn btn-default">확인</button>
		<button type="reset" class="btn btn-default">취소</button>
	</div>

</form>




<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>