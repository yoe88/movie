<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>

<h3 class="text-center"> 영화 수정 </h3>
<br>
<br>
<div class="text-center">
	<input type="button" value="영화 관리자 페이지"
		   onclick="location.href='./masterMov.do'"
		   class="btn btn-warning btn-lg">
</div>
<br>
<br>

<c:set var="mdto" value="${list }"></c:set>
<form method="post" action="./movUpdate.do"
	enctype="multipart/form-data">
	<c:set var="currTime" value='<%=new java.util.Date(new java.util.Date().getTime())%>'></c:set>
   	<c:set var="today"><fmt:formatDate value="${currTime}" pattern='yyyy-MM-dd' /> </c:set>
   	<c:set var="mopen"><fmt:formatDate value="${mdto.mov_opening }" pattern="yyyy-MM-dd" /></c:set>
	
	<input type="hidden" name="mov_code" value="${mdto.mov_code }">
	<c:if test="${mopen > today }">
		<input type="hidden" name="mov_end" value="CON" >
	</c:if>
	
	
	<table class="table table-bordered" style="width: 55%; margin: auto">
		
   		
   		<c:if test="${mopen <= today}">
			<tr>
				<th class="table-active"> 영화 종료 체크 </th>
				<td>
					<p> 영화 상영 종료시 체크박스 체크 해주세요 </p>
					<input type="checkbox" name="mov_end" value="END" style="zoom:2.0;" <c:if test="${mdto.mov_end eq 'END'}">  checked </c:if>> 상영 종료
					&nbsp;&nbsp;
					<input type="checkbox" name="mov_end" value="CON" style="zoom:2.0;" <c:if test="${mdto.mov_end eq 'CON'}">  checked </c:if>> 상영중
				</td>
			</tr>
		</c:if>
		<tr>
			<th class="table-active">제목</th>
			<td>
				<input type="text" name="mov_title" size="50"
					   class="form-control" value="${mdto.mov_title }">
			</td>
		</tr>
		<tr>
			<th class="table-active">영화 소개</th>
			<td>
				<textarea id="mov_content" name="mov_content" class="form-control">
					 ${mdto.mov_content }
				</textarea> 	
				<script type="text/javascript">
					 CKEDITOR.replace("mov_content"
					                , {height: 200                                                  
					                 });
					 
				</script>
				
			</td>
		</tr>
		<tr>
			<th class="table-active">감독</th>
			<td>
				<input type="text" name="mov_director" size="50"
					   class="form-control" value="${mdto.mov_director }">
			</td>
		</tr>
		<tr>
			<th class="table-active">장르</th>
			<td>
				<input type="text" name="mov_genre" size="50"
					   class="form-control" value="${mdto.mov_genre }">
			</td>
		</tr>
		<tr>
			<th class="table-active">영화등급</th>
			<td>
				<c:set var="rate" value="${mdto.mov_rate }"></c:set>
				<select class="form-control" id="mov_rate" name="mov_rate">
				    <option <c:if test="${rate eq 0}">  selected </c:if> value=0> 전체 </option>
				    <option <c:if test="${rate eq 12}"> selected </c:if> value=12> 12 </option>
				    <option <c:if test="${rate eq 15}"> selected </c:if> value=15> 15 </option>
				    <option <c:if test="${rate eq 19}"> selected </c:if> value=19> 19 </option>
			  	</select>
  			</td>
		</tr>
		<tr>
			<th class="table-active">러닝타임</th>
			<td>
				<input type="number" name="mov_time" value="${mdto.mov_time }"> 분
			</td>
		</tr>
		<tr>
			<th class="table-active">개봉일 <i class="fa fa-calendar bigger-110"></i>
			</th>
			<td>
				<input type="text" id="datepicker" name="mov_opening" 
					   class="form-control" value="<fmt:formatDate value="${mdto.mov_opening }" pattern="yyyy-MM-dd" />">
			</td>
			
			<script>
				$("#datepicker").datepicker({dateFormat: 'yy-mm-dd', //날짜 표시 형식 설정
						showOtherMonths: true, //이전 달과 다음 달 날짜를 표시
						showMonthAfterYear:true, //연도 표시 후 달 표시
						//changeYear: true, //연도 선택 콤보박스
						//changeMonth: true, //월 선택 콤보박스
						showOn: "both", //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시
						yearSuffix: "년", //연도 뒤에 나오는 텍스트 지정
						monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
						monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
						dayNamesMin: ['일','월','화','수','목','금','토'],
						dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
						minDate: "-1M", // -1D:하루전, -1M:한달전, -1Y:일년전
						//maxDate: "+1M", // +1D:하루후, -1M:한달후, -1Y:일년후
						//buttonImage: "image/calendar.gif", //버튼에 띄워줄 이미지 경로
						//buttonImageOnly: true, //디폴트 버튼 대신 이미지 띄워줌
						//buttonText: "선택", //버튼 마우스오버 시 보이는 텍스트
						
						
				});
			</script>

		</tr>
		<tr>
			<th class="table-active">주연배우</th>
			<td><input type="text" name="mov_mainactor" size="50"
					   class="form-control" value="${mdto.mov_mainactor }"></td>
		</tr>
		<tr>
			<th class="table-active">조연배우</th>
			<td><input type="text" name="mov_supportactor" size="50"
					   class="form-control" value="${mdto.mov_supportactor }"></td>
		</tr>
		<tr>
			<th class="table-active">포스터</th>
			<td class="text-left">
				<input type="file" id="select_poster" name="posterMF" >
				<div class="poster" style="margin:20px 0;"> 
					<img src="" /> 
				</div>
			</td>
		</tr>
		<tr>
			<th class="table-active">스틸컷</th>
			<td class="text-left">
				<p> 스틸컷 3개 이상 선택!! </p>
				<input multiple="multiple" id="select_still" type="file" name="stillMF">
				<div class="stills" style="margin:20px 0;">  </div>
			</td>
		</tr>
	</table>
	<br>
	<div class="text-center">
		<input type="submit" value="수정" class="btn btn-outline-primary">
		&nbsp;&nbsp;&nbsp; 
		<input type="reset" value="취소" class="btn btn-outline-success">
	</div>
</form>

<br>
<br>
<br>
<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>



<script>
	//포스터 미리보기

	
	
	//포스터 미리보기 다른방법 이거로 해도 됨
	$("#select_poster").change(function(){
		if( this.files && this.files[0] ){
			var reader = new FileReader;
			reader.onload = function(data) {
				$(".poster img").attr("src", data.target.result).width(200);
			}
			reader.readAsDataURL(this.files[0]);				
		}
	});
	
	

	//스틸컷 미리보기
	var still_imgs = [];
	$(document).ready(function(){
		$("#select_still").on("change", Stills);
		
	});
	
	function fileUploadAction() {
		console.log("fileUploadAction");
		$("#select_still").trigger("click");

	}
	
	function Stills(e) {
		//이미지 정보들 초기화
		still_imgs = [];
		$(".stills").empty();
		
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		
		var index = 0;
		filesArr.forEach(function(f) {
			if( !f.type.match("image.*") ) {
				alert("확장자는 이미지 확장자만 가능합니다.");
				
				return;
			}
			
			still_imgs.push(f);
			
			var reader = new FileReader();
			reader.onload = function(e) {
				var html = "<img src=\"" + e.target.result + "\" data-file='"+ f.name +"' style='width:200px; padding:10px 15px 10px 15px ;'>";
				$(".stills").append(html);
				
				index++;
				
			}
			reader.readAsDataURL(f);
			
		});
	}
	
	
</script>

