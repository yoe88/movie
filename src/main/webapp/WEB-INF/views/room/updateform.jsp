<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<title>상영관정보수정 하기위한 코드 입력창</title>
<style>
.center{
	text-align: center;
}
</style>


	<p align="center">상영관정보 검색</p>
	<form action="/movieForest/updateproc" method="get">
		<div class="center">
			<table style="margin-left: auto; margin-right: auto;">
				<tr>
					<td>영화관코드</td>
					<td><input type="text" style="text-transform: uppercase;" id="thea_code" name="thea_code"></td>
				</tr>
				<tr>
					<td>상영관코드</td>
					<td><input type="text" style="text-transform: uppercase;" id="room_code" name="room_code"></td>
				</tr>
				
			</table>
			<input type="submit" value="수정하기">
			<input type='button' value='리스트' onclick="location.href='/movieForest/roomlist.do'">
			
		</div>


	</form>
	
	<script>
	$(document).ready(function() {

	    $("#thea_code").bind("keyup", function() {

	    $( this ).val($( this ).val().toUpperCase());

	    }); 

	});
	
	$(document).ready(function() {

	    $("#room_code").bind("keyup", function() {

	    $( this ).val($( this ).val().toUpperCase());

	    }); 

	});
	</script>
<%@ include file="../footer.jsp"%>