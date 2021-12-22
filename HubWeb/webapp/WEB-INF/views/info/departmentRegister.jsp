<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<nav class="subHeader">
	<div id="subHeader-content">
		<h3 id="subHeader-name">부서등록</h3>
		<div class="main-icon">
			<!-- pdf, excel -->
			<i class="far fa-file-pdf fa-2x"></i> 
			<i class="fas fa-file-import fa-2x"></i> 
			<a href="javascript:excelExport();" class="click" style="float: left; text-align: center; display: inline-block;"><i class="fas fa-file-export fa-2x"></i></a>
		</div>
		<input type="button" class="btn btn-primary btn-sm"	name="delete" id="delete" value="삭제"  style="float: right; margin-left: 10px"/>
	</div>
</nav>
<div id="jspcontent">
	<div>
		<div class="row">
			<div class="col-lg-6">
				<div id="depart_master"></div>
			</div>
			<div class="col-lg-6">
				<div id="depart_detail"></div>
			</div>
		</div>
	</div>
</div>
<script src="${cPath }/resources/js/info1/departmentmanage.js"></script>
