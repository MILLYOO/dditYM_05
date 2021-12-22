<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String result = (String)request.getAttribute("result");
	if(result.equals("성공")){
%>
	{"sw" : "성공"}
<%
	}else{
%>
	{"sw" : "실패"}
<%
	}
%>

