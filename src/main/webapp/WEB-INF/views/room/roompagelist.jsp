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
<br><br>
<p class="font center">상영관 리스트</p>

<div align="center" class="container">
<div class="text-right my-3 float-right">
	<form action="../room/roompagelist.do" method="get">
		<input type='text' name='query' id='query' size='10'
			placeholder="영화관입력"> <input type="submit" value="영화관검색">
	</form>
	</div>

	

	<table border="1" class="table table-striped">

		<thead class="thead-dark">
			<tr>
				<th>영화관</th>
				<th>상영관</th>
				<th>상영관인원</th>
				<th>상영관타입</th>
				<th></th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="room" items="${list }">
				<input type="hidden" id="room_code" name="room_code"
					value="${room.room_code}">
				<input type="hidden" id="thea_code" name="thea_code"
					value="${room.thea_code}">
				<tr>
					<td>${room.thea_name}</td>
					<td>${room.room_name }</td>
					<td>${room.room_maximum}</td>
					<td>${room.room_type}</td>
					<td>
						<button type="button"
							onclick="location.href='../room/deleteroom.do?room_code=${room.room_code}&thea_code=${room.thea_code}'">삭제하기</button>
						<button type="button"
							onclick="location.href='../room/updateproc.do?thea_code=${room.thea_code}&room_code=${room.room_code}'">수정하기</button>
						<button type="button"
							onclick="location.href='../seat/pagelist.do?query=${room.room_code}&thea_name=${room.thea_name}&room_name=${room.room_name }'">좌석보기</button>
					</td>
				</tr>
			</c:forEach>


		</tbody>

	</table>
	
<ul class="pagination justify-content-center">

	<c:if test="${p  != 1}">
	<li class="page-item"><a class="page-link" href="../room/roompagelist.do?page=1&query=${query}"><<</a></li>
	<li class="page-item"><a class="page-link" href="../room/roompagelist.do?page=${p-1}&query=${query}"><</a></li>

	</c:if>

	<!-- 리스트 번호 출력  -->
	<c:if test="${p+9 <= pageMaxNum}">
		<c:forEach begin="${p}" end="${p+9}" varStatus="st">
			<li class="page-item"><a class="page-link" href="../room/roompagelist.do?page=${st.current}&query=${query}">${st.current}</a></li>
		</c:forEach>
	</c:if>
	<c:if test="${p+9 > pageMaxNum}">
		<c:forEach begin="${p}" end="${pageMaxNum}" varStatus="st">
				<li class="page-item"><a class="page-link" href="../room/roompagelist.do?page=${st.current}&query=${query}">${st.current}</a></li>
		</c:forEach>
	</c:if>

	<c:if test="${pageMaxNum!=0}">
		<c:if test="${p  != pageMaxNum }">
		<li class="page-item"><a class="page-link" href="../room/roompagelist.do?page=${p+1}&query=${query}">></a></li>
		<li class="page-item"><a class="page-link" href="../room/roompagelist.do?page=${pageMaxNum}&query=${query}">>></a></li>		
		</c:if>
	</c:if>
	</ul>
	<hr>
	<button type="button" onclick="location.href='../room/room.do'">상영관추가하기</button>
	<input type='button' value='HOME' onclick="location.href='../'">
</div>
</form>
<br><br><br>

<%@ include file="../footer.jsp"%>