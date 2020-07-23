<%@ page contentType="text/html; charset=UTF-8" %> 

<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>

<br><br>
<h3 class="text-center"> 알림 </h3>

<br><br>
<div class="content text-center">
	<p>
		${link1 } ${link2 } ${link3 }
	</p>
	<br>
	<dl>
		<dd> ${msg1 } ${msg1 != null ? img : "" } </dd>
		<dd> ${msg2 } ${msg2 != null ? img : "" }</dd>
		<dd> ${msg3 } ${msg3 != null ? img : "" }</dd>
	</dl>
</div>

<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>
