<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/destination.jsp</title>
</head>
<body>
	<h4></h4>
	<pre>
도착지
</pre>
	<%
		out.println(pageContext.getAttribute("pageAttr"));
		out.println(request.getAttribute("reqeustAttr"));
		out.println(session.getAttribute("sessionAttr"));
		out.println(application.getAttribute("applicationAttr"));
	%>
</body>
</html>