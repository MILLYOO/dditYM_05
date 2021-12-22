$(function() {
	
	
	// 전체 마을 위치 마커를 담을 배열
	var markers = [];
	var overlays = [];
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	mapOption = {
		center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level : 9
	// 지도의 확대 레벨
	};

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다

	$.ajax({
				url : '/Travel_Maker/searchInfo.do',
				type : 'post',
				data : {"data" : search},
				dataType : 'json',
				success : function(res) {
					$.each(res, function(i, v) {
										var id = v.tm_search_id;
										var title = v.tm_search_name;
										var latlng = new kakao.maps.LatLng(
												v.tm_search_y,
												v.tm_search_x);
										var imgName = v.img_name;
										var img = v.img_id;
										var addr = v.tm_search_addr;
										var tel = v.tm_search_tel;
										var cate = v.tm_search_cate;
										var con = v.tm_search_con;
										// 마커를 생성합니다
										var marker = new kakao.maps.Marker({
											map : map, // 마커를 표시할 지도
											position : latlng, // 마커의 위치
											addr : addr
										});
										var geocoder = new kakao.maps.services.Geocoder();
										geocoder.addressSearch(search, function(result, status) {
										    // 정상적으로 검색이 완료됐으면 
										     if (status === kakao.maps.services.Status.OK) {
										    	 var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
										    	 map.setCenter(coords);
										     }
										});
										var $wrap = $('<div class="wrap" />');
										var $info = $('<div class="info" />');
										var $title = $('<div class="title" />')
												.text(title);
										var $close = $(
												'<div class="close" title="닫기" />')
												.click(closeOverlay);
										var $body = $('<div class="body">'
												+ '<div class="img">'
												+ '<img src="http://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">'
												+ '</div>'
												+ '<div class="desc">'
												+ '<div class="ellipsis">'
												+ addr
												+ '</div><hr>'
												+ '<div class="tel">'
												+ tel
												+ '</div>'
												+ '</div>'); // body 안쪽은 생략

										$wrap.append($info);
										$info.append($title).append($body);
										$title.append($close);

										var content = $wrap[0];

										// 커스텀 오버레이 생성
										var overlay = new kakao.maps.CustomOverlay(
												{
													content : content,
													map : map,
													position : marker
															.getPosition()
												});

										// 커스텀 오버레이를 닫는 함수
										function closeOverlay() {
											overlay.setMap(null);
										}

										function makeList(){
											console.log('이미지이름' + imgName)
											var content = 
												'<div class="col-3">'+
													'<div class="card" id="'+id+'">'+
														'<img class="sImg" src="../imageForSearch/' + imgName + '.jpg" alt="" class="card-img-top" />'+
														'<div class="card-body">'+ 
														'<h5 class="card-title">'+title+'</h5>'+
														'<p class="card-text">'+addr+'</p>'+
														'</div>'+
													'</div>'+
												'</div>';
											
											$('.cardinfo').append(content);											


										}
										kakao.maps.event.addListener(marker,'click',function() {
											$("#modalScrap").empty();
											$.ajax({
												url : '/Travel_Maker/searchInfo2.do',
												type : 'post',
												data : {"data" :id},
												dataType : 'json',
												success : function(res) {
													id = res.tm_search_id;
													var title = res.tm_search_name;
													var latlng = new kakao.maps.LatLng(
															v.tm_search_y,
															v.tm_search_x);
													var img = res.img_id;
													var addr = res.tm_search_addr;
													var tel = res.tm_search_tel;
													var cate = res.tm_search_cate;
													var con = res.tm_search_con;
													$(".modalId").text(id);
													$("#modalTitle").text(title);
													$("#modalContent").text(con);
													$("#exampleModalCenter").modal("show");
												},
												error : function(xhr){
													console.log("상태 : " + xhr.status);
												}
											})

											$.ajax({
												url : '/Travel_Maker/ScrapButton.do',
												type : 'get',
												data : {"id" : id},
												dataType : 'json',
												success : function(res) {
													if(res.sw=="true"){
														$("#modalScrap").append('<span class="icon-minus mr-2" id ="scrapDelete"></span>');
													}else{
														$("#modalScrap").append('<span class="icon-plus mr-2" id ="scrapInsert"></span>');
													}
												},
												error : function(xhr){
													console.log("상태: " + xhr.status);
												}
											})

										});
										// 마커를 클릭했을때 커스텀 오버레이를 표시합니다.
										kakao.maps.event.addListener(marker,'mouseover',function() {
												overlay.setMap(map);
														});
										kakao.maps.event.addListener(marker,'mouseout',function() {
												closeOverlay();
										});
										// 검색된 마커만 지도 위에 표시하기 위해 전체 마커 숨기기
										closeOverlay();

										// 마커가 지도 위에 표시되도록 설정합니다
										marker.setMap(map);

										// 생성된 마커를 배열에 추가
										markers.push(marker);
										overlays.push(overlay);
										makeList();
									})
				},
				error : function(xhr) {
					console.log("상태 : " + xhr.status);
				}
			}); 
})


						

