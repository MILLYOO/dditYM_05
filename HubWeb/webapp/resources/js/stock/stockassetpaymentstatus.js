/**
 * 재고자산수불부
 */

 var fields = [
	    { fieldName: "icodeName", dataType: "text" }
	  , { fieldName: "rawsCode", dataType: "text" }
	  , { fieldName: "rawsName", dataType: "text" }
	  , { fieldName: "gcommName", dataType: "text" }
	  , { fieldName: "ucommStname", dataType: "text" }
	  , { fieldName: "ldivName", dataType: "text" }
	  , { fieldName: "mdivName", dataType: "text" }
	  , { fieldName: "sdivName", dataType: "text" }
	];
 
 var columns = [
	    { name: "icodeName", fieldName: "icodeName", type: "data", width: "90", header: { text: "품목계정" } }
	  , { name: "rawsCode", fieldName: "rawsCode", type: "data", width: "90", header: { text: "품목코드" } }
	  , { name: "rawsName", fieldName: "rawsName", type: "data", width: "90", header: { text: "품목명" } }
	  , { name: "gcommName", fieldName: "gcommName", type: "data", width: "60", header: { text: "규격" } }
	  , { name: "ucommStname", fieldName: "ucommStname", type: "data", width: "90", header: { text: "단위" } }
	  , { name: "ldivName", fieldName: "ldivName", type: "data", width: "60", header: { text: "대분류" } }
	  , { name: "mdivName", fieldName: "mdivName", type: "data", width: "90", header: { text: "중분류" } }
	  , { name: "sdivName", fieldName: "sdivName", type: "data", width: "60", header: { text: "소분류" } }
	];
 
 
 var fields1 = [
	    { fieldName: "stoassDate", dataType: "date" }
	  , { fieldName: "enterQty", dataType: "number" }
	  , { fieldName: "enterUcost", dataType: "number" }
	  , { fieldName: "enterCost", dataType: "number" }
	  , { fieldName: "outQty", dataType: "number" }
	  , { fieldName: "outUcost", dataType: "number" }
	  , { fieldName: "outCost", dataType: "number" }
	  , { fieldName: "stoQty", dataType: "number" }
	  , { fieldName: "stoUcost", dataType: "number" }
	  , { fieldName: "stoCost", dataType: "number" }
	];

var columns1 = [
	    { name: "stoassDate", fieldName: "stoassDate", type: "data", width: "60", header: { text: "수불일자" }, editor: {datetimeFormat: "yyyy-MM-dd"} }
	  , { name: "enterQty", fieldName: "enterQty", type: "data", width: "60", header: { text: "입고수량" }, numberFormat: "#,##0"
		  , footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column"}
	  , { name: "enterUcost", fieldName: "enterUcost", type: "data", width: "60", header: { text: "입고단가" }, numberFormat: "#,##0"
		  , footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column"}
	  , { name: "enterCost", fieldName: "enterCost", type: "data", width: "60", header: { text: "입고금액" }, numberFormat: "#,##0" 
		  , footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column"}
	  , { name: "outQty", fieldName: "outQty", type: "data", width: "90", header: { text: "출고수량" }, numberFormat: "#,##0" 
		  , footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column"}
	  , { name: "outUcost", fieldName: "outUcost", type: "data", width: "60", header: { text: "출고단가" }, numberFormat: "#,##0" 
		  , footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column"}
	  , { name: "outCost", fieldName: "outCost", type: "data", width: "90", header: { text: "출고금액" }, numberFormat: "#,##0" 
		  , footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column"}
	  , { name: "stoQty", fieldName: "stoQty", type: "data", width: "60", header: { text: "재고수량" }, numberFormat: "#,##0" 
		  , footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column"}
	  , { name: "stoUcost", fieldName: "stoUcost", type: "data", width: "60", header: { text: "재고단가" }, numberFormat: "#,##0" 
		  , footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column"}
	  , { name: "stoCost", fieldName: "stoCost", type: "data", width: "60", header: { text: "재고금액" }, numberFormat: "#,##0" 
		  , footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column"}
	  ]
	  
// 목록 조회
$("#select").on("click",function(){
	masterGrid.footer.visible = false
	var startDate = $("#dateStart").val();
	var endDate = $("#dateEnd").val();
	
	// 날짜 지정하지 않았을 때
	if(startDate.replace(/\s/g,"").length==0){
		Swal.fire("날짜를 입력하세요.");
		return false;
	}
	
	if(endDate >= startDate){
		let searchForm = $("#searchForm").ajaxForm({
			url : $.getContextPath() + "/stock/stockAssetPaymentStatusRetrieve.do",
			method : "post",
			dataType : "json",
			success : function(resp) {
				masterProvider.fillJsonData(resp, {fillMode: "set"});
			}
		}).submit();
	}else{
		Swal.fire("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
});	  
	  

/********************************* 디테일 설정하기 ******************************/

function detailGet(masterRow) {
	  detailProvider.clearRows();
	  console.log(masterRow);
	  if (masterRow >= 0) {
	      var mstKey = masterProvider.getValue(masterRow, "rawsName");
	      let searchForm = $("#searchForm").ajaxForm({
			url : $.getContextPath()+"/stock/stockAssetList.do",
			data : {"prodName" : mstKey},
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
	  };
  };
	  
  var date = new Date();
	$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEnd").val(new Date().toISOString().substring(0, 10));
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
