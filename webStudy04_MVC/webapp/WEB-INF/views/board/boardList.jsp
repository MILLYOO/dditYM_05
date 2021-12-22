<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>댓글수</th>
				<th>파일수</th>
			</tr>
		</thead>
		<tbody id ="listBody">
		
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6">
					<div id = "pagingArea">
					
					</div>
					<form id="searchUI">
						<select name="searchType">
							<option value>전체</option>
							<option value="title">제목</option>
							<option value="writer">작성자</option>
						</select>				
						<input type="text" name="searchWord"/>	
						<input type="button" value="검색" id="searchBtn"/>
					</form>
				</td>
			</tr>
		</tfoot>
	</table>
	<form id="searchForm">
		<input type="text" name="searchType" />
		<input type="text" name="searchWord" />
		<input type="text" name="page" /> 
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
	<script type="text/javascript">
// 		let searchUI = $("#searchUI");
		let listBody = $("#listBody").on("click", "tr", function(){ // 한 레코드를 클릭 할 경우
			let board = $(this).data("board");
			if(!board) return false; // 회원이 없으면 리턴
			let boNo = board.boNo
			location.href="${pageContext.request.contextPath }/board/boardView.do?what="+boNo;
		});
		let pagingArea = $("#pagingArea");
		let searchForm = $("#searchForm").paging({
			searchUI : "#searchUI",
			searchBtn : "#searchBtn",
			pagingArea : "#pagingArea",
			pageKey  : "page",
			pageTag : "[name='page']"	
		}).ajaxForm({
			dataType:"json",
			success:function(resp){
				listBody.empty();
				pagingArea.empty();
				searchForm.find("[name='page']").val("");
				let boardList = resp.dataList;
				let pagingHTML = resp.pagingHTML;
				let trTags = [];
				if(boardList){
					$.each(boardList, function(idx, board){
						let trTag = $("<tr>").append(
							$("<td>").text(board.boNo),
							$("<td>").html(board.boTitle),
							$("<td>").text(board.boWriter),
							$("<td>").text(board.boDate),
							$("<td>").text(board.boHit),
							$("<td>").text(board.repCount),
							$("<td>").text(board.attCount)
						).data("board", board)
						.css("cursor","pointer");
						trTags.push(trTag);
					});
				}else{
					let trTag = $("<tr>").html(
						$("<td>").attr({
							colspan : "6"
						}).text("조건에 맞는 게시글 없음.")		
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
</html>