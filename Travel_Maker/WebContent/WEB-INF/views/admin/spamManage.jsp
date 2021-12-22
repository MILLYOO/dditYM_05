<%@page import="tm.comm.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>불량게시글관리</title>
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

<script src="<%=request.getContextPath() %>/js/notice.js"></script>

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
.card:hover{
	box-shadow: 1px 1px 20px #ddd;
}
</style>
<script>

$(document).ready(function() {
	$("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp")
	$("#footers").load("<%=request.getContextPath() %>/views/footer.html")
});
</script>

</head>
<body>	
<div id="header"></div>
<div class="container">
       <div class="row">
        <h1>불량게시글 관리</h1>
    	</div>

    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive project-list">
                        <table class="table project-table table-centered table-nowrap">
                             <thead>
			                 <tr>
			                     <th scope="col">게시판</th>
			                     <th scope="col">글번호</th>
			                     <th scope="col">회원ID</th>
			                     <th scope="col">글제목</th>
			                     <th scope="col">날짜</th>
			                     <th scope="col">신고하기</th>
			                 </tr>
			             </thead>
			             
			             <tbody id="tbody">
			                 <%
			                 	for(int i = 0; i <list.size(); i++){
			                 %>
			                 <tr>
			                 	<td>
			                 	<%
			                 	String ctgr = "";
			                 	int cate = list.get(i).getTm_category_id();
			                 	if(cate == 100){
			                 		ctgr = "자유게시판";
			                 	}else if(cate == 110){
			                 		ctgr = "유저문답게시판";
			                 	}else if(cate == 120){
			                 		ctgr = "여행리뷰게시판";
			                 	}
			                 	%>
			                 	<%=ctgr %>
			                 	</td>
			                 	<td><%=list.get(i).getTm_b_no() %></td>
			                 	<td><%= list.get(i).getTm_id() %></td>
			                 	<td><a class="clickTitle" href="/Travel_Maker//SpamList.do?tm_b_no=<%=list.get(i).getTm_b_no() %>&tm_category_id=<%=list.get(i).getTm_category_id()%>"><%= list.get(i).getTm_b_title() %></a></td>
			                 	<%
			                 	if(list.get(i).getTm_b_modate() != null){
			                 	%>
			                 	<td>
			                 		<%=list.get(i).getTm_b_modate() %>
			                 	</td>
			                 	<%
			                 	}else{
			                 	%>
			                 	<td>
			                 		<%=list.get(i).getTm_b_date() %>
			                 	</td>
			                 	<%
			                 	}
			                 	%>
			                 	<td><%=list.get(i).getTm_lh_hate()%></td>
			                 </tr>
			                 <%
			                 	}
			                 %>
			             </tbody>
			         </table>
			     </div>
			    </div>
			   </div>
			  </div>
			 </div>
			 </div>
<div id="footers"></div>
</body>
</html>