<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" href="${cPath}/resources/css/hubweb_realgrid.css">

<!-- 서브헤더 -->
<nav class="subHeader">
<div id="subHeader-content">
<h3 id="subHeader-name">매입마감서</h3>
  	<div class="main-icon">
	  <!-- pdf, excel -->
		<i class="far fa-file-pdf fa-2x"></i>
	    <i class="fas fa-file-import fa-2x"></i>
	    <i class="fas fa-file-export fa-2x"></i>
	</div>
	<input class="btn btn-primary btn-sm" type="button" id="IncApplyForPclose" value="입고처리서적용" >
</div>
</nav>

<div id="jspcontent">
<!-- 모든 검색조건(상세검색)이 포함되는 form -->
  	<form:form id="searchForm" action="${cPath}/buy/purchCloseList.do">
  	<fieldset>
  	<div class="hubweb_searchMaster" >
		<div class="searchDate"> 
			<input type="date" id="dateStart" name="dateStart" />
			<input type="date" id="dateEnd" name="dateEnd" />
		</div>
	<!-- 상세검색 내리는 버튼.-->	
		<a href="javascript:doDisplay(); javascript:doHide();" class="click" style="float: left; text-align: center; display: inline-block;"><i class="fas fa-chevron-down fa-border fa-fw" style="margin-top:3px;"></i></a><br/><br/>
		<input type="button" id="select1" class="select btn btn-outline-primary btn-sm" value="조회" style="float: left;height: 32px;"/>
	</div>
	<!-- 상세검색(밑으로 내려오는 검색조건들) 창 -->
		<div id="myDIV" style="display: none">
			<div class="detail-div">
				 품목<input type="search" name="prodName" id="prodName" onclick="itemCode(this)"/><!-- type text가 아니라 search인 이유는 옆에 x 넣으려고 -->
				  사원<input type="search" name="empName" id="empName" onclick="empCode(this)"/>
				  창고<input type="search" name="warName" id="warName" onclick="warCode(this)"/>
				  부서<input type="search" name="deptName" id="deptName" onclick="deptCode(this)"/>
				  거래처<input type="search" name="buyerName" id="buyerName" onclick="buyerCode(this)"/>
				<input type="button" id="select2" class="select btn btn-outline-primary btn-sm" value="조회"/>
			</div>	
		</div>
	</fieldset>
	</form:form>
	
<!-- master -->
    <form:form class="basic_crud">
	  	<input type="button" id="saved" class="btn btn-outline-primary btn-sm" value="저장"/>
	  	<input type="button" id="delete" class="btn btn-outline-primary btn-sm" value="삭제"/>
  	</form:form>
    <div id="realgrid_master"></div>
    
<!--  detail -->
    <form:form class="basic_crud">
	  	<input type="button" id="saved1" class="btn btn-outline-primary btn-sm" value="저장"/>
	  	<input type="button" id="delete1" class="btn btn-outline-primary btn-sm" value="삭제"/>
  	    <span class="rightAlign">단위 : (원)</span>
  	</form:form>
    <div id="realgrid_detail"></div>
    
    <!-- 입고처리서 적용 모달창-->
<div id="IncApplyForPclose-modal" class="modal-overlay modal">
	<div class="modal-applyLg">
		<div class="title">입고처리서 적용</div>
		<div class="close-area">X</div>
		<div class="content">
		<form:form id="incApplySearchForm" action="${cPath}/buy/pCIncomingApply.do" method="post"> 
			<input type="date" name="ipStartDate" id="ipStartDate" />
		 	<input type="date" name="ipEndDate" id="ipEndDate" />
		 	<input type="search" name="buyerName" id="buyerName" placeholder="거래처명" onclick="buyerCode(this)"/>
		 	<input type="text" name="incNum" id="incNum" placeholder="입고번호" />
		 	<input class="btn btn-secondary btn-sm" type="button" id="selectip" value="조회"/>
		</form:form>
		<form:form id="IncApplyForPcloseForm">
		 	<input class="btn btn-dark btn-sm" type="button" id="applyip" value="적용"/>
		</form:form> 	
		  <div id="realgrid_incApplyForPcloseMaster" class="hubWeb_standard"></div>
		   <span class="rightAlign">단위 : (원)</span>
		  <div id="realgrid_incApplyForPcloseDetail" class="hubWeb_standard"></div>
		</div>
	</div>
</div>
    
</div> 
 <script src="${cPath}/resources/js/buy/IncForPclose.js"></script>
 <script src="${cPath}/resources/js/buy/purchclose.js"></script>
 <script src="${cPath}/resources/js/custom/hubweb_managecommon.js"></script>