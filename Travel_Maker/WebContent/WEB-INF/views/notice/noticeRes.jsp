<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int result = (Integer)request.getAttribute("res");
	if(result > 0){
%>
		{"res" : "성공"}
<%		
	}else{
%>
		{"res" : "실패"}
<%		
	}
%>