<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%> <!-- static import 구문 1.7버전 이후-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<!-- meta : 바디의 부가 정보 -->
<!-- <meta http-equiv="Refresh" content="5;url=https://www.naver.com"> -->
<!-- Refresh - 동기요청 -->
<title>06/autoRequest.jsp</title>
</head>
<body>
<h4>헤더를 이용한 페이지 갱신(동기 요청)</h4>
<% Locale[] locales =  Locale.getAvailableLocales();  %>
<select id = "language">
<%
	for(Locale tmp : locales){
		String text = tmp.getDisplayLanguage(tmp);
		if(text == null || text.isEmpty()) continue;
		String languageTag = tmp.toLanguageTag();
		String selected = tmp.equals(request.getLocale())?"selected":"";
		%>
		<option value="<%=languageTag%>" <%=selected %>><%=text %></option>
		<%
	}
%>
</select>
<h4>현재 서버의 시간 : <span id = "serverTime"></span></h4>
<h4>현재 클라이언트의 시간 : <span id = "clientTime"></span></h4>
<!-- <h4><span id = "timer"></span>초 뒤에 네이버로 이동.</h4> -->
<pre>
	auto request 방법
	1. server side : Refresh 응답 헤더
	<%-- <%
		response.setIntHeader("Refresh", 1);
	%> --%>
	2. client side
		1) html : meta 태그의 http-equiv 사용
		2) javascript : scheduling function 사용, location.reload()


</pre>
<script type="text/javascript">
	/* const TIMERAREA = document.getElementById("timer");
	const TIMEINIT = 5;
	let timeVal = TIMEINIT;
	
	setInterval(() => {
		TIMERAREA.innerHTML = --timeVal;
	}, 1000); 
	setTimeout(() => {
		location.reload(true);
	}, 1000);*/
	let client = $("#clientTime");
	let server = $("#serverTime")
	let language = $("#language");
	setInterval(function(){
		$.ajax({
			url : "<%=request.getContextPath() %>/getServerTime.do",
			method : "get",
			data: {
				language:language.val()
			}, 
			dataType : "text",
			success : function(resp) {
				server.text(resp)
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
		client.text(new Date());
	}, 1000);

</script>
</body>
</html>