<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<script type="text/javascript">
   $(document).ready(function() {
      var formObj = $("form[name='onelist']");
      //댓글
      $(".replyWriteBtn").on("click", function () {
         var formObj = $("form[name='replyForm']");
         formObj.attr("action", "replyWrite.do");
         formObj.submit();
      })
   })


</script>
<br>
<main class="container">
   <!-- 게시글  -->
   <table class="clearfix" style="width:100%">
      <thead>
         <tr class="userBoardView_con">
            <th>
            <span style="font-size: 2rem;margin: 10px">${detail.com_title}</span><br>
             <span style="font-size: 16px;color:gray;margin-left: 15px;font-weight: 1">작성자: ${detail.mem_id }
                                 작성일: <fmt:formatDate value="${detail.com_readgt}" pattern="yyyy-MM-dd hh:mm" /></span>
                               
            </th>
         </tr>
      </thead>
      <tbody>
         <tr>
            <td class="col-sm-9">
            <hr>
				
               <%--<textarea class="form-control" cols="30" rows="10"
                           style="border: none">${detail.com_cont}</textarea>--%>
               <div style="word-break: break-all">
               <div style="white-space: pre-wrap;margin-left: 15px;">${detail.com_cont}</div>
               <br><br>
               <img src="${contextPath}/resources/que/${detail.com_filename }" class="img-responsive" alt="${detail.com_filename }" style="width:auto; height:200px;">
               </div>

               

            <hr>
            </td>
         </tr>
         <tr>
            <td>
           	
            <c:if test="${detail.com_gcode=='mov'}"> <input type="submit" class="btn btn-outline-primary" value="게시물 목록" onclick="location.href='../board/movtalk'"> </c:if>
            <c:if test="${detail.com_gcode=='que'}">  <input type="submit" class="btn btn-outline-primary" value="게시물 목록" onclick="location.href='../board/que'"> </c:if>
	        <c:if test="${detail.com_gcode=='myque'}">  <input type="submit" class="btn btn-outline-primary" value="게시물 목록" onclick="location.href='../board/myque'"> </c:if>   
				<c:if test="${s_id eq detail.mem_id || (s_mlev != null && s_mlev eq 'MASTER') }">        
	               <input type="button" class="btn btn-outline-dark" value="게시물 수정"
	               		   onclick="location.href='updateView.do?com_no=${detail.com_no }&prev_url=${prev_url }'">
	               <input  type="button" value="게시물 삭제"onclick="location.href='delete.do?com_no=${detail.com_no }&prev_url=${prev_url }'"class="btn btn-outline-danger float-right">
            	</c:if>
            </td>
         </tr>
      </tbody>
      
   </table>
   <c:set var="detail" value="${detail }"></c:set>
<form name="replyForm" method="post" action="replyWrite.do" style="margin-left: -15%" >
   <input type="hidden" id="com_no" name="com_no" value="${detail.com_no}">
   <input type="hidden" id="page" name="page" value="${page}">
   <input type="hidden" id="perPageNum" name="perPageNum" value="${perPageNum}">
   <input type="hidden" id="searchType" name="searchType" value="${scri.searchType}">
   <input type="hidden" id="keyword" name="keyword" value="${scri.keyword}">
   <input type="hidden" id="prev_url" name="prev_url" value="${prev_url}">

<br>  

<!-- 댓글 작성하기  -->
<c:if test="${sessionScope.s_id!=null && prev_url ne 'myque'}">
 <div style="margin-left: 14%;font-size: 14px;">
     
      <label for="content">댓글 달기 </label><br>
      <input type="text" id="content" name="content" style="width: 50%;"/><button type="submit" class="replyWriteBtn btn btn-outline-secondary btn-sm">작성</button>
       <input type="hidden" id="mem_id" name="mem_id"  value="${sessionScope.s_id}"/> <br />
   </div>
</c:if>

<c:if test="${sessionScope.s_id==null }">
 <div style="margin-left: 14%;font-size: 14px;">
     
      <label for="content" style="font-weight:bold">댓글달기</label><br> 
      <input size="100" type="text" id="content" name="content" value="댓글을 달려면 로그인을 해주세요." readonly/>
   </div>
</c:if>

<!-- 댓글 -->

<div id="reply2" style="margin-left: 12%; font-size: 14px; width:50%; word-break:break-all;" >
<table style="width:50%;">
   <ol class="reply2">
      <c:forEach items="${reply2}" var="ComreplyList">
      <!-- 로그인 안 한 경우 댓글 수정/삭제 불가 -->
      	<c:if test="${sessionScope.s_id==null}">
	      	<hr>
	        <span style="font-weight:bold; width:80%;color:#444444;font-size: 17px">
	           ${ComreplyList.mem_id}     
	        </span>  
	        (<fmt:formatDate value="${ComreplyList.readgt}" pattern="yyyy-MM-dd HH:mm" />)
	         
	         <p style="width:70%;">${ComreplyList.content}</p>
	      </c:if>

      <!-- 로그인 한 경우 댓글 수정/삭제 가능 -->
            <c:if test="${sessionScope.s_id!=null}">
            	<hr>
               <span style="font-weight:bold; width:80%;color:#444444;font-size: 17px">
                  ${ComreplyList.mem_id}     
               </span>
               (<fmt:formatDate value="${ComreplyList.regdate}" pattern="yyyy-MM-dd HH:mm" />)
                 
                  <input type="button" class="replyUpdateBtn btn btn-outline-success btn-sm"
                     onclick='location.href="replyUpdateView?com_no=${ComreplyList.com_no}&rno=${ComreplyList.rno}&prev_url=${prev_url }"'
                     value="수정">

                  <input type="button" class="btn btn-outline-danger btn-sm"
                     onclick='location.href="replyDeleteView?com_no=${ComreplyList.com_no}&rno=${ComreplyList.rno}&prev_url=${prev_url }"'
                     value="삭제">
                             
                <p style="width:70%;">${ComreplyList.content}</p>
               
            </c:if>
      </c:forEach>
   </ol>
   </table>
</div>
</form>
</main>
<br><br>

<%@ include file="../footer.jsp"%>