/**
 * 발주서 적용
 */

//모달 열기
var date = new Date();
$("#ioStartDate").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
$("#ioEndDate").val(new Date().toISOString().substring(0, 10));

//모달닫기
$("#orderApplyForInc-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#orderApplyForInc-modal").css("display", "none")
	}
});  

//필드 컬럼 설정
var fields = [
	{ fieldName: "ordNum", dataType: "text" }
  , { fieldName: "ordDate", dataType: "text" }
  , { fieldName: "buyerName", dataType: "text" }
  , { fieldName: "ordVat", dataType: "text" }
]

var columns = [
	{ name: "ordNum", fieldName: "ordNum", type: "data", width: "150", header: { text: "발주서 번호" } , "editable" : false}
  , { name: "ordDate", fieldName: "ordDate", type: "data", width: "250", header: { text: "일자" } , "editable" : false}
  , { name: "buyerName", fieldName: "buyerName", type: "data", width: "350", header: { text: "거래처" } , "editable" : false}
  , { name: "ordVat", fieldName: "ordVat", type: "data", width: "235", header: { text: "VAT여부" } , "editable" : false, "visible":false  }
]

var fields1 = [
	  { fieldName: "icodeName", dataType: "text" }
	, { fieldName: "rawsCode", dataType: "text" }
	, { fieldName: "rawsName", dataType: "text" }
	, { fieldName: "gcommName", dataType: "text" }
	, { fieldName: "ucommName", dataType: "text" }
	, { fieldName: "orrDate", dataType: "text" }
	, { fieldName: "orrQty", dataType: "number" }
	, { fieldName: "orrUprice", dataType: "number" }
	, { fieldName: "orrSp", dataType: "number" }
	, { fieldName: "orrVat", dataType: "number" }
	, { fieldName: "orrSumcost", dataType: "number" }
]

var columns1 = [
	  { name: "icodeName", fieldName: "icodeName", type: "data", width: "150", header: { text: "품목계정" }, "editable" : false}
	, { name: "rawsCode", fieldName: "rawsCode", type: "data", width: "120", header: { text: "품목코드" }, "editable" : false}
	, { name: "rawsName", fieldName: "rawsName", type: "data", width: "230", header: { text: "품명" }, styleName: "left-column", "editable" : false}
	, { name: "gcommName", fieldName: "gcommName", type: "data", width: "160", header: { text: "규격" } , "editable" : false}
	, { name: "ucommName", fieldName: "ucommName", type: "data", width: "95", header: { text: "단위" } , "editable" : false}
	, { name: "orrDate", fieldName: "orrDate", type: "data", width: "130", header: { text: "납기일" } , "editable" : false, footer: {"styles": {"textAlignment": "far","font": "굴림,12"}, "text": "합계 =>"} }
	, { name: "orrQty", fieldName: "orrQty", type: "data", width: "95",  numberFormat: "#,##0" ,header: { text: "수량" }, styleName: "right-column", "editable" : false, footer: {expression : "sum", numberFormat: "#,##0"}, styleName: "right-column"}
	, { name: "orrUprice", fieldName: "orrUprice", type: "data", width: "95",  numberFormat: "#,##0" ,header: { text: "단가" }, styleName: "right-column", "editable" : false, footer: {expression : "sum", numberFormat: "#,##0"}, styleName: "right-column"}
	, { name: "orrSp", fieldName: "orrSp", type: "data", width: "95", numberFormat: "#,##0" , header: { text: "공급가액" }, styleName: "right-column", "editable" : false, footer: {expression : "sum", numberFormat: "#,##0"}, styleName: "right-column"}
	, { name: "orrVat", fieldName: "orrVat", type: "data", width: "95", numberFormat: "#,##0" , header: { text: "부가세" }, styleName: "right-column", "editable" : false, footer: {expression : "sum", numberFormat: "#,##0"}, styleName: "right-column"}
	, { name: "orrSumcost", fieldName: "orrSumcost", type: "data", width: "97",  numberFormat: "#,##0" ,header: { text: "합계금액" }, styleName: "right-column", "editable" : false, footer: {expression : "sum", numberFormat: "#,##0"}, styleName: "right-column"}
]

$("#selectio").on("click", function() {
	
	var startDate = $("#ioStartDate").val();
	var endDate = $("#ioEndDate").val();	
	
	// 날짜 지정하지 않았을 때
	if(startDate.replace(/\s/g,"").length==0){
//		Swal.fire("날짜를 선택하세요");
		toastr.success('날짜를 선택하세요')
		return false;
	}
	if(endDate >= startDate){
		let ioSearchForm = $("#ioApplySearchForm").ajaxForm({
			dataType : "json",
			success : function(resp) {
				ioApplyMasterProvider.fillJsonData(resp, {fillMode: "set"});
			}
		}).submit();
	}else{
//		Swal.fire("종료일이 시작일보다 이전 날짜 입니다.");
		toastr.success('종료일이 시작일보다 이전 날짜 입니다.')
		return false;
	}
});

