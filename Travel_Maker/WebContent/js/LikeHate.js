
// 좋아요 체크
likecheck = function(){
	var cnt = 0;
	$.ajax({
		url : '/Travel_Maker/LikeCheck.do',
		async: false,
		type : 'post',
		data : {"tm_b_no" : boardNo, "tm_category_id": category, "tm_id" : logid},
		success : function(res){
			cnt = res.cnt;
		},
		error : function(xhr){
			console.log('좋아요 에러 : ' + xhr.status);
		},
		dataType : 'json'
			
	})
	return cnt;
}



// 싫어요 체크
hatecheck = function(){
	var cnt = 0;
	$.ajax({
		url : '/Travel_Maker/HateCheck.do',
		async: false,
		type : 'post',
		data : {"tm_b_no" : boardNo, "tm_category_id": category, "tm_id" : logid},
		success : function(res){
			cnt = res.cnt;
		},
		error : function(xhr){
			console.log('싫어요 에러 : ' + xhr.status);
		},
		dataType : 'json'
			
	})
	return cnt;
}





// 좋아요 적용
addlike = function(){
	
	$.ajax({
		url		: '/Travel_Maker/BoardLike.do',
		type	: 'post',
		data : {"tm_b_no" : boardNo, "tm_category_id": category, "tm_id" : logid},
		success	: function (res) {
		},
		error	: function (xhr) {
			console.log('좋아요 적용애러 : ' + xhr.status);
		},
		dataType: 'json'
	})	
}

// 싫어요 적용
addhate = function(){
	
	$.ajax({
		url		: '/Travel_Maker/BoardHate.do',
		type	: 'post',
		data : {"tm_b_no" : boardNo, "tm_category_id": category, "tm_id" : logid},
		success	: function (res) {
		},
		error	: function (xhr) {
			console.log('싫어요 적용애러 : ' + xhr.status);
		},
		dataType: 'json'
	})	
}





// 좋아요 취소
cancelLike = function(){
	$.ajax({
		url		: '/Travel_Maker/CancelLike.do',
		type	: 'post',
		data : {"tm_b_no" : boardNo, "tm_category_id": category, "tm_id" : logid},
		success	: function (res) {
		},
		error	: function (xhr) {
			console.log('좋아요 취소 애러 : ' + xhr.status);
		},
		dataType: 'json'
	})
}

// 싫어요 취소
cancelHate = function(){
	$.ajax({
		url		: '/Travel_Maker/CancelHate.do',
		type	: 'post',
		data : {"tm_b_no" : boardNo, "tm_category_id": category, "tm_id" : logid},
		success	: function (res) {
		},
		error	: function (xhr) {
			console.log('싫어요 취소 애러 : ' + xhr.status);
		},
		dataType: 'json'
	})
}