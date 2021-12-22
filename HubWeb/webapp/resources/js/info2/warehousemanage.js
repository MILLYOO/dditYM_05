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
	fieldName : "warCode",
	dataType : "text"
}, {
	fieldName : "warName",
	dataType : "text",
}, {
	fieldName : "warUse",
	dataType : "text"
}
];

var columns = [
		{
			name : "warCode",
			fieldName : "warCode",
			type : "data",
			width : "60",
			header : {
				text : "Code"
			},
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
		}, {
			name : "warName",
			fieldName : "warName",
			type : "data",
			width : "2",
			header : {
				text : "창고명"
			},
		},
		{
			name: "warUse",
			fieldName: "warUse",
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
				var preData = grid.getValue(dataCell.index.itemIndex, "warName")
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

	masterGrid.onCurrentRowChanged = function(grid, oldRow, newRow) {
		if (newRow < 0) {
			$("#warehouseDetail").find(":input[name]").attr("disabled", true)
					.val("");
			$("#warehouseDetail").find('.fa-bars').attr("hidden", "hidden");
			$("#saved").attr("disabled", true);
			$("#delete").attr("disabled", true);
			$("#searchAdd").attr("disabled", true);

		} else {
			formGet(masterProvider.getJsonRow(newRow));
		}
	};

	masterGrid.onCellEdited = function (grid, itemIndex, row, field) {
	     if(masterGrid.getItemState(itemIndex) == 'appending' && grid.getColumn(field).fieldName == 'warUse'){
	    	 insertWarehouse(grid.getValues(itemIndex));
	    	 grid.commit();
	     }
	}
	
	masterProvider.onRowInserted = function(provider, row) {
		init();
	};
	
	masterProvider.onRowUpdated = function(provider, row) {
		var data = provider.getJsonRow(row);
		updateWarehouse(data);
	};

	masterProvider.onRowInserting = function (provider, row, values) {
		var result = true;
			if(values[1] == undefined){
				toastr.error(masterGrid.getColumn(i).displayText + "은(는) 빈값일 수 없습니다.");
				result = false;
		}
	    return result;
	};
}

function init(){
	$.ajax({
		url : $.getContextPath() + "/info2/warehouseRetrieve.do",
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
	$("#warehouseDetail").ajaxForm({
		url : $.getContextPath() + "/info2/warehouseDetailUpdate.do",
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
			init();
		}
	}).submit();
});

$("#select").on("click",function(){
	masterGrid.cancel();
	$("#searchForm").ajaxForm({
		url : $.getContextPath() + "/info2/warehouseRetrieve.do",
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
			title: "[ " +data.warName + " ]<br>정말로 삭제하시겠습니까?",
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
					url : $.getContextPath() + "/info2/warehouseDelete.do",
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
		url : $.getContextPath() + "/info2/warehouseRetrieve.do",
		data : data,
		method : "post",
		dataType : "json",
		success : function(resp) {
			var data = resp[0];
			if (data != undefined) {
				$("#warehouseDetail").find(":input[name]").attr("disabled", false);
				$("#warUse").attr("disabled", false);
				$("#saved").attr("disabled", false);
				$("#delete").attr("disabled", false);
				$("#searchAdd").attr("disabled", false);
				$("input[name=warCode]").val(data.warCode);
				$("#warehouseDetail").find(":input[name=warName]").val(data.warName);
				$("input[name=warAdd1]").val(data.warAdd1);
				$("input[name=warAdd2]").val(data.warAdd2);
				$("#warehouseDetail").find("input[name=deptName]").val(data.deptName);
				$("#warehouseDetail").find("input[name=empName]").val(data.empName);
				$("input[name=warLine]").val(data.warLine);
				$("#warUse").val(data.warUse).prop("selected", true);
			}
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
};

function updateWarehouse(value) {
	$.ajax({
		url : $.getContextPath() + "/info2/warehouseUpdate.do",
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

function insertWarehouse(value) {
	$.ajax({
		url : $.getContextPath() + "/info2/warehouseInsert.do",
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
		fileName : '창고등록.xlsx',
		exportGrids : [ {
			grid : masterGrid,
			sheetName : '창고등록'
		}, ]
	})
}

function start() {
	createMasterGrid();
	init();
}

// $.document.ready(start);
window.onload = start();

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
warCode = function(obj) {
	$("#war-modalM").css('display', 'flex')
	warObj = $(obj);
	//데이터 가져오기
	$.ajax({
		   url : $.getContextPath() + "/info2/warehouseRetrieve.do",
		   method : "post",
		   dataType : "json",
		   success : function(data){
			   warProviderM.fillJsonData(data, {fillMode: "set"});
		   },
		   error: function(xhr, errorResp, error){
			   console.log(xhr);
			   console.log(errorResp);
			   console.log(error);
		   }
	});
}
empCode = function(obj) {
	$("#emp-modal").css('display', 'flex')
	empObj = $(obj);
}

deptCode = function(obj) {
	$("#dept-modal").css('display', 'flex')
	deptObj = $(obj);
}


$("#searchAdd").on("click", function() {
	new daum.Postcode({
		oncomplete : function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var addr = ''; // 주소 변수
			var extraAddr = ''; // 참고항목 변수

			// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				addr = data.jibunAddress;
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById("warAdd1").value = addr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("warAdd2").focus();
		}
	}).open();
})

$('#warLine').inputmask({
	  mask:"9{1,3}-9{3,4}-9{4}",
	  placeholder: ' ',
	  showMaskOnHover: false,
	  showMaskOnFocus: false,
}); 

