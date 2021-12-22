// 이달 1일 ~ 현재 날짜 세팅
	var date = new Date();
	$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEnd").val(new Date().toISOString().substring(0, 10));

	 var fields = [
		    { fieldName: "buyYy", dataType: "text" }
		  , { fieldName: "buyMm", dataType: "text" }
		  , { fieldName: "buyDd", dataType: "text" }		
		  , { fieldName: "purNum", dataType: "text" }		
		  , { fieldName: "buyerName", dataType: "text" }
		  , { fieldName: "deptName", dataType: "text" }
		  , { fieldName: "empName", dataType: "text" }
		];

		var columns = [
		    { name: "buyYy", fieldName: "buyYy", type: "data", width: "60", header: { text: "년" }, "editable" : false}
	      , { name: "buyMm", fieldName: "buyMm", type: "data", width: "30", header: { text: "월" }, "editable" : false }
	      , { name: "buyDd", fieldName: "buyDd", type: "data", width: "30", header: { text: "일" }, "editable" : false}	
	      , { name: "purNum", fieldName: "purNum", type: "data", width: "100", header: { text: "마감번호" }, "editable" : false}	
		  , { name: "buyerName", fieldName: "buyerName", type: "data", width: "150", header: { text: "견적처" }, button:"action"}
		  , { name: "deptName", fieldName: "deptName", type: "data", width: "60", header: { text: "부서" }, button:"action" }
		  , { name: "empName", fieldName: "empName", type: "data", width: "60", header: { text: "사원" }, button:"action"}
		];
		
		var fields1 = [	
			{ fieldName: "icodeName", dataType: "text" }
		  , { fieldName: "purNo", dataType: "text" }
		  , { fieldName: "purNum", dataType: "text" }
	   	  , { fieldName: "rawsCode", dataType: "text" }
		  , { fieldName: "rawsName", dataType: "text" }
		  , { fieldName: "gcommName", dataType: "text" }
		  , { fieldName: "ucommName", dataType: "text" }
		  , { fieldName: "pcrQty", dataType: "number" }
		  , { fieldName: "prcUprice", dataType: "number" }
		  , { fieldName: "prcSp", dataType: "number" }
		  , { fieldName: "prcVat", dataType: "number" }
		  , { fieldName: "prcSumcost", dataType: "number" }
		  , { fieldName: "warName", dataType: "text" }
		  , { fieldName: "projName", dataType: "text" }
		  , { fieldName: "incNum", dataType: "text" }
		]

		var columns1 = [
			  { name: "icodeName", fieldName: "icodeName", type: "data", width: "100", header: { text: "품목계정" }, "editor": {
				"type": "dropdown",
				"dropDownCount" : 7,
				"domainOnly" : true,
				"commitOnSelect" : true,
				"dropDownWhenClick" : true,
				"values": ["상품", "원재료","부재료","제품","반제품","부산품","저장품"],
				"labels": ["1.상품", "2.원재료","3.부재료","4.제품","5.반제품","6.부산품","7.저장품"]
			}, "visible":false  }
			, { name: "purNo", fieldName: "purNo", type: "data", width: "0", header: { text: "순번" },"visible" : false }
			, { name: "purNum", fieldName: "purNum", type: "data", width: "120", header: { text: "매입마감번호" }, "editable" : false }
			, { name: "rawsCode", fieldName: "rawsCode", type: "data", width: "90", header: { text: "품목코드" }, button:"action" }
			, { name: "rawsName", fieldName: "rawsName", type: "data", width: "170", header: { text: "품명" }, "editable" : false, styleName: "left-column"}
			, { name: "gcommName", fieldName: "gcommName", type: "data", width: "100", header: { text: "규격" }, button:"action" , "editable" : false }
			, { name: "ucommName", fieldName: "ucommName", type: "data", width: "90", header: { text: "단위" }, button:"action", "editable" : false  
			, footer: {"styles": {"textAlignment": "far","font": "굴림,12"}, "text": "합계 =>","groupText": "합계 =>"}, "editable" : false, styleName: "right-column"}
			, { name: "pcrQty", fieldName: "pcrQty", type: "number", width: "100", numberFormat: "#,##0", header: { text: "수량" }, footer: {expression : "sum", numberFormat: "#,##0"}, "editable" : false, styleName: "right-column"}
			, { name: "prcUprice", fieldName: "prcUprice", type: "number", width: "100", numberFormat: "#,##0", "editable" : false, styleName: "right-column",header: { text: "단가" }, footer: {expression : "sum", numberFormat: "#,##0"}, "editable" : false, styleName: "right-column"}
			, { name: "prcSp", fieldName: "prcSp", type: "number", width: "100", numberFormat: "#,##0", "editable" : false, styleName: "right-column",header: { text: "공급가액" }, footer: {expression : "sum", numberFormat: "#,##0"}, "editable" : false, styleName: "right-column"}
			, { name: "prcVat", fieldName: "prcVat", type: "number", width: "100", numberFormat: "#,##0", "editable" : false, styleName: "right-column",header: { text: "부가세" }, footer: {expression : "sum", numberFormat: "#,##0"}, "editable" : false , styleName: "right-column"}
			, { name: "prcSumcost", fieldName: "prcSumcost", type: "number", width: "100",numberFormat: "#,##0", "editable" : false, styleName: "right-column", header: { text: "합계금액" }, footer: {expression : "sum", numberFormat: "#,##0"}, "editable" : false , styleName: "right-column"}
			, { name: "warName", fieldName: "warName", type: "number", width: "100", header: { text: "창고" }, footer: {expression : "sum", numberFormat: "#,##0"}, "editable" : false , styleName: "right-column"}
			, { name: "projName", fieldName: "projName", type: "data", width: "100", header: { text: "프로젝트" }, "editable" : false  }
			, { name: "incNum", fieldName: "incNum", type: "data", width: "100", header: { text: "입고번호" }, "editable" : false ,"visible" : false  }
			]

