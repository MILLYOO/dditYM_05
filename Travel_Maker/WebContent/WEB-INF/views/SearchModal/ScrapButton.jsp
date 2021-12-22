<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int cnt = (int)request.getAttribute("cnt");
	System.out.println(cnt);
	if(cnt > 0){
		%>
{ "sw" : "true" }
<%}else { %>
{ "sw" : "false" }
<% } 
		%>