 /**
 * 
 */
currentPage = 1;


// 조회수 증가
hitUpdate = function(){
	$.ajax({
		url : '/Travel_Maker/HitUpdate.do',
		type : 'get',
		data : {"seq" : vidx},
		success : function(res){
			console.log("조회수 증가");
		},
		error : function(xhr){
			console.log("조회수 에러: " + xhr.status);
		},
		dataType : 'json'	
	})

}

// 글작성 완료 -> 보내기 --> 아이작스 안 쓰고 폼에서 action 줘서 서블릿으로 바로 subbit했음!
//sendServer = function(){
//	
//	formdata = $('.modal-body form').serialize();
//	console.log(formdata);
//	
////	$.ajax({
////		url : '/Travel_Maker/TravelBoardInsert.do',
////		type : 'post',
////		enctype: 'multipart/form-data',
////		data : formdata,
////		processData: false, //필수
////        contentType: false, // 필수
////        cache: false,
////		success : function(res){
////			alert(res.sw);
////			listPageServer(1);
////		},
////		error : function(xhr){
////			alert('상태 : ' + xhr.status);
////		},
////		dataType : 'json'
////	
////	})
//	
//	//선생님
//    $.ajax({
//        type: "POST",
//        enctype: 'multipart/form-data',
//        url: '/Travel_Maker/TravelBoardInsert.do',
//        data: data,
//        processData: false, //필수
//        contentType: false, // 필수
//        cache: false,
//        timeout: 600000,
//        success: function (data) {
//
//        	alert(res.sw);
//        	listPageServer(1);
//
//        },
//        error: function (e) {
//
//        	alert('상태 : ' + xhr.status);
//
//        }
//    })
	
//}





// 내용 페이지로 출력
listPageServer = function(page){
	
	$.ajax({
		url : '/Travel_Maker/TravelBoardList.do',
		type : 'post',
		data : {"page": page},
		dataType : 'json',
		success : function(res){
			
			str = '<div class="list" id="boardList">';
			
			// 내용 출력 부분
			$.each(res.datas, function(i, v){
				

										str += ' <tr>';
										str += ' 	<th scope="row">'+v.TM_B_NO+'</th>';
										str += '    	<td>'+v.TM_ID+'</td>';
										str += '        <td class="action" id="list" idx="' + v.TM_B_NO + '"><a href = "/Travel_Maker/TravelSelect.do?TM_B_NO='+v.TM_B_NO +'">'+v.TM_B_TITLE+'</td>';
										str += '        <td>'+v.TM_B_DATE+'</td>';
										str += '        <td>'+v.TM_B_HIT+'</td>';
										str += '     </tr>';
				                                                                                                                                                                             
			})     
			
			str+= '</div> ';
			
			
			$('#box').html(str);
			
			
			// 이전버튼
			$('#pageList').empty();
			if(res.spage > 1){
				vpre = '<ul class="pager">';
                vpre += '<a class="page-link" href="#" id="prev">이전</a>';
                vpre += '</ul>';
				$('#pageList').append(vpre);
			}
			
			// 페이징 처리
			console.log(res.spage, res.epage, res.tpage);
			vpage = '<ul class="pagination justify-content-end mb-0">'
				
				for(i = res.spage; i <=res.epage; i++){
					if(i == currentPage ){
						vpage += '<li class="page-item"><a class="page-count" href="#">'+i+'</a></li>'
					}
					else {
						vpage += '<li class="page-item"><a class="page-count" href="#">'+i+'</a></li>'
					}
				}
				
			vpage += '</ul>';
			$('#pageList').append(vpage);

			
			
			// 다음 버튼 (endpage < totalpage)	
			if(res.epage < res.tpage){
				vnext = '<ul class="pager">';
				vnext += '<a class="page-link" href="#" id="next">다음</a>';
				vnext += '</ul>';
				$('#pageList').append(vnext);
			}
	
		},
		error : function(xhr){
			console.log("상태 : " + xhr.status);
		}
	})
}





//댓글 삭제 부분
replyDelete = function(t){
	
	$.ajax({
		
		url : '/Travel_Maker/TravelReplyDelete.do',
		type : 'get',
		data : {"TM_BC_NO" : delReNo},
		success : function(res){
			alert('삭제되었습니다.');
			location.reload();
		},
		error : function(xhr){
			console.log('왜안돼')
		},
		dataType : 'json'
		
		
		
	})
	
	
}

//게시글 댓글 전체 삭제 부분
/*replyDeleteAll = function(t){
	
	$.ajax({
		
		url : '/Travel_Maker/TravelReplyDeleteAll.do',
		type : 'get',
		data : {"TM_B_NO" : vidx},
		success : function(res){
			location.reload();
		},
		error : function(xhr){

		},
		dataType : 'json'
		
		
	})	
}*/

//댓글 전체 삭제 --> 게시글 삭제 용
replyDeleteAll = function(vidx){
	
	$.ajax({
		
		url : '/Travel_Maker/qboard/redeleteall.do',
		type : 'get',
		data : {"TM_B_NO" : vidx},
		success : function(res){
			location.reload();
		},
		error : function(xhr){

		},
		dataType : 'json'
	 
	})

}


//댓글 수정 부분
replyModiServer = function(){
	
	$.ajax({
		
		url : '/Travel_Maker/TravelReplyModify.do',
		type : 'post',
		data : {"TM_BC_NO" : rebcNo, "TM_BC_CONTENT" : modiReCont},
		success : function(res){
			alert('수정되었습니다.');
			location.reload();
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
		},
		dataType : 'json'
		
	})
	
}

