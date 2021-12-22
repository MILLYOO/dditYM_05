/**
 * 출고지시서적용
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
	    fieldName: "reoNum",
	    dataType: "text"
	  },
	  {
		  fieldName: "reoDate",
		  dataType: "date"
	  },
	  {
	    fieldName: "buyerCode",
	    dataType: "number"
	  },
	  {
	    fieldName: "buyerName",
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
	    fieldName: "reoVat",
	    dataType: "text"
	  },
	  {
	    fieldName: "reoResult",
	    dataType: "text"
	  },
	  {
	    fieldName: "reoFinish",
	    dataType: "text"
	  }
	];

	var columns = [
	  {
	    name: "reoNum",
	    fieldName: "reoNum",
	    type: "data",
	    width: "120",
	    header: {
	      text: "출고지시번호"
	    }
	  },
	  {
	    name: "reoDate",
	    fieldName: "reoDate",
	    type: "data",
	    width: "100",
	    header: {
	      text: "날짜"
	    }, 
	    editor: {type: "date", datetimeFormat: "yyyy/MM/dd"}
	    , "visible" : false
	  },
	  {
	    name: "buyerCode",
	    fieldName: "buyerCode",
	    type: "data",
	    width: "65",
	    header: {
	      text: "거래처코드"
	    },
	  	numberFormat: "#,##0"
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
	    name: "reoVat",
	    fieldName: "reoVat",
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
	    name: "reoResult",
	    fieldName: "reoResult",
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
	    name: "reoFinish",
	    fieldName: "reoFinish",
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
			  fieldName: "reoNo",
			  dataType: "number"
		},
		{
			fieldName: "reoNum",
			dataType: "text"
		},
		{
			fieldName: "prodCode",
			dataType: "text"
		},
		{
			fieldName: "icodeName",
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
			fieldName: "ropDate",
			dataType: "date"
		},
		{
			fieldName: "ropQty",
			dataType: "number"
		},
		{
			fieldName: "ropUprice",
			dataType: "number"
		},
		{
			fieldName: "ropSp",
			dataType: "number"
		},
		{
			fieldName: "ropVat",
			dataType: "number"
		},
		{
			fieldName: "ropSumcost",
			dataType: "number"
		},
		{
			fieldName: "warName",
			dataType: "text"
		},
		{
			fieldName: "projName",
			dataType: "text"
		},
		{
			fieldName: "orbNum",
			dataType: "text"
		}
		  
		];
	
	var columns1 = [
		{
			name: "reoNo",
			fieldName: "reoNo",
			type: "data",
			width: "100",
			header: {
				text: "순서"
			},
			"visible" : false
			
		},
		{
			name: "reoNum",
			fieldName: "reoNum",
			type: "data",
			width: "100",
			header: {
				text: "출고지시번호"
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
			name: "icodeName",
			fieldName: "icodeName",
			type: "data",
			width: "50",
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
			name: "ropDate",
			fieldName: "ropDate",
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
			name: "ropQty",
			fieldName: "ropQty",
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
			name : "ropUprice",
			fieldName : "ropUprice",
			type : "data",
			width : "60",
			numberFormat: "#,##0",
			header: {
				text : "단가"
			}
		},
		{
			name: "ropSp",
			fieldName: "ropSp",
			type: "data",
			numberFormat: "#,##0",
			width: "100",
			header: {
				text: "공급가액",
			}
		},
		{
			name: "ropVat",
			fieldName: "ropVat",
			type: "data",
			numberFormat: "#,##0",
			width: "100",
			header: {
				text: "부가세",
			}
		},
		{
			name: "ropSumcost",
			fieldName: "ropSumcost",
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
			name: "warName",
			fieldName: "warName",
			type: "data",
			width: "100",
			header: {
				text: "창고",
			}
		, button:"action"
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
		},
		{
			name: "orbNum",
			fieldName: "orbNum",
			type: "data",
			width: "100",
			header: {
				text: "수주번호",
			}
		, button:"action"
			, "visible" : false
		}
		];

	$("#selectOb").on("click",function(){ 
		var dateStart = $("#dateStart").val();
		var dateEnd = $("#dateEnd").val();
		
		if(dateEnd >= dateStart){ 
			let obSearchForm = $("#obApplySearchForm").ajaxForm({ 
				url : $.getContextPath() + "/sales/piReleaseApply.do", 
				dataType : "json", 
				success : function(resp) {
					obApplyMasterProvider.fillJsonData(resp, {fillMode: "set"}); // 마스터그리드에 수주서 적용 데이터 출력
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
		      var mstKey = obApplyMasterProvider.getValue(masterRow, "reoNum"); 
		     $.ajax({
				url : $.getContextPath()+"/sales/piReleaseProdApply.do", 
				data : {"reoNum" : mstKey}, // 수주서번호 가지고 가기
				method : "post",
				dataType : "json",
				success : function(resp) {
					obApplyDetailProvider.fillJsonData(resp, {fillMode: "set"}); // 수주서 - 제품 컨트롤에서 가져온 데이터를 디테일 그리드에 뿌려준다.
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			});
		  };
	};
	
	// 거래처 
	$("#buyerNameByPI").focus(function(e){
        $("#buyer-modal").css('display', 'flex')
	});
	
	// 출고지시서 추가
	$("#addObApply").on("click",function(){ // 적용 버튼 클릭시 
		var dataList = [];
		
		obApplyMasterGrid.commit();
		 let checkedRows = obApplyMasterGrid.getCheckedRows(); 
		 let checkedDeRows = obApplyDetailGrid.getCheckedRows(); 
		 if(checkedRows.length > 0){
			 obApplyMasterProvider.setRowStates(checkedRows, "none", true); 
			 obApplyDetailProvider.setRowStates(checkedDeRows, "none", true); 
			 for(var checked = 0 ;  checked < checkedRows.length ; checked++){ 
		            var data = obApplyMasterGrid.getValues(checkedRows[checked]); 
		            var year = data.reoDate.getFullYear(); 
		            var month = data.reoDate.getMonth()+1; 
		            var day = data.reoDate.getDate(); 
		            var date = year + "/" + month + "/" + day; 
		            data.reoDate = date; 
		            dataList.push(data);
			 }	// for문 끝
		 }else{
				toastr.error("체크하고 적용해주세요");
		 	  }		
		 if(checkedDeRows.length > 0){
			 var dataProdList = [];
					for(var checked = 0 ;  checked < checkedDeRows.length ; checked++){
						let dedata = obApplyDetailGrid.getValues(checkedDeRows[checked]);
						dataProdList.push(dedata);
					 } // for문 끝
					} // if문 끝
		 var allData = {"dataList" : dataList, "dataProdList" : dataProdList}
		 $.ajax({ 
				url : $.getContextPath()+"/sales/piReleaseApplyInsert.do", 
				data : JSON.stringify(allData),
				method : "post",
				dataType : "json",
				contentType : "application/json",
				success : function(resp) {
					if(resp=="적용실패"){ // 결과값이 실패일 경우
						toastr.error("출고지시서에 품목이 존재하지 않아 일부 적용되지 않습니다.");
					}
					$("#orderbookApplyForPI-modal").css("display", "none")
					$("#searchForm").ajaxForm({
						url : $.getContextPath() + "/sales/ReleaseProcessingRetrieve.do",
						dataType : "json",
						success : function(data) {
							masterProvider.fillJsonData(data, {fillMode : "set"});
							let lenght = masterProvider.getRows().length;
							for(var i = 0; i<lenght; i++){
								data = masterGrid.getValues(i);
								let year = data.relDate.getFullYear();
								let month = data.relDate.getMonth()+1;
								let day = data.relDate.getDate();
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
	  , rowHeight : 30
	  , fitStyle : "even"
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
	  , forceAppend:true
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
  	  , rowHeight : 30
  	, fitStyle : "even"
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