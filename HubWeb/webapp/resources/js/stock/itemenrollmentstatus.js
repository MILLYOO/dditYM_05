/**
 * 
 */
  var fields = [
	  {
	    fieldName: "icodeName",
	    dataType: "text"
	  },
	  {
	    fieldName: "rawsCode",
	    dataType: "text"
	  },
	  {
	    fieldName: "rawsName",
	    dataType: "text"
	  },
	  {
	    fieldName: "gcommName",
	    dataType: "text"
	  },
	  {
	    fieldName: "ucommInname",
	    dataType: "text"
	  },
	  {
	    fieldName: "ucommOutname",
	    dataType: "text"
	  },
	  {
	    fieldName: "ucommStname",
	    dataType: "text"
	  },
	  {
	    fieldName: "ldivName",
	    dataType: "text"
	  },
	  {
		  fieldName: "mdivName",
		  dataType: "text"
	  },
	  {
		  fieldName: "sdivName",
		  dataType: "text"
	  },
	  {
		  fieldName: "rawsAdqinv",
		  dataType: "number"
	  },
	  {
		  fieldName: "rawsStancost",
		  dataType: "number"
	  },
	  {
		  fieldName: "rawsAtcucost",
		  dataType: "number"
	  },
	];

	var columns = [
	  {
	    name: "icodeName",
	    fieldName: "icodeName",
	    type: "data",
	    width: "50",
	    header: {
	      text: "품목계정"
	    }
	  },
	  {
	    name: "rawsCode",
	    fieldName: "rawsCode",
	    type: "data",
	    width: "50",
	    header: {
	      text: "품목코드"
	    }
	  },
	  {
	    name: "rawsName",
	    fieldName: "rawsName",
	    type: "data",
	    width: "150",
	    header: {
	      text: "품목명"
	    }
	  },
	  {
	    name: "gcommName",
	    fieldName: "gcommName",
	    type: "data",
	    width: "60",
	    header: {
	      text: "규격",
	    }
	  },
	  {
	    name: "ucommInname",
	    fieldName: "ucommInname",
	    type: "data",
	    width: "40",
	    header: {
	      text: "입고",
	    }
	  },
	  {
	    name: "ucommOutname",
	    fieldName: "ucommOutname",
	    type: "data",
	    width: "40",
	    header: {
	      text: "출고",
	    }
	  },
	  {
		  name: "ucommStname",
		  fieldName: "ucommStname",
		  type: "data",
		  width: "40",
		  header: {
			  text: "재고",
		  }
	  },
	  {
		  name: "ldivName",
		  fieldName: "ldivName",
		  type: "data",
		  width: "60",
		  header: {
			  text: "대분류",
		  }
	  },
	  {
		  name: "mdivName",
		  fieldName: "mdivName",
		  type: "data",
		  width: "60",
		  header: {
			  text: "중분류",
		  }
	  },
	  {
		  name: "sdivName",
		  fieldName: "sdivName",
		  type: "data",
		  width: "60",
		  header: {
			  text: "소분류",
		  }
	  },
	  {
		  name: "rawsAdqinv",
		  fieldName: "rawsAdqinv",
		  type: "data",
		  width: "60",
		  header: {
			  text: "적정재고량",
		  }, numberFormat: "#,##0"
		  ,footer: {expression: "sum",numberFormat: "#,##0"}, styleName: "right-column"	  
	  },
	  {
		  name: "rawsStancost",
		  fieldName: "rawsStancost",
		  type: "data",
		  width: "60",
		  header: {
			  text: "표준원가",
		  }, numberFormat: "#,##0"
		  ,footer: {expression: "sum",numberFormat: "#,##0"}, styleName: "right-column"
	  },
	  {
		  name: "rawsAtcucost",
		  fieldName: "rawsAtcucost",
		  type: "data",
		  width: "60",
		  header: {
			  text: "실제원가",
		  }, numberFormat: "#,##0"
		  ,footer: {expression: "sum",numberFormat: "#,##0"}, styleName: "right-column"
	  }
	];
	
	//조회
	$("#select").click(function(){
		$("#searchForm").ajaxForm({
			url : $.getContextPath() + "/stock/itemEnrollmentStatusRetrieve.do",
			dataType:"json",
			success:function(data){
				console.log(data);
		 		masterProvider.fillJsonData(data, {fillMode: "set"});
			},
			error : function(xhr, errorResp, error){
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		}).submit();
	});
