<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>회원아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>이메일</th>
				<th>주소(상위주소만)</th>
				<th>마일리지</th>
			</tr>
		</thead>
		<tbody>
			<%
				List<MemberVO> memberList = (List) request.getAttribute("memberList");
				if (memberList.isEmpty()) {
			%>
			<tr>
				<td colspan="6">회원없음</td>
			</tr>
			<%
				} else {
					for (MemberVO vo : memberList) {
			%>
			<tr>
				<td><%=vo.getMemId()%></td>
				<td><%=vo.getMemName()%></td>
				<td><%=vo.getMemHp()%></td>
				<td><%=vo.getMemMail()%></td>
				<td><%=vo.getMemAdd1()%></td>
				<td><%=vo.getMemMileage()%></td>
			</tr>
			<%
				}
			}
			%>
		</tbody>
	</table>
</body>
</html>