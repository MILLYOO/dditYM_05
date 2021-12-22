//모달 열기
$("#item-modalM").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#item-modalM").css('display', 'none')
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#item-modalM").css('display', 'none')
})

//필드 컬럼 설정
var fieldsItemM = [
		{ fieldName: "icodeName", dataType: "text" }
	  , { fieldName: "rawsCode", dataType: "text" }
	  , { fieldName: "rawsName", dataType: "text" }
	  , { fieldName: "gcommName", dataType: "text" }
	]
	
	var columnsItemM = [
		{ name: "icodeName", fieldName: "icodeName", type: "data", width: "80", header: { text: "품목 계정" } }
		, { name: "rawsCode", fieldName: "rawsCode", type: "data", width: "80", header: { text: "코드" } }
		, { name: "rawsName", fieldName: "rawsName", type: "data", width: "305", header: { text: "품명" } , styleName: "left-column"}
		, { name: "gcommName", fieldName: "gcommName", type: "data", width: "100", header: { text: "규격" } }
	]
	



//리얼그리드 공통부분
var itemProviderM, itemAssistGridM;

/* 			기본 셋팅 itemAssistGrid	 	*/
function createitemAssistGridM() {
	itemProviderM = new RealGrid.LocalDataProvider();
	itemAssistGridM = new RealGrid.GridView("realgrid_itemM");

	itemAssistGridM.setDataSource(itemProviderM);
	itemProviderM.setFields(fieldsItemM);
	itemAssistGridM.setColumns(columnsItemM);
	itemAssistGridM.resetCurrent();
	itemAssistGridM.setCheckBar({
	  visible: false
  });
	$("#itemSearchForm").ajaxForm({
		url : $.getContextPath() + "/info2/itemManageRetrieve.do",
		method : "post",
		dataType:"json",
		success:function(data){
			itemProviderM.fillJsonData(data, {fillMode: "set"});
		}
	}).submit();
	
	$("#itemSearchBtn").on("click",function(){
		//코드도움창에서 검색
		$("#itemSearchForm").ajaxForm({
			url : $.getContextPath() + "/info2/itemManageRetrieve.do",
			method : 'post',
			dataType:"json",
			success:function(data){
				itemProviderM.fillJsonData(data, {fillMode: "set"});
			},
			error : function(xhr, errorResp, error){
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		}).submit();
	})
	
	if(masterGrid){
		//품목 field 를 찾아놓는다
		if(masterGrid.columnByName('itemCode')){
			  var fieldItem = masterGrid.columnByName('itemCode').dataIndex
		}else if(masterGrid.columnByName('prodCode')){
			  var fieldItem = masterGrid.columnByName('prodCode').dataIndex
		}else if(masterGrid.columnByName('rawsCode')){
			  var fieldItem = masterGrid.columnByName('rawsCode').dataIndex	  
		}
	}
	
  //코드도움창에서 더블클릭했을때
	itemAssistGridM.onCellDblClicked = function (grid, clickData) {
		//품목 코드 적용
		var current = masterGrid.getCurrent();
	    var row1 = current.dataRow;
	    var field1 = current.fieldIndex;
	    var value1 = itemAssistGridM.getValues(clickData.dataRow).rawsCode;
	    var value2 = itemAssistGridM.getValues(clickData.dataRow).rawsName;
	    
	    if(fieldItem != current.fieldIndex){
	    	  $(itemObj).val(value2)
//			  $("input[name=rawsName]").val(value2)
			  $("#item-modalM").css('display', 'none')
		}else{
			//품목 계정 적용
			var row4 = current.dataRow
			var field4 = masterGrid.columnByName('icodeName').dataIndex
			var value4 = itemAssistGridM.getValues(clickData.dataRow).icodeName;
			masterProvider.setValue(row4, field4, value4);
		    masterProvider.setValue(row1, field1, value1);
		    //품명 적용
		    var row2 = current.dataRow;
		    if(masterGrid.columnByName('rawsName')){
		    	var field2 = masterGrid.columnByName('rawsName').dataIndex
		    }else{
		    	var field2 = masterGrid.columnByName('prodName').dataIndex
		    }
		    masterProvider.setValue(row2, field2, value2);
	//	    //규격 적용
		    var row3 = current.dataRow;
		    var field3 = masterGrid.columnByName('gcommName').dataIndex
		    var value3 = itemAssistGridM.getValues(clickData.dataRow).gcommName;
		    masterProvider.setValue(row3, field3, value3);
		    
		    $("#item-modalM").css('display', 'none')
		}
  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
  itemAssistGridM.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  , fitStyle : "even"
	  });
  
  // empAssistGrid 속성값
  itemAssistGridM.header.height = 40;
  itemAssistGridM.footer.height = 40;
  itemAssistGridM.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
  itemAssistGridM.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : false
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  , enterToTab : true
	  ,forceAppend:true
  })
}


function start() {
  createitemAssistGridM();
  itemAssistGridM.footer.visible = false;
}

window.onload = start();





