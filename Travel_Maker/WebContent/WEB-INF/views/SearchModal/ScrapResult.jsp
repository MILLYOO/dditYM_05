<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 // 서블릿에서 실행된 값 가져오기
int cnt = (Integer)request.getAttribute("cnt");
if(cnt > 0){
%>
{
	"sw" : "스크랩 완료!",
	"cnt" : <%=cnt %>
}
<%}else { %>
{
	"sw" : "비회원은 스크랩을 할 수 없습니다!",
	"cnt" : <%=cnt %>
}
<% } %>