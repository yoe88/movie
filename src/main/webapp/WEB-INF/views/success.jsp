<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
결제성공



<form name="yhForm">
    <%--<input type="hidden" name="mov_title" value="${param.mov_title}">
    <input type="hidden" name="thea_name" value="${param.thea_name}">
    <input type="hidden" name="room_name" value="${param.room_name}">
   &lt;%&ndash; <input type="hidden" name="thea_code" value="${param.thea_code}">&ndash;%&gt;
    <input type="hidden" name="room_code" value="${param.room_code}">
    <input type="hidden" name="date" value="${param.date}">
    <input type="hidden" name="show_time" value="${param.show_time}">
    <input type="hidden" name="mem_id" value="${param.mem_id}">
    <input type="hidden" name="choose" value="${param.choose}">
    <input type="hidden" name="adult" value="${param.adult}">
    <input type="hidden" name="youth" value="${param.youth}">--%>
    <input type="hidden" name="show_code" value="${param.show_code}">
    <input type="hidden" name="res_sum" value="${param.total_amount}">
    <input type="hidden" name="res_point" value="${param.res_point}">
    <input type="hidden" name="seatList" value="${param.seatList}">
    <input type="hidden" name="title" value="${approve.item_name}">
    <input type="hidden" name="theaName" value="${param.theaName}">
    <input type="hidden" name="roomName" value="${param.roomName}">

</form>
</body>
<script>
    //opener.location.href = 'http://localhost:8585/movieForest/mov_show/res_thea.do';
    window.opener.name = 'MovieForest';
    document.yhForm.target = 'MovieForest';
    document.yhForm.action = 'res/resseat.do';
    document.yhForm.method = 'post';
    document.yhForm.submit();

    window.open('','_self').close();
</script>
</html>
