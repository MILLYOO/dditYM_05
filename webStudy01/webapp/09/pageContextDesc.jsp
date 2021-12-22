<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/pageContextDesc.jsp</title>
</head>
<body>
<h4>pageContext(PageContext)</h4>
<pre>
	: 나머지 기본객체의 참조 소유자.
	<%=request == pageContext.getRequest() %>
	<%=response == pageContext.getResponse() %>
	
	활용
	1. 속성(attribute) 데이터 관리(핸들링) <% pageContext.setAttribute("sample", "value", PageContext.SESSION_SCOPE); %>
	2. flow control
	<%--
		String destination = "/07/destination.jsp";
// 		request.getRequestDispatcher(destination).forward(request, response);
// 		pageContext.forward(destination);	// 페이지가 있어야지만 사용할 수 있는 구조다.(모델1구조에서 많이 쓰임) 나중에 여유가 되면 톰캣의 페이지 콘텍스트의 코드를 확인해라 그러면 위에 코드가 숨어있다.
// 		request.getRequestDispatcher(destination).include(request, response);
		pageContext.include(destination); // include 위치 예상 가능.
	--%>
	3. 에러 데이터 확보 : <%= exception == pageContext.getException() %>
	이 라인이 어디에 출력되나!!!!!
</pre>
</body>
</html>