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
