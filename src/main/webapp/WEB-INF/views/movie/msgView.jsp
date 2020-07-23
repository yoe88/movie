<%@ page contentType="text/html; charset=UTF-8" %> 

<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>

<br>
<div class="content text-center">

	<dl>
		<dd> 
			${msg1 } ${msg2 } ${msg3 } <br><br>  
			${link1 } ${link2 } ${link3 } <br><br> 
			${msg1 != null ? img : "" } 
		</dd>
	</dl>
	<br>
</div>

<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>
