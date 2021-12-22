var masterProvider, masterGrid;
var detaileProvider, detailGrid;
var treeProvider, treeView;
// ////////////////////////////////////////////////////
/* 마스터그리드 컬럼 필드 */
// ////////////////////////////////////////////////////
var fields = [ {
	fieldName : "bomCode",
	dataType : "int"
}, {
	fieldName : "icodeName",
	dataType : "text"
}, {
	fieldName : "rawsCode",
	dataType : "text",
}, {
	fieldName : "rawsName",
	dataType : "text"
}, {
	fieldName : "gcommName",
	dataType : "text"
} ];

var columns = [ {
	name : "bomCode",
	fieldName : "bomCode",
	type : "data",
	width : "10",
	header : {
		text : "bom코드"
	},
	visible : false,
}, {
	name : "icodeName",
	fieldName : "icodeName",
	width : "100",
	"sortable" : false,
	"lookupDisplay" : true,
	"editor" : {
		"type" : "dropdown",
		"dropDownCount" : 2,
		"domainOnly" : true,
		"dropDownWhenClick" : true,
		"textReadOnly" : true,
	},
	"values" : [ "제품", "반제품" ],
	"labels" : [ "제품", "반제품" ],
	header : {
		text : "품목계정"
	},
}, {
	name : "rawsCode",
	fieldName : "rawsCode",
	type : "data",
	width : "100",
	header : {
		text : "품목코드"
	},
	button : "action",
	styleCallback : function(grid, dataCell) {
		var ret = {}
		var preData = grid.getValue(dataCell.index.itemIndex, "icodeName")
		if (dataCell.item.rowState == 'created'
				|| dataCell.item.itemState == 'appending'
				|| dataCell.item.itemState == 'inserting') {
			if(preData == undefined){
				ret.editable=false;
				masterGrid.buttonVisibility = "hidden";
			}
		}
		return ret;
	}
}, {
	name : "rawsName",
	fieldName : "rawsName",
	type : "data",
	width : "300",
	header : {
		text : "품목명"
	},
	styleName : "left-column",
	editable : false
}, {
	name : "gcommName",
	fieldName : "gcommName",
	type : "data",
	width : "300",
	header : {
		text : "규격"
	},
	styleName : "left-column",
	editable : false
} ]
// ////////////////////////////////////////////////////
/* 디테일그리드 컬럼 필드 */
// ////////////////////////////////////////////////////
var fields1 = [ 
{
	fieldName : "bomNo",
	dataType : "text"
},
{
	fieldName : "bomCode",
	dataType : "text"
},	
{
	fieldName : "icodeName",
	dataType : "text"
}, {
	fieldName : "rawsCode",
	dataType : "text"
}, {
	fieldName : "rawsName",
	dataType : "text"
}, {
	fieldName : "gcommName",
	dataType : "text"
}, {
	fieldName : "brawNetqty",
	dataType : "number"
}, {
	fieldName : "brawLossrate",
	dataType : "number"
}, {
	fieldName : "brawRequireqty",
	dataType : "number"
} ];

