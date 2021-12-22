<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/implicitObject.jsp</title>
</head>
<body>
<h4>기본 객체(내장 객체)</h4>
<pre>
	: JSP에 해당하는 서블릿 소스가 파싱될 때 컨테이너에 의해 기본 제공되는 객체
	0. 객체(타입)
	1. request(HttpServletRequest)
	2. response(HttpServletResponse)
	3. <a href="bufferDesc.jsp">out(JspWriter)</a> - 출력스트림 (2byte 캐릭터스트림)
		: 출력 스트림으로 버퍼에 응답 데이터를 기록할 때 사용됨. 버퍼 제어에 사용.
	4. <a href="../08/sessionDesc.jsp">session(HttpSession)</a> / Cookie
	5. <a href="../09/servletContextDesc.jsp">application(ServletContext)</a>
	
	6. config(ServletConfig) - ServletConfig:서블릿의 메타정보 
	7. page(Object타입이지만 JSP 인스턴스 자체를 뜻함, this) - jsp의 레퍼런스
	
	8. exception(Throwable) : 에러 처리 용도의 페이지에서 활성화(isErrorPage="true")
	
	***** 9. <a href="../09/pageContextDesc.jsp">pageContext : 가장 먼저 생성되는 기본 객체. 나머지 객체의 참조를 소유함.</a>
</pre>
</body>
</html>