// 이달 1일 ~ 현재 날짜 세팅
var date = new Date();
$(".dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
$(".dateEnd").val(new Date().toISOString().substring(0, 10));

	var masterGrid, detailGrid;

	//거래처별
	var masterPCProvider, masterPCGrid;
	var detailPCProvider, detailPCGrid;

	//부서별
	var masterDPProvider, masterDPGrid;
	var detailDPProvider, detailDPGrid;	
	
	//사원별
	var masterEMPProvider, masterEMPGrid;
	var detailEMPProvider, detailEMPGrid;	

//================================================================================================================	
	
//거래처별 조회버튼
$("#selectPC").on("click",function(){
    
	var startDate = $(".dateStart").val();
	var endDate = $(".dateEnd").val();
	
	// 날짜 지정하지 않았을 때
	if(startDate.replace(/\s/g,"").length==0){
		toastr.error("날짜를 선택하세요");
		return false;
	}
	
	if(endDate >= startDate){
		let searchFormPC = $("#searchFormPC").ajaxForm({
			dataType : "json",
			success : function(resp) {
				masterPCProvider.fillJsonData(resp, {fillMode: "set"});
			}
		}).submit();
	}else{
		toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
});

//부서별 조회버튼
$("#selectDP").on("click",function(){
    
	var startDate = $(".dateStart").val();
	var endDate = $(".dateEnd").val();
	
	// 날짜 지정하지 않았을 때
	if(startDate.replace(/\s/g,"").length==0){
		toastr.error("날짜를 선택하세요");
		return false;
	}
	
	if(endDate >= startDate){
		let searchFormDP = $("#searchFormDP").ajaxForm({
			dataType : "json",
			success : function(resp) {
				masterDPProvider.fillJsonData(resp, {fillMode: "set"});
			}
		}).submit();
	}else{
		toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
});

//사원별 조회버튼
$("#selectEMP").on("click",function(){
	
	var startDate = $(".dateStart").val();
	var endDate = $(".dateEnd").val();
	
	// 날짜 지정하지 않았을 때
	if(startDate.replace(/\s/g,"").length==0){
		toastr.error("날짜를 선택하세요");
		return false;
	}
	
	if(endDate >= startDate){
		let searchFormEMP = $("#searchFormEMP").ajaxForm({
			dataType : "json",
			success : function(resp) {
				masterEMPProvider.fillJsonData(resp, {fillMode: "set"});
			}
		}).submit();
	}else{
		toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
});

//================================================================================================================

//거래처별 detailGrid 조회
function detailGetPC(masterRow) {
	  detailPCProvider.clearRows();
	  if (masterRow >= 0) {
	      var mstKey = masterPCProvider.getValue(masterRow, "buyerName");
	     $.ajax({
			url : $.getContextPath()+"/buy/purchRankRawsListPC.do",
			data : {"buyerName" : mstKey},
			method : "post",
			dataType : "json",
			success : function(resp) {
			  detailPCProvider.fillJsonData(resp, {fillMode: "set"});
			  
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	  };
};

//부서별 detailGrid 조회
function detailGetDP(masterRow) {
	detailDPProvider.clearRows();
	if (masterRow >= 0) {
		var mstKey = masterDPProvider.getValue(masterRow, "deptName");
		$.ajax({
			url : $.getContextPath()+"/buy/purchRankRawsListDP.do",
			data : {"deptName" : mstKey},
			method : "post",
			dataType : "json",
			success : function(resp) {
				detailDPProvider.fillJsonData(resp, {fillMode: "set"});
				
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	};
};

//사원별 detailGrid 조회
function detailGetEMP(masterRow) {
	detailEMPProvider.clearRows();
	if (masterRow >= 0) {
		var mstKey = masterEMPProvider.getValue(masterRow, "empName");
		$.ajax({
			url : $.getContextPath()+"/buy/purchRankRawsListEMP.do",
			data : {"empName" : mstKey},
			method : "post",
			dataType : "json",
			success : function(resp) {
				detailEMPProvider.fillJsonData(resp, {fillMode: "set"});
				
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	};
};
	
//===================================================거래처별=============================================================================	

	var fieldsPC = [
		    { fieldName: "rowNum", dataType: "text" }
		  , { fieldName: "buyerName", dataType: "text" }
		  , { fieldName: "pcrQty", dataType: "number" }
		  , { fieldName: "prcSp", dataType: "number" }		
		  , { fieldName: "prcVat", dataType: "number" }		
		  , { fieldName: "prcSumcost", dataType: "number" }
		  , { fieldName: "prcRatio", dataType: "text" }
		];

		var columnsPC = [
		    { name: "rowNum", fieldName: "rowNum", type: "data", width: "40", header: { text: "순위" } }
	      , { name: "buyerName", fieldName: "buyerName", type: "data", width: "100", header: { text: "견적처" } }	
	      , { name: "pcrQty", fieldName: "pcrQty", type: "data", width: "90", numberFormat: "#,##0", styleName: "right-column",header: { text: "입고수량" }, "editable" : false}	
		  , { name: "prcSp", fieldName: "prcSp", type: "data", width: "90", numberFormat: "#,##0", styleName: "right-column",header: { text: "공급가액" }, "editable" : false }
		  , { name: "prcVat", fieldName: "prcVat", type: "data", width: "90", numberFormat: "#,##0", styleName: "right-column",header: { text: "부가세" }, "editable" : false  }
		  , { name: "prcSumcost", fieldName: "prcSumcost", type: "data", width: "90", numberFormat: "#,##0", styleName: "right-column",header: { text: "합계금액" }, "editable" : false  }
		  , { name: "prcRatio", fieldName: "prcRatio", type: "data", width: "90", header: { text: "점유율" } , "editable" : false }
		];
		
		var fields1 = [
			  { fieldName: "icodeName", dataType: "text" }
			, { fieldName: "rawsName", dataType: "text" }
			, { fieldName: "gcommName", dataType: "text" }
			, { fieldName: "ucommName", dataType: "text" }		
			, { fieldName: "pcrQty", dataType: "number" }		
			, { fieldName: "prcSp", dataType: "number" }
			, { fieldName: "prcVat", dataType: "number" }
			, { fieldName: "prcSumcost", dataType: "number" }
			, { fieldName: "prcRatio", dataType: "text" }
			];
		
		var columns1 = [
			  { name: "icodeName", fieldName: "icodeName", type: "data", width: "48", header: { text: "품목계정" } }
			, { name: "rawsName", fieldName: "rawsName", type: "data", width: "67", header: { text: "품명" } }	
			, { name: "gcommName", fieldName: "gcommName", type: "data", width: "50", header: { text: "규격" }, "editable" : false}	
			, { name: "ucommName", fieldName: "ucommName", type: "data", width: "33", header: { text: "단위" }, "editable" : false }
			, { name: "pcrQty", fieldName: "pcrQty", type: "data", width: "49", numberFormat: "#,##0", styleName: "right-column",header: { text: "입고수량" }, "editable" : false  }
			, { name: "prcSp", fieldName: "prcSp", type: "data", width: "53", numberFormat: "#,##0", styleName: "right-column",header: { text: "공급가액" }, "editable" : false  }
			, { name: "prcVat", fieldName: "prcVat", type: "data", width: "43", numberFormat: "#,##0", styleName: "right-column",header: { text: "부가세" } , "editable" : false }
			, { name: "prcSumcost", fieldName: "prcSumcost", type: "data", width: "60", numberFormat: "#,##0", styleName: "right-column",header: { text: "합계금액" }, "editable" : false  }
			, { name: "prcRatio", fieldName: "prcRatio", type: "data", width: "40", header: { text: "점유율" } , "editable" : false }
			];

//===================================================부서별=============================================================================			
		
		var fieldsDP = [
		    { fieldName: "rowNum", dataType: "text" }
		  , { fieldName: "deptName", dataType: "text" }
		  , { fieldName: "pcrQty", dataType: "number" }
		  , { fieldName: "prcUprice", dataType: "number" }
		  , { fieldName: "prcSp", dataType: "number" }		
		  , { fieldName: "prcVat", dataType: "number" }		
		  , { fieldName: "prcSumcost", dataType: "number" }
		  , { fieldName: "prcRatio", dataType: "text" }
		];

		var columnsDP = [
   		    { name: "rowNum", fieldName: "rowNum", type: "data", width: "50", header: { text: "순위" } }
	      , { name: "deptName", fieldName: "deptName", type: "data", width: "90", header: { text: "부서" } }
	      , { name: "pcrQty", fieldName: "pcrQty", type: "data", width: "90", numberFormat: "#,##0", styleName: "right-column", header: { text: "입고수량" } }	
	      , { name: "prcUprice", fieldName: "prcUprice", type: "data", width: "90", numberFormat: "#,##0", styleName: "right-column", numberFormat: "#,##0", styleName: "right-column", header: { text: "단가" } }	
		  , { name: "prcSp", fieldName: "prcSp", type: "data", width: "90", numberFormat: "#,##0", styleName: "right-column", numberFormat: "#,##0", styleName: "right-column", header: { text: "공급가액" }, "editable" : false }
		  , { name: "prcVat", fieldName: "prcVat", type: "data", width: "90", numberFormat: "#,##0", styleName: "right-column", numberFormat: "#,##0", styleName: "right-column", header: { text: "부가세" }, "editable" : false  }
		  , { name: "prcSumcost", fieldName: "prcSumcost", type: "data", width: "90", numberFormat: "#,##0", styleName: "right-column", numberFormat: "#,##0", styleName: "right-column", header: { text: "합계금액" }, "editable" : false  }
		  , { name: "prcRatio", fieldName: "prcRatio", type: "data", width: "90", header: { text: "점유율" } , "editable" : false }
		];
		
//===================================================사원별=============================================================================			
		
		var fieldsEMP = [
		    { fieldName: "rowNum", dataType: "text" }
		  , { fieldName: "empName", dataType: "text" }
		  , { fieldName: "pcrQty", dataType: "number" }
		  , { fieldName: "prcSp", dataType: "number" }		
		  , { fieldName: "prcVat", dataType: "number" }		
		  , { fieldName: "prcSumcost", dataType: "number" }
		  , { fieldName: "prcRatio", dataType: "text" }
		];

		var columnsEMP = [
		    { name: "rowNum", fieldName: "rowNum", type: "data", width: "40", header: { text: "순위" } }
	      , { name: "empName", fieldName: "empName", type: "data", width: "90", header: { text: "사원" } }
	      , { name: "pcrQty", fieldName: "pcrQty", type: "data", width: "90", numberFormat: "#,##0", styleName: "right-column",header: { text: "입고수량" } }	
		  , { name: "prcSp", fieldName: "prcSp", type: "data", width: "90", numberFormat: "#,##0", styleName: "right-column",header: { text: "공급가액" }, "editable" : false }
		  , { name: "prcVat", fieldName: "prcVat", type: "data", width: "90", numberFormat: "#,##0", styleName: "right-column",header: { text: "부가세" }, "editable" : false  }
		  , { name: "prcSumcost", fieldName: "prcSumcost", type: "data", width: "90", numberFormat: "#,##0", styleName: "right-column",header: { text: "합계금액" }, "editable" : false  }
		  , { name: "prcRatio", fieldName: "prcRatio", type: "data", width: "90", header: { text: "점유율" } , "editable" : false }
		];		
		
		
		
		
//*****************************************************************************************************************************************************************		
		
		
		
		
//===================================================거래처별=============================================================================
		
	function createmasterPCGrid() {
		  masterPCProvider = new RealGrid.LocalDataProvider();
		  masterPCGrid = new RealGrid.GridView("realgrid_masterPC");

		  masterPCGrid.setDataSource(masterPCProvider);
		  masterPCProvider.setFields(fieldsPC);
		  masterPCGrid.setColumns(columnsPC);
		  masterPCGrid.resetCurrent();
		  masterPCGrid.footer.visible=false;
		  masterPCGrid.displayOptions.fitStyle = "even";
		  
		  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
		  masterPCGrid.setDisplayOptions({
			  emptyMessage : "표시할 데이타가 없습니다."
			  , showEmptyMessage: true
			  , rowHeight : 30
			  });
		  masterPCGrid.setEditOptions({
			    insertable : true
			  , appendable : true
			  , editable : true
			  , deletable: true
			  , confirmWhenDelete : true
			  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
			  , crossWhenExitLast : true
			  , commitWhenExitLast : true
			  , enterToTab : true
			  , commitByCell : true
			  });
		  // masterGrid 속성값
		  masterPCGrid.header.height = 20;
		  masterPCGrid.footer.height = 40;
		  masterPCGrid.setCheckBar({visible: false});
	}	
	
	function createdetailPCGrid() {
		detailPCProvider = new RealGrid.LocalDataProvider();
		detailPCGrid = new RealGrid.GridView("realgrid_detailPC");
		
		detailPCGrid.setDataSource(detailPCProvider);
		detailPCProvider.setFields(fields1);
		detailPCGrid.setColumns(columns1);
		detailPCGrid.resetCurrent();
		detailPCGrid.footer.visible=false;
		detailPCGrid.displayOptions.fitStyle = "even";
			
		// gridViewDisplayOptions 그리드뷰 디스플레이 설정
		detailPCGrid.setDisplayOptions({
			emptyMessage : "표시할 데이타가 없습니다."
				, showEmptyMessage: true
				, rowHeight : 30
		});
		detailPCGrid.setEditOptions({
			insertable : true
			, appendable : true
			, editable : true
			, deletable: true
			, confirmWhenDelete : true
			, deleteRowsMessage : "정말로 삭제 하시겠습니까?"
				, crossWhenExitLast : true
				, commitWhenExitLast : true
				, enterToTab : true
				, commitByCell : true
		});
		// masterGrid 속성값
		detailPCGrid.header.height = 20;
		detailPCGrid.footer.height = 40;
		detailPCGrid.setCheckBar({visible: false});
	}	

//===================================================부서별=============================================================================	
	
	function createmasterDPGrid() {
		  masterDPProvider = new RealGrid.LocalDataProvider();
		  masterDPGrid = new RealGrid.GridView("realgrid_masterDP");

		  masterDPGrid.setDataSource(masterDPProvider);
		  masterDPProvider.setFields(fieldsDP);
		  masterDPGrid.setColumns(columnsDP);
		  masterDPGrid.resetCurrent();
		  masterDPGrid.footer.visible=false;
		  masterDPGrid.displayOptions.fitStyle = "even";
		  
		  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
		  masterDPGrid.setDisplayOptions({
			  emptyMessage : "표시할 데이타가 없습니다."
			  , showEmptyMessage: true
			  , rowHeight : 30
			  });
		  masterDPGrid.setEditOptions({
			    insertable : true
			  , appendable : true
			  , editable : true
			  , deletable: true
			  , confirmWhenDelete : true
			  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
			  , crossWhenExitLast : true
			  , commitWhenExitLast : true
			  , enterToTab : true
			  , commitByCell : true
			  });
		  // masterGrid 속성값
		  masterDPGrid.header.height = 20;
		  masterDPGrid.footer.height = 40;
		  masterDPGrid.setCheckBar({visible: false});
	}	
	
	function createdetailDPGrid() {
		detailDPProvider = new RealGrid.LocalDataProvider();
		detailDPGrid = new RealGrid.GridView("realgrid_detailDP");
		
		detailDPGrid.setDataSource(detailDPProvider);
		detailDPProvider.setFields(fields1);
		detailDPGrid.setColumns(columns1);
		detailDPGrid.resetCurrent();
		detailDPGrid.footer.visible=false;
		detailDPGrid.displayOptions.fitStyle = "even";
		
		// gridViewDisplayOptions 그리드뷰 디스플레이 설정
		detailDPGrid.setDisplayOptions({
			emptyMessage : "표시할 데이타가 없습니다."
				, showEmptyMessage: true
				, rowHeight : 30
		});
		detailDPGrid.setEditOptions({
			  insertable : true
			, appendable : true
			, editable : true
			, deletable: true
			, confirmWhenDelete : true
			, deleteRowsMessage : "정말로 삭제 하시겠습니까?"
				, crossWhenExitLast : true
				, commitWhenExitLast : true
				, enterToTab : true
				, commitByCell : true
		});
		// masterGrid 속성값
		detailDPGrid.header.height = 20;
		detailDPGrid.footer.height = 40;
		detailDPGrid.setCheckBar({visible: false});
	}	
	
//===================================================사원별=============================================================================	
	
	function createmasterEMPGrid() {
		masterEMPProvider = new RealGrid.LocalDataProvider();
		masterEMPGrid = new RealGrid.GridView("realgrid_masterEMP");
		
		masterEMPGrid.setDataSource(masterEMPProvider);
		masterEMPProvider.setFields(fieldsEMP);
		masterEMPGrid.setColumns(columnsEMP);
		masterEMPGrid.resetCurrent();
		masterEMPGrid.footer.visible=false;
		masterEMPGrid.displayOptions.fitStyle = "even";
		
		// gridViewDisplayOptions 그리드뷰 디스플레이 설정
		masterEMPGrid.setDisplayOptions({
			emptyMessage : "표시할 데이타가 없습니다."
				, showEmptyMessage: true
				, rowHeight : 30
		});
		masterEMPGrid.setEditOptions({
			  insertable : true
			, appendable : true
			, editable : true
			, deletable: true
			, confirmWhenDelete : true
			, deleteRowsMessage : "정말로 삭제 하시겠습니까?"
				, crossWhenExitLast : true
				, commitWhenExitLast : true
				, enterToTab : true
				, commitByCell : true
		});
		// masterGrid 속성값
		masterEMPGrid.header.height = 20;
		masterEMPGrid.footer.height = 40;
		masterEMPGrid.setCheckBar({visible: false});
	}	
	
	function createdetailEMPGrid() {
		detailEMPProvider = new RealGrid.LocalDataProvider();
		detailEMPGrid = new RealGrid.GridView("realgrid_detailEMP");
		
		detailEMPGrid.setDataSource(detailEMPProvider);
		detailEMPProvider.setFields(fields1);
		detailEMPGrid.setColumns(columns1);
		detailEMPGrid.resetCurrent();
		detailEMPGrid.footer.visible=false;
		detailEMPGrid.displayOptions.fitStyle = "even";
		
		// gridViewDisplayOptions 그리드뷰 디스플레이 설정
		detailEMPGrid.setDisplayOptions({
			      emptyMessage : "표시할 데이타가 없습니다."
				, showEmptyMessage: true
				, rowHeight : 30
		});
		detailEMPGrid.setEditOptions({
			insertable : true
			, appendable : true
			, editable : true
			, deletable: true
			, confirmWhenDelete : true
			, deleteRowsMessage : "정말로 삭제 하시겠습니까?"
				, crossWhenExitLast : true
				, commitWhenExitLast : true
				, enterToTab : true
				, commitByCell : true
		});
		// masterGrid 속성값
		detailEMPGrid.header.height = 20;
		detailEMPGrid.footer.height = 40;
		detailEMPGrid.setCheckBar({visible: false});
	}	
	
	
	
	
	function init(){
		createmasterPCGrid();
		createdetailPCGrid();
		createmasterDPGrid();
		createdetailDPGrid();
		createmasterEMPGrid();
		createdetailEMPGrid();
//		setmasterPC();
//		setmasterDP();
//		setmasterEMP();
	}	
	
	function start() {
		init();
	}	
	
	window.onload = start();

	window.onunload = function() {
		masterPCProvider.clearRows();
		detailPCProvider.clearRows();
		masterDPProvider.clearRows();
		detailDPProvider.clearRows();
		masterEMPProvider.clearRows();
		detailEMPProvider.clearRows();

		masterPCGrid.destroy();
		detailPCGrid.destroy();
		masterDPGrid.destroy();
		detailDPGrid.destroy();
		masterEMPGrid.destroy();
		detailEMPGrid.destroy();
		masterPCProvider.destroy();
		detailPCProvider.destroy();
		masterDPProvider.destroy();
		detailDPProvider.destroy();
		masterEMPProvider.destroy();
		detailEMPProvider.destroy();

		masterPCProvider = null;
		masterPCGrid = null;
		detailPCProvider = null;
		detailPCGrid = null;
		masterDPProvider = null;
		masterDPGrid = null;
		detailDPProvider = null;
		detailDPGrid = null;
		masterEMPProvider = null;
		masterEMPGrid = null;
		detailEMPProvider = null;
		detailEMPGrid = null;
	}	
	
	masterPCGrid.onCurrentRowChanged = function(grid, oldRow, newRow) {
		detailGetPC(newRow);
	};
	masterDPGrid.onCurrentRowChanged = function(grid, oldRow, newRow) {
		detailGetDP(newRow);
	};
	masterEMPGrid.onCurrentRowChanged = function(grid, oldRow, newRow) {
		detailGetEMP(newRow);
	};
	
	$(function(){
		masterPCGrid.stateBar.visible = false;
		detailPCGrid.stateBar.visible = false;
		detailEMPGrid.stateBar.visible = false;
		detailEMPGrid.stateBar.visible = false;
		detailDPGrid.stateBar.visible = false;
		detailDPGrid.stateBar.visible = false;
	});