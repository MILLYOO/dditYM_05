<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<nav class="subHeader">
	<div id="subHeader-content">
		<h3 id="subHeader-name">사원등록</h3>
		<div class="main-icon">
			<!-- pdf, excel -->
			<i class="far fa-file-pdf fa-2x"></i> 
			<i class="fas fa-file-import fa-2x"></i> 
			<a href="javascript:excelExport();" class="click" style="float: left; text-align: center; display: inline-block;"><i class="fas fa-file-export fa-2x"></i></a>
		</div>
		<input class="btn btn-primary btn-sm" type="button"name="empSearch" class="empSearch" id="empSearch" value="사원검색" onclick="empCode()" value="사원검색"  style="float: right; margin-left: 10px"/>
	</div>
</nav>

<div id="jspcontent">
	<div>
<!-- 		<input type="button" name="delete" id="delete" value="삭제" /> -->
		<div class="row">
			<div class="col-lg-7">
				<div id="realgrid_master" style="height: 90%;"></div>
			</div>
			<div class="col-lg-5">
				<form:form id="employeeDetail">
					<input type="hidden" name="empCode" />
					<input type="hidden" name="empName" />
					<table class="table">
						<tr>
							<th>부서코드</th>
							<td>
									<input type="hidden" name="deptCode" />
									<input type="text" name="deptName" id ="deptName"readOnly disabled />
							</td>
						</tr>
						<tr>
							<th>입사일</th>
							<td><input type="date" name="empDate" disabled /></td>
						</tr>
						<tr>
							<th>주민번호</th>
							<td><input type="text" name="empReg1" id="empReg1" disabled style="width: 90px" /> - <input
								type="password" name="empReg2" id="empReg2" disabled style="width: 90px"/></td>
						</tr>
						<tr>
							<th>주소</th>
							<td><input type="text" name="empAdd1" id="empAdd1" disabled />&nbsp&nbsp<input class="btn btn-outline-primary btn-sm" type="button" id="searchAdd" value="검색" disabled /></td>
						</tr>
						<tr>
							<th>상세주소</th>
							<td><input type="text" name="empAdd2" id="empAdd2" disabled /></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><input type="text" name="empLine" id="empLine" disabled /></td>
						</tr>
						<tr>
							<th>핸드폰번호</th>
							<td><input type="text" name="empTel" id="empTel" disabled /></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><input type="text" name="empMail" id="empMail" disabled /></td>
						</tr>
						<tr>
							<th>직급</th>
							<td><select name="empRank" id="empRank" disabled>
									<option value>직급</option>
									<option value="회장">회장</option>
									<option value="사장">사장</option>
									<option value="부사장">부사장</option>
									<option value="상무">상무</option>
									<option value="이사">이사</option>
									<option value="부장">부장</option>
									<option value="차장">차장</option>
									<option value="과장">과장</option>
									<option value="대리">대리</option>
									<option value="주임">주임</option>
									<option value="사원">사원</option>
							</select></td>
						</tr>
						<tr>
							<th>퇴사일자</th>
							<td><input type="date" name="empFire" disabled /></td>
						</tr>
						<tr>
							<th>재직여부</th>
							<td><select name="empUse" id="empUse" disabled>
									<option value="Y">재직</option>
									<option value="N">퇴직</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2" align= "right">
								<input type="button" class="btn btn-outline-primary btn-sm" id="saved" value="저장" disabled/>
							</td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</div>

<script src="${cPath }/resources/js/info1/employeemanage.js"></script>
