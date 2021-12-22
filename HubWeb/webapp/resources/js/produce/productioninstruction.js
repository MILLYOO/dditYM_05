/**
 * 생산지시서
 */
	// 이달 1일 ~ 현재 날짜 세팅
	var date = new Date();
	$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEnd").val(new Date().toISOString().substring(0, 10));

	var fields = [
	    { fieldName: "pdcYy", dataType: "number" }
	  , { fieldName: "pdcMm", dataType: "number" }
	  , { fieldName: "pdcDd", dataType: "number" }
	  , { fieldName: "instNum", dataType: "text" }
	  , { fieldName: "icodeName", dataType: "text" }
	  , { fieldName: "prodCode", dataType: "text" }
	  , { fieldName: "prodName", dataType: "text" }
	  , { fieldName: "gcommName", dataType: "text" }
	  , { fieldName: "instComplete", dataType: "text" }
	  , { fieldName: "instLeadqty", dataType: "int" }
	  , { fieldName: "deptName", dataType: "text" }
	  , { fieldName: "empName", dataType: "text" }
	  , { fieldName: "kcommName", dataType: "text" }
	  , { fieldName: "instFinish", dataType: "text" }
	  , { fieldName: "orbNum", dataType: "text" }
	];

	var columns = [
	    { name: "pdcYy", fieldName: "pdcYy", type: "data", width: "40", numberFormat: "###0", header: { text: "년" } }
	  , { name: "pdcMm", fieldName: "pdcMm", type: "data", width: "25", numberFormat: "###0", header: { text: "월" } }
	  , { name: "pdcDd", fieldName: "pdcDd", type: "data", width: "25", numberFormat: "###0", header: { text: "일" } }
	  , { name: "instNum", fieldName: "instNum", type: "data", width: "90", header: { text: "생산지시번호" }, "editable" : false }
	  , { name: "icodeName", fieldName: "icodeName", type: "data", width: "60", header: { text: "품목계정" }, "editor": {
          "type": "dropdown",
          "dropDownCount" : 7,
          "domainOnly" : true,
          "commitOnSelect" : true,
          "dropDownWhenClick" : true,
          "lookupDisplay": true,
          "values": ["제품","반제품"],
          "labels": ["제품","반제품"],
          "textReadOnly" : true
        } }
	  , { name: "prodCode", fieldName: "prodCode", type: "data", width: "60", header: { text: "품목코드" } , button:"action" , "editable" : false }
	  , { name: "prodName", fieldName: "prodName", type: "data", width: "280", header: { text: "품명" },"editable" : false, styleName: "left-column"}
	  , { name: "gcommName", fieldName: "gcommName", type: "data", width: "120", header: { text: "규격" } , button:"action" , "editable" : false }
	  , { name: "instComplete", fieldName: "instComplete", type: "data", width: "80", header: { text: "완료예정일" } , editor: {type: "date", datetimeFormat: "yyyy/MM/dd"}, footer: {text: "합계 =>"} }
	  , { name: "instLeadqty", fieldName: "instLeadqty", type: "data", width: "60", numberFormat: "#,##0", header: { text: "지시수량" }, footer: {expression : "sum", numberFormat: "#,##0"}, styleName: "right-column"}
	  , { name: "deptName", fieldName: "deptName", type: "data", width: "60", header: { text: "부서" }, button:"action" , "editable" : false }  
	  , { name: "empName", fieldName: "empName", type: "data", width: "60", header: { text: "사원" }, button:"action" , "editable" : false } 
	  , { name: "kcommName", fieldName: "kcommName", type: "data", width: "80", header: { text: "공정" }, button:"action" , "editable" : false } 
	  , { name: "instFinish", fieldName: "instFinish", type: "data", width: "60", header: { text: "마감여부" }, "lookupDisplay": true, "editor": {
          "type": "dropdown",
          "dropDownCount" : 2,
          "domainOnly" : true,
          "commitOnSelect" : true,
          "dropDownWhenClick" : true,
          "textReadOnly" : true
        },
        "values": ["Y", "N"],
        "labels": ["여", "부"] , "visible" : false }
	  , { name: "orbNum", fieldName: "orbNum", type: "data", width: "93", header: { text: "수주서번호" } , "editable" : false }
	];
	
	var fields1 = [
   	    { fieldName: "instNo", dataType: "text" }
   	  , { fieldName: "instNum", dataType: "text" }
   	  , { fieldName: "icodeName", dataType: "text" }
	  , { fieldName: "rawsCode", dataType: "text" }
	  , { fieldName: "rawsName", dataType: "text" }
	  , { fieldName: "gcommName", dataType: "text" }
	  , { fieldName: "ucommName", dataType: "text" }
	  , { fieldName: "prQty", dataType: "number" }
	]

	var columns1 = [
		  { name: "instNo", fieldName: "instNo", type: "data", width: "100", header: { text: "생산지시서-원자재번호" }, "visible" : false }
		, { name: "instNum", fieldName: "instNum", type: "data", width: "100", header: { text: "생산지시서번호" }, "editable" : false , "visible" : false }
		, { name: "icodeName", fieldName: "icodeName", type: "data", width: "100", header: { text: "품목계정" }, "editor": {
	          "type": "dropdown",
	          "dropDownCount" : 7,
	          "domainOnly" : true,
	          "commitOnSelect" : true,
	          "dropDownWhenClick" : true,
	          "lookupDisplay": true,
	          "values": ["원재료","반제품"],
	          "labels": ["원재료","반제품"],
	          "textReadOnly" : true
	        } }
		, { name: "rawsCode", fieldName: "rawsCode", type: "data", width: "90", header: { text: "자재코드" } , button:"action" , "editable" : false } 
		, { name: "rawsName", fieldName: "rawsName", type: "data", width: "300", header: { text: "투입자재" },"editable" : false, styleName: "left-column"}
		, { name: "gcommName", fieldName: "gcommName", type: "data", width: "160", header: { text: "규격" } , button:"action" , "editable" : false } 
		, { name: "ucommName", fieldName: "ucommName", type: "data", width: "60", header: { text: "단위" } , button:"action" , "editable" : false , footer: {text: "합계 =>"} } 
		, { name: "prQty", fieldName: "prQty", type: "data", width: "120", numberFormat: "#,##0", header: { text: "투입예정수량" }, footer: {expression : "sum", numberFormat: "#,##0" },styleName: "right-column" }
	]
	
	
