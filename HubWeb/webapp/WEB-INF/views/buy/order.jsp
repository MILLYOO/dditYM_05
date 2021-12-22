<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- 서브헤더 -->
<nav class="subHeader">
<div id="subHeader-content">
<h3 id="subHeader-name">발주서</h3>
  	<div class="main-icon">
	  <!-- pdf, excel -->
		<i class="far fa-file-pdf fa-2x"></i>
	    <i class="fas fa-file-import fa-2x"></i>
	    <i class="fas fa-file-export fa-2x"></i>
	</div>
</div>
</nav>

<div id="jspcontent">
<!-- 모든 검색조건(상세검색)이 포함되는 form -->
	<form:form id="searchForm" action="${cPath}/buy/orderList.do">
	<fieldset>
	<div class="hubweb_searchMaster" >
		<div class="searchDate"> 
			<input type="date" id="dateStart" name="dateStart" />
			<input type="date" id="dateEnd" name="dateEnd" />
		</div> 
		<!-- 상세검색 내리는 버튼. 각자 js에 카톡에 보낸 스크립트 추가해야됨 -->	
			<a href="javascript:doDisplay(); javascript:doHide();" class="click" style="float: left; text-align: center; display: inline-block;"><i class="fas fa-chevron-down fa-border fa-fw" style="margin-top:3px;"></i></a><br/><br/>
			<input type="button" id="select1" class="select btn btn-outline-primary btn-sm" value="조회" style="float: left;height: 32px;"/>
		<!-- 메뉴 이동 순서 파란글씨로 나타낸 부분 : 이 부분은 해당하는 사람만 쓰세요 -->
		<div class="chained" style="margin-left:auto;">
			<span>참조메뉴 : </span>
			<span style="cursor: pointer; text-decoration: underline;" onclick="location.href='${cPath}/buy/orderList.do'">발주서</span>
			<span> -> </span>
			<span style="cursor: pointer; text-decoration: underline;" onclick="location.href='${cPath}/buy/incomingList.do'">입고처리</span>
			<span> -> </span>
			<span style="cursor: pointer; text-decoration: underline;" onclick="location.href='${cPath}/buy/purchCloseList.do'">매입마감</span>
		</div>
	</div>
		<!-- 상세검색(밑으로 내려오는 검색조건들) 창 -->
		<div id="myDIV" style="display: none">
			<div class="detail-div">
				 품목<input type="search" name="prodName" id="prodName" onclick="itemCode(this)"/><!-- type text가 아니라 search인 이유는 옆에 x 넣으려고 -->
				  사원<input type="search" name="empName" id="empName" onclick="empCode(this)"/>
				  부서<input type="search" name="deptName" id="deptName" onclick="deptCode(this)"/>
				  거래처<input type="search" name="buyerName" id="buyerName" onclick="buyerCode(this)"/>
				<input type="button" id="select2" class="select btn btn-outline-primary btn-sm" value="조회"/>
			</div>	
		</div>
	</fieldset>
	</form:form>
	
<!-- 	master -->
    <form:form class="basic_crud">
	  	<input type="button" id="saved" class="btn btn-outline-primary btn-sm" value="저장"/>
	  	<input type="button" id="append" class="btn btn-outline-primary btn-sm" value="추가"/>
	  	<input type="button" id="delete" class="btn btn-outline-primary btn-sm" value="삭제"/>
  	</form:form>
    <div id="realgrid_master"></div>
    
<!--    detail -->
    <form:form class="basic_crud">
	  	<input type="button" id="saved1" class="btn btn-outline-primary btn-sm" value="저장"/>
	  	<input type="button" id="append1" class="btn btn-outline-primary btn-sm" value="추가"/>
	  	<input type="button" id="delete1" class="btn btn-outline-primary btn-sm" value="삭제"/>
  	  <span class="rightAlign">단위 : (원)</span>
  	</form:form>
    <div id="realgrid_detail"></div>
</div> 
 <script src="${cPath}/resources/js/buy/purchaseorder.js"></script>
 <script src="${cPath}/resources/js/custom/hubweb_managecommon.js"></script>