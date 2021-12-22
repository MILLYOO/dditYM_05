/**
 * 수주서적용
 */
// 이달 1일 ~ 현재 날짜 세팅
var date = new Date();
$("#obStartDate").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
$("#obEndDate").val(new Date().toISOString().substring(0, 10));

//모달 닫기
$("#orderbookApplyForPI-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#orderbookApplyForPI-modal").css("display", "none");
	}
});  

//필드 컬럼 설정
var fields = [
		{ fieldName: "orbNum", dataType: "text" }
	  , { fieldName: "orbDate", dataType: "text" }
	  , { fieldName: "buyerName", dataType: "text" }
//	  , { fieldName: "orbVat", dataType: "text" }
	]
	
var columns = [
	  { name: "orbNum", fieldName: "orbNum", type: "data", width: "300", header: { text: "수주서 번호" } }
	, { name: "orbDate", fieldName: "orbDate", type: "data", width: "250", header: { text: "일자" } }
	, { name: "buyerName", fieldName: "buyerName", type: "data", width: "300", header: { text: "거래처" } }
//	, { name: "orbVat", fieldName: "orbVat", type: "data", width: "103", header: { text: "" } }
	]

var fields1 = [
	  { fieldName: "icodeName", dataType: "text" }
	, { fieldName: "prodCode", dataType: "text" }
	, { fieldName: "prodName", dataType: "text" }
	, { fieldName: "gcommName", dataType: "text" }
	, { fieldName: "ucommName", dataType: "text" }
	, { fieldName: "opDate", dataType: "text" }
	, { fieldName: "opUprice", dataType: "int" }
	, { fieldName: "opSp", dataType: "int" }
	, { fieldName: "opVat", dataType: "int" }
	, { fieldName: "opSumcost", dataType: "int" }
	]

