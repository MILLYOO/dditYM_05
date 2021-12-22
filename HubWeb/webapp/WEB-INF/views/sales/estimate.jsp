<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
.leftAlign{text-align: left;}
#myDIV {
    display=none: ;
    padding: 30px;
    border: 1px solid silver;
    margin: 40px 0 0 0;
    border-radius: 0 0 5px 5px;
}
</style>
<!-- 서브헤더 -->
<nav class="subHeader">
<div id="subHeader-content">
<h3 id="subHeader-name">견적서</h3>
  	<div class="main-icon">
	  <!-- pdf, excel -->
	  	<i class="far fa-file-pdf fa-2x" onclick="pdfIcon()"></i>
	    <i class="fas fa-file-import fa-2x"></i>
	    <i class="fas fa-file-export fa-2x"></i>
	</div>
</div>
</nav>




 <div id="jspcontent">
 	<!-- 모든 검색조건이 포함되는 form -->
	<form:form id="searchForm" action="${cPath}/sales/EstimateRetrieve.do"> 
	 <fieldset>
	  <div style="float: left;">
		<div class="searchDate"> 
			<input type="date" id="dateStart" name="dateStart" />
			<input type="date" id="dateEnd" name="dateEnd" />
		</div>
	  	<!-- 상세검색 내리는 버튼. 각자 js에 카톡에 보낸 스크립트 추가해야됨 -->	
			<a href="javascript:doDisplay(); javascript:doHide();" class="click" style="float: left; text-align: center; display: inline-block;"><i class="fas fa-chevron-down fa-border fa-fw" style="margin-top:3px;"></i></a><br><br>
	 </div>
			<input type="button" id="select1" class="select btn btn-outline-primary btn-sm" value="조회"/>
	<!-- 메뉴 이동 순서 파란글씨로 나타낸 부분 -->
		<div class="chained" style="float: right;">
			<span>참조메뉴 : </span>
			<span style="cursor: pointer; text-decoration: underline;" onclick="location.href='${cPath}/sales/OrderbookRetrieveView.do'">수주서</span>
			<span> -> </span>
			<span style="cursor: pointer; text-decoration: underline;" onclick="location.href='${cPath}/sales/ReleaseOrderRetrieveView.do'">출고지시서</span>
			<span> -> </span>
			<span style="cursor: pointer; text-decoration: underline;" onclick="location.href='${cPath}/sales/ReleaseProcessingRetrieveView.do'">출고처리</span>
			<span> -> </span>
			<span style="cursor: pointer; text-decoration: underline;" onclick="location.href='${cPath}/sales/SalesCloseRetrieveView.do'">매출마감</span>
		</div>
	<!-- 상세검색(밑으로 내려오는 검색조건들) 창 -->
		<div id="myDIV" style="display: none">
		  	<div class="detail-div">
		  	사원<input type="search" name="empName" id="empName" onclick="empCode(this)"/>
		  	부서<input type="search" name="deptName" id="deptName" onclick="deptCode(this)"/>
		  	거래처<input type="search" name="buyerName" id="buyerName" onclick="buyerCode(this)"/>
			<input type="button" id="select2" class="select btn btn-outline-primary btn-sm" value="조회"/>
		</div>
	  </div>
	</fieldset>
    </form:form>
  	
  	<!-- masterGrid 메뉴 -->
  	<form:form class="basic_crud">
        <input type='button' id="append" class="btn btn-outline-primary btn-sm" value="추가"/>
        <input type='button' id="saved" class="btn btn-outline-primary btn-sm" value="저장"/>
        <input type='button' id="delete" class="btn btn-outline-primary btn-sm" value="삭제"/>
  	</form:form>
  	
    <div id="realgrid_master"></div>
    <!-- detailGrid 메뉴 -->
	<form:form class="basic_crud b_detail">
        <input type='button' id="append1" class="btn btn-outline-primary btn-sm" value="추가"/>
        <input type='button' id="saved1" class="btn btn-outline-primary btn-sm" value="저장"/>
        <input type='button' id="delete1" class="btn btn-outline-primary btn-sm" value="삭제"/>
        <span class="rightAlign">단위 : (원)</span>
     </form:form>


    <div id="realgrid_detail"></div>
 </div>
 <script src="${cPath}/resources/js/sales/estimate.js"></script>
 <script src="${cPath}/resources/js/custom/hubweb_managecommon.js"></script>
 <!-- SheetJS -->
    <script type="text/javascript" src="${cPath}/resources/js/realgrid/jszip.js"></script>
    <script type="text/javascript" src="${cPath}/resources/js/realgrid/shim.js"></script>
    <script type="text/javascript" src="${cPath}/resources/js/realgrid/xlsx.js"></script> 
  

