<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
    .map_wrap {position:relative;width:100%;height:350px;}
    .title {font-weight:bold;display:block;}
    .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;}
    #centerAddr {display:block;margin-top:2px;font-weight: normal;}
    .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
    #webcam-container{width:40%;display:inline-block;}
    #label-container{width:30%;float:right;}
    #label-container{float:right;display: inline-block;}
    #webCamStop{margin:10px 0px;}
</style>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2be4973aebee73fd9cfc0a65da56d625&libraries=services"></script>
<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.3.1/dist/tf.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@teachablemachine/image@0.8/dist/teachablemachine-image.min.js"></script>
<div id="jspcontent">

	<form id="stockTakingForm" action="${cPath }/stock/stockTaking.do" method="post">
		<input type="hidden" name="prodCode" id="prodCode" />
		<input type="hidden" name="warName" id="warName" />
		<input type="button" class="btn btn-outline-primary btn-sm" value="재고정보" id="webCamStop"/>
	</form>
	
	<div id="webcam-container"></div>
	<div id="label-container"></div>
	<span id="status"></span>
	<span id="map-link"></span>
	
	<div class="map_wrap">
	    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
	    <div class="hAddr">
	        <span class="title">지도중심기준 행정동 주소정보</span>
	        <span id="centerAddr"></span>
	    </div>
	</div>
</div>

<script type="text/javascript">
//------------------------------------------------------------------------------------------------위경도로주소구하기
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(36.324736, 127.420058), // 지도의 중심좌표
        level: 1 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
    infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
    geoFindMe();
// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
searchAddrFromCoords(map.getCenter(), displayCenterInfo);

// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
            
            var content = '<div class="bAddr">' +
                            '<span class="title">법정동 주소정보</span>' + 
                            detailAddr + 
                        '</div>';

            // 마커를 클릭한 위치에 표시합니다 
            marker.setPosition(mouseEvent.latLng);
            marker.setMap(map);

            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
            infowindow.setContent(content);
            infowindow.open(map, marker);
           
        }   
    });
});

// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'idle', function() {
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
});

function searchAddrFromCoords(coords, callback) {
    // 좌표로 행정동 주소 정보를 요청합니다
    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
}

function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
function displayCenterInfo(result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var infoDiv = document.getElementById('centerAddr');

        for(var i = 0; i < result.length; i++) {
            // 행정동의 region_type 값은 'H' 이므로
            if (result[i].region_type === 'H') {
                infoDiv.innerHTML = result[i].address_name;
                break;
            }
        }
    }    
}
//------------------------------------------------------------------------------------------------위경도로주소구하기



//------------------------------------------------------------------------------------------------위경도구하기
latitude = null;
longitude = null;
function geoFindMe() {

		const status = document.querySelector('#status');
		const mapLink = document.querySelector('#map-link');

		mapLink.href = '';
		mapLink.textContent = '';

		function success(position) {
	   	    latitude  = position.coords.latitude;
	   	    longitude = position.coords.longitude;

			status.textContent = '';
//    	    mapLink.href = 'https://www.openstreetmap.org/#map=18/'+latitude+'/'+longitude;
// 			mapLink.innerText = 'Latitude:'+latitude+'°,Longitude:'+longitude+'°';
		}

		function error() {
		  status.textContent = 'Unable to retrieve your location';
		}
	
		if(!navigator.geolocation) {
		  status.textContent = 'Geolocation is not supported by your browser';
		} else {
		  status.textContent = 'Locating…';
		  navigator.geolocation.getCurrentPosition(success, error);
		}
	}

//------------------------------------------------------------------------------------------------위경도구하기


//------------------------------------------------------------------------------------------------webcam
    const URL = "${cPath}/resources/my_model/";

    let model, webcam, labelContainer, maxPredictions, mapX,mapY;
	
    async function init() {
        const modelURL = URL + "model.json";
        const metadataURL = URL + "metadata.json";

        model = await tmImage.load(modelURL, metadataURL);
        maxPredictions = model.getTotalClasses();

        const flip = true; // whether to flip the webcam
        webcam = new tmImage.Webcam(800, 400, flip); // width, height, flip
        await webcam.setup(); // request access to the webcam
        await webcam.play();
        window.requestAnimationFrame(loop);

        document.getElementById("webcam-container").appendChild(webcam.canvas);
        labelContainer = document.getElementById("label-container");
        for (let i = 0; i < maxPredictions; i++) { // and class labels
            labelContainer.appendChild(document.createElement("div"));
        }
    }

    async function loop() {
        webcam.update(); // update the webcam frame
        await predict();
        window.requestAnimationFrame(loop);
    }

    prodCode = null;

    async function predict() {
        const prediction = await model.predict(webcam.canvas);
        for (let i = 0; i < maxPredictions; i++) {
        	var codeName = prediction[i].probability.toFixed(2);
        	codeName = codeName*100;
            const classPrediction =
                prediction[i].className + ": " + codeName + "%";
            labelContainer.childNodes[i].innerHTML = classPrediction;
            
            if(prediction[i].probability.toFixed(2) >= 0.8){
            	prodCode = prediction[i].className;
            }
        }
    }
    
    var wcStop = document.getElementById("webCamStop");

	wcStop.onclick = function(){
   		webcam.stop();
// 		warName = warName.substring(0,2)+"창고";
		$.ajax({
			async : false,
			url : $.getContextPath() + "/info2/warehouseRetrieve.do",
			method : "post",
			data : {warAdd1 : warName.substring(0,2)},
			dataType : "json",
			success : function(resp) {
				document.getElementById("warName").value = resp[0].warName;
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
		var prodTag = document.getElementById("prodCode").value = prodCode;
		$("#stockTakingForm").submit();
    };
//------------------------------------------------------------------------------------------------webcam   	


$(function(){
	warName = null;
	var callback = function(result, status) {
	    if (status === kakao.maps.services.Status.OK) {
		    warName = result[0].region_1depth_name;
	    }
	};
	geocoder.coord2RegionCode(longitude, latitude, callback);
	
	init();
});

</script>
