/**
 * 유저문답게시판
 */

currentPage = 1;

//댓글 전체 삭제 --> 게시글 삭제 용
replyDeleteAll = function(vidx){
	
	$.ajax({
		
		url : '/Travel_Maker/qboard/redeleteall.do',
		type : 'post',
		data : {"TM_B_NO" : vidx},
		success : function(res){
			location.reload();
		},
		error : function(xhr){

		},
		dataType : 'json'
	 
	})

}
	
//댓글 삭제
replyDelete = function(t){
	
	$.ajax({
		
		url : '/Travel_Maker/qboard/redelete.do',
		type : 'post',
		data : {"TM_BC_NO" : delReNo},
		success : function(res){
			alert('삭제되었습니다.');
			
			location.reload();
		},
		error : function(xhr){
			console.log("상태 : " + xhr.status);
		},
		dataType : 'json'
		
		
		
	})
}

//댓글수정
replyModiServer = function() {
	
	$.ajax({
		url		: '/Travel_Maker/qboard/reupdate.do',
		type	: 'post',
		data	: {"tm_bc_no" : rebcNo, "tm_bc_content" : modiReCont},
		success : function(res) {
			alert('수정되었습니다.');
			location.reload();
		},
		error	: function(xhr) {
			console.log('상태 : ' + xhr.status);
		},
		dataType: 'json'
	})
}

//글 조회 시 댓글출력
//replyListServer = function(vidx) {
//	
//	$.ajax({
//		
//		url		: '/Travel_Maker/qboard/relist.do',
//		type	: 'get',
//		data	: {"tm_b_no" : vidx},
//		success	: function(res) {
//				str = ""
//				
//				$.each(res, function(i, v) {
//					
//					str += ' <tr class="replylist">';
//					str += ' 	<th scope="row" id="replyno">'+v.TM_BC_NO+'</th>';
//					str += '        <td id="replyid">'+v.TM_ID+'</td>';
//					str += '        <td id="replycont">'+v.TM_BC_CONTENT.replaceAll("\r","").replaceAll("\n", "<br>")+'</td>';
//					str += '        <td><input  type="button" value="댓글수정2"  id="replymodify" >';
//					str += '        <input  type="button" value="댓글삭제2"  id="replydelete" ></td>';
//					str += '     </tr>';
//				})
//				
//				$('#replylist').html(str);
//		},
//		error	: function(xhr) {
//				alert('상태 : ' + xhr.status);
//		},
//		dataType : 'json'
//	})
//}

sendServer = function() {
	formdata = $('form').serializeArray();
	console.log(formdata)
	
	$.ajax({
		url		: '/Travel_Maker/qboard/insert.do',
		method 	: 'post',
		data	: formdata,
		success	: function(res) {
				 alert('등록되었습니다')
				 location.href='/Travel_Maker/qboard/list.do';
		},
		error	: function(xhr) {
			console.log('서버 : ' + xhr.status)
		},
		dataType: 'json'
	})
}

listPageServer = function(page) {
	
	$.ajax({
		url		: '/Travel_Maker/qboard/list.do',
		method	: 'get',
		data	: {'page' : page},
		success	: function(res) {
			
	        str  = '            <div class="table-responsive project-list">'
	        str += '                <table class="table project-table table-centered table-nowrap">'
//	        str += '                    <thead>'
//	        str += '                        <tr>'
//	        str += '                            <th scope="col">No</th>'
//	        str += '                            <th scope="col">제목</th>'
//	        str += '                            <th scope="col">작성일</th>'
//	        str += '                            <th scope="col">작성자</th>'
//	        str += '                        </tr>'
//	        str += '                    </thead>'
//	        str +='				<tbody>'
	
			$.each(res.datas, function(i, v) { 
				
				

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
//					
//					str +='                <tr>'
//					str +='				   <th scope="row">' + v.bonum + '</th>'
//					str +='					   <td class="action bunum="' + v.bonum + '"><a href = "/Travel_Maker/qboard/select.do?tm_b_no='+v.bonum +'">'+ v.title + '</a></td>'
//					str +='                    <td>' + v.date + '</td>'
//					str +='                    <td>' + v.id + '</td>'
//					str +='                </tr>'
//					
//			})
//				str +='            </tbody>'
				str +='        </table>'
				str +='    </div>'
//				
//				$('.card-body').html(str)
				
				// 이전버튼
				$('#pageList').empty();
				if(res.startPage > 1){
					vpre = '<ul class="pager">';
	                vpre += '<a class="page-link" href="#" id="prev">이전</a>';
	                vpre += '</ul>';
					$('#pageList').append(vpre);
				}
				
				// 페이징 처리
				console.log(res.startPage, res.endPage, res.totalPage);
				vpage = '<ul class="pagination justify-content-end mb-0">'
					
					for(i = res.startPage; i <=res.endPage; i++){
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
				if(res.endPage < res.totalPage){
					vnext = '<ul class="pager">';
					vnext += '<a class="page-link" href="#" id="next">다음</a>';
					vnext += '</ul>';
					$('#pageList').append(vnext);
				}
		
			},
				
			error	: function(xhr) {
				console.log('서버 상태 : ' + xhr.status)
			},
			dataType: 'json'
		})			
}