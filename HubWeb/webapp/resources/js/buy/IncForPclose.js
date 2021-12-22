/**
 *매입마감서에 입고처리서 적용하기!!!!!!
 */
var date = new Date();
$("#ipStartDate").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
$("#ipEndDate").val(new Date().toISOString().substring(0, 10));

//모달 바깥쪽 누르면 꺼지게
$("#IncApplyForPclose-modal").click(function(e){ //클릭한 위치를 e에 넣고
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) { //
		$("#IncApplyForPclose-modal").css("display", "none")
	}
});  


//필드 컬럼 설정
var fields = [  
  { fieldName: "buyYy", dataType: "text" }
, { fieldName: "buyMm", dataType: "text" }
, { fieldName: "buyDd", dataType: "text" }		
, { fieldName: "incNum", dataType: "text" }
, { fieldName: "incDate", dataType: "text" }
, { fieldName: "buyerName", dataType: "text" }
, { fieldName: "incVat", dataType: "text" }
]

var columns = [
//  { name: "buyYy", fieldName: "buyYy", type: "data", width: "60", header: { text: "년" }, "editable" : false}
//, { name: "buyMm", fieldName: "buyMm", type: "data", width: "30", header: { text: "월" }, "editable" : false}
//, { name: "buyDd", fieldName: "buyDd", type: "data", width: "30", header: { text: "일" }, "editable" : false}	
 { name: "incNum", fieldName: "incNum", type: "data", width: "360", header: { text: "입고번호" }, "editable" : false}
, { name: "incDate", fieldName: "incDate", type: "data", width: "360", header: { text: "일자" }, "editable" : false}
, { name: "buyerName", fieldName: "buyerName", type: "data", width: "300", header: { text: "거래처" }, "editable" : false}
, { name: "incVat", fieldName: "incVat", type: "data", width: "250", header: { text: "VAT여부" }, "editable" : false, "visible" : false }
]

var fields1 = [
	  { fieldName: "icodeName", dataType: "text" }
	, { fieldName: "rawsCode", dataType: "text" }
	, { fieldName: "rawsName", dataType: "text" }
	, { fieldName: "gcommName", dataType: "text" }
	, { fieldName: "ucommName", dataType: "text" }
	, { fieldName: "irDate", dataType: "text" }
	, { fieldName: "irQty", dataType: "text" }
	, { fieldName: "irUprice", dataType: "text" }
	, { fieldName: "irSp", dataType: "text" }
	, { fieldName: "irVat", dataType: "text" }
	, { fieldName: "irSumcost", dataType: "text" }
	, { fieldName: "warName", dataType: "text" }
	, { fieldName: "projName", dataType: "text" }
	]

var columns1 = [
	  { name: "icodeName", fieldName: "icodeName", type: "data", width: "120", header: { text: "품목계정" } , "editable" : false, "visible" : false }
	, { name: "rawsCode", fieldName: "rawsCode", type: "data", width: "100", header: { text: "품목코드" } , "editable" : false}
	, { name: "rawsName", fieldName: "rawsName", type: "data", width: "220", header: { text: "품명" } , styleName: "left-column", "editable" : false}
	, { name: "gcommName", fieldName: "gcommName", type: "data", width: "180", header: { text: "규격" } , "editable" : false}
	, { name: "ucommName", fieldName: "ucommName", type: "data", width: "70", header: { text: "단위" }, "editable" : false}
	, { name: "irDate", fieldName: "irDate", type: "data", width: "120", header: { text: "납기일" }, "editable" : false}
	, { name: "irQty", fieldName: "irQty", type: "data", width: "70",numberFormat: "#,##0" , header: { text: "수량" }, "editable" : false}
	, { name: "irUprice", fieldName: "irUprice", type: "data", width: "101",numberFormat: "#,##0" , header: { text: "단가" } , "editable" : false}
	, { name: "irSp", fieldName: "irSp", type: "data", width: "101", numberFormat: "#,##0" ,header: { text: "공급가액" } , "editable" : false}
	, { name: "irVat", fieldName: "irVat", type: "data", width: "101", numberFormat: "#,##0" ,header: { text: "부가세" }, "editable" : false}
	, { name: "irSumcost", fieldName: "irSumcost", type: "data", width: "101", numberFormat: "#,##0" ,header: { text: "합계금액" }, "editable" : false}
	, { name: "warName", fieldName: "warName", type: "data", width: "150", header: { text: "창고" }, "editable" : false}
	, { name: "projName", fieldName: "projName", type: "data", width: "110", header: { text: "프로젝트" } , "editable" : false}
]

	initIncApp();

	function initIncApp() {
		let ipSearchForm = $("#incApplySearchForm").ajaxForm({
			dataType: "json",
			success: function(resp) {
				ipApplyMasterProvider.fillJsonData(resp, {fillMode: "set"});
			}
		}).submit();
	};



