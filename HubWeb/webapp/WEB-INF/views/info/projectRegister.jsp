<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
th{
	width:175px;
}

</style>
<!-- 서브헤더 -->
<nav class="subHeader">
	<div id="subHeader-content">
		<h3 id="subHeader-name">프로젝트등록</h3>
		<div class="main-icon">
			<!-- pdf, excel -->
			<i class="far fa-file-pdf fa-2x"></i> 
			<i class="fas fa-file-import fa-2x"></i> 
			<a href="javascript:excelExport();" class="click" style="float: left; text-align: center; display: inline-block;"><i class="fas fa-file-export fa-2x"></i></a>
		</div>
	</div>
</nav>
<div id="jspcontent">
		<form:form id="searchForm">
			<fieldset>
				<!-- 	hubweb_searchMaster에 기본 검색 조건과 조회버튼 넣기 -->
				<div class="hubweb_searchMaster">
					<span class="hubweb_span">조회조건</span> <select name="projSort">
						<option value>전체</option>
						<option value="진행">진행</option>
						<option value="완료">완료</option>
						<option value="보류">보류</option>
					</select> <span class="hubweb_span">사용여부</span> <select name="projUse">
						<option value>전체</option>
						<option value="Y">사용</option>
						<option value="N">비사용</option>
					</select> <input class="btn btn-outline-primary btn-sm" type="button"
						id="select1" value="조회" />
				</div>
			</fieldset>
		</form:form>
		<br>
		<div class="row">
			<div class="col-lg-7">
				<div id="realgrid_master" style="height: 90%;"></div>
			</div>
			<div class="col-lg-5">
				<form:form id="projectDetail" >
					<input type="hidden" name="projCode" />
					<input type="hidden" name="projName" />
					<table class="table">
						<tr>
							<th>목적</th>
							<td><textarea name="projPurpose" id="projPurpose"
									placeholder="목적을 입력하세요" style="resize: none;" rows=6 cols=30
									disabled></textarea></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea name="projContent" id="projContent"
									placeholder="내용을 입력하세요" style="resize: none;" rows=6 cols=30
									disabled></textarea></td>
						</tr>
						<tr>
							<th>기간</th>
							<td><input type="text" name="projDate" id="projDate"
								value="" placeholder="기간을 입력하세요" style ="width: 275px; text-align: center;" disabled /></td>
							<td><input type="hidden" name="projStart" id="projStart"
								disabled /></td>
							<td><input type="hidden" name="projEnd" id="projEnd"
								disabled /></td>
						</tr>
						<tr>
							<th>예산금액</th>
							<td><input type="text" name="projBudget" placeholder="금액을 입력하세요" style ="width: 275px;" disabled />(단위:원)</td>
						</tr>
						<tr>
							<th>주관사원</th>
							<td><div class="inputDiv">
									<input type="text" name="empName" placeholder="사원 검색"	onclick="empCode(this)" style ="width: 275px;" readonly disabled />
								</div></td>
						</tr>
						<tr>
							<th>주관부서</th>
							<td><div class="inputDiv">
									<input type="text" name="deptName" placeholder="부서 검색"
										onclick="empCode(this)" style ="width: 275px;" readonly disabled />
								</div></td>
						</tr>
						<tr>
							<td align="left">
								<input type="button" class="btn btn-outline-primary btn-sm" id="delete" value="삭제"	disabled />
							</td>
							<td align="right">
								<input type="button" class="btn btn-outline-primary btn-sm" id="saved" value="저장" disabled />
							</td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
</div>





<script src="${cPath }/resources/js/daterangepicker/moment.min.js"></script>
<script src="${cPath }/resources/js/daterangepicker/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="${cPath }/resources/js/daterangepicker/daterangepicker.css" />
<script src="${cPath }/resources/js/info1/projectmanage.js"></script>