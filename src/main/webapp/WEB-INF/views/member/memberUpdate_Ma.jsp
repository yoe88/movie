<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본문 시작 memberUpdate_Ma.jsp-->
<div style="text-align: center">
	<h5>회 원 수 정</h5>
</div>
<br>
<br>
<form method="post" name="memberUpdate_Ma"
	action="updatemembermapro.do"
	id="myForm">
	<input type="hidden" name="mem_id" id="mem_id" value="${member.mem_id }">
	<input type="hidden" name="mem_passwd" id="mem_passwd" value="${member.mem_passwd }">
	<input type="hidden" name="mem_name" id="mem_name" value="${member.mem_name }">
	<input type="hidden" name="mem_phone" id="mem_phone" value="${member.mem_phone }">
	<input type="hidden" name="mem_date" id="mem_date" value="${member.mem_date }">
	<input type="hidden" name="mem_email" id="mem_email" value="${member.mem_email }">
	<input type="hidden" name="mem_birth" id="mem_birth" value="${member.mem_birth }">
	<input type="hidden" name="mem_zipcode" id="mem_zipcode" value="${member.mem_zipcode }">
	<input type="hidden" name="mem_addr" id="mem_addr" value="${member.mem_addr }">
	<input type="hidden" name="mem_addr2" id="mem_addr2" value="${member.mem_addr2 }">
	<br>
	<table class="table table-bordered">


		<tr>
			<td>회원 아이디</td>
			<td>${member.mem_id }</td>
		</tr>
		<tr>
			<td>회원 이름</td>
			<td>${member.mem_name }</td>
		</tr>
		<tr>
			<td>회원 핸드폰번호</td>
			<td>${member.mem_phone }</td>
		</tr>
		<tr>
			<td>회원 이메일</td>
			<td>${member.mem_email }</td>
		</tr>
		<tr>
			<td>회원 등급</td>
			<c:if test="${member.mem_lev == 'MASTER'}">
				<td><input type="text" name="mem_lev" id="mem_lev" size="15"
				value=${member.mem_lev } readonly></td>
			</c:if>
			
			<c:if test="${member.mem_lev != 'MASTER'}">
			 <td>    
    		<select name="mem_lev" form="myForm" >
    		<option value="V"<c:if test="${member.mem_lev == 'V'}">selected</c:if> >V등급</option>   
    		<option value="G"<c:if test="${member.mem_lev == 'G'}">selected</c:if>>G등급</option>
    		<option value="S"<c:if test="${member.mem_lev == 'S'}">selected</c:if>>S등급</option>
    		<option value="B"<c:if test="${member.mem_lev == 'B'}">selected</c:if>>B등급</option>
  			<option value="OUT" <c:if test="${member.mem_lev == 'OUT'}">selected</c:if>>OUT등급(탈퇴회원)</option>

			</select></td> 

			</c:if>

		</tr>
		<tr>
			<td>회원 포인트</td>
			<td><input type="text" name="mem_point" id="mem_point" size="15"
				value=${member.mem_point }></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" class="btn btn-default" value="수정">
			<input type="reset" class="btn btn-default" value="취소">
			<input type="button" class="btn btn-default" value="목록" onClick="location.href='./memberlist.do?pagenum=${pageNum}'">
			</td>
		</tr>
	</table>
</form>


<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>