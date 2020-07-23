<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>

<br><br>

<!-- 디자인변경 -->
<h3 style="text-align: center">예매가 완료되었습니다.</h3>
<br>
<hr width="500px">
<br>
<table style="margin: auto; width: 450px; height: 200px">
	<tr>
<%-- 		<th rowspan="5">
			<img src="${contextPath}/resources/poster/${poster }" style="height:200px">			
		</th> --%>
		
		<th>영화</th>
		<td>${title}</td>
	</tr>
	<tr>
		<th>영화관</th>
		<td>${theaName} / ${roomName}</td>
	</tr>
	<tr>
		<th>상영날짜</th>
		<td>${date}</td>
	</tr>
	<tr>
		<th>좌석</th>
		<td>${list }</td>
	</tr>
	<tr>
		<th>결제금액</th>
		<td><fmt:formatNumber pattern="#,###" value="${price}"/></td>

	</tr>
	
</table>
<br>
<br>




<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>