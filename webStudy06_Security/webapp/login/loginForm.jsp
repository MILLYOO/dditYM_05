<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login/loginForm.jsp</title>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
	<script type="text/javascript">
		alert("${SPRING_SECURITY_LAST_EXCEPTION.message}");
	</script>
	<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
</c:if>
</head>
<body>
<form:form action ="${pageContext.request.contextPath }/login/loginProcess.do" method="post">
<ul>
	<li>
		<%
			String failId = (String)session.getAttribute("failId"); // jsp는 session을 기본적으로 지원한다. (기본객체)
			// flash attribute
			session.removeAttribute("failId");
		%>
		아이디 : <input type="text" name="memId" id = "id" value="<%=Objects.toString(failId, "") %>" />
	</li>
	<li>
		비밀번호 : <input type="text" name="memPass" />
		<input type="submit" value="로그인" />
	</li>
	
</ul>

</form:form>
</body>
</html>