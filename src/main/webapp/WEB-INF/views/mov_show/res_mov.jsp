<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- ================================Header section================================ -->
<!-- <h3>RES_MOV.jsp</h3>  -->
<!-- 상영시간이 10분 미만으로 남은 영화는 안보이게 처리함 -->
<br>

<div class="container">
	<div style="text-align: right">
		<c:if test="${s_mlev !=null}">
		<button type="button" class="btn btn-outline-secondary" onclick="location.href='res_mov.do'">다시 선택하기</button>
		</c:if>
	
	</div>
<br><br>
<div style="text-align:center"> 
<c:if test="${s_mlev == null }">
비회원입니다.
<br>로그인 해주세요.
</c:if>
</div>

<c:if test="${s_mlev != null }">

	<div style="overflow: scroll; width: 30%; height: 400px; padding: 10px; float: left; margin: 1%" id="mov" class="mov" onload="mov_click(-1)">
	</div>

	<div style="overflow: scroll; width: 30%; height: 400px; float: left; margin: 1%" id="thea" class="thea" onload="thea_click(-1)">
	</div>
	<div style="overflow: scroll; width: 30%; height: 400px; float: left; margin: 1%" id="show">
		
	</div>
	<div style="clear: both"></div>

</c:if>
</div>
<br>
<!-- ================================Footer section================================ -->
<%@ include file="../footer.jsp"%>