function detailGetIo(masterRow) {
	  detailProvider.clearRows();
	  if (masterRow >= 0) {
	      var mstKey = ioApplyMasterProvider.getValue(masterRow, "ordNum");
	      console.log("::ordNum::"+mstKey);
	     $.ajax({
			url : $.getContextPath()+"/buy/incOrderRawsApply.do",
			data : {"ordNum" : mstKey},
			method : "post",
			dataType : "json",
			success : function(resp) {
				ioApplyDetailProvider.fillJsonData(resp, {fillMode: "set"});
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	  };
};

//거래처
//$("#buyerName").focus(function(e){
//  $("#buyer-modal").css('display', 'flex')
//});

//발주서적용
$("#applyio").on("click",function(){
	ioApplyMasterGrid.commit();
	 let checkedRows = ioApplyMasterGrid.getCheckedRows();
	 if(checkedRows.length > 0){
		 ioApplyMasterProvider.setRowStates(checkedRows, "none", true);
		 
		 var dataList = [];	// 체크된 행 데이터 넣을 배열

		 for(var checked = 0 ;  checked < checkedRows.length ; checked++){
	            data = ioApplyMasterGrid.getValues(checkedRows[checked]);
	            dataList.push(data);
		 }//for문 끝
		 let obj = {"commonList":dataList}
					$.ajax({
						url : $.getContextPath()+"/buy/incOrderApplyInsert.do",
						data : JSON.stringify(obj),
						method : "post",
						dataType : "json",
						contentType : "application/json",
						success : function(resp) {
							if(data.ordDate==null){
//								$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
//								$("#dateEnd").val(new Date().toISOString().substring(0, 10));
							}else{
//								$("#dateStart").val(data.ordDate);
//								$("#dateEnd").val(data.ordDate);
							}
							
							$("#orderApplyForInc-modal").css("display", "none")
							
							if(resp=="실패"){
								Swal.fire("발주서에 품목이 존재하지 않아 적용할 수 없습니다.");
							}
							
							let ioSearchForm = $("#searchForm").ajaxForm({
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
//			Swal.fire("");
			toastr.error('체크하고 적용해주세요')
	 }	
});

//리얼그리드 공통부분
var ioApplyMasterProvider, ioApplyMasterGrid, ioApplyDetailProvider, ioApplyDetailGrid;

/* 			기본 셋팅 ioApplyMaster	 	*/
function createioApplyMasterGrid() {
	
	ioApplyMasterProvider = new RealGrid.LocalDataProvider();
	ioApplyMasterGrid = new RealGrid.GridView("realgrid_ioApplyForIncMaster"); //jsp에 그 모달창띄우는 곳의 리얼그리드를 의미하는 거야

	ioApplyMasterGrid.setDataSource(ioApplyMasterProvider);
	ioApplyMasterProvider.setFields(fields);
	ioApplyMasterGrid.setColumns(columns);
	ioApplyMasterGrid.resetCurrent();

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	ioApplyMasterGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  , fitStyle : "even"
	  });
  
  // ioApplyMasterGrid 속성값
	ioApplyMasterGrid.header.height = 40;
	ioApplyMasterGrid.footer.height = 40;
	ioApplyMasterGrid.stateBar.width = 10;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	ioApplyMasterGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : false
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  , enterToTab : true
	  ,forceAppend:true
	  , fitStyle : "even" 
  });
  
  // 클릭한 곳의 Row의 넘버를 넘겨줌.
  ioApplyMasterGrid.onCurrentRowChanged = function (grid, oldRow, newRow) {
    detailGetIo(newRow);
  };
  
}  
  
  /* 		기본 셋팅 ioApplyDetail	 	*/
  function createioApplyDetailGrid() {
	  ioApplyDetailProvider = new RealGrid.LocalDataProvider();
	  ioApplyDetailGrid = new RealGrid.GridView("realgrid_ioApplyForIncDetail");

	  ioApplyDetailGrid.setDataSource(ioApplyDetailProvider);
	  ioApplyDetailProvider.setFields(fields1);
	  ioApplyDetailGrid.setColumns(columns1);

    // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	  ioApplyDetailGrid.setDisplayOptions({
  	  emptyMessage : "표시할 데이타가 없습니다."
  	  , showEmptyMessage: true
  	  , rowHeight : 30
  	  });

    
    // ioApplyDetailGrid 속성값
	  ioApplyDetailGrid.header.height = 40;
	  ioApplyDetailGrid.footer.height = 40;
	  ioApplyDetailGrid.stateBar.width = 10;
	  ioApplyDetailGrid.setCheckBar({visible: false}); //디테일부분에 체크박스 없애주는 거
	  ioApplyDetailGrid.setEditOptions({
		  insertable : true
		  , appendable : true
		  , editable : true
		  , deletable: true
		  , confirmWhenDelete : true
		  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	      , fitStyle : "even" 
	  });
    
	  
	  setTimeout(function(){
		  ioApplyMasterGrid.resetCurrent();
	  },300);
  
  }



function start() {
  createioApplyMasterGrid();
  createioApplyDetailGrid();
}

window.onload = start();
window.onunload = function() {
	  dataProvider.clearRows();

	  gridView.destroy();
	  dataProvider.destroy();

	  gridView = null;
	  dataProvider = null;
}
