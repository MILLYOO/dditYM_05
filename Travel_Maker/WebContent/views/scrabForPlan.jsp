<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
	
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
<script type="text/javascript" src="<%=request.getContextPath() %>/js/scrab.js"></script>


<title>일정용 스크랩 화면</title>
<script>
$(function () {
	scrabForPlan();
	
})

</script>

<style>
.scrabBowl{
	width: 50%;
	margin : 30px auto;
}
.myimg{
	width: 20%;
}
.scrab-img{

	max-width : 100%;
	height: auto;

}
.scimg{
	max-width : 100%;
	height: auto;
}
.scrab-addr{
	width : 70%;
	float: left;
	margin: 10px 10px;
}
.scrab-text{
	width : 70%;
	float: left;
	margin: 10px 10px;
}
</style>
</head>
<body>

<div class="container">
  <h2>스크랩 목록</h2>
  <div class="panel-group myscrab">

  </div>
</div>

</body>
</html>