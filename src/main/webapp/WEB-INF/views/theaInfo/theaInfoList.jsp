<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<%@ include file="../header.jsp"%>
            <style>
.font {
	font-size: 20px;
}

.center {
	text-align: center;
}
</style>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>영화관 정보</title>

    <%-- 부트스트랩 설정 --%>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>--%>

    <%-- 아이콘  설정--%>
    <script src="https://kit.fontawesome.com/9766199556.js" crossorigin="anonymous"></script>
</head>

<main>
    <section class="container">
<br><br>
        <div>

<p class="font center">영화관 리스트</p>
        </div>

        <!--검색창-->
        <div class="text-right my-3 float-right">
            <form action="">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <select class="form-control" name="f">
                            <option value="theaCode" ${f == "thea_code" ? "selected" : ""}>영화관코드</option>
                            <option value="theaName" ${f == "thea_name" ? "selected" : ""}>영화관이름</option>
                        </select>
                    </div>
                    <input type="text" class="form-control" name="q" value="${param.q}">
                    <div class="input-group-append">
                        <button type="submit" class="form-control btn-purple"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </form>
        </div>

        <!--게시글목록-->
        <div class="table-responsive">
            <table class="table tbl-board">
                <thead>
                <tr class="text-center">
                    <th class="">영화코드</th>
                    <th class="">영화관이름</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${list != null}">
                    <c:forEach items="${list}" var="t">
                        <tr class="text-center">
                            <td>${t.thea_code}</td>
                            <td class="tbl-title"><a href="${contextPath}/theainfo/${t.thea_code}">${t.thea_name}</a></td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${listTotalCount == 0}">
                    <tr class="text-center"><td colspan="2">검색된 결과가 없습니다.</td></tr>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="clearfix">
            <div class="float-left">
                <a href="${contextPath}/theainfo/new"><button class="btn btn-outline-danger">글쓰기</button></a>
            </div>
            <h5 class="float-right"><span class="current-page-num">${p}</span>/<span class="font-weight-normal">${pageMaxNum}</span></h5>
        </div>

    </section>

    <section class="container my-2 text-center clearfix">

        <nav class="pager">
            <ul class="pagination">
                <c:if test="${p != 1}">
                    <li class="page-item"><a class="page-link" href="?f=${f}&q=${param.q}&p=1"><i class="fas fa-angle-double-left"></i></a></li>
                    <li class="page-item"><a class="page-link" href="?f=${f}&q=${param.q}&p=${p-1}"><i class="fas fa-angle-left"></i></a></li>
                </c:if>
                <c:forEach begin="${p-2 > 0 ? p-2 : 1}" end="${p+2}" varStatus="st">
                    <c:if test="${st.current <= pageMaxNum}">
                        <li class="page-item ${p==st.current ? "active" : ""}">
                            <a class="page-link" href="<c:if test="${st.current == p}">javascript:;</c:if>
													<c:if test="${st.current != p}">?f=${f}&q=${param.q}&p=${st.current}</c:if> ">${st.current}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <c:if test="${p != pageMaxNum and pageMaxNum !=0}">
                    <li class="page-item"><a class="page-link" href="?f=${f}&q=${param.q}&p=${p+1}"><i class="fas fa-angle-right"></i></a></li>
                    <li class="page-item"><a class="page-link" href="?f=${f}&q=${param.q}&p=${pageMaxNum}"><i class="fas fa-angle-double-right"></i></a></li>
                </c:if>
            </ul>
        </nav>
    </section>
    <br><br>
</main>
<%@ include file="../footer.jsp"%>