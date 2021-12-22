<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String minStr = request.getParameter("minDan");
	String maxStr = request.getParameter("maxDan");
	
	int minDan = 2;
	int maxDan = 9;
	if(minStr != null && minStr.matches("\\d{1,2}"))
	minDan = Integer.parseInt(minStr);
	if(maxStr != null && maxStr.matches("[0-9]{1,2}"))
	maxDan = Integer.parseInt(maxStr);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/gugudan.jsp</title>
</head>
<body>
<h4></h4>
<%!
   StringBuffer makeGugudan(int min, int max){
      StringBuffer danStr = new StringBuffer();
      for(int dan = min; dan <= max; dan++){
         danStr.append("<tr>");
         for(int mul = 1; mul <= 9; mul++){
            danStr.append(String.format("<td>%d*%d=%d</td>", dan, mul,(dan*mul)));
         }
         danStr.append("</tr>");
      }
      return danStr;
   }
%>
<form>
	<input type="number" name="minDan" value="<%=minDan%>"/>
	<input type="number" name="maxDan" value="<%=maxDan%>"/>
	<input type="submit" value="구구단"/>
</form>
<table border="1">
   <%=makeGugudan(minDan,maxDan) %>
</table>
<table border="1">
<%
      StringBuffer danStr = new StringBuffer();
      for(int dan = minDan; dan <= maxDan; dan++){
         danStr.append("<tr>");
         for(int mul = 1; mul <= 9; mul++){
            danStr.append(String.format("<td>%d*%d=%d</td>", dan, mul,(dan*mul)));
         }
         danStr.append("</tr>");
      }
      out.print(danStr);
%>
</table>



<table border="1">
<% for(int i = minDan ; i <= maxDan ; i++){%>
	<tr>
		<% for(int j = 1 ; j < 10 ; j++){ %>
			<td><%=String.format("%d*%d=%d", i, j, (i*j))%></td>
		<%} %>
	</tr>
<%} %>
</table>
</body>
</html>