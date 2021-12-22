<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int result = (Integer)request.getAttribute("res");
	Integer writeNo = (Integer)request.getAttribute("rebNo");
	if(writeNo == null){
		writeNo = 0;
	}

	if(result > 0){
%>
		{"res" : "성공",
		 "writeNo" : <%=writeNo%>}
<%		
	}else{
%>
		{"res" : "실패"}
<%		
	}
%>