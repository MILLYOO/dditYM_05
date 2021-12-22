
// 좋아요 체크
Searchlikecheck = function(){
	var cnt = 0;
	$.ajax({
		url : '/Travel_Maker/SearchLikeCheck.do',
		async: false,
		type : 'post',
		data : {"tm_search_id" : searchNo},
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



// 좋아요 적용
addsearchlike = function(){
	
	$.ajax({
		url		: '/Travel_Maker/SearchLikeAdd.do',
		async: false,
		type	: 'post',
		data : {"tm_search_id" : searchNo},
		success	: function (res) {
			console.log("좋아요를 누르셨습니다.")
		},
		error	: function (xhr) {
			console.log('좋아요 적용애러 : ' + xhr.status);
		},
		dataType: 'json'
	})	
}




// 좋아요 취소
cancelsearchlike = function(){
	$.ajax({
		url		: '/Travel_Maker/SearchLikeCancel.do',
		async: false,
		type	: 'post',
		data : {"tm_search_id" : searchNo},
		success	: function (res) {
			console.log("좋아요가 취소 되었습니다.")
		},
		error	: function (xhr) {
			console.log('좋아요 취소 애러 : ' + xhr.status);
		},
		dataType: 'json'
	})
}

