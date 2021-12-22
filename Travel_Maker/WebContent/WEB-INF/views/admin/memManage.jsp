<%@page import="tm.comm.vo.PagingVO"%>
<%@page import="tm.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<MemberVO> list = (List<MemberVO>)request.getAttribute("list");
	PagingVO pagingVO = (PagingVO) request.getAttribute("pagingVO");
	
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 관리</title>
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
	$("#header").load("<%=request.getContextPath()%>/views/MainHeader.jsp")
	$("#footers").load("<%=request.getContextPath()%>/views/footer.html")
});


/* 회원 목록 조회 */
function fn_selectList(pageNo){
	const listForm = document.listForm;
	
	// 페이지 번호 설정
	pageNo ? listForm.pageNo.value =  pageNo : listForm.pageNo.value = 1;
	listForm.action = "/Travel_Maker/MemManage.do";
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
.card{
	margin-bottom: 40px;
}
body {
	font-family: 'Do Hyeon', sans-serif;
}
#pagelist {
	width: 200px;
}

.page-link {
	width: 50px;
	padding: 5px 20px;
}

.pager, .pagination {
	width: 100px;
	float: left;
}
.page-link1 {
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
.pagination {
    display: inline-block;
    padding-left: 0;
    margin: 20px 0;
    border-radius: 4px;
    width : 100px;
}
.page-item{
   
  float : left;
}
</style>
</head>

<body>
	<div id="header"></div>
<div class="container">
    <div class="row">
        <h1>회원관리</h1>
    </div>
    <div class="row">
        <div class="col-lg-12 searchDiv">
            <div>
                <div>
                    <form action="<%=request.getContextPath()%>/MemManage.do" method="post">
                        <label>회원검색</label>
                        <div class="form-group mb-0">
                        	<div>
                        		<select id="searchSelect" name="searchSelect">
								  <option value="아이디">아이디</option>
								  <option value="이름">이름</option>
								  <option value="탈퇴여부">탈퇴여부</option>
								</select>
                        	</div>
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

    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body" id="showList">
                    <div class="table-responsive project-list">
                    <form name="listForm">
						<!-- 수정 -->
                        <input type="hidden" name="pageNo">
						<!-- 수정 -->
                        <table class="table project-table table-centered table-nowrap">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">아이디</th>
                                    <th scope="col">이름</th>
                                    <th scope="col">가입일</th>
                                    <th scope="col">탈퇴여부</th>
                                    <th scope="col">구분</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                            	int listSize = list.size();
                            
                            	for(int i = 0 ; i < listSize; i++){
                         	%>
                                <tr>
                                    <th scope="row"><%=i+1 %></th>
                                    <td><a href="/Travel_Maker/memManageSelect.do?MEM_ID=<%= list.get(i).getTm_id() %>"><%= list.get(i).getTm_id() %></a></td>
                                    <td><%= list.get(i).getTm_name() %></td>
                                    <td>
									<%
										String mdate = list.get(i).getTm_date();
										if(mdate == null){
											mdate = "-";
										}
									%>                                    
                                    <%= mdate %>
                                    </td>
                                    <%
                                    	String diss = list.get(i).getTm_diss();
                                    	if(diss == null || diss.equals("X")){
                                    		diss = "";
                                    	}
                                    %>
                                    <td>
                                    <%= diss %>
                                    </td>
                                    <td>
                                    <%
                                    	String auth = "";
                                    	int author = list.get(i).getTm_author();
                                    	if(author == 0){
                                    		auth = "관리자";
                                    	}else if(author == 1){
                                    		auth = "일반회원";
                                    	}else if(author == 2){
                                    		auth = "블랙리스트";
                                    	}else if(author == -1){
                                    		auth = "탈퇴회원";
                                    	}
                                    %>
                                    <%=auth %>
                                    </td>
                                </tr>
                         	<%
                            	}
                            %>

                            </tbody>
                        </table>
<!--                                    <div class="pageList"> -->
<!--                         <ul class="pagination justify-content-end mb-0"> -->
	                      <%
						 if(pagingVO.getTotalPageCount() > 0){
						 %>
	
  <% if(pagingVO.getFirstPageNo() > pagingVO.getPageSize()){ %>
	                            <ul class="pager">
	                              
	                                <a class="page-link1" href="javascript:fn_selectList(<%=pagingVO.getFirstPageNo() - pagingVO.getPageSize()%>)">이전</a>
	                               
	                            </ul>
	                        <%} %>    
	                            <ul class="pagination justify-content-end mb-0">
	                        <% for(int pNo = pagingVO.getFirstPageNo(); pNo <= pagingVO.getLastPageNo(); pNo++){ %> 
		                            <li class="page-item <%if(pNo == pagingVO.getCurrentPageNo()){%>active<%} %>">
		                              <a class="page-count" href="javascript:fn_selectList(<%=pNo%>)"><%=pNo %></a></li>
	                        	
	                        <%} %>    
	                         </ul>   
	                        <% if(pagingVO.getLastPageNo() < pagingVO.getTotalPageCount()){ %>    
	                            <ul class="pager">
	                           
	                                <a class="page-link1" href="javascript:fn_selectList(<%=pagingVO.getFirstPageNo() + pagingVO.getPageSize()%>)">다음</a>
	                             
	                            </ul>
	                        <%} %>
	                        
						<% 
						 }
						%>      
						
                        </form>
                    </div>
                    <!-- end project-list -->
					
         
                    </div>

						
                    
                    
                </div>
            </div>
        </div>
    </div>
    <!-- end row -->


   	<div id="footers"></div>
</body>
</html>