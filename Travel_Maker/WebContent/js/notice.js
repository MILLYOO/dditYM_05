/**
 * 공지사항
 */
currentPage = 1;
//조회수
hitUpdate = function(){

	$.ajax({
		url : '/Travel_Maker/NoticeHitUpdate.do',
		type : 'get',
		data : {"modiNo" : modiNo},
		success : function(res){
			console.log("조회수 증가");
		},
		error : function(xhr){
			console.log("상태 조회수: " + xhr.status);
		},
		dataType : 'json'	
	})

}

//글 삭제 버튼
noticeDeleteServer = function(){
	$.ajax({
		url : '/Travel_Maker/NoticeDelete.do',
		type : 'get',
		data : {"modiNo" : modiNo},
		success : function(res){
			alert("삭제되었습니다.");
			location.href = "/Travel_Maker/NoticeList.do";
		},
		error : function(xhr){
			console.log('상태 : ' + xhr.status);
		},
		dataType : 'json'
	})
}


//글 수정 등록 버튼
noticeUpdateServer = function(){
	$.ajax({
		url : '/Travel_Maker/NoticeUpdate.do',
		type : 'post',
		data : {"modiTitle" : modiTitle,
			"modiCont" : modiCont,
			"modiNo" : modiNo},
			success : function(res){
				alert("수정되었습니다.");
				location.reload();
			},
			error : function(xhr){
				console.log('상태 : ' + xhr.status);
			},
			dataType : 'json'
	})
}


//글쓰기 등록 버튼
sendServer = function(){

//	formdata = $('form').serializeArray();

	$.ajax({
		url : '/Travel_Maker/NoticeInsert.do',
		type : 'post',
		data : {"noId" : noId,
			"noTitle" : noTitle,
			"noContent" : noContent},
			success : function(res){
				alert("등록되었습니다.");
				location.href = "/Travel_Maker/NoticeList.do";
			},
			error : function(xhr){
				reload('상태 : ' + xhr.status);
			},
			dataType : 'json'
	})
}


//목록
//listServer = function(){
//$.ajax({
//url : '/Travel_Maker/NoticeList.do',
//method : 'get',
//dataType : 'json',
//success : function(res){
////location.href = "이동할페이지주소...";
//str = "";
//$.each(res,function(i,v){
//str += "<tr>";
//str += '<th scope="row">'+v.no+'</th>';
//str += '<td><a class="clickTitle" href="/Travel_Maker/NoticeBoardSelect.do?tm_b_no='+ v.no +'">'+v.title+'</a></td>';
//str += '<td>' + v.writer +'</td>';
//str += '<td>' + v.date +'</td>';
//str += '<td>' + v.hit + '</td></tr>';
//})
//$('#tbody').html(str);
//},
//error : function(xhr){
//alert('상태 : ' + xhr.status);
//}
//})
//}


//페이징 처리 한 목록
//listPageServer = function(page){

//$.ajax({
//url : '/Travel_Maker/NoticeList.do',
//type : 'post',
//data : {"page" : page},
//success : function(res){
//str = "";
//$.each(res.datas,function(i,v){
//str += "<tr>";
//str += '<th scope="row">'+v.no+'</th>';
//str += '<td><a class="clickTitle" href="/Travel_Maker/NoticeBoardSelect.do?tm_b_no='+ v.no +'">'+v.title+'</a></td>';
//str += '<td>' + v.writer +'</td>';
//str += '<td>' + v.date +'</td>';
//str += '<td>' + v.hit + '</td></tr>';
//})
//$('#tbody').html(str);

//$('#pagelist').empty();	// 새로 append하기 전에 비워주기

//// 이전 버튼
//if(res.spage > 1){
//vpre = '<ul id="btnPre" class="pagination justify-content-end mb-0">';
//vpre += '<li class="page-item"><a class="page-link previous" href="#">&lt;</a></li></ul>';
//$('#pagelist').append(vpre);
//}

//// paging처리
//vpage = '<ul id="btnPage" class="pagination justify-content-end mb-0">';
//for(i = res.spage; i <= res.epage; i++){
//if(i == currentPage){
//vpage += '<li class="page-item active"><a class="page-link paging" href="#">'+ i +'</a></li>';
//}else{
//vpage += '<li class="page-item"><a class="page-link paging" href="#">'+ i +'</a></li>';
//}
//}
//vpage += '</ul>';
//$('#pagelist').append(vpage);

//// 다음 버튼
//if(res.epage < res.tpage){
//vnext = '<ul id="btnNe" class="pagination justify-content-end mb-0">';
//vnext += '<li class="page-item"><a class="page-link next" href="#">&gt;</a></li></ul>';
//$('#pagelist').append(vnext);
//}

//},
//error : function(xhr){
//alert('상태 : ' + xhr.status);
//},
//dataType : 'json'
//})
//}

