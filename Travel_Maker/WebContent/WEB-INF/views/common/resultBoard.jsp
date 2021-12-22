<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int cnt = (Integer)request.getAttribute("cnt");
	if(cnt > 0){
%>

	{
		"sw" : "성공",
		"cnt" : <%=cnt %>
	}

	<%}else { %>
	{
		"sw" : "실패",
		"cnt" : <%=cnt %>
	}
	
	<%} %>

