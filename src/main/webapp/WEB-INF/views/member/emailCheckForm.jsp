<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>emailCheckForm.jsp</title>
</head>
<body>
<div style="text-align:center">
	<h3> 이메일 중복확인 </h3>
	<form method="post"
		  action="emailcheckpro.do"
		  onsubmit="return blankCheck(this)">
	이메일:
	<input type="text" name="mem_email"
		   maxlength="20" autofocus>
	<input type="submit" value="중복확인">
	</form>
	
	
	<!-- 이메일 주소형식 유효한지? (정규표현식 사용하기)  -->
	<script>
	function blankCheck(f) {
	
	var email=f.mem_email.value;
	email=email.trim();
	var reg_email=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;   	  
	if(reg_email.test(email) == false) {  
	    alert("잘못된 이메일 형식입니다"); 
	    //f.email.focus(); //커서 생성
	    return false;
	} //if end
	return true;
	} //blankCheck(f) end
	
	
	
	</script>
</div>
</body>
</html>