<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>
<br><br>
<div class="text-center">
	<input type="button" value="영화 등록"
		   onclick="location.href='./movInsert.do'"
		   class="btn btn-primary btn-lg">
</div>

<br>

<div class="container">
	<div class="row">
		<table class="table table-hover">
			<thead>
			<tr>
				<th> 영화 제목 </th>
				<th> 감독 </th>
				<th> 장르 </th>
				<th> 영화등급 </th>
				<th> 러닝타임 </th>
				<th> 개봉일 </th>
				<th> 영화등록일 </th>
				<th> 영화 상영 종료 여부 </th>
				<th> 수정 </th>
				<th> 삭제 </th>
				<th> 스틸컷 보기 </th>
			</tr>
			</thead>
			<tbody>
			<!-- 반복구간 -->
			<c:forEach var="mdto" items="${list }">

				<tr>
					<td> ${mdto.mov_title } </td>
					<td> ${mdto.mov_director } </td>
					<td> ${mdto.mov_genre } </td>
					<td>
						<c:set var="rate" value="${mdto.mov_rate }" ></c:set>
						<c:choose>
							<c:when test="${ rate == 0}"> 전체 </c:when>
							<c:otherwise> ${rate } </c:otherwise>
						</c:choose>
					</td>
					<td> ${mdto.mov_time } 분 </td>
					<td> <fmt:formatDate value="${mdto.mov_opening }" pattern="yyyy.MM.dd" /> </td>
					<td> <fmt:formatDate value="${mdto.mov_regdate }" pattern="yyyy.MM.dd" /> </td>
					
					<c:set var="currTime" value='<%=new java.util.Date(new java.util.Date().getTime())%>'></c:set>
                    <c:set var="today"><fmt:formatDate value="${currTime}" pattern='yyyy-MM-dd' /> </c:set>
                    <c:set var="mopen"><fmt:formatDate value="${mdto.mov_opening }" pattern="yyyy-MM-dd" /></c:set>
                    <td>  
	                    <c:if test="${mdto.mov_end eq 'CON' && mopen <= today }" >                      	  상영중 </c:if>
	                    <c:if test="${mdto.mov_end eq 'CON' && mopen > today}" > <span style="color:blue;"> 상영예정 </span> </c:if>
	                    <c:if test="${mdto.mov_end eq 'END' }" > <span style="color:orange;">             	  상영종료 </span> </c:if>
                    </td>
					<td>
						<input type="button" value="영화 수정"
							   onclick="location.href='./movUpdate.do?mov_code=${mdto.mov_code }'"
							   class="btn btn-info">
					</td>
					<td>
						<input type="button" value="영화 삭제"
							   onclick="location.href='./movDelete.do?mov_code=${mdto.mov_code }'"
							   class="btn btn-danger">
					</td>
					<td>
						<input type="button" value="스틸컷 보기"
							   onclick="location.href='./masterStill.do?mov_code=${mdto.mov_code }'"
							   class="btn btn-success">
					</td>
				</tr>

			</c:forEach>
			</tbody>
		</table>

	</div>
</div>
<br><br><br>
<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>
