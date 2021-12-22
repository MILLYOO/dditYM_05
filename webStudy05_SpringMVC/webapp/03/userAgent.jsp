<%@page import="kr.or.ddit.enumpkg.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/userAgent</title>
<script type="text/javascript">
<%
			String agent = request.getHeader("User-Agent");
			String browserName = BrowserType.findBrowserName(agent);
// 			agent = agent.toUpperCase();
// 			Map<String,String> browserMap = new LinkedHashMap<>();
// 			browserMap.put("EDG", "엣지");
// 			browserMap.put("SAFARI", "사파리");
// 			browserMap.put("CHROME", "크롬");
// 			browserMap.put("OTHER", "기타");
// 			browserName = BrowserType.OTHER.getBrowerName();
// 			for(BrowserType tmp : BrowserType.values()){
// 				String key = tmp.name();
// 				if(agent.contains(key)){
// 					browserName = tmp.getBrowerName();
// 				}
// 			}
			
//  		browserName = browserMap.get("OTHER");
// 			for(Entry<String,String> entry : browserMap.entrySet()){
// 				String key = entry.getKey();
// 				String name = entry.getValue();
// 				if(agent.contains(key)){
// 					browserName = name;
// 					break;
// 				}
// 			}
			
			String pattern = "당신의 브라우저는 %s입니다!";
			String message = String.format(pattern,browserName);
	%>
window.onload = function(){
	alert("<%=message%>");
}
</script>
</head>
<body>
<h4> User-Agent Header</h4>
<!-- 클라이언트의 브라우저가 크롬이면,
"당신의 브라우저는 크롬입니다!"
엣지면,
"당신의 브라우저는 엣지입니다!"
사피라면,
"당신의 브라우저는 사파리입니다!"
형태의 메시지를 alert 창으로 출력. -->

</body>
</html>