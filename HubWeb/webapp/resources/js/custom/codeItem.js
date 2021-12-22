//모달 열기
$("#item-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#item-modal").css('display', 'none')
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#item-modal").css('display', 'none')
})

//필드 컬럼 설정
var fieldsItem = [
		{ fieldName: "icodeName", dataType: "text" }
	  , { fieldName: "rawsCode", dataType: "text" }
	  , { fieldName: "rawsName", dataType: "text" }
	  , { fieldName: "gcommName", dataType: "text" }
	  , { fieldName: "rawsPurchprice", dataType: "text" }
	  , { fieldName: "rawsSalprice", dataType: "text" }
	]
	
	var columnsItem = [
		{ name: "icodeName", fieldName: "icodeName", type: "data", width: "60", header: { text: "품목 계정" } }
		, { name: "rawsCode", fieldName: "rawsCode", type: "data", width: "60", header: { text: "코드" } }
		, { name: "rawsName", fieldName: "rawsName", type: "data", width: "300", header: { text: "품명" } , styleName: "left-column"}
		, { name: "gcommName", fieldName: "gcommName", type: "data", width: "66", header: { text: "규격" } }
		, { name: "rawsPurchprice", fieldName: "rawsPurchprice", type: "data", width: "60", header: { text: "매입단가" }, "visible" : false }
		, { name: "rawsSalprice", fieldName: "rawsSalprice", type: "data", width: "60", header: { text: "매출단가" }, "visible" : false }
	]
	



//리얼그리드 공통부분
var itemProvider, itemAssistGrid;

