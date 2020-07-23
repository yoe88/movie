<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
.font {
	font-size: 20px;
}

.center {
	text-align: center;
}
</style>
<p class="font center">
	[${thea_name}]<br>[${room_name}]
</p>
<div align="center" class="container">

	<hr>
	<table border="1" class="table table-striped">

		<thead class="thead-dark">
			<tr>

				<th>행</th>
				<th>열</th>
				<th>좌석</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="board" items="${List}">
				<tr>
					<td>${board.seat_column}</td>
					<td>${board.seat_row}</td>
					<td>${board.seat_column}${board.seat_row}</td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
	<ul class="pagination justify-content-center">
	<c:if test="${p  != 1}">
	<li class="page-item"><a class="page-link" href="../seat/pagelist.do?page=1&query=${query}&thea_name=${thea_name}&room_name=${room_name}"><<</a></li>
	<li class="page-item"><a class="page-link" href="../seat/pagelist.do?page=${p-1}&query=${query}&thea_name=${thea_name}&room_name=${room_name}"><</a></li>
	
	</c:if>

	<!-- 리스트 번호 출력  -->
	<c:if test="${p+9 <= pageMaxNum}">
		<c:forEach begin="${p}" end="${p+9}" varStatus="st">
				<li class="page-item"><a class="page-link" href="../seat/pagelist.do?page=${st.current}&query=${query}&thea_name=${thea_name}&room_name=${room_name}">${st.current}</a></li>
		</c:forEach>
	</c:if>
	<c:if test="${p+9 > pageMaxNum}">
		<c:forEach begin="${p}" end="${pageMaxNum}" varStatus="st">
			<li class="page-item"><a class="page-link" href="../seat/pagelist.do?page=${st.current}&query=${query}&thea_name=${thea_name}&room_name=${room_name}">${st.current}</a></li>	
		</c:forEach>
	</c:if>

	<c:if test="${pageMaxNum!=0}">
		<c:if test="${p  != pageMaxNum }">
			<li class="page-item"><a class="page-link" href="../seat/pagelist.do?page=${p+1}&query=${query}&thea_name=${thea_name}&room_name=${room_name}">></a></li>	
			<li class="page-item"><a class="page-link" href="../seat/pagelist.do?page=${pageMaxNum}&query=${query}&thea_name=${thea_name}&room_name=${room_name}">>></a></li>	
		</c:if>
	</c:if>
	</ul>
	<hr>
	<!-- 좌석리스트가 없다면? -->
	<c:if test="${empty List}">
		<button type="button"
		onclick="location.href='../seat/forcreate.do?room_code=${query}'">좌석생성</button>
	</c:if>
	<!-- 좌석리스트가있다면 ? -->
	<c:if test="${!empty List}">
	<button type="button"
		onclick="location.href='../seat/seatdelete.do?room_code=${query}'">좌석삭제</button>
	</c:if>
	
	<button type="button"
		onclick="location.href='/movieForest/room/roompagelist.do'">리스트</button>
	

	<input type='button' value='HOME'
		onclick="location.href='../'">
</div>
<br>

<%@ include file="../footer.jsp"%>