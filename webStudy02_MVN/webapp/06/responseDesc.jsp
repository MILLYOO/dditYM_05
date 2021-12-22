<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/responseDesc.jsp</title>
</head>
<body>
<h4>response(HttpServletResponse)</h4>
<pre>
	: 서버에서 클라이언트로 전송되는 응답과 관련된 모든 wjd보를 캡슐화한 객체.
	
	Http 따른 response 패키징 방식
	1. Response Line : response status code(응답 상태 코드)
		: 명령 처리 결과를 표현하는 세자리 숫자
		stateless(비상태,무상태 유지 특성), connectless(비연결지향) <-> statefull(상태 유지 특성), connectfull(연결지향)
		1) 100~ : ing...
		2) 200~ : OK
		3) 300~ : 처리가 완료되려면, 클라이언트로부터 추가 액션이 필요함
			304(Not Modified) - 변경된 적이 없다.
			302/307(Moved) -> Location과 한쌍으로 사용된다.
		4) 400~	: client side fail, 
			400(Bad Request)
			404(Not found)
			401<%=HttpServletResponse.SC_UNAUTHORIZED %>
			403<%=HttpServletResponse.SC_FORBIDDEN %> 					401,403 데이터의 보호를 위한 코드라고 생각.
			405<%=HttpServletResponse.SC_METHOD_NOT_ALLOWED %>			개발자가 발생시키는 경우보다는 서버나 프레임워크에서 발생하는 경우가 많음.
			415<%=HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE %>		클라이언트가 서버에 요청한 것을 서버에서 지원하지 않을 경우에 발생.
			세션과 쿠키는 stateless의 단점을 보완하기 위해 개발된 것이다.
			 
		5) 500~ : server side fail,
		    500(Internal server error)
	  상태 코드 변경시 : responsesendError[sendRedirect]
	  
	2. Response Header : content meta data, setHeader(name, value)
		1) content mime setting : Content-Type 
			response.setHeader("Content-Type", value);
			response.setContentType(value) - 서블릿 2.5이후에 만들어짐.
			page 지시자의 contentType 속성 - jsp에서만 사용가능함 서블릿에서 사용 X
		2) cache control
			<a href="cacheControl.jsp">cacheControl.jsp</a>	 
		3) auto request 를 통한 페이지 갱신
			<a href="autoRequest.jsp">autoRequest.jsp</a>
		4) flow control
			<a href="flowControl.jsp">flowControl.jsp</a>
		
	3. Response Body(Content Body, Message Body)
		: request method 가 head 인 경우, body가 구성되지 않음.
		: redirect 이동 방식의 경우, 중간에 body가 없는 응답이 전송
</pre>
<img src="<%=request.getContextPath() %>/resources/images/cat1.jpg"/>
</body>
</html>