<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<nav class="subHeader">
	<div id="subHeader-content">
		<h3 id="subHeader-name">공지사항</h3>
	</div>
</nav>
<div id="jspcontent">
	<table class="table table-hover">
		<tr>
			<th>글번호</th>
			<td>${notice.notiNum}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${notice.notiTitle}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${notice.notiWriter}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${notice.notiDate}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${notice.notiHits}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:if test="${not empty notice.attatchList }">
					<c:forEach items="${notice.attatchList }" var="atch">
						<c:url value="/board/noticeAttatchDownload.do" var="downloadURL">
							<c:param name="what" value="${atch.attNo }" />
						</c:url>
						<a href="${downloadURL }">
						${atch.attFilename }
 						<img src="${cPath }/resources/images/attatchicon.png" style="width: 20px; height: 20px;"/>
						</a>		
					</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${notice.notiContent}</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:url value="/board/noticeUpdate.do" var="updateURL">
					<c:param name="what" value="${notice.notiNum }" />
				</c:url>
				<c:url value="/board/noticeList.do" var="listURL" />
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<input type="button" value="글삭제" id="delBtn" class="btn btn-outline-primary btn-sm" />
					<input type="button" value="글수정" class="linkBtn btn btn-outline-primary btn-sm" data-gopage="${updateURL }" />
				</security:authorize>
				<input type="button" value="글목록" class="linkBtn btn btn-outline-primary btn-sm" data-gopage="${listURL }" />
			</td>
		</tr>
	</table>
</div>	
<form id="deleteForm" action="${cPath }/board/noticeDelete.do" method="post">
	<input type="hidden" name="notiNum" value="${notice.notiNum }" />
</form>
<script>
let deleteForm = $("#deleteForm");
$("#delBtn").on("click",function(){
	Swal.fire({
		title: "정말로 삭제하시겠습니까?",
		text: "다시 한 번 확인해주세요",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "승인",
		cancelButtonText: "취소"
	}).then((result) => {
		if (result.isConfirmed) {
			Swal.fire(
					"삭제가 완료되었습니다.",
					"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
					"success"
			);
			deleteForm.submit();
		}
	});
});
</script>
