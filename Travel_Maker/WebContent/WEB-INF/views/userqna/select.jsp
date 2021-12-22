<%@page import="tm.comm.vo.ReplyVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="tm.board.service.UserQnaServiceImple"%>
<%@page import="tm.board.service.IUserQnaBoardService"%>
<%@page import="tm.member.vo.MemberVO"%>
<%@page import="tm.comm.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	BoardVO vo = (BoardVO)request.getAttribute("boardVO");
	MemberVO memvo = (MemberVO)session.getAttribute("memVO");
	List<ReplyVO> reList = (List<ReplyVO>)request.getAttribute("reList");
	
	IUserQnaBoardService boardService = UserQnaServiceImple.getInstance();
	
	int tm_author = -1;
	if(memvo!=null){
		tm_author = boardService.checkNotUser(memvo.getTm_id());
}
	//------------------------------------------------------------------
	String loginId = "nonUser";
	
	if(memvo!=null){
		loginId = ((MemberVO)session.getAttribute("memVO")).getTm_id();
	}

	//------------------------------------------------------------------


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script src="<%=request.getContextPath() %>/js/qboard.js"></script>
<script src="<%=request.getContextPath() %>/js/LikeHate.js"></script>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/page/css/default.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/page/css/style2.css">

<style type="text/css">
h1 {
	margin: 20px 20px 20px 20px;
	text-align: center;
}

body {
	font-family: 'Do Hyeon', sans-serif;
}

.modal-title {
	text-align: center;
}

#pagelist {
	width: 200px;
}

.page-link {
	width: 50px;
}

.pager, .pagination {
	width: 100px;
	float: left;
}

.card-body {
	-ms-flex: 1 1 auto;
	flex: 1 1 auto;
	min-height: 1px;
	padding: 1.25rem;
}

.card {
	border: none;
	margin-bottom: 24px;
	-webkit-box-shadow: 0 0 13px 0 rgba(236, 236, 241, .44);
	box-shadow: 0 0 13px 0 rgba(236, 236, 241, .44);
}

.avatar-xs {
	height: 2.3rem;
	width: 2.3rem;
}

.card:hover{
	box-shadow: 1px 1px 20px #ddd;
}
.table-responsive{
	background-color: aliceblue;
}

.table {
    background-color: aliceblue;
}
</style>

<script>

$(document).ready(function() {
	$("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp")
	$("#footers").load("<%=request.getContextPath() %>/views/footer.html")});

$(function () {
	vidx = <%= vo.getTm_b_no() %>;
	//replyListServer(vidx);
	
	
	
	//글 삭제
	$('#userq_delete').on('click', function () {
		replyDeleteAll(vidx);
		
		$.ajax({
			url		: '/Travel_Maker/qboard/delete.do',
			type	: 'post',
			data	: {"tm_b_no" : vidx},
			success	: function (res) {
					alert(res.sw)
					location.href='/Travel_Maker/qboard/list.do';
			},
			error	: function (xhr) {
				console.log('글삭제 상태 : ' + xhr.status);
			},
			dataType: 'json'
		})
	})
	
	//------------------------------------------------------------
	
	//글 수정 폼 출력
	$('#userq_update').on('click', function () {
		$('#myModal').modal('show');
	})
	
	//글 수정 내용 추출 및 전송
	$('#userq_send').on('click', function () {
		
		moditiltle = $('#retitle').val();
		modicont = $('#recontent').val();
		
		$.ajax({
			
			url		: '/Travel_Maker/qboard/update.do',
			type	: 'post',
			data	: {"tm_b_no" : vidx, "tm_b_content" : modicont, "tm_b_title" : moditiltle },
			success	: function (res) {
				
					  alert(res.sw)
					  $('#myModal').modal('hide');
					  location.reload()
			},
			error	: function (xhr) {
				console.log("상태 : " + xhr.status);
			},
			dataType: 'json'
		})
	})
	
	//---------------------------------------------------------
	
	//댓글 작성 폼 출력
	$('#replywrite').on('click', function () {
		$('#reModal').modal('show');
	})
	
	//댓글 내용 추출 및 전송
	$('#userq_reSend').on('click', function(){
	vidx = <%= vo.getTm_b_no() %>;
	replycont = $('#replycontent').val().replace(/<br>/g, "\n");
	
	$.ajax({
		
		url : '/Travel_Maker/qboard/reinsert.do',
		type : 'post',
		data : {"tm_b_no" : vidx, "tm_bc_content" : replycont},
		success : function(res){
			res.sw
			alert(res.sw);
			$('#reModal').modal('hide');
			location.reload();
		},
		error : function(xhr){
			console.log("상태 : " + xhr.status);
		},
		dataType : 'json'
		
	})		
})
//-----------------------------------------------------------------

//댓글 수정 버튼
$('.replymodify').on('click',function(){
	
	rebcNo = $(this).attr('idx');
	$('#reUpdateModal').modal('show');
})

// 댓글 수정 등록 버튼
$('#reModisend').on('click',function(){
	
	rebNo = $(this).attr('idx');
	modiReCont = $(this).parents('.modal-content').find('#reModiCont').val();
	
	replyModiServer();
	
	$('#reUpdateModal').modal('hide');
})


// 댓글 삭제 버튼
$('.replydelete').on('click',function(){
	
	delReNo = $(this).attr('idx');
	
	replyDelete();
})

	

	
})

</script>


