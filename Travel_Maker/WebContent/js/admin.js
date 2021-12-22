/**
 * 관리자 js
 */

// 베스트 여행지 메인 목록
bestDestInsert  = function(){
	$.ajax({
		url : '/Travel_Maker/BestDestination.do',
		type : 'post',
		success : function(res){
			str = "";
			$.each(res,function(i,v){
				str += '<div class="col-lg-4 col-md-4 col-sm-6">';
				str += '<div class="fh5co-blog animate-box">';
				str += '<div class="blog-text">';
				str += '<div class="prod-title">';
				str += '<h3><a href="#" onclick="return false;">'+ v.addr +'</a></h3>';
				str += '<img class="maincity" alt="'+ v.name +'.jpg" src="../page/images/'+ v.name +'.jpg">';
				str += '<span class="posted_by">'+ v.name +'</span> <span class="comment">'
				str += '<a href=""onclick="return false;">LIKE +'+ v.like +'<i class="icon-bubble2">♥</i></a></span>';
				str += '<p>'+ v.con +'</p>';
				str += '</div></div></div></div>'
			})
			$('#bestDiv').append(str);
		},
		error : function(xhr){F
			console.log('상태 : ' + xhr.status);
		},
		dataType : 'json'
	})
}


// 1:1 문의 답변 삭제
qnaReDeleteServer = function(){
	$.ajax({
		url : '/Travel_Maker/AdminQnaReDelete.do',
		type : 'post',
		data : {"delReNo" : delReNo,
				"delBno" : delBno},
		success : function(res){
			alert("삭제되었습니다.");
			location.href = "/Travel_Maker/AdminQnaSelect.do?tm_b_no=" + res.writeNo;
		},
		error : function(xhr){
			console.log('상태 : ' + xhr.status);
		},
		dataType : 'json'
	})
}



// 1:1 문의 답변 수정
qnaReUpdateServer = function(){
	$.ajax({
		url : '/Travel_Maker/AdminQnaReUpdate.do',
		type : 'post',
		data : {"modiReCont" : modiReCont,
			    "rebNo" : rebNo,
			    "rebcNo" : rebcNo},
		success : function(res){
			location.href = "/Travel_Maker/AdminQnaSelect.do?tm_b_no=" + res.writeNo;
		},
		error : function(xhr){
			console.log('상태 : ' + xhr.status);
		},
		dataType : 'json'
	})
}


// 1:1 문의 답변 등록
qnaReInsertServer = function(){
	$.ajax({
		url : '/Travel_Maker/AdminQnaReInsert.do',
		type : 'post',
		data : {"memId" : memId,
				"num" : num,
				"reWriter" : reWriter,
				"replycont" : replycont
			   },
		success : function(res){
			location.href = "/Travel_Maker/AdminQnaSelect.do?tm_b_no=" + res.writeNo;
		},
		error : function(xhr){
			console.log('상태 : ' + xhr.status);
		},
		dataType : 'json'
	})
}


// 1:1 문의 게시글 삭제
qnaDeleteServer = function(){
	$.ajax({
		url : '/Travel_Maker/AdminQnaDelete.do',
		type : 'get',
		data : {"modiNo" : modiNo},
		success : function(res){
			alert("삭제되었습니다.");
			location.href = "/Travel_Maker/AdminQnaList.do";
		},
		error : function(xhr){
			console.log('상태 : ' + xhr.status);
		},
		dataType : 'json'
	})
}


// 1:1 문의 게시글 수정
qnaUpdateServer = function(){
	$.ajax({
		url : '/Travel_Maker/AdminQnaUpdate.do',
		type : 'post',
		data : {"modiTitle" : modiTitle,
			    "modiCont" : modiCont,
			    "modiNo" : modiNo},
		success : function(res){
			location.href = "/Travel_Maker/AdminQnaSelect.do?tm_b_no=" + res.writeNo;
		},
		error : function(xhr){
			console.log('상태 : ' + xhr.status);
		},
		dataType : 'json'
	})
}


// 회원정보수정
memInfoUpdate = function(){
	$.ajax({
		url : '/Travel_Maker/MemManageUpdate.do',
		type : 'post',
		data : formdata,
		success : function(res){
			location.href = "/Travel_Maker/MemManage.do";
		},
		error : function(xhr){
			console.log('상태 : ' + xhr.status);
		},
		dataType : 'json'
	})
}

// 회원 삭제 (탈퇴 O)
memInfoDelete = function(){
	$.ajax({
		url : '/Travel_Maker/MemManageDelete.do',
		type : 'post',
		data : {"modiId" : modiId},
		success : function(res){
			alert("해당 회원이 탈퇴되었습니다.");
			location.href = "/Travel_Maker/MemManage.do";
		},
		error : function(xhr){
			console.log('상태 : ' + xhr.status);
		},
		dataType : 'json'
	})
}

