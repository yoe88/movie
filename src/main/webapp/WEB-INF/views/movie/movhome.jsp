
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Header section
================================================== -->
<%@ include file="../header.jsp"%>
<!-- 
<h3 class="text-center" style="font-weight: bold;"> 영화 홈 </h3> 
-->



<div id="demo" class="carousel slide" data-ride="carousel">
	<ul class="carousel-indicators" style="margin: 0; width: 100%">
		<li data-target="#demo" data-slide-to="0" class="active"></li>
		<li data-target="#demo" data-slide-to="1"></li>
	</ul>
	<div class="carousel-inner">
		<div class="carousel-item active">
			<img src="${contextPath}/resources/images/onward_mhome.jpg" alt="alive">
		</div>
		<div class="carousel-item">
			<img src="${contextPath}/resources/images/invasion_mhome.jpg" alt="invasion">
		</div>

	</div>
	<a class="carousel-control-prev" href="#demo" data-slide="prev"> <span
		class="carousel-control-prev-icon"></span>
	</a> <a class="carousel-control-next" href="#demo" data-slide="next"> <span
		class="carousel-control-next-icon"></span>
	</a>
</div>

<br>
<br>
<br>

<!--  현재 상영작 -->

<div class="text-center">
	<input type="button" value="상영 예정작 보러가기"
		   onclick="location.href='./pre_movies.do'"
		   class="btn btn-secondary btn-lg">
</div>
<br><br>
<h3 class="text-center" style="font-weight: bold;"> 현재 상영 영화 </h3>




<br>
<br>

<section id="portfolio">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12">

				<!-- iso section -->
				<div class="iso-section" data-wow-delay="2.6s">

					<!-- iso box section -->
					<div class="iso-box-section" data-wow-delay="1s">
						<div class="iso-box-wrapper col4-iso-box">

							<!-- card 반복구간 -->
							<c:forEach var="dto" items="${list }">
								<div class="iso-box col-md-3">
									<div class="portfolio-thumb">
										<img src="${contextPath}/resources/poster/${dto.mov_img }" class="img-responsive"
											alt="${dto.mov_title }">
											
										<div class="portfolio-overlay">
											<div class="portfolio-item">
												<input type="button" value="영화 상세정보"
													onclick="location.href='./movInfo.do?mov_code=${dto.mov_code}'"
													class="btn btn-outline-secondary btn-lg">
											</div>
										</div>
									</div>
									<c:if test="${dto.mov_rate eq 12 }"> <img src="${contextPath}/resources/images/mov_rate_12.png" style="width:23px;"> </c:if>
		                            <c:if test="${dto.mov_rate eq 15 }"> <img src="${contextPath}/resources/images/mov_rate_15.png" style="width:23px;"> </c:if>
		                            <c:if test="${dto.mov_rate eq 19 }"> <img src="${contextPath}/resources/images/mov_rate_19.png" style="width:23px;"> </c:if>
		                            <c:if test="${dto.mov_rate eq 0 }">  <img src="${contextPath}/resources/images/mov_rate_all.png" style="width:23px;"> </c:if>
									<fmt:formatNumber var="per" value="${dto.mov_per / allMovCnt}" pattern="#.#%" />
									<span> ${dto.mov_title } (${per}) </span>
								</div>
								
							</c:forEach>

						</div>
					</div>

				</div>
			</div>

		</div>
	</div>
</section>

<!-- Footer section
================================================== -->
<%@ include file="../footer.jsp"%>