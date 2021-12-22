// 리얼그리드 공통부분
var masterProvider, masterGrid, detailProvider, detailGrid;

/* 기본 셋팅 masterGrid */
function createMasterGrid() {
	masterProvider = new RealGrid.LocalDataProvider();
	masterGrid = new RealGrid.GridView("realgrid_master");
	masterGrid.stateBar.visible = false;
	masterGrid.setDataSource(masterProvider);
	masterProvider.setFields(fields);
	masterGrid.setColumns(columns);
	masterGrid.resetCurrent();


	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	masterGrid.setDisplayOptions({
		emptyMessage : "표시할 데이타가 없습니다.",
		showEmptyMessage : true,
		rowHeight : 36,
		fitStyle : "even"
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
		forceAppend : true,
		commitWhenLeave : true
		// 셀단위 커밋
		,
		commitByCell : true
	})

	// 클릭한 곳의 Row의 넘버를 넘겨줌.
  // By 이원균 : 39라인 재고평가할때 오류나서 if문 추가했음_211122
	if(masterGrid){
		// 클릭한 곳의 Row의 넘버를 넘겨줌.
		masterGrid.onCurrentRowChanged = function(grid, oldRow, newRow) {
			detailGet(newRow);
		};
	}	

	masterGrid.setRowStyleCallback(function(grid, item, fixed) {
		switch (item.rowState) {
		case "created":
			return "lightskyblue-color";
		case "updated":
			return "lightcyan-color";
		}
	})

	// 검색에서 클릭 시 코드도움 모달을 보여준다.
	buyerCode = function(obj) {
		$("#buyer-modal").css('display', 'flex')
		
		console.log(obj)
		buyerObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info1/buyerRetrieve.do",
			   method : "post",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   buyerProvider.fillJsonData(data, {fillMode: "set"});
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
		empObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info1/employeeManageRetrieve.do",
			   method : "post",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   empProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	deptCode = function(obj) {
		$("#dept-modal").css('display', 'flex')
		deptObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info1/departmentManageRetrieve.do",
			   method : "post",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   deptProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	projCode = function(obj) {
		$("#proj-modal").css('display', 'flex')
		projObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info1/projectRetrieve.do",
			   method : "post",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   projProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	warCode = function(obj) {
		$("#war-modal").css('display', 'flex')
		warObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info2/warehouseRetrieve.do",
			   method : "post",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   warProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	kcommCode = function(obj) {
		$("#kcomm-modalM").css('display', 'flex')
		kcommObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info2/common/process.do",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   kcommProviderM.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	gcommCode = function(obj) {
		$("#gcomm-modalM").css('display', 'flex')
		gcommObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info2/common/standard.do",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   gcommMProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	itemCode = function(obj) {
		$("#item-modal").css('display', 'flex')
		itemCodeObj = obj;
		$("#itemSearchForm").ajaxForm({
			url : $.getContextPath() + "/info2/itemManageRetrieve.do",
			method : 'post',
			dataType:"json",
			success:function(data){
				console.log(data);
				itemProvider.fillJsonData(data, {fillMode: "set"});
			},
			error : function(xhr, errorResp, error){
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		}).submit();
	}
	ucommCode = function(obj) {
		$("#ucomm-modalM").css('display', 'flex')
		ucommObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info2/common/unit.do",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   ucommMProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	ldivCode = function(obj) {
		$("#ldiv-modal").css('display', 'flex')
		ldivObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info2/div/divList.do",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   ldivProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	mdivCode = function(obj) {
		mdivObj = obj;
		var divCodeL = $("input[name=divCodeL]").val()
		console.log(divCodeL)
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
					console.log(data);
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

		$("#mdiv-modal").css('display', 'flex')
	}
	sdivCode = function(obj) {
		sdivObj = obj;
		var divCodeM = $("input[name=divCodeM]").val()
		console.log(divCodeM)
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
					console.log(data);
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

		$("#sdiv-modal").css('display', 'flex')
	}

	// 셀버튼 클릭 시 이벤트 발생
	masterGrid.onCellButtonClicked = function(grid, index, column) {
		masterGrid.commit();
		// 모달창 띄우기
		if (column.fieldName === 'buyerName') {
			// 거래처 리스트 모달창 띄우기
			$(function() {
				console.log('buyer_modal')
				$("#buyer-modal").css('display', 'flex')
			});
			//데이터 가져오기
			$.ajax({
				   url : $.getContextPath() + "/info1/buyerRetrieve.do",
				   method : "post",
				   dataType : "json",
				   success : function(data){
					   console.log(data);
					   buyerProvider.fillJsonData(data, {fillMode: "set"});
				   },
				   error: function(xhr, errorResp, error){
					   console.log(xhr);
					   console.log(errorResp);
					   console.log(error);
				   }
			});

		} else if (column.fieldName === 'deptName') {
			// 부서 리스트 모달창 띄우기
			$(function() {
				console.log('dept_modal')
				$("#dept-modal").css('display', 'flex')
				//데이터 가져오기
				$.ajax({
					   url : $.getContextPath() + "/info1/departmentManageRetrieve.do",
					   method : "post",
					   dataType : "json",
					   success : function(data){
						   console.log(data);
						   deptProvider.fillJsonData(data, {fillMode: "set"});
					   },
					   error: function(xhr, errorResp, error){
						   console.log(xhr);
						   console.log(errorResp);
						   console.log(error);
					   }
				});
			});

		} else if (column.fieldName === 'empName') {
			// 사원
			$(function() {
				console.log('emp_modal')
				$("#emp-modal").css('display', 'flex')
				//데이터 가져오기
				$.ajax({
					   url : $.getContextPath() + "/info1/employeeManageRetrieve.do",
					   method : "post",
					   dataType : "json",
					   success : function(data){
						   console.log(data);
						   empProvider.fillJsonData(data, {fillMode: "set"});
					   },
					   error: function(xhr, errorResp, error){
						   console.log(xhr);
						   console.log(errorResp);
						   console.log(error);
					   }
				});
			});
		} else if (column.fieldName === 'gcommName') {
			// 규격 리스트 모달창 띄우기
			$(function() {
				console.log('gcomm_modalM')
				$("#gcomm-modalM").css('display', 'flex')
				//데이터 가져오기
				$.ajax({
					   url : $.getContextPath() + "/info2/common/standard.do",
					   dataType : "json",
					   success : function(data){
						   console.log(data);
						   gcommMProvider.fillJsonData(data, {fillMode: "set"});
					   },
					   error: function(xhr, errorResp, error){
						   console.log(xhr);
						   console.log(errorResp);
						   console.log(error);
					   }
				});
			});
		} else if (column.fieldName === 'ucommName') {
			// 단위 리스트 모달창 띄우기
			$(function() {
				console.log('ucomm_modalM')
				$("#ucomm-modalM").css('display', 'flex')
				//데이터 가져오기
				$.ajax({
					   url : $.getContextPath() + "/info2/common/unit.do",
					   dataType : "json",
					   success : function(data){
						   console.log(data);
						   ucommMProvider.fillJsonData(data, {fillMode: "set"});
					   },
					   error: function(xhr, errorResp, error){
						   console.log(xhr);
						   console.log(errorResp);
						   console.log(error);
					   }
				});
			});
		} else if (column.fieldName === 'rawsCode'
				|| column.fieldName === 'prodCode'
				|| column.fieldName === 'itemCode') {
			// 품목 리스트 모달창 띄우기
			$("#item-modalM").css('display', 'flex')
			let icodeName = masterGrid.getValue(index.dataRow, "icodeName")
			// 데이터 가져오기
			$.ajax({
				url : $.getContextPath() + "/info2/itemManageRetrieve.do",
				data : {
					'icodeName' : icodeName
				},
				method : "post",
				dataType : "json",
				success : function(data) {
					console.log(data);
					itemProviderM.fillJsonData(data, {
						fillMode : "set"
					});
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			});
		} else if (column.fieldName === 'kcommName') {
			// 공정 리스트 모달창 띄우기
			$(function() {
				console.log('kcomm_modalM')
				$("#kcomm-modalM").css('display', 'flex')
				//데이터 가져오기
				$.ajax({
					   url : $.getContextPath() + "/info2/common/process.do",
					   dataType : "json",
					   success : function(data){
						   console.log(data);
						   kcommProviderM.fillJsonData(data, {fillMode: "set"});
					   },
					   error: function(xhr, errorResp, error){
						   console.log(xhr);
						   console.log(errorResp);
						   console.log(error);
					   }
				});
			});
		} else if (column.fieldName === 'warName'
				|| column.fieldName === 'inwarName'
				|| column.fieldName === 'outwarName') {
			// 창고 리스트 모달창 띄우기
			$(function() {
				console.log('war_modalM')
				$("#war-modalM").css('display', 'flex')
				//데이터 가져오기
				$.ajax({
					   url : $.getContextPath() + "/info2/warehouseRetrieve.do",
					   method : "post",
					   dataType : "json",
					   success : function(data){
						   console.log(data);
						   warProviderM.fillJsonData(data, {fillMode: "set"});
					   },
					   error: function(xhr, errorResp, error){
						   console.log(xhr);
						   console.log(errorResp);
						   console.log(error);
					   }
				});
			});
		}
		;

	}

	// 날짜 유효성 검사
	masterGrid.onValidateColumn = function(grid, column, inserting, value) {
		var error = {};
		//연도 유효성 검사
		if (column.fieldName === "pdcYy" || column.fieldName === "buyYy"
			|| column.fieldName === "salYy"
			|| column.fieldName === "stockYy") {
			if (value > 2099 || value < 1970) {
				error.level = "error";
				error.message = "연도를 확인해 주세요";
			}
		}
		//월 유효성 검사
		if (column.fieldName === "pdcMm" || column.fieldName === "buyMm"
			|| column.fieldName === "salMm"
			|| column.fieldName === "stockMm") {
			
			if (value > 12 || value < 1) {
				error.level = "error";
				error.message = "올바른 '월'을 입력해 주세요";
			}
		}
		//일 유효성 검사
		if (column.fieldName === "pdcDd" || column.fieldName === "buyDd"
				|| column.fieldName === "salDd"
				|| column.fieldName === "stockDd") {
			let current = masterGrid.getCurrent()
			let row = current.dataRow
			let fieldY = current.fieldIndex - 2
			let fieldM = current.fieldIndex - 1
			let year = masterGrid.getValue(row, fieldY)
			let month = masterGrid.getValue(row, fieldM)
			let lastday = new Date(year, month, 0).getDate();
			
			if (value < 1) {
				error.level = "error";
				error.message = "1일 이전은 입력 할 수 없습니다.";
			} else if (value > lastday) {
				error.level = "error";
				error.message = "현재 달의 마지막일을 확인해주세요.";
			}
		}
		
		return error;
	}
}

var error = {};

/* 기본 셋팅 detailGrid */
function createDetailGrid() {
	detailProvider = new RealGrid.LocalDataProvider();
	detailGrid = new RealGrid.GridView("realgrid_detail");
	 detailGrid.stateBar.visible = false;
	detailGrid.setDataSource(detailProvider);
	detailProvider.setFields(fields1);
	detailGrid.setColumns(columns1);

	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	detailGrid.setDisplayOptions({
		emptyMessage : "표시할 데이타가 없습니다.",
		showEmptyMessage : true,
		rowHeight : 36,
		fitStyle : "even"
	});
	// detailGrid 속성값
	detailGrid.header.height = 40;
	detailGrid.footer.height = 40;
	detailGrid.stateBar.width = 10;
	detailGrid.setEditOptions({
			insertable : true,
			appendable : true,
			editable : true,
			deletable : true,
			confirmWhenDelete : true,
			deleteRowsMessage : "정말로 삭제 하시겠습니까?",
			enterToTab : true,
			forceAppend : true,
			commitWhenLeave : true
			// 셀단위 커밋
			,
			commitByCell : true
	})

	setTimeout(function() {
		masterGrid.resetCurrent();
	}, 300);

	detailGrid.setRowStyleCallback(function(grid, item, fixed) {
		switch (item.rowState) {
		case "created":
			return "lightskyblue-color";
		case "updated":
			return "lightcyan-color";
		}
	})

	// 셀버튼 클릭 시 이벤트 발생
	detailGrid.onCellButtonClicked = function(grid, index, column) {
		detailGrid.commit();
		// 모달창 띄우기
		if (column.fieldName === 'rawsCode' || column.fieldName === 'prodCode'
				|| column.fieldName === 'itemCode') {
			// 품목 리스트 모달창 띄우기
			$("#item-modal").css('display', 'flex')
			let icodeName = detailGrid.getValue(index.dataRow, "icodeName")
			console.log("dasdsa")
			// 데이터 가져오기
			$.ajax({
				url : $.getContextPath() + "/info2/itemManageRetrieve.do",
				data : {
					'icodeName' : icodeName
				},
				method : "post",
				dataType : "json",
				success : function(data) {
					console.log(data);
					itemProvider.fillJsonData(data, {
						fillMode : "set"
					});
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			});
		} else if (column.fieldName === 'warName') {
			// 창고 리스트 모달창 띄우기
			$(function() {
				console.log('war_modal')
				$("#war-modal").css('display', 'flex')
				//데이터 가져오기
				$.ajax({
					   url : $.getContextPath() + "/info2/warehouseRetrieve.do",
					   method : "post",
					   dataType : "json",
					   success : function(data){
						   console.log(data);
						   warProvider.fillJsonData(data, {fillMode: "set"});
					   },
					   error: function(xhr, errorResp, error){
						   console.log(xhr);
						   console.log(errorResp);
						   console.log(error);
					   }
				});
			});
		} else if (column.fieldName === 'projName') {
			// 프로젝트 리스트 모달창 띄우기
			$(function() {
				console.log('proj_modal')
				$("#proj-modal").css('display', 'flex')
				//데이터 가져오기
				$.ajax({
					   url : $.getContextPath() + "/info1/projectRetrieve.do",
					   method : "post",
					   dataType : "json",
					   success : function(data){
						   console.log(data);
						   projProvider.fillJsonData(data, {fillMode: "set"});
					   },
					   error: function(xhr, errorResp, error){
						   console.log(xhr);
						   console.log(errorResp);
						   console.log(error);
					   }
				});
			});
		} else if (column.fieldName === 'kcommName') {
			// 공정 리스트 모달창 띄우기
			$(function() {
				console.log('kcomm_modal')
				$("#kcomm-modal").css('display', 'flex')
				//데이터 가져오기
				$.ajax({
					   url : $.getContextPath() + "/info2/common/process.do",
					   dataType : "json",
					   success : function(data){
						   console.log(data);
						   kcommProvider.fillJsonData(data, {fillMode: "set"});
					   },
					   error: function(xhr, errorResp, error){
						   console.log(xhr);
						   console.log(errorResp);
						   console.log(error);
					   }
				});
			});
		} else if (column.fieldName === 'gcommName') {
			// 규격 리스트 모달창 띄우기
			$(function() {
				console.log('gcomm_modalD')
				$("#gcomm-modalD").css('display', 'flex')
				//데이터 가져오기
				$.ajax({
					   url : $.getContextPath() + "/info2/common/standard.do",
					   dataType : "json",
					   success : function(data){
						   console.log(data);
						   gcommDProvider.fillJsonData(data, {fillMode: "set"});
					   },
					   error: function(xhr, errorResp, error){
						   console.log(xhr);
						   console.log(errorResp);
						   console.log(error);
					   }
				});
			});
		} else if (column.fieldName === 'ucommName') {
			// 단위 리스트 모달창 띄우기
			$(function() {
				console.log('ucomm_modalD')
				$("#ucomm-modalD").css('display', 'flex')
				$.ajax({
					url : $.getContextPath() + "/info2/common/unit.do",
					dataType : "json",
					success : function(data){
						console.log(data);
						ucommDProvider.fillJsonData(data, {fillMode: "set"});
					},
					error: function(xhr, errorResp, error){
						console.log(xhr);
						console.log(errorResp);
						console.log(error);
					}
				});
			});
			//데이터 가져오기
		};
	}

	// 수량이 필드 내에 존재하는지 찾고, 필드넘버를 받는다.
	if (detailGrid.columnByName('adjQty')) {
		var field1 = detailGrid.columnByName('adjQty').dataIndex
	} else if (detailGrid.columnByName('orpQty')) {
		var field1 = detailGrid.columnByName('orpQty').dataIndex
	} else if (detailGrid.columnByName('irQty')) {
		var field1 = detailGrid.columnByName('irQty').dataIndex
	} else if (detailGrid.columnByName('pcrQty')) {
		var field1 = detailGrid.columnByName('pcrQty').dataIndex
	} else if (detailGrid.columnByName('epQty')) {
		var field1 = detailGrid.columnByName('epQty').dataIndex
	} else if (detailGrid.columnByName('opQty')) {
		var field1 = detailGrid.columnByName('opQty').dataIndex
	} else if (detailGrid.columnByName('ropQty')) {
		var field1 = detailGrid.columnByName('ropQty').dataIndex
	} else if (detailGrid.columnByName('rpQty')) {
		var field1 = detailGrid.columnByName('rpQty').dataIndex
	} else if (detailGrid.columnByName('scpQty')) {
		var field1 = detailGrid.columnByName('scpQty').dataIndex
	} else if (detailGrid.columnByName('orrQty')) {
		var field1 = detailGrid.columnByName('orrQty').dataIndex
	}
	// 수량 입력하면 단가랑 곱해져서 합계가 표시된다
	detailGrid.onEditRowChanged = function(grid, itemIndex, dataRow, field,
			oldValue, newValue) {
		// 변경된 사항이 수량의 필드넘버면 해당 로직을 실행한다.
		console.log("onEditRowChanged, " + field + ": " + oldValue + " => "
				+ newValue);
		if (field == field1) {
			var cur = detailGrid.getCurrent();
			var row = cur.dataRow;
			var index = cur.fieldIndex;
			var qty = detailGrid.getValue(row, index);
			var price = detailGrid.getValue(row, index * 1 + 1);
			var sum = qty * price;
			var vat = sum * 0.1;
			var scost = sum + vat;
			detailGrid.setValue(row, index * 1 + 2, sum);
			detailGrid.setValue(row, index * 1 + 3, vat);
			detailGrid.setValue(row, index * 1 + 4, scost);
		}
	};
	}
 
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


function excelExport() {
	RealGrid.exportGrid({
		type : 'excel',
		target : 'local',
		fileName : 'multiExport.xlsx',
		exportGrids : [ {
			grid : masterGrid,
			sheetName : 'masterSheet'
		}, {
			grid : detailGrid,
			sheetName : 'detailSheet'
		} ]
	})
}

function start() {
	$("#xlf").bind("change", handleXlsFile);
	createMasterGrid();
	createDetailGrid();
}

// $.document.ready(start);
window.onload = start();
// domloaded를 대신 써도 됩니다.

window.onunload = function() {
	masterProvider.clearRows();
	detailProvider.clearRows();

	masterGrid.destroy();
	detailGrid.destroy();

	masterProvider.destroy();
	detailProvider.destroy();

	masterGrid = null;
	masterProvider = null;

	detailGrid = null;
	detailProvider = null;
}

//<--------------------------------엑셀 임포트--------------------------------------->//
function gridClear() {
	masterProvider.clearRows();
	}

	///////////////////////////////////////////////////////////////////////////////
	function fixdata(data) {
	  var o = "", l = 0, w = 10240;
	  for (; l < data.byteLength / w; ++l) o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w)));
	  o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w)));
	  return o;
	}

	function handleXlsFile(e) {
	  var files = e.target.files;
	  var i, f;
	  for (i = 0, f = files[i]; i != files.length; ++i) {
	      var reader = new FileReader();
	      var name = f.name;
	      reader.onload = function (e) {
	          var data = e.target.result;

	          //var workbook = XLSX.read(data, { type: 'binary' });
	          var arr = fixdata(data);
	          workbook = XLSX.read(btoa(arr), { type: 'base64' });

	          process_wb(workbook);
	          /* DO SOMETHING WITH workbook HERE */
	      };
	      //reader.readAsBinaryString(f);
	      reader.readAsArrayBuffer(f);

	  }
	}

	function process_wb(wb) {
	  var output = "";

	  output = to_json(wb);

	  var sheetNames = Object.keys(output);

	  if (sheetNames.length > 0) {
	      var colsObj = output[sheetNames[0]];

	      if (colsObj) {
	          masterProvider.fillJsonData(output, { rows: sheetNames[0] })
	      }
	  }
	}
	
	function to_json(workbook) {
		  var result = {};
		  workbook.SheetNames.forEach(function (sheetName) {
		      var roa = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName], {});
		      if (roa.length > 0) {
		          result[sheetName] = roa;
		      }
		  });
		  return result;
	}

