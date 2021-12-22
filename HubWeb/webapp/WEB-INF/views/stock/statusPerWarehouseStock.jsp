<!-- 창고별 재고현황 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
#realgrid_master {
  width: 105%;
  height: 700px;
}
#realgrid_detail {
  width: 1312px;
  height: 700px;
}
.col{
	margin-top : 35px;
}
.col-lg-5 {
  flex: 0 0 50%;
  max-width: 17%;
  position: relative;  /*****/
  display: flex;
  align-items: center;
  margin-top : 35px;
}
.col-lg-6 {
  flex: 0 0 50%;
  max-width: 80%;
  position: relative;  /*****/
  display: flex;
  align-items: center;
}
</style>

<!-- 서브헤더 -->
<nav class="subHeader">
<div id="subHeader-content">
<h3 id="subHeader-name">창고별 재고현황</h3>
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
	<div style="padding-bottom: 20px;">
  		<div class="searchDate"> 
			<input type="Date" name="dateStart" id="dateStart" /> 
			<input type="Date" name="dateEnd" id="dateEnd" />
		</div>	 
		&nbsp&nbsp&nbsp&nbsp&nbsp
		<input type="hidden" name="warName" onclick="warCode(this)" />
		품목계정<select name="icodeName" id="rawsName">
				<option value>전체</option>
				<option value="상품">1.상품</option>
				<option value="원재료">2.원재료</option>
				<option value="부재료">3.부재료</option>
				<option value="제품">4.제품</option>
				<option value="반제품">5.반재품</option>
				<option value="부산품">6.부산품</option>
				<option value="저장품">5.저장품</option>
			  </select>
<!-- 		품명 <input type="search" name="rawsName" onclick="itemCode(this)" /> -->
	  <input type="button" id="select" value="조회" /> 
	</div>
	</fieldset>
	</form:form>
    <div>
	    <div class="row">
	        <div class="col">
	        	<div id="realgrid_master"></div>
	        </div>
	        <div class="col">
	        	<div id="realgrid_detail"></div>
	        </div>
	    </div>
	</div>
  
</div>
 
<script src="${cPath}/resources/js/stock/stockstatusbywarehouse.js"></script>
<script src="${cPath}/resources/js/custom/hubweb_statuscommon.js"></script>
