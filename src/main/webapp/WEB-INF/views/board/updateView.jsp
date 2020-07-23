<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<br>

<div id="root">
	<section id="container">
		<form name="update" role="form" method="post"
			action="updateView.do?com_no=${detail.com_no }"
			enctype="multipart/form-data">
			<input type="hidden" name="com_no" value="${detail.com_no}" class="form-control"> 
			<table class="table table-bordered" style="width: 55%; margin: auto">
				<c:if test="${detail.com_gcode eq 'que' }">
					<tr>
						<th>문의 유형</th>
						<td>
							<select class="form-control" id="com_type" name="com_type">
								<option <c:if test="${detail.com_type eq '영화관'}">  selected </c:if> value="영화관"> 영화관 </option>
								<option <c:if test="${detail.com_type eq '영화'}">  selected </c:if> value="영화"> 영화 </option>
								<option <c:if test="${detail.com_type eq '예매/결제'}">  selected </c:if> value="예매/결제"> 예매/결제 </option>
								<option <c:if test="${detail.com_type eq '멤버쉽'}">  selected </c:if> value="멤버쉽"> 멤버쉽 </option>
								<option <c:if test="${detail.com_type eq '기타'}">  selected </c:if> value="기타"> 기타 </option>
							</select>
								
						</td>
					</tr>
				</c:if>
				<tr>
					<th>제목</th>
					<td> <input type="text" id="com_title" name="com_title" value="${detail.com_title}" > </td>
				</tr>

				<c:if test="${detail.com_gcode eq 'mov'}" >
					<tr>
						<th>내용</th>
						<td> 
							<textarea id="com_cont" name="com_cont">${detail.com_cont}</textarea> 
							<script type="text/javascript">
								 CKEDITOR.replace("com_cont"
								                , {height: 200                                                  
								                 });
										 
							</script>
						</td>
					</tr>
				</c:if>
				<c:if test="${detail.com_gcode eq 'que'}" >
					<tr>
						<th>내용</th>
						<td>
							<textarea name="com_cont" rows="5" cols="30">${detail.com_cont}</textarea>
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
			<div class="text-center">
				<input type="submit" value="저장" class="btn btn-outline-success update_btn"> 
				<input type="button" class="cancel_btn btn btn-outline-danger"
					onclick="location.href='onelist.do?com_no=${detail.com_no }'"
					value="취소">
			</div>
		</form>
	</section>
	
</div>

<br><br><br><br><br>
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