<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<%@ include file="../header.jsp"%>
<html>
<head>
    <title>상세보기</title>

    <%-- 부트스트랩 설정 --%>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>--%>

    <script src="https://kit.fontawesome.com/9766199556.js" crossorigin="anonymous"></script>

    <style>
        .overlay_info{
            background: white;
            border: 1px solid black;

            padding: 2px 5px;
        }
        .overlay_info .text::after {
            content: "";
            position: absolute;
            top: 100%;
            left: 50%;
            margin-left: -5px;
            border-width: 5px;
            border-style: solid;
            border-color: black transparent transparent transparent;
        }


    </style>

</head>
<body>
<main>
<br><br>
    <section class="container">
        <div class="clearfix">
            <h1 class="float-left">* detail *</h1>
            <a href="${contextPath}/theainfo${qs}" class="btn btn-outline-success float-right mt-1">목록</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${ti.thea_code}/edit" class="btn btn-outline-info align-middle float-right mt-1">수정하기</a>
            &nbsp;&nbsp;&nbsp;
            <button class="btn btn-outline-danger align-middle float-right mt-1" onclick="deleteTheaInfo('${ti.thea_code}');">삭제하기</button>
        </div>

        <table class="table">
            <tr class="row">
                <th class="col-md-2">영화관코드</th>
                <td class="col-md-10">
                    <p>${ti.thea_code}</p>
                </td>
            </tr>
            <tr class="row">
                <th class="col-md-2">영화관이름</th>
                <td class="col-md-10">
                    <p id="theaName">${ti.thea_name}</p>
                </td>
            </tr>
            <tr class="row">
                <th class="col-md-2">위치</th>
                <td class="col-md-10">
                    <input type="hidden" id="theaLoc_" value="${ti.thea_loc}">
                    <p id="theaLoc">${fn:replace(fn:substring(ti.thea_loc,fn:indexOf(ti.thea_loc, ","), fn:length(ti.thea_loc)),"," ,"")}</p>
                </td>
            </tr>

            <tr class="row">
                <th class="col-md-2">운영 시간</th>
                <td class="col-md-10">
                    <p>${ti.thea_time}</p>
                </td>
            </tr>
            
            <tr class="row">
                <th class="col-md-2">전화번호</th>
                <td class="col-md-10">
                    <p>${ti.thea_tel}</p>
                </td>
            </tr>

            <c:if test="${!empty ti.thea_img}">
            <tr class="row">
                <th class="col-md-2">영화관사진</th>
                <td class="col-md-10">
                    <img src="${contextPath}/theainfo/img/${ti.thea_img}" alt="썸넬" class="mt-3" style="max-width: 100%;max-height: 500px">
                </td>
            </tr>
            </c:if>

            <tr>
                <td>
                <p style="font-weight:bold; color:black;"> 영화관 주변 맛집 위치 </p>     

                    <div style="width: 100%;">
                        <div id="map" style="height:300px;width: 100%;"></div>
                    </div>
                </td>
            </tr>
        </table>

    </section>
    <br><br><br><br><br><br>
</main>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=85d1a0d5c859005e21dabd502d6c30b4&libraries=services,clusterer,drawing"></script>
<script src="<c:url value="/resources/js/theaInfoForm.js" />"></script>
<script src="<c:url value="/resources/js/SearchKakaoAddressAPI.js" />"></script>
</body>
</html>
<%@ include file="../footer.jsp"%>
