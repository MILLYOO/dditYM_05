var fields = [ 
	{
	fieldName : "deptCode",
	dataType : "int"
}, {
	fieldName : "deptName",
	dataType : "text"
}
, {
	fieldName : "deptCount",
	dataType : "int"
}]
var columns = [
		{
			name : "deptCode",
			fieldName : "deptCode",
			type : "data",
			width : "60",
			header : {
				text : "부서코드"
			},
			editor : {
				"inputCharacters": "0-9"
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
		}, {
			name : "deptName",
			fieldName : "deptName",
			type : "data",
			width : "200",
			header : {
				text : "부서명"
			}
		},{
			name : "deptCount",
			fieldName : "deptCount",
			type : "data",
			width : "50",
			header : {
				text : "사원수"
			},
			editable : false
		}, ]

var fields1 = [ {
	fieldName : "empCode",
	dataType : "text"
}, {
	fieldName : "empName",
	dataType : "text"
}, {
	fieldName : "empDate",
	dataType : "date"
}, {
	fieldName : "empRank",
	dataType : "text"
},

]
var columns1 = [ {
	name : "empCode",
	fieldName : "empCode",
	type : "data",
	width : "60",
	header : {
		text : "사원코드"
	},
}, {
	name : "empName",
	fieldName : "empName",
	type : "data",
	width : "100",
	header : {
		text : "사원명"
	},
}, {
	name : "empDate",
	fieldName : "empDate",
	type : "data",
	width : "100",
	header : {
		text : "입사일"
	},
}, {
	name : "empRank",
	fieldName : "empRank",
	type : "data",
	width : "100",
	header : {
		text : "직급"
	},
}, ]

// 리얼그리드 공통부분
var masterProvider, masterGrid, detailProvider, detailGrid;

/* 기본 셋팅 masterGrid */
function createMasterGrid() {
	masterProvider = new RealGrid.LocalDataProvider();
	masterGrid = new RealGrid.GridView("depart_master");

	masterGrid.setDataSource(masterProvider);
	masterProvider.setFields(fields);
	masterGrid.setColumns(columns);
	masterGrid.resetCurrent();
	masterGrid.footer.visible = false
//	masterGrid.setRowIndicator({
//		visible : false
//	});
	masterGrid.setCheckBar({
		visible : false
	});
	masterGrid.setStateBar({
		visible : false
	});
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

	masterGrid.displayOptions.fitStyle = "even";

	// 클릭한 곳의 Row의 넘버를 넘겨줌.
	masterGrid.onCurrentRowChanged = function(grid, oldRow, newRow) {
		detailGet(newRow);
	};

	masterProvider.onRowInserted = function(provider, row) {
		init();
	};
	
	masterGrid.onCellEdited = function (grid, itemIndex, row, field) {
	     if(masterGrid.getItemState(itemIndex) == 'appending' && grid.getColumn(field).fieldName == 'deptName'){
	    	 insertDepartment(grid.getValues(itemIndex));
	    	 grid.commit();
	     }
	}
	
	masterProvider.onRowUpdated = function(provider, row) {
		var data = provider.getJsonRow(row);
		updateDepartment(data);
	};
	
	masterProvider.onRowInserting = function (provider, row, values) {
		var result = true;
		console.log(values)
		if(values[1] == undefined){
				toastr.error(masterGrid.getColumn(1).displayText + "은(는) 빈값일 수 없습니다.");
				result = false;
		}
	    return result;
	};
	
}

/* 기본 셋팅 detailGrid */
function createDetailGrid() {
	detailProvider = new RealGrid.LocalDataProvider();
	detailGrid = new RealGrid.GridView("depart_detail");

	detailGrid.setDataSource(detailProvider);
	detailProvider.setFields(fields1);
	detailGrid.setColumns(columns1);
	detailGrid.footer.visible = false
//	detailGrid.setRowIndicator({
//		visible : false
//	});
	detailGrid.setCheckBar({
		visible : false
	});
	detailGrid.setStateBar({
		visible : false
	});
	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	detailGrid.setDisplayOptions({
		emptyMessage : "표시할 데이타가 없습니다.",
		showEmptyMessage : true,
		rowHeight : 36
	});
	// detailGrid 속성값
	detailGrid.header.height = 40;
	detailGrid.footer.height = 40;
	detailGrid.stateBar.width = 10;
	detailGrid.setEditOptions({
		editable : false
	})
	detailGrid.displayOptions.fitStyle = "even";

	detailGrid.onEditChange = function(grid, index, value) {
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

	setTimeout(function() {
		masterGrid.resetCurrent();
	}, 300);
}
$("#delete").on("click", function(){
	if(detailProvider.getRowCount() > 0){
		Swal.fire({
			icon: 'error',
			title: '삭제 실패',
			text: '해당 부서에 사원이 존재합니다.',
			footer: '<a href='+$.getContextPath() + "/info1/employeeManageRetrieve.do"+'>사원등록으로 이동</a>'
		})
		return;
	}
	row = masterGrid.getCurrent().dataRow;
	data = masterGrid.getValues(masterGrid.getCurrent().itemIndex);
	if(masterGrid.getCurrent().dataRow > -1){
		Swal.fire({
			title: "[ "+data.deptName+" ]<br>정말로 삭제하시겠습니까?",
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
					url : $.getContextPath() + "/info1/departmentDelete.do",
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


function excelExport() {
	RealGrid.exportGrid({
		type : 'excel',
		target : 'local',
		fileName : '부서등록.xlsx',
		exportGrids : [ {
			grid : masterGrid,
			sheetName : '부서등록'
		}]
	})
}

function updateDepartment(value) {
	$.ajax({
		url : $.getContextPath() + "/info1/departmentUpdate.do",
		data : value,
		async : false,
		method : "post",
		dataType : "json",
		success : function(resp) {
			if(resp.result == 'OK'){
				toastr.success(resp.message);
			}else{
				if(Array.isArray(resp.message)){
					for(var idx in resp.message){
						console.log(resp.message)
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

function insertDepartment(value) {
	console.log("insert 들어옴")
	$.ajax({
		url : $.getContextPath() + "/info1/departmentInsert.do",
		data : value,
		async : false,
		method : "post",
		dataType : "json",
		success : function(resp) {
			if(resp.result == 'OK'){
				toastr.success(resp.message);
				masterGrid.commit();
				init();
			}else{
				if(Array.isArray(resp.message)){
					for(var idx in resp.message){
						console.log(resp.message)
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

function start() {
	createMasterGrid();
	createDetailGrid();
	init();
}

// $.document.ready(start);
window.onload = start();
// domloaded를 대신 써도 됩니다.

window.onunload = function() {
	dataProvider.clearRows();

	gridView.destroy();
	dataProvider.destroy();

	gridView = null;
	dataProvider = null;
}

// 1. 각자 메뉴에 맞는 컬럼, 필드 만들기(해당 메뉴에 맞는 컬럼과 필드를 만들어야함, VO와 명칭도 일치해야함)
// 2. 검색, 수정, 삭제시, 조건 알아서 넣기.

// 전화번호(Phone) 컬럼 편집불가 설정.
// gridView.columnByName("Phone").editable = false;
// 전화번호(Phone) 컬럼 편집가능 설정.
// gridView.columnByName("Phone").editable = true;

// -> alert, confirm 나중에 모달로 변경해야함.
/** ******************************* 마스터에 값 넣기 ***************************** */
// 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능
function init(){
	$.ajax({
		url : $.getContextPath() + "/info1/departmentManageRetrieve.do",
		method : "post",
		dataType : "json",
		async : false,
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

/** ******************************* 디테일 설정하기 ***************************** */

function detailGet(masterRow) {
	detailProvider.clearRows();
	if (masterRow >= 0) {
		var mstKey = masterProvider.getValue(masterRow, "deptName");
		// 여기서 ajax로 datas에 값 넣어주기
		// 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능

		$.ajax({
			url : $.getContextPath() + "/info1/employeeManageRetrieve.do",
			data : {
				deptName : mstKey
			},
			method : "post",
			dataType : "json",
			success : function(resp) {
				detailProvider.fillJsonData(resp, {
					fillMode : "set"
				});
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
		// ajax로 검색을 하여 완료되면, 해당 메소드를 이용하여 값 넣어주기.
		// detailProvider.setRows(datas);
	};
	
};

