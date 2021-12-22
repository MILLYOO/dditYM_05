<%@page import="java.io.FilenameFilter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// String folderPath = "d:/contents";
// File folder = new File(folderPath);
// // 1.7버전 이전
// // 이미지 파일만 걸러서 가져와 컨트롤 할 수 있도록
// File[] images = folder.listFiles(new FilenameFilter(){
// 	public boolean accept(File dir, String name){
// 		String mime = application.getMimeType(name);
// 		return mime!=null && mime.startsWith("image/");	//톰캣에매칭안된확장자는null이므로 null이 아닌 조건 걸어야 함
// 	}
// });	
	File[] images = (File[])request.getAttribute("images");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		let imageArea = $("#imageArea")
		const SRCPTRN = "${pageContext.request.contextPath }/image.do?image=%v"
		
		$("[name='image']").on("change",function(event){
// 			this.form.submit();	//html element 함수 , 이벤트 발생하지 않음
// 			$(this.form).submit();	//jQuery 함수
			let image = $(this).val();
			imageArea.empty();
			if(image){
				let src = SRCPTRN.replace("%v",image);
				let imgTag = $("<img>").attr("src",src)
				imageArea.html(imgTag);
			}				
			return true;
		});
		
		$("form:first").on("submit",function(event){
			// 선택한 옵션의 value가 유효한지 검증
			let value = $(this.image).val();
			let valid = true;
			if(!value){
				console.log("파라미터 누락");
				valid = false;
				$(this.image).focus();
			}
			return valid; // value가 있는지, 유효한 길이를 가지고 있는지
		});
	});
</script>
</head>
<body>

<form action="${pageContext.request.contextPath }/image.do">
	<select name="image" required> 	
<!-- 	html5부터 required 사용 가능 , 이 옵션 사용하면 검증하지 않아도 됨...-->
		<option value >이미지선택</option>
<%
	for(int i = 0; i < images.length; i++){
%>
		<option><%=images[i].getName() %></option>
<%
	}
%>
	</select>
</form>
<div id="imageArea">
</div>
</body>
</html>