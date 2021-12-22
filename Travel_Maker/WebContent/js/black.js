/**
 * 블랙리스트 관련 java script
 */

currentPage = 1;

listPageServer = function(page) {

	$.ajax({
		url		: '/Travel_Maker/black/list.do',
		method	: 'get',
		data	: {'page' : page}, 
		success	: function(res) {
			str  = '<div>'
				str += '	<table border="1">'
					str += '		<tr>'
						str += '			<th>아이디</th>'
							str += '			<th>차단사유</th>'
								str += '			<th>차단일</th>'
									str += '			<th>차단한 관리자</th>'
										str += '			<th>권한 수정</th>'
											str += '		</tr>'

												$.each(res.datas, function(i, v) { 

													//id = v.id
													str += '		<tr>'
														str += '			<td>' + v.id + '</td>'
														str += '			<td>' + v.rs + '</td>'
														str += '			<td>' + v.date + '</td>'
														str += '			<td>' + v.admn + '</td>'
														str += '			<td><input type="button" id="replymodify" class="action" vid="' + v.id + '" name="btom" value="일반회원 전환" onclick="blackToMem()"></td>'
														str += '		</tr>'

												})
												str += '</table>'
													str += '</div>'

														$('.blackList').html(str)

														//이전 버튼
//														$('#pageList').empty();
//			if(res.startPage > 1){
//				vpre = '<ul class="pager">'
//					vpre += '<li><a class="page-link" href="#" id="prev">이전</a></li>'
//						vpre += '</ul>'
//							$('#pageList').append(vpre)
//			}

			//페이징 처리
//			console.log(res.startPage, res.endPage, res.totalPage)
//			vpage  = '<ul  class="pagination justify-content-end mb-0">'
//				for(i=res.startPage; i<=res.endPage; i++){
//					
//					
//					if(i == currentPage){
//						vpage += '<li class="page-item"><a class="aa page-count" href="#">' + i +'</a></li>'
//					}else{
//						vpage += '<li class="page-item"><a class="aa page-count" href="#">' + i +'</a></li>'
//					}
//					
//					
//				}
//			vpage += '</ul>'	//html쓰면 이전, 다음이 페이지 버튼 덮어쓰기 되니까 못 씀
//				$('#pageList').append(vpage)

				//다음 버튼
//				if(res.endPage < res.totalPage){
//					vnext  = '<ul class="pager">'
//						vnext += '<a class="page-link" href="#" id="next">다음</a>'
//							vnext += '</ul>'
//								$('#pageList').append(vnext)
//				}

		},
		error	: function(xhr) {
			console.log('서버 상태 : ' + xhr.status)
		},
		dataType: 'json'
	})



	blackToMem = function() {

		$.ajax({
			url		: '/Travel_Maker/black/btom.do',
			method	: 'post',
			data	: {'id' : vid},
			success	: function(res) {
				alert(vid + "의 등급변경에 " + res.msg + "하였습니다")
				listPageServer(1)
			},
			error	: function(xhr) {
				console.log('상태 : ' + xhr.status)
			},
			dataType: 'json'
		})
	}




}