<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<%@ include file="../header.jsp"%>
<html>
<head>
    <title>글 수정</title>

    <%-- 부트스트랩 설정 --%>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>--%>

    <!-- 다음 주소 API -->
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
    <script src="https://kit.fontawesome.com/9766199556.js" crossorigin="anonymous"></script>

    <style>
        .form-control[readonly]{
            background: white!important;
        }
    </style>
</head>
<body>
<main>
    <section class="container">
        <form action="${contextPath}/theainfo/${ti.thea_code}/edit" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="thea_loc" id="loc">
            <input type="hidden" name="thea_time" id="time">
            <table class="table">
                <tr class="row">
                    <th class="col-md-2">영화관코드</th>
                    <td class="col-md-10">
                      <input type="text" name="thea_code" readonly class="form-control" value="${ti.thea_code}">
                    </td>
                </tr>
                <tr class="row">
                    <th class="col-md-2">영화관이름</th>
                    <td class="col-md-10">
                        <input type="text" id="theaName" name="thea_name" required minlength="1" maxlength="16" size="16" class="form-control" autocomplete="off" value="${ti.thea_name}">
                    </td>
                </tr>
                <tr class="row">
                    <th class="col-md-2">위치</th>
                    <td class="col-md-10">
                    <div class="input-group my-1">
                        <input type="text" id="zonecode" name="zoneCode" class="form-control d-inline" placeholder="우편번호" autocomplete="off" readonly value="${info.zoneCode}">

                        <div class="input-group-append">
                            <button type="button" onclick="execDaumPostcode()" class="input-group-text">우편번호 찾기</button><br>
                        </div>
                    </div>

                    <input type="text" id="address" name="address" placeholder="주소" class="form-control float-left" autocomplete="off" readonly value="${info.address}" style="width: 50%;">
                    <input type="text" id="extraAddress" name="extraAddress" placeholder="참고항목" class="form-control float-left" autocomplete="off" value="${info.extraAddress}" readonly style="width: 50%;">
                    <input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" class="form-control d-inline " value="${info.detailAddress}">

                    <div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
                        <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
                    </div>
                    </td>
                </tr>

                <tr class="row">
                    <th class="col-md-2">운영 시간</th>
                    <td class="col-md-10">
                        <div class="row">
                            <div class="col-sm-3">
                                <div class="form-group">
                                    <div class="input-group date" id="datetimepicker3" data-target-input="nearest">
                                        <input type="text" id="startTime" class="form-control datetimepicker-input" readonly value="${info.startTime}" data-target="#datetimepicker3"/>
                                        <div class="input-group-append" data-target="#datetimepicker3" data-toggle="datetimepicker">
                                            <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <span> ~ </span>
                            <div class="col-sm-3">
                                <div class="form-group">
                                    <div class="input-group date" id="datetimepicker4" data-target-input="nearest">
                                        <input type="text" id="endTime" class="form-control datetimepicker-input" readonly value="${info.endTime}" data-target="#datetimepicker4"/>
                                        <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
                                            <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>


                <tr class="row">
                    <th class="col-md-2">전화번호</th>
                    <td class="col-md-10">
                        <input type="tel" id="theaTel" name="thea_tel" required minlength="1" maxlength="20" size="20" class="form-control" autocomplete="off" value="${ti.thea_tel}">
                    </td>
                </tr>

                <tr class="row">
                    <th class="col-md-2">영화관사진</th>
                    <td class="col-md-10">
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="customFile" name="file" accept="image/*" onchange="changeFile(this)">
                            <label class="custom-file-label" id="fileName" for="customFile">${ti.thea_img}</label>
                        </div>

                        <img src="${contextPath}/theainfo/img/${ti.thea_img}" alt="썸넬" class="mt-3" id="preView" style="max-width: 100%;max-height: 500px">
                    </td>
                </tr>

                <tr>
                    <td colspan="2" class="py-3 d-flex justify-content-around">
                        <input type="button" value="쓰기" class="btn btn-success" onclick="addTheaInfo(this.form);">
                        <input type="button" value="취소" class="btn btn-danger" onclick="cancel('${contextPath}')">
                    </td>
                </tr>
            </table>
        </form>
    </section>
</main>
<script src="<c:url value="/resources/js/DaumAddressAPI.js" />"></script>
<script src="<c:url value="/resources/js/theaInfoForm.js" />"></script>
</body>
</html>
<%@ include file="../footer.jsp"%>