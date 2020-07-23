
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>


<form method="post" action="show_update.do">
	<input type="hidden" name="show_code" value="${dto.show_code }">
	<table class="table">
		<tr>
		<!-- 영화리스트를 가져오는 것은 상영정보 테이블의 일이 아니니, 임시로 텍스트로 채워넣음 -->
			<th>영화</th>
			<td>
				<select name="mov_code">
					<c:forEach var="movie" items="${nowMovie }">
						<option value="${movie.mov_code }" <c:if test="${dto.mov_code eq movie.mov_code}">selected</c:if>>${movie.mov_title }</option>
					</c:forEach>
				</select>
			</td>
		<!-- ====================================================== -->
		</tr>
		<tr>
		<!-- 영화관리스트를 가져오는 것은 상영정보 테이블의 일이 아니니, 임시로 텍스트로 채워넣음 -->
			<th>영화관</th>
			<td>
				<select name="thea_code">
					<c:forEach var="thea" items="${thea }">
						<option value="${thea.thea_code }" <c:if test="${thea_code eq thea.thea_code}">selected</c:if>>${thea.thea_name}</option>
					</c:forEach>
				</select>
			</td>
		<!-- ======================================================= -->
		</tr>
		<tr>
		<!-- 각 영화관의 상영관 리스트도 가져올 수 있으면 가져오기 -->
			<th>상영관</th>
			<td><input type="text" name="room_code" value="${dto.room_code}" placeholder="ex.1관 -> 1" value="${room_code}"></td>
		</tr>
		<tr>
			<th>상영날짜</th>
			<td><input type="text" id="datepicker" class="form-control col-xs-12" name="date" value="${dto.show_date}"/></td>
		</tr>
		<tr>
			<th>상영시간</th>
			<td><input type="time" name="time" value="${dto.show_time }"></td>
		</tr>
	</table>

	<div class='bottom'>
		<input type='submit' value='수정'> 
		<input type='button' value='목록' onclick="location.href='show_list.do'">
	</div>
</form>
<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>

<script>
$("#datepicker").datepicker({
	dateFormat: 'yy-mm-dd', //날짜 표시 형식 설정
	showOtherMonths: true, //이전 달과 다음 달 날짜를 표시
	showMonthAfterYear:true, //연도 표시 후 달 표시
	changeMonth: true, //월 선택 콤보박스
	yearSuffix: "년", //연도 뒤에 나오는 텍스트 지정
	monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
	monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	dayNamesMin: ['일','월','화','수','목','금','토'],
	dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
	minDate: "0D", // -1D:하루전, -1M:한달전, -1Y:일년전
	maxDate: "+1M", // +1D:하루후, -1M:한달후, -1Y:일년후
	});

</script>