<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<nav class="subHeader">
	<div id="subHeader-content">
		<h3 id="subHeader-name">창고등록</h3>
		<div class="main-icon">
			<!-- pdf, excel -->
			<i class="far fa-file-pdf fa-2x"></i> 
			<i class="fas fa-file-import fa-2x"></i> 
			<a href="javascript:excelExport();" class="click" style="float: left; text-align: center; display: inline-block;"><i class="fas fa-file-export fa-2x"></i></a>
		</div>
	</div>
</nav>
<div id="jspcontent">
	<div>
				<form:form id="searchForm">
				<fieldset>
				<!-- 	hubweb_searchMaster에 기본 검색 조건과 조회버튼 넣기 -->
				<div class="hubweb_searchMaster">
					<span class="hubweb_span">창고</span><input type="text" name="warName" onclick="warCode(this)"/>
					<span class="hubweb_span">부서</span><input type="text" name="deptName" onclick="deptCode(this)"/>
					<span class="hubweb_span">사원</span><input type="text" name="empName" onclick="empCode(this)"/>
					<span class="hubweb_span">사용여부</span>
					<select name="warUse">
								<option value>전체</option>
								<option value="Y">사용</option>
								<option value="N">비사용</option>
					</select>
					</select>&nbsp <input class="btn btn-outline-primary btn-sm" type="button"
						id="select" value="조회" />
				</div>
			</fieldset>
			</form:form>
			<br>
		<div class="row">
			<div class="col-lg-7">
				<div id="realgrid_master" style="height: 620px"></div>
			</div>
			<div class="col-lg-5">
				<form:form id="warehouseDetail">
					<input type="hidden" name="warCode" />
					<input type="hidden" name="warName" />
					<table class="table">
						<tr>
							<th>기본주소</th>
							<td><input type="text" name="warAdd1" id="warAdd1" readonly disabled />&nbsp;&nbsp;<input
								type="button" class="btn btn-outline-primary btn-sm" id="searchAdd" value="검색" disabled /></td>
						</tr>
						<tr>
							<th>상세주소</th>
							<td><input type="text" name="warAdd2" id="warAdd2" disabled /></td>
						</tr>
						<tr>
							<th>담당부서</th>
							<td><input type="text" name="deptName" readonly disabled onclick="deptCode(this)" /></td>
						</tr>
						<tr>
							<th>담당사원</th>
							<td><input type="text" name="empName" readonly disabled onclick="empCode(this)" /></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><input type="text" name="warLine" id="warLine" disabled /></td>
						</tr>
						<tr>
							<th>사용여부</th>
							<td><select name="warUse" id="warUse" disabled>
									<option value="Y">사용</option>
									<option value="N">비사용</option>
							</select></td>
						</tr>
						<tr>
							<td align="left">
								<input class="btn btn-outline-primary btn-sm" type="button" id="delete" value="삭제" disabled/>
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
</div>


<script src="${cPath }/resources/js/info2/warehousemanage.js"></script>
<script	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>