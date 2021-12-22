<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int cntBlack = (Integer)(request.getAttribute("cntBlack"));
	int cntMember = (Integer)(request.getAttribute("cntMember"));
	
	if(cntBlack > 0 && cntMember > 0){
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