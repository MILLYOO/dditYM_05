<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<script src="<%=request.getContextPath() %>/views/Search/modal-19/js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/views/Search/modal-19/js/popper.min.js"></script>
    <script src="<%=request.getContextPath() %>/views/Search/modal-19/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e08d921eaf92b6587ac46f3f1255b6a3&libraries=services"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/scrab.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
	 <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Source+Serif+Pro:400,600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/views/Search/modal-19/fonts/icomoon/style.css">

	 <link rel="stylesheet" href="<%=request.getContextPath() %>/views/Search/modal-19/css/style.css">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/page/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/page/css/style2.css">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/views/Search/modal-19/css/style.css">	
	

	

<script type="text/javascript">
	$(document).ready(function() {
		$("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp")
		$("#footers").load("<%=request.getContextPath() %>/views/footer.html")
		
		$("#test2").on('click', function(){
			
		})
		
	});
	
	$(function () {
		listPageServer(1);
		
		//페이징---------------------------------------------------
		// 이벤트 - 페이지 번호 클릭
		$('#pagelist').on('click','.paging', function(){
			currentPage = $(this).text().trim();
			listPageServer(currentPage);
		})
		
		// 다음 버튼 클릭
		$('#pagelist').on('click','.next', function(){
			nextstr = $('.paging').last().text().trim();
			currentPage = parseInt(nextstr) + 1;
			listPageServer(currentPage);
		})
		
		// 이전 버튼 클릭
		$('#pagelist').on('click','.previous', function(){
			prestr = $('.paging').first().text().trim();
			currentPage = parseInt(prestr) - 1;
			listPageServer(currentPage);
		})
		//-------------------------------------------------------
		
		//이미지 클릭 --> 모달창
		$(document).on('click','.scrabid', function () {
			searchId = $(this).find('.prod-title').attr('idx');
			
			openModal(searchId);
		})
		
	})
</script>


<style>

h1{
	text-align: center;
	margin-top: 30px;
}
.scrabL{
	height: 100px;
}

.container{
	font-family: 'Do Hyeon', sans-serif;
}
#blog-text{
	margin-top: 20px;
}
</style>

<title>스크랩 목록</title>
</head>
<body>

	<div id="header"></div>
	<div class="container">
		<div class="row">
			<h1>MY SCRAB</h1>
		</div>


			<!-- 스크랩 목록 -->
			<div class="container">
				<div class="row myscrab">
					
				</div>
			</div>


			<!-- 페이징 -->
		 	<div class="pt-3" id="pagelist"> </div>
		 	
		 	
		 	<!-- 여행지 조회 -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content rounded-0">
          <div class="modal-body py-0">
			 <div class="d-flex main-content">
               <div class="bg-image promo-img mr-3" style="background-image: url('../../images/여행지1.jpg');"> 
                 <span class="modalId"></span> 
               </div>
              <div class="content-text p-4 px-5 align-item-stretch">
                <div class="text-center">
                    <h3 class="mb-3 line" id ="modalTitle"></h3>
                    <p class="mb-3"> </p>
                    <p class="mb-5" id="modalContent"></p>
	                   <div class="d-flex text-center social w-50 mx-auto">
	                        <a href="#" onclick="return false;" class="d-inline-block d-flex align-items-center mr-auto like" id ="modalId">
	                            <span class="icon-heart mr-2 icon" id="likeon" style="display:hide"></span><span id="likeoff" class="icon-heart-o mr-2 icon" style="display:hide"></span> <span id="likecount"></span>
	                        </a>
	                        <a href="#" onclick="return false;" class="d-inline-block d-flex align-items-center ml-auto add" id="modalScrap">
	<!--                             <span class="icon-add mr-2"></span> <span>55</span> -->
	                        </a>
	                    </div>
                </div>
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