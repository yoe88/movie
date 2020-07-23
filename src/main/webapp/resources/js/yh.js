'use strict'

let currentCount = 0; // 선택된 좌석 개수
let adult = 0;   //성인
let youth = 0;  //청소년
let list = ""; //선택한 좌석 리스트

//select 값이 변했을때 값 adult, youth 변경하기
function change(){
    adult = Number(document.querySelector('#adult').value);
    youth = Number(document.querySelector('#youth').value);

    if(adult + youth > 8){
        alert("최대인원은 8명입니다");

        adult = 0;
        youth = 0;
        document.querySelector('#adult').value = 0;
        document.querySelector('#youth').value = 0;
    }
}

function seat(div){  							//div = 본인
    if(div.classList.contains('isExists'))  	//이미 예약 되어 있는 좌석인경우 패스
        return;

    const id = div.id; 							//선택 하려는 좌석 아이디
    if(!div.classList.contains('on') ){
        if((adult + youth) <= currentCount){  //총 인원이랑 현재 선택된 좌석 개수가 같을경우
            alert("인원보다 많이 선택되었습니다.");
            return;
        }
        div.classList.add('on');
        currentCount++;
    }else{
        div.classList.remove('on');
        currentCount--;
    }
}



//유효성 검사
function validateSeat(){

    if(adult + youth > currentCount){ //인원이랑 선택된 좌석개수가 다른경우
        alert("인원보다 좌석이 적게 선택되었습니다, 다시 확인해주세요");
        return false;
    }else if(adult + youth < currentCount){
        alert("인원보다 좌석이 많이 선택되었습니다, 다시 확인해주세요");
        return false;
    }
    else if(adult + youth === 0){
        alert("인원을 선택해주세요.");
        return false;
    }

    const selectSeatList = document.querySelectorAll('div.on'); //선택된 좌석 리스트
    const list = [];

    for(let item of selectSeatList){
        const seatId = item.innerHTML.trim();
        list.push(seatId);
    }
    document.querySelector('#list').value = list.join(',');

    return true;
}

function kakaoPay(root){

    /* post */
    const form = document.createElement("form");
    form.setAttribute("method", "POST");
    form.setAttribute("action", `${root}/kakao`);
    form.setAttribute("target", "kakao");

    const title = document.querySelector('#title').textContent;  //영화 제목
    const total_amount = document.querySelector('#final').value.replace(',',''); //결제 요금
    const showCode = document.querySelector('#showCode').value;
    const point = document.querySelector('#point').value;

    const input1 = document.createElement('input');
    input1.type = 'hidden';
    input1.name = 'title';
    input1.value = title;
    form.append(input1);

    const input2 = document.createElement('input');
    input2.type = 'hidden';
    input2.name = 'show_code';
    input2.value = showCode;
    form.append(input2);

    const input3 = document.createElement('input');
    input3.type = 'hidden';
    input3.name = 'total_amount';
    input3.value = total_amount;
    form.append(input3);

    const input4 = document.createElement('input');
    input4.type = 'hidden';
    input4.name = 'res_point';
    input4.value = point;
    form.append(input4);

    const input5 = document.createElement('input');
    input5.type = 'hidden';
    input5.name = 'seatList';
    input5.value = document.querySelector('#seatList').textContent;
    form.append(input5);

    const input6 = document.createElement('input');
    input6.type = 'hidden';
    input6.name = 'theaName';
    input6.value = document.querySelector('#theaName').textContent;
    form.append(input6);

    const input7 = document.createElement('input');
    input7.type = 'hidden';
    input7.name = 'roomName';
    input7.value = document.querySelector('#roomName').textContent;
    form.append(input7);

    const arr = getInput(form);
    const input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'map';
    input.value = arr;
    form.append(input);

    document.body.appendChild(form);

    window.name = "parent"
    window.open(`${root}/kakao`
        , `kakao`
        , "width=500, height=550");
    form.submit();
    document.body.removeChild(form);
}


function getInput(form) {
    let params = [];

    for(let i=0; i<form.elements.length; i++){
        //console.log(form.elements[i]);
       /* let data = encodeURIComponent(form.elements[i].name);
        data += '=';
        data += encodeURIComponent(form.elements[i].value);
        params.push(data);*/
        let data = (form.elements[i].name);
        data += '=';
        data += (form.elements[i].value);
        params.push(data);
    }
    return params.join("&");
}


async function deleteRes(resCode, button) {
    if(!confirm('정말 취소하시겠습니까?')) return;

    //const resCode = document.querySelector(`span[data-num="${num}"]`).attributes.getNamedItem('data-num');

    const pointSpan = button.parentElement.parentElement.querySelector('#point');
    let point = 0;
    if(pointSpan != null){
        point = pointSpan.textContent.replace(',','');
    }


    const response = await fetch('res',{
        method : 'DELETE',
        headers : {'Content-Type' : 'application/json'},
        body : JSON.stringify({'res' : resCode, 'point' : point})
    }).then(r => r.text());

    console.log('응답코드',  response);

    if(response === '1'){
        button.parentElement.parentElement.remove();
    }else{
        console.log('error');
    }
}

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}