<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta charset="UTF-8">
<title>상영관정보 수정창</title>
<style>
.center{
	text-align: center;
}
</style>


<p class="center">상영관정보 수정창</p>
<hr>

<c:if test="${roominfo!=null}">
<div class="center">
<form method="get"
      action="/movieForest/room/roomupdatesuc.do">

<input type="hidden" id="thea_code" name="thea_code" value="${roominfo.thea_code}">
<input type="hidden" id="thea_name" name="thea_name" value="${roominfo.thea_name}">
<input type="hidden" id="room_code" name="room_code" value="${roominfo.room_code}">
<input type="hidden" id="room_maximum" name="room_maximum" value="${roominfo.room_maximum}">






상영관정보 : <input type="text" id="room_type" name="room_type" value="${roominfo.room_type}">

<br><br>

<input type="submit" value="수정하기">
<button type="button" onclick="location.href='../room/roompagelist.do'">리스트</button>

</form>
</div>
</c:if>

<c:if test="${roominfo==null }">
<div class="center">
	수정할 데이터 없음 <br>
	<button type="button" onclick="location.href='../room/roompagelist.do'">리스트</button>
</div>
</c:if>




<%@ include file="../footer.jsp"%>