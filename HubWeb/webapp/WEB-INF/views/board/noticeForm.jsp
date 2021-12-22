<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js" ></script>

<style>
h3{margin-bottom: 50px;}
input{margin: 15px 5px;}
#titleTag{width:800px;margin:30px 0px;}
#conDiv{width:810px;}
#fileBtnArea{width: 300px; display: inline-block; margin-left:360px;}
#fileContainer{float: left;}
#noticeForm, h3{margin-left:300px;}
#cke_1_contents{height: 400px;}
</style>
<nav class="subHeader">
	<div id="subHeader-content">
		<h3 id="subHeader-name">공지사항</h3>
	</div>
</nav>
<div id="jspcontent">
<form:form commandName="notice" method="post" enctype="multipart/form-data" id="noticeForm">
	<div>
		<input type="hidden" name="notiNum" value="${notice.notiNum}" />
		<input type="text" id="titleTag" name="notiTitle" placeholder="제목을 입력하세요" value="${notice.notiTitle}" />&nbsp;<span style="color: red;">*</span>
		<form:errors path="notiTitle" element="span" cssClass="error" /><br>
		<div id="fileBtnArea">
			<input type="button" value="추가" id="plus" class="ctlBtn btn btn-outline-primary btn-sm" />
			<input type="button" value="제거" id="minus" class="ctlBtn btn btn-outline-primary btn-sm" />
		</div>
		<div id="fileContainer">
			<input type="file" name="boFiles" multiple accept="image/*" />
		</div>
		<div id="conDiv">
			<textarea rows="5" cols="450" name="notiContent" id="notiContent" placeholder="내용을 입력하세요">
				${notice.notiContent }
			</textarea>
		</div>
		<form:errors path="notiContent" element="span" cssClass="error" />
		<c:url value="/board/noticeList.do" var="listURL" />
		<br><br>
		<input type="submit" value="저장" class="btn btn-outline-primary btn-sm"/>
		<input type="reset" value="취소" class="linkBtn btn btn-outline-primary btn-sm" data-gopage="${listURL }" />
	</div>
</form:form>
</div>
<script type="text/javascript">

	CKEDITOR.replace("notiContent",{
		filebrowserImageUploadUrl:"${cPath}/board/noticeAttatchUpload.do?type=Images"
	});
	let fileArea = $("#fileArea").on("click", ".ctlBtn", function(){
		let id = this.id;
		console.log(id);
		let divTag = $(this).closest("div");
		if(id == 'plus'){
			let clone = divTag.clone();
			$(clone).find(":input[name]").val("");
			divTag.after(clone);
		}else{
			let divs = fileArea.find("div");
			if(divs.length>1)
				divTag.remove();
		}
	});
// 	$("#cke_1_contents").removeAttr('style');
// 	$("#cke_1_contents").css("height","400px");
</script>