<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int cnt = (Integer)request.getAttribute("cnt");				//db에서 변한 행 갯수
	String msg = (String)request.getAttribute("msg");			//성공 or 실패
	String userReq = (String)request.getAttribute("userReq");	//수정 or 삭제
	
	if(msg.equals("성공")){
		if(cnt == 1){
%>
			{
				"sw" : "<%=userReq %>에 성공하였습니다"
			}
	<%
		}else{
	%>
			{
				"sw" : "<%=userReq %>에 성공하였습니다"
			}
		<%
		}
	}else{
		%>
		{
			"sw" : "자신이 작성한 글만 <%=userReq %> 할 수 있습니다"
		}
	<%	
	}
	%>
		
