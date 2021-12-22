<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
#standardForm, #unitForm, #processForm, #sdivForm, #mdivForm, #ldivForm{
	float: right;
}

</style>
	<!-- 서브헤더 -->
<nav class="subHeader">
	<div id="subHeader-content">
		<h3 id="subHeader-name">공통코드등록</h3>
		<div class="main-icon">
			<!-- pdf, excel -->
			<i class="far fa-file-pdf fa-2x"></i> 
			<i class="fas fa-file-import fa-2x"></i> 
			<a href="javascript:excelExport();" class="click" style="float: left; text-align: center; display: inline-block;"><i class="fas fa-file-export fa-2x"></i></a>
		</div>
	</div>
</nav>
<div id="jspcontent">
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation"><a
			class="nav-link active" id="common1-tab" data-bs-toggle="tab"
			href="#common1" role="tab" aria-controls="common1"
			aria-selected="true">규격/단위/공정</a></li>
		<li class="nav-item" role="presentation"><a class="nav-link"
			id="common2-tab" data-bs-toggle="tab" href="#common2" role="tab"
			aria-controls="common2" aria-selected="false">분류</a></li>
	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="common1" role="tabpanel"
			aria-labelledby="common1-tab">
				<div class="row">
					<div class="col">
						<form:form id="standardForm">
						<input type="button" id="addStandard" class="add btn btn-outline-primary btn-sm" value="추가" data-category="standard" /> 
						<input type="button" id="delStandard" class="del btn btn-outline-primary btn-sm" value="삭제" data-category="standard" /> 
						<input type="button" id="saveStandard" class="save btn btn-outline-primary btn-sm" value="저장"	data-category="standard" />
						</form:form>
						<h3>규격 등록</h3> 
						<div id="register_standard" class="hubWeb_code"></div>
					</div>
					<div class="col">
						<form:form id="unitForm">
						<input type="button" id="addUnit" class="add btn btn-outline-primary btn-sm" value="추가 " data-category="unit" /> 
						<input type="button" id="delUnit" class="del btn btn-outline-primary btn-sm" value="삭제" data-category="unit" /> 
						<input type="button" id="saveUnit" class="save btn btn-outline-primary btn-sm" value="저장" data-category="unit" />
						</form:form>
						<h3>단위 등록</h3> 
						<div id="register_unit" class="hubWeb_code"></div>
					</div>
					<div class="col">
						<form:form id="processForm">
						<input type="button" id="addProcess" class="add btn btn-outline-primary btn-sm" value="추가" data-category="process" /> 
						<input type="button" id="delProcess" class="del btn btn-outline-primary btn-sm" value="삭제" 	data-category="process" /> 
						<input type="button" id="saveProcess" class="save btn btn-outline-primary btn-sm" value="저장" data-category="process" />
						</form:form>
						<h3>공정 등록</h3> 
						<div id="register_process" class="hubWeb_code"></div>
					</div>
				</div>
		</div>
		<div class="tab-pane fade" id="common2" role="tabpanel"
			aria-labelledby="common2-tab">
<!-- 			<div class="container"> -->
				<div class="row">
					<div class="col">
						<form:form id="ldivForm">
						<input type="button" id="addLdiv" class="add btn btn-outline-primary btn-sm" value="추가" data-category="ldiv" /> 
						<input type="button" id="delLdiv" class="del btn btn-outline-primary btn-sm" value="삭제" data-category="ldiv" /> 
						<input type="button" id="saveLdiv" class="save btn btn-outline-primary btn-sm" value="저장"	data-category="ldiv" />
						</form:form>
						<h3>대분류</h3> 
						<div id="register_ldiv" class="hubWeb_code"></div>
					</div>
					<div class="col">
						<form:form id="mdivForm"> 
						<input type="button" id="addMdiv" class="add btn btn-outline-primary btn-sm" value="추가" data-category="mdiv" /> 
						<input type="button" id="delMdiv" class="del btn btn-outline-primary btn-sm" value="삭제" data-category="mdiv" /> 
						<input type="button" id="saveMdiv" class="save btn btn-outline-primary btn-sm" value="저장"	data-category="mdiv" />
						</form:form>
						<h3>중분류</h3>
						<div id="register_mdiv" class="hubWeb_code"></div>
					</div>
					<div class="col">
						<form:form id="sdivForm"> 
						<input type="button" id="addSdiv" class="add btn btn-outline-primary btn-sm" value="추가" data-category="sdiv" /> 
						<input type="button" id="addSdiv" class="del btn btn-outline-primary btn-sm" value="삭제" data-category="sdiv" /> 
						<input type="button" id="addSdiv" class="save btn btn-outline-primary btn-sm" value="저장" data-category="sdiv" />
						</form:form>
						<h3>소분류</h3>
						<div id="register_sdiv" class="hubWeb_code"></div>
					</div>
				</div>
<!-- 			</div> -->
		</div>
	</div>
</div>
<script src="${cPath}/resources/js/info2/codemanage.js"></script>