<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    // 서블릿에서 실행된 값 가져오기
    
    int seq = (Integer)request.getAttribute("insertNo");
    	if(seq > 0){
    		%>
    			{
    				"sw" : "저장 성공!"
    			}
    		
    		
    		
    	<%	
    	}
    	else{
    	%>
    		{
    			"sw" : "저장 실패.."
    		}  		
    	<%
    	}
    	%>

    
    