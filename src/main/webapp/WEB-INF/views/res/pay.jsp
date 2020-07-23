<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp" %>
<style>
    p {
        margin-bottom: 5px;
        color: black;
    }
</style>
<br>
<br>
<br>
<main class="container">
    <form action="resseat.do">

        <h4 style="text-align: center">결제를 선택해주세요.</h4>
        <br>
        <br>
        <table style="margin: auto; width: 700px; height: 200px">


            <tr>
                <th rowspan="9"><img
                        src="${contextPath}/resources/poster/${poster }"
                        style="height: 200px"></th>
            <tr>
                <th>영화</th>
                <td><span id="title">${m.mov_title}</span></td>
            </tr>
            <tr>
                <th>영화관</th>
                <td><span id="theaName">${m.thea_name}</span>/<span id="roomName">${m.room_name}</span></td>
            </tr>
            <tr>
                <th>상영날짜</th>
                <td>${m.date}</td>
            </tr>
            <tr>
                <th>선택한 좌석</th>
                <td><span id="seatList">${m.list}</span></td>
            </tr>
            <tr>
                <th>요금</th>
                <td><span id="account"><fmt:formatNumber pattern="#,###"
                                                         value="${m.account}"/></span></td>
            </tr>
            <tr>
                <th>보유포인트</th>
                <td><span id="my-point"><fmt:formatNumber
                        pattern="#,###" value="${m.point}"/></span></td>
            </tr>
            <tr>
                <th>사용할포인트</th>
                <td><input type="text" id="use-point" value="0"> <input
                        type="button" value="포인트 사용" onclick="usePoint();"></td>
            </tr>
            <tr>
                <th>
                    <h5 style="font-weight:bold;"> 최종 결제요금 </h5>
                </th>
                <td>
                    <input id="final" name="res_sum"
                           value="<fmt:formatNumber pattern="#,###" value="${m.account}" />"
                           readonly>
                </td>

            </tr>

        </table>
        <br>

        <div style="margin:auto; text-align: center">

            <input type="hidden" name="show_code" id="showCode" value="${m.show_code}">
			<input type="hidden" name="res_point" id="point" value="0">
			<br>
			<input type="image" src="${contextPath}/resources/images/payment_icon.png" width=100px onclick="kakaoPay('${contextPath}');return false;">
        </div>
    </form>
</main>

<br>
<br>
<br>
<br>
<br>


<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp" %>


<script>
    function usePoint() {
        const usePoint = Number(document.querySelector('#use-point').value); //사용하려는 포인트
        const myPoint_ = document.querySelector('#my-point').textContent;
        const myPoint = Number(myPoint_.replace(',', ''));                  //보유한 포인트
        const account_ = document.querySelector('#account').textContent;
        const account = Number(account_.replace(',', ''));                  //요금

        if (isNaN(usePoint)) {
            alert('0 이상의 숫자만 입력해주세요.');
            document.querySelector('#use-point').value = 0;
            document.querySelector('#point').value = 0;
            document.querySelector('#final').value = numberWithCommas(account);
            return;
        }
        if (0 > usePoint) {
            alert('0 이상의 숫자만 입력해주세요.');
            document.querySelector('#use-point').value = 0;
            document.querySelector('#point').value = 0;
            document.querySelector('#final').value = numberWithCommas(account);
            return;
        }
        if (usePoint > myPoint) {
            alert('보유한 포인트를 초과하였습니다.');
            document.querySelector('#use-point').value = 0;
            document.querySelector('#point').value = 0;
            document.querySelector('#final').value = numberWithCommas(account);
            return;
        }
        if (usePoint > account) { //사용하려는 포인트가 요금보다 큰경우
            document.querySelector('#use-point').value = account;
            document.querySelector('#final').value = 0;
            document.querySelector('#point').value = usePoint;
            return;
        }
        const final = account - usePoint;
        document.querySelector('#final').value = numberWithCommas(final);
        document.querySelector('#point').value = usePoint;
    }

    function aaa(form) {
        for (let i = 0; i < form.elements.length; i++) {

            let data = (form.elements[i].name);
            console.log(data);
            data += '=';
            data += (form.elements[i].value);
            console.log(form.elements[i].value);
        }
        return false;
    }
</script>
<script src="<c:url value="/resources/js/yh.js"/>"></script>