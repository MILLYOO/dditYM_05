<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="../page/css/default.css">
<link rel="stylesheet" type="text/css" href="../page/css/style2.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>블랙리스트 등록</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#header").load("../views/MainHeader.jsp")
		$("#footers").load("../views/footer.html")
	});
</script>  
<style>
input[type="text"]{
	border: 1px solid lightgray;
	margin: 10px;
}
form{
	height:500px;
    margin-top: 15%;
}
.blackform{
	    width: 493px;
    margin: 0 auto;
}
.btn-primary {
    color: #fff;
    background-color: #337ab7;
    border-color: #2e6da4;
    display: inline-block;
    margin-bottom: 0;
    font-weight: 400;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    border-radius: 4px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}   
</style>


</head>
<body>
<div id="header"></div>
	<form action="<%=request.getContextPath() %>/black/insert.do" method="post">
	<div class="blackform">
		<table>
			<tr>
				<td>회원 아이디</td>
				<td><input type="text" name="tm_id"></td>
			</tr>
			<tr>
				<td>차단 사유</td>
				<td><input type="text" name="tm_bl_rs"></td>
			</tr>
		</table>
		<div class="write">
		<input type="submit" value="등록" class="btn btn-primary">
		</div>
		</div>
	</form>
	<div id="footers"></div>
</body>
</html>