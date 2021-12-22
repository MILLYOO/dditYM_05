<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<html>
  <head>
  <style>
#realgrid_master {
  width: 100%;
  height: 450px;
  margin-bottom: 36px;
}

#realgrid_detail {
width: 100%;
height: 450px;
}

.alternate-row {
  background: #f8f8f8;
}

.blue-column {
  color: blue;
}

.bold-cell {
  font-weight: bold;
  background-color: greenyellow;
}

#actions {
  height: 50px;
}

#realgrid {
  width: 100%;
  height: 450px; /* for WebKit */
}

.test-style {
  background: linear-gradient(to right, #96f58f, red);
}

.right-column {
  text-align: right;
}

.left-column {
  text-align: left;
}

.red-bar-solid .rg-bar-renderer-bar {
  background: #ff0000;
}

.green-bar-solid .rg-bar-renderer-bar-right {
  background: #00ff00;
}

.blue-bar-solid .rg-bar-renderer-bar-top {
  background: #0000ff;
}

.yellow-bar-solid .rg-bar-renderer-bar-bottom {
  background: #ffff00;
}
.lightskyblue-color{
  background: rgb(223, 241, 252);
}

.lightcyan-color{
  background: lightcyan;
}
.green-hidden span {
  right: 12% !important;
  transform: translate(0%, -50%) !important;
  display: none !important;
}

.yellow-column {
  background: #ffff00
}

.cursor {
  cursor: crosshair;
}

.orange-column{
  background: #ff8b00;
}
</style>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        
    <!-- RealGrid -->
    <link href="<%=request.getContextPath()%>/resources/js/realgrid/realgrid-style.css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/realgrid/realgrid-lic.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/realgrid/realgrid.min.js"></script>

    <!-- Custom -->
    <link href="./index.css" rel="stylesheet" />
    <script src="./index.js"></script>
  </head>

  <body>
  	<form>
	  	<input type="button" id="saved" value="저장"/>
	  	<input type="button" id="append" value="추가"/>
	  	<input type="button" id="delete" value="삭제"/>
  	</form>
    <div id="realgrid_master"></div>
      	<form>
	  	<input type="button" id="saved1" value="저장"/>
	  	<input type="button" id="append1" value="추가"/>
	  	<input type="button" id="delete1" value="삭제"/>
  	</form>
    <div id="realgrid_detail"></div>
  </body>
  <script type="text/javascript">
  var fields = [
	  {
	    fieldName: "memId",
	    dataType: "text"
	  },
	  {
	    fieldName: "memName",
	    dataType: "text"
	  },
	  {
	    fieldName: "memHp",
	    dataType: "text"
	  },
	  {
	    fieldName: "memAdd1",
	    dataType: "text"
	  },
	  {
	    fieldName: "memMileage",
	    dataType: "text"
	  },
	  {
	    fieldName: "memMail",
	    dataType: "text"
	  }
	];

	var columns = [
	  {
	    name: "memId",
	    fieldName: "memId",
	    type: "data",
	    width: "60",
	    header: {
	      text: "아이디"
	    },
	    button : "action"
	  },
	  {
	    name: "memName",
	    fieldName: "memName",
	    type: "data",
	    width: "40",
	    header: {
	      text: "이름"
	    }
	  },
	  {
	    name: "memHp",
	    fieldName: "memHp",
	    type: "data",
	    width: "40",
	    header: {
	      text: "번호"
	    }
	  },
	  {
	    name: "memAdd1",
	    fieldName: "memAdd1",
	    type: "data",
	    width: "100",
	    header: {
	      text: "주소",
	    }
	  },
	  {
	    name: "memMileage",
	    fieldName: "memMileage",
	    type: "data",
	    width: "120",
	    header: {
	      text: "마일리지",
	    }
	  },
	  {
	    name: "memMail",
	    fieldName: "memMail",
	    type: "data",
	    width: "100",
	    header: {
	      text: "이메일",
	    }
	  }
	];
	
	
	var columns1 = [
	{
		name : "boNo",
		fieldName : "boNo",
		width : "100",
		header : {
			text : "글번호",
		}
	},
	{
		name : "boTitle",
		fieldName : "boTitle",
		width : "120",
		header : {
			text : "제목",
		}
	}
	]
	var fields1 = [
	{
		fieldName : "boNo",
		dataType : "text"
	},
	{
		fieldName : "boTitle",
		dataType : "text"
	}
	]
// realgrid 만들기

/* 			기본 셋팅 masterGrid	 	*/
var masterProvider, masterGrid, detailProvider, detailGrid;

function createMasterGrid() {
  masterProvider = new RealGrid.LocalDataProvider();
  masterGrid = new RealGrid.GridView("realgrid_master");

  masterGrid.setDataSource(masterProvider);
  masterProvider.setFields(fields);
  masterGrid.setColumns(columns);
  masterGrid.resetCurrent();

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
  masterGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 36
	  });
  
  // masterGrid 속성값
  masterGrid.header.height = 40;
  masterGrid.footer.height = 40;
  masterGrid.stateBar.width = 10;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
  masterGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : true
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  , enterToTab : true
	  , forceAppend : true
	  
  })
  
  masterGrid.onCellButtonClicked =  function (grid, index, col) {
     console.log(index);
     console.log("잘나옵니다");
     console.log(col);
  };
  masterGrid.onCurrentChanged =  function (grid, newIndex) {
	  detailGrid.cancel();
  };
  
  // 클릭한 곳의 Row의 넘버를 넘겨줌.
  masterGrid.onCurrentRowChanged = function (grid, oldRow, newRow) {
    detailGet(newRow);
  };

	masterGrid.setRowStyleCallback(function(grid, item, fixed) {
	    switch (item.rowState) {
	      case "created": 
	        return "lightskyblue-color";
	      case "updated": 
	        return "lightcyan-color";
	    }
})
  
  // 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능
  $.getJSON("${cPath}/member/memberList.do", function(data){
	  console.log(data);
	  masterProvider.fillJsonData(data.dataList, {fillMode: "set"});
  }) 
}
	// 저장버튼을 사용하기 위한 설정
	// 백단에서 기본키가 되는 code가 없으면 insert, 있으면 update
	$("#saved").on("click", function(){
		masterGrid.commit();
		let checkedRows = masterGrid.getCheckedRows();
		if(checkedRows.length > 0){
		masterProvider.setRowStates(checkedRows, "none", true);
			console.log(checkedRows);
			for(var checked = 0 ;  checked < checkedRows.length ; checked++){
				data = masterGrid.getValues(checkedRows[checked]);
				masterGrid.refresh(true);
				console.log(data); // 입력한 object
			} 
		}else{
			alert("체크하고 해주세요");
		}
	});


	
	// 추가버튼
	$("#append").on("click", function(){
		var count = masterProvider.getRowStateCount("created");
		if(count > 0){
			alert("추가된 행을 저장해주세요")	
		}else{
			var values = [];
			for(var i = 0 ; i < fields.length ; i++){
				values.push = "";
			}
			masterProvider.addRow(values);
		}
	})
	
	// 삭제버튼
	$("#delete").on("click", function(){
		console.log("삭제");
		let checkedRows = masterGrid.getCheckedRows();
		
// 		gridView.deleteSelection(false);
		var result = window.confirm("정말로 삭제하시겠습니까?");
		if(result === true){
		masterProvider.removeRows(checkedRows);
		}
	})

