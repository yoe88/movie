<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>idCheckForm.jsp</title>
</head>
<body>
<div style="text-align:center">
	<h3> 아이디 중복확인 </h3>
	<form method="post"
		  action="idcheckpro.do"
		  onsubmit="return blankCheck(this)">
	아이디:
	<input type="text" name="mem_id"
		   maxlength="10" autofocus>
	<input type="submit" value="중복확인">
	</form>
	
	
	<script>
	function blankCheck(f) {
		var id=f.mem_id.value;
		id=id.trim();
		if (id.length<5){
			alert("아이디는 5글자 이상 입력해 주세요");
			return false;
		} //if end
		return true;
	} //blankCheck(f) end
	
	
	
	</script>
</div>
</body>
</html>