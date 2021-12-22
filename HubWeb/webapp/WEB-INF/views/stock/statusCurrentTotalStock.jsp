<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
#realgrid_master {
  width: 100%;
  height: 680px;
  margin-top: 2%;
}
</style>

<!-- 서브헤더 -->
<nav class="subHeader">
<div id="subHeader-content">
<h3 id="subHeader-name">현재고총괄현황</h3>
  	<div class="main-icon">
	  <!-- pdf, excel -->
		<i class="far fa-file-pdf fa-2x"></i>
	    <i class="fas fa-file-import fa-2x"></i>
	    <i class="fas fa-file-export fa-2x"></i>
	</div>
</div>
</nav>


<div id="jspcontent">
	<!-- 검색 -->
  	<form:form id="searchForm">
	<fieldset>
		<div style="padding-bottom: 20px;">
			품목계정<select name="rawsCode" id="rawsName">
					<option value>전체</option>
					<option value="상품">1.상품</option>
					<option value="원재료">2.원재료</option>
					<option value="부재료">3.부재료</option>
					<option value="제품">4.제품</option>
					<option value="반제품">5.반재품</option>
					<option value="부산품">6.부산품</option>
					<option value="저장품">5.저장품</option>
				  </select>
			품명 <input type="search" name="rawsName" onclick="itemCode(this)" />
			<input type="button" class="btn btn-outline-primary btn-sm" id="select" value="조회"/>
		</div>
	</fieldset>
	</form:form>
    <div id="realgrid_master"></div>
  
</div>
 
<script src="${cPath}/resources/js/stock/stockoverallstatus.js"></script>
<script src="${cPath}/resources/js/custom/hubweb_statuscommon.js"></script>


  
  
