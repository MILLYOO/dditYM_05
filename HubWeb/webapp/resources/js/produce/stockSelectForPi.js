/**
 * 생산지시서-현재고
 */


//모달 닫기
$("#stockForPI-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#stockForPI-modal").css("display", "none")
	}
});  

//필드 컬럼 설정
var fields = [
		{ fieldName: "rawsCode", dataType: "text" }
	  , { fieldName: "rawsName", dataType: "text" }
	  , { fieldName: "gcommName", dataType: "text" }
	  , { fieldName: "rawsQty", dataType: "text" }
	  , { fieldName: "warName", dataType: "text" }
	  , { fieldName: "wrQty", dataType: "int" }
	]
	
var columns = [
	  { name: "rawsCode", fieldName: "rawsCode", type: "data", width: "80", header: { text: "품목코드" } }
	, { name: "rawsName", fieldName: "rawsName", type: "data", width: "445", header: { text: "품목명" } , styleName: "left-column"}
	, { name: "gcommName", fieldName: "gcommName", type: "data", width: "102", header: { text: "규격" } }
	, { name: "rawsQty", fieldName: "rawsQty", type: "data", width: "81", header: { text: "품목수량" } , styleName: "right-column"}
	, { name: "warName", fieldName: "warName", type: "data", width: "82", header: { text: "창고이름" } }
	, { name: "wrQty", fieldName: "wrQty", type: "data", width: "81", header: { text: "창고수량" } , footer: {expression : "sum", numberFormat: "#,##0"} , styleName: "right-column"}
	]

init();

function init(){
	let stSearchForm = $("#stockSelectForm").ajaxForm({
		dataType : "json",
		success : function(resp) {
			stSelectMasterProvider.fillJsonData(resp, {fillMode: "set"});
		}
	}).submit();
}

// 조회버튼
$("#selectSt").on("click",function(){
	let stSearchForm = $("#stockSelectForm").ajaxForm({
		dataType : "json",
		success : function(resp) {
			stSelectMasterProvider.fillJsonData(resp, {fillMode: "set"});
		}
	}).submit();
});


// 품목 모달
$("input[name=rawsCode]").focus(function(e){
    $("#item-modal").css('display', 'flex')
    itemCodeObj = this;
});

	
//리얼그리드 공통부분
var stSelectMasterProvider, stSelectMasterGrid;

/* 			기본 셋팅 Master	 	*/
function createStSelectMasterGrid() {
	
	stSelectMasterProvider = new RealGrid.LocalDataProvider();
	stSelectMasterGrid = new RealGrid.GridView("realgrid_stockSelectForPI");

	stSelectMasterGrid.setDataSource(stSelectMasterProvider);
	stSelectMasterProvider.setFields(fields);
	stSelectMasterGrid.setColumns(columns);
	stSelectMasterGrid.resetCurrent();

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	stSelectMasterGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // MasterGrid 속성값
	stSelectMasterGrid.header.height = 40;
	stSelectMasterGrid.footer.height = 40;
	stSelectMasterGrid.stateBar.width = 10;
	stSelectMasterGrid.setCheckBar({visible: false});
  // gridViewEditOptions 그리드뷰 edit 옵션
	stSelectMasterGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : false
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  , enterToTab : true
	  ,forceAppend:true
  });
  
}  
  


function start() {
	createStSelectMasterGrid();
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
	stSelectMasterGrid.stateBar.visible = false;
});