<script>
	var list= "${list}".split(",");
	//window.alert(list[2].substring(11,22));
	var nowMovie = "${nowMovie}".split(",");
	//window.alert(nowMovie[1]);
	var thea = "${thea}".split(",");
	
	window.onload=mov_click(-1);
	window.onload=thea_click(-1);
	
	let today = new Date();
	var month = (today.getMonth() + 1);//월
	var date = today.getDate();//일
	if(month.toString().length == 1){
		month = "0" + month;
	}
	if(date.toString().length == 1){
		date = "0" + date;
	}
	let hours = today.getHours(); // 시
	let minutes = today.getMinutes();  // 분
	if(hours.toString().length == 1){
		hours = "0" + hours;
	}
	if(minutes.toString().length == 1){
		minutes = "0" + minutes;
	}
	var day = today.getFullYear() + "-" + month + "-" + date;
	var time = hours + ":" +  minutes;

	
	function mov_click(i) {
		
		$('.mov').empty();
		
		if(i == -1){
			var i = nowMovie.length;
		}
		mov_str = "";
		thea_str = "";
		for(var j=1; j<nowMovie.length; j=j+15){
			if(i != nowMovie.length){
				if(j != i){
					mov_str += "<p onclick='mov_click("+j+")' style='color:#D8D8D8;background-color:white'>"+nowMovie[j].substring(11)+"</p>";
				}else if(j == i){
					mov_str += "<p onclick='mov_click("+j+")' style='color:white;background-color:RGB(52,58,64)'>"+nowMovie[j].substring(11)+"</p>";
				}
			}else{
				mov_str += "<p onclick='mov_click("+j+")' style='color:black;background-color:white'>"+nowMovie[j].substring(11)+"</p>";
				
			} 
		}
		document.getElementById('mov').innerHTML=mov_str;

		if(i != nowMovie.length){
			$('.thea').empty();
			for(var a=1; a<thea.length; a=a+9){
				for(var b=3; b<list.length; b=b+6){
					var mov_name = list[b].substring(11,list[b].length); //영화이름
					var thea_name = list[b+2].substring(11,list[b+2].indexOf("}"));//영화관

					if(thea_name == thea[a].substring(11) && mov_name == nowMovie[i].substring(11)){
						thea_str += "<p onclick='mov_thea_click(\"" + nowMovie[i].substring(11) + "\",\"" + thea[a].substring(11) + "\",\"" + day + "\")' style='color:black;background-color:white'>" + thea[a].substring(11) + "</p>";
						break;
					}else if(b == (list.length)-3){
						thea_str += "<p style='color:#D8D8D8;background-color:white'>" + thea[a].substring(11) + "</p>";
					}
				}
				if(list.length == 1){
					thea_str += "<p style='color:#D8D8D8;background-color:white'>" + thea[a].substring(11) + "</p>";
				}
			}
		}
		document.getElementById('thea').innerHTML=thea_str;
	}
	
	function thea_click(i) {
		
		$('.thea').empty();
		
		if(i == -1){
			var i = thea.length;
		}
		
		mov_str = "";
		thea_str = "";
		for(var j=1; j<thea.length; j=j+9){
			if(i != thea.length){
				if(j != i){
					thea_str += "<p onclick='thea_click("+j+")' style='color:#D8D8D8;background-color:white'>"+thea[j].substring(11)+"</p>";
				}else if(j == i){
					thea_str += "<p onclick='thea_click("+j+")' style='color:white;background-color:RGB(52,58,64)'>"+thea[j].substring(11)+"</p>";
				}
			}else{
				thea_str += "<p onclick='thea_click("+j+")' style='color:black;background-color:white'>"+thea[j].substring(11)+"</p>";
				
			} 
		}
		document.getElementById('thea').innerHTML=thea_str;

		if(i != thea.length){
			$('.mov').empty();
			for(var a=1; a<nowMovie.length; a=a+15){
				for(b=3; b<list.length; b=b+6){
					var mov_name = list[b].substring(11,list[b].length); //영화이름
					var thea_name = list[b+2].substring(11,list[b+2].indexOf("}"));//영화관
					
					
					if(mov_name == nowMovie[a].substring(11) && thea_name == thea[i].substring(11)){
						mov_str += "<p onclick='mov_thea_click(\"" + nowMovie[a].substring(11) + "\",\"" + thea[i].substring(11) + "\",\"" + day + "\")' style='color:black;background-color:white'>" + nowMovie[a].substring(11) + "</p>";
						break;
					}else if(b == (list.length)-3){
						mov_str += "<p style='color:#D8D8D8;background-color:white'>" + nowMovie[a].substring(11) + "</p>";
					}
				}
				if(list.length == 1){
					mov_str += "<p style='color:#D8D8D8;background-color:white'>" + nowMovie[a].substring(11) + "</p>";
				}
			}
			document.getElementById('mov').innerHTML=mov_str;
		}
	}
	
	function mov_thea_click(mov_name, thea_name, click_day) {
		
		thea_str="";
		mov_str="";
		
		
		$('.thea').empty();
		for(var i=1; i<thea.length; i=i+9){
			if(thea[i].substring(11) != thea_name){
				thea_str += "<p style='color:#D8D8D8;background-color:white'>"+thea[i].substring(11)+"</p>";
			}else{
				thea_str += "<p style='color:white;background-color:RGB(52,58,64)'>"+thea[i].substring(11)+"</p>";
			}
		}
		document.getElementById('thea').innerHTML=thea_str;
		
		$('.mov').empty();
		for(var i=1; i<nowMovie.length; i=i+15){
			if(nowMovie[i].substring(11) != mov_name){
				mov_str += "<p style='color:#D8D8D8;background-color:white'>"+nowMovie[i].substring(11)+"</p>";
			}else{
				mov_str += "<p style='color:white;background-color:RGB(52,58,64)'>"+nowMovie[i].substring(11)+"</p>";
			}
		}
		document.getElementById('mov').innerHTML=mov_str;
		
		
		//window.alert(year + '/' + month + '/' + date);
		
		show_str = "";
		show_str += '<div style="width:100%; margin-bottom:10px" class="btn-group btn-group-md">';
		
		for(var i=0;i<4;i++){  
			let next_day = new Date();
			next_day.setDate(next_day.getDate()+i)
			var next_year = next_day.getFullYear(); // 년도
			var next_month = next_day.getMonth() + 1;  // 월
			var next_date = next_day.getDate();  // 날짜
			
			if(next_month.toString().length == 1){
				next_month = "0" + next_month;
			}
			if(next_date.toString().length == 1){
				next_date = "0" + next_date;
			}
			var new_day = next_year + "-" + next_month + "-" + next_date;
			
			show_str += '<button class="btn btn-dark" style="width:25%" onclick="mov_thea_click(\''+mov_name+'\',\''+thea_name+'\',\''+new_day+'\')">'+next_month + '/' + next_date+'</button>';
		}
		show_str += '</div>';

		for(b=0; b<list.length; b=b+6){
			var list_mov = list[b+3].substring(11,list[b+3].length); //영화이름
			var list_thea = list[b+5].substring(11,list[b+5].indexOf("}"));//영화관
			var list_time = list[b+1].substring(11,list[b+1].length);//시간
			var list_room = list[b].substring(12,list[b].length);//관
			var list_date = list[b+2].substring(11,21);//상영날짜
			var list_code = list[b+4].substring(11,list[b+4].length);//show_code
			
			 
			if(list_mov == mov_name && list_thea == thea_name && list_date == click_day){
				
				if(list_time.substring(0,2)-time.substring(0,2) < 0 && day == click_day){
					show_str += "";
					console.log(list_time.substring(0,2))
					console.log(time.substring(0,2))
					console.log(day)
					console.log(click_day)
					
				}else{
					if(list_time.substring(0,2)-time.substring(0,2) == 0 && list_time.substring(3,5)-time.substring(3,5) < 10 && day == click_day){
						console.log(list_time.substring(0,2)-time.substring(0,2));
						console.log(list_time.substring(3,5)-time.substring(3,5));
						const a = list_time.substring(0,2)-time.substring(0,2) == 0 && list_time.substring(3,5)-time.substring(3,5) < 10;
						console.log("list_code",list_code);
						console.log("day", day);
						console.log("click" ,click_day);
						console.log('if');
						show_str += ""
					}else{ 
						//show_str += "<button style='width:80px' onclick=\"location.href='../res/seatChoose.do?show_time="+list_time+"&mov_title="+list_mov+"&date="+list_date+"&thea_name="+list_thea+"&room_name="+list_room+"'\">"+
						show_str +=
						"<button class='btn btn-outline-dark' style='width:80px;margin:0 7px 5px 0' onclick=\"location.href='../res/seatChoose.do?show_time="+list_time+"&mov_title="+list_mov+"&date="+list_date+"&thea_name="+list_thea+"&room_name="+list_room+"&show_code="+list_code+"'\">"+
							list_time+"<br>"+
							"<span style='font-size: 8px;margin:0;height: 15px;color:gray'>"+list_room+"</span>"+
						"</button>"
						//alert(list_code)
						
						console.log('else');

	
					}
				} 
			}
		}
	
	
		if(show_str.indexOf("span") == -1){
			show_str += "이 날은 예약할 수 없습니다";
		}
	
		document.getElementById('show').innerHTML= show_str;
		//window.alert(mov_name + "," + thea_name);
	}
</script>