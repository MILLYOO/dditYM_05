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
				<th>회원아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>이메일</th>
				<th>주소(상위주소만)</th>
				<th>마일리지</th>
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
							<option value="name">이름</option>
							<option value="address">지역</option>
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
			let member = $(this).data("member");
			if(!member) return false; // 회원이 없으면 리턴
			let memId = member.memId
			location.href="${pageContext.request.contextPath }/member/memberView.do?who="+memId;
		});
		let pagingArea = $("#pagingArea");
		let searchForm = $("#searchForm").paging()
						.ajaxForm({
			dataType:"json",
			success:function(resp){
				console.log(resp);
				listBody.empty();
				pagingArea.empty();
				searchForm.find("[name='page']").val("");
				let memberList = resp.dataList;
				let pagingHTML = resp.pagingHTML;
				let trTags = [];
				if(memberList){
					$.each(memberList, function(idx, member){
						let trTag = $("<tr>").append(
							$("<td>").text(member.memId),
							$("<td>").text(member.memName),
							$("<td>").text(member.memHP),
							$("<td>").text(member.memAdd1),
							$("<td>").text(member.memMail),
							$("<td>").text(member.memMileage)
						).data("member", member)
						.css("cursor","pointer");
						console.log(trTag);
						trTags.push(trTag);
					});
				}else{
					let trTag = $("<tr>").html(
						$("<td>").attr({
							colspan : "6"
						}).text("조건에 맞는 회원이 없음.")		
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
// 		let searchBtn = $("#searchBtn").on("click", function(){
// 			searchUI.find(":input[name]").each(function(idx, input){
// 				let value = $(input).val();
// 				let formInput = searchForm.find("[name='"+input.name+"']");
// 				$(formInput).val(value);
// 			});
// 			searchForm.submit();
// 		});
		
// 		let pagingArea = $("#pagingArea").on('click', "a" ,function(){
// 			event.preventDefault();
// 			let page = $(this).data("page");
// 			if(!page) return false;
// 			searchForm.find("[name='page']").val(page);
// 			searchForm.submit();
// 		});
	</script>
</body>
</html>