$("#selectip").on("click", function() {
	
	var startDate = $("#ipStartDate").val();
	var endDate = $("#ipEndDate").val();	
	
	// 날짜 지정하지 않았을 때
	if(startDate.replace(/\s/g,"").length==0){
		Swal.fire("날짜를 선택하세요");
		return false;
	}
	if(endDate >= startDate){
		let ipSearchForm = $("#incApplySearchForm").ajaxForm({
			dataType : "json",
			success : function(resp) {
				ipApplyMasterProvider.fillJsonData(resp, {fillMode: "set"});
			}
		}).submit();
	}else{
		Swal.fire("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
});

function detailGetIp(masterRow) {
	  detailProvider.clearRows();
	  if (masterRow >= 0) {
	      var mstKey = ipApplyMasterProvider.getValue(masterRow, "incNum");
	      console.log("::incNum::"+mstKey);
	     $.ajax({
			url : $.getContextPath()+"/buy/pCIncomingRawsApply.do",
			data : {"incNum" : mstKey},
			method : "post",
			dataType : "json",
			success : function(resp) {
				ipApplyDetailProvider.fillJsonData(resp, {fillMode: "set"});
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	  };
};


//입고처리서 적용
$("#applyip").on("click",function(){
	ipApplyMasterGrid.commit();
	 let checkedRows = ipApplyMasterGrid.getCheckedRows();
	 if(checkedRows.length > 0){
		 ipApplyMasterProvider.setRowStates(checkedRows, "none", true);
		
		 var dataList = [];	// 체크된 행 데이터 넣을 배열

		 for(var checked = 0 ;  checked < checkedRows.length ; checked++){
			        data = ipApplyMasterGrid.getValues(checkedRows[checked]);
			        dataList.push(data);
		 }//for문 끝
		 let obj = {"commonList":dataList}
					$.ajax({
						url : $.getContextPath()+"/buy/pCIncomingApplyInsert.do",
						data : JSON.stringify(obj),
						method : "post",
						dataType : "json",
						contentType : "application/json",
						success : function(resp) {
							$("#IncApplyForPclose-modal").css("display", "none")
							if(resp.result == "OK"){
								toastr.success(resp.message);
								let ipSearchForm = $(".searchForm").ajaxForm({
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
var ipApplyMasterProvider, ipApplyMasterGrid, ipApplyDetailProvider, ipApplyDetailGrid;

/* 			기본 셋팅 ipApplyMaster	 	*/
function createipApplyMasterGrid() {
	
	ipApplyMasterProvider = new RealGrid.LocalDataProvider();
	ipApplyMasterGrid = new RealGrid.GridView("realgrid_incApplyForPcloseMaster"); //jsp에 그 모달창띄우는 곳의 리얼그리드를 의미하는 거야

	ipApplyMasterGrid.setDataSource(ipApplyMasterProvider);
	ipApplyMasterProvider.setFields(fields);
	ipApplyMasterGrid.setColumns(columns);
	ipApplyMasterGrid.resetCurrent();

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	ipApplyMasterGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  , fitStyle : "even"
	  });
  
  // ipApplyMasterGrid 속성값
	ipApplyMasterGrid.header.height = 40;
	ipApplyMasterGrid.footer.height = 40;
	ipApplyMasterGrid.stateBar.width = 10;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	ipApplyMasterGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : false
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  , enterToTab : true
	  ,forceAppend:true
	  , fitStyle : "even" //크게크게 넣어주면 적당히 비율로 맞춰줌. 
  });
  
	ipApplyMasterGrid.setFooters({visible: false});
	
//   클릭한 곳의 Row의 넘버를 넘겨줌.
  ipApplyMasterGrid.onCurrentRowChanged = function (grid, oldRow, newRow) {
    detailGetIp(newRow);
  };
}  

/* 		기본 셋팅 ipApplyDetail	 	*/
function createipApplyDetailGrid() {
	  ipApplyDetailProvider = new RealGrid.LocalDataProvider();
	  ipApplyDetailGrid = new RealGrid.GridView("realgrid_incApplyForPcloseDetail");

	  ipApplyDetailGrid.setDataSource(ipApplyDetailProvider);
	  ipApplyDetailProvider.setFields(fields1);
	  ipApplyDetailGrid.setColumns(columns1);

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	  ipApplyDetailGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });

  
  // ipApplyDetailGrid 속성값
	  ipApplyDetailGrid.header.height = 40;
	  ipApplyDetailGrid.footer.height = 40;
	  ipApplyDetailGrid.stateBar.width = 10;
	  ipApplyDetailGrid.setCheckBar({visible: false}); //디테일부분에 체크박스 없애주는 거
	  ipApplyDetailGrid.setEditOptions({
		  insertable : true
		  , appendable : true
		  , editable : true
		  , deletable: true
		  , confirmWhenDelete : true
		  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
		  , fitStyle : "even"
	  });
  
	  
	  setTimeout(function(){
		  ipApplyMasterGrid.resetCurrent();
	  },300);

}


function start() {
  createipApplyMasterGrid();
  createipApplyDetailGrid();
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
	ipApplyMasterGrid.stateBar.visible = false;
	ipApplyDetailGrid.stateBar.visible = false;
});