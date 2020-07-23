'use strict'
const contextPath = '/movieForest'

function addTheaInfo(form) {
    const theaName = form.querySelector("#theaName").value;
    if(theaName === ''){
        alert('영화관 이름을 입력해주세요.');
        return;
    }
    const zoneCode = form.querySelector("#zonecode").value;
    if(zoneCode === ''){
        alert('위치를 입력해주세요.');
        return;
    }

    const startTime = form.querySelector("#startTime").value;
    const endTime = form.querySelector("#endTime").value;
    if(startTime === '' || endTime === ''){
        alert('운영시간을 입력해주세요.');
        return;
    }

    const theaTel = form.querySelector("#theaTel").value;
    if(theaTel === ''){
        alert('전화번호를 입력해주세요.');
        return;
    }

    const fileName = form.querySelector("#fileName");
    if(fileName.textContent === '이미지를 선택해 주세요.'){
        alert('영화관 사진은 필수입니다.');
        return;
    }

    //주소 하나로 묶기  컬럼이 하나..
    const address = form.querySelector("#address");
    const extraAddress = form.querySelector("#extraAddress");
    const detailAddress = form.querySelector("#detailAddress");

    document.querySelector('#loc').value = zoneCode + ','+ address.value +','+ extraAddress.value + ', ' + detailAddress.value.trim();

    //시간 하나로 묶기
    form.querySelector("#time").value = `${startTime} ~ ${endTime}`;

    form.submit();
}

function removePreview() {
    document.querySelector('#preView').src = '';
    document.querySelector('#preView').classList.add('d-none');
}

function changeFile(fileButton) {
    const fileName = document.querySelector(".custom-file-label");
    const filePath = fileButton.value;
    if (filePath.length === 0){
        fileName.textContent = '이미지를 선택해 주세요.'
        removePreview();
        return;
    }
    const file = fileButton.files[0];
    if(file.size > 10485760 ){
        alert('최대 허용 파일 사이즈는 10mb입니다.');
        removePreview();
        return;
    }
    const acceptedImageTypes = ['image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tiff'];
    if(!acceptedImageTypes.includes(file.type)) {
        alert('gif, jpeg, png, bgm, tiff 같은 이미지를 올려주세요.');
        removePreview();
        return;
    }
    fileName.textContent = file.name;

    let reader = new FileReader();
    reader.readAsDataURL(file);  //DataURL 읽기
    reader.onload = function () {
        //로컬 이미지를 보여주기
        document.querySelector('#preView').src = reader.result; //썸네일에 이미지 대입
        document.querySelector('#preView').classList.remove('d-none');
    }
}
function cancel(root) {
    if (confirm("변경사항이 저장되지 않을 수 있습니다."))
        location.href = `${root}/theainfo`;
}

if(document.querySelector('#datetimepicker3') == null){
}
async function deleteTheaInfo(theaCode) {
    if(confirm('정말 삭제하시겠습니까?')){

        const response = await fetch(theaCode,{method: 'DELETE'});
        const responseText = await  response.text();

       if(responseText === '1')
            location.href = contextPath + '/theainfo';
       else{
           alert('삭제를 실패했습니다.');
       }
    }
}

window.addEventListener("load",function (){
    if(document.querySelector('#datetimepicker3') != null){
        $(function () {
            $('#datetimepicker3').datetimepicker({
                ignoreReadonly: true,
                format: 'HH:mm'
            });
        });
        $(function () {
            $('#datetimepicker4').datetimepicker({
                ignoreReadonly: true,
                format: 'HH:mm'
            });
        });

    }
})
