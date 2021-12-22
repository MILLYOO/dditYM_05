<!-- 재고평가 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
#stockValInsertForm,#insertStockValForm,#delete{display: inline-block;}
#realgrid_master {
  width: 100%;
  height: 750px;
  margin-bottom: 36px;
}
.modal, .modal-window2 {
   background: rgba(174, 182, 204);
/*    box-shadow: 0 8px 32px 0 rgb(31 38 135/ 37%); */
   backdrop-filter: blur(13.5 px);
   -webkit-backdrop-filter: blur(13.5px);
   border-radius: 10 px;
   border: 1 px solid rgba(255, 255, 255, 0.18);
   width: 21%;
   position: relative;
   top: -100 px;
   padding: 10 px;
   color: #5C5F68;
   overflow: auto;
   height : 300px;
}
</style>

<!-- 서브헤더 -->
<nav class="subHeader">
<div id="subHeader-content">
<h3 id="subHeader-name">재고평가</h3>
  	<div class="main-icon">
	  <!-- pdf, excel -->
		<i class="far fa-file-pdf fa-2x"></i>
	    <i class="fas fa-file-import fa-2x"></i>
	    <i class="fas fa-file-export fa-2x"></i>
	</div>
	<input class="btn btn-primary btn-sm" type="button" id="insertStockVal" value="재고평가"/>
</div>
</nav>
<div id="jspcontent">

	<!-- 검색 -->
	<form:form id="searchForm">
	<fieldset>
		<div style="padding-bottom: 20px;">
			<div class="searchDate"> 
				<input type="date" id="dateStart" name="dateStart" />
				<input type="date" id="dateEnd" name="dateEnd" />
			</div>
			<input class="btn btn-outline-primary btn-sm" type="button" id="select" value="조회" style="margin-left: 10px"/>
			<form:form class="basic_crud">
				<input class="btn btn-outline-primary btn-sm" type="button" id="delete" value="삭제"/>
		  	</form:form>
		  	<input type="hidden" id="stvNum">
		</div>
	</fieldset>
	</form:form>
	
	<!-- master -->
	<div id="realgrid_master"></div>
</div>

<!-- 재고평가 진행 모달창(insert) -->
<div id="stockValInsert-modal" class="modal-overlay modal">
	<div class="modal-window2">
		<div class="title">재고평가</div>
		<div class="close-area">X</div>
		<div class="content">
			<form:form id="stockValInsertForm">
			  	<input type="Date" name="stvStart" id="insertDateStart" style="float:left; margin-right: 20px"/>
		 		<input type="Date" name="stvEnd" id="insertDateEnd" />
		 	 	<div style="margin: 5% 3% 5% 21%;">
			 	 	평가 부서<input type="search" name="deptName" onclick="deptCode(this)" style="display:block; margin-bottom:5px " />
			 	 	평가 사원<input type="search" name="empName" onclick="empCode(this)" style="display:block;"/>
		 	 	</div>
		 	 	<input type="hidden" name="stvCheck" value="Y" />
		  	</form:form>
		  	<input class="btn btn-dark btn-sm" type="button" id="insertStockValForm" value="확인" style="display:block; margin: 0px 152px;"/>
		</div>
	</div>
</div>	

<!-- 재고평가 리스트 모달창(select) -->
<div id="stockValuation-modal" class="modal-overlay modal">
	<div class="modal-window">
		<div class="title">재고평가 조회</div>
		<div class="close-area">X</div>
		<div class="content">
			<form:form id="searchFormModal">
			재고평가기간<input type="Date" name="dateStart" id="dateStartValuModal"/>
			 ~	<input type="Date" name="dateEnd" id="dateEndValuModal"/>
			<input class="btn btn-dark btn-sm" type="button" id="searchValuModal" value="검색"/>
		  	</form:form>
		</div>
		<div class="content">
			<div id="realgrid_stockVal" class="hubWeb_standard"></div>
		</div>
	</div>
</div>

<script src="${cPath }/resources/js/stock/stockvaluation.js"></script>
<script src="${cPath }/resources/js/custom/hubweb_statuscommon.js"></script>
<script src="${cPath}/resources/js/stock/stockValuationList.js"></script>
<script>
	masterGrid.header.height = 80;
</script>

