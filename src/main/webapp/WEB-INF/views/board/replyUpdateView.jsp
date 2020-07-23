<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ page import="java.sql.Timestamp"%>
	<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[name='updateForm']");
			
			$(".cancel_btn").on("click", function(){
				location.href = "onelist.do?com_no=${replyUpdate.com_no}"
					   + "&page=${scri.page}"
					   + "&perPageNum=${scri.perPageNum}"
					   + "&searchType=${scri.searchType}"
					   + "&keyword=${scri.keyword}";
			})
			
		})
		
	</script>

<br><br>	
		<div id="root" style="margin:auto; width:50%;">
			
			<section id="container">

				<form name="updateForm" role="form" method="post" action="replyUpdate">
					<input type="hidden" name="com_no" value="${replyUpdate.com_no}" readonly="readonly"/>
					<input type="hidden" id="rno" name="rno" value="${replyUpdate.rno}" />
					<input type="hidden" id="page" name="page" value="${scri.page}"> 
					<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
					<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
					<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}"> 
					<table>
						<tbody>
							<tr>
								<td>
									<label for="content">댓글 내용</label>
									<input type="text" id="content" name="content" value="${replyUpdate.content}" size="80">
								</td>
							</tr>	
							
						</tbody>			
					</table>
					<div>
						<button type="submit" class="update_btn">저장</button>
						<button type="button" class="cancel_btn">취소</button>
					</div>
				</form>
			</section>
		</div>

<br><br><br><br><br><br><br>


<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>