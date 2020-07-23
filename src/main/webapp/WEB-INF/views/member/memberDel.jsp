<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 본문시작 memberDel.jsp -->

<div style="text-align: center">
	<h5>회 원 탈 퇴</h5>
</div>
<input type="hidden" id="mem_id" name="mem_id" value="${sessionScope.s_id}">
<form method="post" action="./memberdelpro.do"
	onsubmit="return loginCheck(this)">
	<table border="1" id='customers' width='30%'
		style="margin-left: auto; margin-right: auto;">
					

		<tr>
			<td>비밀번호</td>
			<td><input type="password" name='mem_passwd'></td>
		</tr>
		<tr style="text-align: center">
			<td colspan='2'><input type='submit' value='확인'></td>
		</tr>
	</table>
</form>
<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>