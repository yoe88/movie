<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>

<style>
.on{
	background:black !important;
}
.isExists{
	background:silver !important;
}

.no {
	width:30px; height:30px; 
	float:left; 
	text-align:center; 
	border:solid 2px black; 
	background:gray; 
	color:white; 
	cursor:pointer;
}
</style>

<br><br>

<%--버튼 클릭시 카카오 페이 아닌 결제 페이지로 가게하기 ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★--%>
<div class="container" style="text-align:center">
 <form action="pay" name="yhForm" method="post" onsubmit = "return validateSeat()">
	<input type="hidden" id="mov_title" name="mov_title" value="${mov_title}">
	<input type="hidden" id="thea_name" name="thea_name" value="${thea_name}">
	<input type="hidden" id="room_name" name="room_name" value="${room_name}">
	<%--<input type="hidden" id="thea_code" name="thea_code" value="${thea_code }">--%>
	<input type="hidden" id="room_code" name="room_code" value="${room_code}">
	<input type="hidden" id="date" name="date" value="${date}">
	<input type="hidden" id="show_time" name="show_time" value="${show_time}">
	<%--<input type="hidden" id="mem_id" name="mem_id" value="${sessionScope.s_id}">--%>
	<input type="hidden" id="list" name="list">
	<input type="hidden" name="show_code" value="${param.show_code}">

 
    <div class="form-group">
성인 : 
<select name="adult" id="adult" onchange="change()">
	<option value=0>0</option>
	<option value=1>1</option>
	<option value=2>2</option>
	<option value=3>3</option>
	<option value=4>4</option>	
	<option value=5>5</option>
	<option value=6>6</option>
	<option value=7>7</option>
	<option value=8>8</option>
</select>
청소년:
<select name="youth" id="youth" onchange="change()">
	<option value=0>0</option>
	<option value=1>1</option>
	<option value=2>2</option>
	<option value=3>3</option>
	<option value=4>4</option>
	<option value=5>5</option>
	<option value=6>6</option>
	<option value=7>7</option>
	<option value=8>8</option>	
</select>
</div>
<c:if test="${s_mlev != null }">

<input type="submit" value="결제하기" class="btn btn-outline-secondary">

</c:if>
</form>

</div>


<br>
<hr>
<h5 style="color: black; text-align:center">S C R E E N</h5>
<br>
<div class="container">
	<div style="margin:auto; width:310px">
		<c:forEach var="seat" items="${seat_list}" varStatus="st">
			<div id="${seat.seat_column}${seat.seat_row}" onclick="seat(this)" class="no ${isExists[st.index] ? "isExists" : ""}"> ${seat.seat_column}${seat.seat_row} </div>
			
			<c:if test="${seat.seat_row%10 eq 0 }">
				<br><br>
			</c:if>
		</c:forEach>
	</div>
</div>



<br><br><br><br><br><br>
<!-- Footer section
================================================== -->
<script src="<c:url value="/resources/js/yh.js"/>"></script>
<%@ include file="../footer.jsp"%>