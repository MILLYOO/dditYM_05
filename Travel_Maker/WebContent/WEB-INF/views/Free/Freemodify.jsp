<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	//서블릿에서 가져오기
    int cnt = (Integer)request.getAttribute("res");
    
    
    if(cnt > 0){
    %>
    
    {
    	"sw" : "수정성공"
    }
    
    <%	}else{ %>
    
    {
    	"sw" : "수정실패"
    }
    
    <%	} %>