<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int cnt = (Integer)(request.getAttribute("cnt"));
	String id = (String)request.getAttribute("id");
	
	if(cnt == 1){
%>	
		{
			"msg"	: "성공"
		}
	<% 
	}else{
	%>
		{
			"msg"	: "실패"
		}
<%	
	}
%>