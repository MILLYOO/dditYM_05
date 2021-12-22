let unit = 100;
$.timeFormat=function(timer){
	let minute = Math.trunc(timer / 60);
	let second = timer % 60;
	return minute + ":" + second;
}

var timerJob = null;
$.fn.sessionTimer=function(obj){ // 세션 타이머라는 함수를 만듬.
	const TIMEOUT = obj.timeout;
	let msgArea = obj.msgArea;
	let timer = TIMEOUT;
	this.html($.timeFormat(TIMEOUT));
	let timerTag = this;
	if(!msgArea){
		//generate  -- 만들기.
	}
	msgArea.hide().on("click", ".controlBtn",function(){
		console.log(this.id)
		if(this.id == "yesBtn"){
//			sessionTimer.html($.timeFormat(TIMEOUT))
			timer = TIMEOUT;
			$.ajax({
//				url : "", 현재 페이지의 url로
//				data : "", 보낼 데이터 없음
				method : "head", // 기본 get방식 들어오는 body는 안받겠따.
//				dataType : "", 응답 데이터 형식
//				success : function(resp) { // request 데이터 없음.
//
//				},
//				error : function(xhr, errorResp, error) {
//					console.log(xhr);
//					console.log(errorResp);
//					console.log(error);
//				}
			});
		}
		msgArea.hide();
	});
	
	timerJob = setInterval(() => {
//		this.hmtl.(TIMEOUT); 이 형식으로 하면 this는 sessionTimer를 가르키는게 아닌 setInterval의 부모 window를 가리킴.
		if(--timer > 0 ){
			timerTag.html($.timeFormat(timer));		
		}else{
			clearInterval(timerJob);
			location.reload();
		}
	}, 1 * unit);
	
	setTimeout(function(){
		$("#msgArea").show();
	}, (TIMEOUT - 60) * unit);
	
	return this;
}