/*
 * 입고처리서적용 *************************************************************************************************************************************************************************************
 */	
	// 입고처리서적용모달열
	$("#IncApplyForPclose").on("click",function(){
		$(function () {
			$("#IncApplyForPclose-modal").css("display", "flex")
		});
	});
	
	//x표 눌러서 모달 닫
	$(".close-area").click(function(){
		$("#IncApplyForPclose-modal").css("display", "none")
	});
//*************
		

  // 1. 각자 메뉴에 맞는 컬럼, 필드 만들기(해당 메뉴에 맞는 컬럼과 필드를 만들어야함, VO와 명칭도 일치해야함)
  // 2. 검색, 수정, 삭제시, 조건 알아서 넣기.
  // 
  // -> alert, confirm 나중에 모달로 변경해야함.
  /********************************* 마스터에 값 넣기 ******************************/
  // 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능
	
//		// 거래처 검색 모달 띄우기
//		$("#buyerName").focus(function(e){
//			$("#buyer-modal").css('display', 'flex')
//		});
//		
//		// 부서 검색 모달 띄우기
//		$("#deptName").focus(function(e){
//		    $("#dept-modal").css('display', 'flex')
//		});
//		
//		// 사원 검색 모달 띄우기
//		$("#empName").focus(function(e){
//			$("#emp-modal").css('display', 'flex')
//		});		
		
		// 목록 조회
		$("#select1").on("click",function(){
	        
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
				toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
				return false;
			}
		});
		
		// 목록 조회
		$("#select2").on("click",function(){
			
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
				toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
				return false;
			}
		});
	
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
	    			var lastDate = new Date(data.buyYy, data.buyMm, 0).getDate();
	    			
	    			if(data.buyYy >= 2023 || data.buyYy <= 2020){
	    				toastr.error("해당 연도 입력 불가");
	    				return false;
	    			}else if(data.buyMm < 1 || data.buyMm > 12){
	    				toastr.error("입력할 수 없는 달입니다");
	    				return false;
	    			}else if(data.buyDd < 1 || data.buyDd > lastDate){
	    				toastr.error("입력한 달의 마지막 일을 확인하세요");
	    				return false;
	    			}
		            dataList.push(data);
		            
