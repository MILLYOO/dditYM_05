
// 1. 각자 메뉴에 맞는 컬럼, 필드 만들기(해당 메뉴에 맞는 컬럼과 필드를 만들어야함, VO와 명칭도 일치해야함)
// 2. 검색, 수정, 삭제시, 조건 알아서 넣기.

// 전화번호(Phone) 컬럼 편집불가 설정.
// gridView.columnByName("Phone").editable = false;
// 전화번호(Phone) 컬럼 편집가능 설정.
// gridView.columnByName("Phone").editable = true;

// -> alert, confirm 나중에 모달로 변경해야함.
/** ******************************* 마스터에 값 넣기 ***************************** */
// 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능
// 리얼그리드 공통부분
var masterProvider, masterGrid;
var detaileProvider, detailGrid;

var fields = [ {
	fieldName : "icodeName",
	dataType : "text"
}, {
	fieldName : "rawsCode",
	dataType : "text",
}, {
	fieldName : "rawsName",
	dataType : "text"
},
// {
// fieldName: "gcommName",
// dataType: "text"
// }
];

var columns = [ {
	name : "icodeName",
	fieldName : "icodeName",
	width : "100",
	"sortable" : false,
//	"lookupDisplay" : true,
	"editor" : {
		"type" : "dropdown",
		"dropDownCount" : 7,
		"domainOnly" : true,
		"dropDownWhenClick" : true,
		"textReadOnly" : true,
	},
	"values" : [ "상품", "원재료", "부재료", "제품", "반제품", "부산품", "저장품" ],
	"labels" : [ "1.상품", "2.원재료", "3.부재료", "4.제품", "5.반제품", "6.부산품", "7.저장품" ],
	header : {
		text : "품목계정"
	},
	styleCallback : function(grid, dataCell) {
		var ret = {}

		if (dataCell.item.rowState == 'created'
				|| dataCell.item.itemState == 'appending'
				|| dataCell.item.itemState == 'inserting') {
			ret.editable = true;
		} else {
			ret.editable = false;
		}
		return ret;
	},
},
{
	name : "rawsCode",
	fieldName : "rawsCode",
	type : "data",
	width : "100",
	header : {
		text : "품목코드"
	},
	styleCallback : function(grid, dataCell) {
		var ret = {}
		var preData = grid.getValue(dataCell.index.itemIndex, "icodeName")
		if (dataCell.item.rowState == 'created'
				|| dataCell.item.itemState == 'appending'
				|| dataCell.item.itemState == 'inserting') {
			if(preData == undefined){
				ret.editable=false;
			}
		}
		return ret;
	}
    },
 {
	name : "rawsName",
	fieldName : "rawsName",
	type : "data",
	width : "300",
	header : {
		text : "품목명"
	},
	styleCallback : function(grid, dataCell) {
		var ret = {}
		var preData = grid.getValue(dataCell.index.itemIndex, "rawsCode")
		if (dataCell.item.rowState == 'created'
				|| dataCell.item.itemState == 'appending'
				|| dataCell.item.itemState == 'inserting') {
			if(preData == undefined){
				ret.editable=false;
			}
		}
		return ret;
	},
	styleName : "left-column"
},


// {
// name: "gcommName",
// fieldName : "gcommName",
// type : "data",
// header: {
// text : "규격"
// },
// },
];


