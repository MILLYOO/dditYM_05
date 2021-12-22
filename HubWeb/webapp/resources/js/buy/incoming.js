/**
 * 
 */
	// 이달 1일 ~ 현재 날짜 세팅
	var date = new Date();
	$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEnd").val(new Date().toISOString().substring(0, 10));

 var fields = [
	    { fieldName: "buyYy", dataType: "number" }
	  , { fieldName: "buyMm", dataType: "number" }
	  , { fieldName: "buyDd", dataType: "number" }		
	  , { fieldName: "incNum", dataType: "text" }
	  , { fieldName: "buyerCode", dataType: "text" }
	  , { fieldName: "buyerName", dataType: "text" }
	  , { fieldName: "deptName", dataType: "text" }
	  , { fieldName: "empName", dataType: "text" }
	  , { fieldName: "incVat", dataType: "text" }
	  , { fieldName: "incResult", dataType: "text" }
	  , { fieldName: "incFinish", dataType: "text" }
	];

	var columns = [
	    { name: "buyYy", fieldName: "buyYy", type: "data", width: "60", numberFormat: "###0", header: { text: "년" } }
      , { name: "buyMm", fieldName: "buyMm", type: "data", width: "30", numberFormat: "###0", header: { text: "월" } }
      , { name: "buyDd", fieldName: "buyDd", type: "data", width: "30", numberFormat: "###0", header: { text: "일" } }	
	  , { name: "incNum", fieldName: "incNum", type: "data", width: "100", header: { text: "입고처리번호" }, "editable" : false}
	  , { name: "buyerCode", fieldName: "buyerCode", type: "data", width: "150", header: { text: "견적처코드도움" }, button:"action" , "editable" : false, "visible":false }
	  , { name: "buyerName", fieldName: "buyerName", type: "data", width: "150", header: { text: "견적처" }, button:"action" , "editable" : false }
	  , { name: "deptName", fieldName: "deptName", type: "data", width: "60", header: { text: "부서" }, button:"action" , "editable" : false}
	  , { name: "empName", fieldName: "empName", type: "data", width: "60", header: { text: "사원" }, button:"action" , "editable" : false}
	  , { name: "incVat", fieldName: "incVat", type: "data", width: "60", header: { text: "VAT여부" }, "lookupDisplay": true, "visible":false, "editor": {
		  "type": "dropdown",
          "dropDownCount" : 2,
          "domainOnly" : true,
          "commitOnSelect" : true,
          "dropDownWhenClick" : true,
          "textReadOnly" : true
        },
        "values": ["Y", "N"],
        "labels": ["여", "부"] }
	  , { name: "incResult", fieldName: "incResult", type: "data", width: "60", header: { text: "완료여부" }, "visible":false, "lookupDisplay": true, "editor": {
          "type": "dropdown",
          "dropDownCount" : 2,
          "domainOnly" : true,
          "commitOnSelect" : true,
          "dropDownWhenClick" : true,
          "textReadOnly" : true
        },
        "values": ["Y", "N"],
        "labels": ["여", "부"] }
	  , { name: "incFinish", fieldName: "incFinish", type: "data", width: "60", header: { text: "마감여부" }, "visible":false,"lookupDisplay": true, "editor": {
          "type": "dropdown",
          "dropDownCount" : 2,
          "domainOnly" : true,
          "commitOnSelect" : true,
          "dropDownWhenClick" : true,
          "textReadOnly" : true
        },
        "values": ["Y", "N"],
        "labels": ["여", "부"] }
	];
	
	var fields1 = [	
   	    { fieldName: "incNo", dataType: "text"}
   	  , { fieldName: "incNum", dataType: "text" }
   	  , { fieldName: "icodeName", dataType: "text" }
   	  , { fieldName: "rawsCode", dataType: "text" }
	  , { fieldName: "rawsName", dataType: "text" }
	  , { fieldName: "gcommName", dataType: "text" }
	  , { fieldName: "ucommName", dataType: "text" }
	  , { fieldName: "irDate", dataType: "text" }
	  , { fieldName: "irQty", dataType: "number" }
	  , { fieldName: "irUprice", dataType: "number" }
	  , { fieldName: "irSp", dataType: "number" }
	  , { fieldName: "irVat", dataType: "number" }
	  , { fieldName: "irSumcost", dataType: "number" }
	  , { fieldName: "warName", dataType: "text" }
	  , { fieldName: "projName", dataType: "text" }
	]

	var columns1 = [
		  { name: "incNo", fieldName: "incNo", type: "data", width: "0", header: { text: "순번" }, "visible" : false }
		, { name: "incNum", fieldName: "incNum", type: "data", width: "120", header: { text: "입고처리번호" }, "editable" : false , "visible" : false }
		, { name: "icodeName", fieldName: "icodeName", type: "data", width: "100", header: { text: "품목계정" }, "editor": {
	          "type": "dropdown",
	          "dropDownCount" : 7,
	          "domainOnly" : true,
	          "commitOnSelect" : true,
	          "dropDownWhenClick" : true,
	          "lookupDisplay": true,
	          "values": ["원재료","부재료","반제품","부산품","저장품"],
	          "labels": ["1.원재료","2.부재료","3.반제품","4.부산품","5.저장품"],
			  "textReadOnly" : true
	       }, "visible":false  }
		, { name: "rawsCode", fieldName: "rawsCode", type: "data", width: "90", header: { text: "품목코드" }, button:"action", "editable" : false}
		, { name: "rawsName", fieldName: "rawsName", type: "data", width: "170", header: { text: "품명" }, "editable" : false, styleName: "left-column"}
		, { name: "gcommName", fieldName: "gcommName", type: "data", width: "100", header: { text: "규격" }, button:"action" , "editable" : false  }
		, { name: "ucommName", fieldName: "ucommName", type: "data", width: "90", header: { text: "단위" }, button:"action" , "editable" : false  }
		, { name: "irDate", fieldName: "irDate", type: "data", width: "120", header: { text: "납기일" } , editor: {type: "date", datetimeFormat: "yyyy/MM/dd"}, footer: {"styles": {"textAlignment": "far","font": "굴림,12"}, "text": "합계 =>"} }
		, { name: "irQty", fieldName: "irQty", type: "data", width: "100", numberFormat: "#,##0", header: { text: "수량" }, styleName: "right-column", footer: {expression : "sum", numberFormat: "#,##0"}, styleName: "right-column"}
		, { name: "irUprice", fieldName: "irUprice", type: "data", width: "100", numberFormat: "#,##0", header: { text: "단가" }, footer: {expression : "sum", numberFormat: "#,##0"}, styleName: "right-column"  }
		, { name: "irSp", fieldName: "irSp", type: "data", width: "100", header: { text: "공급가액" }, numberFormat: "#,##0",styleName: "right-column" , footer: {expression : "sum", numberFormat: "#,##0"}, styleName: "right-column"  }
		, { name: "irVat", fieldName: "irVat", type: "data", width: "100", header: { text: "부가세" }, numberFormat: "#,##0",styleName: "right-column" ,  footer: {expression : "sum", numberFormat: "#,##0"}, styleName: "right-column"  }
		, { name: "irSumcost", fieldName: "irSumcost", type: "data", width: "100", header: { text: "합계금액" }, numberFormat: "#,##0",styleName: "right-column" ,  footer: {expression : "sum", numberFormat: "#,##0"}, styleName: "right-column"  }
		, { name: "warName", fieldName: "warName", type: "data", width: "100", header: { text: "창고" }, button:"action" }
		, { name: "projName", fieldName: "projName", type: "data", width: "100", header: { text: "프로젝트" }, button:"action" , "editable" : false }
		]


