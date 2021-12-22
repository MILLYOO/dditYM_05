<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
input.img-button {
	background: url(https://static.wehago.com/imgs/sao/sp_sao.png) -424px
		-319px/auto no-repeat;
	display: inline-block;
	height: 24px;
	line-height: 100em;
	margin-top: 3px;
	overflow: hidden;
	vertical-align: top;
	width: 28px !important;
}

#buyerDetail input[name] {
width: 275px;
}
th{
	width:175px;
}
</style>
<!-- 서브헤더 -->
<nav class="subHeader">
<div id="subHeader-content">
<h3 id="subHeader-name">거래처등록</h3>
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
			<span class="hubweb_span">사용여부</span>
				<select name="buyerUse">
				  	<option value>전체</option>
					<option value="Y">사용</option>
					<option value="N">미사용</option>
			      </select>
		<!-- 상세검색 내리는 버튼. 각자 js에 카톡에 보낸 스크립트 추가해야됨 -->	
				<a href="javascript:doDisplay(); javascript:doHide();" class="click" ><i class="fas fa-chevron-down fa-border fa-fw" style="margin-top:3px;" ></i></a>
				<input type="button" id="select1" class="select btn btn-outline-primary btn-sm" value="조회"/>
	</div>
		<!-- 상세검색(밑으로 내려오는 검색조건들) 창 -->
			<div id="myDIV" style="display: none">
				<span class="hubweb_span">거래처명</span><input type="text" name="buyerName" />
				<span class="hubweb_span">대표자명</span><input type="text" name="buyerCeo" />
				<span class="hubweb_span">사업자등록번호</span><input type="text" name="buyerRegnumber" id="buyerRegnumber" />
				<input class="select btn btn-outline-primary btn-sm" type="button" id="select2" value="조회" />
			</div>
		</fieldset>
	</form:form>
		<br>
		<div>
			<div class="row">
				<div class="col-lg-7">
					<div id="realgrid_master" style="height: 90%;"></div>
				</div>
				<div class="col-lg-5">
					<form:form id="buyerDetail">
						<input type="hidden" id="buyerCode" name="buyerCode" />
						<input type="hidden" id="buyerName" name="buyerName" />
						<table class="table">
							<tr>
								<th>사업자등록번호</th>
								<td><input type="text" name="buyerRegnumber"
									id="buyerRegnumber2" disabled>&nbsp&nbsp<input type="button"
									id="RegnumberCheck" name="RegnumberCheck" class="img-button"
									disabled></td>
							</tr>
							<tr>
								<th>업태</th>
								<td><input type="text" name="buyerWorktype"
									id="buyerWorktype" disabled></td>
							</tr>
							<tr>
								<th>종목</th>
								<td><input type="text" name="buyerEvent" id="buyerEvent"
									disabled></td>
							</tr>
							<tr>
								<th>기본주소</th>
								<td><input type="text" name="buyerAdd1" id="buyerAdd1"
									readonly disabled />&nbsp&nbsp<input type="button" id="searchAdd"
									value="검색" disabled /></td>
							</tr>
							<tr>
								<th>상세주소</th>
								<td><input type="text" name="buyerAdd2" id="buyerAdd2"
									disabled /></td>
							</tr>

							<tr>
								<th>전화번호</th>
								<td><input type="text" name="buyerTel" id="buyerTel"
									disabled></td>
							</tr>
							<tr>
								<th>팩스번호</th>
								<td><input type="text" name="buyerFax" id="buyerFax"
									disabled></td>
							</tr>
							<tr>
								<th>담당사원</th>
								<td><input type="text" name="buyerPartner" readonly
									placeholder="사원코드, 사원명으로 검색" id="buyerPartner"onclick="empCode(this)" disabled></td>
							</tr>
							<tr>
								<th>담당부서</th>
								<td><input type="text" name="buyerDesk" readonly
									placeholder="부서코드, 부서명으로 검색" id="buyerDesk" onclick="deptCode(this)" disabled></td>
							</tr>
							<tr>
								<th>거래기간</th>
								<td>
									<input type="text" name="buyerDate" id="buyerDate"	readonly value="" placeholder="기간을 입력하세요" disabled /> 
									<input type="hidden" name="buyerStart" id="buyerStart" disabled/>
									<input type="hidden" name="buyerEnd" id="buyerEnd" disabled/>
								</td>
							<tr>
								<td align="left">
									<input type="button" class="btn btn-outline-primary btn-sm"	id="delete" value="삭제" disabled />
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

<script src="${cPath }/resources/js/info1/buyermanage.js"></script>