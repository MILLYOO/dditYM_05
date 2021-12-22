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
	fieldName : "projCode",
	dataType : "text"
}, {
	fieldName : "projName",
	dataType : "text",
}, {
	fieldName : "projSort",
	dataType : "text",
}, {
	fieldName : "projUse",
	dataType : "text",
}

];

var columns = [
		{
			name : "projCode",
			fieldName : "projCode",
			type : "data",
			width : "60",
			header : {
				text : "Code"
			},
			width : '3',
			editor : {
				"inputCharacters" : "0-9"
			},
			visible : false,
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
			}
		}
		, {
			name : "projName",
			fieldName : "projName",
			type : "data",
			width : "3",
			header : {
				text : "프로젝트명"
			},
		} 
		, {
			name: "projSort",
			fieldName: "projSort",
			width: "1",
			"sortable": false,
			"lookupDisplay": true,
			"editor": {
			  "type": "dropdown",
			  "dropDownCount" : 3,
			  "domainOnly" : true,
			  "textReadOnly" : true,
			  "commitOnSelect" : true,
			  "dropDownWhenClick" : true,
			},
			"values": ["진행", "완료", "보류"],
			"labels": ["진행", "완료", "보류"],
			header: {
				  text: "구분"
			},
			styleCallback : function(grid, dataCell) {
				var ret = {}
				var preData = grid.getValue(dataCell.index.itemIndex, "projName")
				if (dataCell.item.rowState == 'created'
						|| dataCell.item.itemState == 'appending'
						|| dataCell.item.itemState == 'inserting') {
					if(preData == undefined){
						ret.editable=false;
					}
				}
				return ret;
			}
		} 
		, {
			name: "projUse",
			fieldName: "projUse",
			width: "1",
			"sortable": false,
			"lookupDisplay": true,
			"editor": {
			  "type": "dropdown",
			  "dropDownCount" : 2,
			  "domainOnly" : true,
			  "commitOnSelect" : true,
			  "textReadOnly" : true,
			  "dropDownWhenClick" : true,
			},
			"values": ["Y", "N"],
			"labels": ["사용", "비사용"],
			header: {
				  text: "사용여부"
			},
			styleCallback : function(grid, dataCell) {
				var ret = {}
				var preData = grid.getValue(dataCell.index.itemIndex, "projSort")
				if (dataCell.item.rowState == 'created'
						|| dataCell.item.itemState == 'appending'
						|| dataCell.item.itemState == 'inserting') {
					if(preData == undefined){
						ret.editable=false;
					}
				}
				return ret;
			}
		} 
		
		
		
		];