var columns1 = [
	{
		name : "bomNo",
		fieldName : "bomNo",
		type : "data",
		width : "10",
		header : {
			text : "순번"
		},
		visible : false,
	},{
		name : "bomCode",
		fieldName : "bomCode",
		type : "data",
		width : "10",
		header : {
			text : "bom코드"
		},
		visible : false,
	},{
	name : "icodeName",
	fieldName : "icodeName",
	width : "10",
	"sortable" : false,
	"lookupDisplay" : true,
	"editor" : {
		"type" : "dropdown",
		"dropDownCount" : 4,
		"domainOnly" : true,
		"dropDownWhenClick" : true,
		"textReadOnly" : true,
	},
	"values" : [ "원재료", "부재료", "반제품", "부산품" ],
	"labels" : [ "원재료", "부재료", "반제품", "부산품" ],
	header : {
		text : "품목계정"
	},
}, {
	name : "rawsCode",
	fieldName : "rawsCode",
	type : "data",
	width : "10",
	header : {
		text : "품목코드"
	},
	button : "action"
}, {
	name : "rawsName",
	fieldName : "rawsName",
	type : "data",
	width : "10",
	header : {
		text : "품목명"
	},
	styleName : "left-column",
	editable : false
}, {
	name : "gcommName",
	fieldName : "gcommName",
	type : "data",
	width : "10",
	header : {
		text : "규격"
	},
	styleName : "left-column",
	editable : false
}, {
	name : "brawNetqty",
	fieldName : "brawNetqty",
	width : "10",
	header : {
		text : "정미수량"
	},
	"sortable": false,
	  "editor": {
	    "type": "number",
	    "textAlignment": "far",
	    "editFormat": "#,##0.##",
	  },
	  "numberFormat": "#,##0.##",
}, {
	name : "brawLossrate",
	fieldName : "brawLossrate",
	width : "10",
	header : {
		text : "로스율"
	},
	"editor": {
	    "type": "number",
	    "textAlignment": "far",
	    "editFormat": "#,##0",
	  },
	  "numberFormat": "#,##0",
	  defaultValue : 0
}, {
	name : "brawRequireqty",
	fieldName : "brawRequireqty",
	width : "10",
	header : {
		text : "필요수량"
	},
	"sortable": false,
	  "editor": {
	    "type": "number",
	    "textAlignment": "far",
	    "editFormat": "#,##0.##",
	  },
	  "numberFormat": "#,##0.##",
} ]
// ////////////////////////////////////////////////////
/* 트리 컬럼 필드 */
// ////////////////////////////////////////////////////
var fields3 = [  {
	fieldName : "rawsName",
	dataType : "text"
}, {
	fieldName : "rawsCode",
	dataType : "text",
}, {
	fieldName : "icodeName",
	dataType : "text"
}, {
	fieldName : "gcommName",
	dataType : "text"
},{
	fieldName : "brawRequireqty",
	dataType : "text"
} ];
var columns3 = [
	{
		name : "rawsName",
		fieldName : "rawsName",
		type : "data",
		width : "10",
		header : {
			text : "품명"
		},
	},{
		name : "rawsCode",
		fieldName : "rawsCode",
		type : "data",
		width : "10",
		header : {
			text : "품목코드"
		},
	},{
		name : "icodeName",
		fieldName : "icodeName",
		type : "data",
		width : "10",
		header : {
			text : "품목계정"
		},
	},{
		name : "gcommName",
		fieldName : "gcommName",
		type : "data",
		width : "10",
		header : {
			text : "규격"
		},
	},{
		name : "brawRequireqty",
		fieldName : "brawRequireqty",
		type : "data",
		width : "10",
		header : {
			text : "필요수량"
		},
	},]
// ////////////////////////////////////////////////
/* 마스터그리드 */
// ////////////////////////////////////////////////
function createMasterGrid() {
	masterProvider = new RealGrid.LocalDataProvider();
	masterGrid = new RealGrid.GridView("realgrid_BOMmaster");

	masterGrid.setDataSource(masterProvider);
	masterProvider.setFields(fields);
	masterGrid.setColumns(columns);
	masterGrid.resetCurrent();
	masterGrid.displayOptions.fitStyle = "even";
	masterGrid.footer.visible = false
//	masterGrid.setRowIndicator({
//	 visible : false
//	});
	masterGrid.setCheckBar({
	 visible : false
	});
	masterGrid.setStateBar({
	 visible : false
	});
	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	masterGrid.setDisplayOptions({
		insertable : true,
		appendable : true,
		editable : true,
		deletable : true,
		confirmWhenDelete : true,
		deleteRowsMessage : "정말로 삭제 하시겠습니까?",
		enterToTab : true,
		commitByCell : true,
		rowHeight : 36
	});

	masterProvider.onValueChanged = function (provider, row, field) {
		detailProvider.clearRows();
		treeProvider.clearRows();
	     if(field == 3){
	    	 CheckBomCode(row)
	    	 console.log(masterGrid.getValues(0))
	    	 if(masterGrid.getValues(0).bomCode != undefined){
	    		 detailGet(row);
	    		 treeGet(masterGrid.getValues(0).bomCode);
	    	 }
	     }
	};
	
// masterProvider.setRowCount(1);
	masterProvider.addRow([]);
	// masterGrid 속성값
	masterGrid.header.height = 25;
	masterGrid.footer.height = 30;
	masterGrid.stateBar.width = 10;
	
	masterGrid.onItemChecked = function (grid, itemIndex, checked) {
// detailGrid.checkAll();
	};
}

