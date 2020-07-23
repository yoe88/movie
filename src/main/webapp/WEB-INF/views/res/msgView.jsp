
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>


<div class="title">알림</div>
<div class="content">
	<dl>
		<dd>${msg1 != null ? img : "" }${msg1 }</dd>
		<dd>${msg2 != null ? img : "" }${msg2 }</dd>
		<dd>${msg3 != null ? img : "" }${msg3 }</dd>
		<dd>${msg4 != null ? img : "" }${msg4 }</dd>
	</dl>
	<p>${link1 } ${link2 } ${link3 }</p>
</div>


<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>