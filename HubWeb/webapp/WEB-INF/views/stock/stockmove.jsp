<!-- 재고이동 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- 
	stockmove.jsp
	By 이원균_211207(최종수정)
	재고이동메뉴 jsp 입니다.
	1. 재고이동 jsp Tag
	2. 로딩 Script
-->

<!-- ======================================================================================== -->
<!-- 1. 재고이동 jsp Tag-->
<!-- 서브헤더 -->
<nav class="subHeader">
<div id="subHeader-content">
<h3 id="subHeader-name">재고이동</h3>
  	<div class="main-icon">
	  <!-- pdf, excel -->
		<i class="far fa-file-pdf fa-2x"></i>
	    <i class="fas fa-file-import fa-2x"></i>
	    <i class="fas fa-file-export fa-2x"></i>
	</div>
</div>
</nav>

<div id="jspcontent">
	<!--   	검색 -->
	<form:form id="searchForm">
	<fieldset>
	<div>
		<div class="searchDate"> 
			<input type="Date" name="dateStart" id="dateStart" /> 
			<input type="Date" name="dateEnd" id="dateEnd" />
		</div>	 
  		  <!-- 상세검색 내리는 버튼. 각자 js에 카톡에 보낸 스크립트 추가해야됨 -->	
		  <a href="javascript:doDisplay(); javascript:doHide();" class="click"><i class="fas fa-chevron-down fa-border  fa-fw"></i></a>
		  <!-- 상세검색(밑으로 내려오는 검색조건들) 창 -->
		  <input type="button" id="select1" value="조회" class="select btn btn-outline-primary btn-sm"/> 
	  </div>
		<div id="myDIV" style="display: none">
			<div class="detail-div">
			  입고 창고 <input type="search" name="inwarName" onclick="warCode(this)" />
			  출고 창고 <input type="search" name="outwarName" onclick="warCode(this)" />
			   부서 <input type="search" name="deptName" onclick="deptCode(this)" />
			  사원 <input type="search" name="empName" onclick="empCode(this)" />
			  <input type="button" id="select2" class="select btn btn-outline-primary btn-sm" value="조회"/>
		</div>
	</div>
  </fieldset>
  </form:form>
	
	<!-- masterGrid 버튼 -->
	<form:form class="basic_crud">
		<input class="btn btn-outline-primary btn-sm" type="button" id="append" value="추가" />
		<input class="btn btn-outline-primary btn-sm" type="button" id="saved" value="저장" /> 
		<input class="btn btn-outline-primary btn-sm" type="button" id="delete" value="삭제" />
	</form:form>
	<div id="realgrid_master"></div>	
	
	<!-- detailGrid 버튼 -->
	<form:form class="basic_crud">
		<input class="btn btn-outline-primary btn-sm" type="button" id="append1" value="추가" />
		<input class="btn btn-outline-primary btn-sm" type="button" id="saved1" value="저장" /> 
		<input class="btn btn-outline-primary btn-sm" type="button" id="delete1" value="삭제" />
	</form:form>
	<div id="realgrid_detail"></div>  
</div>


<!-- ======================================================================================== -->
<!-- 2. 로딩 Script-->
<script src="${cPath}/resources/js/stock/stockmove.js"></script>
<script src="${cPath}/resources/js/custom/hubweb_managecommon.js"></script>

