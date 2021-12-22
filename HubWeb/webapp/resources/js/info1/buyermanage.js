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
	fieldName : "buyerCode",
	dataType : "text"
}, {
	fieldName : "buyerName",
	dataType : "text",
}, {
	fieldName : "buyerCeo",
	dataType : "text",
}, {
	fieldName : "buyerSort",
	dataType : "text",
}, {
	fieldName : "buyerUse",
	dataType : "text"
}

];

var columns = [
		{
			name : "buyerCode",
			fieldName : "buyerCode",
			type : "data",
			width : "60",
			header : {
				text : "Code"
			},
			editor : {
				"inputCharacters" : "0-9",
			},
			visible : false,
		}, {
			name : "buyerName",
			fieldName : "buyerName",
			type : "data",
			header : {
				text : "거래처명"
			},
			width : '3',
			styleCallback : function(grid, dataCell) {
				var ret = {}
				if (dataCell.item.rowState == 'created'
						|| dataCell.item.itemState == 'appending'
						|| dataCell.item.itemState == 'inserting') {
					ret.editable=true;
				}
				return ret;
			},
			styleName : "left-column"
		}
		, {
			name : "buyerCeo",
			fieldName : "buyerCeo",
			type : "data",
			header : {
				text : "대표자"
			},
			width : '2',
			styleCallback : function(grid, dataCell) {
				var ret = {}
				var preData = grid.getValue(dataCell.index.itemIndex, "buyerName")
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
			name: "buyerSort",
			fieldName: "buyerSort",
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
			"values": ["전체", "매출", "매입"],
			"labels": ["전체", "매출", "매입"],
			header: {
				  text: "구분"
			},
			styleCallback : function(grid, dataCell) {
				var ret = {}
				var preData = grid.getValue(dataCell.index.itemIndex, "buyerCeo")
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
			name: "buyerUse",
			fieldName: "buyerUse",
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
				var preData = grid.getValue(dataCell.index.itemIndex, "buyerSort")
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
			$("#buyerDetail").find(":input[name]").attr("disabled", true).val("");
			$('#buyerRegnumber2').css('border-color', '');
			$("#saved").attr("disabled", true);
			$("#delete").attr("disabled", true);
			$("#searchAdd").attr("disabled", true);
		} else {
			formGet(masterProvider.getJsonRow(newRow));
		}
	};
	// 마지막 셀 수정 완료시 커밋 -> onRowInserting -> onRowInsertied
	masterGrid.onCellEdited = function (grid, itemIndex, row, field) {
	     if(masterGrid.getItemState(itemIndex) == 'appending' && grid.getColumn(field).fieldName == 'buyerUse'){
	    	 insertBuyer(grid.getValues(itemIndex));
	     }
	}
	masterProvider.onRowInserted = function(provider, row) {
		init();
	};

	masterProvider.onRowUpdated = function(provider, row) {
		var data = provider.getJsonRow(row);
		updateBuyer(data);
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

$("#saved").on("click", function() {
	$("#buyerDetail").ajaxForm({
		url : $.getContextPath() + "/info1/buyerDetailUpdate.do",
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

$("#select1,#select2").on("click",function(){
	masterGrid.cancel();
	$("#searchForm").ajaxForm({
		url : $.getContextPath() + "/info1/buyerRetrieve.do",
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
			title: "[ " +data.buyerName + " ]<br>정말로 삭제하시겠습니까?",
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
					url : $.getContextPath() + "/info1/buyerDelete.do",
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
		url : $.getContextPath() + "/info1/buyerRetrieve.do",
		data : data,
		method : "post",
		dataType : "json",
		success : function(resp) {
			var data = resp[0];
			$('#buyerRegnumber2').css('border-color', '');
			if (data != undefined) {
				$("#buyerDetail").find(":input[name]").attr("disabled", false);
				$("#saved").attr("disabled", false);
				$("#delete").attr("disabled", false);
				$("#buyerCode").val(data.buyerCode);
				$("#buyerName").val(data.buyerName);
				$("#buyerStart").val(data.buyerStart);
				$("#buyerWorktype").val(data.buyerWorktype);
				$("#buyerEnd").val(data.buyerEnd);
				$("#buyerDetail").find("input[name=buyerRegnumber]").val(data.buyerRegnumber);
				$("#buyerRegnumber").val(data.buyerWorktype);
				$("#buyerEvent").val(data.buyerEvent);
				$("#buyerAdd1").val(data.buyerAdd1);
				$("#buyerAdd2").val(data.buyerAdd2);
				$("#buyerTel").val(data.buyerTel);
				$("#buyerFax").val(data.buyerFax);
				$("#buyerPartner").val(data.buyerPartner);
				$("#buyerDesk").val(data.buyerDesk);
				$("#searchAdd").attr("disabled", false);
				if(data.buyerStart != undefined){
					$('#buyerDate').data('daterangepicker').setStartDate(data.buyerStart);
					$('#buyerDate').data('daterangepicker').setEndDate(data.buyerEnd);
				}else{
					$('#buyerDate').val('');
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

function updateBuyer(value) {
	$.ajax({
		url : $.getContextPath() + "/info1/buyerUpdate.do",
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

function insertBuyer(value) {
	if(masterGrid.commit()){
		$.ajax({
			url : $.getContextPath() + "/info1/buyerInsert.do",
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
				init();
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	}
}

function excelExport() {
	RealGrid.exportGrid({
		type : 'excel',
		target : 'local',
		fileName : '거래처등록.xlsx',
		exportGrids : [ {
			grid : masterGrid,
			sheetName : '거래처등록'
		}, ]
	})
}

function init(){

	$.ajax({
		url : $.getContextPath() + "/info1/buyerRetrieve.do",
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

function start() {
	init();
	createMasterGrid();
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
	warObj = $(obj);
}
empCode = function(obj) {
	$("#emp-modal").css('display', 'flex')
	empObj = $(obj);
}

deptCode = function(obj) {
	$("#dept-modal").css('display', 'flex')
	deptObj = $(obj);
}

$('#RegnumberCheck').on('click', function(){
		var data = { b_no : [$('#buyerRegnumber2')[0].inputmask.unmaskedvalue()] };
		$.ajax({
			url : "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=A%2F2yW7atuqmnDD4sX%2Fh46%2B69s19NhZzbFbV2ISWzS5gm%2BU8XFQVZrUG6sysmo9XtVKvV%2FotUawfdE37JMCmiDw%3D%3D",
			data : JSON.stringify(data),
			method : "post",
			contentType : "application/json",
			dataType : "json",
			success : function(resp) {
				var dataList = resp.data;
				for(var idx in dataList ){
					if(resp.status_code == 'OK'){
						if(resp.match_cnt === 1){
							Swal.fire({
								  icon: 'success',
								  title: dataList[idx].tax_type});
							$('#buyerRegnumber2').css('border-color', 'green');
						}else{
							Swal.fire({
								  icon: 'error',
								  title: dataList[idx].tax_type});
							$('#buyerRegnumber2').css('border-color', 'red');
						}
					}else{
						Swal.fire("서버 문제");
					}
				}
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
})
$('#buyerDate').daterangepicker(
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
			$('#buyerStart').val(start.format('YYYY/MM/DD'));
			$('#buyerEnd').val(end.format('YYYY/MM/DD'));
});

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
			document.getElementById("buyerAdd1").value = addr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("buyerAdd2").focus();
		}
	}).open();
})

$('#buyerDate').val('');
$('#buyerRegnumber').inputmask({
	  mask:"999-99-99999",
	  placeholder: ' ',
	  showMaskOnHover: false,
	  showMaskOnFocus: false,
});
$('#buyerRegnumber2').inputmask({
	  mask:"999-99-99999",
	  placeholder: ' ',
	  showMaskOnHover: false,
	  showMaskOnFocus: false,
});
$('#buyerTel').inputmask({
	  mask:"9{1,3}-9{3,4}-9{4}",
	  placeholder: ' ',
	  showMaskOnHover: false,
	  showMaskOnFocus: false,
}); 
$('#buyerFax').inputmask({
	  mask:"9{1,3}-9{3,4}-9{4}",
	  placeholder: ' ',
	  showMaskOnHover: false,
	  showMaskOnFocus: false,
}); 

//상세검색창 열고 닫기
function doDisplay(){
	var con = document.getElementById("myDIV");
	if(con.style.display=='none'){
		con.style.display = 'block';
	}else{
		con.style.display = 'none';
	}
}
function doHide(){
	var hid = []
	hid = document.getElementsByClassName("select");
	var con = document.getElementById("myDIV");
	if(con.style.display=='none'){
		hid[0].style.display = 'block';
	}else{
		hid[0].style.display = 'none';
	}
}	

