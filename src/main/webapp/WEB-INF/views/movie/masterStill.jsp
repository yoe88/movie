<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>

<br><br>
<div class="text-center">
	<input type="button" value="영화 관리자 페이지"
		   onclick="location.href='./masterMov.do'"
		   class="btn btn-warning btn-lg">
</div>

<br><br>

<p class="text-center" style="font-size:30px; color:orange;"> 스틸컷 3개 이하 삭제 불가 ㅍㅅㅍ)/☆ </p>


<section id="portfolio">
	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<!-- iso section -->
				<div class="iso-section fadeInUp">

					<!-- iso box section -->
					<div class="iso-box-section fadeInUp">
						<div class="iso-box-wrapper col4-iso-box" style="margin:auto;">

							<!-- card 반복구간 -->
							<c:forEach var="dto" items="${list }">
								<div class="iso-box photoshop branding col-md-4">
									<div>
										<img src="${contextPath}/resources/still_imgs/${dto.still_img }" class="img-responsive still-thumb"
											 alt="${dto.still_img }">
									</div>
									<span> ${dto.still_img } </span>
									<br>
									
									<c:if test="${scnt > 3}">
										<input  type="button" value="스틸컷 삭제"
												onclick="location.href='./stillDelete.do?still_no=${dto.still_no }&mov_code=${dto.mov_code }' "
												class="btn btn-danger sdel">
									</c:if>
								</div>
								
							</c:forEach>

						</div>
					</div>

				</div>
			</div>

		</div>
	</div>
</section>
<br><br><br>
<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>
