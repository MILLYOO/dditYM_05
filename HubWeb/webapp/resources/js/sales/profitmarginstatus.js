/**
 * 매출이익현황
 */
	var masterGrid, detailGrid;

	//거래처별
	var masterPCProvider, masterPCGrid;

	//부서별
	var masterDPProvider, masterDPGrid;
	
	//사원별
	var masterEMPProvider, masterEMPGrid;

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
		    url : $.getContextPath() + "/sales/profitMarginStatusListPC.do",
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
			url : $.getContextPath() + "/sales/profitMarginStatusListDP.do",
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
			url : $.getContextPath() + "/sales/profitMarginStatusListEMP.do",
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

	
//===================================================거래처별=============================================================================	

	var fieldsPC = [
			{ fieldName: "buyerName", dataType: "text" }
		  , { fieldName: "icodeName", dataType: "text" }
		  , { fieldName: "prodName", dataType: "text" }
		  , { fieldName: "gcommName", dataType: "text" }
		  , { fieldName: "ucommName", dataType: "text" }		
		  , { fieldName: "scpQty", dataType: "number" }		
		  , { fieldName: "scpSumcost", dataType: "number" }		
		  , { fieldName: "prodActucost", dataType: "number" }		
		  , { fieldName: "scpProfit", dataType: "number" }		
		  , { fieldName: "profitRatio", dataType: "text" }		
		];

		var columnsPC = [
	        { name: "buyerName", fieldName: "buyerName", type: "data", width: "80", header: { text: "거래처" } }
	      , { name: "icodeNAme", fieldName: "icodeNAme", type: "data", width: "80", header: { text: "품목계정" }, "editable" : false}	
		  , { name: "prodName", fieldName: "prodName", type: "data", width: "150", header: { text: "품명" }, "editable" : false,styleName: "left-column"}
		  , { name: "gcommName", fieldName: "gcommName", type: "data", width: "100", header: { text: "규격" },"editable" : false}
		  , { name: "ucommName", fieldName: "ucommName", type: "data", width: "50", header: { text: "단위" },"editable" : false}
		  , { name: "scpQty", fieldName: "scpQty", type: "data", width: "80", header: { text: "출고수량" }, button:"action", "editable" : false, numberFormat: "#,##0" }
		  , { name: "scpSumcost", fieldName: "scpSumcost", type: "data", width: "100", header: { text: "매출총액" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
		  , { name: "prodActucost", fieldName: "prodActucost", type: "data", width: "100", header: { text: "매출원가" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
		  , { name: "scpProfit", fieldName: "scpProfit", type: "data", width: "100", header: { text: "매출총이익" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
		  , { name: "profitRatio", fieldName: "profitRatio", type: "data", width: "80", header: { text: "이익률" }, button:"action", "editable" : false }
		];
		

//===================================================부서별=============================================================================			
		
		var fieldsDP = [
				{ fieldName: "deptName", dataType: "text" }
			  , { fieldName: "icodeName", dataType: "text" }
			  , { fieldName: "prodName", dataType: "text" }
			  , { fieldName: "gcommName", dataType: "text" }
			  , { fieldName: "ucommName", dataType: "text" }		
			  , { fieldName: "scpQty", dataType: "number" }		
			  , { fieldName: "scpSumcost", dataType: "number" }		
			  , { fieldName: "prodActucost", dataType: "number" }		
			  , { fieldName: "scpProfit", dataType: "number" }		
			  , { fieldName: "profitRatio", dataType: "text" }	
		];

		var columnsDP = [
			    { name: "deptName", fieldName: "deptName", type: "data", width: "60", header: { text: "부서명" } }
		      , { name: "icodeNAme", fieldName: "icodeNAme", type: "data", width: "50", header: { text: "품목계정" }, "editable" : false}	
			  , { name: "prodName", fieldName: "prodName", type: "data", width: "150", header: { text: "품명" }, "editable" : false,styleName: "left-column"}
			  , { name: "gcommName", fieldName: "gcommName", type: "data", width: "100", header: { text: "규격" },"editable" : false}
			  , { name: "ucommName", fieldName: "ucommName", type: "data", width: "50", header: { text: "단위" },"editable" : false}
			  , { name: "scpQty", fieldName: "scpQty", type: "data", width: "50", header: { text: "출고수량" }, button:"action", "editable" : false, numberFormat: "#,##0"  }
			  , { name: "scpSumcost", fieldName: "scpSumcost", type: "data", width: "100", header: { text: "매출총액" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
			  , { name: "prodActucost", fieldName: "prodActucost", type: "data", width: "100", header: { text: "매출원가" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
			  , { name: "scpProfit", fieldName: "scpProfit", type: "data", width: "100", header: { text: "매출총이익" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
			  , { name: "profitRatio", fieldName: "profitRatio", type: "data", width: "50", header: { text: "이익률" }, button:"action", "editable" : false }
		];
		
//===================================================사원별=============================================================================			
		
		var fieldsEMP = [
			    { fieldName: "empName", dataType: "text" }
			  , { fieldName: "icodeName", dataType: "text" }
			  , { fieldName: "prodName", dataType: "text" }
			  , { fieldName: "gcommName", dataType: "text" }
			  , { fieldName: "ucommName", dataType: "text" }		
			  , { fieldName: "scpQty", dataType: "number" }		
			  , { fieldName: "scpSumcost", dataType: "number" }		
			  , { fieldName: "prodActucost", dataType: "number" }		
			  , { fieldName: "scpProfit", dataType: "number" }		
			  , { fieldName: "profitRatio", dataType: "text" }	
		];

		var columnsEMP = [
			    { name: "empName", fieldName: "empName", type: "data", width: "60", header: { text: "사원" } }
		      , { name: "icodeNAme", fieldName: "icodeNAme", type: "data", width: "50", header: { text: "품목계정" }, "editable" : false}	
			  , { name: "prodName", fieldName: "prodName", type: "data", width: "150", header: { text: "품명" }, "editable" : false,styleName: "left-column"}
			  , { name: "gcommName", fieldName: "gcommName", type: "data", width: "100", header: { text: "규격" },"editable" : false}
			  , { name: "ucommName", fieldName: "ucommName", type: "data", width: "50", header: { text: "단위" },"editable" : false}
			  , { name: "scpQty", fieldName: "scpQty", type: "data", width: "50", header: { text: "출고수량" }, button:"action", "editable" : false, numberFormat: "#,##0"  }
			  , { name: "scpSumcost", fieldName: "scpSumcost", type: "data", width: "100", header: { text: "매출총액" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
			  , { name: "prodActucost", fieldName: "prodActucost", type: "data", width: "100", header: { text: "매출원가" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
			  , { name: "scpProfit", fieldName: "scpProfit", type: "data", width: "100", header: { text: "매출총이익" }, button:"action", "editable" : false, numberFormat: "#,##0",styleName: "right-column"  }
			  , { name: "profitRatio", fieldName: "profitRatio", type: "data", width: "50", header: { text: "이익률" }, button:"action", "editable" : false }
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
		  masterPCGrid.stateBar.width = 10;
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
		  masterDPGrid.stateBar.width = 10;
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
		masterEMPGrid.stateBar.width = 10;
	}	
	
	
	function init(){
		createmasterPCGrid();
		createmasterDPGrid();
		createmasterEMPGrid();
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
		masterDPProvider.clearRows();
		masterEMPProvider.clearRows();

		masterPCGrid.destroy();
		masterDPGrid.destroy();
		masterEMPGrid.destroy();
		masterPCProvider.destroy();
		masterDPProvider.destroy();
		masterEMPProvider.destroy();

		masterPCProvider = null;
		masterPCGrid = null;
		masterDPProvider = null;
		masterDPGrid = null;
		masterEMPProvider = null;
		masterEMPGrid = null;
	}	

	