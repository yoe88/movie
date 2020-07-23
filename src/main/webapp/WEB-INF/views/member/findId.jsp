<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 본문시작 loginForm.jsp -->

<h5 style="text-align:center"> 아이디 찾기 </h5>


<form name="findId" method="post" action="findid.do" style="text-align:center">

	<div style='padding: 30px;'>
		<input type="text" name="mem_name" placeholder="이름" required> <br><br> 
		<input type="text" name="mem_phone" placeholder="핸드폰번호" required><br><br>
		<button type="submit" class="btn btn-default">확인</button>
		<button type="reset" class="btn btn-default">취소</button>
	</div>

</form>




<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>