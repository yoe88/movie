<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- movieforest -->

<br>
	<form method="post" action="./createResult.do"
		enctype="multipart/form-data">
		<input type="hidden" name='mem_id' id="mem_id" value="${sessionScope.s_id}" size='50'>
		<input type='hidden' name='com_grpno' value=2>
		<input type="hidden" name="prev_url" value="${prev_url }">
		
		<table class="table table-bordered" style="width: 55%; margin: auto">
			<c:if test="${prev_url == 'que' || prev_url == 'myque'}">
				<tr>
					<th>문의 유형</th>
					<td>
						<select class="form-control" id="com_type" name="com_type">
						    <option value="영화관"> 영화관 </option>
						    <option value="영화"> 영화 </option>
						    <option value="예매/결제"> 예매/결제 </option>
						    <option value="멤버쉽"> 멤버쉽 </option>
						    <option value="기타"> 기타 </option>
					  	</select>
						
					</td>
				</tr>
			</c:if>
			<tr>
				<th>제목</th>
				<td><input type='text' name='com_title' size='50'></td>
			</tr>
			<c:if test="${prev_url == 'movtalk'}" >
				<input type="hidden" name="com_gcode" value="mov">
				<tr>
					<th>내용</th>
					<td> 
						<textarea id="com_cont" name="com_cont"></textarea> 
						<script type="text/javascript">
							 CKEDITOR.replace("com_cont"
							                , {height: 200                                                  
							                 });
							 
						</script>
					</td>
				</tr>
			</c:if>
			<c:if test="${prev_url == 'que' || prev_url == 'myque'}">
				<input type="hidden" name="com_gcode" value="que">
				<tr>
					<th>내용</th>
					<td>
						<textarea name="com_cont" rows="5" cols="30"></textarea>
					</td>
				</tr>
				
			</c:if>
			<tr>
				<th>파일업로드</th>
				<td>
					<input type='file' id="select_que" name='filenameMF' size='100'>
					<div class="que"> 
						<img src=""  style="margin:20px 0;"/> 
					</div>
				</td>    
			</tr>
			
		</table>

	<br>
		<div class='bottom text-center'>
			<input type='submit' value='등록' class="btn btn-outline-secondary"> 
			
			<c:if test="${prev_url == 'que'}" >
				<input type='button' value='취소' onclick="location.href='./que'" class="btn btn-outline-danger"> 
			</c:if>
			<c:if test="${prev_url == 'myque'}" >
				<input type='button' value='취소' onclick="location.href='./myque'" class="btn btn-outline-danger"> 
			</c:if>
			<c:if test="${prev_url == 'movtalk'}" >
				<input type='button' value='취소' onclick="location.href='./movtalk'" class="btn btn-outline-danger"> 
			</c:if>
			
		</div>

	</form>
	
	<br>

<%@ include file="../footer.jsp"%>


<script>
	$("#select_que").change(function(){
		if( this.files && this.files[0] ){
			var reader = new FileReader;
			reader.onload = function(data) {
				$(".que img").attr("src", data.target.result).width(150);
			}
			reader.readAsDataURL(this.files[0]);				
		}
	});
</script>