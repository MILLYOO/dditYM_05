<%@page import="tm.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.invalidate();
%>

		<script>
			alert('로그아웃 완료')
			location.href="<%=request.getContextPath()%>/views/home.html";
		</script>
