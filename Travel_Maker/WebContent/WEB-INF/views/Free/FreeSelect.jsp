<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="tm.board.service.FreeBoardService"%>
<%@page import="tm.board.service.UserQnaServiceImple"%>
<%@page import="tm.board.service.IFreeBoardService"%>
<%@page import="tm.comm.vo.ReplyVO"%>
<%@page import="java.util.List"%>
<%@page import="tm.member.vo.MemberVO"%>
<%@page import="tm.comm.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
     BoardVO vo = (BoardVO)request.getAttribute("boardVO");
    
    MemberVO memvo = (MemberVO)session.getAttribute("memVO");
	
	List<ReplyVO> reList = (List<ReplyVO>)request.getAttribute("reList");
	
	System.out.println("vo : " +vo);
	System.out.println("memvo : " +memvo);
	System.out.println("reList : " +reList);
	
	
	
	
	int tm_author = -1;
	System.out.println("tm_author" + tm_author);
	
	IFreeBoardService service = FreeBoardService.getService();
	

	
	String loginId = "nonUser";		
	if(memvo == null){
		tm_author = -1;
		loginId = "nonUser";		
	}
	else{
		

	if(memvo!=null){
		tm_author = service.checkNotUser(memvo.getTm_id());
		System.out.println("tm_author" + tm_author);
	}
	//------------------------------------------------------------------
	loginId = "nonUser";
	
	if(memvo!=null){
		loginId = ((MemberVO)session.getAttribute("memVO")).getTm_id();
		System.out.print("아이디1 :" + loginId );
		
		System.out.println("loginId" + loginId);
	}
	System.out.print("아이디2 :" + loginId );
	}
	//-------------------------------------------------------------------
	System.out.print("아이디3 :" + loginId );
    
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../page/css/default.css">
<link rel="stylesheet" type="text/css" href="../page/css/style2.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script src="<%=request.getContextPath() %>/js/freeboard.js"></script>
<script src="<%=request.getContextPath() %>/js/LikeHate.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/style2.css">

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
.page-link{
	width : 50px;
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

#modiform{
	width : 50px;
}
#btnok, #btnreset{
	margin-top : -72%
}
.table-responsive{
	background-color: aliceblue;
}

.table {
    background-color: aliceblue;
}
</style>




<script type="text/javascript">

 $(document).ready(function() {
	$("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp") 
 	$("#footers").load("<%=request.getContextPath() %>/views/footer.html") 
 });
 
