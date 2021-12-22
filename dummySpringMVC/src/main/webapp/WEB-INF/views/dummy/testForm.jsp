<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${dataList }
<form method="post" enctype="multipart/prom_\">
	<input type="text" name="prop1" placeholder="prop1">
	<input type="text" name="prop2" placeholder="prop2">
	<input type="file" name="prop3" placeholder="prop3">
	<input type="submit" value="전송">
</form>
</body>
</html>