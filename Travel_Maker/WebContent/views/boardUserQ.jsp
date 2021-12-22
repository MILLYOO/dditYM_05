
<%@page import="tm.comm.vo.PagingVO"%>
<%@page import="tm.comm.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="tm.board.service.UserQnaServiceImple"%>
<%@page import="tm.board.service.IUserQnaBoardService"%>
<%@page import="tm.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>질답게시판</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

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

<script type="text/javascript" src="../js/qboard.js"></script>
<link rel="stylesheet" type="text/css" href="../page/css/default.css">
<link rel="stylesheet" type="text/css" href="../page/css/style2.css">

</head>
<style type="text/css">
h1 {
	margin: 20px 20px 20px 20px;
	text-align: center;
	color: #727272;
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
	width: auto;
	padding: 5px 20px;
}

.pager, .pagination {
	width: auto;
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
.page-link {
    width: 81px;
    padding: 8px;
    border-radius: 5px;
    position: relative;
    display: block;
    padding: .5rem .75rem;
    margin-left: -1px;
    line-height: 1.6;
    color: #007bff;
    background-color: #fff;
    border: 1px solid #dee2e6;
}
.card:hover{
	box-shadow: 1px 1px 20px #ddd;
}
</style>

<script>

$(document).ready(function() {
	$("#header").load("<%=request.getContextPath()%>/views/MainHeader.jsp")
	$("#footers").load("<%=request.getContextPath()%>/views/footer.html")
});

/* 글 목록 조회 */
function fn_selectList(pageNo){
	const listForm = document.listForm;
	
	// 페이지 번호 설정
	pageNo ? listForm.pageNo.value =  pageNo : listForm.pageNo.value = 1;
	listForm.action = "/Travel_Maker/qboard/list.do";
	listForm.method = "post";
	listForm.submit();
}

	$(function() {

		//글쓰기 클릭
		$('#btnWrite').on('click', function() {
			$('#myModal').modal('show')
		})

		//글 다 쓰고 등록 클릭
		$('#send').on('click', function() {
			sendServer()
			$('#myModal').modal('hide')
			$('.text').val("")
		})

	})

	//-------------------------------------------------
</script>

<%
	List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");
	PagingVO pagingVO = (PagingVO) request.getAttribute("pagingVO");

//------------------------------------------------------------------------
	MemberVO memvo = (MemberVO) session.getAttribute("memVO");
	int tm_author = -1;
	IUserQnaBoardService boardService = UserQnaServiceImple.getInstance();
	if (memvo != null) {
		tm_author = boardService.checkNotUser(memvo.getTm_id());
	}
%>

<body>
	<link
		href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
		rel="stylesheet">

	<div id="header"></div>
	<div class="container">
		<div class="row">
			<h1>유저끼리 물어봐 게시판</h1>
		</div>

<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
					
						<!-- 글 목록 -->
						<div class="table-responsive project-list">
                     	 <table class="table project-table table-centered table-nowrap">
							<thead>
								<tr>
									<th scope="col">No</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Writer</th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Hit</th>
								</tr>
							</thead>
							
								<tbody id="othertbody">
									<%
                                	for(int i = 0; i <list.size(); i++){
                                	%>
                                <tr>
                                	<th scope="row"><%=list.get(i).getTm_b_no() %></th>
                                	<td><a class="clickTitle" href="/Travel_Maker/qboard/select.do?tm_b_no=<%=list.get(i).getTm_b_no()%>"><%=list.get(i).getTm_b_title() %></a></td>
                                	<td><%= list.get(i).getTm_id() %></td>
                                	<td><%= list.get(i).getTm_b_date() %></td>
                                	<td><%= list.get(i).getTm_b_hit() %></td>
                                </tr>
                                <%
                                	}
                                %>
								</tbody>
						</table>
						
						<!-- 페이징 -->
						<div class="pt-3">
                        <ul class="pagination justify-content-end mb-0">
						<%
						 if(pagingVO.getTotalPageCount() > 0){
						 %>
	 
	                        <% if(pagingVO.getFirstPageNo() > pagingVO.getPageSize()){ %>
	                            <li class="page-item">
	                                <a class="page-link" href="javascript:fn_selectList(<%=pagingVO.getFirstPageNo() - pagingVO.getPageSize()%>)">이전</a>
	                            </li>
	                        <%} %>    
	                            
	                        <% for(int pNo = pagingVO.getFirstPageNo(); pNo <= pagingVO.getLastPageNo(); pNo++){ %> 
		                            <li class="page-item <%if(pNo == pagingVO.getCurrentPageNo()){%>active<%} %>"><a class="page-count" href="javascript:fn_selectList(<%=pNo%>)"><%=pNo %></a></li>
	                        <%} %>    
	                            
	                        <% if(pagingVO.getLastPageNo() < pagingVO.getTotalPageCount()){ %>    
	                            <li class="page-item">
	                                <a class="page-link" href="javascript:fn_selectList(<%=pagingVO.getFirstPageNo() + pagingVO.getPageSize()%>)">다음</a>
	                            </li>
	                        <%} %>
	                        
						<% 
						 }
						%>          
                        </ul>
                    </div>
						
					<!-- 글쓰기 버튼 -->
					  <%if(memvo != null && tm_author == 1 || memvo != null && tm_author == 0){%>
		            	<div class="write">
								<button type="button" class="btn btn-primary" id="btnWrite">글쓰기</button>
							</div>
                    <%} %>

				</div>
			</div>
		</div>
	</div>
</div>

		
		<!-- Modal -->
		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-lg">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">유저끼리 물어봐 질문작성</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<form>
							<label>제목</label> <input class="text" type="text"
								name="tm_b_title"><br> <label>내용</label><br>
							<textarea class="text" rows="15" cols="45" name="tm_b_content"></textarea>
						</form>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-primary" value="등록" id="send">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>


		<div id="footers"></div>
</body>
</html>