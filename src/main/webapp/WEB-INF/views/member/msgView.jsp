<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="net.utility.Utility"%>
<%@page import="javax.mail.Transport"%>
<%@page import="java.util.Date"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Session"%>
<%@page import="net.utility.MyAuthenticator"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="java.util.Properties"%>
<%@ include file="../header.jsp"%>

<br>
<br>
<h3 class="text-center">결과</h3>
<div class="content">

	<br>
	<dl style="text-align: center">
		<dd>${msg1 != null ? img : "" }${msg1 }</dd>
		<dd>${msg2 != null ? img : "" }${msg2 }</dd>
		<dd>${msg3 != null ? img : "" }${msg3 }</dd>
	</dl>



	<p style="text-align: center">${link1 }${link2 } ${link3 }</p>


</div>





<%@ include file="../footer.jsp"%>
