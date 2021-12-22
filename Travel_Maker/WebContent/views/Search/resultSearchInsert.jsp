<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int result = (Integer)request.getAttribute("result");
	if(result > 0){
%>		
	<script>
		alert('여행지 추가에 성공하였습니다')
		location.href="<%=request.getContextPath()%>/views/Search/InsertSearch.html";
	</script>
	<%	
	}else{
	%>
	<script>
		alert('여행지 추가에 실패하였습니다')
		location.href="<%=request.getContextPath()%>/views/Search/InsertSearch.html";
	</script>
	<%	
	}
	%>
