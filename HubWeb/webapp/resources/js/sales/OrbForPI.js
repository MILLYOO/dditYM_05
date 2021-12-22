/**
 * 견적서적용
 */

// 이달 1일 ~ 현재 날짜 세팅
//모달 열기
$("#orderbookApplyForPI-modal").click(function(e){
	
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#orderbookApplyForPI-modal").css("display", "none")
	}
});  

//필드 컬럼 설정
var fields = [
	  {
	    fieldName: "estCode",
	    dataType: "text"
	  },
	  {
		  fieldName: "estDate",
		  dataType: "date"
	  },
	  {
	    fieldName: "buyerCode",
	    dataType: "text"
	  },
	  {
	    fieldName: "buyerName",
	    dataType: "text"
	  },
	  {
	    fieldName: "buyerReceiver",
	    dataType: "text"
	  },
	  {
	    fieldName: "deptName",
	    dataType: "text"
	  },
	  {
	    fieldName: "empName",
	    dataType: "text"
	  },
	  {
	    fieldName: "estVat",
	    dataType: "text"
	  },
	  {
	    fieldName: "estResult",
	    dataType: "text"
	  },
	  {
	    fieldName: "estFinish",
	    dataType: "text"
	  }
	];

	var columns = [
	  {
	    name: "estCode",
	    fieldName: "estCode",
	    type: "data",
	    width: "120",
	    header: {
	      text: "견적번호"
	    }
	  },
	  {
	    name: "estDate",
	    fieldName: "estDate",
	    type: "data",
	    width: "100",
	    header: {
	      text: "날짜"
	    }, 
	    editor: {type: "date", datetimeFormat: "yyyy.MM.dd"}
	    , "visible" : false
	  },
	  {
	    name: "buyerCode",
	    fieldName: "buyerCode",
	    type: "data",
	    width: "65",
	    header: {
	      text: "거래처코드"
	    }
	  , "visible" : false
	  },
	  {
	    name: "buyerName",
	    fieldName: "buyerName",
	    type: "data",
	    width: "120",
	    header: {
	      text: "거래처명"
	    }
	  	, button:"action"
	  },
	  {
	    name: "buyerReceiver",
	    fieldName: "buyerReceiver",
	    type: "data",
	    width: "120",
	    header: {
	      text: "수취인",
	    }, "visible" : false
	  },
	  {
	    name: "deptName",
	    fieldName: "deptName",
	    type: "data",
	    width: "110",
	    header: {
	      text: "부서",
	    }
	  , button:"action"
	  },
	  {
	    name: "empName",
	    fieldName: "empName",
	    type: "data",
	    width: "110",
	    header: {
	      text: "사원",
	    }
	  , button:"action"
	  },
	  {
	    name: "estVat",
	    fieldName: "estVat",
	    type: "data",
	    width: "60",
	    header: {
	      text: "부가세",
	    },"lookupDisplay": true,
		  "editor": {
			  "type": "dropdown",
	          "dropDownCount" : 2,
	          "domainOnly" : true,
	          "commitOnSelect" : true,
	          "dropDownWhenClick" : true,
	          "textReadOnly" : true
		  },
		  "values": ["Y", "N"],
		  "labels": ["여", "부"]
		  , "visible" : false
	  },
	  {
	    name: "estResult",
	    fieldName: "estResult",
	    type: "data",
	    width: "60",
	    header: {
	      text: "완료여부",
	    },"lookupDisplay": true,
		  "editor": {
			  "type": "dropdown",
	          "dropDownCount" : 2,
	          "domainOnly" : true,
	          "commitOnSelect" : true,
	          "dropDownWhenClick" : true,
	          "textReadOnly" : true
		  },
		  "values": ["Y", "N"],
		  "labels": ["여", "부"]
		  , "visible" : false
	  },
	  {
	    name: "estFinish",
	    fieldName: "estFinish",
	    type: "data",
	    width: "60",
	    header: {
	      text: "마감여부",
	    },"lookupDisplay": true,
		  "editor": {
			  "type": "dropdown",
	          "dropDownCount" : 2,
	          "domainOnly" : true,
	          "commitOnSelect" : true,
	          "dropDownWhenClick" : true,
	          "textReadOnly" : true
		  },
		  "values": ["Y", "N"],
		  "labels": ["여", "부"]
		  , "visible" : false
	  }
	];
	
	var fields1 = [
		{
			  fieldName: "estNo",
			  dataType: "number"
		},
		{
			fieldName: "estCode",
			dataType: "text"
		},
		{
			fieldName: "icodeName",
			dataType: "text"
		},
		{
			fieldName: "prodCode",
			dataType: "text"
		},
		{
			fieldName: "prodName",
			dataType: "text"
		},
		{
			fieldName: "gcommName",
			dataType: "text"
		},
		{
			fieldName: "ucommName",
			dataType: "text"
		},
		{
			fieldName: "epDate",
			dataType: "date"
		},
		{
			fieldName: "epQty",
			dataType: "number"
		},
		{
			fieldName: "epUprice",
			dataType: "number"
		},
		{
			fieldName: "epSp",
			dataType: "number"
		},
		{
			fieldName: "epVat",
			dataType: "number"
		},
		{
			fieldName: "epScost",
			dataType: "number"
		},
		{
			fieldName: "projName",
			dataType: "text"
		}
		  
		];
	
	var columns1 = [
		{
			name: "estNo",
			fieldName: "estNo",
			type: "data",
			width: "100",
			header: {
				text: "순서"
			},
			"visible" : false
			
		},
		{
			name: "estCode",
			fieldName: "estCode",
			type: "data",
			width: "100",
			header: {
				text: "견적코드"
			}
		},
		{
			name: "icodeName",
			fieldName: "icodeName",
			type: "data",
			width: "70",
			header: {
				text: "품목계정"
			}
		, "editor": {
	          "type": "dropdown",
	          "dropDownCount" : 7,
	          "domainOnly" : true,
	          "commitOnSelect" : true,
	          "dropDownWhenClick" : true,
	          "values": ["상품", "원재료","부재료","제품","반제품","부산품","저장품"],
	          "labels": ["1.상품", "2.원재료","3.부재료","4.제품","5.반제품","6.부산품","7.저장품"]
	        } 
		
		},
		{
			name: "prodCode",
			fieldName: "prodCode",
			type: "data",
			width: "100",
			header: {
				text: "제품코드"
			}
		, button:"action"
		},
		{
			name: "prodName",
			fieldName: "prodName",
			type: "data",
			width: "100",
			header: {
				text: "품명",
			}
		},
		{
			name: "gcommName",
			fieldName: "gcommName",
			type: "data",
			width: "50",
			header: {
				text: "규격",
			}
			, button:"action"
		},
		{
			name: "ucommName",
			fieldName: "ucommName",
			type: "data",
			width: "50",
			header: {
				text: "단위",
			}
		, button:"action"
		},
		{
			name: "epDate",
			fieldName: "epDate",
			type: "data",
			width: "100",
			header: {
				text: "납기일",
			}, editor: {type: "date", datetimeFormat: "yyyy.MM.dd"}
			, footer: {
	            "styles": {
	                "textAlignment": "far",
	                "font": "굴림,12",
	            },
	            "text": "합계 =>",
	            "groupText": "합계 =>",
	        }
		},
		{
			name: "epQty",
			fieldName: "epQty",
			type: "data",
			width: "60",
			numberFormat: "#,##0",
			header: {
				text: "수량",
			}
			, footer: {
			      expression: "sum",
			      numberFormat: "#,##0",
			      styleName:"orange-column"
			    },
			    styleName: "right-column"
		},
		{
			name : "epUprice",
			fieldName : "epUprice",
			type : "data",
			width : "60",
			numberFormat: "#,##0",
			header: {
				text : "단가"
			}
		},
		{
			name: "epSp",
			fieldName: "epSp",
			type: "data",
			numberFormat: "#,##0",
			width: "100",
			header: {
				text: "공급가액",
			}
		},
		{
			name: "epVat",
			fieldName: "epVat",
			type: "data",
			numberFormat: "#,##0",
			width: "100",
			header: {
				text: "부가세",
			}
		},
		{
			name: "epScost",
			fieldName: "epScost",
			type: "data",
			numberFormat: "#,##0",
			width: "100",
			header: {
				text: "합계금액",
			}
		,  footer: {
		      expression: "sum",
		      numberFormat: "#,##0",
		      styleName:"orange-column"
		    },
		    styleName: "right-column"
		},
		{
			name: "projName",
			fieldName: "projName",
			type: "data",
			width: "100",
			header: {
				text: "프로젝트",
			}
		, button:"action"
		}
		];

	$("#selectOb").on("click",function(){
		var dateStart = $("#obdateStart").val();
		var dateEnd = $("#obdateEnd").val();
		
		if(dateEnd >= dateStart){
			let obSearchForm = $("#obApplySearchForm").ajaxForm({
				url : $.getContextPath() + "/sales/piEstimateApply.do",
				dataType : "json",
				success : function(resp) {
					
					obApplyMasterProvider.fillJsonData(resp, {fillMode: "set"});
				}
			}).submit();
		}else{
			toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
			return false;
		}
	});

	function detailGetOb(masterRow) {
		  detailProvider.clearRows();
		  if (masterRow >= 0) {
		      var mstKey = obApplyMasterProvider.getValue(masterRow, "estCode");
		     $.ajax({
				url : $.getContextPath()+"/sales/piEstimateProdApply.do",
				data : {"estCode" : mstKey},
				method : "post",
				dataType : "json",
				success : function(resp) {
					obApplyDetailProvider.fillJsonData(resp, {fillMode: "set"});
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			});
		  };
	};
	
	// 거래처 모달
	$("#buyerNameByPI").focus(function(e){
        $("#buyer-modal").css('display', 'flex')
	});
	
	// 견적서 추가(적용)
	$("#addObApply").on("click",function(){
		var dataList = []; // 보낼 데이터들 배열
		obApplyMasterGrid.commit();
		 let checkedRows = obApplyMasterGrid.getCheckedRows();
		 let checkedDeRows = obApplyDetailGrid.getCheckedRows();
		 if(checkedRows.length > 0){
			 obApplyMasterProvider.setRowStates(checkedRows, "none", true);
			 obApplyDetailProvider.setRowStates(checkedDeRows, "none", true);
			 for(var checked = 0 ;  checked < checkedRows.length ; checked++){
		            var data = obApplyMasterGrid.getValues(checkedRows[checked]);
		            var year = data.estDate.getFullYear();
		            var month = data.estDate.getMonth()+1;
		            var day = data.estDate.getDate();
		            var date = year + "/" + month + "/" + day;
		            data.estDate = date;
		            dataList.push(data);
			 	}
			 }else{
				 toastr.error("체크하고 적용해주세요");
			 		}
			 if(checkedDeRows.length > 0){
				 var dataProdList = [];
				 var dedata = null;
					for(var checked = 0 ;  checked < checkedDeRows.length ; checked++){
						 dedata = obApplyDetailGrid.getValues(checkedDeRows[checked]);
						 dataProdList.push(dedata);
					}
			 } // if끝
			 // 견적서 적용 데이터 요청하기
			 var allData = {"dataList" : dataList, "dataProdList" : dataProdList}
						$.ajax({
							url : $.getContextPath()+"/sales/piEstimateApplyInsert.do",
							data : JSON.stringify(allData),
							method : "post",
							dataType : "json",
							contentType : "application/json",
							success : function(resp) {
								if(resp=="적용실패"){
									toastr.error("수주서에 품목이 존재하지 않아 일부 적용되지 않습니다.");
								}
								$("#orderbookApplyForPI-modal").css("display", "none")
								$("#searchForm").ajaxForm({
									url : $.getContextPath() + "/sales/OrderbookRetrieve.do",
									dataType : "json",
									success : function(data) {
										masterProvider.fillJsonData(data, {fillMode : "set"});
										let lenght = masterProvider.getRows().length;
										for(var i = 0; i<lenght; i++){
											data = masterGrid.getValues(i);
											let year = data.orbDate.getFullYear();
											let month = data.orbDate.getMonth()+1;
											let day = data.orbDate.getDate();
											masterProvider.setValue(i,0,year);
											masterProvider.setValue(i,1,month);
											masterProvider.setValue(i,2,day);
											masterProvider.setRowState(i,"none");
										}
									},
									error : function(xhr, errorResp, error){
										console.log(xhr);
										console.log(errorResp);
										console.log(error);
									}
								}).submit();
							},
							error : function(xhr, errorResp, error) {
								console.log(xhr);
								console.log(errorResp);
								console.log(error);
							}
						}); // ajax 끝
	});
	
	
	
//리얼그리드 공통부분
var obApplyMasterProvider, obApplyMasterGrid, obApplyDetailProvider, obApplyDetailGrid;

/* 			기본 셋팅 obApplyMaster	 	*/
function createobApplyMasterGrid() {
	
	obApplyMasterProvider = new RealGrid.LocalDataProvider();
	obApplyMasterGrid = new RealGrid.GridView("realgrid_obApplyForPIMaster");
	obApplyMasterGrid.stateBar.visible = false;
	obApplyMasterGrid.setDataSource(obApplyMasterProvider);
	obApplyMasterProvider.setFields(fields);
	obApplyMasterGrid.setColumns(columns);
	obApplyMasterGrid.resetCurrent();
	obApplyMasterGrid.footer.visible = false;

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	obApplyMasterGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , fitStyle : "even"
	  , rowHeight : 30
	  });
  
  // obApplyMasterGrid 속성값
	obApplyMasterGrid.header.height = 40;
	obApplyMasterGrid.footer.height = 40;
	obApplyMasterGrid.stateBar.width = 10;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	obApplyMasterGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : false
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  , enterToTab : true
	  ,forceAppend:true
  });
  
  // 클릭한 곳의 Row의 넘버를 넘겨줌.
  obApplyMasterGrid.onCurrentRowChanged = function (grid, oldRow, newRow) {
    detailGetOb(newRow);
  };
  
}  
  
  /* 		기본 셋팅 obApplyDetail	 	*/
  function createobApplyDetailGrid() {
	  obApplyDetailProvider = new RealGrid.LocalDataProvider();
	  obApplyDetailGrid = new RealGrid.GridView("realgrid_obookApplyForPIDetail");
	  obApplyDetailGrid.stateBar.visible = false;
	  obApplyDetailGrid	.setCheckBar({visible: false});
	  obApplyDetailGrid.setDataSource(obApplyDetailProvider);
	  obApplyDetailProvider.setFields(fields1);
	  obApplyDetailGrid.setColumns(columns1);

    // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	  obApplyDetailGrid.setDisplayOptions({
  	  emptyMessage : "표시할 데이타가 없습니다."
  	  , showEmptyMessage: true
  	  , fitStyle : "even"
  	  , rowHeight : 30
  	  });

    
    // obApplyMasterGrid 속성값
	  obApplyDetailGrid.header.height = 40;
	  obApplyDetailGrid.footer.height = 40;
	  obApplyDetailGrid.stateBar.width = 10;
	  obApplyDetailGrid.setEditOptions({
		  insertable : true
		  , appendable : true
		  , editable : true
		  , deletable: true
		  , confirmWhenDelete : true
		  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  });
    
	  
	  setTimeout(function(){
		  obApplyMasterGrid.resetCurrent();
	  },300);
  
  }



function start() {
  createobApplyMasterGrid();
  createobApplyDetailGrid();
}

window.onload = start();
window.onunload = function() {
	  dataProvider.clearRows();

	  gridView.destroy();
	  dataProvider.destroy();

	  gridView = null;
	  dataProvider = null;
}




