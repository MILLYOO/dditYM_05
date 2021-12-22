<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String search = request.getParameter("search");



%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>유저 검색</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<script src="<%=request.getContextPath() %>/views/Search/modal-19/js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/views/Search/modal-19/js/popper.min.js"></script>
    <script src="<%=request.getContextPath() %>/views/Search/modal-19/js/bootstrap.min.js"></script>
	 <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e08d921eaf92b6587ac46f3f1255b6a3&libraries=services"></script>
	 <script src="<%=request.getContextPath() %>/js/userSearch.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Source+Serif+Pro:400,600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/views/Search/modal-19/fonts/icomoon/style.css">
    <!-- Bootstrap CSS -->
<%--     <link rel="stylesheet" href="<%=request.getContextPath() %>/views/Search/modal-19/css/bootstrap.min.css"> --%>
    <!-- Style -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/views/Search/modal-19/css/style.css">


	 
	 
    <style>
    /* map 전용 style, marker 전용 style*/
    .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
    .wrap * {padding: 0;margin: 0;}
    .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
    .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
    .info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
    .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
    .info .close:hover {cursor: pointer;}
    .info .body {position: relative;overflow: hidden;}
    .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
    .desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
    .desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
    .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
    .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
    .info .link {color: #5085BB;}
    .container {
    border: 1px solid lightgray;
	}
    /* map 컨트롤 전용 style */
    html, body {width:100%;height:100%;margin:0;padding:0; }
	.map_wrap {position:relative;overflow:hidden;width:100%;height:480px;}
	.custom_typecontrol {
			position:absolute;
			top:10px;
			right:10px;
			overflow:hidden;
			width:350px;
			height:60px;
			margin:0;
			padding:0;
			z-index:1;
			font-size:12px;
			}
    #wrapper{
	width: 1200px;
	margin: 0 auto;	
}
    /* 버튼전용 style */
    body{
		  margin: 0;
		  padding: 0;
		  background-color: #fff;
		}

    /* 정보를 담는 폼 스타일*/
    .TM_ML{
	display : inline-block;
	width: 100px;
	}
	.card{
		cursor: pointer;
	
    margin: 8px 0;
}
	.card-title{
		text-align: center;
	}
	.card-body {
    	
    	height: 145px;
	}
	.col-4 {
   		margin: 10px 0px;
	}
	.card-text{
		height: 80px;
		text-align: center;
	}
	.sImg{
		height: 200px;
	}
	.content-text{
		width: 100%;
	}
</style>
<script type="text/javascript">
search= "<%=search%>";






</script>
</head>
<body>
	<br>
	<br>
	<div id="wrapper">
		<div class="map_wrap">
			<div id="map"
				style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
		</div>
		<br>
		<div class="container">
			<div class="row cardinfo">
				<!-- 카드 정보가 들어가는 곳 -->
			</div>
		</div>
	</div>
		
	
	<!-- 모달 -->
	    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-lg modal-dialog-centered" style="width: 20%;" role="document">
        <div class="modal-content rounded-0">
          <div class="modal-body py-0">
            <div class="main-content">
              <div class="bg-image promo-img mr-3" style="background-image: url('../../images/여행지1.jpg');">
                <span class="modalId"></span>
              </div>
              <div class="content-text p-4 px-5 align-item-stretch">
                <div class="text-center">
                    <h3 class="mb-3 line" id ="modalTitle"></h3>
                    <p class="mb-3">
                    </p>
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
</body>
</html>