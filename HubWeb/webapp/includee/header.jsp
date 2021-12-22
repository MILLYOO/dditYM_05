<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<security:authorize access="isAuthenticated()">
     <security:authentication property="principal.authMember" var="authMember"/>
</security:authorize>
<!-- 검색 자동완성 구현 -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="${cPath}/resources/js/common/searchData.js"></script>
<script>
	$(function () {	//화면 로딩후 시작
		$("#searchInput").autocomplete({  //오토 컴플릿트 시작
			source: List,	// source는 data.js파일 내부의 List 배열
			focus : function(event, ui) { // 방향키로 자동완성단어 선택 가능하게 만들어줌	
				return false;
			},
			minLength: 1,// 최소 글자수
			delay: 100,	//autocomplete 딜레이 시간(ms)
		});
	});
</script>    

		<!-- 사이드메뉴바 제외한 메인화면 -->
<nav class="navbar navbar-expand navbar-light bg-white topbar static-top shadow">

    <!-- Sidebar Toggle (Topbar) -->
	<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
	    <i class="fa fa-bars"></i>
	</button>
	<!-- Topbar Search -->
	<form:form id="mainSearchForm" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
		action="${cPath}/common/mainSearch.do">
		<div class="input-group">
		    <input type="text" name="mainSearchStr" class="form-control bg-light border-0 small" id="searchInput" 
		    	placeholder="메뉴명을 입력해주세요" aria-label="Search" aria-describedby="basic-addon2"/>
		    <div class="input-group-append">
		        <button class="btn btn-primary" type="button" onclick="$('#mainSearchForm').submit()">
		            <i class="fas fa-search fa-sm"></i>
		        </button>
		    </div>
		</div>
	</form:form>

	<!-- Topbar Navbar -->
	<ul class="navbar-nav ml-auto">
		<a class="collapse-item" href="${cPath}/board/noticeList.do"><i class="far fa-clipboard fa-3x"></i></a>
		<div class="topbar-divider d-none d-sm-block nav-link"></div>
	
		<!-- Nav Item - User Information -->
		<div style= "display:flex; align-items: center; margin-top: -6%;" >
			<img class="img-profile rounded-circle" src="${cPath}/resources/images/undraw_profile.svg">
		</div>
		<div id="main-memInfo">
		    ${authMember.memId }
		        <span class="mr-2 d-none d-lg-inline text-gray-600 small"></span>
			<a href="${pageContext.request.contextPath }/login/logout.do" onclick="return clickHandler(event);">로그아웃</a>
		</div>	
		<form name="logoutForm" method="post">
			<input type="hidden" name="_csrftoken" value="token_value" />
		</form>
    </ul>
</nav>

