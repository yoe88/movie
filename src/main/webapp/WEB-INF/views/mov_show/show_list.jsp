<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>
	<br>
	<div style="margin:auto; width: 50%;">
	<select name="thea_name" onchange="location.href=this.value"  >
		<c:forEach var="thea" items="${thea }">
	    	<option value="show_list.do?thea_code=${thea.thea_code }&date=${date }" <c:if test="${thea_code eq thea.thea_code}">selected</c:if>> ${thea.thea_name }</option>
		</c:forEach>
	</select>
	<input type="text" name="date" id="date" size="12" value="${date}" onchange="date_choose(this.value)"/>
	<button onclick="location.href='show_create.do'">상영시간 추가</button>
</div>
	<br>
	<form style="margin:auto; width: 50%;">
		<table class="table">
			<tr>
				<td>영화이름</td>
				<td>영화등급</td>
				<td>상영관</td>
				<td>상영시간</td>
				<td>상영종류</td>
				<td>수정</td>
				<td>삭제</td>
			</tr>
			<c:if test="${fn:length(list) > 0}">
				<c:forEach var="dto" items="${list}">
					<tr>
						<td>${dto.mov_title }</td>
						<td>${dto.mov_rate }</td>
						<td>${dto.room_name }</td>
						<td>${dto.show_time }</td>
						<td>${dto.room_type }</td>
						<td>
							<input type="button" value="수정" onclick="location.href='show_update.do?show_code=${dto.show_code}'" class="btn btn-outline-info btn-sm">
						</td>
          				<td>
          					<input type="button" value="삭제" onclick="location.href='show_delete.do?show_code=${dto.show_code}'" class="btn btn-outline-danger btn-sm">
          				</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(list) <= 0}">
				<tr>
					<td colspan="7" style='text-align: center;'>이 날의 상영예정이 없습니다!</td>
				</tr>
			</c:if>
			
		</table>
	</form>
	
	
	<script>
	$(function() {
	  $( "#date" ).datepicker({
	    dateFormat: 'yy-mm-dd',
	    prevText: '이전 달',
	    nextText: '다음 달',
	    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    dayNames: ['일','월','화','수','목','금','토'],
	    dayNamesShort: ['일','월','화','수','목','금','토'],
	    dayNamesMin: ['일','월','화','수','목','금','토'],
	    showMonthAfterYear: true,
	    changeMonth: true,
	    changeYear: true,
	    yearSuffix: '년'
	  });
	});
	
	function date_choose(da) {
		location.href="show_list.do?thea_code=${thea_code}&date=" + da;
	}
	</script>
<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>