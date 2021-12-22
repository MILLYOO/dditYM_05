<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<table>
		<tr>
			<th>글번호</th>
			<td>${board.boNo}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.boTitle}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.boWriter}</td>
		</tr>
		<tr>
			<th>아이피</th>
			<td>${board.boIp}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${board.boMail}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.boDate}</td>
		</tr>
		<tr>
			<th>신고수</th>
			<td>
				<span>${board.boRep}</span>
				<input type="button" value="신고" id="repBtn" data-url="/board/report.do"/>
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.boHit}</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td>
			<span>${board.boRec}</span>
			<input type="button" value="추천" id="recBtn"  data-url="/board/recommend.do"/>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach items="${board.attatchList }" var="attatch">
					<c:url value="/board/download.do" var="downloadURL">
						<c:param name="what" value="${attatch.attNo }"></c:param>
					</c:url>
				<a href="${downloadURL }">
					${attatch.attFilename }
					<img src="${pPath}/resources/images/attatchment.png" style="width:20px; height:20px;">
				</a>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.boContent}</td>
		</tr>
		<tr>
			<td colspan=2>
				<c:url value="/board/boardInsert.do" var="insertURL">
					<c:param name="boParent" value="${board.boNo}"/>
				</c:url>
				<c:url value="/board/boardUpdate.do" var="updateURL">
					<c:param name="what" value="${board.boNo}"/>
				</c:url>
				<input type="button" value="답글쓰기" class="linkBtn" data-gopage="${insertURL}"/>
				<input type="button" value="수정하기" class="linkBtn" data-gopage="${updateURL}"/>
				<input type="button" value="글삭제" id="delBtn" />
			</td>
		</tr>
	</table>
	<form id="deleteForm" action="${cPath }/board/boardDelete.do" method="post">
		<input type="hidden" name ="boNo" value="${board.boNo }"/>
		<input type="hidden" name ="boPass"/>
	</form>
	<form id="replyInsertForm" action="${cPath }/reply/replyInsert.do" method="post">
		<input type="hidden" name ="boNo" value="${board.boNo }"/>
		<input type="text" name="repWriter" placeholder="작성자" required>
		<input type="text" name="repWriter" placeholder="비밀번호" required>
		<input type="text" name="repWriter" placeholder="이메일" required>
		<textarea rows="3" cols="90" name="repContent" placeholder="내용"></textarea>
		<input type="submit" value="저장">
	</form>
	<table>
		<thead>
			<tr>
				<th>일련번호</th>
				<th>덧글내용</th>
				<th>작성자</th>
				<th>이메일</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody id="listBody">
		
		</tbody>
		<tfoot>
		<tr>
			<td id="pagingArea"></td>
		</tr>
		</tfoot>
	</table>
		<form id="searchForm">
		<input type="text" name="page" /> 
		<input type="text" name="boNo" value="${board.boNo }"/>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
	<script type="text/javascript">
		let deleteForm = $("#deleteForm");
		let boPassTag = deleteForm.find("[name='boPass']");
		$("#delBtn").on("click", function(){
			let password = prompt("비밀번호");
			if(password){
				boPassTag.val(password);
				deleteForm.submit();
			}
		});
	
		$("#repBtn,#recBtn").on("click", function(){
			let clickBtn = $(this);
			let url = clickBtn.data("url");
			$.ajax({
				url : "${cPath}"+url,
				data : {
					what:${board.boNo}
				},
				dataType : "text",
				success : function(resp) {
					if(resp=="OK"){
						let span = clickBtn.siblings("span:first");
						$(span).text( parseInt($(span).text())+1 );
					}
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			});
		});
		let listBody = $("#listBody");
		let pagingArea = $("#pagingArea");
		let searchForm = $("#searchForm").paging().ajaxForm({
			dataType:"json",
			url:"${cPath}/reply/replyList.do?what=${board.boNo}",
			success:function(resp){
				listBody.empty();
				pagingArea.empty();
				searchForm.find("[name='page']").val("");
				let replyList = resp.dataList;
				let pagingHTML = resp.pagingHTML;
				let trTags = [];
				if(replyList){
					$.each(replyList, function(idx, reply){
						console.log(reply)
						let trTag = $("<tr>").append(
							$("<td>").text(reply.repNo),
							$("<td>").text(reply.repContent),
							$("<td>").text(reply.repWriter),
							$("<td>").text(reply.repMail),
							$("<td>").text(reply.repDate)
						).data("reply", reply);
						trTags.push(trTag);
					});
				}else{
					let trTag = $("<tr>").html(
						$("<td>").attr({
							colspan : "6"
						}).text("조건에 맞는 댓글 없음.")		
					);
					trTags.push(trTag);
				}
				listBody.append(trTags);
				pagingArea.html(pagingHTML);
			},
			error:function(xhr, jQuery, error){
				console.log(jQuery);
				console.log(error);
			}
		}).submit();
	</script>
</body>
<jsp:include page="/includee/postScript.jsp"></jsp:include>
</html>



