var columns1 = [
	  { name: "icodeName", fieldName: "icodeName", type: "data", width: "80", header: { text: "품목계정" } }
	, { name: "prodCode", fieldName: "prodCode", type: "data", width: "80", header: { text: "품목코드" } }
	, { name: "prodName", fieldName: "prodName", type: "data", width: "194", header: { text: "품명" } , styleName: "left-column"}
	, { name: "gcommName", fieldName: "gcommName", type: "data", width: "70", header: { text: "규격" } , "visible" : false }
	, { name: "ucommName", fieldName: "ucommName", type: "data", width: "60", header: { text: "단위" } , "visible" : false }
	, { name: "opDate", fieldName: "opDate", type: "data", width: "100", header: { text: "납기일" } , footer: {text: "합계 =>"} }
	, { name: "opUprice", fieldName: "opUprice", type: "data", width: "82", header: { text: "단가" } , footer: {expression : "sum", numberFormat: "#,##0" }, styleName: "right-column"}
	, { name: "opSp", fieldName: "opSp", type: "data", width: "82", header: { text: "공급가액" } , footer: {expression : "sum", numberFormat: "#,##0" }, styleName: "right-column"}
	, { name: "opVat", fieldName: "opVat", type: "data", width: "82", header: { text: "부가세" } , footer: {expression : "sum", numberFormat: "#,##0" }, styleName: "right-column"}
	, { name: "opSumcost", fieldName: "opSumcost", type: "data", width: "82", header: { text: "합계금액" } , footer: {expression : "sum", numberFormat: "#,##0" },styleName: "right-column"}
	]

	initObApp();
	
	function initObApp(){
		let obSearchForm = $("#obApplySearchForm").ajaxForm({
			dataType : "json",
			success : function(resp) {
				obApplyMasterProvider.fillJsonData(resp, {fillMode: "set"});
			}
		}).submit();
	};

	$("#selectOb").on("click",function(){
		var startDate = $("#obStartDate").val();
		var endDate = $("#obEndDate").val();
		// 날짜 지정하지 않았을 때
		if(startDate.replace(/\s/g,"").length==0){
			toastr.error("날짜를 선택하세요");
			return false;
		}
		if(endDate >= startDate){
			let obSearchForm = $("#obApplySearchForm").ajaxForm({
				dataType : "json",
				success : function(resp) {
					obApplyMasterProvider.fillJsonData(resp, {fillMode: "set"});
				}
			}).submit();
		}else{
			toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
			return false;
		}
	});

	function detailGetOb(masterRow) {
		  detailProvider.clearRows();
		  if (masterRow >= 0) {
		      var mstKey = obApplyMasterProvider.getValue(masterRow, "orbNum");
		     $.ajax({
				url : $.getContextPath()+"/produce/piOrderBookRawApply.do",
				data : {"orbNum" : mstKey},
				method : "post",
				dataType : "json",
				success : function(resp) {
					obApplyDetailProvider.fillJsonData(resp, {fillMode: "set"});
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			});
		  };
	};
	
	// 거래처 
	$("#buyerName").focus(function(e){
        $("#buyer-modal").css('display', 'flex')
        buyerObj = this;
	});
	
	// 수주서 적용
	$("#addObApply").on("click",function(){
		obApplyMasterGrid.commit();
		 let checkedRows = obApplyMasterGrid.getCheckedRows();
		 if(checkedRows.length > 0){
			 obApplyMasterProvider.setRowStates(checkedRows, "none", true);
			 var dataList = [];	// 체크된 행 데이터 넣을 배열
			 for(var checked = 0 ;  checked < checkedRows.length ; checked++){
		            data = obApplyMasterGrid.getValues(checkedRows[checked]);
		            dataList.push(data);
			 }	// for문 끝
			 let obj = {"commonList":dataList}
			$.ajax({
				url : $.getContextPath()+"/produce/piOrderBookApplyInsert.do",
				data : JSON.stringify(obj),
				method : "post",
				dataType : "json",
				contentType : "application/json",
				success : function(resp) {
					$("#orderbookApplyForPI-modal").css("display", "none");
					if(resp.result == "OK"){
						toastr.success(resp.message);
						let obSearchForm = $("#searchForm").ajaxForm({
							dataType : "json",
							success : function(resp) {
								masterProvider.fillJsonData(resp, {fillMode: "set"});
							}
						}).submit();
					}else{
						if(Array.isArray(resp.message)){
							for(var idx in resp.message){
								toastr.error(resp.message[idx]);
							}
						}else{
							toastr.error(resp.message);
						}
					}	
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
var obApplyMasterProvider, obApplyMasterGrid, obApplyDetailProvider, obApplyDetailGrid;

/* 			기본 셋팅 obApplyMaster	 	*/
function createobApplyMasterGrid() {
	
	obApplyMasterProvider = new RealGrid.LocalDataProvider();
	obApplyMasterGrid = new RealGrid.GridView("realgrid_obApplyForPIMaster");

	obApplyMasterGrid.setDataSource(obApplyMasterProvider);
	obApplyMasterProvider.setFields(fields);
	obApplyMasterGrid.setColumns(columns);
	obApplyMasterGrid.resetCurrent();

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	obApplyMasterGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // obApplyMasterGrid 속성값
	obApplyMasterGrid.header.height = 40;
	obApplyMasterGrid.footer.height = 40;
	obApplyMasterGrid.stateBar.width = 10;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	obApplyMasterGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : false
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  , enterToTab : true
	  , forceAppend:true
	  , fitStyle : "even"
  });
	
	obApplyMasterGrid.setFooters({visible: false});
  
  // 클릭한 곳의 Row의 넘버를 넘겨줌.
  obApplyMasterGrid.onCurrentRowChanged = function (grid, oldRow, newRow) {
    detailGetOb(newRow);
  };
  
}  
  
  /* 		기본 셋팅 obApplyDetail	 	*/
  function createobApplyDetailGrid() {
	  obApplyDetailProvider = new RealGrid.LocalDataProvider();
	  obApplyDetailGrid = new RealGrid.GridView("realgrid_obookApplyForPIDetail");

	  obApplyDetailGrid.setDataSource(obApplyDetailProvider);
	  obApplyDetailProvider.setFields(fields1);
	  obApplyDetailGrid.setColumns(columns1);

    // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	  obApplyDetailGrid.setDisplayOptions({
  	  emptyMessage : "표시할 데이타가 없습니다."
  	  , showEmptyMessage: true
  	  , rowHeight : 30
  	  , fitStyle : "even"
  	  });

    
    // obApplyMasterGrid 속성값
	  obApplyDetailGrid.header.height = 40;
	  obApplyDetailGrid.footer.height = 40;
	  obApplyDetailGrid.stateBar.width = 10;
	  obApplyDetailGrid.setCheckBar({visible: false});
	  obApplyDetailGrid.setEditOptions({
		  insertable : true
		  , appendable : true
		  , editable : false
		  , deletable: true
		  , confirmWhenDelete : true
		  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	      , fitStyle : "even"
	  });
    
	  setTimeout(function(){
		  obApplyMasterGrid.resetCurrent();
	  },300);
  
  }

function start() {
  createobApplyMasterGrid();
  createobApplyDetailGrid();
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
	obApplyMasterGrid.stateBar.visible = false;
	obApplyDetailGrid.stateBar.visible = false;
});