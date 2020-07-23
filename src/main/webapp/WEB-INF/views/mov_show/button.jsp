<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- ================================Header section================================ -->

	<h3>BUTTON.jsp</h3>
	<input type="button" value="예매페이지" onclick="location.href='res_mov.do'">
	<input type="button" value="영화관페이지" onclick="location.href='res_thea.do'">
	<input type="button" value="상영표 리스트(관리자용)" onclick="location.href='show_list.do'">
	
<!-- ================================Footer section================================ -->
<%@ include file="../footer.jsp"%>