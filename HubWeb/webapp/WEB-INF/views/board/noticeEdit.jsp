<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="${cPath }/resources/js/ckeditor/ckeditor.js"></script>
<style>
h3{margin-bottom: 50px;}
input{margin: 15px 5px;}
#titleTag{width:800px;}
#conDiv{width:810px;}
#boFiles{width : 680px;}
#editForm, h3{margin-left:300px;}
</style>
<nav class="subHeader">
	<div id="subHeader-content">
		<h3 id="subHeader-name">공지사항</h3>
	</div>
</nav>
<div id="jspcontent">
<form:form modelAttribute="notice" method="post" enctype="multipart/form-data" id="editForm">
	<input type="hidden" name="notiNum" value="${notice.notiNum}" />
	<c:if test="${not empty notice.notiNum}">
<%-- 		<p>NO ${notice.notiNum}</p> --%>
		<input type="hidden" name="notiNum" value="${notice.notiNum}" />
		<form:errors path="notiNum" element="span" cssClass="error" />
	</c:if>
	<input type="hidden" name="notiWriter" id="notiWriter" value="${notice.notiWriter}" />
	<input type="text" name="notiTitle" id="titleTag" value="${notice.notiTitle}" />&nbsp;<span style="color: red;">*</span>
	<form:errors path="notiTitle" element="span" cssClass="error" /><br>
	<c:forEach items="${notice.attatchList }" var="attatch">
		<span data-atch="${attatch.attNo }"> ${attatch.attFilename }
			&nbsp; <input type="button" value="삭제" class="atchDelBtn btn btn-outline-primary btn-sm" />
		</span>
	</c:forEach>
	<div id="fileArea">
		<input type="file" name="boFiles" id="boFiles" multiple accept="image/*" />
		<input type="button" value="추가" id="plus" class="ctlBtn btn btn-outline-primary btn-sm" />
		<input type="button" value="제거" id="minus" class="ctlBtn btn btn-outline-primary btn-sm" />
	</div>
	<div id="conDiv">
		<textarea rows="5" cols="50" name="notiContent" id="notiContent">
			${notice.notiContent }
		</textarea>
	</div>
	<form:errors path="notiContent" element="span" cssClass="error" />
	<c:url value="/board/noticeView.do" var="viewURL">
		<c:param name="what" value="${notice.notiNum }" />
	</c:url>
	<br><br>
	<input type="submit" value="저장" class="btn btn-outline-primary btn-sm"/>
	<input type="reset" class="linkBtn btn btn-outline-primary btn-sm" data-gopage="${viewURL }" value="취소" />
</form:form>
</div>
<script type="text/javascript">
	CKEDITOR.replace("notiContent", {
		filebrowserImageUploadUrl : "${cPath}/board/noticeAttatchUpload.do?type=Images"
	});
	let editForm = $("#editForm");
	$(".atchDelBtn").on("click", function() {
		let span = $(this).closest("span");
		span.hide();
		let attNo = span.data("atch");
		editForm.append($("<input>").attr({
			type : "hidden",
			name : "delAttNos"
		}).val(attNo));
	});

	let fileArea = $("#fileArea").on("click", ".ctlBtn", function() {
		let id = this.id;
		console.log(id);
		let divTag = $(this).closest("div");
		if (id == 'plus') {
			let clone = divTag.clone();
			$(clone).find(":input[name]").val("");
			divTag.after(clone);
		} else {
			let divs = fileArea.find("div");
			if (divs.length > 1)
				divTag.remove();
		}
	});
</script>