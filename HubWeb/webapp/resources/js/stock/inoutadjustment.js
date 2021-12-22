/*
 	입출고조정
 	inOutAdjustment.js
	By 이원균_211207(최종수정)
	adjustment.jsp 에 로딩되는 스크립트입니다.
	
	1. Grid 컬럼 설정
	2. 이벤트 스크립트 : 조회/추가/삭제에 따른 resp
	3. 이벤트 처리를 돕기 위한 스크립트	
 */

// ========================================================================================
// 1. Grid 컬럼 설정

  //masterGrid
  var fields = [
	    { fieldName: "stockYy", dataType: "text" }
	  , { fieldName: "stockMm", dataType: "text" }
	  , { fieldName: "stockDd", dataType: "text" }
	  , { fieldName: "adjNum", dataType: "text" }
	  , { fieldName: "adjSort", dataType: "text" }
	  , { fieldName: "deptName", dataType: "text" }
	  , { fieldName: "empName", dataType: "text" }
	  , { fieldName: "buyerCode", dataType: "text" }
	  , { fieldName: "buyerName", dataType: "text" }
//	  , { fieldName: "ucommName", dataType: "text" }
//	  , { fieldName: "gcommName", dataType: "text" }
//	  , { fieldName: "itemCode", dataType: "text" }
//	  , { fieldName: "kcommName", dataType: "text" }
	];
  var columns = [
	    { name: "stockYy", fieldName: "stockYy", type: "data", width: "30", header: { text: "년" } }
	  , { name: "stockMm", fieldName: "stockMm", type: "data", width: "30", header: { text: "월" } }
	  , { name: "stockDd", fieldName: "stockDd", type: "data", width: "30", header: { text: "일" } }
	  , { name: "adjNum", fieldName: "adjNum", type: "data", width: "90", header: { text: "입출고조정번호" } }
	  , { name: "adjSort", fieldName: "adjSort", type: "data", width: "60", header: { text: "유형" }, "editor": {
		    "type": "dropdown",
		    "dropDownCount" : 2,
		    "domainOnly" : true,
		    "commitOnSelect" : true,
		    "dropDownWhenClick" : true,
		    "values": ["입고", "출고"],
		    "labels": ["1.입고", "2.출고"]
		  } }
	  , { name: "deptName", fieldName: "deptName", type: "data", width: "60", header: { text: "부서" }, button:"action" }
	  , { name: "empName", fieldName: "empName", type: "data", width: "90", header: { text: "사원" }, button:"action" }
	  , { name: "buyerCode", fieldName: "buyerCode", type: "data", width: "60", header: { text: "거래처코드" }, button:"action", "visible" : false }
	  , { name: "buyerName", fieldName: "buyerName", type: "data", width: "60", header: { text: "거래처" }, button:"action"}
//	  , { name: "ucommName", fieldName: "ucommName", type: "data", width: "60", header: { text: "단위" }, button:"action"}
//	  , { name: "gcommName", fieldName: "gcommName", type: "data", width: "60", header: { text: "규격" }, button:"action"}
//	  , { name: "itemCode", fieldName: "itemCode", type: "data", width: "60", header: { text: "품목" }, button:"action"}
//	  , { name: "kcommName", fieldName: "kcommName", type: "data", width: "60", header: { text: "공정" }, button:"action"}

	];
	
	//detailGrid
  var fields1 = [
		{ fieldName: "adjNo", dataType: "text" }
	  ,	{ fieldName: "adjNum", dataType: "text" }
	  ,	{ fieldName: "adjSort", dataType: "text" }
   	  , { fieldName: "icodeName", dataType: "text" }
	  , { fieldName: "itemCode", dataType: "text" }
	  , { fieldName: "rawsName", dataType: "text" }
	  , { fieldName: "gcommName", dataType: "text" }
	  , { fieldName: "ucommName", dataType: "text" }
	  , { fieldName: "adjQty", dataType: "number" }
	  , { fieldName: "adjPrice", dataType: "number" }
	  , { fieldName: "adjCost", dataType: "number" }
	  , { fieldName: "test1", dataType: "text" }
	  , { fieldName: "test2", dataType: "text" }
	  , { fieldName: "warName", dataType: "text" }
	  , { fieldName: "projName", dataType: "text" }
//	  , { fieldName: "kcommName", dataType: "text" }
//	  , { fieldName: "ldivName", dataType: "text" }
//	  , { fieldName: "mdivName", dataType: "text" }
//	  , { fieldName: "sdivName", dataType: "text" }
	]
  var columns1 = [
		{ name: "adjNo", fieldName: "adjNo", type: "data", width: "90", header: { text: "번호" } , visible:false}
		, { name: "adjNum", fieldName: "adjNum", type: "data", width: "90", header: { text: "입출고조정번호" } , visible:false}
		, { name: "adjSort", fieldName: "adjSort", type: "data", width: "90", header: { text: "유형" } , visible:false}
		, { name: "icodeName", fieldName: "icodeName", type: "data", width: "100", header: { text: "품목계정" }, "editor": {
		    "type": "dropdown",
		    "dropDownCount" : 7,
		    "domainOnly" : true,
		    "commitOnSelect" : true,
		    "dropDownWhenClick" : true,
		    
		    "values": ["상품", "원재료","부재료","제품","반제품","부산품","저장품"],
		    "labels": ["1.상품", "2.원재료","3.부재료","4.제품","5.반제품","6.부산품","7.저장품"]
		  } }
		, { name: "itemCode", fieldName: "itemCode", type: "data", width: "80", header: { text: "품목코드" }, button:"action"}
		, { name: "rawsName", fieldName: "rawsName", type: "data", width: "250", header: { text: "품명" },styleName : "left-column" }
		, { name: "gcommName", fieldName: "gcommName", type: "data", width: "90", header: { text: "규격" }, button:"action"}
		, { name: "ucommName", fieldName: "ucommName", type: "data", width: "60", header: { text: "단위" }, button:"action"}
		, { name: "adjQty", fieldName: "adjQty", type: "data", width: "120", header: { text: "수량" }, numberFormat: "#,##0" }
		, { name: "adjPrice", fieldName: "adjPrice", type: "data", width: "120", header: { text: "단가" }, numberFormat: "#,##0", "visible" : false }
		, { name: "adjCost", fieldName: "adjCost", type: "data", width: "120", header: { text: "금액" }, numberFormat: "#,##0", "visible" : false }
		, { name: "warName", fieldName: "warName", type: "data", width: "120", header: { text: "창고" }, button:"action"}
		, { name: "projName", fieldName: "projName", type: "data", width: "120", header: { text: "프로젝트" }, button:"action"}
//		, { name: "kcommName", fieldName: "kcommName", type: "data", width: "120", header: { text: "공정" }, button:"action"}
//		  , { name: "ldivName", fieldName: "ldivName", type: "data", width: "60", header: { text: "대분류" }, button:"action"}
//		  , { name: "mdivName", fieldName: "mdivName", type: "data", width: "60", header: { text: "중분류" }, button:"action"}
//		  , { name: "sdivName", fieldName: "sdivName", type: "data", width: "60", header: { text: "소분류" }, button:"action"}		
		]
	

