<%@page import="tm.member.vo.MemberVO"%>
<%@page import="tm.comm.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
     BoardVO vo = (BoardVO)request.getAttribute("board");
	 MemberVO memVO = (MemberVO)session.getAttribute("memVO");
	 
	 System.out.print("jsp 에서 vo : " + vo.getTm_b_no());

    %>
    
    
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">


<script src="<%=request.getContextPath() %>/js/privQnAboard.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/style2.css">

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

.card:hover{
	box-shadow: 1px 1px 20px #ddd;
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
.table-responsive{
	background-color: aliceblue;
}

.table {
    background-color: aliceblue;
}
</style>




<script >


$(function(){
	$("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp")
	$("#footers").load("<%=request.getContextPath() %>/views/footer.html")
	vidx =  '<%=vo.getTm_b_no()%>';
		hitUpdate();
		replyListServer(vidx);
		
		
	//글 수정 화면 출력 부분
$('#write').on('click', function(){
	$('#myModal').modal('show');
})

	//글 수정 완료(전송) 부분
$('#send').on('click', function(){
	boardno = <%= vo.getTm_b_no() %>;
	modititle = $('#retitle').val();
	modicont = $('#recontent').val();

	$.ajax({
			url : '/Travel_Maker/privQnABoardModify.do',
			type : 'post',
			data : {"TM_B_NO" : boardno, "TM_B_CONTENT" : modicont, "TM_B_TITLE" : modititle},
			success : function(res){
				res.sw;
				alert('수정되었습니다.');
				$('#myModal').modal('hide');
				location.reload();
			},
			error : function(xhr){
				console.log("수정 완료상태 : " + xhr.status);
			},
			dataType : 'json'
		})
})	
	//댓글 등록 창 팝업
$('#replywrite').on('click', function(){
	$('#reModal').modal('show');
})

	//댓글 작성 후 전송 버튼 클릭
$('#replysend').on('click', function(){
	vidx = <%= vo.getTm_b_no() %>;
	replycont = $('#replycontent').val().replace(/<br>/g, "\n");
	$.ajax({
		url : '/Travel_Maker/privQnAReplySave.do',
		type : 'post',
		data : {"tm_b_no" : vidx, "tm_bc_content" : replycont},
		success : function(res){
			res.sw;
			alert('등록되었습니다.');
			$('#reModal').modal('hide');
			location.reload();
		},
		error : function(xhr){
			console.log("댓글 작성 전송상태 : " + xhr.status);
		},
		dataType : 'json'
		
	})
		
		
	})

	//댓글 수정
$(document).on('click','#replymodify', function(){
	if($('#modifyForm').css('display') != 'none'){
		replyReset(); // 이미 어딘가에 열려있으면 --> 원래 내용과 모양이 같게 유지 --> Form은 다시 body로 append
		
	}
	modicont = $(this).parents('.replylist').find('#replycont').html();
	
	modicont = modicont.replace(/<br>/g, "\n");
	
	$('#modifyForm #rearea').val(modicont);
	$(this).parents('.replylist').find('#replycont').empty().append($('#modifyForm'));
	$('#modifyForm').show();
	
	
})


	//댓글 수정 변수 (이미 다른 댓글을 수정하고 있는 경우)
	replyReset = function(){
	spantag = $('#modifyForm').parent();
	
	$('body').append($('#modifyForm'));
	$('#modifyForm').hide();
	//spantag에 원래 내용을 넣어
	modicont = modicont.replace(/\n/g, "<br>");
	spantag.html(modicont);
}
	
	//댓글 수정 변수 (수정중 취소버튼을 누르는 경우)
	$(document).on('click','#btnreset', function(){
	replyReset();
})


	//댓글 수정 완료 (수정 완료 버튼을 누르는 경우)
	$(document).on('click','#btnok', function(){
	modicont = $('#modifyForm #rearea').val();
	
	vidx = $(this).parents('.replylist').find('#replyno').html();
	
	replyModiServer();// (cont = modicont, renum = vidx)
	
	modicont = modicont.replace(/\r/g, "").replace(/\n/g, "<br>");
	
	spantag = $(this).parents('.replylist').find('#replycont');
	
	$('body').append($('#modifyForm'));
	$('#modifyForm').hide();
	
	spantag.html(modicont);

})
	
	
	//댓글 삭제
$(document).on('click','#replydelete', function(){
	console.log('댓글삭제');
	vidx = $(this).parents('.replylist').find('#replyno').html();
	replyDelete(vidx);
	
})	


	//글 삭제 부분
$('#delete').on('click', function(){
	vidx = <%= vo.getTm_b_no() %>;
	replyDeleteAll(vidx);

	$.ajax({
		
		url : '/Travel_Maker/privQnABoardDelete.do',
		type : 'get',
		data : {"TM_B_NO" : vidx},
		success : function(res){
			alert("게시글이 삭제되었습니다.");
			location.href='/views/privQnA/privQnABoard.html';
		},
		error : function(xhr){
			console.log("글 삭제 상태 : " + xhr.status);
		},
		dataType : 'json'
		
	})


})
})	


</script>
</head> 


<body>
<div id="header">
<div class="container">
    <div class="row">
        <h1>1:1문의게시판</h1>

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
                            <br><br><br>
                            <tr>내용</tr>
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
                            		<td class="data"><%= vo.getTm_b_hit()+1 %></td>
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
                        <div id = "optionbox">
                        <div class="write">
                        <input  type="button" value="수정하기"  id="write" class="btn btn-primary">&nbsp;
                    	<input  type="button" value="삭제하기"  id="delete" class="btn btn-primary">&nbsp;
                    	<input  type="button" value="돌아가기"  id="back" class="btn btn-primary" onclick="location.href='/Travel_Maker/views/privQnA/privQnABoard.html'">
                    	</div><br><br>
                    </div>
<<<<<<< .mine
||||||| .r185536
                    
=======
                    </div>
                    
>>>>>>> .r185737
                            <tbody id="reply">
                            	<table class="table project-table table-centered table-nowrap">
                            <thead>
                            <br><br>
                            	<tr>관리자의 답변</tr>
                                <tr>
                                    <th class="re" scope="col">등록번호</th>
                                    <th class="re" scope="col">id</th>
                                    <th id="recont" scope="col">내용</th>
                                    <th class="rebutton" scope="col"></th>
                                </tr>
                            </thead>
                            <tbody id="replylist">
                            </tbody>
                        </table>
						<div id = "optionbox">
						<div class="write">
                        <input  type="button" value="댓글달기"  id="replywrite" >
                    	</div>
<<<<<<< .mine
||||||| .r185536
                            
=======
                            </div>
>>>>>>> .r185737
                            </tbody>
                    <!-- end project-list -->
                    <div class="pt-3">
                    	<div id = "pageList">
                    	</div>
                    </div>
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
					<h4 class="modal-title">1:1 문의 게시글 수정 두둥!</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">

						<form>


							<label>제목</label>
							<br>  
							<input id="retitle" class="input" type="text" name="subject" value="<%= vo.getTm_b_title() %>"><br>
							<br> 
							<label>내용</label>
							<br> 
							<textarea id="recontent" class="input" rows="15" cols="50" name="content" ><%= vo.getTm_b_content() %></textarea>
							
							<p>
								<input type="button" value="전송!" id="send" class="btn btn-primary">
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
					<h4 class="modal-title">댓글 작성 두둥탁!</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">

						<form>

							<label>내용</label>
							<br> 
							<textarea id="replycontent" class="input" rows="15" cols="50" name="tm_bc_content" ></textarea>
							
							<p>
								<input type="button" value="전송!" id="replysend">
							</p>
						</form>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>

		
		<!-- 댓글 수정 부분 -->
		<div id="modifyForm" style="display:none">
		<textarea id="rearea" rows="1" cols="30"></textarea>
		<input type="button" value="확인" class="rebtn" id="btnok">
		<input type="button" value="취소" class="rebtn" id="btnreset">
		</div>

		<!-- end row -->
</div>    			
<div id="footer"></div>
</div>
</body>
</html>	



























