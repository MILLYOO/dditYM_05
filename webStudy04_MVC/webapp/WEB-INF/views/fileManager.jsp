<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 1. /resources/images/* -> 옵션으로 이미지 목록 제공하기 -->
<select size="10" id = "srcSelect">

</select>
<div>
	<input type="radio" name="command" value="COPY"/> COPY
	<input type="radio" name="command" value="MOVE"/> MOVE
	<input type="radio" name="command" value="DELETE"/> DELETE
</div>
<!-- 2. /09/* 에 해당하는 자원의 목록 제공 -->
<select size="10" id = "destSelect">

</select>
</body>
</html>