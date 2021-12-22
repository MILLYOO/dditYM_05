<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${faceTokenSession}">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- 부트스트랩 -->
<!--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script> -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.form.min.js"></script>
   <!-- 메뉴 폰트 -->
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <!-- css경로 -->
    <link rel="stylesheet" href="${cPath}/resources/css/sb-admin-2.css">
    <!-- 폰트어썸아이콘 갖다붙이는 link -->
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
	<!-- 경고창, confirm 모달 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<!-- SweetAlert2 --> 
	<link rel="stylesheet" href="${cPath }/resources/css/bootstrap-4.min.css">
	<!-- SweetAlert2 -->
	<script src="${cPath }/resources/js/sweetalert2.min.js"></script>
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
<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.3.1/dist/tf.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@teachablemachine/image@0.8/dist/teachablemachine-image.min.js"></script>
</head>
<div id="jspcontent">
	<div id="webcam-container"></div>
	<div id="label-container"></div>
</div>

<form:form id="loginForm" name="loginForm" action="${cPath}/login/faceLoginProcess.do" method="POST">
	<input type="hidden" name="memId" id="memId"/>
	<input type="hidden" name="faceToken" id="faceToken"/>
</form:form>

	
<script>
	//학습시킨 모델에 대한 URL
	const URL = "${cPath}/resources/face_model/";

    let model, webcam, labelContainer, maxPredictions;

    // Load the image model and setup the webcam
    async function init() {
        const modelURL = URL + "model.json";
        const metadataURL = URL + "metadata.json";

        // load the model and metadata
        // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
        // or files from your local hard drive
        // Note: the pose library adds "tmImage" object to your window (window.tmImage)
        model = await tmImage.load(modelURL, metadataURL);
        maxPredictions = model.getTotalClasses();

	        // Convenience function to setup a webcam
	        const flip = true; // whether to flip the webcam
	        webcam = new tmImage.Webcam(450, 450, flip); // width, height, flip
	        await webcam.setup(); // request access to the webcam
	        await webcam.play();
	        window.requestAnimationFrame(loop);

	        // append elements to the DOM
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

	    async function predict() {
	        const prediction = await model.predict(webcam.canvas);
	        for (let i = 0; i < maxPredictions; i++) {
	        	//학습한 모델의 라벨 : 인식률
	            const classPrediction = 
	            	prediction[i].className + ": " + prediction[i].probability.toFixed(2);
	            
	            if(prediction[i].probability.toFixed(2)*100 == 100){ //인식 모델과 100% 일치 하면
	            	webcam.stop()//카메라 정지
	            	Swal.fire({//Sweet alert API : confirm 창
	                    title: "아이디 : "+ prediction[i].className,
	                    text: "로그인 하시겠습니까?",
	                    icon: "question",
	                    showCancelButton: true,
	                    confirmButtonColor: "#3085d6",
	                    cancelButtonColor: "#d33",
	                    confirmButtonText: "로그인",
	                    cancelButtonText: "취소"
				 	}).then((result) => {
				 		if (result.isConfirmed) {
				 			//인식한 유저의 아이디 전송을 위한 세팅
							document.getElementById("memId").value = prediction[i].className;
							//발급한 토큰 전송을 위한 세팅
							$("#faceToken").val($("meta[name=_csrf]").attr("content"));
							loginForm.target="main";    //메인창의 이름값으로 타겟
							loginForm.submit();
	 					    self.close();
				 		  } else {
				 			 location.reload();
				 		  }
				 		})
				 		
					}
	            }
	        }
		
	    window.onload = function(){
	    	init();
	    }
</script>
</body>
</html>