<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!-- 서브헤더 -->
<nav class="subHeader">
<div id="subHeader-content">
<h3 id="subHeader-name">BOM관리</h3>
  	<div class="main-icon">
	  <!-- pdf, excel -->
		<i class="far fa-file-pdf fa-2x"></i>
	    <i class="fas fa-file-import fa-2x"></i>
	    <i class="fas fa-file-export fa-2x"></i>
	</div>
</div>
</nav>
<div id="jspcontent">
	<form:form style="float:right;">
	  	<input type="button" id="saved" class="btn btn-outline-primary btn-sm" value="저장"/>
	  	<input type="button" id="delete" class="btn btn-outline-primary btn-sm" value="삭제"/>
  	</form:form>
  	<h3>모품목</h3>
	<div id="realgrid_BOMmaster"></div>
	<br>
	<form:form style="float:right;">
	  	<input type="button" id="saved1" class="btn btn-outline-primary btn-sm" value="저장"/>
	  	<input type="button" id="append1" class="btn btn-outline-primary btn-sm" value="추가"/>
	  	<input type="button" id="delete1" class="btn btn-outline-primary btn-sm" value="삭제"/>
  	</form:form>
  	<h3>자품목</h3>
	<div id="realgrid_BOMdetail"></div>
	<h3>BOM전개</h3>
	<div id="realgrid_BOMview"></div>
</div>


<script src="${cPath }/resources/js/info2/bommanage.js"></script>