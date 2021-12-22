<%@page import="tm.board.service.FreeBoardService"%>
<%@page import="tm.board.service.IFreeBoardService"%>
<%@page import="tm.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/freeboard.js"></script>
<link rel="stylesheet" type="text/css" href="../page/css/default.css">
<link rel="stylesheet" type="text/css" href="../page/css/style2.css">
<script type="text/javascript">
	$(document).ready(function() {
		$("#header").load("MainHeader.jsp")
		$("#footers").load("footer.html")
	});
</script>
<style type="text/css">
h1{
    margin: 20px 20px 20px 20px;
    text-align: center;
}
body{
	font-family: 'Do Hyeon', sans-serif;
}
.modal-title{
	text-align: center;
}
#pagelist{
	width : 200px;
}
.pager, .pagination{
		width : 100px;
		float : left;
	}
.card:hover{
	box-shadow: 1px 1px 20px #ddd;
}
/* .card-body {
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    min-height: 1px;
    padding: 1.25rem;
} */
.card-body{
    flex: 1 1 auto;
    min-height: 1px;
    padding: 1.25rem;
      -webkit-box-shadow: 0px 10px 20px -12px rgba(0, 0, 0, 0.18);
  -moz-box-shadow: 0px 10px 20px -12px rgba(0, 0, 0, 0.18);
  box-shadow: 0px 10px 20px -12px rgba(0, 0, 0, 0.18);
}
.card {
    border: none;
    margin-bottom: 24px;
    -webkit-box-shadow: 0 0 13px 0 rgba(236,236,241,.44);
    box-shadow: 0 0 13px 0 rgba(236,236,241,.44);
}

.avatar-xs {
    height: 2.3rem;
    width: 2.3rem;
}

</style>
</head>

<script type="text/javascript">

$(function(){
	listPageServer(1);
	
	
	// 페이지 번호 클릭
	$('#pageList').on('click', '.paging', function(){
			currentPage = $(this).text().trim();
			listPageServer(currentPage);
			
		})
		
	// 다음페이지
	$('#pageList').on('click', '#next', function(){
			nextstr = $('.paging').last().text().trim();
			currentPage = parseInt(nextstr) +1;
			listPageServer(currentPage);
		})
		
	// 이전페이지
	$('#pageList').on('click', '#prev', function(){
			prevstr = $('.paging').first().text().trim();
			currentPage = parseInt(prevstr) -1;
			listPageServer(currentPage);
		})
		
	
	// 글쓰기 버튼 누르면
	$('#write').on('click', function(){
			$('#myModal').modal('show');
			
		})
		
		
	// 글쓰고 나서 전송 버튼 누르면
	$('#send').on('click', function(){
			sendServer();
			$('#myModal').modal('hide');
			$('.input').val("");
		})
	
		
		
})
	
	
</script>

<%
	MemberVO memvo = (MemberVO)session.getAttribute("memVO");
	int tm_author = -1; 
	IFreeBoardService service = FreeBoardService.getService();
	if(memvo!=null){
		tm_author = service.checkNotUser(memvo.getTm_id());
	}

%>


<body>

	<div id="header"></div>
	<div class="container">
		<div class="row">
			<h1>자유게시판</h1>
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
										<th scope="col">글번호</th>
										<th scope="col">작성자</th>
										<th scope="col">제목</th>
										<th scope="col">작성일</th>
										<th scope="col">조회수</th>
									</tr>
								</thead>

								<tbody id="box">

								</tbody>

							</table>
							<%
								if (memvo != null && tm_author == 1 || memvo != null && tm_author == 0) {
							%>
							<div class="write">
								<button type="button" class="btn btn-primary" id="write">글쓰기</button>
							</div>
							<%
								}
							%>

						</div>
						<!-- end project-list -->
						<div class="pt-3">
							<div id="pageList"></div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<%-- 
   				<%
			     if(memvo!=null && tm_author==1 || memvo!=null && tm_author==0){
			     %>
			     <!-- 글쓰기 -->
 		     	 <div class="pt-3" id="pageList"> 
 			     	<button type="button" class="btn btn-primary" id="write">글쓰기</button> 
			     </div>  
				<%
			     }
				%>
 --%>


		<!-- Modal -->
		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">자유게시판 글쓰기</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">

						<form>


							<label>제목</label> <br> <input class="input" type="text"
								name="tm_b_title"><br> <br> <label>내용</label>
							<br>
							<textarea class="input" rows="15" cols="45" name="tm_b_content"></textarea>

							<p>
								<input type="button" value="전송" id="send" class="btn btn-primary">
							</p>
						</form>

					</div>
					<div class="modal-footer">
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