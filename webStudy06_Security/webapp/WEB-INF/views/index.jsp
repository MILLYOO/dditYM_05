<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<h1>웰컴 페이즤~</h1>

<!-- 조건절 : 로그인이 되어있으면 authMember를 꺼내라-->
<security:authorize access="isAuthenticated()">
<!-- authentication의 session 안에 memberVOWrapper안의 authMember -->
<security:authentication property="principal.authMember" var="authMember"/>
				<a href ="${pageContext.request.contextPath }/mypage.do">${authMember.memName }[${authMember.memRole }]</a>
			<a href = "${pageContext.request.contextPath }/login/logout.do" onclick = "return clickHandler(event)" >로그아웃</a>
			<form name="logoutForm" method="post">
				<input type="hidden" name="_csrftoken" value ="token_value" />
			</form>
</security:authorize>
<security:authorize access="isAnonymous()">
			<a href = "${pageContext.request.contextPath }/login/loginForm.jsp" >로그인</a>
			<a href = "${pageContext.request.contextPath }/member/memberInsert.do" >회원가입</a>
</security:authorize>

<script type="text/javascript">
	function clickHandler(event) {
		event.preventDefault();
		document.logoutForm.action = event.target.href;
		document.logoutForm.submit();
		return false;
	}
</script>