//		            if(data.buyDd == null){
//		            	Swal.fire("날짜를 입력하세요");
//		            	return false;
//		            }
//		            var ddData = data.pdcDd;
//		            if(ddData < 10){
//		            	ddData = "0"+ddData;
//		            }
//		            dataList.push(data);
		         } //for문 끝
		        let obj = {"commonList":dataList}
				$.ajax({
					url : $.getContextPath()+"/buy/pCloseInsert.do",
					data : JSON.stringify(obj),
					method : "post",
//					dataType : "text",
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

//		// 추가버튼
//		$("#append").on("click", function(){
//			var date = new Date();
//			var count = masterProvider.getRowStateCount("created");
//		      if(count > 0){
//		    	  Swal.fire("추가된 행을 저장해주세요");
//		      }else{
//		         var values = [];
//		         for(var i = 0 ; i < fields.length ; i++){
//		            values.push = "";
//		         }
//		         
//		         values[0] = date.getFullYear();
//		         values[1] = date.getMonth()+1;
//		         
//		         masterProvider.addRow(values);
//		      }
//		});
	
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
	    					} //else
	    				} //for
	    						$.ajax({
	    							url : $.getContextPath()+"/buy/pCloseDelete.do",
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
	    			toastr.error("체크해주세요");
	    		}
			});
	
  /********************************* 디테일 설정하기 ******************************/
 
	
  function detailGet(masterRow) {
	  detailProvider.clearRows();
	  console.log(masterRow);
	  if (masterRow >= 0) {
	      var mstKey = masterProvider.getValue(masterRow, "purNum");
	     $.ajax({
			url : $.getContextPath()+"/buy/closeRawsList.do",
			data : {"purNum" : mstKey},
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
				var purNum = insertData.purNum;
				
				if(rawscd == null){
					toastr.error("필수항목누락");
					return false;
				}		
				dataList.push(insertData);
			}		
			let obj = {"commonList":dataList}
					$.ajax({
						url : $.getContextPath()+"/buy/pCloseRawsInsert.do",
						data : JSON.stringify(obj),
						method : "post",
						dataType : "text",
						contentType : "application/json",
						success : function(resp) {
							if(resp.result == 'OK'){
								toastr.success(resp.message);
								 $.ajax({
										url : $.getContextPath()+"/buy/closeRawsList.do",
										data : {"purNum" : purNum},
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
								if(Array.isArray(resp.message)){
									for(var idx in resp.message){
										console.log(resp.message)
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
	

//	// 추가버튼
//	$("#append1").on("click", function(){
//		var values = [];
//        for(var i = 0 ; i < fields1.length ; i++){
//           values.push = "";
//        }
//        var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
//        values[1] = masterData.purNum;
//        detailProvider.addRow(values);
//
//        detailGrid.commit();
//		detailGrid.showEditor();
//		detailGrid.setFocus();
//		
//	});
	
	// 삭제버튼
	$("#delete1").on("click", function(){
		var rows;
		var data;
		rows = detailGrid.getCheckedItems();
		
		let checkedRows = detailGrid.getCheckedRows();
		
		console.log("checkedRows 크기 : " + checkedRows.length);
		
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
	    				for(var i = rows.length ; i >= 0 ; i--){
	    					if(detailProvider.getRowState(rows[i]) == "created"){
	    						detailProvider.removeRow(rows[i]);
	    						continue;
	    					}else {
	    						data = detailGrid.getValues(checkedRows[i]);
	    						if(data == null) continue;
	    						dataList.push(data);
	    						
	    						purNum = data.purNum;
	    					} //else
	    				} //for
	    						$.ajax({
	    							url : $.getContextPath()+"/buy/pCloseRawsDelete.do",
	    							data : data,
	    							method : "post",
	    							dataType : "text",
	    							async : false,
	    							success : function(resp) {
	    								if(resp=="삭제되었습니다."){
	    									Swal.fire(
	    											"삭제가 완료되었습니다.",
	    											"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
	    											"success"
	    									);
	    									
	    									$.ajax({
	    										url : $.getContextPath()+"/buy/closeRawsList.do",
												data : {"purNum" : purNum},
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
		    	toastr.error("체크해주세요");
			}
			
		});


//	window.onload = function() {
// 		detailGrid.columnByName("purNum").editable =  false;
//	};
