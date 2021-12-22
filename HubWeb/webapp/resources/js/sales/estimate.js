/**
 * 
 */


2	// 이달 1일 ~ 현재 날짜 세팅
	var date = new Date();
	$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEnd").val(new Date().toISOString().substring(0, 10));


  var fields = [
	  {
		  fieldName: "salYy",
		  dataType: "number"
	  },
	  {
		  fieldName: "salMm",
		  dataType: "number"
	  },
	  {
		  fieldName: "salDd",
		  dataType: "number"
	  },
	  {
	    fieldName: "estCode",
	    dataType: "text"
	  },
	  {
		  fieldName: "estDate",
		  dataType: "date"
	  },
	  {
			fieldName : "buyerCode",
			dataType : "number"
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
			name: "salYy",
			fieldName: "salYy",
			type: "data",
			width: "20",
			header: {
				text: "년"
			},
		numberFormat: "###0"
		},
		{
			name: "salMm",
			fieldName: "salMm",
			type: "data",
			width: "10",
			header: {
				text: "월"
			},
		numberFormat: "#,##0"
		},
		{
			name: "salDd",
			fieldName: "salDd",
			type: "data",
			width: "10",
			header: {
				text: "일"
			},
			numberFormat: "#,##0"
		},
	  {
	    name: "estCode",
	    fieldName: "estCode",
	    type: "data",
	    width: "60",
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
			name : "buyerCode",
			fieldName : "buyerCode",
			type : "data",
			width : "60",
			header: {
				text : "거래처코드"
			},
			numberFormat: "#,##0"
			, "visible" : false
		},
	  {
	    name: "buyerName",
	    fieldName: "buyerName",
	    type: "data",
	    width: "50",
	    header: {
	      text: "거래처명"
	    }
	  	, button:"action"
	  },
	  {
	    name: "deptName",
	    fieldName: "deptName",
	    type: "data",
	    width: "30",
	    header: {
	      text: "부서",
	    }
	  , button:"action"
	  },
	  {
	    name: "empName",
	    fieldName: "empName",
	    type: "data",
	    width: "30",
	    header: {
	      text: "사원",
	    }
	  , button:"action"
	  },
	  {
	    name: "estVat",
	    fieldName: "estVat",
	    type: "data",
	    width: "20",
	    header: {
	      text: "부가세",
	    },
	    "lookupDisplay": true,
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
	    width: "20",
	    header: {
	      text: "완료여부",
	    },
	    "lookupDisplay": true,
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
	    width: "20",
	    header: {
	      text: "마감여부",
	    },
	    "lookupDisplay": true,
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
			},
			"visible" : false
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
			},
			styleName: "left-column"
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
			width: "80",
			numberFormat: "#,##0",
			header: {
				text: "수량(단위:개)",
			}
			, footer: {
			      expression: "sum",
			      numberFormat: "#,##0",
			      styleName:"orange-column"
			    }
			,styleName: "right-column"
		},
		{
			name : "epUprice",
			fieldName : "epUprice",
			type : "data",
			width : "60",
			numberFormat: "#,##0",
			header: {
				text : "단가"
			},
		    styleName: "right-column"
		},
		{
			name: "epSp",
			fieldName: "epSp",
			type: "data",
			numberFormat: "#,##0",
			width: "100",
			header: {
				text: "공급가액",
			},
		    styleName: "right-column"
		},
		{
			name: "epVat",
			fieldName: "epVat",
			type: "data",
			numberFormat: "#,##0",
			width: "100",
			header: {
				text: "부가세",
			},
		    styleName: "right-column"
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

  // 1. 각자 메뉴에 맞는 컬럼, 필드 만들기(해당 메뉴에 맞는 컬럼과 필드를 만들어야함, VO와 명칭도 일치해야함)
  // 2. 검색, 수정, 삭제시, 조건 알아서 넣기.
  // 
  // -> alert, confirm 나중에 모달로 변경해야함.
  /********************************* 마스터에 값 넣기 ******************************/
  // 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능
  
  // 목록 조회	
  $("#select2").click(function(){
	  // 시작 날짜
	  var startDate = $("#dateStart").val();
	  // 종료 날짜
	  var endDate = $("#dateEnd").val();
	  // 종료 날짜가 시작 날짜보다 이전일 경우
	  if (endDate < startDate) {
		  	toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
			return false;
	  }
	  	
	  	masterGrid.columnByName("buyerName").editable =  false;
		masterGrid.columnByName("deptName").editable =  false;
		masterGrid.columnByName("empName").editable =  false;
		masterGrid.columnByName("estCode").editable =  false;
		masterGrid.columnByName("salYy").editable =  false;
		masterGrid.footer.visible = false;
		
	$("#searchForm").ajaxForm({
	   url : $.getContextPath() + "/sales/EstimateRetrieve.do",
	    dataType:"json",
	    success:function(data){
	       masterProvider.fillJsonData(data, {fillMode: "set"});
	       let lenght = masterProvider.getRows().length;
	       console.log(lenght);
	    	   for(var i = 0 ; i < lenght; i++){
		    	   let index = i;
		    	   data = masterGrid.getValues(index);
		    	   let year = data.estDate.getFullYear();
		    	   let month = data.estDate.getMonth()+1;
		    	   let day = data.estDate.getDate();
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
  });
  
  $("#select1").click(function(){
	  // 시작 날짜
	  var startDate = $("#dateStart").val();
	  // 종료 날짜
	  var endDate = $("#dateEnd").val();
	  // 종료 날짜가 시작 날짜보다 이전일 경우
	  if (endDate < startDate) {
		  toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		  return false;
	  }
	  
	  masterGrid.columnByName("buyerName").editable =  false;
	  masterGrid.columnByName("deptName").editable =  false;
	  masterGrid.columnByName("empName").editable =  false;
	  masterGrid.columnByName("estCode").editable =  false;
	  masterGrid.columnByName("salYy").editable =  false;
	  masterGrid.columnByName("salMm").editable =  false;
	  masterGrid.footer.visible = false;
	  
	  $("#searchForm").ajaxForm({
		  url : $.getContextPath() + "/sales/EstimateRetrieve.do",
		  dataType:"json",
		  success:function(data){
			  masterProvider.fillJsonData(data, {fillMode: "set"});
			  let lenght = masterProvider.getRows().length;
			  console.log(lenght);
			  for(var i = 0 ; i < lenght; i++){
				  let index = i;
				  data = masterGrid.getValues(index);
				  console.log(data);
				  let year = data.estDate.getFullYear();
				  console.log(year);
				  let month = data.estDate.getMonth()+1;
				  console.log(month);
				  let day = data.estDate.getDate();
				  console.log(day);
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
  });
  

	// 저장버튼을 사용하기 위한 설정
	// 백단에서 기본키가 되는 code가 없으면 insert, 있으면 update
	$("#saved").on("click", function(){
		var dataList = [];
		let checkedRows = masterGrid.getCheckedRows();
		if(checkedRows.length > 0){
			for(var checked = 0 ;  checked < checkedRows.length ; checked++){
				let index = checkedRows[checked]; // 그리드에서 체크된 행의 번호
				let data = masterGrid.getValues(index);
				let year = data.salYy;
				let month = data.salMm;
				let day = data.salDd;
				let date = year + "/" + month + "/" + day;
				data.estDate = date;
				var state = masterProvider.getRowState(index);
				if (state == "created") { // 새로 만들어진 행일 경우
					data.estCode = '';
				}
				dataList.push(data); // 여러 문서를 저장할 경우 리스트 형태로 담아준다.
			} // 반복문 끝
			// 하위 품목 존재할 경우
			let value = detailProvider.getRows().length;
			if (value > 0) {
				let checkedRows = detailGrid.getCheckedRows(); //디테일 그리드에서 체크된 행 수
				 detailGrid.commit(); // 디테일 그리드 커밋
				 if(checkedRows.length > 0){ // 체크된 행이 있을 경우
					 var dataProdList = [];
					 for(var checked = 0; checked < checkedRows.length; checked++){
						 let dedata = detailGrid.getValues(checkedRows[checked]);
						 let deyear = dedata.epDate.getFullYear(); // 연도 변수
						 let demonth = dedata.epDate.getMonth() + 1; // 월 변수
						 let deday = dedata.epDate.getDate(); // 일 변수
						 let dedate = deyear + "/" + demonth + "/" + deday; // 디테일 그리드에서 꺼낸 년,월,일을 저장하기 위해 다시 날짜 변수에 초기화
						 dedata.epDate = dedate; // 초기화한 날짜 변수를 vo에 넣기 위해 초기화
						 let mstKey = dedata.estCode; // 마스터키에 디테일 그리드에서 가져온 수주번호값 넣어줌
//						 let Vat = dedata.epVat;
//						 let qty = dedata.epQty;
//						 Vat = dedata.epSp*0.03*qty; // 부가세
//						 dedata.epVat = Vat;
//						 let scost = dedata.epSp * qty + Vat; // 합계 금액
//						 dedata.epSumcost = scost;
						 dataProdList.push(dedata);
					 }
				 }
			} // if문 끝 
			var obj = {"commonList":dataList}
			$.ajax({
					   url : $.getContextPath() + "/sales/EstimateInsert.do",
					   data : JSON.stringify(obj),
					   method : 'post',
					   dataType : 'json',
					   contentType : "application/json",
					   success : function(resp){
						   if(resp.result == 'OK'){
							   
								toastr.success(resp.message);
								
								var searchForm = $("#searchForm").ajaxForm({
									   url : $.getContextPath() + "/sales/EstimateRetrieve.do",
									   dataType: "json",
									    success: function(data){
									       masterProvider.fillJsonData(data, {fillMode: "set"});
									       let lenght = masterProvider.getRows().length;
									       for(var i = 0 ; i < lenght; i++){
												let data = masterGrid.getValues(i);
												let year = data.estDate.getFullYear();
												let month = data.estDate.getMonth() +1;
												let day = data.estDate.getDate();
												masterProvider.setValue(i,0,year);
												masterProvider.setValue(i,1,month);
												masterProvider.setValue(i,2,day);
												masterProvider.setRowState(i,"none");
											}
										}
									}).submit();
							}else{
								if (Array.isArray(resp.message)) {
									
									for(var idx in resp.message){
										toastr.error(resp.message[idx]);
									}
								}else{
									toastr.error(resp.message);
								}
							}
						  
					   },
					   error: function(xhr, errorResp, error){
						   console.log(xhr);
						   console.log(errorResp);
						   console.log(error);
					   }
				});
		}else{
			toastr.error("체크하고 등록해 주세요");
		}
	});

	// 추가버튼
	$("#append").on("click", function(){
		
		var count = masterProvider.getRowStateCount("created");
	      if(count > 0){
	         toastr.error("추가된 행을 저장해주세요");
	      }else{
	         var values = [];
	         for(var i = 0 ; i < fields.length ; i++){
	            values.push = "";
	         }
	         let today = new Date();
	         let year = today.getFullYear();
	         let month = today.getMonth()+1;
	         var values = [year, month, "", "", "", "", ""];
	         masterProvider.addRow(values);
	         let lastday = new Date(year,month,0).getDate();
	      }
	})
	
	// 삭제버튼
	$("#delete").on("click", function(){
		console.log("삭제");
		let checkedRows = masterGrid.getCheckedRows();
		if(checkedRows.length > 0){
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
                		var dataList = [];
                		for(var checked = 0 ;  checked < checkedRows.length ; checked++){
                			let index = checkedRows[checked];
                			let data = masterGrid.getValues(index);
                			dataList.push(data);
                		} // for문 끝
                		$.ajax({
						   url : $.getContextPath() + "/sales/EstimateDelete.do",
						   data : JSON.stringify(dataList),
						   method : 'post',
						   dataType : 'json',
						   contentType : 'application/json',
						   success : function(data){
							   toastr.success(data.message);
						   $("#searchForm").ajaxForm({
							   url : $.getContextPath() + "/sales/EstimateRetrieve.do",
							    dataType:"json",
							    success:function(data){
							       masterProvider.fillJsonData(data, {fillMode: "set"});
							       let lenght = masterProvider.getRows().length;
							       for(var i = 0 ; i < lenght; i++){
							    	   let index = i;
							    	   data = masterGrid.getValues(index);
							    	   let year = data.estDate.getFullYear();
							    	   let month = data.estDate.getMonth() +1;
							    	   let day = data.estDate.getDate();
							    	   console.log(day);
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
					   error: function(xhr, errorResp, error){
						   console.log(xhr);
						   console.log(errorResp);
						   console.log(error);
					   		}
                		});
                	 } // 조건문 끝
                });
		}else{
			toastr.error("체크하고 삭제해주세요");
		}
		});
	
  /********************************* 디테일 설정하기 ******************************/
  
  function detailGet(masterRow) {
	  detailProvider.clearRows();
	  if (masterRow >= 0) {
	      var mstKey = masterProvider.getValue(masterRow, "estCode");
	      $.getJSON( $.getContextPath() + "/sales/EstimateProdRetrieve.do?estCode=" + mstKey, function(data){
    	  detailProvider.fillJsonData(data, {fillMode: "set"});
    	  var datas = []
      	}) 
	  };
  
	  detailGrid.columnByName("estCode").editable =  false;
	  detailGrid.commit();
	  detailGrid.columnByName("icodeName").editable =  false;
	  detailGrid.commit();
	  detailGrid.columnByName("prodCode").editable =  false;
	  detailGrid.commit();
	  detailGrid.columnByName("prodName").editable =  false;
	  detailGrid.commit();
	  detailGrid.columnByName("gcommName").editable =  false;
	  detailGrid.commit();
	  detailGrid.columnByName("ucommName").editable =  false;
	  detailGrid.commit();
	  detailGrid.columnByName("projName").editable =  false;
  	};

	// 저장버튼을 사용하기 위한 설정
	// 백단에서 기본키가 되는 code가 없으면 insert, 있으면 update
	$("#saved1").on("click", function(){
		let checkedRows = detailGrid.getCheckedRows();
		detailGrid.commit();
		if(checkedRows.length > 0){
			var dataProdList = [];
			for(var checked = 0 ;  checked < checkedRows.length ; checked++){
				let index = checkedRows[checked];
				let data = detailGrid.getValues(index);
					if(data.epDate){
						let year = data.epDate.getFullYear();
						let month = data.epDate.getMonth() + 1;
						let day = data.epDate.getDate();
						let date = year + "/" + month + "/" + day;
						data.epDate = date;
					}
				let mstKey = data.estCode; 
//				let Vat = data.epVat;
//				let qty = data.epQty;
//				Vat = data.epSp*0.03*qty;
//				data.epVat = Vat;
//				let scost = data.epSp * qty + Vat;
//				data.epScost = scost;
				dataProdList.push(data);
			} // for문 끝
			let obj = {"commonList":dataProdList}
				$.ajax({
					   url : $.getContextPath() + "/sales/EstimateProdInsert.do",
					   data : JSON.stringify(obj),
					   async : false,
					   method : 'post',
					   dataType : 'json',
					   contentType : "application/json",
					   success : function(data){
						   if(data.result=="OK"){
							   toastr.success(data.message);
							   var mstKey = detailGrid.getValue(0,1);
							   $.getJSON( $.getContextPath() + "/sales/EstimateProdRetrieve.do?estCode=" + mstKey, function(data){
								   detailProvider.fillJsonData(data, {fillMode: "set"});
							   }) 
						   }else{
							   if(Array.isArray(data.message)){
								   for(var idx in data.message){
									   toastr.error(data.message[idx]);
								   }
							   }else{
								   toastr.error(data.message);
							   }
						   }
					   },
					   error: function(xhr, errorResp, error){
						   console.log(xhr);
						   console.log(errorResp);
						   console.log(error);
					   }
				});
		}else{
			toastr.error("체크하고 해주세요");
		}
	});

	// 추가버튼
	$("#append1").on("click", function(){
		var values = [];
        for(var i = 0 ; i < fields1.length ; i++){
           values.push = "";
        }
        var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
        values[1] = masterData.estCode;
        detailProvider.addRow(values);
        var row = detailGrid.getItemCount();
        detailProvider.setValue(row-1,2,'제품');
        detailGrid.commit();
		detailGrid.showEditor();
		detailGrid.setFocus();
	})
	
	// 삭제버튼
	$("#delete1").on("click", function(){
			let checkedRows = detailGrid.getCheckedRows();
			if(checkedRows.length > 0){
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
	                    		var dataProdList = [];
	                    		for(var checked = 0 ;  checked < checkedRows.length ; checked++){
	                    			let data = detailGrid.getValues(checkedRows[checked]);
	                    			dataProdList.push(data);
	                    		} // for문 끝
	                    			
	                    			$.ajax({
	         						   url : $.getContextPath() + "/sales/EstimateProdDelete.do",
	         						   data : JSON.stringify(dataProdList),
	         						   method : 'post',
	         						   dataType : "json",
	         						  contentType : "application/json",
	         						   success : function(data){
	         							   toastr.success(data.message)
	         							   var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
	         						       let mstKey = masterData.estCode;
	         							   $.getJSON( $.getContextPath() + "/sales/EstimateProdRetrieve.do?estCode=" + mstKey, function(data){
	         									  detailProvider.fillJsonData(data, {fillMode: "set"});
	         							      	}) 
	         						   },
	         						   error: function(xhr, errorResp, error){
	         							   console.log(xhr);
	         							   console.log(errorResp);
	         							   console.log(error);
	         						   }
	         					});	
	                    	}
	                })
		}else{
				var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
				let mstKey = masterData.estCode;
				$.getJSON( $.getContextPath() + "/sales/EstimateProdRetrieve.do?estCode=" + mstKey, function(data){
					detailProvider.fillJsonData(data, {fillMode: "set"});
				})
			}
	});
	
//PDF 아이콘 클릭 시 함수 호출	
pdfIcon = function(){
		let checkedRows = masterGrid.getCheckedRows();
		if(checkedRows.length != 1){
			toastr.error("한개만 체크해 주세요");
		}else{
			let index = checkedRows[0];
			var data = masterGrid.getValues(index);
			//PDF 변환을 위해 체크한 내역을 데이터로 하여 비동기 방식으로 요청
			$.ajax({
				url : $.getContextPath() + "/sales/pdfDownload.do",
				data : data,
				method : 'post',
				dataType : 'text',
				success : function(resp){
					console.log(resp);
				},
				error: function(xhr, errorResp, error){
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			})
		}
	}	
