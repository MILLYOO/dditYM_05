<%@page import="tm.board.service.NoticeBoardService"%>
<%@page import="tm.board.service.INoticeBoardService"%>
<%@page import="tm.member.vo.MemberVO"%>
<%@page import="tm.comm.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVO vo = (BoardVO)request.getAttribute("vo");

	String content = vo.getTm_b_content().replaceAll("\r","").replaceAll("\n", "<br>");
	
// 	String loginId = (String)request.getAttribute("loginId");

	String wdate = "";
	if(vo.getTm_b_modate() != null){
		wdate = vo.getTm_b_modate();
	}else{
		wdate = vo.getTm_b_date();
	}
	
	MemberVO memvo = (MemberVO)session.getAttribute("memVO");
	int tm_author = -1; 
	INoticeBoardService service = NoticeBoardService.getInstance();
	if(memvo!=null){
		tm_author = service.checkNotUser(memvo.getTm_id());
	}
	
%>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>공지사항 조회</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<script src="<%=request.getContextPath() %>/js/notice.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/page/css/default.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/page/css/style2.css">
<script>

$(document).ready(function() {
	$("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp")
	$("#footers").load("<%=request.getContextPath() %>/views/footer.html")
});

$(function(){
	modiNo = <%= vo.getTm_b_no() %>;
	hitUpdate();
	
	// 수정하기 눌렀을 때
	$('#btnUpdate').on('click', function(){
		$('#myModal').modal('show');
	})
	
	
	// 글 수정 등록 버튼
	$('#send').on('click',function(){
		modiTitle = $(this).parents('.modal-content').find('#modiTitle').val();
		modiCont = $(this).parents('.modal-content').find('#modiCont').val();
		modiNo = $(this).attr('idx');
		
		noticeUpdateServer();
		
		$('#myModal').modal('hide');
		$('.text').val("");
	})
	
	// 글 삭제 버튼
	$('#btnDelete').on('click',function(){
		
		if(confirm("정말 삭제하시겠습니까?") == true){
			modiNo = $(this).attr('idx');
			noticeDeleteServer();			
		}else{
			return;
		}
		
	})
	
})
</script>

<style type="text/css">
h1 {
	margin: 20px 20px 20px 20px;
	text-align: center;
	font-family: 'Do Hyeon', sans-serif;
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
.card:hover{
	box-shadow: 1px 1px 20px #ddd;
}
</style>

</head>

<body>
	<div id="header"></div>
	<div class="container">
		<div class="row">
	<h1>공지사항</h1>
		</div>
		
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
<div class="table-responsive project-list" id="bodyDiv">
   <table class="table project-table table-centered table-nowrap">
       
       <tbody id="tbody">
     	   <tr>내용</tr>
           <tr>
       		<td class="name">글번호</td>
       		<td class="data"><%= vo.getTm_b_no() %></td>
  		   </tr>
		  	<tr>
		  		<td class="name">작성일</td>
		  		<td class="data"><%= wdate %></td>
		  	</tr>
		  	<tr>
		  		<td class="name">조회수</td>
		  		<td class="data"><%= vo.getTm_b_hit() %></td>
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
		  		<td class="data"><%= content %></td>
		    </tr>
        </tbody>
    </table>
	<div class="pt-3">
	<%if(tm_author == 0){%>
      <button type="button" class="btn btn-info" id="btnUpdate">수정하기</button>
      <button type="button" idx="<%= vo.getTm_b_no() %>" class="btn btn-warning" id="btnDelete">삭제하기</button>
    <%} %>
      <input type="button" class="btn btn-outline-secondary" value="돌아가기"  id="back" onclick="location.href='/Travel_Maker/NoticeList.do'">
    </div>
</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

 <br><br>
 
        <!-- Modal -->
		<div id="myModal" class="modal fade" role="dialog">
		  <div class="modal-dialog modal-lg">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title">공지사항 글수정</h4>
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		      </div>
		      <div class="modal-body">
		        <label>카테고리</label>
		        <select id="category">
			      <option value="999" selected="selected">공지사항</option>
			      <option value="998">1:1문의게시판</option>
		   	      <option value="100">자유게시판</option>
			      <option value="110">질답게시판</option>
			      <option value="120">리뷰게시판</option>
			    </select><br>
		        <form id="modi">
			        <label>제목</label>
			        <input id="modiTitle" class="text" type="text" name="tm_b_title" value="<%=vo.getTm_b_title()%>"><br>
			        <label>내용</label><br>
			        <textarea id="modiCont" class="text" rows="20" cols="75" name="tm_b_content"><%=vo.getTm_b_content()%></textarea>
		        </form>
		      </div>
		      <div class="modal-footer">
		        <input type="button" idx="<%= vo.getTm_b_no() %>" class="btn btn-primary" value="등록" id="send">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
       	<div id="footers"></div>
</body>

</html>