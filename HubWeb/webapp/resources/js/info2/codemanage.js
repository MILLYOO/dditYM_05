// 규격등록
var standardProvider, standardGrid;

// 단위등록
var unitProvider, unitGrid;

// 공정등록
var processProvider, processGrid;

// 분류등록
var registerldivProvider, ldivGrid, registermdivProvider, mdivGrid, registersdivProvider, sdivGrid;

var masterGrid, detailGrid;
// COMMON 
var fields = [
	{
	    fieldName: "commCategory",
	    dataType: "text"
	},
	{
	    fieldName: "commCode",
	    dataType: "text",
	},
	{
	    fieldName: "commName",
	    dataType: "text"
	}
];

var columns = [
	{
		name: "commCategory",
		fieldName : "commCategory",
		type : "data",
		header: {
			text : "타입"
		},
		visible : false
	},
	{
		name: "commCode",
	    fieldName: "commCode",
	    type: "data",
	    width: "180",
	    header: {
	    },
	    editor : {
	    	type : "data",
	    	"inputCharacters": "0-9"
	    },
	    "displayCallback": function(grid, index, value) {
            var code = value;
            if (code) {
                return value.padStart(4, '0');
            }
        },
	    editable : false,
	    visible : false
	},
	{
		name: "commName",
		fieldName : "commName",
		type : "data",
		header: {
		},
	},
];

//DIVISION
var fields1 = [
	{
	    fieldName: "divCategory",
	    dataType: "text"
	},
	{
	    fieldName: "divChild",
	    dataType: "text",
	},
	{
	    fieldName: "divName",
	    dataType: "text"
	},
	{
		fieldName: "divParent",
		dataType: "text"
	}
];

var columns1 = [
	{
		name: "divCategory",
		fieldName : "divCategory",
		type : "data",
		header: {
			text : "타입"
		},
		visible : false
	},
	{
		name: "divChild",
	    fieldName: "divChild",
	    type: "data",
	    width: "180",
	    header: {
	    },
	    editor : {
	    	type : "data",
	    	"inputCharacters": "0-9"
	    },
	    "displayCallback": function(grid, index, value) {
            var code = value;
            if (code) {
                return value.padStart(4, '0');
            }
        },
	    editable : false,
	    visible : false
	},
	{
		name: "divName",
		fieldName : "divName",
		type : "data",
		header: {
		},
	},
	{
		name : "divParent",
		fieldName : "divParent",
		type : "data",
		header: {
			text : "부모"
		},
		visible : false
	}
];

$(".add").on("click", function(){
	var category = $(this).data("category");
	var values = [];
	if(category.includes("div")){
		for(var i = 0 ; i < fields1.length ; i++){
			values.push = "";
		}
	}else{
		for(var i = 0 ; i < fields.length ; i++){
			values.push = "";
		}
	}
    
    switch(category){
    case 'standard' :
    	values[0] = "G";
    	standardProvider.addRow(values);
    	standardGrid.commit();
    	break;
    	
    case 'unit' :
    	values[0] = "U";
    	unitProvider.addRow(values);
    	unitGrid.commit();
    	break;
    	
    case 'process':
    	values[0] = "K";
    	processProvider.addRow(values);
    	processGrid.commit();
    	break;
    
    
	case 'ldiv':
		values[0] = "L";
		registerldivProvider.addRow(values);
		ldivGrid.commit();
		break;
	
	case 'mdiv':
		if(ldivGrid.getCurrent().dataRow > -1){
			values[0] = "M";
			values[3] = ldivGrid.getValue(ldivGrid.getCurrent().dataRow, "divCategory") + ldivGrid.getValue(ldivGrid.getCurrent().dataRow, "divChild")
			registermdivProvider.addRow(values);
			mdivGrid.commit();
		}
		break;
	
	case 'sdiv':
		if(mdivGrid.getCurrent().dataRow > -1){
			console.log(mdivGrid.getCurrent().dataRow)
			var data = sdivGrid.getValues(registersdivProvider.getRowCount()-1);
			values[0] = "S";
			values[3] = mdivGrid.getValue(mdivGrid.getCurrent().dataRow, "divCategory") + mdivGrid.getValue(mdivGrid.getCurrent().dataRow, "divChild")
			registersdivProvider.addRow(values);
			sdivGrid.commit();
		}
		break;
	}
});

