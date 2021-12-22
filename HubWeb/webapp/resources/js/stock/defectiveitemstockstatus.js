/**
 * 불량품재고현황
 */

  var fields = [
	    { fieldName: "icodeName", dataType: "text" }
	  , { fieldName: "prodCode", dataType: "text" }
	  , { fieldName: "prodName", dataType: "text" }
	  , { fieldName: "gcommName", dataType: "text" }
	  , { fieldName: "kcommName", dataType: "text" }
	  , { fieldName: "ppBadsum", dataType: "nuumber" }
	  ]
  
  var columns = [
	   { name: "icodeName", fieldName: "icodeName", type: "data", width: "30", header: { text: "품목 계정" } }
	 , { name: "prodCode", fieldName: "prodCode", type: "data", width: "30", header: { text: "품목 코드" } }
	 , { name: "prodName", fieldName: "prodName", type: "data", width: "30", header: { text: "품명" } }
	 , { name: "gcommName", fieldName: "gcommName", type: "data", width: "30", header: { text: "규격" } }
	 , { name: "kcommName", fieldName: "kcommName", type: "data", width: "90", header: { text: "공정" } }
	 , { name: "ppBadsum", fieldName: "ppBadsum", type: "data", width: "60", header: { text: "입고량" }, numberFormat: "#,##0"
		 ,  footer: {expression: "sum",numberFormat: "#,##0",}, styleName: "right-column"}
	 ]
  
//목록 조회
	$("#select").on("click",function(){
		var startDate = $("#dateStart").val();
		var endDate = $("#dateEnd").val();
		
		// 날짜 지정하지 않았을 때
		if(startDate.replace(/\s/g,"").length==0){
			Swal.fire("날짜를 입력하세요.");
			return false;
		}
		
		if(endDate >= startDate){
			let searchForm = $("#searchForm").ajaxForm({
				url : $.getContextPath() + "/stock/defectiveItemStockStatusRetrieve.do",
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
	
	
	stDate = $("#dateStart").val();
	enDate = $("#dateEnd").val();
	
	var date = new Date();
	$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEnd").val(new Date().toISOString().substring(0, 10));