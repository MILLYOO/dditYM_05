/*
 	재고이동
 	stockmove.js
	By 이원균_211207(최종수정)
	stockmove.jsp 에 로딩되는 스크립트입니다.
	
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
	  , { fieldName: "stmNum", dataType: "text" }
	  , { fieldName: "outwarName", dataType: "text" }
	  , { fieldName: "inwarName", dataType: "text" }
	  , { fieldName: "deptName", dataType: "text" }
	  , { fieldName: "empName", dataType: "text" }
	  , { fieldName: "stmWhy", dataType: "text" }
	];
 var columns = [
	    { name: "stockYy", fieldName: "stockYy", type: "data", width: "30", header: { text: "년" } }
	  , { name: "stockMm", fieldName: "stockMm", type: "data", width: "30", header: { text: "월" } }
	  , { name: "stockDd", fieldName: "stockDd", type: "data", width: "30", header: { text: "일" } }
	  , { name: "stmNum", fieldName: "stmNum", type: "data", width: "90", header: { text: "재고이동번호" } }
	  , { name: "outwarName", fieldName: "outwarName", type: "data", width: "90", header: { text: "출고창고" }, button:"action" }
	  , { name: "inwarName", fieldName: "inwarName", type: "data", width: "90", header: { text: "입고창고" }, button:"action" }
	  , { name: "deptName", fieldName: "deptName", type: "data", width: "90", header: { text: "부서" }, button:"action" }
	  , { name: "empName", fieldName: "empName", type: "data", width: "90", header: { text: "사원" }, button:"action" }
	  , { name: "stmWhy", fieldName: "stmWhy", type: "data", width: "90", header: { text: "재고이동사유" } }
	];
 
//detailGrid 
 var fields1 = [
		{ fieldName: "stmNo", dataType: "text" }
	  ,	{ fieldName: "stmNum", dataType: "text" }
	  ,	{ fieldName: "outwarName", dataType: "text" }
	  ,	{ fieldName: "inwarName", dataType: "text" }
	  , { fieldName: "icodeName", dataType: "text" }
	  , { fieldName: "itemCode", dataType: "text" }
	  , { fieldName: "rawsName", dataType: "text" }
	  , { fieldName: "gcommName", dataType: "text" }
	  , { fieldName: "ucommName", dataType: "text" }
	  , { fieldName: "siQty", dataType: "number" }
	]
var columns1 = [
	  { name: "stmNo", fieldName: "stmNo", type: "data", width: "90", header: { text: "번호" }, "visible" : false }
	, { name: "stmNum", fieldName: "stmNum", type: "data", width: "90", header: { text: "재고이동번호" }, "visible" : false }
	, { name: "outwarName", fieldName: "outwarName", type: "data", width: "90", header: { text: "출고창고" }, "visible" : false }
	, { name: "inwarName", fieldName: "inwarName", type: "data", width: "90", header: { text: "입고창고" }, "visible" : false }
	, { name: "icodeName", fieldName: "icodeName", type: "data", width: "100", header: { text: "품목계정" }, "editor": {
	    "type": "dropdown",
	    "dropDownCount" : 7,
	    "domainOnly" : true,
	    "commitOnSelect" : true,
	    "dropDownWhenClick" : true,
	    "values": ["상품", "원재료","부재료","제품","반제품","부산품","저장품"],
	    "labels": ["1.상품", "2.원재료","3.부재료","4.제품","5.반제품","6.부산품","7.저장품"]
	  } }
	, { name: "itemCode", fieldName: "itemCode", type: "data", width: "60", header: { text: "품목코드" }, button:"action"}
	, { name: "rawsName", fieldName: "rawsName", type: "data", width: "300", header: { text: "품명" } }
	, { name: "gcommName", fieldName: "gcommName", type: "data", width: "60", header: { text: "규격" }, button:"action"}
	, { name: "ucommName", fieldName: "ucommName", type: "data", width: "60", header: { text: "단위" }, button:"action"}
	, { name: "siQty", fieldName: "siQty", type: "data", width: "120", header: { text: "이동수량" }, numberFormat: "#,##0" }
	]
 
	
//========================================================================================
//2. 이벤트 스크립트 : 조회/추가/삭제에 따른 resp
 
// 목록 조회
$("#select1").on("click", function() {
	masterGrid.footer.visible = false
	masterGrid.columnByName("stmNum").editable = false;
	var startDate = $("#dateStart").val();
	var endDate = $("#dateEnd").val();

	// 날짜 지정하지 않았을 때
	if (startDate.replace(/\s/g, "").length == 0) {
		toastr.error("날짜를 입력하세요.");
		return false;
	}

	if (endDate >= startDate) {
		let searchForm = $("#searchForm").ajaxForm({
			url : $.getContextPath() + "/stock/stockMoveRetrieve.do",
			method : "post",
			dataType : "json",
			success : function(resp) {
				masterProvider.fillJsonData(resp, {fillMode : "set"});
			}
		}).submit();
	} else {
		toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
});
// 목록 조회
 $("#select2").on("click", function() {
	 masterGrid.columnByName("stmNum").editable = false;
	 var startDate = $("#dateStart").val();
	 var endDate = $("#dateEnd").val();
	 
	 // 날짜 지정하지 않았을 때
	 if (startDate.replace(/\s/g, "").length == 0) {
		 toastr.error("날짜를 입력하세요.");
		 return false;
	 }
	 
	 if (endDate >= startDate) {
		 let searchForm = $("#searchForm").ajaxForm({
			 url : $.getContextPath() + "/stock/stockMoveRetrieve.do",
			 method : "post",
			 dataType : "json",
			 success : function(resp) {
				 masterProvider.fillJsonData(resp, {fillMode : "set"});
			 }
		 }).submit();
	 } else {
		 toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		 return false;
	 }
 });

stDate = $("#dateStart").val();
enDate = $("#dateEnd").val();
 
//저장버튼을 사용하기 위한 설정
// 백단에서 기본키가 되는 code가 없으면 insert, 있으면 update(등록+수정)
$("#saved").on("click", function(){
	  masterGrid.commit();
      let checkedRows = masterGrid.getCheckedRows();
      if(checkedRows.length > 0){
      masterProvider.setRowStates(checkedRows, "none", true);
      
      var dataList = [];	// 체크된 행 데이터 넣을 배열
      
         for(var checked = 0 ;  checked < checkedRows.length ; checked++){
            data = masterGrid.getValues(checkedRows[checked]);
            //클라이언트검증--------------------
            if(data.stockYy == null || data.stockMm == null|| data.stockDd == null){
            	toastr.error("날짜를 입력하세요");
            	return false;
            }
            if(data.outwarName == null){
            	toastr.error("출고창고를 입력하세요");
            	return false;
            }
            if(data.inwarName == null){
            	toastr.error("입고창고를 입력하세요");
            	return false;
            }
            //---------------------------------
            dataList.push(data)
         }
         let obj = {"commonList":dataList}
			$.ajax({
				url : $.getContextPath()+ "/stock/stockMoveInsert.do",
				data : JSON.stringify(obj),
				method : "post",
				dataType : "json",
				contentType : "application/json",
				success : function(resp) {
					console.log(resp)
					$("#dateStart").val(stDate);
					$("#dateEnd").val(enDate);
					if(resp.result == 'OK'){
						toastr.success("저장되었습니다");
						let searchForm = $("#searchForm").ajaxForm({
							dataType : "json",
							success : function(resp) {
								masterProvider.fillJsonData(resp, {fillMode: "set"});
							}
						}).submit();
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
	}else{
		toastr.error("체크해주세요");
	}
});

// 추가버튼
$("#append").on("click", function(){
	masterGrid.commit();
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
    						url : $.getContextPath()+"/stock/stockMoveDelete.do",
    						data : JSON.stringify(obj),
    						method : "post",
    						dataType : "json",
    						contentType : "application/json",
    						success : function(resp) {
    							if(resp.result == 'OK'){
    								toastr.success("삭제가 완료되었습니다.");
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
	      var mstKey = masterProvider.getValue(masterRow, "stmNum");
	     $.ajax({
			url : $.getContextPath()+"/stock/stockMoveRetrieveItem.do",
			data : {"stmNum" : mstKey},
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
  };
 

  //입출고내역-품목 저장
	$("#saved1").on("click", function(){
		detailGrid.commit();
		let checkedRows = detailGrid.getCheckedRows();
		if(checkedRows.length > 0){
			detailProvider.setRowStates(checkedRows, "none", true);
			var dataList = [];
			for(var checked = 0 ;  checked < checkedRows.length ; checked++){
				data = checkedRows[checked];
				
				var insertData = detailGrid.getValues(data);

				var stmNum = insertData.stmNum;
				//클라이언트검증----------------------------
				var rawsName = insertData.rawsName;
				if(rawsName == null){
					toastr.error("품명이 누락되었습니다");
					return false;
				}			
				let siQty = insertData.siQty;
				if(siQty == null){
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
				//---------------------------------------	
				dataList.push(insertData);
			}
			let obj = {"commonList":dataList}
					$.ajax({
						url : $.getContextPath()+"/stock/stockMoveItemInsert.do",
						data : JSON.stringify(obj),
						method : "post",
						dataType : "json",
						contentType : "application/json",
						success : function(resp) {
							if(resp.result == 'OK'){
								toastr.success('저장되었습니다');
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
	
  
	$("#append1").on("click", function(){
		detailGrid.commit();
		var values = [];
      for(var i = 0 ; i < fields1.length ; i++){
         values.push = "";
      }
      var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
      values[1] = masterData.stmNum;
      values[2] = masterData.outwarName;
      values[3] = masterData.inwarName;
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
	    					}	// else
	    				}	// for
                  	let obj = {"commonList":dataList}
	    					$.ajax({
	    						url : $.getContextPath()+"/stock/stockMoveItemDelete.do",
	    						data : JSON.stringify(obj),
	    						method : "post",
	    						dataType : "json",
	    						contentType : "application/json",
	    						success : function(resp) {
	    							console.log(resp)
	    							if(resp.result == 'OK'){
	    								toastr.success("삭제가 완료되었습니다.");
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
                  	}//for문끝
                  });
              };
		}else{
			toastr.error("체크해주세요");
		}
	}); 
 
 
//========================================================================================
//3. 이벤트 처리를 돕기 위한 스크립트
	
//이달 1일 ~ 현재 날짜 세팅
var date = new Date();
$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
$("#dateEnd").val(new Date().toISOString().substring(0, 10));
 
window.onload = function() {
	masterGrid.columnByName("outwarName").editable = false;
	masterGrid.columnByName("inwarName").editable = false;
	masterGrid.columnByName("deptName").editable = false;
	masterGrid.columnByName("empName").editable = false;
}; 
 