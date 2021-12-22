listmyinfo = function(){ // t : 클릭한 제목(요소), 등록버튼일수도
	$.ajax({
		
		url : '/Travel_Maker/MyInfo.do',
		type : 'post',
		success : function(res){
			console.log("성공");
		},
		error : function(xhr){
			console.log("상태 : " + xhr.status);
			alert("로그인이 필요한 서비스입니다.");
		},
		dataType : 'json'
		
	})
}


currentPage = 1;

listPageServer = function(page){
	$.ajax({
	url : '/Travel_Maker/ListMyBoardServlet.do',
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
									str += '        <td class="action" id="list">'+v.TM_B_TITLE+'</td>';
									str += '        <td>'+v.TM_B_DATE+'</td>';
									str += '        <td>'+v.TM_B_HIT+'</td>';
									str += ' </tr>';
                                       
									
									
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
		alert("로그인이 필요한 서비스입니다.");
	}
	})
}
