<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta charset="UTF-8">
<title>좌석정보 등록</title>


<p align="center"> 좌석정보 등록</p>

	
<form method="post"
        action="/movieForest/seat/seatforcreateresult.do"
        onsubmit="return seatCheck(this)"
        id="myForm">         
	<input type="hidden" id=room_code name=room_code value="${room_code}">

<table align='center' border='1px' cellspacing='0px' cellpadding='5px'>

	<tr>
      <th>수용인원</th>
      <td> <select name="room_maximum" form="myForm" >
    <option value="80">80명</option>
    <option value="100">100명</option>

	</select></td>    
    </tr>    
    


<!--     <tr>
      <th>마지막좌석행(알파벳)</th>
      <td><input type='text' style="text-transform: uppercase;" name='seat_column_end' id='seat_column_end' size='5'></td>    
    </tr>    
    <tr>
   
    <tr>
      <th>마지막좌석열(숫자)</th>
      <td><input type="number" name='seat_row_end' id = 'seat_row_end' min="1" max="10"></td>
    </tr> --> 
       
  </table>     
	<br>
	
  <div class='bottom' align="center">
  <input type='button' value='HOME'   onclick="location.href='../'">
    <input type='submit' value='추가하기' >
    <input type='button' value='리스트' onclick="location.href='/movieForest/room/roompagelist.do'">
  </div>  
  </form>  

<script type="text/javascript">

$(document).ready(function() {

    $("#seat_code").bind("keyup", function() {

    $( this ).val($( this ).val().toUpperCase());

    }); 

});

$(document).ready(function() {

    $("#room_code").bind("keyup", function() {

    $( this ).val($( this ).val().toUpperCase());

    }); 

});


$(document).ready(function() {

    $("#seat_column_start").bind("keyup", function() {

    $( this ).val($( this ).val().toUpperCase());

    }); 

});

$(document).ready(function() {

    $("#seat_column_end").bind("keyup", function() {

    $( this ).val($( this ).val().toUpperCase());

    }); 

});

</script>

<%@ include file="../footer.jsp"%>