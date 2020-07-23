<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
	<div class="container">
	<br><br>
	   <c:set var="detail" value="${detail }"></c:set>
	   <form method="post" action="delete.do">
	      <input type="hidden" name="com_no" value="${detail.com_no }">
	      <input type="hidden" name="prev_url" value="${prev_url }">
	      <table class="table">
			<tr>
				<td style="text-align: center;border: 0px">
	      			<img alt="warning" src="${contextPath}/resources/images/warning.png" style="width: 50px">
					<hr style="width: 300px;border-color: #666666"><br>
					게시물을 삭제하시겠습니까?<br>
					관련 파일도 전부 삭제됩니다
					<br><br><hr style="width: 300px;border-color: #666666">
				</td>
			</tr>
		 </table>
	      <div class='bottom' style="text-align: center;">
	         <input type='submit' value="삭제진행" class="btn btn-outline-success"> 
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
   </div>
   
   <br><br><br><br><br><br><br><br>
<%@ include file="../footer.jsp"%>