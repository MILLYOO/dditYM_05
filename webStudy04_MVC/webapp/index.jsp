<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
	//request.setCharacterEncoding("UTF-8"); // 이 메소드는 원래 바디에만 가능하지만, 우리는 server.xml에 useBodyUR~~ 어쩌구 넣어서 가능함
	String message = request.getParameter("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includee/preScript.jsp"/>
<title>Insert title here</title>
</head>
<body>
<h1>웰컴 페이즤~</h1>
<c:set var="authMember" value="${sessionScope.authMember }" />
<c:choose>
	<c:when test="${not empty authMember }">
				<a href ="${pageContext.request.contextPath }/mypage.do">${authMember.memName }[${authMember.memRole }]</a>
<%-- 		<form method = "post" action ="${pageContext.request.contextPath }/login/logout.do"> --%>
<!-- 			<input type="submit" value ="로그아웃"> -->
<!-- 		</form> -->
			<a href = "${pageContext.request.contextPath }/login/logout.do" onclick = "return clickHandler(event)" >로그아웃</a>
			<form name="logoutForm" method="post">
				<input type="hidden" name="_csrftoken" value ="token_value" />
			</form>
	</c:when>
	<c:otherwise>
			<a href = "${pageContext.request.contextPath }/login/loginForm.jsp" >로그인</a>
			<a href = "${pageContext.request.contextPath }/member/memberInsert.do" >회원가입</a>
	</c:otherwise>
</c:choose>
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