$(function(){
	<%System.out.print("아이디4 :" + loginId );%>
		vidx = <%= vo.getTm_b_no() %>;
		hitUpdate();
		//replyListServer(vidx);
		
		
	//글 수정 화면 출력 부분
$('#write').on('click', function(){
	$('#myModal').modal('show');
})

	//글 수정 완료(전송) 부분
$('#send').on('click', function(){
	vidx = <%= vo.getTm_b_no() %>;
	modititle = $('#retitle').val();
	modicont = $('#recontent').val();

	$.ajax({
			
			url : '/Travel_Maker/FreeBoardModify.do',
			type : 'post',
			data : {"TM_B_NO" : vidx, "TM_B_CONTENT" : modicont, "TM_B_TITLE" : modititle},
			success : function(res){
				res.sw
				alert(res.sw);
				$('#myModal').modal('hide');
				location.reload();
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
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
		
		url : '/Travel_Maker/FreeReplySave.do',
		type : 'post',
		data : {"tm_b_no" : vidx, "tm_bc_content" : replycont},
		success : function(res){
			res.sw
			alert(res.sw);
			$('#reModal').modal('hide');
			location.reload();
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
		},
		dataType : 'json'
		
	})
		
		
	})
	
	//댓글 수정 버튼
	$('.replymodify').on('click',function(){
		$(this).hide();
		// 수정공간이 이미 열려있는 경우
 		if($('#modifyForm').css('display') != 'none'){
 			replyReset();
 		}
 		
 		modicont = $(this).parents('tr').find('.reContent').html();
 		modicont = modicont.replace(/<br>/g, "\n");	
 		console.log(modicont);
 		
 		$('#modifyForm #rearea').val(modicont);
		$(this).parents('tr').find('.reContent').empty().append($('#modifyForm'));
		$('#modifyForm').show();
 			

	})
/* 
	// 댓글 수정 등록 버튼
	$('#reModisend').on('click',function(){
		
		rebNo = $(this).attr('idx');
		modiReCont = $(this).parents('.modal-content').val();
		
		replyModiServer();
		
		$('#reUpdateModal').modal('hide');
	})
 */

	// 댓글 삭제  버튼
	$('.replydelete').on('click',function(){
		
		delReNo = $(this).attr('idx');
	
		replyDelete();
	})
	
	
	//글 삭제
	$('#free_delete').on('click', function () {
		
		replyDeleteAll(vidx);
		
		$.ajax({
			url		: '/Travel_Maker/FreeBoardDelete.do',
			type	: 'get',
			data	: {"tm_b_no" : vidx},
			success	: function (res) {
					alert(res.sw)
					location.href='/Travel_Maker/views/FreeBoard.jsp';
			},
			error	: function (xhr) {
				 	alert('글삭제 상태 : ' + xhr.status);
			},
			dataType: 'json'
		})
	})
	
	
	//좋아요 버튼
	$('#like').on('click', function(){
		console.log("좋아요 눌림");
		boardNo = <%= vo.getTm_b_no() %>;
		category = <%= vo.getTm_category_id() %>;
		<%-- logid = '<%= memvo.getTm_id() %>'; --%>
		
		cnt = likecheck();
		if(cnt == 0){
			cancelHate();
			addlike();
		}
		else{
			cancelLike();
		}
	
	})
	
	
	
	//싫어요 버튼
	$('#hate').on('click', function(){
		console.log("싫어요 눌림");
		boardNo = <%= vo.getTm_b_no() %>;
		category = <%= vo.getTm_category_id() %>;
		logid = '<%= memvo.getTm_id() %>';
		if(confirm("정말 신고하시겠습니까?") == true){
			prompt('신고사유를 입력하세요', '');
			alert('신고되었습니다.');
			location.reload();
		}else{
			return;
		}
		cnt = hatecheck();
		if(cnt == 0){
			cancelLike();
			addhate();
		}
		else{
			cancelHate();
		}
	
	})

	
	
	
	
	
	// 댓글 수정관련 옵션
	
	// 리셋
	replyReset = function(){
	$(document).find('.replymodify').show();
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

	
	// 댓글 수정중에 확인(수정완료 적용하기)
	$(document).on('click','#btnok', function(){
	modiReCont = $(document).find('#modifyForm #rearea').val();
	console.log(modiReCont)
	
	rebcNo = $(this).parents('tr').find('#bno').html();
	console.log(rebcNo)
	// 수정한 내용을 DB에 적용하기 (cont = modicont, renum = vidx)
	replyModiServer();
	
	
	
	modiReCont = modiReCont.replace(/\r/g, "").replace(/\n/g, "<br>");
	
	spantag = $(this).parents('.replylist').find('#replycont');
	
	$('body').append($('#modifyForm'));
	$('#modifyForm').hide();
	
	spantag.html(modicont);

})
	
	
	
<%System.out.print("아이디5 :" + loginId );%>
	})




</script>
</head> 




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
                                    <th scope="col"></th>
                                    <th scope="col"></th>
<%System.out.print("아이디 :" + loginId ); %>

                                </tr>
                            </thead>
                    	
                            <tbody id="box">
                            <div id="likebox">
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
	                        <%
						     if(memvo!=null && tm_author==1 || memvo!=null && tm_author==0){
						     %>
						     	<div class="write">
								<input  type="button" value="수정하기" id="write" class="btn btn-primary" >&nbsp;
                    			<input  type="button" value="삭제하기" id="free_delete" class="btn btn-primary">&nbsp;
	                    	<%
						     }
	                    	%>
	                    		<input  type="button" value="목록" id="back" onclick="location.href='/Travel_Maker/views/FreeBoard.jsp'" class="btn btn-primary">&nbsp;
                    			</div>
                    	</div>
                    </div>
                    <br>
                    
                            <tbody id="reply">
                            	<table class="table project-table table-centered table-nowrap">
                            <thead>
                            	<tr>댓글</tr>
                                <tr>
                                    <th class="re" scope="col">등록번호</th>
                                    <th class="re" scope="col">id</th>
                                    <th id="recont" scope="col">내용</th>
                                    <th class="rebutton" scope="col"></th>
                                </tr>
                            </thead>

                            <tbody id="replylist">
		                           
	                            	<%
	                            		for(int i =0; i<reList.size(); i++){
	                            	%>
	                            		<tr>
		               				  		<th scope="row" id="bno"><%=reList.get(i).getTm_bc_no()%></th>
		               						<td><%=reList.get(i).getTm_id() %></td>
		                            		<td class="reContent"><%=reList.get(i).getTm_bc_content().replaceAll("\r","").replaceAll("\n", "<br>") %></td>
	                            		
	                            		<%
	                            		System.out.print("아이디 :" + loginId );
         								if(loginId.equals(reList.get(i).getTm_id()) || tm_author == 0){
         								%>
         									<td>
	        								   <input idx="<%= reList.get(i).getTm_bc_no() %>" type="button" value="댓글수정"  class="replymodify" >
	        								   <input idx="<%= reList.get(i).getTm_bc_no() %>" type="button" value="댓글삭제"  class="replydelete" >
	       								    </td>
       								
       								   <%	
         								}else{
         								%>
         									<td>
         									</td>
         							   <%	
         								}
                         			}
                          			 %>
                          			 	</tr>

                            </tbody>
    
                        </table>
                        <%
						 if(memvo!=null && tm_author==1 ){
					     %>
						<div id = "optionbox">
                        <input  type="button" value="댓글달기"  id="replywrite" >
                    	</div>
                    	<%
					     }
                    	%>
                            
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
					<h4 class="modal-title">자유게시판 글 수정 두둥!</h4>
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
							<textarea id="recontent" class="input" rows="15" cols="45" name="content" ><%= vo.getTm_b_content() %></textarea>
							
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
							<textarea id="replycontent" class="input" rows="15" cols="45" name="tm_bc_content" ></textarea>
							
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
		
		
		<!-- Modal 답변 수정 -->
		<!-- 
		<div id="reUpdateModal" class="modal fade" role="dialog">
		  <div class="modal-dialog modal-lg">
		 --> 
		    <!-- Modal content-->
		    <!--
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title">답변 수정</h4>
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		      </div>
		      <div class="modal-body">
		        <form id="remodi">
			        <label>내용</label><br>
			        <textarea id="reModiCont" class="text" rows="30" cols="70" name="tm_bc_content"></textarea>
		        </form>
		      </div>
		      <div class="modal-footer">
		        <input type="button" idx="<%= vo.getTm_b_no() %>" class="btn btn-primary" value="등록" id="reModisend">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		 --> 
		
		

		
		<!-- 댓글 수정 부분 --> 
 		<div id="modifyForm" style="display:none">
		<textarea id="rearea" rows="15" cols="45" style="width: 110px;"></textarea>
		<input type="button" value="확인" class="btn btn-primary" id="btnok" >
		<input type="button" value="취소" class="btn btn-primary" id="btnreset">
		</div> 

		<!-- end row -->
</div>    			
    			
    			
    			
    			
   	<div id="footers"></div> 			

</body>
</html>	




















