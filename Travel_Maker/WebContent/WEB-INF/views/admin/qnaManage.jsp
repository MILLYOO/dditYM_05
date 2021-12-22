<%@page import="tm.comm.vo.PagingVO"%>
<%@page import="tm.comm.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");
	PagingVO pagingVO = (PagingVO) request.getAttribute("pagingVO");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>1:1 문의 관리</title>
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

<script src="<%=request.getContextPath() %>/js/admin.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/style2.css">

<script>

$(document).ready(function() {
	$("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp")
	$("#footers").load("<%=request.getContextPath() %>/views/footer.html")
});

/* 회원 목록 조회 */
function fn_selectList(pageNo){
	const listForm = document.listForm;
	
	// 페이지 번호 설정
	pageNo ? listForm.pageNo.value =  pageNo : listForm.pageNo.value = 1;
	listForm.action = "/Travel_Maker/AdminQnaList.do";
	listForm.method = "post";
	listForm.submit();
}
</script>

<style>
h1 {
	margin: 20px 20px 20px 20px;
	text-align: center;
	color: #727272;
}
.col-lg-12{
 	font-size : 20px;
}
.pt-3{
 	text-align : center; 
}
#btnWrite{ 
 	float : right;
 	vertical-align : bottom;
}
.pagination{
 	 width : 100px;
	 margin-left: auto;
	 margin-right: auto;
}
#pagelist #btnPre{
	display : inline-block;
	width : 30px; 
	margin-right : 10px;
}
#pagelist #btnNe{
	display : inline-block;
	width : 30px;
	margin-left : 10px; 
}
#bodyDiv{
	font-family: 'Do Hyeon', sans-serif;
	height : 500px;
}
 #pagelist ul{ 
 	float : left;
 } 
#btnPage{
	 width : 100px;
	 margin-left: auto;
	 margin-right: auto;
}
#pagelist{
	width : 300px;
	margin-left: auto;
	margin-right: auto;
}
#searchSelect{
	display : inline-block;
	float : left;
	margin : 0px 10px;
	height : 34px;
}
#searchText{
	display : inline-block;
	float : left;
	width : 20%;
}
#searchIcon{
	display : inline-block;
	float : left;
}
.btn-danger{
	height : 34px;
	margin-left : 10px;
	width : 150%;
}
.searchDiv{
	width : 40%;
	margin : 30px;
}
.sortdiv{
	margin-right : 100px;
	float : right;
	font-size : 20px;
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

body {
	font-family: 'Do Hyeon', sans-serif;
}

</style>
</head>
<body>
	<div id="header"></div>
	<div class="container">
		<div class="row">
			<h1>1:1 문의 관리</h1>
		</div>

    <div class="row">
        <div class="col-lg-12 searchDiv">
				<div>
					<div>
                    <form action="<%=request.getContextPath()%>/AdminQnaList.do" method="post">
                        <label>회원검색</label>
                        <div class="form-group mb-0">
                            <div class="input-group mb-0" id="searchText">
                                <input id="inputSearch" type="text" name="inputSearch" class="form-control" placeholder="Search..." aria-describedby="project-search-addon" />
                            </div>
                            <div id="searchIcon">
                                <button class="btn btn-danger" type="submit" id="project-search-addon"><i class="fa fa-search search-icon font-12"></i></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- end row -->
    
    
 <div id="bodyDiv">
    <form name="listForm">
	<input type="hidden" name="pageNo">
        <div class="col-lg-12">
 				<div class="card">
					<div class="card-body">
                    <div class="table-responsive project-list">
                        <table class="table project-table table-centered table-nowrap">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">제목</th>
                                    <th scope="col">회원ID</th>
                                    <th scope="col">날짜</th>
                                    <th scope="col">상태</th>
                                </tr>
                            </thead>
                            
                            <tbody id="tbody">
                                <%
                                	for(int i = 0; i <list.size(); i++){
                                %>
                                <tr>
                                	<th scope="row"><%=i + 1 %></th>
                                	<td><a class="clickTitle" href="/Travel_Maker/AdminQnaSelect.do?tm_b_no=<%=list.get(i).getTm_b_no()%>"><%=list.get(i).getTm_b_title() %></a></td>
                                	<td><%= list.get(i).getTm_id() %></td>
                                	<td><%= list.get(i).getTm_b_date() %></td>
                                	<td>
                                	<% 
                                	   String tmp = "";
                                	   int ox = list.get(i).getTm_b_ox();
                                	   if(ox == 0){
                                		   tmp = "답변대기"; 
                                	%>  
                                			<p style="color:black; font-size:1.1em;"><%= tmp %></p>
                                	
                                	<%}else{
                                			tmp = "답변완료";	%>
                                			<p style="color:red; font-size:1.1em;"><%= tmp %></p>
                                	<%} %>
                                	</td>
                                </tr>
                                <%
                                	}
                                %>
                                
                                
                                
                            </tbody>
                        </table>
                                
                        <%
						 if(pagingVO.getTotalPageCount() > 0){
						 %>
	 <ul class="pagination justify-content-end mb-0">
	                        <% if(pagingVO.getFirstPageNo() > pagingVO.getPageSize()){ %>
	                            <li class="page-item">
	                                <a class="page-link" href="javascript:fn_selectList(<%=pagingVO.getFirstPageNo() - pagingVO.getPageSize()%>)">Previous</a>
	                            </li>
	                        <%} %>    
	                            
	                        <% for(int pNo = pagingVO.getFirstPageNo(); pNo <= pagingVO.getLastPageNo(); pNo++){ %> 
		                            <li class="page-item <%if(pNo == pagingVO.getCurrentPageNo()){%>active<%} %>"><a class="page-count" href="javascript:fn_selectList(<%=pNo%>)"><%=pNo %></a></li>
	                        <%} %>    
	                            
	                        <% if(pagingVO.getLastPageNo() < pagingVO.getTotalPageCount()){ %>    
	                            <li class="page-item">
	                                <a class="page-link" href="javascript:fn_selectList(<%=pagingVO.getFirstPageNo() + pagingVO.getPageSize()%>)">Next</a>
	                            </li>
	                        <%} %>
	                        
						<% 
						 }
						%>  
						</ul>
                    </div>
                    </div>
                    </div>
                    </div>
           </form>
                    <!-- end project-list -->

                    <div class="pt-3">
                        <ul class="pagination justify-content-end mb-0">
                        
					
					
                        </ul>
                    </div>
                    </div>   
<!-- end row -->
</div>
       	<div id="footers"></div>
</body>
</html>