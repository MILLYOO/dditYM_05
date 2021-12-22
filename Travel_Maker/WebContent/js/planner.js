$(function(){
	// 리스트 출력부분
	markers=[];
	select = [];
	$.ajax({
		url : '/Travel_Maker/ScrapList.do',
		type : 'get',
		dataType : 'json',
		success : function(res) {
			$.each(res, function(i, v) {
				
				var imgName = v.img_name;
				
				var id = v.tm_search_id;
				var title = v.tm_search_name;
				var latlng = new kakao.maps.LatLng(
						v.tm_search_y,
						v.tm_search_x);
				var img = v.img_id;
				var addr = v.tm_search_addr;
				var tel = v.tm_search_tel;
				var cate = v.tm_search_cate;
				var con = v.tm_search_con;
				var content = '<div class="col-12">'+
				'<div class="card" id="'+id+'">'+
				'<div class="row no-gutters">'+
				'<div class="col-4">'+
				'<img src="../imageForSearch/' + imgName + '.jpg" alt="" class="card-img">'+
				'</div>'+
				'<div class="col-8" id="'+title+'">'+
				'<div class="card-body">'+
				'<p class="card-text">장소명 : '+title+'<br>주소 : '+addr+'</p>'+
				'<div class="card-X" style="display:none;">'+v.tm_search_x+'</div>'+
				'<div class="card-Y" style="display:none;">'+v.tm_search_y+'</div>'+
				'</div>'+
				'</div>'+
				'</div>'+
				'</div>'+
				'</div>'
				$('.cardinfo').append(content);
			})
		},
		error : function(xhr){
			console.log("상태: " + xhr.status);
		}
	})
	
	var mapContainer = document.getElementById('pl_map_area'), // 지도를 표시할 div  
	mapOption = { 
		center: new kakao.maps.LatLng(36.50721692146517, 127.99844701458512), // 지도의 중심좌표
		level: 12 // 지도의 확대 레벨
	};
	
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	var positions = [];
// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다
	
// 마우스 호버시 마커뜨게함.
	$(document).off().on('mouseover','.card', function(){
		var marker = new kakao.maps.Marker({
			  map: map, // 마커를 표시할 지도
			  position: new kakao.maps.LatLng($(this).find('.card-Y').text(), $(this).find('.card-X').text()) // 마커의 위치
		  });
		
		marker.setMap(map);
		
		
		var pos = marker.getPosition();
		map.panTo(pos);
		
// 마우스 클릭시 실행 마커만들기
		$(this).off().on('click', function(){
			var content = 
				'<div class="col-1 cardcon" style="background-color:#F1F2F5; border-radius:10%; margin:11px">'+
					'<div class="card2" id="'+$(this).attr("id")+'">'+
						'<img src="../page/images/여행지1.jpg" alt="" class="card-img-top" style="    margin-top: 2vh;" />'+
						'<div class="card-body2">'+ 
						'<p class="card-title" style="font-size:0.8em; text-align:center;">'+$(this).find(".col-8").attr("id")+'</p>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<i class="fas fa-angle-double-right fa-3x"></i>';
			$('.makeinfo').append(content);
			select.push($(this).attr("id"));
			console.log(select);
			var marker = new kakao.maps.Marker({
				  map: map, // 마커를 표시할 지도
				  position: new kakao.maps.LatLng($(this).find('.card-Y').text(), $(this).find('.card-X').text()) // 마커의 위치
			  });
			  
			  // 마커에 표시할 인포윈도우를 생성합니다 
			  var infowindow = new kakao.maps.InfoWindow({
				  content: $(this).find(".col-8").attr("id") // 인포윈도우에 표시할 내용
			  });
			  
			  // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
			  // 이벤트 리스너로는 클로저를 만들어 등록합니다 
			  // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
			  kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
			  kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
		  

			// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
			
			function makeOverListener(map, marker, infowindow) {
				return function() {
					infowindow.open(map, marker);
				};
			}
			
			// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
			function makeOutListener(infowindow) {
				return function() {
					infowindow.close();
				};
			}
			$(this).parent(".col-12").remove();
			markers.push(marker);
		})
		
		
		$(document).on('mouseout',this, function(){
			marker.setMap(null);
		})
	})
})