function CheckBomCode(masterRow){
	$.ajax({
		url : $.getContextPath() + "/info2/bomCodeRetrieve.do",
		data : {
			icodeName : masterProvider.getValue(masterRow, "icodeName"),
			rawsCode : masterProvider.getValue(masterRow, "rawsCode"),
			},
		method : "post",
		dataType : "text",
		async : false,
		success : function(resp) {
			console.log(resp)
			masterGrid.setValue(0, 'bomCode', resp);
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
}

// ////////////////////////////////////////////////
/* 디테일그리드 */
// ////////////////////////////////////////////////
function createDetailGrid() {
	detailProvider = new RealGrid.LocalDataProvider();
	detailGrid = new RealGrid.GridView("realgrid_BOMdetail");

	detailGrid.setDataSource(detailProvider);
	detailProvider.setFields(fields1);
	detailGrid.setColumns(columns1);
	detailGrid.resetCurrent();
	detailGrid.displayOptions.fitStyle = "even";
	detailGrid.footer.visible = false;
	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	detailGrid.setDisplayOptions({
		emptyMessage : "표시할 데이타가 없습니다.",
		showEmptyMessage : true,
		rowHeight : 36
	});
	detailGrid.setStateBar({
		 visible : false
	});
	// masterGrid 속성값
	detailGrid.header.height = 40;
	detailGrid.footer.height = 40;
	detailGrid.stateBar.width = 10;
	detailGrid.setStateBar({
// visible : false
	});
	// gridViewEditOptions 그리드뷰 edit 옵션
	detailGrid.setEditOptions({
		insertable : true,
		appendable : true,
		editable : true,
		deletable : true,
		confirmWhenDelete : true,
		deleteRowsMessage : "정말로 삭제 하시겠습니까?",
		enterToTab : true,
		commitByCell : true
	})
	
	detailGrid.setRowStyleCallback(function(grid, item, fixed) {
		switch (item.rowState) {
		case "created":
			return "lightskyblue-color";
		case "updated":
			return "lightcyan-color";
		}
	})

	detailProvider.onRowInserting = function(provider, row, values) {
		var result = true;
		for (var i = 2; i < values.length; i++) {
			if (values[i] == undefined || values[i] == null || values[i] == '') {
				toastr.error(detailGrid.getColumn(i).displayText
						+ "은(는) 빈값일 수 없습니다.");
				result = false;
			}
		}
		return result;
	};
	
	detailGrid.onEditRowChanged = function(grid, itemIndex, dataRow, field,
			oldValue, newValue) {
		// 변경된 사항이 수량의 필드넘버면 해당 로직을 실행한다.
		console.log("onEditRowChanged, " + field + ": " + oldValue + " => "
				+ newValue);
		if(field == 7 || field == 6){
				var cur = detailGrid.getCurrent();
				var row = cur.dataRow;
				var index = cur.itemIndex;
		    	var qty = grid.getValue(index, "brawNetqty");
		    	var loss = grid.getValue(index, "brawLossrate");
		    	
		    	var result = Math.ceil(qty + (qty * (loss/100)));
				detailGrid.setValue(row, 'brawRequireqty',result);
		}
	};
}

function detailGet(masterRow) {
	detailProvider.clearRows();
	console.log('들어옴')
	if (masterRow >= 0) {
			// 여기서 ajax로 datas에 값 넣어주기
		    // 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능
		$.ajax({
			url : $.getContextPath() + "/info2/bomDetailRetrieve.do",
			data : {
				icodeName : masterProvider.getValue(masterRow, "icodeName"),
				rawsCode : masterProvider.getValue(masterRow, "rawsCode")
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
// detailProvider.setRows(datas);
	};
};

function detailGet(masterRow) {
	if (masterRow >= 0) {
		// 여기서 ajax로 datas에 값 넣어주기
		// 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능
		$.ajax({
			url : $.getContextPath() + "/info2/bomDetailRetrieve.do",
			data : {
				icodeName : masterProvider.getValue(masterRow, "icodeName"),
				rawsCode : masterProvider.getValue(masterRow, "rawsCode")
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
// detailProvider.setRows(datas);
	};
};

// ////////////////////////////////////////////////////////////////////

function createTreeViewGrid() {
	  treeProvider = new RealGrid.LocalTreeDataProvider();
	  treeView = new RealGrid.TreeView("realgrid_BOMview");

	  treeView.setDataSource(treeProvider);
	  treeProvider.setFields(fields3);
	  treeView.setColumns(columns3);
	  treeView.displayOptions.fitStyle = "even";
	  treeView.setDisplayOptions({
			emptyMessage : "표시할 데이타가 없습니다.",
			showEmptyMessage : true,
			rowHeight : 36
	  });
	  treeView.setRowIndicator({
			 visible : false
			});
	  treeView.setCheckBar({
		 visible : false
		});
	  treeView.setStateBar({
		 visible : false
		});
	  treeView.footer.visible = false;
	  treeView.editOptions.editable = false;
	  treeView.displayOptions.emptyMessage = "표시할 데이타가 없습니다.";
	  treeView.displayOptions.rowHeight = 36;
	  treeView.header.height = 40;
	  treeView.footer.height = 40;
	  treeView.stateBar.width = 16;

	}

function treeGet(bomCode) {
		$.ajax({
			url : $.getContextPath() + "/info2/bomRetrieve.do",
			data : {'bomCode' : bomCode},
			method : "post",
			dataType : "json",
			success : function(resp) {
				var bomRawsList = new Array();
				resp.brawRequireqty = '-';
				bomRawsList.push(resp);
				var obj = {"bomRawsList" : bomRawsList};
				treeProvider.setObjectRows(obj, "bomRawsList", "", "");
				treeView.expandAll();
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
	createTreeViewGrid();
}

window.onload = start();

window.onunload = function() {
	/* master */
	masterProvider.clearRows();

	masterGrid.destroy();
	masterProvider.destroy();

	masterGrid = null;
	masterProvider = null;
	/* detail */
	detailProvider.clearRows();

	detailGrid.destroy();
	detailProvider.destroy();

	detailGrid = null;
	detailProvider = null;
	/* view */
	viewProvider.clearRows();

	viewGrid.destroy();
	viewProvider.destroy();

	viewGrid = null;
	viewProvider = null;
}

$("#saved").on("click", function() {
	var data = masterProvider.getJsonRows(masterGrid.getCurrent().dataRow);
	console.log(data);
	if(data[0].rawsCode != undefined){
		if(data[0].bomCode == undefined){
			// bom 인서트
			$.ajax({
				url : $.getContextPath() + "/info2/bomInsert.do",
				data : data[0],
				method : "post",
				dataType : "json",
				success : function(resp) {
					if(resp.result == 'OK'){
						toastr.success(resp.message);
						CheckBomCode(masterGrid.getCurrent().dataRow);
						masterGrid.checkAll(false);
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
			});
		}else{
			Swal.fire({
				  icon: 'error',
				  title: '저장된 항목입니다.',
			})
		}
	}else{
		Swal.fire({
			  icon: 'error',
			  title: '품목을 선택해 주세요',
		})
	}
});

$("#delete").on("click", function(){
	row = masterGrid.getCurrent().dataRow;
	data = masterGrid.getValues(masterGrid.getCurrent().itemIndex);
	if(masterGrid.getCurrent().dataRow > -1){
		Swal.fire({
			title: "정말로 삭제하시겠습니까?",
			text: "모품목 삭제 시 등록된 자품목도 모두 삭제됩니다.",
			icon: "warning",
			showCancelButton: true,
			confirmButtonColor: "#3085d6",
			cancelButtonColor: "#d33",
			confirmButtonText: "승인",
			cancelButtonText: "취소"
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url : $.getContextPath() + "/info2/bomDelete.do",
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
							detailProvider.clearRows();
							masterProvider.addRow([]);
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

$("#append1").on("click", function() {
	if( masterGrid.getCurrent().dataRow == -1){
		Swal.fire({
			  icon: 'error',
			  title: '품목을 선택해 주세요',
		})
		return;
	}else if(masterGrid.getValue(0, 'bomCode') == null){
		Swal.fire({
			  icon: 'error',
			  title: '모품목을 저장해 주세요',
		})
		return;
	}
	var count = detailProvider.getRowStateCount("created");
	var values = [];
	for (var i = 0; i < fields1.length; i++) {
		values.push = "";
	}
	values[1] = masterGrid.getValues(0).bomCode;
	detailProvider.addRow(values);
});

$("#saved1").on("click", function() {
	var checkedList = detailGrid.getCheckedItems();
	var dataList = [];
	var obj;
	if(checkedList.length > 0){
		for(var i = 0 ; i < checkedList.length ; i++){
				dataList.push(detailGrid.getValues(checkedList[i]));
			}
		}
		if(dataList.length > 0 ){
			obj = {"commonList" : dataList}
			$.ajax({
				url : $.getContextPath() + "/info2/bomRawsCreateOrUpdate.do",
				data : JSON.stringify(obj),
				method : "post",
				dataType : "json",
				async: false,
				contentType : "application/json",
				success : function(resp) {
					if(resp.result == 'OK'){
						toastr.success(resp.message);
						detailGrid.checkAll(false);
						$.ajax({
							url : $.getContextPath() + "/info2/bomDetailRetrieve.do",
							data : {
								icodeName : masterProvider.getValue(masterGrid.getCurrent().dataRow, "icodeName"),
								rawsCode : masterProvider.getValue(masterGrid.getCurrent().dataRow, "rawsCode")
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
						treeGet(masterGrid.getValues(0).bomCode);
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
		Swal.fire({
			  icon: 'error',
			  title: '추가/수정 항목을 체크해주세요',
		})
	}
});

$("#delete1").on("click", function() {
	if( masterGrid.getCurrent().dataRow == -1){
		Swal.fire({
			  icon: 'error',
			  title: '품목을 선택해 주세요',
		})
		return;
	}else if(masterGrid.getValue(0, 'bomCode') == null){
		Swal.fire({
			  icon: 'error',
			  title: '모품목을 저장해 주세요',
		})
		return;
	}
	var checkedList = detailGrid.getCheckedItems();
	var dataList = [];
	var obj;
	if(checkedList.length > 0){
		Swal.fire({
			title: "정말로 삭제하시겠습니까?",
			text: "다시 한 번 확인해주세요",
			icon: "warning",
			showCancelButton: true,
			confirmButtonColor: "#3085d6",
			cancelButtonColor: "#d33",
			confirmButtonText: "승인",
			cancelButtonText: "취소"
		}).then((result) => {
		if (result.isConfirmed) {
		for(var i = checkedList.length-1 ; i >= 0 ; i--){
			if(detailGrid.getValues(checkedList[i]).bomNo == undefined){
				detailProvider.removeRow(checkedList[i]); 
				continue;
			}
			dataList.push(detailGrid.getValues(checkedList[i]));
		}
		
		if(dataList.length > 0 ){
			obj = {"commonList" : dataList}
			$.ajax({
				url : $.getContextPath() + "/info2/bomRawDelete.do",
				data : JSON.stringify(obj),
				method : "post",
				dataType : "json",
				async: false,
				contentType : "application/json",
				success : function(resp) {
					if(resp.result == 'OK'){
						toastr.success(resp.message);
						detailGrid.checkAll(false);
						console.log(checkedList);
						for(var i = checkedList.length-1 ; i >= 0 ; i--){
							detailProvider.removeRow(checkedList[i]);
						}
						treeGet(masterGrid.getValues(0).bomCode);
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
		}
		})
	}else{
		Swal.fire({
			  icon: 'error',
			  title: '삭제할 항목을 체크해주세요',
		})
	}
})
// /////////////////////////////////////////////////////////////////////////////
// 모달 뛰우기 버튼
// 셀버튼 클릭 시 이벤트 발생
masterGrid.onCellButtonClicked = function(grid, index, column) {
	masterGrid.commit();
	if (column.fieldName === 'rawsCode' || column.fieldName === 'prodCode'
			|| column.fieldName === 'itemCode') {
		// 품목 리스트 모달창 띄우기
		$("#item-modalM").css('display', 'flex');
		let icodeName = masterGrid.getValue(index.dataRow, "icodeName");
		// 데이터 가져오기
		$.ajax({
			url : $.getContextPath() + "/info2/itemManageRetrieve.do",
			data : {
				'icodeName' : icodeName
			},
			method : "post",
			dataType : "json",
			success : function(data) {
				itemProviderM.fillJsonData(data, {
					fillMode : "set"
				});
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		})
	}
}
detailGrid.onCellButtonClicked = function(grid, index, column) {
	detailGrid.commit();
	if (column.fieldName === 'rawsCode' || column.fieldName === 'prodCode'
			|| column.fieldName === 'itemCode') {
		// 품목 리스트 모달창 띄우기
		$("#item-modal").css('display', 'flex');
		let icodeName = detailGrid.getValue(index.dataRow, "icodeName");
		// 데이터 가져오기
		$.ajax({
			url : $.getContextPath() + "/info2/itemManageRetrieve.do",
			data : {
				'icodeName' : icodeName
			},
			method : "post",
			dataType : "json",
			success : function(data) {
				itemProvider.fillJsonData(data, {
					fillMode : "set"
				});
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		})
	}
}

// ///////////////////////////////////////////////////////////////////////////////////

