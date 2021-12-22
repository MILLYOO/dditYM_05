/**
 * 창고별 재고현황
 */

 var fields = [
	    { fieldName: "warCode", dataType: "text" }
	  , { fieldName: "warName", dataType: "text" }
	  , { fieldName: "warSum", dataType: "number" }
	  ]
  
  var columns = [
	   { name: "warCode", fieldName: "warCode", type: "data", width: "60", header: { text: "창고코드" }, "visible" : false  }
	 , { name: "warName", fieldName: "warName", type: "data", width: "125", header: { text: "창고명" } }
	 , { name: "warSum", fieldName: "warSum", type: "data", width: "40", header: { text: "합계" }
	 	, footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0"}
	 ]
 
 
 var fields1 = [
	    { fieldName: "icodeName", dataType: "text" }
	  , { fieldName: "rawsCode", dataType: "text" }
	  , { fieldName: "rawsName", dataType: "text" }
	  , { fieldName: "gcommName", dataType: "text" }
	  , { fieldName: "baseQty", dataType: "number" }
	  , { fieldName: "enterQty", dataType: "number" }
	  , { fieldName: "ucommInname", dataType: "text" }
	  , { fieldName: "outQty", dataType: "number" }
	  , { fieldName: "ucommOutname", dataType: "text" }
	  , { fieldName: "stoQty", dataType: "number" }
	  , { fieldName: "ucommStname", dataType: "number" }
	  , { fieldName: "rawsAdqinv", dataType: "number" }
	  , { fieldName: "retroExcess", dataType: "number" }
	  ]

var columns1 = [
	   { name: "icodeName", fieldName: "icodeName", type: "data", width: "60", header: { text: "품목계정" } }
	 , { name: "rawsCode", fieldName: "rawsCode", type: "data", width: "60", header: { text: "품목코드" } }
	 , { name: "rawsName", fieldName: "rawsName", type: "data", width: "150", header: { text: "품목명" } }
	 , { name: "gcommName", fieldName: "gcommName", type: "data", width: "60", header: { text: "규격" } }
	 , { name: "baseQty", fieldName: "baseQty", type: "data", width: "60", header: { text: "기초수량" }
	 	, footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0"}
	 , { name: "enterQty", fieldName: "enterQty", type: "data", width: "60", header: { text: "입고수량" }
	 	, footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0"}
	 , { name: "ucommInname", fieldName: "ucommInname", type: "data", width: "60", header: { text: "단위" } }
	 , { name: "outQty", fieldName: "outQty", type: "data", width: "60", header: { text: "출고수량" }
	 	, footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0"}
	 , { name: "ucommOutname", fieldName: "ucommOutname", type: "data", width: "60", header: { text: "단위" } }
	 , { name: "stoQty", fieldName: "stoQty", type: "data", width: "60", header: { text: "재고수량" } 
	 	, footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0"}
	 , { name: "ucommStname", fieldName: "ucommStname", type: "data", width: "60", header: { text: "단위" } }
	 , { name: "rawsAdqinv", fieldName: "rawsAdqinv", type: "data", width: "60", header: { text: "적정량" }
	 	, footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0"}
	 , { name: "retroExcess", fieldName: "retroExcess", type: "data", width: "60", header: { text: "과다량" }
	 	, footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column", numberFormat: "#,##0"}
	 ]
 

 	$.ajax({
		url : $.getContextPath() + "/stock/stockStatusByWarehouseRetrieve.do",
		method : "post",
		dataType : "json",
		success : function(resp) {
			masterProvider.fillJsonData(resp, {fillMode: "set"});
		}
	})

	
	 function detailGet(masterRow) {
	  detailProvider.clearRows();
	  console.log(masterRow);
	  if (masterRow >= 0) {
	      var mstKey = masterProvider.getValue(masterRow, "warName");
	      $("input[name=warName]").val(mstKey);
	     $.ajax({
			url : $.getContextPath()+"/stock/stockStatusByWarehouseRetrieveItem.do",
			data : {"warName" : mstKey},
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
 
//목록 조회
$("#select").on("click",function(){
	var startDate = $("#dateStart").val();
	var endDate = $("#dateEnd").val();
	
	if(endDate >= startDate){
		let searchForm = $("#searchForm").ajaxForm({
			url : $.getContextPath()+"/stock/stockStatusByWarehouseRetrieveItem.do",
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
		}).submit();
	}else{
		Swal.fire("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
	
});
	
	
	stDate = $("#dateStart").val();
	enDate = $("#dateEnd").val();
	
	var date = new Date();
	$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEnd").val(new Date().toISOString().substring(0, 10));