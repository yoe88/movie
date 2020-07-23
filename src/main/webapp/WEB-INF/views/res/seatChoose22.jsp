<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>

<style>
.on {
	background:pink !important;
	border: solid 2px green !important;
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

<script>
	'use strict'
	
	let currentCount = 0;
	var counts = 0;
	var countch = 0;
	var length = 0;
	var list = "";
	//select 값 가져오기
	function change(){
		
		counts = Number($("#s option:selected").val());
		//alert(counts);
		
		countch = Number($("#ch option:selected").val());
		//alert(countch);

		//alert("if밖"+(counts+countch));
		if(counts+countch > 8){
			//alert("if안"+(counts+countch));

			alert("최대인원은 8명입니다");
			
			$("#s option:eq(0)").prop("selected", true);
			$("#ch option:eq(0)").prop("selected", true);	
		}
	}
	
	
	
	
	//클릭한 div에 add class로 class 넣어서 구분 해주고
	//선택한 div length로 세서 가져오기
	function seat(a,div){  //div = 본인
		//alert(a);
		if(div.classList.contains('on'))
			return;
		
		//var classck = $("#" + a).attr('class');
		//alert("classck : " + classck.indexOf("on"));
		
		if( classck.indexOf("on") == -1 ){
			//클래스 추가
			list += a + ",";

			$("#" + a).addClass('on');
			$(".choose").html('<input type="hidden" name="choose" value="'+list+'">');
			currentCount++;
		}else{
			//클래스 삭제
			list = list.replace(a + ",","");

			$("#" + a).removeClass('on');			
			$(".choose").html('<input type="hidden" name="choose" value="'+list+'">');
			currentCount--;
			
		}
		
		//length = $(".on").length;
		//alert("length : "+length);
		
		//counts + countch 더한거랑 legnth 비교해서 
		//1.
		//counts+countch > length면 alert 띄워주기
		console.log(counts+countch);
		console.log(currentCount);
		if(counts+countch< currentCount){

			alert("인원보다 많이 선택되었습니다.");

			list = list.replace(a + ",","");
			//마지막 클래스 삭제해주기
			$("#" + a).removeClass('on');
			$(".choose").html('<input type="hidden" name="choose" value="'+list+'">');
		}
		$(".counts").html('<input type="hidden" name="counts" value="'+counts+'">');
		$(".countch").html('<input type="hidden" name="countch" value="'+countch+'">');
		

		
		
		//2.
		//counts+countch < length면 예매 버튼 못 넘어가게
		

	
	}//seat end
	function count(){
		if(counts+countch > length){

			alert("인원보다 좌석이 적게 선택되었습니다, 다시 확인해주세요");
			return false;
		}
		if(counts+countch==0){
			alert("인원을 선택해주세요.");
			return false;
		}
		return true;
	}
    /* mav.addObject("room_code", room_code);
    mav.addObject("thea_name", thea_name);
    mav.addObject("room_name", room_name);
    mav.addObject("thea_code", thea_code);
    mav.addObject("show_time", show_time); */
</script>

<div class="container" style="text-align:center">
 <form action="./resseat.do" onsubmit = "return count()">
 <input type="hidden" id="show_code" name="mov_title" value="${mov_title}">
 <input type="hidden" id="thea_name" name="thea_name" value="${thea_name}">
<input type="hidden" id="room_name" name="room_name" value="${room_name}">
<input type="hidden" id="thea_code" name="thea_code" value="${thea_code }">
<input type="hidden" id="room_code" name="room_code" value="${room_code}">
<input type="hidden" id="date" name="date" value="${date}">
<input type="hidden" id="show_time" name="show_time" value="${show_time}">
<input type="hidden" id="mem_id" name="mem_id" value="${sessionScope.s_id}">
 
    <div class="form-group">
성인 : 
<select name="s" id="s" class="s" onchange="change()">
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
<select name="ch" id="ch" class="ch" onchange="change()">
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
<div class="choose"></div>
<div class="counts"></div>
<div class="countch"></div>
</div>
<c:if test="${s_mlev != null }">
<input type="submit" value="예매" class="btn btn-outline-primary btn-sm">

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
			<div id="${seat.seat_column}${seat.seat_row}" onclick="seat('${seat.seat_column}${seat.seat_row}',this)" class="no ${isExists[st.index] ? "on" : ""}"> ${seat.seat_column}${seat.seat_row} </div>
			
			<c:if test="${seat.seat_row%10 eq 0 }">
				<br><br>
			</c:if>
		</c:forEach>
	</div>
</div>



<br><br><br><br><br><br>
<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>