$(".save").on("click", function(){
	var category = $(this).data("category");
	var grid;
	var provider;
	switch(category){
	case 'standard' :
		grid = standardGrid;
		provider = standardProvider;
		break;
	case 'unit' :
		grid = unitGrid;
		provider = unitProvider;
		break;
	case 'process':
		grid = processGrid;
		provider = processProvider;
		break;
	case 'ldiv':
		grid = ldivGrid;
		provider = registerldivProvider;
		break;
	case 'mdiv':
		grid = mdivGrid;
		provider = registermdivProvider;
		break;
	case 'sdiv':
		grid = sdivGrid;
		provider = registersdivProvider;
	}
	var rows;
	var data;
	rows = grid.getCheckedItems();
	if(rows){
		for(var i = 0 ; i < rows.length ; i++){
			if(provider.getRowState(rows[i]) == "created"){
				data = grid.getValues(rows[i]);
				if(category.includes("div")){
					if(data.divName == "" || !data.divName){
						Swal.fire("빈 값은 저장할 수 없습니다.");
						return;
					}
					$.ajax({
						url : $.getContextPath() + "/info2/divManageInsert.do",
						data : data,
						method : "post",
						dataType : "text",
						async: false,
						success : function(resp) {
							Swal.fire(resp);
						},
						error : function(xhr, errorResp, error) {
							console.log(xhr);
							console.log(errorResp);
							console.log(error);
						}
					});
				}else{
					if(data.commName == "" || !data.commName){
						Swal.fire("빈 값은 저장할 수 없습니다.");
						return;
					}
					$.ajax({
						url : $.getContextPath() + "/info2/codeManageInsert.do",
						data : data,
						method : "post",
						dataType : "text",
						async: false,
						success : function(resp) {
							Swal.fire(resp);
						},
						error : function(xhr, errorResp, error) {
							console.log(xhr);
							console.log(errorResp);
							console.log(error);
						}
					});
				}
			}else if(provider.getRowState(rows[i]) == "updated"){
				data = grid.getValues(rows[i]);
				if(category.includes("div")){
					if(data.divName == "" || !data.divName){
						Swal.fire("빈 값은 저장할 수 없습니다.");
						return;
					}
					$.ajax({
						url : $.getContextPath() + "/info2/divManageInsert.do",
						data : data,
						method : "post",
						dataType : "text",
						async: false,
						success : function(resp) {
							Swal.fire(resp);
						},
						error : function(xhr, errorResp, error) {
							console.log(xhr);
							console.log(errorResp);
							console.log(error);
						}
					});
				}else{
					if(data.commName == "" || !data.commName){
						Swal.fire("빈 값은 저장할 수 없습니다.");
						return;
					}
					$.ajax({
						url : $.getContextPath() + "/info2/codeManageUpdate.do",
						data : data,
						method : "post",
						dataType : "text",
						async: false,
						success : function(resp) {
							Swal.fire(resp);
						},
						error : function(xhr, errorResp, error) {
							console.log(xhr);
							console.log(errorResp);
							console.log(error);
						}
					});
				}
			}
		}
		switch(category){
		case 'standard' :
			setStandard();
			break;
		case 'unit' :
			setUnit();
			break;
		case 'process':
			setProcess();
			break;
		case 'ldiv':
			setLdiv();
			break;
		case 'mdiv':
			mdivGet(ldivGrid.getCurrent().dataRow);
			break;
		case 'sdiv':
			sdivGet(mdivGrid.getCurrent().dataRow);
			break;
		}
	}else{
		Swal.fire("추가/수정 항목을 체크 해주세요");
	}
})