//========================================================================================
//2. 이벤트 스크립트 : 조회/추가/삭제에 따른 resp
	// 목록 조회
	$("#select1").on("click",function(){
		masterGrid.commit()
		masterGrid.columnByName("adjNum").editable =  false;
		masterGrid.footer.visible = false
		var startDate = $("#dateStart").val();
		var endDate = $("#dateEnd").val();
		
		// 날짜 지정하지 않았을 때
		if(startDate.replace(/\s/g,"").length==0){
			toastr.error("날짜를 입력하세요.");
			return false;
		}
		
		if(endDate >= startDate){
			let searchForm = $("#searchForm").ajaxForm({
				url : $.getContextPath() + "/stock/inOutAdjustmentRetrieve.do",
				method : "post",
				dataType : "json",
				success : function(resp) {
					masterProvider.fillJsonData(resp, {fillMode: "set"});
				}
			}).submit();
		}else{
			toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
			return false;
		}
		
		masterGrid.columnByName("buyerName").editable =  false;
		masterGrid.columnByName("deptName").editable =  false;
		masterGrid.columnByName("empName").editable =  false;
	});
	// 목록 조회
	$("#select2").on("click",function(){
		masterGrid.commit()
		masterGrid.columnByName("adjNum").editable =  false;
		var startDate = $("#dateStart").val();
		var endDate = $("#dateEnd").val();
		
		// 날짜 지정하지 않았을 때
		if(startDate.replace(/\s/g,"").length==0){
			toastr.error("날짜를 입력하세요.");
			return false;
		}
		
		if(endDate >= startDate){
			let searchForm = $("#searchForm").ajaxForm({
				url : $.getContextPath() + "/stock/inOutAdjustmentRetrieve.do",
				method : "post",
				dataType : "json",
				success : function(resp) {
					masterProvider.fillJsonData(resp, {fillMode: "set"});
				}
			}).submit();
		}else{
			toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
			return false;
		}
		
		masterGrid.columnByName("buyerName").editable =  false;
		masterGrid.columnByName("deptName").editable =  false;
		masterGrid.columnByName("empName").editable =  false;
	});
	
	
	stDate = $("#dateStart").val();
	enDate = $("#dateEnd").val();

	// 저장버튼을 사용하기 위한 설정
	// 백단에서 기본키가 되는 code가 없으면 insert, 있으면 update(등록+수정)
	
	$("#saved").on("click", function(){
		 masterGrid.commit();
	     let checkedRows = masterGrid.getCheckedRows();
	     if(checkedRows.length > 0){
	    	 masterProvider.setRowStates(checkedRows, "none", true);
	    	 var dataList = [];	// 체크된 행 데이터 넣을 배열
	         for(var checked = 0 ;  checked < checkedRows.length ; checked++){
	            data = masterGrid.getValues(checkedRows[checked]);
				let adjSort = data.adjSort;
				if(adjSort == null || adjSort == ""){
					toastr.error("유형을 입력하세요");
					return false;
				}
				let stockYy = data.stockYy;
				let stockMm = data.stockMm;
				let stockDd = data.stockDd;
	            if(stockYy == null || stockMm == null|| stockDd == null){
	            	toastr.error("날짜를 입력하세요");
	            	return false;
	            }
	            dataList.push(data)
	         }
	         let obj = {"commonList":dataList}
				$.ajax({
					url : $.getContextPath()+ "/stock/inOutAdjustmentInsert.do",
					data : JSON.stringify(obj),
					method : "post",
					dataType : "json",
					contentType : "application/json",
					success : function(resp) {
						if(resp.result == 'OK'){
							toastr.success(resp.message);
						}else{
							for(var idx in resp.message){
								console.log(resp.message)
								toastr.error(resp.message[idx]);
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
			masterProvider.setRowStates(checkedRows, "none", true);
			for(var checked = 0 ;  checked < checkedRows.length ; checked++){
				data = masterGrid.getValues(checkedRows[checked]);
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
		    						data = masterGrid.getValues(checkedRows[i]);
		    						if(data == null) continue;
		    			            dataList.push(data);
		    					}	// else
		    				}	// for
	                    	let obj = {"commonList":dataList}
	    					$.ajax({
	    						url : $.getContextPath()+"/stock/inOutAdjustmentDelete.do",
	    						data : JSON.stringify(obj),
	    						method : "post",
	    						dataType : "json",
	    						contentType : "application/json",
	    						success : function(resp) {
	    							if(resp.result == 'OK'){
	    								toastr.success(
	    										"삭제가 완료되었습니다.",
	    										"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
	    										"success"
	    								);
	    								for(var i = rows.length-1 ; i >= 0 ; i--){
											masterProvider.removeRow(rows[i]);
										}
	    							}else{
	    								for(var idx in resp.message){
	    									console.log(resp.message)
	    									toastr.error(resp.message[idx]);
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
	                });
					

				}
			}else{
				toastr.error("체크하고 삭제해주세요");
			}
		});
	
  /********************************* 디테일 설정하기 ******************************/
 
	function detailGet(masterRow) {
		  detailProvider.clearRows();
		  console.log(masterRow);
		  if (masterRow >= 0) {
		      var mstKey = masterProvider.getValue(masterRow, "adjNum");
		     $.ajax({
				url : $.getContextPath()+"/stock/inOutItemList.do",
				data : {"adjNum" : mstKey},
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
		  
	        detailGrid.columnByName("itemCode").editable =  false;
	        detailGrid.columnByName("rawsName").editable =  false;
	        detailGrid.columnByName("gcommName").editable =  false;
	        detailGrid.columnByName("ucommName").editable =  false;
	        detailGrid.columnByName("projName").editable =  false;
	        detailGrid.columnByName("warName").editable =  false;
	  };
	  
	// 입출고내역-품목 저장
	$("#saved1").on("click", function(){
		detailGrid.commit();
		
		let checkedRows = detailGrid.getCheckedRows();
		if(checkedRows.length > 0){
			detailProvider.setRowStates(checkedRows, "none", true);
			var dataList = [];
			for(var checked = 0 ;  checked < checkedRows.length ; checked++){
				data = checkedRows[checked];
				
				var insertData = detailGrid.getValues(data);

				var adjNum = insertData.adjNum;
				
				//클라이언트검증----------------------------
				var rawsName = insertData.rawsName;
				if(rawsName == null){
					toastr.error("품명이 누락되었습니다");
					return false;
				}			
				let warName = insertData.warName;
				if(warName == null){
					toastr.error("창고명이 누락되었습니다");
					return false;
				}	
				let icodeName = insertData.icodeName;
				if(icodeName == null){
					toastr.error("품목계정이 누락되었습니다");
					return false;
				}	
				let itemCode = insertData.itemCode;
				if(itemCode == null){
					toastr.error("품목코드가 누락되었습니다");
					return false;
				}	
				let adjQty = insertData.adjQty;		//수량 이름은 각자 찾아써야합니다.
				if(adjQty == null){
					toastr.error("수량이 누락되었습니다");
					return false;
				}	
				//---------------------------------------
				dataList.push(insertData);
			}
			let obj = {"commonList":dataList}
					$.ajax({
						url : $.getContextPath()+"/stock/inOutItemInsert.do",
						data : JSON.stringify(obj),
						method : "post",
						dataType : "json",
						contentType : "application/json",
						success : function(resp) {
							if(resp.result == 'OK'){
								toastr.success(resp.message);
							     $.ajax({
										url : $.getContextPath()+"/stock/inOutItemList.do",
										data : {"adjNum" : adjNum},
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
								for(var idx in resp.message){
									console.log(resp.message)
									toastr.error(resp.message[idx]);
								}
							}
						},
						error : function(xhr, errorResp, error) {
							console.log(xhr);
							console.log(errorResp);
							console.log(error);
				}
			}) 
		}else{
			toastr.error("체크해주세요");
		}
	});
	

	// 생산지시서-원자재 추가
	$("#append1").on("click", function(){
		var values = [];
        for(var i = 0 ; i < fields1.length ; i++){
           values.push = "";
        }
        var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
        values[1] = masterData.adjNum;
        values[2] = masterData.adjSort;
        detailProvider.addRow(values);
		
		detailGrid.commit();
		detailGrid.showEditor();
		detailGrid.setFocus();
		
		
	});
	
	// 삭제버튼
	$("#delete1").on("click", function(){
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
	    					}	// else
	    				}	// for
                    	let obj = {"commonList":dataList}
	    					$.ajax({
	    						url : $.getContextPath()+"/stock/inOutItemDelete.do",
	    						data : JSON.stringify(obj),
	    						method : "post",
	    						dataType : "json",
	    						contentType : "application/json",
	    						success : function(resp) {
	    							if(resp.result == 'OK'){
	    								detailProvider.removeRows(checkedRows);
	    								toastr.success(
	    										"삭제가 완료되었습니다.",
	    										"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
	    										"success"
	    								);
	    							}else{
	    								for(var idx in resp.message){
	    									console.log(resp.message)
	    									toastr.error(resp.message[idx]);
	    								}
	    							}
	    						},
	    						error : function(xhr, errorResp, error) {
	    							console.log(xhr);
	    							console.log(errorResp);
	    							console.log(error);
	    						}
	    					});
                    	}//for문끝
                    });
                };
		}else{
			toastr.error("체크해주세요");
		}
	});

	
//========================================================================================
//3. 이벤트 처리를 돕기 위한 스크립트	
	var date = new Date();
	$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEnd").val(new Date().toISOString().substring(0, 10));
	
	
	
	
	
	