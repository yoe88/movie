<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>
<br><br>
	<div class="container" style="border-color: #ccc; ">
		<c:forEach var="dto" items="${list}">
			<table style="width:100%;border-top: 1px solid #eee;padding: 5px 20px;">
				<tr>
					<td colspan="2">
						영화 : ${dto.mov_title } &nbsp;&nbsp;|&nbsp;&nbsp; 작성자 : ${dto.mem_name } &nbsp;&nbsp;|&nbsp;&nbsp; 평점 : <c:forEach begin="1" end="${dto.grade }">★</c:forEach>
					</td>
				</tr>
				<tr>
					<td style="font-size: 20px;">${dto.content }</td>
					
					
					<td style="width: 50px">
						<button onclick="deleteGrade(${dto.mov_grade_no})" class="btn btn-outline-danger btn-sm">삭제</button>
					</td>
				</tr>
			</table>
				
		</c:forEach>
		
	</div>
<br><br><br>

<script>
	function deleteGrade(no){
	  var message="삭제 시 복구할 수 없습니다 \n평점을 정말 삭제하시겠습니까?";
	  if(confirm(message)){//확인 true, 취소 false
		  location.href="grade_delete.do?mov_grade_no="+no;
	  }
  }
</script>
<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>