/**
 * 생산품입고처리-생산지시서적용
 */
var date = new Date();
$("#piStartDate").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
$("#piEndDate").val(new Date().toISOString().substring(0, 10));

//모달 닫기
$("#piApplyForPW-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#piApplyForPW-modal").css("display", "none")
	}
}); 

//필드 컬럼 설정
var fields = [
		{ fieldName: "instNum", dataType: "text" }
	  , { fieldName: "instDate", dataType: "text" }
	  , { fieldName: "prodCode", dataType: "text" }
	  , { fieldName: "prodName", dataType: "text" }
	  , { fieldName: "gcommName", dataType: "text" }
	  , { fieldName: "instLeadqty", dataType: "int" }
	  , { fieldName: "empName", dataType: "text" } 
	]
	
var columns = [
	  { name: "instNum", fieldName: "instNum", type: "data", width: "120", header: { text: "지시서 번호" } }
	, { name: "instDate", fieldName: "instDate", type: "data", width: "105", header: { text: "일자" } }
	, { name: "prodCode", fieldName: "prodCode", type: "data", width: "90", header: { text: "품목코드" } }
	, { name: "prodName", fieldName: "prodName", type: "data", width: "375", header: { text: "품명" } ,styleName: "left-column", footer: {text: "합계 =>"} }
	, { name: "gcommName", fieldName: "gcommName", type: "data", width: "90", header: { text: "규격" } , "visible" : false }
	, { name: "instLeadqty", fieldName: "instLeadqty", type: "data", width: "100", header: { text: "지시수량" } , footer: {expression : "sum", numberFormat: "#,##0"} ,styleName: "right-column"}
	, { name: "empName", fieldName: "empName", type: "data", width: "80", header: { text: "사원" } }
	]

// 품목 코드도움
$("input[name=rawsCode]").focus(function(e){
    $("#item-modal").css('display', 'flex')
    itemCodeObj = this;
});

initPiAppPw();

function initPiAppPw(){
	let obSearchForm = $("#piApplySearchForm").ajaxForm({
		dataType : "json",
		success : function(resp) {
			piApplyMasterProvider.fillJsonData(resp, {fillMode: "set"});
		}
	}).submit();
}

// 조회
$("#selectPi").on("click",function(){
	var startDate = $("#piStartDate").val();
	var endDate = $("#piEndDate").val();
	// 날짜 지정하지 않았을 때
	if(startDate.replace(/\s/g,"").length==0){
		toastr.error("날짜를 선택하세요");
		return false;
	}
	if(endDate >= startDate){
		let obSearchForm = $("#piApplySearchForm").ajaxForm({
			dataType : "json",
			success : function(resp) {
				piApplyMasterProvider.fillJsonData(resp, {fillMode: "set"});
			}
		}).submit();
	}else{
		toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
});

//지시서 적용
$("#piApplyForPW").on("click",function(){
	piApplyMasterGrid.commit();
	let checkedRows = piApplyMasterGrid.getCheckedRows();
	if(checkedRows.length > 0){
		piApplyMasterProvider.setRowStates(checkedRows, "none", true);
		 
		var dataList = [];	// 체크된 행 데이터 넣을 배열
		dataList.push(arrNumData);
		for(var checked = 0 ;  checked < checkedRows.length ; checked++){
			data = piApplyMasterGrid.getValues(checkedRows[checked]);
			dataList.push(data);
		}	// for문 끝
		$.ajax({
			url : $.getContextPath()+"/produce/picProductionInstructionApply.do",
			data : JSON.stringify(dataList),
			method : "post",
			dataType : "text",
			contentType : "application/json",
			success : function(resp) {
				$("#piApplyForPW-modal").css("display", "none")
				if(resp=="성공"){
					toastr.success(resp);
				}else{
					toastr.error(resp);
				}
				let piSearchForm = $("#searchForm").ajaxForm({
					dataType : "json",
					success : function(resp) {
						masterProvider.fillJsonData(resp, {fillMode: "set"});
					}
				}).submit();
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	 }else{
			toastr.error("체크하고 적용해주세요");
	 }	
});

//리얼그리드 공통부분
var piApplyMasterProvider, piApplyMasterGrid;

/* 			기본 셋팅 		 	*/
function createpiApplyMasterGrid() {
	
	piApplyMasterProvider = new RealGrid.LocalDataProvider();
	piApplyMasterGrid = new RealGrid.GridView("realgrid_piApplyForPwMaster");

	piApplyMasterGrid.setDataSource(piApplyMasterProvider);
	piApplyMasterProvider.setFields(fields);
	piApplyMasterGrid.setColumns(columns);
	piApplyMasterGrid.resetCurrent();

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	piApplyMasterGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // piApplyMasterGrid 속성값
	piApplyMasterGrid.header.height = 40;
	piApplyMasterGrid.footer.height = 40;
	piApplyMasterGrid.stateBar.width = 10;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	piApplyMasterGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : false
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  , enterToTab : true
	  , forceAppend : true
	  , fitStyle : "even"
  });
  
  // 클릭한 곳의 Row의 넘버를 넘겨줌.
//  piApplyMasterGrid.onCurrentRowChanged = function (grid, oldRow, newRow) {
//    detailGetPi(newRow);
//  };
}  
  
function start() {
  createpiApplyMasterGrid();
}

window.onload = start();
window.onunload = function() {
	  dataProvider.clearRows();

	  gridView.destroy();
	  dataProvider.destroy();

	  gridView = null;
	  dataProvider = null;
}
$(function(){
	piApplyMasterGrid.stateBar.visible = false;
});