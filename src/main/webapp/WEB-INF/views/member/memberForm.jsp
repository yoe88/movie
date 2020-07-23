<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본문 시작 memberForm.jsp-->

<style>
input::placeholder {
	font-size: 12px;
}
</style>
<br><br>
<div class="container">
	<div style="text-align: center">
		<h5>회 원 가 입</h5>
	</div>
	<form name="memberForm" method="post" action="memberpro.do"
		onsubmit="return memberCheck(this)" style="text-align: center">
		<span style="color: red; font-weight: bold">* 필수입력</span> <br> <br>
		<table class="table" style="text-align: left;">
	
			<tr>
				<th>*아이디</th>
				<td><input type="text" name="mem_id" id="mem_id" size="15"
					readonly> <input type="button" class="btn btn-primary btn-sm"
					value="ID중복확인" onclick="idCheck()"></td>
			</tr>
			<tr>
				<th>*비밀번호</th>
				<td><input type="password" name="mem_passwd" id="mem_passwd"
					size="15" required></td>
			</tr>
			<tr>
				<th>*비밀번호 확인</th>
				<td><input type="password" name="repasswd" id="repasswd"
					size="15" required></td>
			</tr>
			<tr>
				<th>*이름</th>
				<td><input type="text" name="mem_name" id="mem_name" size="15"
					required></td>
			</tr>
			<tr>
				<th>*생년월일</th>
				<td><input type="text" name="mem_birth" id="mem_birth" size="20"
					placeholder="-없이 숫자만 입력  ex)19990101" required></td>
			</tr>
			<tr>
				<th>*이메일</th>
				<td><input type="text" name="mem_email" id="mem_email" size="30"
					readonly> <input type="button" class="btn btn-primary btn-sm"
					value="Email 중복확인" onclick="emailCheck()"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="mem_phone" id="mem_phone" size="15"></td>
			</tr>
	
	
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="mem_zipcode" id="mem_zipcode"
					size="7" readonly> <input type="button"
					class="btn btn-primary btn-sm" value="주소찾기" onclick="DaumPostcode()">
				</td>
			</tr>
	
	
			<tr>
				<th>주소</th>
				<td><input type="text" name="mem_addr" id="mem_addr" size="45"
					readonly></td>
			</tr>
	
	
			<tr>
				<th>나머지주소</th>
				<td><input type="text" name="mem_addr2" id="mem_addr2" size="45"></td>
			</tr>
	
	
	
			<tr>
				<td colspan="2" style="text-align: center;"><input type="submit"
					value="회원가입" class="btn btn-outline-primary"> <input type="reset"
					value="취소" class="btn btn-outline-danger" onclick="javascript:history.back();"></td>
			</tr>
		</table>
	
		<!-- ----- DAUM 우편번호 API 시작 ----- -->
		<div id="wrap"
			style="display: none; border: 1px solid; width: 500px; height: 300px; margin: 5px 110px; position: relative">
			<img
				src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png"
				id="btnFoldWrap"
				style="cursor: pointer; position: absolute; right: 0px; top: -1px; z-index: 1; width: 25px; height: 25px;"
				onclick="foldDaumPostcode()" alt="접기 버튼">
		</div>
	
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script>
		
		
			// 우편번호 찾기 화면을 넣을 element
			var element_wrap = document.getElementById('wrap');
	
			function foldDaumPostcode() {
				// iframe을 넣은 element를 안보이게 한다.
				element_wrap.style.display = 'none';
			}
	
			function DaumPostcode() {
				// 현재 scroll 위치를 저장해놓는다.
				var currentScroll = Math.max(document.body.scrollTop,
						document.documentElement.scrollTop);
				new daum.Postcode(
						{
							oncomplete : function(data) {
								// 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
								// 각 주소의 노출 규칙에 따라 주소를 조합한다.
								// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
								var fullAddr = data.address; // 최종 주소 변수
								var extraAddr = ''; // 조합형 주소 변수
	
								// 기본 주소가 도로명 타입일때 조합한다.
								if (data.addressType === 'R') {
									//법정동명이 있을 경우 추가한다.
									if (data.bname !== '') {
										extraAddr += data.bname;
									}
									// 건물명이 있을 경우 추가한다.
									if (data.buildingName !== '') {
										extraAddr += (extraAddr !== '' ? ', '
												+ data.buildingName
												: data.buildingName);
									}
									// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
									fullAddr += (extraAddr !== '' ? ' ('
											+ extraAddr + ')' : '');
								}
	
								// 우편번호와 주소 정보를 해당 필드에 넣는다.
								document.getElementById('mem_zipcode').value = data.zonecode; //5자리 새우편번호 사용
								document.getElementById('mem_addr').value = fullAddr;
	
								// iframe을 넣은 element를 안보이게 한다.
								// (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
								element_wrap.style.display = 'none';
	
								// 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
								document.body.scrollTop = currentScroll;
	
								$('#mem_addr2').focus();
							},
							// 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
							onresize : function(size) {
								element_wrap.style.height = size.height + 'px';
							},
							width : '100%',
							height : '100%'
						}).embed(element_wrap);
	
				// iframe을 넣은 element를 보이게 한다.
				element_wrap.style.display = 'block';
			}
		</script>
	
		<!-- ----- DAUM 우편번호 API 종료----- -->
	
	</form>
</div>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>