/* 			기본 셋팅 itemAssistGrid	 	*/
function createitemAssistGrid() {
	itemProvider = new RealGrid.LocalDataProvider();
	itemAssistGrid = new RealGrid.GridView("realgrid_item");

	itemAssistGrid.setDataSource(itemProvider);
	itemProvider.setFields(fieldsItem);
	itemAssistGrid.setColumns(columnsItem);
	itemAssistGrid.resetCurrent();
	itemAssistGrid.setCheckBar({
	  visible: false
  });

	$("#itemSearchForm").ajaxForm({
		url : $.getContextPath() + "/info2/itemManageRetrieve.do",
		method : "post",
		dataType:"json",
		success:function(data){
			itemProvider.fillJsonData(data, {fillMode: "set"});
		}
	}).submit();
	
	$("#itemSearchBtn").on("click",function(){
		//코드도움창에서 검색
		$("#itemSearchForm").ajaxForm({
			url : $.getContextPath() + "/info2/itemManageRetrieve.do",
			method : 'post',
			dataType:"json",
			success:function(data){
				itemProvider.fillJsonData(data, {fillMode: "set"});
			},
			error : function(xhr, errorResp, error){
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		}).submit();
	})
	
	if(detailGrid){
		//품목 field 를 찾아놓는다
		if(detailGrid.columnByName('itemCode')){
			  var fieldItem = detailGrid.columnByName('itemCode').dataIndex
		}else if(detailGrid.columnByName('prodCode')){
			  var fieldItem = detailGrid.columnByName('prodCode').dataIndex
		}else if(detailGrid.columnByName('rawsCode')){
			  var fieldItem = detailGrid.columnByName('rawsCode').dataIndex	  
		}
	}
  //코드도움창에서 더블클릭했을때
	itemAssistGrid.onCellDblClicked = function (grid, clickData) {
		//품목 코드 적용
		var current = detailGrid.getCurrent();
	    var row1 = current.dataRow;
	    var field1 = current.fieldIndex;
	    var value1 = itemAssistGrid.getValues(clickData.dataRow).rawsCode;
	    var value2 = itemAssistGrid.getValues(clickData.dataRow).rawsName;
	    
	    if(fieldItem != current.fieldIndex){
	    	  $("input[name=rawsCode]").val(value1);
    		  $(itemCodeObj).val(value1);
	    	  $(itemObj).val(value2);
			  $("input[name=rawsName]").val(value2);
			  $("#item-modal").css('display', 'none');
		}else{
		    detailProvider.setValue(row1, field1, value1);
		    //품명 적용
		    var row2 = current.dataRow;
		    if(detailGrid.columnByName('rawsName')){
		    	var field2 = detailGrid.columnByName('rawsName').dataIndex
		    }else{
		    	var field2 = detailGrid.columnByName('prodName').dataIndex
		    }
			
		   detailProvider.setValue(row2, field2, value2);
		   
		   
	//	    //규격 적용
		    var row3 = current.dataRow;
		    var field3 = detailGrid.columnByName('gcommName').dataIndex
		    var value3 = itemAssistGrid.getValues(clickData.dataRow).gcommName;
		    detailProvider.setValue(row3, field3, value3);
		    //품목 계정 적용
		    var row4 = current.dataRow
		    var field4 = detailGrid.columnByName('icodeName').dataIndex
		    var value4 = itemAssistGrid.getValues(clickData.dataRow).icodeName;
		    detailProvider.setValue(row4, field4, value4);
		   
		    //입고단가 적용
		    if(detailGrid.columnByName('rawsPurchprice')){
		    	var field5 = detailGrid.columnByName('rawsPurchprice').dataIndex
		    	var row5 = current.dataRow
			    var value5 = itemAssistGrid.getValues(clickData.dataRow).rawsPurchprice;
			    detailProvider.setValue(row5, field5, value5);
		    }else if(detailGrid.columnByName('orrUprice')){
		    	var field5 = detailGrid.columnByName('orrUprice').dataIndex
		    	var row5 = current.dataRow
			    var value5 = itemAssistGrid.getValues(clickData.dataRow).rawsPurchprice;
			    detailProvider.setValue(row5, field5, value5);
		    }else if(detailGrid.columnByName('irUprice')){
		    	var field5 = detailGrid.columnByName('irUprice').dataIndex
		    	var row5 = current.dataRow
			    var value5 = itemAssistGrid.getValues(clickData.dataRow).rawsPurchprice;
			    detailProvider.setValue(row5, field5, value5);
		    }else if(detailGrid.columnByName('prcUprice')){
		    	var field5 = detailGrid.columnByName('prcUprice').dataIndex
		    	var row5 = current.dataRow
			    var value5 = itemAssistGrid.getValues(clickData.dataRow).rawsPurchprice;
			    detailProvider.setValue(row5, field5, value5);
		    }
		    
		    
		    //출고단가 적용
		    if(detailGrid.columnByName('rawsSalprice')){
			    var field6 = detailGrid.columnByName('rawsSalprice').dataIndex
			    var row6 = current.dataRow
			    var value6 = itemAssistGrid.getValues(clickData.dataRow).rawsSalprice;
			    detailProvider.setValue(row6, field6, value6);
		    }else if(detailGrid.columnByName('epUprice')){
		    	var field6 = detailGrid.columnByName('epUprice').dataIndex
		    	var row6 = current.dataRow
			    var value6 = itemAssistGrid.getValues(clickData.dataRow).rawsSalprice;
			    detailProvider.setValue(row6, field6, value6);
		    }else if(detailGrid.columnByName('opUprice')){
		    	var field6 = detailGrid.columnByName('opUprice').dataIndex
		    	var row6 = current.dataRow
			    var value6 = itemAssistGrid.getValues(clickData.dataRow).rawsSalprice;
			    detailProvider.setValue(row6, field6, value6);
		    }else if(detailGrid.columnByName('ropUprice')){
		    	var field6 = detailGrid.columnByName('ropUprice').dataIndex
		    	var row6 = current.dataRow
			    var value6 = itemAssistGrid.getValues(clickData.dataRow).rawsSalprice;
			    detailProvider.setValue(row6, field6, value6);
		    }else if(detailGrid.columnByName('rpUprice')){
		    	var field6 = detailGrid.columnByName('rpUprice').dataIndex
		    	var row6 = current.dataRow
			    var value6 = itemAssistGrid.getValues(clickData.dataRow).rawsSalprice;
			    detailProvider.setValue(row6, field6, value6);
		    }else if(detailGrid.columnByName('scpUprice')){
		    	var field6 = detailGrid.columnByName('scpUprice').dataIndex
		    	var row6 = current.dataRow
			    var value6 = itemAssistGrid.getValues(clickData.dataRow).rawsSalprice;
			    detailProvider.setValue(row6, field6, value6);
		    }
		    
		    
		    //입출고조정 단가 적용
		    if((detailGrid.getValues(detailGrid.getCurrent().dataRow).adjSort)===('입고')){
		    	var row7 = current.dataRow
		    	var field7 = detailGrid.columnByName('adjPrice').dataIndex
		    	var value7 = itemAssistGrid.getValues(clickData.dataRow).rawsPurchprice;
		    	detailProvider.setValue(row7, field7, value7);
		    }else if((detailGrid.getValues(detailGrid.getCurrent().dataRow).adjSort)===('출고')){
		    	var row7 = current.dataRow
		    	var field7 = detailGrid.columnByName('adjPrice').dataIndex
		    	var value7 = itemAssistGrid.getValues(clickData.dataRow).rawsSalprice;
		    	detailProvider.setValue(row7, field7, value7);
		    }
		    
		    
		    $("#item-modal").css('display', 'none')
		}
  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
  itemAssistGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  ,fitStyle : "even"
	  });
  
  // empAssistGrid 속성값
  itemAssistGrid.header.height = 40;
  itemAssistGrid.footer.height = 40;
  itemAssistGrid.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
  itemAssistGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : false
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  , enterToTab : true
	  ,forceAppend:true
	  ,fitStyle : "even"
  })
}


function start() {
  createitemAssistGrid();
  itemAssistGrid.footer.visible = false;
}

window.onload = start();





