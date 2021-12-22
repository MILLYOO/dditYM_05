<%@page import="tm.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)session.getAttribute("msg");
	MemberVO memVO = (MemberVO)session.getAttribute("memVO");	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>

		<%
		if(msg.equals("성공")){
		%>
		<script>
			alert('로그인에 성공하였습니다')
			location.href="<%=request.getContextPath()%>/views/home.html";
		</script>
		<%
		}else{
		%>	
		<script>
			alert('가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.')
			location.href="<%=request.getContextPath()%>/views/login.html";
		</script>
		<%
		}
		%>

</body>
</html>