/*
 * 발주서적용 *************************************************************************************************************************************************************************************
 */	
	// 발주서적용모달열
	$("#orderApplyForInc").on("click",function(){
		$(function () {
			$("#orderApplyForInc-modal").css("display", "flex")
		});
	});
	
	//x표 눌러서 모달 닫
	$(".close-area").click(function(){
		$("#orderApplyForInc-modal").css("display", "none")
	});
//**********************************************************************************************************************************************************
	

	
  /********************************* 마스터에 값 넣기 ******************************/
  // 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능
	
	// 목록 조회
	$("#select1").on("click",function(){
		console.log("되는겨..?1")
        
		var startDate = $("#dateStart").val();
		var endDate = $("#dateEnd").val();
		// 날짜 지정하지 않았을 때
		if(startDate.replace(/\s/g,"").length==0){
			toastr.error("날짜를 선택하세요");
			return false;
		}
		
		if(endDate >= startDate){
			let searchForm = $("#searchForm").ajaxForm({
				dataType : "json",
				success : function(resp) {
					masterProvider.fillJsonData(resp, {fillMode: "set"});
				}
			}).submit();
		}else{
//			Swal.fire("종료일이 시작일보다 이전 날짜 입니다.");
			toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
			return false;
		}
	});
	// 목록 조회
	$("#select2").on("click",function(){
		console.log("되는겨..?2")
		var startDate = $("#dateStart").val();
		var endDate = $("#dateEnd").val();
		// 날짜 지정하지 않았을 때
		if(startDate.replace(/\s/g,"").length==0){
			toastr.error("날짜를 선택하세요");
			return false;
		}
		
		if(endDate >= startDate){
			let searchForm = $("#searchForm").ajaxForm({
				dataType : "json",
				success : function(resp) {
					masterProvider.fillJsonData(resp, {fillMode: "set"});
				}
			}).submit();
		}else{
//			Swal.fire("종료일이 시작일보다 이전 날짜 입니다.");
			toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
			return false;
		}
	});

	stDate = $("#dateStart").val();
	enDate = $("#dateEnd").val();
	
	// 저장버튼을 사용하기 위한 설정
	$("#saved").on("click", function(){
		 masterGrid.commit();
	      let checkedRows = masterGrid.getCheckedRows();
	      if(checkedRows.length > 0){
	        masterProvider.setRowStates(checkedRows, "none", true);
	      
	        	var dataList = [];	// 체크된 행 데이터 넣을 배열
	        
	         for(var checked = 0 ;  checked < checkedRows.length ; checked++){
	            data = masterGrid.getValues(checkedRows[checked]);
				
	            var ddData = data.buyDd;
	            if(ddData < 10){
	            	ddData = "0"+ddData;
	            }
	            var incDates = data.buyYy+"/"+data.buyMm+"/"+ddData;
	            if(data.incComplete < incDates){
	            	toastr.error("완료예정일이 입고처리날짜보다 이전 날짜입니다.");
	            	return false;
	            }
	            var lastDate = new Date(data.buyYy, data.buyMm, 0).getDate();
    			
//    			if(data.buyYy >= 2023 || data.buyYy <= 2020){
//    				toastr.error("해당 연도 입력 불가");
//    				return false;
//    			}else if(data.buyMm < 1 || data.buyMm > 12){
//    				toastr.error("입력할 수 없는 달입니다");
//    				return false;
//    			}else if(data.buyDd < 1 || data.buyDd > lastDate){
//    				toastr.error("입력한 달의 마지막 일을 확인하세요");
//    				return false;
//    			}
	            dataList.push(data);
	         } //for문 끝
	         let obj = {"commonList":dataList}
	         	$.ajax({
	         		url : $.getContextPath()+"/buy/incomingInsert.do",
					data : JSON.stringify(obj), //체크된 애들을 여기에 넣어주는 거야
					method : "post",
					dataType : "json",
					contentType : "application/json",
					success : function(resp) {
						if(resp.result == 'OK'){
							toastr.success(resp.message);
							let searchForm = $(".searchForm").ajaxForm({
								dataType : "json",
								success : function(resp) {
									masterProvider.fillJsonData(resp, {fillMode: "set"});
								}
							}).submit();
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
			toastr.error("체크해주세요");
		}
	});
	
	// 추가버튼
	$("#append").on("click", function(){
		var date = new Date();
		var count = masterProvider.getRowStateCount("created");
	      if(count > 0){
//	    	  Swal.fire("추가된 행을 저장해주세요");
	    	  toastr.error("추가된 행을 저장해주세요");
	      }else{
	         var values = [];
	         for(var i = 0 ; i < fields.length ; i++){
	            values.push = "";
	         }
	         
	         values[0] = date.getFullYear();
	         values[1] = date.getMonth()+1;
	         
	         masterProvider.addRow(values);
	      }
	});
	
	// 삭제버튼
	$("#delete").on("click", function(){
		masterGrid.commit();
		var rows;
		var data;
		rows = masterGrid.getCheckedItems();
		let checkedRows = masterGrid.getCheckedRows();
	    if(checkedRows.length > 0){
	    	if(rows){
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
	    				var dataList = [];	// 체크된 행 데이터 넣을 배열
	    				for(var i = rows.length ; i >= 0 ; i--){
	    					if(masterProvider.getRowState(rows[i]) == "created"){
	    						masterProvider.removeRow(rows[i]);
	    						continue;
	    					}else {
	    						data = masterGrid.getValues(rows[i]);
	    						if(data == null) continue;
	    			            dataList.push(data);
	    					}	// else
	    				}	// for
	    						$.ajax({
	    							url : $.getContextPath()+"/buy/incomingDelete.do",
	    							data : JSON.stringify(dataList),
	    							method : "post",
	    							dataType : "text",
	    							contentType : "application/json",
	    							success : function(resp) {
	    								if(resp=="삭제되었습니다."){
//	    									Swal.fire(
//	    											"삭제가 완료되었습니다.",
//	    											"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
//	    											"success"
//	    									);
	    									toastr.success('삭제가 완료되었습니다.')
	    									for(var i = rows.length-1 ; i >= 0 ; i--){
	    										masterProvider.removeRow(rows[i]);
	    									}
	    									detailProvider.clearRows()
	    								}else{
	    									toastr.success('삭제가 완료되었습니다.')
	    								}
	    							},
	    							error : function(xhr, errorResp, error) {
	    								console.log(xhr);
	    								console.log(errorResp);
	    								console.log(error);
	    							}
	    						});
	    	    			}	//if 
	    	    		})	//Swal.fire
	    	    	}
	    	    }else{
//	    			Swal.fire("체크해주세요");
	    	    	toastr.error("체크해주세요");
	    		}
	    	});
  /********************************* 디테일 설정하기 ******************************/
	//detailGrid조회
  function detailGet(masterRow) {
	  detailProvider.clearRows();
	  if (masterRow >= 0) {
	      var mstKey = masterProvider.getValue(masterRow, "incNum");
	     $.ajax({
			url : $.getContextPath()+"/buy/incomingRawsList.do",
			data : {"incNum" : mstKey},
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
	  };
  };

	// 저장버튼을 사용하기 위한 설정
	$("#saved1").on("click", function(){
		detailGrid.commit();
		let checkedRows = detailGrid.getCheckedRows();
		if(checkedRows.length > 0){
			detailProvider.setRowStates(checkedRows, "none", true);
			
			var dataList = [];	// 체크된 행 데이터 넣을 배열
			
			for(var checked = 0 ;  checked < checkedRows.length ; checked++){
				data = checkedRows[checked];
				
				var insertData = detailGrid.getValues(data);
				
				var rawscd = insertData.rawsCode;
				var incNum = insertData.incNum;
				
//				if(rawscd == null){
////					Swal.fire("입력 후 저장 해주세요");
//					toastr.success('입력 후 저장 해주세요')
//					return false;
//				}				
				dataList.push(insertData);
			}
			let obj = {"commonList":dataList}		
				$.ajax({
						url : $.getContextPath()+"/buy/incomingRawsInsert.do",
						data : JSON.stringify(obj),
						method : "post",
						dataType : "json",
						async : false,
						contentType : "application/json",
						success : function(resp) {
							if(resp.result == 'OK'){
								toastr.success(resp.message);
							}else{
								if(Array.isArray(resp.message)){
									for(var idx in resp.message){
										console.log(resp.message)
										toastr.error(resp.message[idx]);
									}
								}else{
									toastr.error(resp.message);
								}
							}	
							let searchForm = $("#searchForm").ajaxForm({
								dataType : "json",
								success : function(resp) {
									masterProvider.fillJsonData(resp, {fillMode: "set"});
								}
							}).submit();
							
						},
						error : function(xhr, errorResp, error) {
							console.log(xhr);
							console.log(errorResp);
							console.log(error);
						}
					});
			}else{
				toastr.error("체크해주세요");
			}
		});
	

	// 추가버튼
	$("#append1").on("click", function(){
		var values = [];
        for(var i = 0 ; i < fields1.length ; i++){
           values.push = "";
        }
        var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
        values[1] = masterData.incNum;
        detailProvider.addRow(values);

        detailGrid.commit();
		detailGrid.showEditor();
		detailGrid.setFocus();
	});
	
	// 삭제버튼
	$("#delete1").on("click", function(){
		detailGrid.commit();
		var rows;
		var data;
		rows = detailGrid.getCheckedItems();
		
		let checkedRows = detailGrid.getCheckedRows();
		
	    if(checkedRows.length > 0){
	    	if(rows){
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
	    				var dataList = [];	// 체크된 행 데이터 넣을 배열
	    				for(var i = rows.length ; i >= 0 ; i--){
	    					if(detailProvider.getRowState(rows[i]) == "created"){
	    						detailProvider.removeRow(rows[i]);
	    						continue;
	    					}else {
	    						data = detailGrid.getValues(checkedRows[i]);
	    						if(data == null) continue;
	    						dataList.push(data);
	    						
	    						incNum = data.incNum;
	    					} //else
	    				} //for
	    						$.ajax({
	    							url : $.getContextPath()+"/buy/incomingRawsDelete.do",
	    							data : JSON.stringify(dataList),
	    							method : "post",
	    							dataType : "text",
	    							contentType : "application/json",
	    							success : function(resp) {
	    								if(resp=="삭제되었습니다."){
//	    									Swal.fire(
//	    											"삭제가 완료되었습니다.",
//	    											"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
//	    											"success"
//	    									);
	    									toastr.success('삭제가 완료되었습니다')
	    									$.ajax({
												url : $.getContextPath()+"/buy/incomingRawsList.do",
												data : {"incNum" : incNum},
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
									}else{
//										Swal.fire(resp);
										toastr.error(resp);
									}
								},
								error : function(xhr, errorResp, error) {
									console.log(xhr);
									console.log(errorResp);
									console.log(error);
								}
							});	//ajax
		    			}	// if
		    		})	// swal
		    	} // if
		    }else{
//				Swal.fire("체크해주세요");
		    	toastr.error("체크해주세요");
			}
			
		});
	
	//조그마한 그냥 빈칸 컬럼 없애는 거
	$(function(){
		masterGrid.stateBar.visible = false;
		detailGrid.stateBar.visible = false;
		masterGrid.footer.visible = false;
	});
