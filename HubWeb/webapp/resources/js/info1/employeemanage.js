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
	fieldName : "empCode",
	dataType : "text"
}, {
	fieldName : "empName",
	dataType : "text",
},{
	fieldName : "empTel",
	dataType : "text",
}

];

var columns = [
		{
			name : "empCode",
			fieldName : "empCode",
			type : "data",
			width : "60",
			header : {
				text : "Code"
			},
			editor : {
				"inputCharacters" : "0-9"
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
			visible : false,
		}, {
			name : "empName",
			fieldName : "empName",
			type : "data",
			header : {
				text : "사원명"
			},
		},{
			name : "empTel",
			fieldName : "empTel",
			type : "data",
			header : {
				text : "휴대폰번호",
			},
			editor:{
				 "mask" :{
					 "editMask" : "000-0000-0000",
					 "includedFormat" : true
				 }
			
			},
			"textFormat" : "([0-9]{2,3})-([0-9]{3,4})-([0-9]{4});$1-$2-$3",
//			styleCallback : function(grid, dataCell) {
//				var ret = {}
//				var preData = grid.getValue(dataCell.index.itemIndex, "empName")
//				if (dataCell.item.rowState == 'created'
//						|| dataCell.item.itemState == 'appending'
//						|| dataCell.item.itemState == 'inserting') {
//					if(preData == undefined){
//						ret.editable=false;
//					}
//				}
//				return ret;
//			}
		}];

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
	  masterGrid.setCheckBar({
		  visible: false
	  });
	  masterGrid.setStateBar({
		  visible: false
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

	masterGrid.onCurrentRowChanged = function(grid, oldRow, newRow) {
		if (newRow < 0) {
			$("#employeeDetail").find(":input[name]").attr("disabled", true)
					.val("");
			$("#saved").attr("disabled", true);
			$("#searchAdd").attr("disabled", true);
			$('.fa-bars').attr("hidden", "hidden");

		} else {
			formGet(masterProvider.getJsonRow(newRow));
		}
	};

	masterGrid.onCellEdited = function (grid, itemIndex, row, field) {
	     if(masterGrid.getItemState(itemIndex) == 'appending' && grid.getColumn(field).fieldName == 'empName'){
	    	 insertEmployee(grid.getValues(itemIndex));
	    	 grid.commit();
	     }
	}
	
	masterProvider.onRowInserted = function(provider, row) {
			init();
	};


	masterProvider.onRowUpdated = function(provider, row) {
		var data = provider.getJsonRow(row);
		updateEmployee(data);
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
		url : $.getContextPath() + "/info1/employeeManageRetrieve.do",
		method : "post",
		dataType : "json",
		success : function(resp) {
			console.log(resp)
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
	$("#employeeDetail").ajaxForm({
		url : $.getContextPath() + "/info1/employeeDetailUpdate.do",
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

function formGet(data) {
	$.ajax({
		url : $.getContextPath() + "/info1/employeeManageRetrieve.do",
		data : data,
		method : "post",
		dataType : "json",
		success : function(resp) {
			var data = resp[0];
			if (data != undefined) {
				$("#employeeDetail").find(":input[name]").attr("disabled",
						false);
				$("#empUse").attr("disabled", false);
				$("#saved").attr("disabled", false);
				$("#searchAdd").attr("disabled", false);
				$('.fa-bars').removeAttr("hidden");
				$("input[name=empCode]").val(data.empCode);
				$("input[name=empName]").val(data.empName);
				$("input[name=deptCode]").val(data.deptCode);
				$("input[name=deptName]").val(data.deptName);
				$("input[name=empDate]").val(data.empDate);
				$("input[name=empReg1]").val(data.empReg1);
				$("input[name=empReg2]").val(data.empReg2);
				$("input[name=empAdd1]").val(data.empAdd1);
				$("input[name=empAdd2]").val(data.empAdd2);
				$("input[name=empLine]").val(data.empLine);
				$("input[name=empTel]").val(data.empTel);
				$("input[name=empMail]").val(data.empMail);
				$("#empRank").val(data.empRank).prop("selected", true);
				$("input[name=rawsAdqinv]").val(data.rawsAdqinv);
				$("input[name=empFire]").val(data.empFire);
				$("#empUse").val(data.empUse).prop("selected", true);
			}
		},
		error : function(xhr, errorResp, error) {
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	});
};

function updateEmployee(value) {
	$.ajax({
		url : $.getContextPath() + "/info1/employeeUpdate.do",
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

function insertEmployee(value) {
	$.ajax({
		url : $.getContextPath() + "/info1/employeeInsert.do",
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
		fileName : '사원등록.xlsx',
		exportGrids : [ {
			grid : masterGrid,
			sheetName : '사원등록'
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
warCode = function(obj) {
	$("#war-modal").css('display', 'flex')
	warObj = $(obj).prev();
}
empCode = function() {
	$("#emp-modal").css('display', 'flex')
}
kcommCode = function(obj) {
	$("#kcomm-modalM").css('display', 'flex')
	kcommObj = $(obj).prev();
}
gcommCode = function(obj) {
	$("#gcomm-modalM").css('display', 'flex')
	gcommObj = $(obj).prev();
}
rawsCode = function(obj) {
	$("#item-modalM").css('display', 'flex')
	rawsObj = $(obj).prev();
}
$('#deptName').on('focus',function(e){
	$("#dept-modal").css('display', 'flex')
	deptObj = this
})
ucommCode = function(obj) {
	$("#ucomm-modalM").css('display', 'flex')
	ucommObj = $(obj).prev();
}
ldivCode = function(obj) {
	$("#ldiv-modal").css('display', 'flex')
	ldivObj = $(obj).prev();
}
mdivCode = function(obj) {
	divCodeL = $("input[name=divCodeL]").val()
	if (divCodeL) {
		// 데이터 가져오기
		$.ajax({
			url : $.getContextPath() + "/info2/div/divList.do",
			data : {
				"divCategory" : "L",
				"divChild" : divCodeL
			},
			method : 'post',
			dataType : "json",
			success : function(data) {
				mdivProvider.fillJsonData(data, {
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

	mdivObj = $(obj).prev();
	$("#mdiv-modal").css('display', 'flex')
}
sdivCode = function(obj) {
	var divCodeM = $("input[name=divCodeM]").val()
	if (divCodeM) {
		// 데이터 가져오기
		$.ajax({
			url : $.getContextPath() + "/info2/div/divList.do",
			data : {
				"divCategory" : "M",
				"divChild" : divCodeM
			},
			method : 'post',
			dataType : "json",
			success : function(data) {
				sdivProvider.fillJsonData(data, {
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

	sdivObj = $(obj).prev();
	$("#sdiv-modal").css('display', 'flex')
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
			document.getElementById("empAdd1").value = addr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("empAdd2").focus();
		}
	}).open();
})

function gridSearch(value) {
	var fields = masterProvider.getOrgFieldNames();
	var startFieldIndex = fields.indexOf(masterGrid.getCurrent().fieldName) + 1;
	var options = {
		fields : fields,
		value : value,
		startIndex : masterGrid.getCurrent().itemIndex,
		startFieldIndex : startFieldIndex,
		wrap : true,
		caseSensitive : false,
		partialMatch : true
	};

	var index = masterGrid.searchCell(options);
	masterGrid.setCurrent(index);
}

$('#empReg1').inputmask({
	  mask:"9{6}",
	  placeholder: ' ',
	  showMaskOnHover: false,
	  showMaskOnFocus: false,
		});
$('#empReg2').inputmask({
	  mask:"9{7}",
	  placeholder: ' ',
	  showMaskOnHover: false,
	  showMaskOnFocus: false,
		});
$('#empLine').inputmask({mask:"9{1,3}-9{3,4}-9{4}",   
	  placeholder: ' ',
	  showMaskOnHover: false,
	  showMaskOnFocus: false,
	  }); 
$('#empTel').inputmask({
	mask:"9{1,3}-9{3,4}-9{4}",   
	placeholder: ' ',
	showMaskOnHover: false,
	showMaskOnFocus: false,
	}); 
$('#empMail').inputmask({
    mask: "*{1,20}[.*{1,20}][.*{1,20}][.*{1,20}]@*{1,20}[.*{2,6}][.*{1,2}]",
    greedy: false,
    placeholder: ' ',
    showMaskOnHover: false,
    showMaskOnFocus: false,
    onBeforePaste: function (pastedValue, opts) {
      pastedValue = pastedValue.toLowerCase();
      return pastedValue.replace("mailto:", "");
    },
    definitions: {
      '*': {
        validator: "[0-9A-Za-z!#$%&'*+/=?^_`{|}~\-]",
        casing: "lower"
      }
    }
  });