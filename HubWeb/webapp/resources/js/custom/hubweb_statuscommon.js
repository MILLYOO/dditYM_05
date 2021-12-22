// 리얼그리드 공통부분
var masterProvider, masterGrid, detailProvider, detailGrid;

/* 			기본 셋팅 masterGrid	 	*/
function createMasterGrid() {
  masterProvider = new RealGrid.LocalDataProvider();
  masterGrid = new RealGrid.GridView("realgrid_master");
  masterGrid.setCheckBar({visible: false});
  masterGrid.setDataSource(masterProvider);
  masterProvider.setFields(fields);
  masterGrid.setColumns(columns);
  masterGrid.resetCurrent();
  masterGrid.stateBar.visible = false;

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
  masterGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 36
	  , fitStyle : "even"
  });
  
  // masterGrid 속성값
  masterGrid.header.height = 40;
  masterGrid.footer.height = 40;
  masterGrid.stateBar.width = 10;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
  masterGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : false
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  , enterToTab : true
	  ,forceAppend:true
  })
  
  // 클릭한 곳의 Row의 넘버를 넘겨줌.
  // By 이원균 : 39라인 재고평가할때 오류나서 if문 추가했음_211122
  if(masterGrid){
	  masterGrid.onCurrentRowChanged = function (grid, oldRow, newRow) {
		  detailGet(newRow);
	  };
  }
}

//검색에서 클릭 시 코드도움 모달을 보여준다.
buyerCode = function(obj){
    $("#buyer-modal").css('display', 'flex')
    buyerObj = obj;
}
empCode = function(obj){
	$("#emp-modal").css('display', 'flex')
	empObj = obj;
}
deptCode = function(obj){
	$("#dept-modal").css('display', 'flex')
	deptObj = obj;
}
prodCode = function(obj){
	$("#proj-modal").css('display', 'flex')
	projObj = obj;
}
warCode = function(obj){
	$("#war-modal").css('display', 'flex')
	warObj = obj;
}
kcommCode = function(obj){
	$("#kcomm-modalM").css('display', 'flex')
	kcommObj = obj;
}
gcommCode = function(obj){
	$("#gcomm-modalM").css('display', 'flex')
	gcommObj = obj;
}
itemCode = function(obj){
	$("#item-modalM").css('display', 'flex')
	itemObj = obj;
}
ucommCode = function(obj){
	$("#ucomm-modalM").css('display', 'flex')
	ucommObj = obj;
}
ldivCode = function(obj){
	$("#ldiv-modal").css('display', 'flex')
	ldivObj = obj;
}
mdivCode = function(obj){
	mdivObj = obj;
	var divCodeL = $("input[name=divCodeL]").val()
	console.log(divCodeL)
	if(divCodeL){
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info2/div/divList.do",
			   data : {"divCategory":"L",
				   		"divChild":divCodeL},
			   method : 'post',
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   mdivProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	
	$("#mdiv-modal").css('display', 'flex')
}
sdivCode = function(obj){
	sdivObj = obj;
	var divCodeM = $("input[name=divCodeM]").val()
	console.log(divCodeM)
	if(divCodeM){
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info2/div/divList.do",
			   data : {"divCategory":"M",
				   		"divChild":divCodeM},
			   method : 'post',
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   sdivProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	
	$("#sdiv-modal").css('display', 'flex')
}

/* 			기본 셋팅 detailGrid	 	*/
function createDetailGrid() {
  detailProvider = new RealGrid.LocalDataProvider();
  detailGrid = new RealGrid.GridView("realgrid_detail");
  detailGrid.setCheckBar({visible: false});
  detailGrid.stateBar.visible = false;
  detailGrid.setDataSource(detailProvider);
  detailProvider.setFields(fields1);
  detailGrid.setColumns(columns1);

    // gridViewDisplayOptions 그리드뷰 디스플레이 설정
  detailGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 36
	  , fitStyle : "even"
	  });
  // detailGrid 속성값
  detailGrid.header.height = 40;
  detailGrid.footer.height = 40;
  detailGrid.stateBar.width = 10;
  detailGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : false
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
  })

  setTimeout(function(){
    masterGrid.resetCurrent();
  },300);
}


function excelExport() {
  RealGrid.exportGrid({
    type: 'excel',
    target: 'local',
    fileName: 'multiExport.xlsx',
    exportGrids: [
      { grid: masterGrid, sheetName: 'masterSheet' },
      { grid: detailGrid, sheetName: 'detailSheet' }
    ]
  })
}

function start() {
  createMasterGrid();
  createDetailGrid();
}

// $.document.ready(start);
window.onload = start();
// domloaded를 대신 써도 됩니다.

window.onunload = function() {
	  masterProvider.clearRows();
	  detailProvider.clearRows();

	  masterGrid.destroy();
	  detailGrid.destroy();
	  
	  masterProvider.destroy();
	  detailProvider.destroy();

	  masterGrid = null;
	  masterProvider = null;
	  
	  detailGrid = null;
	  detailProvider = null;
}