<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본문 시작 memberList.jsp-->
<div style="text-align: center; padding-top: 20px;">
	<h5>회 원 목 록</h5>
</div>

<br>
<!-- <input type="button" class="btn btn-default btn-sm" value="글쓰기"
onclick="location.href='./bbsform.do'"> -->

<div style="text-align:center">
<br>

</div>
<div align="center" class="container">
<form action="memberlist.do" >
<input type='text'  name='query' id='query' size='10' placeholder="아이디입력">
<input type="submit" value="아이디검색">
</form>
<hr>





<br>
<br>
<form>
	<table class="table table-bordered" style="width: 80%; margin: auto">
		<tr>
			<td>회원아이디</td>
			<td>회원이름</td>
			<td>회원등급</td>
			<td>가입날짜</td>
		</tr>
		<c:if test="${fn:length(list) > 0}">
			<c:forEach var="member" items="${list}">
				<tr>
					<td><a href="memberdetail.do?mem_id=${member.mem_id}">${member.mem_id }</a></td>
					<td>${member.mem_name }</td>
					<td>${member.mem_lev }</td>
					<td>${member.mem_date }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${fn:length(list) <= 0}">
			<tr>
				<td colspan="7" style='text-align: center;'>회원없음</td>
			</tr>
		</c:if>
	</table>
	
	
	
	<c:if test="${p  != 1}">
		<input type='button' value='<<'
			onclick="location.href='../member/memberlist.do?page=1&query=${query}'">
		<input type='button' value='<'
			onclick="location.href='../member/memberlist.do?page=${p-1}&query=${query}'">
	</c:if>
	
	<!-- 리스트 번호 출력  -->
	<c:if test="${p+9 <= pageMaxNum}">
	<c:forEach begin="${p}" end="${p+9}" varStatus="st">
		<span><a href="../member/memberlist.do?page=${st.current}&query=${query}">${st.current}</a></span>
	</c:forEach>
	</c:if>
	<c:if test="${p+9 > pageMaxNum}">
	<c:forEach begin="${p}" end="${pageMaxNum}" varStatus="st">
		<span><a href="../member/memberlist.do?page=${st.current}&query=${query}">${st.current}</a></span>
	</c:forEach>
	</c:if>
	
	<c:if test="${pageMaxNum!=0}">
	<c:if test="${p  != pageMaxNum }">
		<input type='button' value='>'
			onclick="location.href='../member/memberlist.do?page=${p+1}&query=${query}'">
		<input type='button' value='>>'
			onclick="location.href='../member/memberlist.do?page=${pageMaxNum}&query=${query}'">
	</c:if>
</c:if>
<hr>
	
	
	
	
	
	
	
	
</form>
</div>
<br>
<br>


<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>