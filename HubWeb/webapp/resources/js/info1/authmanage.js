
var fields = [
	  {
	    fieldName: "empCode",
	    dataType: "text",
	  },
	  {
		  fieldName: "memId",
		  dataType: "text",
	  },
	  {
	    fieldName: "empName",
	    dataType: "text"
	  },
	  {
		  fieldName: "deptName",
		  dataType: "text"
	  }
]
var columns = [
	  {
	    name: "empCode",
	    fieldName: "empCode",
	    type: "data",
	    header: {
	      text: "사원코드"
	    },
	    visible : false,
	  },
	  {
		  name: "memId",
		  fieldName: "memId",
		  type: "data",
		  width: "3",
		  header: {
			  text: "ID"
		  },
	  },
	  {
	    name: "empName",
	    fieldName: "empName",
	    type: "data",
	    width: "1",
	    header: {
	      text: "이름"
	    }
	  },
	  {
		  name: "deptName",
		  fieldName: "deptName",
		  type: "data",
		  width: "1",
		  header: {
			  text: "부서명"
		  }
	  }
]

var fields1 =[
	  {
		  fieldName: "authName",
		    dataType: "text"
		  },
		  {
			  fieldName: "authKname",
			  dataType: "text"
		  },
		  {
			  fieldName: "authUse",
			  dataType: "text"
		  },
		  {
			  fieldName: "authNum",
			  dataType: "text"
		  },
	
]
var columns1 = [
	{
	    name: "authName",
	    fieldName: "authName",
	    type: "data",
	    width: "200",
	    header: {
	      text: "serviceName"
	    },
	  },
	  {
		  name: "authNum",
		  fieldName: "authNum",
		  type: "data",
		  width: "200",
		  header: {
			  text: "serviceName"
		  },
	  },
	  {
		  name: "authKname",
		  fieldName: "authKname",
		  type: "data",
		  width: "400",
		  header: {
			  text: "서비스명"
		  },
	  },
	  {
		  name: "authUse",
		  fieldName: "authUse",
		  width: "150",
		  "sortable": false,
		  "lookupDisplay": true,
		  "editor": {
		    "type": "dropdown",
		    "dropDownCount" : 2,
		    "domainOnly" : true,
		    "commitOnSelect" : true,
		    "dropDownWhenClick" : true,
		  },
		  "values": ["T", "N"],
		  "labels": ["사용", "비사용"],
		  header: {
			  text: "사용여부"
		  },
	  },
	
]

// 리얼그리드 공통부분
var masterProvider, masterGrid, detailProvider, detailGrid;


/* 			기본 셋팅 masterGrid	 	*/
function createMasterGrid() {
  masterProvider = new RealGrid.LocalDataProvider();
  masterGrid = new RealGrid.GridView("realgrid_master");

  masterGrid.setDataSource(masterProvider);
  masterProvider.setFields(fields);
  masterGrid.setColumns(columns);
  masterGrid.resetCurrent();
  masterGrid.footer.visible=false
  masterGrid.setRowIndicator({
	  visible: false
	});
  masterGrid.setCheckBar({
	  visible: false
  });
  masterGrid.setStateBar({
	  visible: false
  });
  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
  masterGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 36
	  , fitStyle : "even"
	  });
  
  masterGrid.displayOptions
  // masterGrid 속성값
  masterGrid.header.height = 40;
  masterGrid.footer.height = 40;
  masterGrid.stateBar.width = 10;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
  masterGrid.setEditOptions({
	  editable : false
  })
  
  // 클릭한 곳의 Row의 넘버를 넘겨줌.
  masterGrid.onCurrentRowChanged = function (grid, oldRow, newRow) {
    detailGet(newRow);
  };
}

/* 			기본 셋팅 detailGrid	 	*/
function createDetailGrid() {
  detailProvider = new RealGrid.LocalDataProvider();
  detailGrid = new RealGrid.GridView("realgrid_detail");

  detailGrid.setDataSource(detailProvider);
  detailProvider.setFields(fields1);
  detailGrid.setColumns(columns1);
  detailGrid.footer.visible=false
  detailGrid.setRowIndicator({
	  visible: false
	});
  detailGrid.setCheckBar({
	  visible: false
  });
  detailGrid.setStateBar({
	  visible: false
  });
    // gridViewDisplayOptions 그리드뷰 디스플레이 설정
  detailGrid.columnByName("authKname").editable =  false;
  detailGrid.columnByName("authName").visible =  false;
  detailGrid.columnByName("authNum").visible =  false;
  
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
	  editable : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
  })
  detailGrid.onEditChange = function (grid, index, value) {
	 dataRow = index.dataRow;
	 detailGrid.commit();
	 var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
	 var detailData = detailGrid.getValues(dataRow);
	 var resultData = $.extend({}, masterData, detailData);
	 $.ajax({
			url : $.getContextPath() + "/info1/authManageUpdate.do",
			data : resultData,
			method : "post",
			dataType : "json",
			success : function(resp) {
				if(resp.result == 'OK'){
					toastr.success(resp.message);
				}else{
					for(var idx in resp.message){
						toastr.error(resp.message[idx]);
					}
				}
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	 
	 
}
 
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

  masterGrid.destroy();
  masterProvider.destroy();

  masterGrid = null;
  masterProvider = null;
}

// 1. 각자 메뉴에 맞는 컬럼, 필드 만들기(해당 메뉴에 맞는 컬럼과 필드를 만들어야함, VO와 명칭도 일치해야함)
// 2. 검색, 수정, 삭제시, 조건 알아서 넣기.

// 전화번호(Phone) 컬럼 편집불가 설정.
// gridView.columnByName("Phone").editable = false;
// 전화번호(Phone) 컬럼 편집가능 설정.
// gridView.columnByName("Phone").editable = true;

// -> alert, confirm 나중에 모달로 변경해야함.
/********************************* 마스터에 값 넣기 ******************************/
// 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능

$.ajax({
	url : $.getContextPath() + "/info1/authManageList.do",
	method : "post",
	dataType : "json",
	success : function(resp) {
		console.log(resp);
		masterProvider.fillJsonData(resp, {fillMode: "set"});
	},
	error : function(xhr, errorResp, error) {
		console.log(xhr);
		console.log(errorResp);
		console.log(error);
	}
});


/********************************* 디테일 설정하기 ******************************/

function detailGet(masterRow) {
detailProvider.clearRows();
	console.log(masterRow);
if (masterRow >= 0) {
    var mstKey = masterProvider.getValue(masterRow, "empCode");

		// 여기서 ajax로 datas에 값 넣어주기
	    // 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능

	$.ajax({
		url : $.getContextPath() + "/info1/authManageView.do",
		data : {
			empCode : mstKey
		},
		method : "post",
		dataType : "json",
		success : function(resp) {
			detailProvider.fillJsonData(resp, {fillMode: "set"});
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
		// ajax로 검색을 하여 완료되면, 해당 메소드를 이용하여 값 넣어주기.
//    detailProvider.setRows(datas);
};
};

