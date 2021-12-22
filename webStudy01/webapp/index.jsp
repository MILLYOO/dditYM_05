<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8"); // 이 메소드는 원래 바디에만 가능하지만, 우리는 server.xml에 useBodyUR~~ 어쩌구 넣어서 가능함
	String message = request.getParameter("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	if(StringUtils.isNotBlank(message)){
		%>
			<script type="text/javascript">
				alert("<%=message %>");
			</script>
		<%
	}
%>
<title>Insert title here</title>
</head>
<body>
<h1>웰컴 페이즤~</h1>
<%
	MemberVO authMember = (MemberVO)session.getAttribute("authMember");
	if(authMember != null){
		%>
		<a><%=authMember.getMem_id() %></a>
<%-- 		<form method = "post" action ="<%=request.getContextPath() %>/login/logout.do"> --%>
<!-- 			<input type="submit" value ="로그아웃"> -->
<!-- 		</form> -->
			<a href = "<%=request.getContextPath() %>/login/logout.do" onclick = "return clickHandler(event)" >로그아웃</a>
			<form name="logoutForm" method="post"></form>
		<%
	}else {
		%>
		<a href = "<%=request.getContextPath() %>/login/loginForm.jsp" >로그인</a>
		<%
	}
%>
<script type="text/javascript">
	function clickHandler(event) {
		event.preventDefault();
		document.logoutForm.action = event.target.href;
		document.logoutForm.submit();
		return false;
	}
</script>
</body>
</html>
