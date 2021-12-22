$(function() {
	
	// 마커를 담을 배열입니다
	var markers = [];

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	mapOption = {
		center : new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
		level : 3
	// 지도의 확대 레벨
	};

	// 지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption);

	// 장소 검색 객체를 생성합니다
	var ps = new kakao.maps.services.Places();

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
		zIndex : 1
	});

	var selectmarker = new kakao.maps.Marker();

	// 키워드로 장소를 검색합니다

	// 키워드 검색을 요청하는 함수입니다
	
	$("#search").on('click', function(){
		var keyword = document.getElementById('keyword').value;

		if (!keyword.replace(/^\s+|\s+$/g, '')) {
			alert('키워드를 입력해주세요!');
			return false;
		}

		// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
		ps.keywordSearch(keyword, placesSearchCB);
	})
	
	
	
//	function searchPlaces() {
//
//		var keyword = document.getElementById('keyword').value;
//
//		if (!keyword.replace(/^\s+|\s+$/g, '')) {
//			alert('키워드를 입력해주세요!');
//			return false;
//		}
//
//		// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
//		ps.keywordSearch(keyword, placesSearchCB);
//	}

	// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
	function placesSearchCB(data, status, pagination) {
		if (status === kakao.maps.services.Status.OK) {

			// 정상적으로 검색이 완료됐으면
			// 검색 목록과 마커를 표출합니다
			displayPlaces(data);

			// 페이지 번호를 표출합니다
			displayPagination(pagination);

		} else if (status === kakao.maps.services.Status.ZERO_RESULT) {

			alert('검색 결과가 존재하지 않습니다.');
			return;

		} else if (status === kakao.maps.services.Status.ERROR) {

			alert('검색 결과 중 오류가 발생했습니다.');
			return;

		}
	}
	
