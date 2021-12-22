/**
 * 재고평가 조회
 * 1. 첫 화면 : 검색조건 + 비어있는 그리드
 * 2. 날짜 입력 후 조회 클릭 시 -> 재고평가 리스트 출력하는 모달 창
 * 3. 모달창에서 선택 시 -> 마스터그리드 출력(재고평가 결과 조회)
 * 
 * 재고평가(insert)
 * 1. 평가 클릭 -> 정보 입력 모달창 -> 확인 시 insert(재고평가 table) 
 *  -> 재고평가의 날짜 데이터를 쿼리에 넣어 데이터 조회 및 insert
 */
	var date = new Date();
	$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEnd").val(new Date().toISOString().substring(0, 10));
  var fields = [
	    { fieldName: "icodeName", dataType: "text" }
	  , { fieldName: "itemCode", dataType: "text" }
	  , { fieldName: "prodName", dataType: "text" }
	  , { fieldName: "gcommName", dataType: "text" }
	  , { fieldName: "ucommName", dataType: "text" }
	  , { fieldName: "prodBaseqty", dataType: "number" }
	  , { fieldName: "prodBaseucost", dataType: "number" }
	  , { fieldName: "prodBasecost", dataType: "number" }
	  , { fieldName: "sviInqty", dataType: "number" }
	  , { fieldName: "sviIncost", dataType: "number" }
	  , { fieldName: "sviOutqty", dataType: "number" }
	  , { fieldName: "sviOutcost", dataType: "number" }
	  , { fieldName: "prodInvqty", dataType: "number" }
	  , { fieldName: "prodInvucost", dataType: "number" }
	  , { fieldName: "prodInvcost", dataType: "number" }
	  ];
  
  var columns = [
	    { name: "icodeName", fieldName: "icodeName", type: "data", width: "20", header: { text: "품목계정" } }
	  , { name: "itemCode", fieldName: "itemCode", type: "data", width: "50", header: { text: "품목" } }
	  , { name: "prodName", fieldName: "prodName", type: "data", width: "150", header: { text: "품목명" }, styleName : "left-column"}
	  , { name: "gcommName", fieldName: "gcommName", type: "data", width: "60", header: { text: "규격" } }
	  , { name: "ucommName", fieldName: "ucommName", type: "data", width: "40", header: { text: "재고단위" }}
	  , { name: "prodBaseqty", fieldName: "prodBaseqty", type: "data", width: "20", header: { text: "기초수량" }
	  		,footer: {expression: "sum", numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0" }
	  , { name: "prodBaseucost", fieldName: "prodBaseucost", type: "data", width: "60", header: { text: "기초단가" }
	  		,footer: {expression: "sum", numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0", "visible" : false }
	  , { name: "prodBasecost", fieldName: "prodBasecost", type: "data", width: "60", header: { text: "기초금액" }
	  		,footer: {expression: "sum", numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0", "visible" : false }
	  , { name: "sviInqty", fieldName: "sviInqty", type: "data", width: "20", header: { text: "입고수량" }
	  		,footer: {expression: "sum", numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0" }
	  , { name: "sviIncost", fieldName: "sviIncost", type: "data", width: "60", header: { text: "입고금액" }
	  		,footer: {expression: "sum", numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0" }
	  , { name: "sviOutqty", fieldName: "sviOutqty", type: "data", width: "20", header: { text: "출고수량" }
	  		,footer: {expression: "sum", numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0" }
	  , { name: "sviOutcost", fieldName: "sviOutcost", type: "data", width: "60", header: { text: "출고금액" }
	  		,footer: {expression: "sum", numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0" }
	  , { name: "prodInvqty", fieldName: "prodInvqty", type: "data", width: "20", header: { text: "재고수량" }
	  		,footer: {expression: "sum", numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0" }
	  , { name: "prodInvucost", fieldName: "prodInvucost", type: "data", width: "60", header: { text: "재고단가" }
	  		,footer: {expression: "sum", numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0" }
	  , { name: "prodInvcost", fieldName: "prodInvcost", type: "data", width: "60", header: { text: "재고금액" }
	  		,footer: {expression: "sum", numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0" }
	  ];
  
  
  
  // 목록 조회
$("#select").on("click", function() {
	var startDate = $("#dateStart").val();
	var endDate = $("#dateEnd").val();
	
	// 날짜 지정하지 않았을 때
	if(startDate.replace(/\s/g,"").length==0){
		toastr.error("날짜를 선택하세요");
		return false;
	}
	
	if(endDate >= startDate){
		let searchForm = $("#searchForm").ajaxForm({
			url : $.getContextPath() + "/stock/inventoryValuationRetrieveList.do",
			method : "post",
			dataType : "json",
			success : function(resp) {
				$("#stockValuation-modal").css('display', 'flex')
				var startDate = $("#dateStart").val();
				var endDate = $("#dateEnd").val();
				$("#dateStartValuModal").val(startDate);
				$("#dateEndValuModal").val(endDate);
				console.log(resp)
				stockValProvider.fillJsonData(resp, {fillMode: "set"});
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		
		}).submit();
	}else{
		toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
});

//모달창에서 기간 넣어 조회하기
$("#searchValuModal").on("click", function() {
	if(dateEndValuModal >= dateStartValuModal){
		let searchForm = $("#searchFormModal").ajaxForm({
			url : $.getContextPath() + "/stock/inventoryValuationRetrieveList.do",
			method : "post",
			dataType : "json",
			success : function(resp) {
				console.log(resp)
				stockValProvider.fillJsonData(resp, {fillMode: "set"});
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		
		}).submit();
	}else{
		toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
});


//재고평가 등록
//insertStockVal 버튼 클릭시 stockValInsertForm 정보 insert
//stock/inventoryValuationInsert.do << insert 하기
$("#insertStockVal").on("click", function() {
	$("#stockValInsert-modal").css('display', 'flex')
	var insertDateStart = $("#insertDateStart").val();
	var insertDateEnd = $("#insertDateEnd").val();
	
	if(insertDateEnd.replace(/\s/g,"").length==0){
		toastr.error("날짜를 선택하세요");
		var date = new Date();
		$("#insertDateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
		$("#insertDateEnd").val(new Date().toISOString().substring(0, 10));
		return false;
	}
});
$("#insertStockValForm").on("click", function() {
	if(insertDateEnd >= insertDateEnd){
		let stockValInsertForm = $("#stockValInsertForm").ajaxForm({
			url : $.getContextPath() + "/stock/inventoryValuationInsert.do",
			method : "post",
			dataType : "json",
			success : function(resp) {
				console.log(resp)
				let resultMessage = resp.resultMessage;
				//재고평가서의 날짜 데이터를 쿼리에 넣어 조회한다
				$("#stockValInsert-modal").css('display', 'none')
				if(resp.result == 'OK'){
					toastr.success('재고평가가 완료되었습니다')
//					$.ajax({
//						url : $.getContextPath()+ "/stock/inventoryValuationItemRetrieve.do",
//						data : {"stvNum" : stvNum},
//						method : "post",
//						dataType : "json",
//						success : function(resp) {
//							//재고평가서-품목 정보를 끌어와 데이터를 넣어준다. 마스터그리드에 결과 적용
//							$("#stvNum").val(stvNum);
//						    masterProvider.fillJsonData(resp.valuationList, {fillMode: "set"});
//							//모달창 끄기
//						    $("#stockValuation-modal").css('display', 'none')
//						},
//						error : function(xhr, errorResp, error) {
//							console.log(xhr);
//							console.log(errorResp);
//							console.log(error);
//						}
//					});
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
		
		}).submit();
	}else{
		toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
});
  
  
  
$("#delete").on("click", function() {
	var stvNum = $("#stvNum").val()
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
			$.ajax({
				url : $.getContextPath()+"/stock/inventoryValuationDelete.do",
				data : {"stvNum" : stvNum},
				method : "post",
				dataType : "json",
				success : function(resp) {
					if(resp.result == 'OK'){
						toastr.success(
								"삭제가 완료되었습니다.",
								"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
								"success"
						);
						masterProvider.clearRows();
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
	
})
  
  
  
  
  