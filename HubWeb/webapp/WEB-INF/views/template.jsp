<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<tiles:insertAttribute name="preScript" />
<meta charset="UTF-8">
<title>HUB-WEB 물류관리를 쉽고 빠르고 자신있게!! </title>
</head>
<body id="page-top">
<div id="wrapper">
   	<div class="loading">
   	 <img src="${cPath}/resources/images/허브웹로고.gif" alt="loading">
	</div>
	<div id="left">
		<tiles:insertAttribute name="left"/>
	</div>
	<div id="content">
	     <tiles:insertAttribute name="header"/>
		 <tiles:insertAttribute name="content"/> 
		 <tiles:insertAttribute name="codeScript" />
	</div>
		<tiles:insertAttribute name="postScript" />
</div>
</body>
</html>
