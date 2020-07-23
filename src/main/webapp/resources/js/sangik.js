function roomCheck(f){
	var room_code = f.room_code.value;
	room_code = room_code.trim();
	if(room_code.length<1 || room_code.length>10){
		alert("상영관코드를 1~10자 이내로 입력해주세요")
		f.room_code.focus();
		return false;
	}
	var thea_code = f.thea_code.value;
	thea_code = thea_code.trim();
	if(thea_code<1 || thea_code>5){
		alert("영화관코드를 1~5자 이내로 입력해주세요")
		f.thea_code.focus();
		return false;
	}
	var room_name = f.room_name.value;
	room_name = room_name.trim();
	if(room_name<1 || room_name>5){
		alert("상영관이름을 1~5자 이내로 입력해주세요")
		f.room_name.focus();
		return false;
	}
	var room_type = f.room_type.value;
	room_type = room_type.trim();
	if(room_type<1 || room_type>10){
		alert("상영관정보를 1~10자 이내로 입력해주세요")
		f.room_type.focus();
		return false;
	}
	
	return true;
	
}//roomCheck(f) end


function seatCheck(f){
	var seat_code = f.seat_code.value;
	seat_code = seat_code.trim();
	if(seat_code<1 || seat_code>10){
		alert("좌석일련번호를  1~10자 이내로 입력해주세요")
		f.seat_code.focus();
		return false;
	}
	var room_code = f.room_code.value;
	room_code = room_code.trim();
	if(room_code<1 || room_code>10){
		alert("상영관코드를 1~10자 이내로 입력해주세요")
		f.room_code.focus();
		return false;
	}
	var seat_column = seat_column.value;
	seat_column = seat_column.trim();
	if(seat_column<1 || seat_column>2){
		alert("좌석행을 1~2자로 입력해주세요")
		f.seat_column.focus();
		return false;
	}
	
	return true;
}//seatCheck(f) end

















