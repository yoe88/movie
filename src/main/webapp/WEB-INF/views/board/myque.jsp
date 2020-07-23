<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ page import="java.sql.Timestamp"%>

<!-- board/list -->
<!--//http://localhost:9090/movieForest/board -->
<!-- 본문 시작 list.jsp-->


<style>
/* .center{text-align: center;} */
li {list-style: none; float: center;}
</style>
<br>

	<div class='container' align="right">
	   <form role="form" method="get">
	   <input type='button' value='게시물 작성' onclick="location.href='create.do'" class='btn btn-dark'>
	   </form>
	</div>


<!-- 검색을 해보자 -->
  <div class="text-center">
    <select name="searchType" >
      <option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
      <option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
      <option value="w"<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
      <option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
    </select>

    <input type="text" name="keyword" id="keywordInput" value="${scri.keyword}"/>

    <button id="searchBtn" type="button" class="btn btn-dark">검색</button>
    <script>
      $(function(){
        $('#searchBtn').click(function() {
          self.location = "myque" + '${pageMaker.makeQuery(1)}' + "&searchType=" + $("select option:selected").val() + "&keyword=" + encodeURIComponent($('#keywordInput').val());
        });
      });   
    </script>
    </div>
</div>


<div id = navi align="center" class="container">
	<br>
   <table class="table" >
      <thead class="thead-dark" align="center">
      
         <tr>
            <th>제목</th>
            <th>문의 유형</th>
            <th>작성자</th>
            <th>문의 열람</th>
            <th>작성시간</th>
         </tr>
      </thead>

      <c:forEach var="Com_notice" items="${list}">
         <tr onmouseover="this.style.backgroundColor='#9FB6FF'" onmouseout="this.style.backgroundColor=''">
            <td width=300 style="text-align: center;"><a
               href="onelist.do?com_no=${Com_notice.com_no}">${Com_notice.com_title}</a></td>
            <td style="text-align: center;">${Com_notice.com_type}</td>
            <td style="text-align: center;">${Com_notice.mem_id}</td>
            <td style="text-align: center;"> 
               <c:if test="${Com_notice.com_repno > 0}"> 열람 </c:if>
               <c:if test="${Com_notice.com_repno < 1}"> 미열람 </c:if> 
            </td>
            <td style="text-align: center;"><fmt:formatDate value="${Com_notice.com_readgt}" pattern="yyyy.MM.dd" /></td>
         </tr>
      </c:forEach>
   </table>
</div>

<br><br>

<%-- <!-- 페이지를 넣어보자 -->
<div class="text-center" style="margin-top:50px;">
  <ul>
    <c:if test="${pageMaker.prev}">
       <span><a href="list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></span> &nbsp;&nbsp;&nbsp;&nbsp;
    </c:if> 
    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
       <span><a href="list${pageMaker.makeSearch(idx)}">${idx}</a></span> &nbsp;&nbsp;
    </c:forEach>
    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
       &nbsp;&nbsp;&nbsp;&nbsp; <span><a href="list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></span>
    </c:if> 
  </ul>
  


</div>
 --%>



<br><br><br><br><br><br><br>


<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>