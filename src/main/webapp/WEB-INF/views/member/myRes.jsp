<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>
<br>
<div class="container">
    <h2 class="text-dark">나의 예매 정보</h2>
    <table class="table">
    <c:if test="${list != null}">
        <c:forEach items="${list}" var="r" varStatus="vst">
            <tr>
                <td>
                    <div>
                        <ul style="font-weight: bold">
                            <li><span>예매번호 : ${r.resCode}</span></li><br>
                            <li>예매내역
                                <ul>
                                    <li>관람영화 : <a href="${contextPath}/movie/movInfo.do?mov_code=${r.movCode}"><span class="text-info">${r.title}</span></a></li>
                                    <li>관람시간 : ${r.showTime}</li>
                                    <li>관람극장 : ${r.location}</li>
                                    <li>인원/좌석 : ${r.people}명 [
                                        <c:forEach items="${r.seat}" var="s" varStatus="st">
                                            ${s}
                                            <c:if test="${!st.last}">
                                                ,
                                            </c:if>
                                        </c:forEach>
                                        ]
                                    </li>
                                </ul>
                            </li>
                            <br>
                            <li>결제정보
                                <ul>
                                    <li>결제금액 : <span class="text-info"><fmt:formatNumber value="${r.price}" pattern="#,###"/></span>원</li>
                                    <c:if test="${r.point != '0'}">
                                        <li>사용한 포인트 : <span id="point"><fmt:formatNumber value="${r.point}" pattern="#,###"/></span></li>
                                    </c:if>
                                </ul>
                            </li>
                            <br>
                        </ul>
                    </div>
                </td>
                <c:if test="${isNow[vst.index]}">
                <td style="vertical-align: middle">
                    <button class="btn btn-outline-danger" onclick="deleteRes('${r.resCode}',this);">예매 취소</button>
                </td>
                </c:if>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${empty list}">
        <tr>
            <td><h4>예매내역이 존재하지 않습니다.</h4></td>
        </tr>
    </c:if>
    </table>
</div>
<script src="<c:url value="/resources/js/yh.js"/>"></script>


<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>
