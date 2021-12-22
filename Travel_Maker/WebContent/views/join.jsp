<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
  //servlet에서 결과를 request로 답을 가져와
  //클라이언트 요청시(전송) 전송되는 데이터를 받아와야 한다
  String id = (String)request.getAttribute("joinSend1"); //db에서 가져온 id
  String inputid = (String)request.getAttribute("joinSend2"); //내가 입력한 id

  if(id != null){
%>
	{
	  "flag" : "ok",		
	  "msg" : "<%= id %>님 가입을 축하합니다."
	}	
	  
<%  }else{ %>
	{
	  "flag" : "no",	
	  "msg" : "<%= inputid %>님 가입 도중 오류가 발생하였습니다"
	}

<%	  
  }
%>  
  
