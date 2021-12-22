$(function() {
	// 전체 마을 위치 마커를 담을 배열
	var markers = [];
	var overlays = [];
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	mapOption = {
		center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level : 10
	// 지도의 확대 레벨
	};

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(27, 69)};
	// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다
	var positions = [];

	$.ajax({
				url : '/Travel_Maker/AllInfo.do',
				type : 'post',
				dataType : 'json',
				success : function(res) {
					$.each(res, function(i, v) {
										var id = v.tm_search_id;
										var title = v.tm_search_name;
										var latlng = new kakao.maps.LatLng(
												res[i].tm_search_y,
												v.tm_search_x);
										var img = v.img_id;
										var addr = v.tm_search_addr;
										var tel = v.tm_search_tel;
										var cate = v.tm_search_cate;
										var con = v.tm_search_con;
										// 마커를 생성합니다
										var marker = new kakao.maps.Marker({
											map : map, // 마커를 표시할 지도
											position : latlng // 마커의 위치
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
												+ '<div style="float: right; margin-right: 10px;">'
												+ '<input type="button" id="del" name ="'
												+ id + '"value ="삭제"></div>'
												+ '</div>' +

												'</div>'); // body 안쪽은 생략

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

										// 마커를 클릭했을때 커스텀 오버레이를 표시합니다.
										kakao.maps.event.addListener(marker,'click',function() {
															overlay.setMap(map);
															var pos = marker.getPosition();
															map.panTo(pos);
															document.getElementById('pid').value = id;
															document.getElementById('pname').value = title;
															document.getElementById('paddress').value = addr;
															document.getElementById('ptel').value = tel;
															$('#pcon').val(con);
															document.getElementById('latclick').value = latlng.getLat();
															document.getElementById('lngclick').value = latlng.getLng();

															if (cate != 1) {
																$("#pcate1").removeAttr(
																				"checked",
																				"checked");
																$("#pcate2").attr(
																				"checked",
																				"checked");
															} else {
																$("#pcate2").removeAttr(
																				"checked",
																				"checked");
																$("#pcate1").attr(
																				"checked",
																				"checked");
															}
														});
										kakao.maps.event.addListener(marker,'mouseout',function() {
											setTimeout(function() {
												closeOverlay();
												}, 60000);
										});
										// 검색된 마커만 지도 위에 표시하기 위해 전체 마커 숨기기
										closeOverlay();

										// 마커가 지도 위에 표시되도록 설정합니다
										marker.setMap(map);

										// 생성된 마커를 배열에 추가
										markers.push(marker);
										overlays.push(overlay);

									})
				},
				error : function(xhr) {
					console.log("상태 : " + xhr.status);
				}
			});

	$(document).on('click', "#del", function() {
		var id = document.getElementById('pid').value;
		$.ajax({
			url : '/Travel_Maker/deleteInfo.do',
			type : 'post',
			data : {
				id : id
			},
			dataType : 'json',
			success : function(res) {
				alert(res.sw);
				markers[id - 1].setMap(null);
				overlays[id - 1].setMap(null);
				markers.splice(id - 1, 1);
				overlays.splice(id - 1, 1);
				location.reload();
				$("#tm_ml_form").each(function() {
					this.reset();
				});
			},
			error : function(xhr) {
				console.log('상태 : ' + xhr.status);
			}
		})
	})

	// 지도에 마커 표시
	function setMarkers(map) {
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(map);
		}
	}

	$('.search-btn').on('click', function(event) {
		event.preventDefault();
		setMarkers(null);
		$.ajax({
			url : '/Travel_Maker/searchInfo.do',
			type : 'post',
			data : {
				data : $('#search').val()
			},
			dataType : 'json',
			success : function(res) {
				$.each(res, function(i, v) {
					idx = v.tm_search_id;
					markers[idx - 1].setMap(map);
					map.setCenter(markers[idx - 1].getPosition());
				});
			},
			error : function(xhr) {
				console.log("상태 : " + xhr.status);
			}
		})
	})

	$('#update').on('click', function() {
		formdata = $('form').serializeArray();
		$.ajax({
			url : '/Travel_Maker/updateSearch.do',
			type : 'post',
			data : formdata,
			dataType : 'json',
			success : function(res) {
				alert(res.sw);
				location.reload();
			},
			error : function(xhr) {
				console.log('상태 : ' + xhr.status);
			}
		})
	})
	/** ****************************************************************************************************************************************************** */
})