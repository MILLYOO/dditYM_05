/**
 * 매출순위표
 */
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

// 이달 1일 ~ 현재 날짜 세팅
	var date = new Date();
	$("#dateStartPC").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEndPC").val(new Date().toISOString().substring(0, 10));
	$("#dateStartDP").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEndDP").val(new Date().toISOString().substring(0, 10));
	$("#dateStartEMP").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEndEMP").val(new Date().toISOString().substring(0, 10));
	
// 목록 조회 거래처
$("#selectPC").on("click",function(){
    
	var startDate = $("#dateStartPC").val();
	var endDate = $("#dateEndPC").val();
	
	// 날짜 지정하지 않았을 때
	if(startDate.replace(/\s/g,"").length==0){
		toastr.error("날짜를 선택하세요");
		return false;
	}
	
	if(endDate >= startDate){
		let searchForm = $("#searchFormPC").ajaxForm({
		    url : $.getContextPath() + "/sales/salesRankListPC.do",
		    dataType:"json",
		    success:function(data){
		    	masterPCProvider.fillJsonData(data, {fillMode: "set"});
		       	},
		    error : function(xhr, errorResp, error){
		       console.log(xhr);
		       console.log(errorResp);
		       console.log(error);
		    }
		 }).submit();
	}else{
		toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
	
});
$("#selectDP").on("click",function(){
	
	var startDate = $("#dateStartDP").val();
	var endDate = $("#dateEndDP").val();
	
	// 날짜 지정하지 않았을 때
	if(startDate.replace(/\s/g,"").length==0){
		toastr.error("날짜를 선택하세요");
		return false;
	}
	
	if(endDate >= startDate){
		let searchForm = $("#searchFormDP").ajaxForm({
			url : $.getContextPath() + "/sales/salesRankListDP.do",
			dataType:"json",
			success:function(data){
				masterDPProvider.fillJsonData(data, {fillMode: "set"});
			},
			error : function(xhr, errorResp, error){
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		}).submit();
	}else{
		toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
	
});
$("#selectEMP").on("click",function(){
	
	var startDate = $("#dateStartEMP").val();
	var endDate = $("#dateEndEMP").val();
	
	// 날짜 지정하지 않았을 때
	if(startDate.replace(/\s/g,"").length==0){
		toastr.error("날짜를 선택하세요");
		return false;
	}
	
	if(endDate >= startDate){
		let searchForm = $("#searchFormEMP").ajaxForm({
			url : $.getContextPath() + "/sales/salesRankListEMP.do",
			dataType:"json",
			success:function(data){
				masterEMPProvider.fillJsonData(data, {fillMode: "set"});
			},
			error : function(xhr, errorResp, error){
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		}).submit();
	}else{
		toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
	
});
// 매출순위표 세부항목
function detailPCGet(masterRow) {
	detailPCProvider.clearRows();
	var startDate = $("#dateStartPC").val();
	var endDate = $("#dateEndPC").val();
	  if (masterRow >= 0) {
	      var mstKey = masterPCProvider.getValue(masterRow, "buyerName");
	      $.getJSON( $.getContextPath() + "/sales/salesRankListProd.do?buyerName=" + mstKey+ "&startDate="+startDate+"&endDate="+endDate, function(data){
	    	  detailPCProvider.fillJsonData(data, {fillMode: "set"});
  	  var datas = []
    	}) 
	  };
}
function detailDPGet(masterRow) {
	detailPCProvider.clearRows();
	var startDate = $("#dateStartDP").val();
	var endDate = $("#dateEndDP").val();
	if (masterRow >= 0) {
		var mstKey = masterDPProvider.getValue(masterRow, "deptName");
		$.getJSON( $.getContextPath() + "/sales/salesRankListProd.do?deptName=" + mstKey+"&startDate="+startDate+"&endDate="+endDate, function(data){
			detailDPProvider.fillJsonData(data, {fillMode: "set"});
			var datas = []
		}) 
	};
}
function detailEMPGet(masterRow) {
	detailEMPProvider.clearRows();
	var startDate = $("#dateStartEMP").val();
	var endDate = $("#dateEndEMP").val();
	if (masterRow >= 0) {
		var mstKey = masterEMPProvider.getValue(masterRow, "empName");
		$.getJSON( $.getContextPath() + "/sales/salesRankListProd.do?empName=" + mstKey+"&startDate="+startDate+"&endDate="+endDate, function(data){
			detailEMPProvider.fillJsonData(data, {fillMode: "set"});
			var datas = []
		}) 
	};
}


	
//===================================================거래처별=============================================================================	

	var fieldsPC = [
		    { fieldName: "rownum", dataType: "number" }
		  , { fieldName: "buyerName", dataType: "text" }
		  , { fieldName: "salcQty", dataType: "number" }
		  , { fieldName: "salcSp", dataType: "number" }
		  , { fieldName: "salcVat", dataType: "number" }		
		  , { fieldName: "salcSumcost", dataType: "number" }		
		  , { fieldName: "salcRatio", dataType: "text" }		
		];

		var columnsPC = [
		    { name: "rownum", fieldName: "rownum", type: "data", width: "30", header: { text: "순위" }, numberFormat: "###" }
	      , { name: "buyerName", fieldName: "buyerName", type: "data", width: "50", header: { text: "거래처명" } }
	      , { name: "salcQty", fieldName: "salcQty", type: "data", width: "40", header: { text: "출고수량" }, "editable" : false, numberFormat: "#,##0"}	
		  , { name: "salcSp", fieldName: "salcSp", type: "data", width: "50", header: { text: "공급가액" }, "editable" : false, numberFormat: "#,##0",styleName: "right-column" }
		  , { name: "salcVat", fieldName: "salcVat", type: "data", width: "50", header: { text: "부가세" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
		  , { name: "salcSumcost", fieldName: "salcSumcost", type: "data", width: "50", header: { text: "합계금액" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
		  , { name: "salcRatio", fieldName: "salcRatio", type: "data", width: "30", header: { text: "점유율" }, button:"action", "editable" : false }
		];
		
		var fields1 = [
			  { fieldName: "rownum", dataType: "number" }
			, { fieldName: "icodeName", dataType: "text" }
			, { fieldName: "prodName", dataType: "text" }
			, { fieldName: "gcommName", dataType: "text" }
//			, { fieldName: "ucommName", dataType: "text" }		
			, { fieldName: "scpQty", dataType: "number" }		
			, { fieldName: "scpSp", dataType: "number" }
			, { fieldName: "scpVat", dataType: "number" }
			, { fieldName: "scpSumcost", dataType: "number" }
			, { fieldName: "scpRatio", dataType: "text" }
			];
		
		var columns1 = [
			  { name: "rownum", fieldName: "rownum", type: "data", width: "40", header: { text: "순위" }, numberFormat: "###" }
			, { name: "icodeName", fieldName: "icodeName", type: "data", width: "70", header: { text: "품목계정" } }
			, { name: "prodName", fieldName: "prodName", type: "data", width: "90", header: { text: "품명" }, styleName: "left-column" }	
			, { name: "gcommName", fieldName: "gcommName", type: "data", width: "70", header: { text: "규격" }, "editable" : false}	
//			, { name: "ucommName", fieldName: "ucommName", type: "data", width: "90", header: { text: "단위" }, "editable" : false }
			, { name: "scpQty", fieldName: "scpQty", type: "data", width: "70", header: { text: "출고수량" }, "editable" : false, numberFormat: "#,##0"  }
			, { name: "scpSp", fieldName: "scpSp", type: "data", width: "70", header: { text: "공급가액" }, "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
			, { name: "scpVat", fieldName: "scpVat", type: "data", width: "80", header: { text: "부가세" }, "editable" : false, numberFormat: "#,##0",styleName: "right-column" }
			, { name: "scpSumcost", fieldName: "scpSumcost", type: "data", width: "85", header: { text: "합계금액" }, "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
			, { name: "scpRatio", fieldName: "scpRatio", type: "data", width: "55", header: { text: "점유율" } , "editable" : false }
			];

//===================================================부서별=============================================================================			
		
		var fieldsDP = [
		    { fieldName: "rownum", dataType: "number" }
		  , { fieldName: "deptName", dataType: "text" }
		  , { fieldName: "salcQty", dataType: "number" }
		  , { fieldName: "salcSp", dataType: "number" }
		  , { fieldName: "salcVat", dataType: "number" }		
		  , { fieldName: "salcSumcost", dataType: "number" }		
		  , { fieldName: "salcRatio", dataType: "text" }
		];

		var columnsDP = [
		    { name: "rownum", fieldName: "rownum", type: "data", width: "40", header: { text: "순위" },numberFormat: "###" }
	      , { name: "deptName", fieldName: "deptName", type: "data", width: "60", header: { text: "부서" } }
	      , { name: "salcQty", fieldName: "salcQty", type: "data", width: "60", header: { text: "출고수량" }, numberFormat: "#,##0" }	
		  , { name: "salcSp", fieldName: "salcSp", type: "data", width: "90", header: { text: "공급가액" }, "editable" : false, numberFormat: "#,##0",styleName: "right-column" }
		  , { name: "salcVat", fieldName: "salcVat", type: "data", width: "100", header: { text: "부가세" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
		  , { name: "salcSumcost", fieldName: "salcSumcost", type: "data", width: "100", header: { text: "합계금액" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
		  , { name: "salcRatio", fieldName: "salcRatio", type: "data", width: "40", header: { text: "점유율" }, button:"action" , "editable" : false}
		];
		
//===================================================사원별=============================================================================			
		
		var fieldsEMP = [
		    { fieldName: "rownum", dataType: "number" }
		  , { fieldName: "empName", dataType: "text" }
		  , { fieldName: "salcQty", dataType: "number" }
		  , { fieldName: "salcSp", dataType: "number" }
		  , { fieldName: "salcVat", dataType: "number" }		
		  , { fieldName: "salcSumcost", dataType: "number" }		
		  , { fieldName: "salcRatio", dataType: "text" }
		];

		var columnsEMP = [
//		    { name: "rownum", fieldName: "rownum", type: "data", width: "50", header: { text: "순위" },numberFormat: "###"  }
	        { name: "empName", fieldName: "empName", type: "data", width: "60", header: { text: "사원" } }
	      , { name: "salcQty", fieldName: "salcQty", type: "data", width: "60", header: { text: "출고수량" }, numberFormat: "#,##0" }	
		  , { name: "salcSp", fieldName: "salcSp", type: "data", width: "90", header: { text: "공급가액" }, "editable" : false, numberFormat: "#,##0",styleName: "right-column" }
		  , { name: "salcVat", fieldName: "salcVat", type: "data", width: "150", header: { text: "부가세" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
		  , { name: "salcSumcost", fieldName: "salcSumcost", type: "data", width: "150", header: { text: "합계금액" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
		  , { name: "salcRatio", fieldName: "salcRatio", type: "data", width: "130", header: { text: "점유율" }, button:"action" , "editable" : false }
		];		
		
		
		
		
//*****************************************************************************************************************************************************************		
		
		
		
		
//===================================================거래처별=============================================================================
		
	function createmasterPCGrid() {
		  masterPCProvider = new RealGrid.LocalDataProvider();
		  masterPCGrid = new RealGrid.GridView("realgrid_masterPC");
		  masterPCGrid.setCheckBar({visible: false});
		  masterPCGrid.stateBar.visible = false;
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
		  masterPCGrid.stateBar.width = 10;
	}	
	
	function createdetailPCGrid() {
		detailPCProvider = new RealGrid.LocalDataProvider();
		detailPCGrid = new RealGrid.GridView("realgrid_detailPC");
		detailPCGrid.setCheckBar({visible: false});
		detailPCGrid.stateBar.visible = false;
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
		detailPCGrid.stateBar.width = 10;
	}	

//===================================================부서별=============================================================================	
	
	function createmasterDPGrid() {
		  masterDPProvider = new RealGrid.LocalDataProvider();
		  masterDPGrid = new RealGrid.GridView("realgrid_masterDP");
		  masterDPGrid.setCheckBar({visible: false});
		  masterDPGrid.stateBar.visible = false;
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
		  masterDPGrid.stateBar.width = 10;
	}	
	
	function createdetailDPGrid() {
		detailDPProvider = new RealGrid.LocalDataProvider();
		detailDPGrid = new RealGrid.GridView("realgrid_detailDP");
		detailDPGrid.setCheckBar({visible: false});
		detailDPGrid.stateBar.visible = false;
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
		detailDPGrid.stateBar.width = 10;
	}	
	
//===================================================사원별=============================================================================	
	
	function createmasterEMPGrid() {
		masterEMPProvider = new RealGrid.LocalDataProvider();
		masterEMPGrid = new RealGrid.GridView("realgrid_masterEMP");
		masterEMPGrid.setCheckBar({visible: false});
		masterEMPGrid.stateBar.visible = false;
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
		masterEMPGrid.stateBar.width = 10;
	}	
	
	function createdetailEMPGrid() {
		detailEMPProvider = new RealGrid.LocalDataProvider();
		detailEMPGrid = new RealGrid.GridView("realgrid_detailEMP");
		detailEMPGrid.setCheckBar({visible: false});
		detailEMPGrid.stateBar.visible = false;
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
		detailEMPGrid.stateBar.width = 10;
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
	// 매출순위표 선택한 행의 번호를 넘겨주어 세부항목에 넘겨주는 부분
	if(masterPCGrid){
		// 클릭한 곳의 Row의 넘버를 넘겨줌.
		masterPCGrid.onCurrentRowChanged = function(grid, oldRow, newRow) {
			detailPCGet(newRow);
		};
	}
	if(masterDPGrid){
		// 클릭한 곳의 Row의 넘버를 넘겨줌.
		masterDPGrid.onCurrentRowChanged = function(grid, oldRow, newRow) {
			detailDPGet(newRow);
		};
	}
	if(masterEMPGrid){
		// 클릭한 곳의 Row의 넘버를 넘겨줌.
		masterEMPGrid.onCurrentRowChanged = function(grid, oldRow, newRow) {
			detailEMPGet(newRow);
		};
	}

	