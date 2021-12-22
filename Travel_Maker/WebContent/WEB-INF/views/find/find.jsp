<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sw = (String)request.getAttribute("res");

	if(sw != null){
%>
{"sw" : "찾기 결과 : [ <%=sw %> ]"}
<%		
	}else{
%>
{"sw" : "회원 정보가 일치하지 않거나 존재하지 않는 회원입니다."}
<%		
	}
%>