<title>유저끼리 물어봐 글 조회</title>
</head>
<body>

	<div id="header"></div>
	<div class="container">
		<div class="row">
			<h1>유저끼리 물어봐 게시판</h1>

		</div>
		<!-- end row -->

		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<div class="table-responsive project-list">
							<table class="table project-table table-centered table-nowrap">
								<thead>
									<tr>
										<th scope="col"></th>
										<th scope="col"></th>


									</tr>
								</thead>

								<tbody id="box">
									<br>
									<br>
									<br>
									        <div class="write">
                            <input  type="button" value="신고하기" id="hate" class="myButtonhate" >
                            </div>
		                            </div>
									<tr>
										<td class="name">글번호</td>
										<td class="data"><%= vo.getTm_b_no() %></td>
									</tr>
									<tr>
										<td class="name">작성일</td>
										<td class="data"><%= vo.getTm_b_date() %></td>
									</tr>
									<tr>
										<td class="name">조회수</td>
										<td class="data"><%= vo.getTm_b_hit()%></td>
									</tr>
									<tr>
										<td class="name">제목</td>
										<td class="data"><%= vo.getTm_b_title() %></td>
									</tr>
									<tr>
										<td class="name">작성자</td>
										<td class="data"><%= vo.getTm_id() %></td>
									</tr>
									<tr>
										<td class="name">내용</td>
										<td class="data"><%= vo.getTm_b_content().replaceAll("\r","").replaceAll("\n", "<br>") %></td>
									</tr>


								</tbody>


							</table>
							<div id="optionbox">
								<%
						     if(memvo!=null && tm_author==0 || loginId.equals(vo.getTm_id())){
						     %>
						     	<div class="write">
									<input type="button" value="수정" id="userq_update" class="btn btn-primary" >&nbsp; 
									<input type="button" value="삭제" class="btn btn-primary"  id="userq_delete">&nbsp;
								<%
						     }
	                    	%>
									<input type="button" value="목록"  class="btn btn-primary" 
										onclick="location.href='/Travel_Maker/qboard/list.do'">&nbsp;
								</div>
						</div>
						</div>
<br><br><br><br>
						<tbody id="reply">댓글
							<table class="table project-table table-centered table-nowrap">
							<div class="table-responsive project-list">
								<thead>
									<tr>
										<th class="re" scope="col">댓글번호</th>
										<th class="re" scope="col">작성자</th>
										<th id="recont" scope="col">댓글내용</th>
										<th class="rebutton" scope="col"></th>
									</tr>
								</thead>


								<tr class="replylist">
								<tbody id="replylist">
								</tbody>

								<%
	                            		for(int i =0; i<reList.size(); i++){
	                            	%>
								<tr>
									<th scope="row"><%=reList.get(i).getTm_bc_no()%></th>
									<td><%=reList.get(i).getTm_id() %></td>
									<td><%=reList.get(i).getTm_bc_content().replaceAll("\r","").replaceAll("\n", "<br>") %></td>

									<%
         								if(loginId.equals(reList.get(i).getTm_id()) || tm_author==0){
         								%>
									<td><input idx="<%= reList.get(i).getTm_bc_no() %>"
										type="button" value="수정" class="replymodify"> <input
										idx="<%= reList.get(i).getTm_bc_no() %>" type="button"
										value="삭제" class="replydelete"></td>

									<%	
         								}else{
         								%>
									<td></td>
									<%	
         								}
                         			}
                          			 %>
								</tr>
								</tr>
							</table>

							<%
						 if(memvo!=null && tm_author==1 || memvo!=null && tm_author==0){
					     %>
							<div id="optionbox">
								<input type="button" value="댓글쓰기" class="btn btn-primary" id="replywrite">
							</div>
							<%
					     }
                    	%>

						</tbody>
						<!-- end project-list -->

						<div class="pt-3">
							<div id="pageList"></div>
						</div>
					</div>
				
			</div>
		</div>

		<!-- Modal 게시판 글 수정 부분-->
		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">글 수정</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">

						<form>


							<label>제목</label> <br> <input id="retitle" class="input"
								type="text" name="subject" value="<%= vo.getTm_b_title() %>">
							 <label>내용</label> <br>
							<textarea id="recontent" class="input" rows="15" cols="45"
								name="content"><%= vo.getTm_b_content() %></textarea>

							<p>
								<input type="button" value="등록" id="userq_send"><br>
								<input type="button" value="취소"
									onclick="location.href='/Travel_Maker/views/boardUserQ.html'">
							</p>
						</form>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>



		<!-- Modal 댓글 작성 부분-->
		<div id="reModal" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">댓글 작성란</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">

						<form>

							<label>내용</label> <br>
							<textarea id="replycontent" class="input" rows="15" cols="45"
								name="tm_bc_content"></textarea>

							<p>
								<input type="button" value="등록" id="userq_reSend"><br>
							</p>
						</form>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>

		<!-- Modal 답변 수정 -->
		<div id="reUpdateModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-lg">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">답변 수정</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<form id="remodi">
							<label>내용</label><br>
							<textarea id="reModiCont" class="text" rows="15" cols="45"
								name="tm_bc_content"></textarea>
						</form>
					</div>
					<div class="modal-footer">
						<input type="button" idx="<%= vo.getTm_b_no() %>"
							class="btn btn-primary" value="등록" id="reModisend">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!-- end row -->
	</div>

	<div id="footers"></div>
</body>
</html>