<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<style>
.input-file-button{
    display: inline-block;
    margin-bottom: 0.5rem;
    border: 1px solid #4e73df;
    border-radius: 2px;
    padding: 3.7px;
    font-size: 14px;
    color: #4e73df;
}

.input-file-button:hover{
	background-color: #4e73df;
	color: white;
}

#xlf{
	display : none;

}

#modal{
	width: 700px;
}
#detailTable th{
	padding : 0.3em
}
</style>
<nav class="subHeader">
	<div id="subHeader-content">
		<h3 id="subHeader-name">품목등록</h3>
		<div class="main-icon">
			<!-- pdf, excel -->
			<a class="click" style="float: left; text-align: center; display: inline-block;" id = "excelImportBtn"><i class="fas fa-file-import fa-2x"></i></a>
			<a href="javascript:excelExport();" class="click" style="float: left; text-align: center; id = "excelImportBtn"><i class="fas fa-file-export fa-2x"></i></a>
		</div>
	</div>
</nav>
<div id="jspcontent">
		<form:form clss="searchForm" id="searchForm">
		<fieldset>
				<!-- 	hubweb_searchMaster에 기본 검색 조건과 조회버튼 넣기 -->
				<div class="hubweb_searchMaster">
					<span class="hubweb_span">구분</span> 
					<select name="icodeName" id="icodeName">
						<option value>전체</option>
						<option value="상품">1.상품</option>
						<option value="원재료">2.원재료</option>
						<option value="부재료">3.부재료</option>
						<option value="제품">4.제품</option>
						<option value="반제품">5.반제품</option>
						<option value="부산품">6.부산품</option>
						<option value="저장품">7.저장품</option>
					</select>
					<span class="hubweb_span">품목</span> 
					<input type="text" name="rawsName" id="rawsName" onclick="rawsCode(this)" />
					<span class="hubweb_span">규격</span> 
					<input type="text" name="gcommName" onclick="gcommCode(this)"/>
					<span class="hubweb_span">사용여부</span> 						
		  			<select name="rawsUse">
						<option value>전체</option>
						<option value="Y">사용</option>
						<option value="N">비사용</option>
					</select>
					
					<input class="btn btn-outline-primary btn-sm" type="button"	id="select1" value="조회" />
				</div>
	</fieldset>
	<br>
		</form:form>
	<div class="row">
		<div class="col-lg-7">
			<div id="realgrid_master" style="height: 80%;"></div>
		</div>
		<div class="col-lg-5" style="height: 60%;">
			<form:form id="itemDetail">
				<input type="hidden" name="rawsCode" id="rawsCode" />
				<input type="hidden" name="icodeName" id="icodeName" />
				<table id="detailTable" class="table table-border" style="font-size: 13px">
					<tr>
						<th>대분류</th>
						<td>
						<div class="inputDiv">
							<input type="text" name="ldivName" id="ldivName" placeholder="" onclick="ldivCode(this)" readOnly disabled />
			 				<input type="hidden" name="divCodeL" />
			 			</div>
						</td>
					</tr>
					<tr>
						<th>중분류</th>
						<td>
						<div class="inputDiv">
							<input type="text" name="mdivName" id="mdivName" onclick="mdivCode(this)" readOnly disabled />
			 				<input type="hidden" name="divCodeM"/>
			 			</div>
						</td>
					</tr>
					<tr>
						<th>소분류</th>
						<td>
						<div class="inputDiv">
							<input type="text" name="sdivName" id="sdivName" onclick="sdivCode(this)" readOnly disabled />
						</div>
						</td>
					</tr>
					<tr>
						<th>규격명</th>
						<td>
						<div class="inputDiv">
							<input type="text" name="gcommName" id="gcommName" onclick="gcommCode(this)" readOnly disabled/>
						</div>
						</td>
					</tr>
					<tr>
						<th>입고단위</th>
						<td>
							<div class="inputDiv">
								<input type="text" name="ucommInname" id="ucommInname" onclick="ucommCode(this)" readOnly disabled/>
							</div>
						</td>
					</tr>
					<tr>
						<th>출고단위</th>
						<td>
							<div class="inputDiv">
								<input type="text" name="ucommOutname" id="ucommOutname" onclick="ucommCode(this)" readOnly disabled/>
							</div>
						</td>
					</tr>
					<tr>
						<th>재고단위</th>
						<td>
							<div class="inputDiv">
								<input type="text" name="ucommStname" id="ucommStname" onclick="ucommCode(this)" readOnly disabled/>
							</div>
						</td>
					</tr>
					<tr>			
						<th>적정재고량</th>
						<td>
							<input type="text" name="rawsAdqinv" id="rawsAdqinv" disabled/>(단위:개)
						</td>
					</tr>
					<tr>
						<th>표준원가</th>
						<td>
							<div class="inputDiv">
								<input type="text" name="rawsStancost" id="rawsStancost" disabled/>(단위:원)
							</div>
						</td>
					</tr>
					<tr>
						<th>실제원가</th>
						<td>
							<input type="text" name="rawsActucost" id="rawsActucost" disabled/>(단위:원)
						</td>
					</tr>
					<tr>
						<th>매입단가</th>
						<td>
							<input type="text" name="rawsPurchprice" id="rawsPurchprice" disabled/>(단위:원)
						</td>
					</tr>
					<tr>
						<th>매출단가</th>
						<td>
							<input type="text" name="rawsSalprice" id="rawsSalprice" disabled/>(단위:원)
						</td>
					</tr>
					<tr>
						<th>사용여부</th>
						<td>
							<select name="rawsUse" id="rawsUse" disabled>
									<option value="Y">사용</option>
									<option value="N">비사용</option>
							</select>
						</td>
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
<!-- 엑셀임포트 모달창 -->
<div id="excelImport-modal" class="modal-overlay modal">
	<div class="modal-scode" id=modal>
		<div class="title" >엑셀 Import</div> 
		<div class="close-area">X</div>
		<div style="float: right; margin: 30px -9px 0 0;" >
		<input class="select btn btn-outline-primary btn-sm" type="button" id="addExcel" value="선택"/>
        <label class="input-file-button" for="xlf">
		  업로드
		</label>
        <input class="select btn btn-outline-primary btn-sm" type="file" name="excelFile" id="xlf" style={{display:"none"}}/>
		</div>
		<div class="content">
			<div id="realgrid_excelImport" class="hubWeb_standard"></div>
		</div>
	</div>
</div>

 <script src="${cPath}/resources/js/info2/itemmanage.js"></script>
 <script src="${cPath}/resources/js/info2/itemmanageForPI.js"></script>
 <script src="${cPath}/resources/js/custom/hubweb_codeAssSearch.js"></script>
 <!-- SheetJS -->
 <script type="text/javascript" src="${cPath}/resources/js/realgrid/jszip.js"></script>
 <script type="text/javascript" src="${cPath}/resources/js/realgrid/shim.js"></script>
 <script type="text/javascript" src="${cPath}/resources/js/realgrid/xlsx.js"></script>
