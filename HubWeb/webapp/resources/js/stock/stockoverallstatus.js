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
		  fieldName: "rawsBaseqty",
		  dataType: "number"
	  },
	  {
		  fieldName: "buyEnter",
		  dataType: "number"
	  },
	  {
		  fieldName: "prodEnter",
		  dataType: "number"
	  },
	  {
		  fieldName: "sumEnter",
		  dataType: "number"
	  },
	  {
		  fieldName: "sellOut",
		  dataType: "number"
	  },
	  {
		  fieldName: "prodOut",
		  dataType: "number"
	  },
	  {
		  fieldName: "sumOut",
		  dataType: "number"
	  },
	  {
		  fieldName: "rawsQty",
		  dataType: "number"
	  }
	];

	var columns = [
	  {
	    name: "icodeName",
	    fieldName: "icodeName",
	    type: "data",
	    width: "60",
	    header: {
	      text: "품목계정"
	    }
	  },
	  {
	    name: "rawsCode",
	    fieldName: "rawsCode",
	    type: "data",
	    width: "60",
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
	    },styleName : "left-column"
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
	    name: "ucommStname",
	    fieldName: "ucommStname",
	    type: "data",
	    width: "60",
	    header: {
	      text: "단위",
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
		  name: "rawsBaseqty",
		  fieldName: "rawsBaseqty",
		  type: "data",
		  width: "60",
		  header: {
			  text: "기초재고",
		  },
		  numberFormat: "#,##0"
			  ,  footer: {
			      expression: "sum",
			      numberFormat: "#,##0",
			    },
			    styleName: "right-column"	  
			  
	  },
	  {
		  name: "buyEnter",
		  fieldName: "buyEnter",
		  type: "data",
		  width: "60",
		  header: {
			  text: "구매입고",
		  },
		  numberFormat: "#,##0"
			  ,  footer: {
			      expression: "sum",
			      numberFormat: "#,##0",
			    },
			    styleName: "right-column"
	  },
	  {
		  name: "prodEnter",
		  fieldName: "prodEnter",
		  type: "data",
		  width: "60",
		  header: {
			  text: "생산입고",
		  },
		  numberFormat: "#,##0"
			  ,  footer: {
			      expression: "sum",
			      numberFormat: "#,##0",
			    },
			    styleName: "right-column"
	  },
	  {
		  name: "sumEnter",
		  fieldName: "sumEnter",
		  type: "data",
		  width: "60",
		  header: {
			  text: "입고합계",
		  },
		  numberFormat: "#,##0"
			  ,  footer: {
			      expression: "sum",
			      numberFormat: "#,##0",
			    },
			    styleName: "right-column"
	  },
	  {
		  name: "sellOut",
		  fieldName: "sellOut",
		  type: "data",
		  width: "60",
		  header: {
			  text: "판매출고",
		  },
		  numberFormat: "#,##0"
			  ,  footer: {
			      expression: "sum",
			      numberFormat: "#,##0",
			    },
			    styleName: "right-column"
	  },
	  {
		  name: "prodOut",
		  fieldName: "prodOut",
		  type: "data",
		  width: "60",
		  header: {
			  text: "생산출고",
		  },
		  numberFormat: "#,##0"
			  ,  footer: {
			      expression: "sum",
			      numberFormat: "#,##0",
			    },
			    styleName: "right-column"
	  },
	  {
		  name: "sumOut",
		  fieldName: "sumOut",
		  type: "data",
		  width: "60",
		  header: {
			  text: "출고합계",
		  },
		  numberFormat: "#,##0"
			  ,  footer: {
			      expression: "sum",
			      numberFormat: "#,##0",
			    },
			    styleName: "right-column"
	  },
	  {
		  name: "rawsQty",
		  fieldName: "rawsQty",
		  type: "data",
		  width: "60",
		  header: {
			  text: "기말재고",
		  } ,
		  numberFormat: "#,##0"
			  ,  footer: {
			      expression: "sum",
			      numberFormat: "#,##0",
			    },
			    styleName: "right-column"
	  }
	];
	
	//조회
	$("#select").click(function(){
		$("#searchForm").ajaxForm({
			url : $.getContextPath() + "/stock/stockOverallStatusRetrieve.do",
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
	
	function detailGet(masterRow) {
		  detailProvider.clearRows();
		   console.log(masterRow);
		  if (masterRow >= 0) {
		      var mstKey = masterProvider.getValue(masterRow, "estCode");
		      console.log(mstKey);
		      
		      var datas = []
		     
		      // 여기서 ajax로 datas에 값 넣어주기
		       // 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능
		         $.getJSON( $.getContextPath() + "/sales/EstimateRetrieve.do?estCode=" + mstKey, function(data){
		        console.log(data);
		        detailProvider.fillJsonData(data.dataList, {fillMode: "set"});
		   }) 
		      // ajax로 검색을 하여 완료되면, 해당 메소드를 이용하여 값 넣어주기.
//		      detailProvider.setRows(datas);
		  };
		};