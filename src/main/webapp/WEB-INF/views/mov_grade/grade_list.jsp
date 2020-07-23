<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>

<br>
	<div class="container">
		<form action="grade_create.do" method="post" class="form">
			<div class="star"></div>
			<input type="hidden" name="mov_code" value="${mov_code }">
			<input type="hidden" name="mem_id" value="${s_id }">
			<div class="starRev" style="float: left;margin-top:15px">
			  <span class="starR on">별1</span>
			  <span class="starR on">별2</span>
			  <span class="starR on">별3</span>
			  <span class="starR on">별4</span>
			  <span class="starR on">별5</span>
			</div>
			<c:if test="${s_id eq null }">
				<textarea name="content" style="width: 70%;height:60px;float: left" placeholder="후기를 남기려면 로그인을 해야 합니다"></textarea>
				&nbsp;
				<input type="button" class="btn btn-outline-primary" onclick="location.href='../member/login.do'" value="로그인" style="margin-top:15px">
			</c:if>
			<c:if test="${s_id != null }">
				<textarea name="content" style="width: 70%;height:60px;float: left" placeholder="영화의 후기를 작성해주세요"></textarea>
				<input type="submit" value="작성" style="margin-top:15px">
			</c:if>
		</form>
	</div>
	
	<br><br>
	
	<div class="container" style="border-color: #ccc; ">
		<c:if test="${fn:length(list) > 0}">
			<c:forEach var="dto" items="${list}">
				<c:if test="${update_no eq dto.mov_grade_no }">
					
					<form action="grade_update.do" method="post" style="border-top: 1px solid #eee;padding: 5px 20px; ">
						<input type="hidden" name="mov_grade_no" value="${dto.mov_grade_no }">
						<input type="hidden" name="mov_code" value="${dto.mov_code }">
						<div class="up_star"></div>
						<table style="width:100%">
							<tr>
								<td style="width: 70px">${dto.mem_name }</td>
								<td style="width: 10px">|</td>
								<td>
									<div class="starUpdate" style="float: left;margin-top: 5px; float: left;">
									  <span class="starR<c:if test="${dto.grade >= 1 }"> up_on</c:if>" style="width: 15px; height: 15px">별1</span>
									  <span class="starR<c:if test="${dto.grade >= 2 }"> up_on</c:if>" style="width: 15px; height: 15px">별2</span>
									  <span class="starR<c:if test="${dto.grade >= 3 }"> up_on</c:if>" style="width: 15px; height: 15px">별3</span>
									  <span class="starR<c:if test="${dto.grade >= 4 }"> up_on</c:if>" style="width: 15px; height: 15px">별4</span>
									  <span class="starR<c:if test="${dto.grade >= 5 }"> up_on</c:if>" style="width: 15px; height: 15px">별5</span>
									</div>
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td colspan="5" style="font-size: 20px;">
									<textarea name="content" style="width: 100%;height:70px;font-size: 20px;">${dto.content }</textarea>
								</td>
								
								<c:if test="${dto.mem_id eq s_id}">
									<td style="width: 60px">
										<input type="submit" value="수정" class="btn btn-outline-success btn-sm" style="float: right; margin-right: 10px;">
									</td>
								</c:if>
								
								<c:if test="${dto.mem_id != s_id}"> <td></td> </c:if>
								
								<c:if test="${s_id eq 'master' ||  dto.mem_id eq s_id}">
									<td style="width: 50px">
										<button type="button" style="float: right;" onclick="location.href='grade_list.do?update_no=${0 }&mov_code=${dto.mov_code }'" class="btn btn-outline-secondary btn-sm">취소</button>
									</td>
								</c:if>
								
								<c:if test="${s_id != 'master' &&  dto.mem_id != s_id}"> <td></td> </c:if>
							</tr>
						</table>
						
					</form>
					<div style="clear: both;"></div>
				</c:if>
				<c:if test="${update_no != dto.mov_grade_no }">
					<div style="border-top: 1px solid #eee;padding: 5px 20px; ">
						<table style="width:100%">
							<tr>
								<td style="width: 70px">${dto.mem_name }</td>
								<td style="width: 10px">|</td>
								<td><c:forEach begin="1" end="${dto.grade }">★</c:forEach></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td colspan="5" style="font-size: 20px;">${dto.content }</td>
								
								<c:if test="${dto.mem_id eq s_id}">
									<td style="width: 60px">
										<button style="margin-right: 10px" onclick="location.href='grade_list.do?update_no=${dto.mov_grade_no}&mov_code=${dto.mov_code }'" class="btn btn-outline-info btn-sm">수정</button>
									</td>
								</c:if>
								
								<c:if test="${dto.mem_id != s_id}"> <td></td> </c:if>
								
								<c:if test="${s_id eq 'master' ||  dto.mem_id eq s_id}">
									<td style="width: 50px">
										<button onclick="deleteGrade(${dto.mov_grade_no})" class="btn btn-outline-danger btn-sm">삭제</button>
									</td>
								</c:if>
								
								<c:if test="${s_id != 'master' &&  dto.mem_id != s_id}"> <td></td> </c:if>
							</tr>
						</table>
					</div>
				</c:if>
			</c:forEach>
		</c:if>
		
		<c:if test="${fn:length(list) <= 0}">
			아직 이 영화에 평점이 없습니다 첫 후기를 남겨주세요!
		</c:if>
	</div>
	<br>
	
	
<script>
	var length = 5;
	$(".star").html('<input type="hidden" name="grade" value="'+length+'">');
	up_length = $(".up_on").length;
	$(".up_star").html('<input type="hidden" name="grade" value="'+up_length+'">');

	$('.starRev span').click(function(){
		$(this).parent().children('span').removeClass('on');
		$(this).addClass('on').prevAll('span').addClass('on');
		length = $(".on").length;
		$(".star").html('<input type="hidden" name="grade" value="'+length+'">');
	  return false;
	})

	$('.starUpdate span').click(function(){
		$(this).parent().children('span').removeClass('up_on');
		$(this).addClass('up_on').prevAll('span').addClass('up_on');
		up_length = $(".up_on").length;
		$(".up_star").html('<input type="hidden" name="grade" value="'+up_length+'">');
	  return false;
	})
	
	function deleteGrade(no){
	  var message="삭제 시 복구할 수 없습니다 \n평점을 정말 삭제하시겠습니까?";
	  if(confirm(message)){//확인 true, 취소 false
		  location.href="grade_delete.do?mov_grade_no="+no+"&mov_code=${mov_code}";
	  }
  }
</script>

${msg != null ? msg : "" }
<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>