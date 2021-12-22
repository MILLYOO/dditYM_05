/**
 * 지시서적용
 */
// 이달 1일 ~ 현재 날짜 세팅
var date = new Date();
$("#piStartDate").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
$("#piEndDate").val(new Date().toISOString().substring(0, 10));

//모달 닫기
$("#piApplyForMR-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#piApplyForMR-modal").css("display", "none")
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
	  { name: "instNum", fieldName: "instNum", type: "data", width: "110", header: { text: "지시서 번호" } }
	, { name: "instDate", fieldName: "instDate", type: "data", width: "100", header: { text: "일자" } }
	, { name: "prodCode", fieldName: "prodCode", type: "data", width: "90", header: { text: "품목코드" } }
	, { name: "prodName", fieldName: "prodName", type: "data", width: "318", header: { text: "품명" } ,styleName: "left-column"}
	, { name: "gcommName", fieldName: "gcommName", type: "data", width: "100", header: { text: "규격" } , footer: {text: "합계 =>"} }
	, { name: "instLeadqty", fieldName: "instLeadqty", type: "data", width: "80", header: { text: "지시수량" } , footer: {expression : "sum", numberFormat: "#,##0"} ,styleName: "right-column"}
	, { name: "empName", fieldName: "empName", type: "data", width: "70", header: { text: "사원" } }
	]

var fields1 = [
	  { fieldName: "icodeName", dataType: "text" }
	, { fieldName: "rawsCode", dataType: "text" }
	, { fieldName: "rawsName", dataType: "text" }
	, { fieldName: "gcommName", dataType: "text" }
	, { fieldName: "ucommName", dataType: "text" }
	, { fieldName: "prQty", dataType: "int" }
	]

var columns1 = [
	  { name: "icodeName", fieldName: "icodeName", type: "data", width: "90", header: { text: "품목계정" } }
	, { name: "rawsCode", fieldName: "rawsCode", type: "data", width: "90", header: { text: "품목코드" } }
	, { name: "rawsName", fieldName: "rawsName", type: "data", width: "380", header: { text: "투입자재" } ,styleName: "left-column"}
	, { name: "gcommName", fieldName: "gcommName", type: "data", width: "110", header: { text: "규격" } }
	, { name: "ucommName", fieldName: "ucommName", type: "data", width: "100", header: { text: "단위" } , footer: {text: "합계 =>"} }
	, { name: "prQty", fieldName: "prQty", type: "data", width: "120", header: { text: "투입예정수량" } , footer: {expression : "sum", numberFormat: "#,##0"} ,styleName: "right-column"}
	]

	initPiApp();
	
	function initPiApp(){
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

	function detailGetPi(masterRow) {
		  detailProvider.clearRows();
		  if (masterRow >= 0) {
		      var mstKey = piApplyMasterProvider.getValue(masterRow, "instNum");
		     $.ajax({
				url : $.getContextPath()+"/produce/instRawsList.do",
				data : {"instNum" : mstKey},
				method : "post",
				dataType : "json",
				success : function(resp) {
					piApplyDetailProvider.fillJsonData(resp, {fillMode: "set"});
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			});
		  };
	};
	
	// 품목 코드도움
	$("#rawsCode").focus(function(e){
        $("#item-modal").css('display', 'flex')
        itemCodeObj = this;
	});
	
	// 지시서 적용
	$("#addPiApply").on("click",function(){
		piApplyMasterGrid.commit();
		let checkedRows = piApplyMasterGrid.getCheckedRows();
		if(checkedRows.length > 0){
			piApplyMasterProvider.setRowStates(checkedRows, "none", true);
			 
			var dataList = [];	// 체크된 행 데이터 넣을 배열
			 
			for(var checked = 0 ;  checked < checkedRows.length ; checked++){
				data = piApplyMasterGrid.getValues(checkedRows[checked]);
				dataList.push(data);
			}	// for문 끝
			
			$.ajax({
				url : $.getContextPath()+"/produce/mrProductionInstructionApply.do",
				data : JSON.stringify(dataList),
				method : "post",
				dataType : "text",
				contentType : "application/json",
				success : function(resp) {
					$("#piApplyForMR-modal").css("display", "none")
					if(resp=="성공"){
						toastr.success("적용되었습니다");
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
var piApplyMasterProvider, piApplyMasterGrid, piApplyDetailProvider, piApplyDetailGrid;

/* 			기본 셋팅	 	*/
function createpiApplyMasterGrid() {
	
	piApplyMasterProvider = new RealGrid.LocalDataProvider();
	piApplyMasterGrid = new RealGrid.GridView("realgrid_piApplyForMrMaster");

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
  piApplyMasterGrid.onCurrentRowChanged = function (grid, oldRow, newRow) {
    detailGetPi(newRow);
  };
  
}  
  
  /* 		기본 셋팅 	 	*/
  function createpiApplyDetailGrid() {
	  piApplyDetailProvider = new RealGrid.LocalDataProvider();
	  piApplyDetailGrid = new RealGrid.GridView("realgrid_piApplyForMrDetail");

	  piApplyDetailGrid.setDataSource(piApplyDetailProvider);
	  piApplyDetailProvider.setFields(fields1);
	  piApplyDetailGrid.setColumns(columns1);

    // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	  piApplyDetailGrid.setDisplayOptions({
  	  emptyMessage : "표시할 데이타가 없습니다."
  	  , showEmptyMessage: true
  	  , rowHeight : 30
  	  });
    
    // piApplyDetailGrid 속성값
	  piApplyDetailGrid.header.height = 40;
	  piApplyDetailGrid.footer.height = 40;
	  piApplyDetailGrid.stateBar.width = 10;
	  piApplyDetailGrid.setCheckBar({visible: false});
	  piApplyDetailGrid.setEditOptions({
		  insertable : true
		  , appendable : true
		  , editable : false
		  , deletable: true
		  , confirmWhenDelete : true
		  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
		  , fitStyle : "even"	
	  });
    
	  
	  setTimeout(function(){
		  piApplyMasterGrid.resetCurrent();
	  },300);
  
  }

function start() {
  createpiApplyMasterGrid();
  createpiApplyDetailGrid();
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
	piApplyDetailGrid.stateBar.visible = false;
});