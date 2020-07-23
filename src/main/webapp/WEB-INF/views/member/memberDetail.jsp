<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본문 시작 memberDetail.jsp-->
<br><br>
<div style="text-align: center">
	<h5>회 원 상 세 보 기</h5>
</div>
<br>
<br>
<form>
<table class="table table-bordered"
	style="width: 80%; margin: auto; text-align: center">
	
	<tr>
		<th>회원 아이디</th>
		<td>${member.mem_id}</td>
	</tr>
	<tr>
		<th>회원 이름</th>
		<td>${member.mem_name}</td>
	</tr>
	<tr>
		<th>회원 핸드폰번호</th>
		<td>${member.mem_phone}</td>
	</tr>
	<tr>
		<th>회원 이메일</th>
		<td>${member.mem_email}</td>
	</tr>
	<tr>
		<th>회원 등급</th>
		<td>${member.mem_lev}</td>
	</tr>
	<tr>
		<th>회원 포인트</th>
		<td>${member.mem_point}</td>
	</tr>

</table>
</form>
<br>
<br>

<div style="text-align:center">
<input type="button" class="btn btn-default" value="회원수정"
	onclick="location.href='./memberupdatema.do?mem_id=${member.mem_id}'">
<input type="button" class="btn btn-default" value="회원목록"
	onclick="location.href='./memberlist.do?pagenum=${pageNum}'">
</div>


<br><br><br><br><br><br>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>