<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%> 

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<meta charset="UTF-8">
<title>${title}</title>


<body>
<p align="center" >${title}</p>
<form align="center">
${msg} <hr>
${msg1} 
${msg2} 
${msg3} 
</form>
<br>
<%@ include file="../footer.jsp"%>