// ---------------------------------------------수주서적용 --------------------------------------
	
	// 수주서 적용 모달 나와라~!
	$("#orderBookApply").on("click",function(){
		initObApp();
		$(function () {
			$("#orderbookApplyForPI-modal").css("display", "flex");
		});
	});
	
	//x표 눌러서 모달 닫기
	$(".close-area").click(function(){
		$("#orderbookApplyForPI-modal").css("display", "none");
		$("#stockForPI-modal").css("display", "none");
	});
	
// ---------------------------------------------수주서적용 끝--------------------------------------
	
	// 현재고 모달
	$("#selectStockStatus").on("click",function(){
		$(function () {
			$("#stockForPI-modal").css("display", "flex");
		});
	});
	
	// 부서 검색 모달 띄우기
	$("#deptName").focus(function(e){
        $("#dept-modal").css("display", "flex");
        deptObj = this;
	});
	
	// 사원 검색 모달 띄우기
	$("#empName").focus(function(e){
		$("#emp-modal").css("display", "flex");
		empObj = this;
	});
	
	
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
	$("#select3").on("click",function(){
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

	// 생산지시서 저장(등록+수정)
	$("#saved").on("click", function(){
		 masterGrid.commit();
	      let checkedRows = masterGrid.getCheckedRows();
	      if(checkedRows.length > 0){
	    	 masterProvider.setRowStates(checkedRows, "none", true);
	    	 var dataList = [];	// 체크된 행 데이터 넣을 배열
	         for(var checked = 0 ;  checked < checkedRows.length ; checked++){
	            data = masterGrid.getValues(checkedRows[checked]);
	            var ddData = data.pdcDd;
	            if(ddData < 10){
	            	ddData = "0"+ddData;
	            }
	            var instDates = data.pdcYy+"/"+data.pdcMm+"/"+ddData;
	            if(data.instComplete < instDates){
	            	toastr.error("완료예정일이 생산지시일보다 이전 날짜입니다.");
	            	return false;
	            }
            	if(data.icodeName!="제품" && data.icodeName!="반제품"){
            		toastr.error("제품 또는 반제품만 등록 가능합니다.");
            		return false;
            	}
    			var lastDate = new Date(data.pdcYy, data.pdcMm, 0).getDate();
    			
    			if(data.pdcYy >= 2023 || data.pdcYy <= 2020){
    				toastr.error("해당 연도 입력 불가");
    				return false;
    			}else if(data.pdcMm < 1 || data.pdcMm > 12){
    				toastr.error("입력할 수 없는 달입니다");
    				return false;
    			}else if(data.pdcDd < 1 || data.pdcDd > lastDate){
    				toastr.error("입력한 달의 마지막 일을 확인하세요");
    				return false;
    			}
	            dataList.push(data);
	         } //for문 끝
	         let obj = {"commonList":dataList}
			$.ajax({
				url : $.getContextPath()+"/produce/productionInstructionInsert.do",
				data : JSON.stringify(obj),
				method : "post",
				dataType : "json",
				contentType : "application/json",
				success : function(resp) {
					if(resp.result == "OK"){
						toastr.success(resp.message);
						let searchForm = $("#searchForm").ajaxForm({
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

	// 생산지시서 추가
	$("#append").on("click", function(){
		var date = new Date();
		var count = masterProvider.getRowStateCount("created");
	      if(count > 0){
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
	
	// 생산지시서 삭제
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
	    						data = masterGrid.getValues(checkedRows[i]);
	    						if(data == null) continue;
	    			            dataList.push(data);
	    					}	// else
	    				}	// for
						$.ajax({
							url : $.getContextPath()+"/produce/productionInstructionDelete.do",
							data : JSON.stringify(dataList),
							method : "post",
							dataType : "text",
							contentType : "application/json",
							success : function(resp) {
								if(resp=="삭제되었습니다."){
									Swal.fire(
											"삭제가 완료되었습니다.",
											"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
											"success"
									);
									for(var i = rows.length-1 ; i >= 0 ; i--){
										masterProvider.removeRow(rows[i]);
									}
								}else{
									toastr.error(resp);
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
var leadQty;	
 
  // 생산지시서-원자재 목록 조회 (생산지시서 클릭 시)	
  function detailGet(masterRow) {
	  detailProvider.clearRows();
	  if (masterRow >= 0) {
	      var mstKey = masterProvider.getValue(masterRow, "instNum");
	      leadQty = masterProvider.getValue(masterRow, "instLeadqty");
	     $.ajax({
			url : $.getContextPath()+"/produce/instRawsList.do",
			data : {"instNum" : mstKey},
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

	// 생산지시서-원자재 저장
	$("#saved1").on("click", function(){
		detailGrid.commit();
		let checkedRows = detailGrid.getCheckedRows();
		if(checkedRows.length > 0){
			detailProvider.setRowStates(checkedRows, "none", true);
			var dataList = [];	// 체크된 행 데이터 넣을 배열
			for(var checked = 0 ;  checked < checkedRows.length ; checked++){
				data = checkedRows[checked];
				var insertData = detailGrid.getValues(data);
				var qty = insertData.prQty;
				var rawscd = insertData.rawsCode;
				var instNum = insertData.instNum;
				var icdName = insertData.icodeName;
				if(qty < leadQty){
					toastr.error("지시수량에 비해 투입예정수량이 부족합니다");
					return false;
				}
				if(icdName != "원재료" && icdName != "반제품"){
					toastr.error("원재료 및 반제품만 등록 가능합니다.");
					return false;
				}
				dataList.push(insertData);
			}
			let obj = {"commonList":dataList}
			$.ajax({
				url : $.getContextPath()+"/produce/productionRawsInsert.do",
				data : JSON.stringify(obj),
				method : "post",
				dataType : "json",
				contentType : "application/json",
				success : function(resp) {
					if(resp.result == "OK"){
						toastr.success(resp.message);
						$.ajax({
							url : $.getContextPath()+"/produce/instRawsList.do",
							data : {"instNum" : instNum},
							method : "post",
							dataType : "json",
							success : function(resp) {
								detailProvider.fillJsonData(resp, {fillMode: "set"});
							}
						});
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
	
	// 생산지시서-원자재 추가
	$("#append1").on("click", function(){
		var values = [];
        for(var i = 0 ; i < fields1.length ; i++){
           values.push = "";
        }
        var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
        values[1] = masterData.instNum;
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
	    						instNum = data.instNum;
	    					}	// else
	    				}	// for
						$.ajax({
							url : $.getContextPath()+"/produce/productionRawsDelete.do",
							data : JSON.stringify(dataList),
							method : "post",
							dataType : "text",
							contentType : "application/json",
							success : function(resp) {
								if(resp=="삭제되었습니다."){
									Swal.fire(
											"삭제가 완료되었습니다.",
											"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
											"success"
									);
								     $.ajax({
											url : $.getContextPath()+"/produce/instRawsList.do",
											data : {"instNum" : instNum},
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

	// BOM전개
	$("#bomApply").on("click",function(){
		masterGrid.commit();
		let checkedRows = masterGrid.getCheckedRows();
	    if(checkedRows.length > 0){
				Swal.fire({
		            title: "다시 한 번 확인해주세요",
		            text: "해당 품목에 등록된 BOM 1단계 품목을 불러옵니다. 기존에 입력했던 하단의 데이터는 삭제됩니다. 삭제하시겠습니까?",
		            icon: "warning",
		            showCancelButton: true,
		            confirmButtonColor: "#3085d6",
		            cancelButtonColor: "#d33",
		            confirmButtonText: "승인",
		            cancelButtonText: "취소"
		        }).then((result) => {
		        	 if (result.isConfirmed) {
		        		 var dataList = [];	// 체크된 행 데이터 넣을 배열
		        		 for(var checked = 0 ;  checked < checkedRows.length ; checked++){
		        			 var data = checkedRows[checked];
		        			 var checkData = masterGrid.getValues(data);
		        			 var prodCode = checkData.prodCode;
		        			 var instNum = checkData.instNum;
		        			 if(prodCode==null){
		        				 toastr.error("해당 항목은 BOM 전개 대상이 아닙니다.");
		        				 return false;
		        			 }
		        			 dataList.push(checkData);
		        		 }// for문
 						$.ajax({
							url : $.getContextPath()+"/produce/PIBomApply.do",
							data : JSON.stringify(dataList),
							method : "post",
							dataType : "text",
							contentType : "application/json",
							success : function(resp) {
								if(resp =="저장되었습니다."){
									Swal.fire(
											" 완료되었습니다.",
											"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
											"success"
									);
									 $.ajax({
											url : $.getContextPath()+"/produce/instRawsList.do",
											data : {"instNum" : instNum},
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
		        	 }	// confirmed
		        })
	    }else{
	    	toastr.error("체크해주세요");
		}
	});
	
	$(function(){
		masterGrid.stateBar.visible = false;
		detailGrid.stateBar.visible = false;
	});