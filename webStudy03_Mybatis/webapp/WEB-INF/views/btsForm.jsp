<%@page import="java.util.Map.Entry"%>
<%@page import="kr.or.ddit.vo.BTSVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Map<String, BTSVO> btsMap = (Map) application.getAttribute("btsMap");
	BTSVO selected = (BTSVO) request.getAttribute("selected");
%>
<form method="post"> <!-- 폼태그에 액션이 없으면 해당 브라우저의 주소를 그대로 호출. -->
<select name="member" onchange="this.form.submit();" required>
	<option value>멤버선택</option>
	<%
		for(Entry<String, BTSVO> entry : btsMap.entrySet()){
				String code = entry.getKey();
				BTSVO member = entry.getValue();
				String selectedAttr = member.equals(selected)?"selected":"";
			%>
				<option value="<%=code %>"<%=selectedAttr %>><%=member.getName() %></option>
			<%
		}
	%>
</select>
</form>
</body>
</html>