//	$(function(){
//		$('#send').on('click', function() {
//			formdata = $('form').serializeArray();
//			console.log(formdata);
//			$.ajax({
//				url : '/Travel_Maker/InsertSearch.do',
//				type : 'post',
//				data : formdata,
//				dataType : 'json',
//				success : function(res){
//					alert(res.sw);
//				},
//				error : function(xhr){
//					alert('상태 : ' + xhr.status);
//				}
//			})
//		})
//	})
	
	
	// 검색 결과 목록과 마커를 표출하는 함수입니다
	function displayPlaces(places) {

		var listEl = document.getElementById('placesList'), menuEl = document
				.getElementById('menu_wrap'), fragment = document
				.createDocumentFragment(), bounds = new kakao.maps.LatLngBounds(), listStr = '';

		// 검색 결과 목록에 추가된 항목들을 제거합니다
		removeAllChildNods(listEl);

		// 지도에 표시되고 있는 마커를 제거합니다
		removeMarker();

		for (var i = 0; i < places.length; i++) {

			// 마커를 생성하고 지도에 표시합니다
			var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x), marker = addMarker(
					placePosition, i), itemEl = getListItem(i, places[i]); // 검색
			// 결과
			// 항목
			// Element를
			// 생성합니다

			// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
			// LatLngBounds 객체에 좌표를 추가합니다
			bounds.extend(placePosition);

			// 마커와 검색결과 항목에 mouseover 했을때
			// 해당 장소에 인포윈도우에 장소명을 표시합니다
			// mouseout 했을 때는 인포윈도우를 닫습니다
			(function(marker, pname, praddress, paddress, plat, plng) {
				kakao.maps.event.addListener(marker, 'mouseover', function() {
					if (praddress) {
						fulladdress = "[" + pname + "]" + praddress;
					} else {
						fulladdress = "[" + pname + "]" + paddress;
					}
					displayInfowindow(marker, pname);

				});

				kakao.maps.event.addListener(map, 'mouseout', function() {
					infowindow.close();
				});

				// 리스트의 아이템을 클릭하면 정보들을 text 영역으로 전송 (hidden 사용가능)
				itemEl.onclick = function() {
					if (praddress) {
						document.getElementById('fulladdress').value = "["	+ pname + "]" + praddress;
					} else {
						document.getElementById('fulladdress').value = "["  + pname + "]" + paddress;
					}

					document.getElementById('pname').value = pname;
					if (praddress) {
						document.getElementById('paddress').value = praddress;
					} else {
						document.getElementById('paddress').value = paddress;
					}
					document.getElementById('latclick').value = plat;
					document.getElementById('lngclick').value = plng;
				};

				itemEl.onmouseover = function() {
					displayInfowindow(marker, pname);
				};

				itemEl.onmouseout = function() {
					infowindow.close();
				};
			})(marker, places[i].place_name, places[i].road_address_name,
					places[i].address_name, places[i].y, places[i].x);

			fragment.appendChild(itemEl);
		}

		// 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
		listEl.appendChild(fragment);
		menuEl.scrollTop = 0;

		// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
		map.setBounds(bounds);
	}

	// 검색결과 항목을 Element로 반환하는 함수입니다
	function getListItem(index, places) {

		var el = document.createElement('li'), itemStr = '<span class="markerbg marker_'
				+ (index + 1)
				+ '"></span>'
				+ '<div class="info" style="cursor:pointer;">'
				+ '   <h5>'
				+ places.place_name + '</h5>';

		if (places.road_address_name) {
			itemStr += '    <span>' + places.road_address_name + '</span>'
					+ '   <span class="jibun gray">' + places.address_name
					+ '</span>';
		} else {
			itemStr += '    <span>' + places.address_name + '</span>';
		}
		itemStr += '  <span class="tel">' + places.phone + '</span>';
		+'</div>';

		el.innerHTML = itemStr;
		el.className = 'item';

		return el;
	}

	// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
	function addMarker(position, idx, title) {
		var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커
		// 이미지
		// url,
		// 스프라이트
		// 이미지를
		// 씁니다
		imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
		imgOptions = {
			spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
			spriteOrigin : new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트
			// 이미지 중
			// 사용할
			// 영역의
			// 좌상단
			// 좌표
			offset : new kakao.maps.Point(13, 37)
		// 마커 좌표에 일치시킬 이미지 내에서의 좌표
		}, markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
				imgOptions), marker = new kakao.maps.Marker({
			position : position, // 마커의 위치
			image : markerImage
		});

		marker.setMap(map); // 지도 위에 마커를 표출합니다
		markers.push(marker); // 배열에 생성된 마커를 추가합니다

		return marker;
	}

	// 지도 위에 표시되고 있는 마커를 모두 제거합니다
	function removeMarker() {
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
		markers = [];
	}

	// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
	// 인포윈도우에 장소명을 표시합니다
	function displayInfowindow(marker, title) {
		var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

		infowindow.setContent(content);
		infowindow.open(map, marker);
	}

	// 검색결과 목록의 자식 Element를 제거하는 함수입니다
	function removeAllChildNods(el) {
		while (el.hasChildNodes()) {
			el.removeChild(el.lastChild);
		}
	}

	// 지도에 클릭 이벤트를 등록합니다
	// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
	kakao.maps.event
			.addListener(
					map,
					'click',
					function(mouseEvent) {
						searchDetailAddrFromCoords(
								mouseEvent.latLng,
								function(result, status) {
									if (status === kakao.maps.services.Status.OK) {
										var detailAddr = !!result[0].road_address ? result[0].road_address.address_name
												: result[0].address.address_name;

										// 마커를 클릭한 위치에 표시합니다
										selectmarker
												.setPosition(mouseEvent.latLng);
										selectmarker.setMap(map);
										document.getElementById('fulladdress').value = detailAddr;
										document.getElementById('paddress').value = detailAddr;
										document.getElementById('latclick').value = mouseEvent.latLng
												.getLat();
										document.getElementById('lngclick').value = mouseEvent.latLng
												.getLng();

									}
								});
					});

	function searchDetailAddrFromCoords(coords, callback) {
		// 좌표로 법정동 상세 주소 정보를 요청합니다
		geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
	}
})
