<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<style>
#searchUI{width: 390px; margin: 0px auto;}
#pagingArea{width: 200px; margin: 0px auto;}
</style>
<nav class="subHeader">
	<div id="subHeader-content">
		<h3 id="subHeader-name">공지사항</h3>
	</div>
</nav>
<div id="jspcontent">
<div id="noticeTb">
<table class="table table-hover">
	<thead>
		<tr>
			<th>no</th>
			<th>title</th>
			<th>writer</th>
			<th>date</th>
			<th>hit</th>
		</tr>
	</thead>
	<c:set var="noticelist" value="${pagingVO.dataList }" />
	<tbody>
	<c:choose>
			<c:when test="${not empty noticelist }">
				<c:forEach items="${noticelist }" var="notice">
					<tr>
						<td>${notice.rnum }</td>
						<td>
							<c:url value="/board/noticeView.do" var="viewURL">
								<c:param name="what" value="${notice.notiNum }" />
							</c:url>
							<a href="${viewURL }">${notice.notiTitle }</a>
							<c:forEach begin="1" end="${notice.attatchCount }">
								<img src="${pageContext.request.contextPath }/resources/images/attatchicon.png" style="width: 10px; height: 10px;"/>
							</c:forEach>
						</td>
						<td>${notice.notiWriter }</td>
						<td>${notice.notiDate }</td>
						<td>${notice.notiHits }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5">검색 조건에 맞는 게시글이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
</div>
 <hr style="border:1px color= silver;" width="100%"><br><br><br>
	<div id="pagingArea">
		${pagingVO.pagingHTML }
	</div>
	<div id="searchUI">
		<select name="searchType">
			<option value>전체</option>
			<option value="title">제목</option>
			<option value="content">내용</option>
		</select>
		<input type="text" name="searchWord" />
		<input type="button" value="검색" class="btn btn-outline-primary btn-sm" id="searchBtn"/>
		<security:authorize access="hasRole('ROLE_ADMIN')">
			<input type="button" value="새글쓰기" class="linkBtn btn btn-outline-primary btn-sm" data-gopage="${cPath }/board/noticeInsert.do"/>
		</security:authorize>
	</div>
</div>
<form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="page" />
</form>
<script src="${pageContext.request.contextPath}/resources/js/board/paging.js"></script>
<script type="text/javascript">	
	$("[name='searchWord']").val("${searchVO.searchWord}");
	$("[name='searchType']").val("${searchVO.searchType}")
	let searchForm = $("#searchForm").paging();
</script>