/* 			기본 셋팅 detailGrid	 	*/
function createDetailGrid() {
  detailProvider = new RealGrid.LocalDataProvider();
  detailGrid = new RealGrid.GridView("realgrid_detail");

  detailGrid.setDataSource(detailProvider);
  detailProvider.setFields(fields1);
  detailGrid.setColumns(columns1);

    // gridViewDisplayOptions 그리드뷰 디스플레이 설정
  detailGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 36
	  });
  // detailGrid 속성값
  detailGrid.header.height = 40;
  detailGrid.footer.height = 40;
  detailGrid.stateBar.width = 10;
  detailGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : true
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
  })

  setTimeout(function(){
    masterGrid.resetCurrent();
  },300);
}

function detailGet(masterRow) {
  detailProvider.clearRows();
	console.log(masterRow);
  if (masterRow >= 0) {
      var mstKey = masterProvider.getValue(masterRow, "memName");

      
      var datas = []
	  
	  // 여기서 ajax로 datas에 값 넣어주기
	    // 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능
	$.getJSON("${cPath}/board/boardList.do?searchWord=" + mstKey, function(data){
		  console.log(data);
		  detailProvider.fillJsonData(data.dataList, {fillMode: "set"});
	}) 
//      detailProvider.setRows(datas);
  };
};

	// 저장버튼을 사용하기 위한 설정
	// 백단에서 기본키가 되는 code가 없으면 insert, 있으면 update
	$("#saved1").on("click", function(){
		let checkedRows = detailGrid.getCheckedRows();
		detailGrid.commit();
		if(checkedRows.length > 0){
			console.log(checkedRows);
			for(var checked = 0 ;  checked < checkedRows.length ; checked++){
				data = checkedRows[checked];
				console.log(data)
				console.log(detailGrid.getValues(data));
			} 
		}else{
			alert("체크하고 해주세요");
		}
	});

	// 추가버튼
	$("#append1").on("click", function(){
// 		var values = ["", "", "", "", "", "", ""]; // 컬럼의 수에 맞춰서 추가하기
//		detailProvider.addRow(values);
		detailGrid.beginAppendRow();
		detailGrid.showEditor();
		detailGrid.setFocus();
	})
	
	// 삭제버튼
	$("#delete1").on("click", function(){
		console.log("삭제");
		let checkedRows = detailGrid.getCheckedRows();
		
// 		gridView.deleteSelection(false);
		var result = window.confirm("정말로 삭제하시겠습니까?");
		if(result === true){
		detailProvider.removeRows(checkedRows);
		}
	})


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
window.onload = start;
// domloaded를 대신 써도 됩니다.

window.onunload = function() {
  dataProvider.clearRows();

  gridView.destroy();
  dataProvider.destroy();

  gridView = null;
  dataProvider = null;
}
  </script>
</html>