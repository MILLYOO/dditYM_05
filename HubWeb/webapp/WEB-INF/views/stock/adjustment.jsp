<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
#reqSelect{
	margin-right: 10px;
	float: right; 
}
</style>
<!-- 
	adjustment.jsp
	By 이원균_211207(최종수정)
	입출고조정메뉴 jsp 입니다.
	1. 재고실사에 따른 조정 요청 시 알림
	2. 입출고조정 jsp Tag
	3. 모달창 jsp Tag
	4. 모달창 Script
	5. 로딩 Script
-->

<!-- ======================================================================================== -->
<!-- 1. 재고실사에 따른 조정 요청 시 알림 : 있을경우 alert-->
<c:if test="${stockTakingNList.size() != 0}">
	<script>
    	Swal.fire({
    			title: "알림",
    			html:"입출고조정 요청이 존재합니다. 확인해주세요"
    	})
	 </script> 
</c:if>

<!-- ======================================================================================== -->
<!-- 2. 입출고조정 jsp Tag-->
<!-- 서브헤더 -->
<nav class="subHeader">
<div id="subHeader-content">
<h3 id="subHeader-name">입출고조정</h3>
  	<div class="main-icon">
	  <!-- pdf, excel -->
		<i class="far fa-file-pdf fa-2x"></i>
	    <i class="fas fa-file-import fa-2x"></i>
	    <i class="fas fa-file-export fa-2x"></i>
	</div>
	<input class="btn btn-primary btn-sm" type="button" id="reqSelect" value="조정요청내역" >
</div>
</nav>

<div id="jspcontent">
	<!-- 검색 -->
	<form:form id="searchForm">
	<fieldset>
	<div>
		<div class="searchDate"> 
			<input type="Date" name="dateStart" id="dateStart" /> 
			<input type="Date" name="dateEnd" id="dateEnd" />
		</div>	 
		  <!-- 상세검색 내리는 버튼. 각자 js에 카톡에 보낸 스크립트 추가해야됨 -->	
		  <a href="javascript:doDisplay(); javascript:doHide();" class="click"><i class="fas fa-chevron-down fa-border  fa-fw"></i></a>
		  <input type="button" id="select1" value="조회" class="select btn btn-outline-primary btn-sm"/> 
	</div>
		  <!-- 상세검색(밑으로 내려오는 검색조건들) 창 -->
		<div id="myDIV" style="display: none">
			<div class="detail-div">
			유형<select name="adjSort">
					<option value>전체</option>
					<option value="입고">1.입고</option>
					<option value="출고">2.출고</option>
				</select>
			 부서 <input type="search" name="deptName" onclick="deptCode(this)" />
			사원 <input type="search" name="empName" onclick="empCode(this)" />
			거래처 <input type="search" name="buyerName" onclick="buyerCode(this)" />
			프로젝트 <input type="search" name="projName" onclick="projCode(this)" />
	<!-- 		대분류 <input type="search" name="ldivName" onclick="ldivCode(this)" /> -->
	<!-- 			 <input type="hidden" name="divCodeL"> -->
	<!-- 		중분류 <input type="search" name="mdivName" onclick="mdivCode(this)" /> -->
	<!-- 			 <input type="hidden" name="divCodeM"> -->
	<!-- 		소분류 <input type="search" name="sdivName" onclick="sdivCode(this)" /> -->
	<!-- 		창고 <input type="search" name="warName" onclick="warCode(this)" /> -->
	<!-- 		공정 <input type="search" name="kcommName" onclick="kcommCode(this)" /> -->
	<!-- 		규격 <input type="search" name="gcommName" onclick="gcommCode(this)" /> -->
	<!-- 		품목 <input type="search" name="rawsName" onclick="itemCode(this)" /> -->
	<!-- 		단위 <input type="search" name="ucommName" onclick="ucommCode(this)" /> -->
			  <input type="button" id="select2" class="select btn btn-outline-primary btn-sm" value="조회"/>
			</div>
		</div>
	</fieldset>	
	</form:form>

	<!-- masterGrid 버튼 -->
	<form:form class="basic_crud">
		<input class="btn btn-outline-primary btn-sm" type="button" id="append" value="추가" />
		<input class="btn btn-outline-primary btn-sm" type="button" id="saved" value="저장" /> 
		<input class="btn btn-outline-primary btn-sm" type="button" id="delete" value="삭제" />
	</form:form>
	<div id="realgrid_master"></div>
	
	<!-- detailGrid 버튼 -->
	<form:form class="basic_crud b_detail">
		<input class="btn btn-outline-primary btn-sm" type="button" id="append1" value="추가" />
		<input class="btn btn-outline-primary btn-sm" type="button" id="saved1" value="저장" /> 
		<input class="btn btn-outline-primary btn-sm" type="button" id="delete1" value="삭제" />
	</form:form>
	<div id="realgrid_detail"></div>
</div>

<!-- ======================================================================================== -->
<!-- 3. 모달창 jsp Tag-->
	<!-- 조정요청내역 모달창 -->
	<div id="inOutReq-modal" class="modal-overlay modal">
		<div class="modal-apply">
			<div class="title">재고조정 요청 내역</div>
			<div class="close-area">X
				<input type="button" id="deleteInOutReq" class="btn btn-primary btn-sm" value="확인" style="position: absolute; right: 15px;">
				</div>
				<br><br>
			<div class="content">
				<div id="realgrid_inOutReq" class="hubWeb_standard">
				</div>
			</div>
			<div>
				
			</div>
		</div>
	</div>
	
<!-- ======================================================================================== -->
<!-- 4. 모달창 Script-->	
<script>
//데이터 가져오기
$("#reqSelect").on("click",function(){
	$("#inOutReq-modal").css('display', 'flex')
	$.ajax({
		url : $.getContextPath()+"/stock/stockTakingRetrieve.do",
		method : "post",
		dataType : "json",
		success : function(resp) {
			inOutReqProvider.fillJsonData(resp, {fillMode: "set"});
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
})
</script>

<!-- ======================================================================================== -->
<!-- 5. 로딩 Script-->
<script src="${cPath}/resources/js/stock/inoutadjustment.js"></script>
<script src="${cPath}/resources/js/custom/hubweb_managecommon.js"></script>
<script src="${cPath}/resources/js/custom/codeInOutReq.js"></script>


<script>
	masterGrid.footer.visible = false
</script>