<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">

<title>Travel Maker &mdash; 나만의 여행플래너. 쉽고 빠르게 여행을 계획하세요 :)</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/page/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/page/css/style2.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp")
		$("#footers").load("<%=request.getContextPath() %>/views/footer.html")
		
		$("#test2").on('click', function(){
			
		})
		
	});
</script>
</head>
<body>
	<!-- 헤더영역 -->
	<div id="header"></div>
	<!-- 헤더영역 끝 -->
	<!-- 본문영역 -->
	<div class="main">
		<div class="visual-wrap">
			<img src="<%=request.getContextPath() %>/page/images/main.jpg" class="visual" alt="">
			<div class="main-txt">
				나만의 여행 플래너 TRAVEL MAKER!<br> <span>쉽게 빠르게 여행을 계획하세요.</span>
			</div>
			<div class="searchBox">
				<input class="searchInput" type="text" name=""
					placeholder="여행지, 맛집명으로 검색해주세요!">
				<button class="searchButton">
					<div class="material-icons" id ="test2">검색</div>
				</button>
				<div id="city_autocomplete"></div>
				<div class="latest_search">
					최근검색 : <a href="#" class="latest_a">부산</a>
				</div>
				<a class="go_map" href="javascript:void(0)"
					onclick="et_full_modal('/ko/modal/world_map')">지도에서 검색 &gt;</a> <!-- 지도연결해줘야 됨 -->

			</div>
		</div>
		<div id ="search">
		</div>
		<div id="fh5co-blog-section" class="fh5co-section-gray">
			<div class="container">
				<div class="row"></div>
			</div>
			<div class="container">
				<div class="row row-bottom-padded-md">
					<div class="col-lg-4 col-md-4 col-sm-6">
						<!-- 이 박스가 사진들 담는 하얀 상자 -->
						<div class="fh5co-blog animate-box">
<!-- 							<a href="#"><img class="img-responsive" -->
<!-- 								src="../page/images/place-1.jpg" alt=""></a> -->
							<div class="blog-text">
								<div class="prod-title">
									<h3>
										<a href="#">대전</a>
									</h3>
									<img class="maincity" alt="대전.jpg" src="<%=request.getContextPath() %>/page/images/대전.jpg">
									<span class="posted_by">Sep. 15th</span> <span class="comment"><a
										href="">조회수연결해야돼여<i class="icon-bubble2">♥</i></a></span>
									<p>평화의 도시 대전 핫플 구경하기</p>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6">
						<div class="fh5co-blog animate-box">
<!-- 							<a href="#"><img class="img-responsive" -->
<!-- 								src="../page/images/place-2.jpg" alt=""></a> -->
							<div class="blog-text">
								<div class="prod-title">
									<h3>
										<a href="#">부산</a>
									</h3>
									<img class="maincity" alt="부산.jpg" src="<%=request.getContextPath() %>/page/images/부산.jpg">
									<span class="posted_by">Sep. 15th</span> <span class="comment"><a
										href="">조회수연결해야돼여<i class="icon-bubble2">♥</i></a></span>
									<p>광안대교에서 드라이브 어때요?</p>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix visible-sm-block"></div>
					<div class="col-lg-4 col-md-4 col-sm-6">
						<div class="fh5co-blog animate-box">
<!-- 							<a href="#"><img class="img-responsive" -->
<!-- 								src="images/place-3.jpg" alt=""></a> -->
							<div class="blog-text">
								<div class="prod-title">
									<h3>
										<a href="#">서울</a>
									</h3>
									<img class="maincity" alt="서울.jpg" src="<%=request.getContextPath() %>/page/images/서울.jpg">
									<span class="posted_by">Sep. 20th</span> <span class="comment"><a
										href="">조회수연결해야돼여<i class="icon-bubble2">♥</i></a></span>
									<p>#클린하이킹 명소를 찾아떠나는 서울 등산투어</p>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6">
						<!-- 이 박스가 사진들 담는 하얀 상자 -->
						<div class="fh5co-blog animate-box">
