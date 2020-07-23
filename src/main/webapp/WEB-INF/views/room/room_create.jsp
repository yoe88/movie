<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>


  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<p align="center"> 상영관정보추가</p>
<form method="get"
        action="/movieForest/room/createresult.do"
        onsubmit="return roomCheck(this)"
        id = "myForm">         

    <table align='center' border='1px' cellspacing='0px' cellpadding='5px'>
   

    <tr>
      <th>영화관</th>
      <td>
      <select class="form-control" id="thea_name" name="thea_name">
          <c:forEach var="thealist" items="${thealist }">
          <option>${thealist.thea_name} </option>
             
          </c:forEach>


      </select>     
      </td>
    </tr>
    <tr>
      <th>상영관이름</th>
      <!--  <td><input type='text' name='room_name' id='room_name' size='5'></td>    -->
      <td><input type='number' name='room_name' id='room_name' size='5' min="1">관</td>
    </tr>    
   <tr>
      <th>수용인원</th>
      <td>    
      <select name="room_maximum" form="myForm" >
    <option value="80">80명</option>
    <option value="100">100명</option>

	</select></td>    
    </tr>  
    <tr>
      <th>상영관정보</th>
      <td><input type="text" name='room_type' id='room_type' ></td>    
    </tr>       
  </table>    
	<br>
	
    <table align='center' border='1px' cellspacing='0px' cellpadding='5px'>



 <!--    <tr>
      <th>마지막좌석행(알파벳)</th>
      <td><input type='text' style="text-transform: uppercase;" name='seat_column' id='seat_column' size='5'></td>    
    </tr>    
    <tr>
   
    <tr>
      <th>마지막좌석열(숫자)</th>
      <td><input type="number" name='seat_row' id = 'seat_row' min="1" max="10"></td>
    </tr> -->
       
  </table>    
	<br>	
	
	
	
	
  <div class='bottom' align="center">
  <input type='button' value='HOME'   onclick="location.href='../'">
    <input type='submit' value='추가하기' >
    <input type='button' value='리스트' onclick="location.href='../room/roompagelist.do'">

  </div>  
  </form>  

<script type="text/javascript">

$(document).ready(function() {

    $("#room_code").bind("keyup", function() {

    $( this ).val($( this ).val().toUpperCase());

    }); 

});

$(document).ready(function() {

    $("#thea_code").bind("keyup", function() {

    $( this ).val($( this ).val().toUpperCase());

    }); 

});

</script>
<br>
<%@ include file="../footer.jsp"%>