/* 기본 셋팅 masterGrid */
function createMasterGrid() {
	masterProvider = new RealGrid.LocalDataProvider();
	masterGrid = new RealGrid.GridView("realgrid_master");

	masterGrid.setDataSource(masterProvider);
	masterProvider.setFields(fields);
	masterGrid.setColumns(columns);
	
	masterGrid.displayOptions.fitStyle = "even";
	masterGrid.footer.visible = false;
	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	masterGrid.setDisplayOptions({
		emptyMessage : "표시할 데이타가 없습니다.",
		showEmptyMessage : true,
		rowHeight : 36,
		
	});

	// masterGrid 속성값
	masterGrid.header.height = 40;
	masterGrid.footer.height = 40;
	masterGrid.stateBar.width = 10;
	
	  masterGrid.setCheckBar({
		  visible: false
	  });
	  masterGrid.setStateBar({
		  visible: false
	  });
	// gridViewEditOptions 그리드뷰 edit 옵션
	masterGrid.setEditOptions({
		insertable : true,
		appendable : true,
		editable : true,
		deletable : true,
		confirmWhenDelete : true,
		deleteRowsMessage : "정말로 삭제 하시겠습니까?",
		enterToTab : true,
		displayEmptyEditRow : true,
		commitWhenExitLast : true,
		commitByCell : true
	})

	masterGrid.onCurrentRowChanged = function(grid, oldRow, newRow) {
		if (newRow < 0) {
			$("#itemDetail").find(":input[name]").attr("disabled", true).val("");
			$("#saved").attr("disabled", true);
			$("#itemDetail").find('.fa-bars').attr("hidden", "hidden");
			
		} else {
			formGet(masterProvider.getJsonRow(newRow));
		}
	};
	masterGrid.onCellEdited = function (grid, itemIndex, row, field) {
	     if(masterGrid.getItemState(itemIndex) == 'appending' && grid.getColumn(field).fieldName == 'rawsName'){
	    	 insertItem(grid.getValues(itemIndex));
	     }
	}
	
	masterProvider.onRowInserted = function(provider, row) {
		init();
	};

	
	
	masterProvider.onRowUpdated = function (provider, row) {
		var data = provider.getJsonRow(row);
		updateItem(data);
	};
	

	masterProvider.onRowInserting = function (provider, row, values) {
		var result = true;
		for(var i = 1 ; i < values.length ; i++){
			if(values[i] == undefined || values[i] == null || values[i] == ''){
				toastr.error(masterGrid.getColumn(i).displayText + "은(는) 빈값일 수 없습니다.");
				result = false;
			}
		}
	    return result;
	};

}
function init(){
	$.ajax({
		url : $.getContextPath() + "/info2/itemManageRetrieve.do",
		method : "post",
		dataType : "json",
		success : function(resp) {
			masterProvider.fillJsonData(resp, {
				fillMode : "set"
			});
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
}


$("#saved").on("click", function(){
	$("#itemDetail").ajaxForm({
		url : $.getContextPath() + "/info2/itemDetailUpdate.do",
		method : "post",
		dataType : "json",
		success : function(resp) {
			if(resp.result == 'OK'){
				toastr.success(resp.message);
			}else{
				if(Array.isArray(resp.message)){
					for(var idx in resp.message){
						toastr.error(resp.message[idx]);
					}
				}else{
				 toastr.error(resp.message);
				}
			}
		}
	}).submit();
});

$("#select1").on("click",function(){
	$("#searchForm").ajaxForm({
		url : $.getContextPath() + "/info2/itemManageRetrieve.do",
		method : "post",
		dataType : "json",
		success : function(resp) {
			masterProvider.fillJsonData(resp, {fillMode: "set"});
		}
	}).submit();
})

function formGet(data) {
	$.ajax({
		url : $.getContextPath() + "/info2/itemManageRetrieve.do",
		data : data,
		method : "post",
		dataType : "json",
		success : function(resp) {
			var data = resp[0];
			if (data != undefined) {
				$("#itemDetail").find(":input[name]").attr("disabled", false);
				$("#rawUse").attr("disabled", false);
				$("#saved").attr("disabled", false);
				$("input[name=rawsCode]").val(data.rawsCode);
				$("input[name=icodeName]").val(data.icodeName);
				$("input[name=ldivName]").val(data.ldivName);
				$("input[name=mdivName]").val(data.mdivName);
				$("input[name=sdivName]").val(data.sdivName);
				$("#gcommName").val(data.gcommName);
				$("input[name=ucommInname]").val(data.ucommInname);
				$("input[name=ucommOutname]").val(data.ucommOutname);
				$("input[name=ucommStname]").val(data.ucommStname);
				$("input[name=rawsAdqinv]").val(data.rawsAdqinv);
				$("input[name=rawsStancost]").val(data.rawsStancost);
				$("input[name=rawsActucost]").val(data.rawsActucost);
				$("input[name=rawsPurchprice]").val(data.rawsPurchprice);
				$("input[name=rawsSalprice]").val(data.rawsSalprice);
				$("input[name=rawsUse]").val(data.rawsUse);
				$("#rawsUse").val(data.rawsUse).prop("selected", true);
			}
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
};

function updateItem(value){
	$.ajax({
		url : $.getContextPath() + "/info2/itemUpdate.do",
		data : value,
		async : false,
		method : "post",
		dataType : "json",
		success : function(resp) {
			if(resp.result == 'OK'){
				toastr.success(resp.message);
				init();
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
}

function insertItem(value) {
	var dataList = [];
	dataList.push(value);
	var obj = {"commonList" : dataList}
	$.ajax({
		url : $.getContextPath() + "/info2/itemManageInsert.do",
		data : JSON.stringify(obj),
		async : false,
		method : "post",
		dataType : "json",
		contentType : "application/json",
		success : function(resp) {
			if(resp.result == 'OK'){
				toastr.success(resp.message);
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
}

function excelExport() {
	RealGrid.exportGrid({
		type : 'excel',
		target : 'local',
		fileName : '품목등록.xlsx',
		exportGrids : [ {
			grid : masterGrid,
			sheetName : '품목등록'
		}, ]
	})
	masterGrid.setColumns(columns);
}

function gridClear() {
	masterProvider.clearRows();
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



$(":input[name]").keypress(function(e) {
	if (e.keyCode === 13) {
		console.log($(this))
		$(this).closest("tr").next().find(":input[name]").focus();
//		$("#itemDetail").find(":input[name]").focus();
	}
	
});
//
//검색에서 클릭 시 코드도움 모달을 보여준다.
warCode = function(obj){
  $("#war-modal").css('display', 'flex')
  warObj = $(obj);
}
kcommCode = function(obj){
  $("#kcomm-modalM").css('display', 'flex')
  kcommObj = $(obj);
}
gcommCode = function(obj){
  $("#gcomm-modalM").css('display', 'flex')
  gcommObj = $(obj);
}
$("#rawsName").focus(function(){
	$("#item-modalM").css('display', 'flex')
	itemObj = this;
})
deptCode = function(obj){
  $("#dept-modal").css('display', 'flex')
  deptObj = $(obj);
}
ucommCode = function(obj){
  $("#ucomm-modalM").css('display', 'flex')
  ucommObj = $(obj);
}
ldivCode = function(obj){
  $("#ldiv-modal").css('display', 'flex')
  ldivObj = $(obj);
}
mdivCode = function(obj){
  divCodeL = $("input[name=divCodeL]").val()
  if(divCodeL){
     //데이터 가져오기
     $.ajax({
           url : $.getContextPath() + "/info2/div/divList.do",
           data : {"divCategory":"L",
                    "divChild":divCodeL},
           method : 'post',
           dataType : "json",
           success : function(data){
              mdivProvider.fillJsonData(data, {fillMode: "set"});
           },
           error: function(xhr, errorResp, error){
              console.log(xhr);
              console.log(errorResp);
              console.log(error);
           }
     });
  }
  
  mdivObj = $(obj).prev();
  $("#mdiv-modal").css('display', 'flex')
}

sdivCode = function(obj){
  var divCodeM = $("input[name=divCodeM]").val()
  if(divCodeM){
     //데이터 가져오기
     $.ajax({
           url : $.getContextPath() + "/info2/div/divList.do",
           data : {"divCategory":"M",
                    "divChild":divCodeM},
           method : 'post',
           dataType : "json",
           success : function(data){
              sdivProvider.fillJsonData(data, {fillMode: "set"});
           },
           error: function(xhr, errorResp, error){
              console.log(xhr);
              console.log(errorResp);
              console.log(error);
           }
     });
  }
  
  sdivObj = $(obj).prev();
  $("#sdiv-modal").css('display', 'flex')
}

$("#rawsSalprice, #rawsPurchprice, #rawsActucost, #rawsStancost, #rawsAdqinv").inputmask("numeric", {
	autoGroup: true, // default: false, 정수 부분 그룹화 사용 여부
	groupSeparator: ",", // default: "", 그룹 구분자 정의
	digits: 0, // default: "*", 소수 크기 정의
	showMaskOnHover: false,
	showMaskOnFocus: false,
	allowMinus: false, // default: true, 음수 사용 여부
	repeat: 12 // 반복 기능? 나한테는 그냥 자리수..
});

	function start() {
		
		createMasterGrid();
		init();
	}