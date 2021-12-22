<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- 서브헤더 -->
<nav class="subHeader">
<div id="subHeader-content">
<h3 id="subHeader-name">권한관리</h3>
  	<div class="main-icon">
	  <!-- pdf, excel -->
		<i class="far fa-file-pdf fa-2x"></i>
	    <i class="fas fa-file-import fa-2x"></i>
	    <i class="fas fa-file-export fa-2x"></i>
	</div>
</div>
</nav>
<div id="jspcontent">
	<div>
		<div class="row">
			<div class="col">
				<div id="realgrid_master" style="height: 700px"></div>
			</div>
			<div class="col">
				<div id="realgrid_detail" style="height: 700px"></div>
			</div>
		</div>
	</div>
</div>
<script src="${cPath}/resources/js/info1/authmanage.js"></script>
