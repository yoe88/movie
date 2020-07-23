<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div style="text-align:center">
<br>
<h3> 결과 </h3>
      <c:if test="${cnt == 1 }">
      <p>이미 사용중인 이메일 입니다.</p>
       <a href="javascript:history.go(-1)">[돌아가기]</a>
      </c:if>
      
    <c:if test="${cnt == 0 }">  
      <p>입력하신 ${mem_email} 는 사용가능한 이메일입니다. </p>
      <input type="button" value="사용하기" onclick="apply()">
    </c:if>
</div>

<script>
	function apply(email){
		//alert(id);
		//부모창 opener
		opener.document.memberForm.mem_email.value="${mem_email}";
		window.close();
	}
</script>





