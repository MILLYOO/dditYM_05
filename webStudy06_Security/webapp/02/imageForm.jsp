<%@page import="java.io.FilenameFilter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String folderPath = "d:/contents";
	File folder = new File(folderPath);
	File[] images = folder.listFiles(new FilenameFilter(){
		public boolean accept(File dir, String name){
			String mime = application.getMimeType(name); // application == getServletContext()
			return mime!=null && mime.startsWith("image");
		}
	});
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		$("[name='image']").on("change", function(event){
			console.log(event);
			console.log(this);
			console.log($(this));
// 	 		this.form.submit();
			$(this.form).submit();
			return true;	
		})
		
		$("form:first").on("submit",function(){
			console.log(event);
			let value = $(this.image).val();
			console.log(value);
			let valid = true;
			if(!value){
				console.log("파라미터 누락");
				valid = false;
				$(this.image).focus();
			}
	 		return valid;
		});
		
	});
</script>
</head>
<body>
<form action="${pageContext.request.contextPath }/image.do">
<%-- 	<select name="image" onchange="this.form.submit();">
		<%
		for(int i = 0 ; i < images.length ; i++){
		%>
		<option><%=	images[i].getName()%></option>
		<%
		}
		%>
	</select> --%>
	
	<select name="image" required="required">
		<option value>이미지 선택 </option> <!-- prompt텍스트는 벨류를 가지고 있어야하는데 그게 값이 없어야한다. -->
		<% for(File image : images){
			%>
			<option><%=image.getName() %></option>
		<%
		}
		%>
	</select>
</form>
</body>
</html>