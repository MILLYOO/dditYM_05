<%@page import="tm.comm.vo.ReplyVO"%>
<%@page import="java.util.List"%>
<%@page import="tm.comm.vo.BoardVO"%>
<%@page import="tm.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	 BoardVO vo = (BoardVO) request.getAttribute("vo");

	 List<ReplyVO> relist = (List<ReplyVO>)request.getAttribute("relist");
%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1:1 문의 게시글 조회</title>

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

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script src="<%=request.getContextPath() %>/js/admin.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.serializejson.min.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/style2.css">

<style>
h1{
    margin: 20px 20px 20px 20px;
    text-align: center;
}
label{
	display : inline-block;
	width : 100px;
	margin : 10px;
}
#repTable{
	font-family: 'Do Hyeon', sans-serif;
	font-size : 1.3em;
	height :700px;
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
.card-body {
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    min-height: 1px;
    padding: 1.25rem;
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
#btnWrite{ 
 	float : right;
 	vertical-align : bottom;
}
</style>
</style>

<script>

$(document).ready(function() {
	$("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp")
	$("#footers").load("<%=request.getContextPath() %>/views/footer.html")
});


$(function(){
	
	// 수정하기 눌렀을 때
	$('#btnUpdate').on('click', function(){
		$('#myModal').modal('show');
	})
	
	// 글 수정 등록 버튼
	$('#send').on('click',function(){
		modiTitle = $(this).parents('#qnaUpdatediv').find('#modiTitle').val();
		modiCont = $(this).parents('#qnaUpdatediv').find('#modiCont').val();
		modiNo = $(this).attr('idx');
		
		qnaUpdateServer();
		
		$('#myModal').modal('hide');
		$('.text').val("");
	})
	
	// 글 삭제 버튼
	$('#btnDelete').on('click',function(){
		
		if(confirm("정말 삭제하시겠습니까?") == true){
			modiNo = $(this).attr('idx');
			qnaDeleteServer();			
		}else{
			return;
		}
		
	})
	
	// 답변 등록 버튼
	$('#btnReInsert').on('click',function(){
		$('#reModal').modal('show');
	})
	
	// 답변 작성 후 등록 버튼
	$('#reSend').on('click',function(){
		memId = "<%= vo.getTm_id()%>";
		num = <%= vo.getTm_b_no() %>;
		reWriter = "Travel_Maker STAFF";
		replycont = $('#replycontent').val();
		
		qnaReInsertServer();
	})
	
	// 답변 수정 버튼
	$('.btnReUpdate').on('click',function(){
		rebcNo = $(this).attr('idx');
		$('#reUpdateModal').modal('show');
	})
	
	// 답변 수정 등록 버튼
	$('#reModisend').on('click',function(){
		
		rebNo = $(this).attr('idx');
		modiReCont = $(this).parents('.modal-content').find('#reModiCont').val();
		
		qnaReUpdateServer();
		
		$('#reUpdateModal').modal('hide');
	})
	
	
	// 답변 삭제 버튼
	$('.btnReDelete').on('click',function(){
		delReNo = $(this).attr('idx');
		delBno = <%= vo.getTm_b_no() %>;
		
		qnaReDeleteServer();
	})
})

</script>


</head>
<body>
	<div id="header"></div>
<div class="container">
    <div class="row">
        <h1>1:1 문의 게시글 조회</h1>
    </div>
<div id="repTable">
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive project-list">
<div class="table-responsive project-list">
  <h4><%=vo.getTm_id() %> 님 문의 게시글</h4>
   <table class="table project-table table-centered table-nowrap">
       
       <tbody id="tbody">
       	<tr>
       		<td class="name">답변여부</td>
       		<td class="data">
       		<% 
           	   String tmp = "";
           	   int ox = vo.getTm_b_ox();
           	   if(ox == 0){
           		   tmp = "답변대기"; 
           	%>  
           			<%= tmp %>
           	
           	<%}else{
           			tmp = "답변완료";	%>
           			<%= tmp %>
           	<%} %>
       		</td>
       	</tr>
       	<tr>
       		<td class="name">글번호</td>
       		<td class="data"><%= vo.getTm_b_no() %></td>
       	</tr>
       	<tr>
       		<td class="name">작성일</td>
       		<td class="data"><%= vo.getTm_b_date() %></td>
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
  
                     </div>
                </div>
            </div>
        </div>
    </div>  
    	<div class="pt-3">
	      <button type="button" class="btn btn-info" id="btnUpdate">수정하기</button>
	      <button type="button" idx="<%= vo.getTm_b_no() %>" class="btn btn-warning" id="btnDelete">삭제하기</button>
	      <input type="button" class="btn btn-outline-dark" value="돌아가기"  id="back" onclick="location.href='/Travel_Maker/AdminQnaList.do'">
   	    </div>
</div>

<hr><br><br>

		<table class="table project-table table-centered table-nowrap">
           <thead>
               
           </thead>
           
           <tbody id="rebody">
           <%
           	if(relist.size() <= 0){
       	   %>
       	     <tr>답변 대기 중 입니다.</tr>
       	   <%
           	}else{
           		for(int i = 0; i <relist.size(); i++){
               %>
               <tr>
               	<th scope="row"><%=i + 1 %></th>
               	<td><%=relist.get(i).getTm_bc_writer() %></td>
               	<td><%=relist.get(i).getTm_bc_content().replaceAll("\r","").replaceAll("\n", "<br>") %></td>
               	<td>
               	<%
               		if(relist.get(i).getTm_bc_modate() != null){
               	%>
               		<%=relist.get(i).getTm_bc_modate() %>
               	<%
               		}else{
                %>
                	<%=relist.get(i).getTm_bc_date() %>
                <%
               		}
               	%>
               	</td>
               	<td id="reButton">
               		<div class="pt-3">
				      <button type="button" idx="<%= relist.get(i).getTm_bc_no() %>" class="btn btn-info btnReUpdate">수정하기</button>
				      <button type="button" idx="<%= relist.get(i).getTm_bc_no() %>" class="btn btn-warning btnReDelete">삭제하기</button>
			   	    </div>
               	</td>
               </tr>
               <%
               	}
           	}
            %>
           </tbody>
        </table>
       <div class="pt-3">
	      <button type="button" class="btn btn-dark" id="btnReInsert">답변등록</button>
   	    </div> 
        
        



 		<!-- Modal 게시글 수정-->
		<div id="myModal" class="modal fade" role="dialog">
		  <div class="modal-dialog modal-lg">
		
		    <!-- Modal content-->
		    <div class="modal-content" id="qnaUpdatediv">
		      <div class="modal-header">
		        <h4 class="modal-title">1:1 문의 게시글 수정</h4>
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		      </div>
		      <div class="modal-body">
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
		
		
		
		<!-- Modal 답변 작성 부분-->
		<div id="reModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-lg">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
					<h4 class="modal-title">1:1 문의 답변 작성</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">

						<form>
							<label>내용</label>
							<br> 
							<textarea id="replycontent" class="input" rows="30" cols="75" name="tm_bc_content" ></textarea>
						</form>

					</div>
					<div class="modal-footer">
						 <input type="button" idx="<%= vo.getTm_b_no() %>" class="btn btn-primary" value="등록" id="reSend">
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
			        <textarea id="reModiCont" class="text" rows="30" cols="100" name="tm_bc_content"></textarea>
		        </form>
		      </div>
		      <div class="modal-footer">
		        <input type="button" idx="<%= vo.getTm_b_no() %>" class="btn btn-primary" value="등록" id="reModisend">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
</div>

       	<div id="footers"></div>
</body>
</html>