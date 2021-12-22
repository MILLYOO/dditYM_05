<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<link rel="stylesheet" href="${cPath}/resources/css/hubweb_realgrid.css">
<link rel="stylesheet" href="${cPath}/resources/css/produce.css">

<nav class="subHeader">
	<div id="subHeader-content">
		<h3 id="subHeader-name">생산지시서</h3>
	  	<div class="main-icon">
			<i class="far fa-file-pdf fa-2x"></i>
		    <i class="fas fa-file-import fa-2x"></i>
		    <i class="fas fa-file-export fa-2x"></i>
		</div>
	  	<input class="btn btn-primary btn-sm piExtra" type="button" id="orderBookApply" value="수주서적용">
	  	<input class="btn btn-primary btn-sm piExtra" type="button" id="bomApply" value="BOM전개">
	  	<input class="btn btn-primary btn-sm piExtra" type="button" id="selectStockStatus" value="현재고">
	</div>
</nav>

<div id="jspcontent">
	<form:form id="searchForm" action="${cPath }/produce/productionInstructionList.do" method="post">
		<fieldset>
		<div>
			<div class="searchDate"> 
			  	<input type="date" name="dateStart" id="dateStart" />
			  	<input type="date" name="dateEnd" id="dateEnd" />
			  	<a href="javascript:doDisplay(); javascript:doHide();" class="click"><i class="fas fa-chevron-down fa-border  fa-fw"></i></a>&nbsp;&nbsp;&nbsp;
			  	<input class="select btn btn-outline-primary btn-sm" type="button" id="select1" value="조회"/>
			</div>
		  	<div id="myDIV" class="mydiv-wrap" style="display: none">
			  	<div class="div-wrap detail-div">
					<label>부서</label><input type="search" name="deptName" id="deptName" />
					<label id="empLable">사원</label><input type="search" name="empName" id="empName" />
<!-- 		  		  	<label>마감여부</label>					 -->
<!-- 				  	<select name="instFinish"> -->
<!-- 						<option value>전체</option> -->
<!-- 						<option value="Y">Y</option> -->
<!-- 						<option value="N">N</option> -->
<!-- 					</select> -->
					<input type="button" id="select3" class="select btn btn-outline-primary btn-sm" value="조회"/>
			  	</div>
			</div>
		</div><br><br>
		</fieldset>
	</form:form>
	<div class="basic_crud">
	  	<input class="btn btn-outline-primary btn-sm" type="button" id="append" value="추가"/>
	  	<input class="btn btn-outline-primary btn-sm" type="button" id="saved" value="저장"/>
	  	<input class="btn btn-outline-primary btn-sm" type="button" id="delete" value="삭제">
	  	<span class="rightAlign">단위 : (개)</span>
	</div>
    <div id="realgrid_master"></div>
	<div class="basic_crud">
	  	<input class="btn btn-outline-primary btn-sm" type="button" id="append1" value="추가"/>
	  	<input class="btn btn-outline-primary btn-sm" type="button" id="saved1" value="저장"/>
	  	<input class="btn btn-outline-primary btn-sm" type="button" id="delete1" value="삭제"/>
	  	<span class="rightAlign">단위 : (개)</span>
	</div>
    <div id="realgrid_detail"></div>
</div>

<!-- 수주서 적용 -->
<div id="orderbookApplyForPI-modal" class="modal-overlay modal">
	<div class="modal-apply">
		<div class="title">수주서 적용</div>
		<div class="close-area">X</div>
		<div class="content">
			<form:form id="obApplySearchForm" action="${cPath }/produce/piOrderBookApply.do" method="post">
			  	<input type="date" name="obStartDate" id="obStartDate" />
		 	 	<input type="date" name="obEndDate" id="obEndDate" />
		 	 	<input type="text" name="buyerName" id="buyerName" class="searchTag" placeholder="거래처명" />
		 	 	<input type="text" name="orbNum" id="orbNum" class="searchTag" placeholder="수주서번호" />
		 	 	<input class="btn btn-secondary btn-sm" type="button" id="selectOb" value="조회"/>
			</form:form>
		  	<form:form id="obApplyForPIForm">
			  	<input class="btn btn-dark btn-sm" type="button" id="addObApply" value="적용"/>
		  	</form:form>
			<div id="realgrid_obApplyForPIMaster" class="hubWeb_standard"></div>
			<span class="rightAlign">단위 : (원)</span>
			<div id="realgrid_obookApplyForPIDetail" class="hubWeb_standard"></div>
		</div>
	</div>
</div>

<!-- 현재고 -->
<div id="stockForPI-modal" class="modal-overlay modal">
	<div class="modal-apply">
		<div class="title">현재고</div>
		<div class="close-area">X</div>
		<div class="content">
			<form:form id="stockSelectForm" action="${cPath }/produce/selectStockStatusListForPi.do" method="post">
		 	 	<input type="text" name="rawsCode" id="rawsCode" placeholder="품목코드" />
		 	 	<input type="text" name="rawsName" id="rawsNameByStock" placeholder="품목명" />
		 	 	<input class="btn btn-secondary btn-sm" type="button" id="selectSt" value="조회"/>
			</form:form>
			<span class="rightAlign">단위 : (개)</span>
			<div id="realgrid_stockSelectForPI" class="hubWeb_standard"></div>
		</div>
	</div>
</div>

<script src="${cPath}/resources/js/produce/stockSelectForPi.js"></script>
<script src="${cPath}/resources/js/produce/obApplyForPi.js"></script>
<script src="${cPath}/resources/js/produce/productioninstruction.js"></script>
<script src="${cPath}/resources/js/custom/hubweb_managecommon.js"></script>