/* 기본 셋팅 masterGrid */
function createMasterGrid() {
	masterProvider = new RealGrid.LocalDataProvider();
	masterGrid = new RealGrid.GridView("realgrid_master");

	masterGrid.setDataSource(masterProvider);
	masterProvider.setFields(fields);
	masterGrid.setColumns(columns);
	masterGrid.resetCurrent();
	masterGrid.displayOptions.fitStyle = "even";
	masterGrid.footer.visible = false;
	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	masterGrid.setDisplayOptions({
		emptyMessage : "표시할 데이타가 없습니다.",
		showEmptyMessage : true,
		rowHeight : 36
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

	masterProvider.onRowInserting = function (provider, row, values) {
		var result = true;
		for(var i = 1 ; i < values.length ; i++){
			if(values[i] == undefined){
				toastr.error(masterGrid.getColumn(i).displayText + "은(는) 빈값일 수 없습니다.");
				result = false;
			}
		}
	    return result;
	};
	
	// 그리드에서 어팬드 상태인것
	masterGrid.onCellEdited = function (grid, itemIndex, row, field) {
	     if(masterGrid.getItemState(itemIndex) == 'appending' && grid.getColumn(field).fieldName == 'projUse'){
	    	 insertProject(grid.getValues(itemIndex));
	    	 grid.commit();
	     }
	}
	
	masterGrid.onCurrentRowChanged = function(grid, oldRow, newRow) {
		if (newRow < 0) {
			$("#projectDetail").find(":input[name]").attr("disabled", true)
					.val("");
			$("#saved").attr("disabled", true);
			$("#delete").attr("disabled", true);
			$("#searchAdd").attr("disabled", true);

		} else {
			formGet(masterProvider.getJsonRow(newRow));
		}
	};

	masterProvider.onRowInserted = function(provider, row) {
		init();
	};

	masterProvider.onRowUpdated = function(provider, row) {
		var data = provider.getJsonRow(row);
		updateProject(data);
	};
}

function init(){
	$.ajax({
		url : $.getContextPath() + "/info1/projectRetrieve.do",
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

$("#saved").on("click", function() {
	$("#projectDetail").ajaxForm({
		url : $.getContextPath() + "/info1/projectDetailUpdate.do",
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
		}
	}).submit();
});

$("#select1, #select2").on("click",function(){
	masterGrid.cancel();
	$("#searchForm").ajaxForm({
		url : $.getContextPath() + "/info1/projectRetrieve.do",
		method : "post",
		dataType : "json",
		success : function(resp) {
			masterProvider.fillJsonData(resp, {fillMode: "set"});
		}
	}).submit();
})

$("#delete").on("click", function(){
	row = masterGrid.getCurrent().dataRow;
	data = masterGrid.getValues(masterGrid.getCurrent().itemIndex);
	if(masterGrid.getCurrent().dataRow > -1){
		Swal.fire({
			title: "[ " +data.projName + " ]<br>정말로 삭제하시겠습니까?",
			text: "다시 한 번 확인해주세요",
			icon: "warning",
			showCancelButton: true,
			confirmButtonColor: "#3085d6",
			cancelButtonColor: "#d33",
			confirmButtonText: "승인",
			cancelButtonText: "취소"
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url : $.getContextPath() + "/info1/projectDelete.do",
					data : data,
					method : "post",
					dataType : "json",
					async: false,
					success : function(resp) {
						if(resp.result == 'OK'){
							Swal.fire({
								  icon: 'success',
								  title: resp.message
								})
							masterProvider.removeRow(row);
						}else{
							Swal.fire({
								  icon: 'error',
								  title: resp.message,
							})
						}
					},
					error : function(xhr, errorResp, error) {
						console.log(xhr);
						console.log(errorResp);
						console.log(error);
					}
				});
			}
		})
	}
});


function formGet(data) {
	$.ajax({
		url : $.getContextPath() + "/info1/projectRetrieve.do",
		data : data,
		method : "post",
		dataType : "json",
		success : function(resp) {
			var data = resp[0];
			if (data != undefined) {
				$("#projectDetail").find(":input[name]").attr("disabled", false);
				$("#saved").attr("disabled", false);
				$("#delete").attr("disabled", false);
				$("input[name=projCode]").val(data.projCode);
				$("input[name=projName]").val(data.projName);
				var projStart = $("input[name=projStart]").val(data.projStart);
				var projEnd = $("input[name=projEnd]").val(data.projEnd);
				$("textarea[name=projPurpose]").val(data.projPurpose);
				$("textarea[name=projContent]").val(data.projContent);
				$("input[name=projBudget]").val(data.projBudget);
				$("input[name=empName]").val(data.empName);
				$("input[name=deptName]").val(data.deptName);
				if(data.projStart != undefined){
					$('#projDate').data('daterangepicker').setStartDate(data.projStart);
					$('#projDate').data('daterangepicker').setEndDate(data.projEnd);
				}else{
					$('#projDate').val('');
				}
			}
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
};

function updateProject(value) {
	$.ajax({
		url : $.getContextPath() + "/info1/projectUpdate.do",
		data : value,
		async : false,
		method : "post",
		dataType : "json",
		success : function(resp) {
			console.log(resp)
			console.log(resp.result)
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

function insertProject(value) {
	$.ajax({
		url : $.getContextPath() + "/info1/projectInsert.do",
		data : value,
		async : false,
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

function excelExport() {
	RealGrid.exportGrid({
		type : 'excel',
		target : 'local',
		fileName : '프로젝트등록.xlsx',
		exportGrids : [ {
			grid : masterGrid,
			sheetName : '프로젝트등록'
		}, ]
	})
}

function start() {
	createMasterGrid();
	init();
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
		// $("#itemDetail").find(":input[name]").focus();
	}

});

// 검색에서 클릭 시 코드도움 모달을 보여준다.
empCode = function(obj) {
	$("#emp-modal").css('display', 'flex')
	empObj = $(obj);
}

deptCode = function(obj) {
	$("#dept-modal").css('display', 'flex')
	deptObj = $(obj);
}


$('#projDate').daterangepicker(
		{ "locale": {
			"format": "YYYY/MM/DD", "separator": " ~ ",
			"applyLabel": "확인", "cancelLabel": "취소",
			"fromLabel": "From", "toLabel": "To",
			"customRangeLabel": "Custom", "weekLabel": "W",
			"daysOfWeek": ["일", "월", "화", "수", "목", "금", "토"],
			"monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"], 
		},
		"startDate": new Date(),
		"endDate": new Date(),
		"drops": "auto" },
		function (start, end, label) {
			$('#projStart').val(start.format('YYYY/MM/DD'));
			$('#projEnd').val(end.format('YYYY/MM/DD'));
});

$('#projDate').val('');
$(":input[name=projBudget]").inputmask("numeric", {
	autoGroup: true,
	groupSeparator: ",",
	showMaskOnHover: false,
	showMaskOnFocus: false,
	allowMinus: false,
	digits: 0,
	repeat: 12
});