$(".del").on("click", function(){
	
	var category = $(this).data("category");
	var grid;
	var provider;
	switch(category){
	case 'standard' :
		grid = standardGrid;
		provider = standardProvider;
		break;
	case 'unit' :
		grid = unitGrid;
		provider = unitProvider;
		break;
	case 'process':
		grid = processGrid;
		provider = processProvider;
		break;
	case 'ldiv':
		grid = ldivGrid;
		provider = registerldivProvider;
		break;
	case 'mdiv':
		grid = mdivGrid;
		provider = registermdivProvider;
		break;
	case 'sdiv':
		grid = sdivGrid;
		provider = registersdivProvider;
	}
	var rows;
	var data;
	rows = grid.getCheckedRows();
	if(rows.length > 0){
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
			console.log(result);
			for(var i = rows.length-1 ; i >= 0 ; i--){
				if(provider.getRowState(rows[i]) == "created"){
					provider.removeRow(rows[i]);
					continue;
				}
				data = grid.getValues(rows[i]);
				if(category.includes("div")){
					$.ajax({
						url : $.getContextPath() + "/info2/divManageDelete.do",
						data : data,
						method : "post",
						dataType : "text",
						async: false,
						success : function(resp) {
							Swal.fire(resp);
							provider.removeRow(rows[i]);
						},
						error : function(xhr, errorResp, error) {
							console.log(xhr);
							console.log(errorResp);
							console.log(error);
						}
					});
				}else{
					$.ajax({
						url : $.getContextPath() + "/info2/codeManageDelete.do",
						data : data,
						method : "post",
						dataType : "text",
						async: false,
						success : function(resp) {
							Swal.fire(resp);
							provider.removeRow(rows[i]);
						},
						error : function(xhr, errorResp, error) {
							console.log(xhr);
							console.log(errorResp);
							console.log(error);
						}
					});
				}
			}// for문
			}
		});
	}else{
		Swal.fire({
			  icon: 'error',
			  title: '삭제할 항목을 체크해주세요',
		})
	}
});

