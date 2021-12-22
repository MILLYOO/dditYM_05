<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  //서블릿에서 수행된 결과값을 가져오기
  String chkk = (String)request.getAttribute("chkI"); //CheckID서블릿에 있는 chkId의 값을 request.getAttribute("chk");가 가져와서 chkk에 넣어주는 거야.
   if(chkk != null){
%>	
	{ 
	  "flag" : "no",	
	  "sw" : "사용불가능한 아이디입니다"}
   
 <% }else{ %>
 
	{ 
	  "flag" : "ok",
	  "sw" : "사용가능한 아이디입니다" }
<%  }
%>
