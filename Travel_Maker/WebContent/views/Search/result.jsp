<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 // 서블릿에서 실행된 값 가져오기
int result = (Integer)request.getAttribute("result");
if(result > 0){
%>
{
	"sw" : "저장성공"
}
<%}else { %>
{
	"sw" : "저장실패"
}
<% } %>