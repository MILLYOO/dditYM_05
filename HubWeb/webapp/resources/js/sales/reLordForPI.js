/**
 * 수주서적용
 */

// 이달 1일 ~ 현재 날짜 세팅
 var date = new Date();
 $("#obStartDate").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
 $("#obEndDate").val(new Date().toISOString().substring(0, 10));


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
	    fieldName: "orbNum",
	    dataType: "text"
	  },
	  {
		  fieldName: "orbDate",
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
	    fieldName: "orbVat",
	    dataType: "text"
	  },
	  {
	    fieldName: "orbResult",
	    dataType: "text"
	  },
	  {
	    fieldName: "orbFinish",
	    dataType: "text"
	  }
	];

	var columns = [
	  {
	    name: "orbNum",
	    fieldName: "orbNum",
	    type: "data",
	    width: "120",
	    header: {
	      text: "수주번호"
	    }
	  },
	  {
	    name: "orbDate",
	    fieldName: "orbDate",
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
	    name: "orbVat",
	    fieldName: "orbVat",
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
	    name: "orbResult",
	    fieldName: "orbResult",
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
	    name: "orbFinish",
	    fieldName: "orbFinish",
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
			  fieldName: "orbNo",
			  dataType: "number"
		},
		{
			fieldName: "orbNum",
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
			fieldName: "opDate",
			dataType: "date"
		},
		{
			fieldName: "opQty",
			dataType: "number"
		},
		{
			fieldName: "opUprice",
			dataType: "number"
		},
		{
			fieldName: "opSp",
			dataType: "number"
		},
		{
			fieldName: "opVat",
			dataType: "number"
		},
		{
			fieldName: "opSumcost",
			dataType: "number"
		},
		{
			fieldName: "projName",
			dataType: "text"
		}
		  
		];
	
	var columns1 = [
		{
			name: "orbNo",
			fieldName: "orbNo",
			type: "data",
			width: "100",
			header: {
				text: "순서"
			},
			"visible" : false
			
		},
		{
			name: "orbNum",
			fieldName: "orbNum",
			type: "data",
			width: "100",
			header: {
				text: "수주번호"
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
			name: "opDate",
			fieldName: "opDate",
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
			name: "opQty",
			fieldName: "opQty",
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
			name : "opUprice",
			fieldName : "opUprice",
			type : "data",
			width : "60",
			numberFormat: "#,##0",
			header: {
				text : "단가"
			}
		},
		{
			name: "opSp",
			fieldName: "opSp",
			type: "data",
			numberFormat: "#,##0",
			width: "100",
			header: {
				text: "공급가액",
			}
		},
		{
			name: "opVat",
			fieldName: "opVat",
			type: "data",
			numberFormat: "#,##0",
			width: "100",
			header: {
				text: "부가세",
			}
		},
		{
			name: "opSumcost",
			fieldName: "opSumcost",
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

	$("#selectOb").on("click",function(){ // 수주서 적용에서 조회 버튼 클릭시
		var dateStart = $("#dateStart").val();
		var dateEnd = $("#dateEnd").val();
		
		// 날짜 지정하지 않았을 때
//		if(dateStart.replace(/\s/g,"").length==0){
//			Swal.fire("날짜를 선택하세요");
//			return false;
//		}
		
		if(dateEnd >= dateStart){ // 시작일자가 끝나는 일자보다 크면 안된다.
			let obSearchForm = $("#obApplySearchForm").ajaxForm({ // 폼 안에 데이터를 가지고 에이작스 폼으로 날려보낸다.
				url : $.getContextPath() + "/sales/piOrderBookApply.do", // 수주서 적용을 위한 컨트롤 주소 입력
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

	function detailGetOb(masterRow) { // 수주서 적용에서 제품 목록 나타내는 프로그램
		  detailProvider.clearRows();
		  if (masterRow >= 0) { // 마스터 로우 값이 있으면
		      var mstKey = obApplyMasterProvider.getValue(masterRow, "orbNum"); // 수주번호 가져오기
		     $.ajax({
				url : $.getContextPath()+"/sales/piOrderBookProdApply.do", // 수주서 - 제품 적용 컨트롤러 가기
				data : {"orbNum" : mstKey}, // 수주서번호 가지고 가기
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
	
	// 수주서 추가
	$("#addObApply").on("click",function(){ // 적용 버튼 클릭시 
		var dataList = []; // 보낼 데이터들 배열
		obApplyMasterGrid.commit();
		 let checkedRows = obApplyMasterGrid.getCheckedRows(); // 체크된 수주서 개수
		 let checkedDeRows = obApplyDetailGrid.getCheckedRows(); // 체크된 수주서 - 제품 개수
		 if(checkedRows.length > 0){
			 obApplyMasterProvider.setRowStates(checkedRows, "none", true); // 마스터 그리드에서 선택된 행의 상태를 일반상태로
			 obApplyDetailProvider.setRowStates(checkedDeRows, "none", true); // 디테일 그리드에서 선택된 행의 상태를 일반상태로
			 for(var checked = 0 ;  checked < checkedRows.length ; checked++){ // 체크된 수만큼 포문 진행
		            let data = obApplyMasterGrid.getValues(checkedRows[checked]); // 체크된 그리드에서 값을 꺼내온다.
		            let year = data.orbDate.getFullYear(); // 년
		            let month = data.orbDate.getMonth()+1; // 월
		            let day = data.orbDate.getDate(); // 일
		            let date = year + "/" + month + "/" + day; // 날짜 값 다시 정의
		            data.orbDate = date; // 날짜 값 등록
		            dataList.push(data);
			 }
		 }else{
				toastr.error("체크하고 적용해주세요");
		 	  }
		 if(checkedDeRows.length > 0){
			 var dataProdList = [];
			 var dedata = null
				for(var checked = 0 ;  checked < checkedDeRows.length ; checked++){
					 dedata = obApplyDetailGrid.getValues(checkedDeRows[checked]);
					dataProdList.push(dedata);
				}
		 }
			 var allData = {"dataList" : dataList, "dataProdList" : dataProdList}
						$.ajax({ // 에이작스 실행
							url : $.getContextPath()+"/sales/piOrderBookApplyInsert.do", // 수주서 출고지시서 마스터 그리드 행에 삽입하는 컨트롤
							data : JSON.stringify(allData),
							method : "post",
							dataType : "json",
							contentType : "application/json",
							success : function(resp) {
								if(resp=="적용실패"){ // 결과값이 실패일 경우
									toastr.error("수주서에 품목이 존재하지 않아 일부 적용되지 않습니다.");
								}
								$("#orderbookApplyForPI-modal").css("display", "none")
								$("#searchForm").ajaxForm({
									url : $.getContextPath() + "/sales/ReleaseOrderRetrieve.do",
									dataType : "json",
									success : function(data) {
										masterProvider.fillJsonData(data, {fillMode : "set"});
										let lenght = masterProvider.getRows().length;
										for(var i = 0; i<lenght; i++){
											data = masterGrid.getValues(i);
											let year = data.reoDate.getFullYear();
											let month = data.reoDate.getMonth()+1;
											let day = data.reoDate.getDate();
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
//								var reoNum = resp.roVO.reoNum;
//								var buyerCode = resp.roVO.buyerCode;
//								var buyerName = resp.roVO.buyerName;
//								var deptName = resp.roVO.deptName;
//								var empName = resp.roVO.empName;
//								var reoVat = resp.roVO.reoVat;
//								var reoResult = resp.roVO.reoResult;
//								var reoFinish = resp.roVO.reoFinish;
//								var rows = masterProvider.getRows();
//								var index = rows.length-1;
//								masterProvider.setValue(index,3,reoNum);
//								masterProvider.setValue(index,5,buyerCode);
//								masterProvider.setValue(index,6,buyerName);
//								masterProvider.setValue(index,7,deptName);
//								masterProvider.setValue(index,8,empName);
//								masterProvider.setValue(index,9,reoVat);
//								masterProvider.setValue(index,10,reoResult);
//								masterProvider.setValue(index,11,reoFinish);
//								
//								Swal.fire("등록 성공");
////								
//								$("#orderbookApplyForPI-modal").css("display", "none")
//								
//								
//							},
//							error : function(xhr, errorResp, error) {
//								console.log(xhr);
//								console.log(errorResp);
//								console.log(error);
//							}
//						});
//		            
//		 		
//		 
//						$.ajax({
//							url : $.getContextPath()+"/sales/piOrderBookApplyInsert.do",
//							data : dedata,
//							method : "post",
//							dataType : "json",
//							async : false,
//							success : function(resp) {
//								if(resp=="적용실패"){
//									Swal.fire("수주서에 품목이 존재하지 않아 일부 적용되지 않습니다.");
//								}
//								var values = []; // 추가할 행의 배열
//								 for(var i = 0 ; i < fields1.length ; i++){ // 포문을 통해 배열에 빈값 배치
//							           values.push = "";
//							        }
//								 
//								 	var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow); // 마스터 그리드에서 선택한 데이터를 추가 버튼 누르면 가져온다.
//							        values[1] = masterData.orbNum; // 가져온 데이터중 2번째 행의 수주번호를 가져온다.
//							        detailProvider.addRow(values); // 가져온 데이터를 디테일 그리드에 추가
//							        var row = detailGrid.getItemCount();
//							        detailProvider.setValue(row-1,2,'제품'); // 품목은 제품으로 한정하기 위한 코드
//							        detailGrid.commit(); // 그리드 커밋 편집 상태 해제
//									detailGrid.showEditor();
//									detailGrid.setFocus();
//									
//									var reoNum = masterData.reoNum;
//									var prodCode = resp.roprodVO.prodCode;
//									var prodName = resp.roprodVO.prodName;
//									var gcommName = resp.roprodVO.gcommName;
//									var ucommName = resp.roprodVO.ucommName;
//									var icodeName = resp.roprodVO.icodeName;
//									var ropDate = resp.roprodVO.ropDate;
//									var ropQty = resp.roprodVO.ropQty;
//									var ropUprice = resp.roprodVO.ropUprice;
//									var ropSp = resp.roprodVO.ropSp;
//									var ropVat = resp.roprodVO.ropVat;
//									var ropSumcost = resp.roprodVO.ropSumcost;
//									var orbNum = resp.roprodVO.orbNum;
//									var rows = detailProvider.getRows();
//									var index = rows.length-1;
//									detailProvider.setValue(index,1,reoNum);
//									detailProvider.setValue(index,2,icodeName);
//									detailProvider.setValue(index,3,prodCode);
//									detailProvider.setValue(index,4,prodName);
//									detailProvider.setValue(index,5,gcommName);
//									detailProvider.setValue(index,6,ucommName);
//									detailProvider.setValue(index,7,ropDate);
//									detailProvider.setValue(index,8,ropQty);
//									detailProvider.setValue(index,9,ropUprice);
//									detailProvider.setValue(index,10,ropSp);
//									detailProvider.setValue(index,11,ropVat);
//									detailProvider.setValue(index,12,ropSumcost);
//									detailProvider.setValue(index,13,orbNum);
//								Swal.fire("등록 성공");
////								
//								$("#orderbookApplyForPI-modal").css("display", "none")
//								
//								
//							},
//							error : function(xhr, errorResp, error) {
//								console.log(xhr);
//								console.log(errorResp);
//								console.log(error);
//							}
//						});
//						 
//					}
	});
	
	
//리얼그리드 공통부분
var obApplyMasterProvider, obApplyMasterGrid, obApplyDetailProvider, obApplyDetailGrid;

/* 			기본 셋팅 obApplyMaster	 	*/
function createobApplyMasterGrid() {
	
	obApplyMasterProvider = new RealGrid.LocalDataProvider();
	obApplyMasterGrid = new RealGrid.GridView("realgrid_obApplyForPIMaster");

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