<!-- 							<a href="#"><img class="img-responsive" -->
<!-- 								src="../page/images/place-1.jpg" alt=""></a> -->
							<div class="blog-text">
								<div class="prod-title">
									<h3>
										<a href="#">대전</a>
									</h3>
									<img class="maincity" alt="대전.jpg" src="<%=request.getContextPath() %>/page/images/대전.jpg">
									<span class="posted_by">Sep. 15th</span> <span class="comment"><a
										href="">조회수연결해야돼여<i class="icon-bubble2">♥</i></a></span>
									<p>평화의 도시 대전 핫플 구경하기</p>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-6">
						<div class="fh5co-blog animate-box">
<!-- 							<a href="#"><img class="img-responsive" -->
<!-- 								src="../page/images/place-2.jpg" alt=""></a> -->
							<div class="blog-text">
								<div class="prod-title">
									<h3>
										<a href="#">부산</a>
									</h3>
									<img class="maincity" alt="부산.jpg" src="<%=request.getContextPath() %>/page/images/부산.jpg">
									<span class="posted_by">Sep. 15th</span> <span class="comment"><a
										href="">조회수연결해야돼여<i class="icon-bubble2">♥</i></a></span>
									<p>광안대교에서 드라이브 어때요?</p>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix visible-sm-block"></div>
					<div class="col-lg-4 col-md-4 col-sm-6">
						<div class="fh5co-blog animate-box">
<!-- 							<a href="#"><img class="img-responsive" -->
<!-- 								src="images/place-3.jpg" alt=""></a> -->
							<div class="blog-text">
								<div class="prod-title">
									<h3>
										<a href="#">서울</a>
									</h3>
									<img class="maincity" alt="서울.jpg" src="<%=request.getContextPath() %>/page/images/서울.jpg">
									<span class="posted_by">Sep. 20th</span> <span class="comment"><a
										href="">조회수연결해야돼여<i class="icon-bubble2">♥</i></a></span>
									<p>#클린하이킹 명소를 찾아떠나는 서울 등산투어</p>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<!-- fh5co-blog-section -->
			<div id="fh5co-testimonial">
<!-- 				style="background-image: url(../page/images/img_bg_1.jpg);"> -->
				<div class="container">
					<div class="row animate-box">
						<div class="col-md-8 col-md-offset-2 text-center fh5co-heading">
							<h2 id="TM">트매픽! 추천 여행지!!</h2>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="box-testimony animate-box">
								<blockquote>
									<span class="quote"><img class="icon-quotes-right"
										src="<%=request.getContextPath() %>/page/images/여자.png" alt=""></span>
									<p>&ldquo;어쩌구저쩌구해서 픽했다. 주말 어떠세요???&rdquo;</p>
									<!-- &ldquo;&rdquo; : 큰따옴표 표시 -->
								</blockquote>
								<p class="author">경미 대리픽!</p>
							</div>

						</div>
						<div class="col-md-4">
							<div class="box-testimony animate-box">
								<blockquote>
									<span class="quote"><img class="icon-quotes-right"
										src="<%=request.getContextPath() %>/page/images/남자.png" alt=""></span>
									<p>&ldquo;어쩌구저쩌구해서 픽했다. 주말 어떠세요??&rdquo;</p>
								</blockquote>
								<p class="author">유밀 차장픽!</p>
							</div>


						</div>
						<div class="col-md-4">
							<div class="box-testimony animate-box">
								<blockquote>
									<span class="quote"><img class="icon-quotes-right"
										src="<%=request.getContextPath() %>/page/images/남자.png" alt=""></span>
									<p>&ldquo;어쩌구저쩌구해서 픽했다. 주말 어떠세요???&rdquo;</p>
								</blockquote>
								<p class="author">영관 사장픽!</p>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footers"></div>

</body>
</html>