function createStandardGrid() {
	  console.log("createStandardGrid()");
	  standardProvider = new RealGrid.LocalDataProvider();
	  standardGrid = new RealGrid.GridView("register_standard");

	  standardGrid.setDataSource(standardProvider);
	  standardProvider.setFields(fields);
	  standardGrid.setColumns(columns);
	  standardGrid.resetCurrent();
	  standardGrid.footer.visible=false;
	  standardGrid.displayOptions.fitStyle = "even";
	  // 개별 컬럼 text 설정
	  let col1 = standardGrid.columnByName('commCode');
	  col1.header.text = "규격코드";
	  let col2 = standardGrid.columnByName('commName');
	  col2.header.text = "규격명";
	  
	  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	  standardGrid.setDisplayOptions({
		  emptyMessage : "표시할 데이타가 없습니다."
		  , showEmptyMessage: true
		  , rowHeight : 30
		  });
	  standardGrid.setEditOptions({
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
	  standardGrid.header.height = 20;
	  standardGrid.footer.height = 40;
	  standardGrid.stateBar.width = 10;
	  
}

function createUnitGrid() {
	console.log("createUnitGrid()");
	unitProvider = new RealGrid.LocalDataProvider();
	unitGrid = new RealGrid.GridView("register_unit");

	unitGrid.setDataSource(unitProvider);
	unitProvider.setFields(fields);
	unitGrid.setColumns(columns);
	unitGrid.resetCurrent();
	unitGrid.footer.visible=false
	unitGrid.displayOptions.fitStyle = "even";
	let col1 = unitGrid.columnByName('commCode');
	col1.header.text = "단위코드";
	let col2 = unitGrid.columnByName('commName');
	col2.header.text = "단위명";
	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	unitGrid.setDisplayOptions({
		emptyMessage : "표시할 데이타가 없습니다."
			, showEmptyMessage: true
			, rowHeight : 30
	});
	unitGrid.setEditOptions({
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
	unitGrid.header.height = 20;
	unitGrid.footer.height = 40;
	unitGrid.stateBar.width = 10;
}


function createProcessGrid() {
	console.log("createProdcessGrid()");
	processProvider = new RealGrid.LocalDataProvider();
	processGrid = new RealGrid.GridView("register_process");
	
	processGrid.setDataSource(processProvider);
	processProvider.setFields(fields);
	processGrid.setColumns(columns);
	processGrid.resetCurrent();
	processGrid.footer.visible=false
	processGrid.displayOptions.fitStyle = "even";
	let col1 = processGrid.columnByName('commCode');
	col1.header.text = "공정코드";
	let col2 = processGrid.columnByName('commName');
	col2.header.text = "공정명";
	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	processGrid.setDisplayOptions({
		emptyMessage : "표시할 데이타가 없습니다."
			, showEmptyMessage: true
			, rowHeight : 30
	});
	processGrid.setEditOptions({
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
	processGrid.header.height = 20;
	processGrid.footer.height = 40;
	processGrid.stateBar.width = 10;
	
}

function createLdivGrid(){
	console.log("createLdivGrid()");
	registerldivProvider = new RealGrid.LocalDataProvider();
	ldivGrid = new RealGrid.GridView("register_ldiv");
	
	ldivGrid.setDataSource(registerldivProvider);
	registerldivProvider.setFields(fields1);
	ldivGrid.setColumns(columns1);
	ldivGrid.resetCurrent();
	ldivGrid.footer.visible=false
	ldivGrid.displayOptions.fitStyle = "even";
	let col1 = ldivGrid.columnByName('divChild');
	col1.header.text = "대분류코드";
	let col2 = ldivGrid.columnByName('divName');
	col2.header.text = "대분류명";
	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	ldivGrid.setDisplayOptions({
		emptyMessage : "표시할 데이타가 없습니다."
			, showEmptyMessage: true
			, rowHeight : 30
	});
	ldivGrid.setEditOptions({
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
	ldivGrid.header.height = 20;
	ldivGrid.footer.height = 40;
	ldivGrid.stateBar.width = 10;

	ldivGrid.onCurrentRowChanged = function (grid, oldRow, newRow) {
		if(newRow == -1)
			return;
		mdivGet(newRow);
		registersdivProvider.clearRows();
	};
}
function createMdivGrid(){
	console.log("createLdivGrid()");
	registermdivProvider = new RealGrid.LocalDataProvider();
	mdivGrid = new RealGrid.GridView("register_mdiv");
	
	mdivGrid.setDataSource(registermdivProvider);
	registermdivProvider.setFields(fields1);
	mdivGrid.setColumns(columns1);
	mdivGrid.resetCurrent();
	mdivGrid.footer.visible=false
	mdivGrid.displayOptions.fitStyle = "even";
	let col1 = mdivGrid.columnByName('divChild');
	col1.header.text = "중분류코드";
	let col2 = mdivGrid.columnByName('divName');
	col2.header.text = "중분류명";
	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	mdivGrid.setDisplayOptions({
		emptyMessage : "표시할 데이타가 없습니다."
			, showEmptyMessage: true
			, rowHeight : 30
	});
	mdivGrid.setEditOptions({
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
	mdivGrid.header.height = 20;
	mdivGrid.footer.height = 40;
	mdivGrid.stateBar.width = 10;
	
	mdivGrid.onCurrentRowChanged = function (grid, oldRow, newRow) {
		if(newRow == -1)
			return;
		sdivGet(newRow);
	};
}

function createSdivGrid(){
	console.log("createLdivGrid()");
	registersdivProvider = new RealGrid.LocalDataProvider();
	sdivGrid = new RealGrid.GridView("register_sdiv");
	
	sdivGrid.setDataSource(registersdivProvider);
	registersdivProvider.setFields(fields1);
	sdivGrid.setColumns(columns1);
	sdivGrid.resetCurrent();
	sdivGrid.footer.visible=false
	sdivGrid.displayOptions.fitStyle = "even";
	let col1 = sdivGrid.columnByName('divChild');
	col1.header.text = "소분류코드";
	let col2 = sdivGrid.columnByName('divName');
	col2.header.text = "소분류명";
	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	sdivGrid.setDisplayOptions({
		emptyMessage : "표시할 데이타가 없습니다."
			, showEmptyMessage: true
			, rowHeight : 30
	});
	sdivGrid.setEditOptions({
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
	sdivGrid.header.height = 20;
	sdivGrid.footer.height = 40;
	sdivGrid.stateBar.width = 10;
}

// mdiv, sdiv 채우기
function mdivGet(ldivRow) {
	  registermdivProvider.clearRows();
	  if(registerldivProvider.getRowState(ldivRow) != "created"){
		  if (ldivRow >= 0) {
		      var data = ldivGrid.getValues(ldivRow);
		     $.ajax({
				url : $.getContextPath()+"/info2/div/divList.do",
				data : data,
				method : "post",
				dataType : "json",
				success : function(resp) {
				  registermdivProvider.fillJsonData(resp, {fillMode: "set"});
				  mdivGrid.setCurrent(0);
				  console.log(">>>>>>>>ASDAD>>>>>>>>>>>>")
				  console.log(resp)
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			});
		  };
	  }
};

function sdivGet(mdivRow) {
	registersdivProvider.clearRows();
	if(registermdivProvider.getRowState(mdivRow) != "created"){
	if (mdivRow >= 0) {
		var data = mdivGrid.getValues(mdivRow);
		$.ajax({
			url : $.getContextPath()+"/info2/div/divList.do",
			data : data,
			method : "post",
			dataType : "json",
			success : function(resp) {
				registersdivProvider.fillJsonData(resp, {fillMode: "set"});
				sdivGrid.setCurrent(0);
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	};
	}
};

// 최초 url 데이터 채우기
function setUnit(){
	$.ajax({
		url : $.getContextPath() + "/info2/common/unit.do",
		dataType : "json",
		success : function(resp) {
			unitProvider.fillJsonData(resp, {fillMode: "set"});
			unitGrid.setCurrent(0);
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
}

function setStandard(){
	$.ajax({
		url : $.getContextPath() + "/info2/common/standard.do",
		dataType : "json",
		success : function(resp) {
			standardProvider.fillJsonData(resp, {fillMode: "set"});
			standardGrid.setCurrent(0);
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
}

function setProcess(){
	$.ajax({
		url : $.getContextPath() + "/info2/common/process.do",
		dataType : "json",
		success : function(resp) {
			processProvider.fillJsonData(resp, {fillMode: "set"});
			processGrid.setCurrent(0);
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
}

function setLdiv(){
	$.ajax({
		url : $.getContextPath() +"/info2/div/divList.do",
		dataType : "json",
		success : function(resp) {
			registerldivProvider.fillJsonData(resp, {fillMode: "set"});
			ldivGrid.setCurrent(0);
			console.log(">>>>>>>>>>>>>>>>>>>>>>>>")
			console.log(resp);
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
		fileName : '규격/분류/단위/공정.xlsx',
		exportGrids : [ {
			grid : unitGrid,
			sheetName : '단위'
		},{
			grid : standardGrid,
			sheetName : '규격'
		},{
			grid : processGrid,
			sheetName : '공정'
		}, {
			grid : ldivGrid,
			sheetName : '대분류'
		}, {
			grid : mdivGrid,
			sheetName : '중분류'
		}, {
			grid : sdivGrid,
			sheetName : '소분류'
		} ]
	})
}

function init(){
	createStandardGrid();
	createUnitGrid();
	createProcessGrid();
	createLdivGrid();
	createMdivGrid();
	createSdivGrid();
	setProcess();
	setUnit();
	setStandard();
	setLdiv();
}


function start() {
	init();
	console.log("실행");
}

// $.document.ready(start);
window.onload = start();
// domloaded를 대신 써도 됩니다.

//규격등록
var standardProvider, standardGrid;

// 단위등록
var unitProvider, unitGrid;

// 공정등록
var processProvider, processGrid;

// 분류등록
var registerldivProvider, ldivGrid, registermdivProvider, mdivGrid, registersdivProvider, sdivGrid;

var masterGrid, detailGrid;

window.onunload = function() {
	standardProvider.clearRows();
	unitProvider.clearRows();
	processProvider.clearRows();
	registerldivProvider.clearRows();
	registermdivProvider.clearRows();
	registersdivProvider.clearRows();

	processGrid.destroy();
	unitGrid.destroy();
	standardGrid.destroy();
	mdivGrid.destroy();
	ldivGrid.destroy();
	sdivGrid.destroy();
	standardProvider.destroy();
	unitProvider.destroy();
	processProvider.destroy();
	registerldivProvider.destroy();
	registermdivProvider.destroy();
	registersdivProvider.destroy();

	processGrid = null;
	unitGrid = null;
	standardGrid = null;
	mdivGrid = null;
	ldivGrid = null;
	sdivGrid = null;
	standardProvider = null;
	unitProvider = null;
	processProvider = null;
	registerldivProvider = null;
	registermdivProvider = null;
	registersdivProvider = null;
}