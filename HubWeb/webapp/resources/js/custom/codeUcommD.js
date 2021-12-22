//모달 열기
$("#ucomm-modalD").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#ucomm-modalD").css('display', 'none')
		$("#ucomm-modalD").find(':input[name]').prop('value','');
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#ucomm-modalD").css('display', 'none')
	$("#ucomm-modalD").find(':input[name]').prop('value','');
})

//필드 컬럼 설정
var fieldsUcomm = [
		{ fieldName: "commCode", dataType: "text" }
	  , { fieldName: "commName", dataType: "text" }
	]
	
	var columnsUcomm = [
		{ name: "commCode", fieldName: "commCode", type: "data", width: "100", header: { text: "단위 코드" } ,"visible" : false }
		, { name: "commName", fieldName: "commName", type: "data", width: "326", header: { text: "단위명" } }
	]
	
	

//리얼그리드 공통부분
var ucommDProvider, ucommDAssistGrid;

/* 			기본 셋팅 gcommDAssistGrid	 	*/
function createucommDAssistGrid() {
	ucommDProvider = new RealGrid.LocalDataProvider();
	ucommDAssistGrid = new RealGrid.GridView("realgrid_ucommD");

	ucommDAssistGrid.setDataSource(ucommDProvider);
	ucommDProvider.setFields(fieldsUcomm);
	ucommDAssistGrid.setColumns(columnsUcomm);
	ucommDAssistGrid.resetCurrent();
	ucommDAssistGrid.setCheckBar({
	  visible: false
  });
  
	
	if(detailGrid){
		//단위 field 를 찾아놓는다
		if(detailGrid.columnByName('ucommName')){
			var fieldUcomm = detailGrid.columnByName('ucommName').dataIndex
		}
	}
	
  //코드도움창에서 더블클릭했을때
	ucommDAssistGrid.onCellDblClicked = function (grid, clickData) {
		let current = detailGrid.getCurrent();
		let row = current.dataRow;
	    let field = current.fieldIndex;
	    let value = ucommDAssistGrid.getValues(clickData.dataRow).commName;
	    
	  //현재 그리드의 커서가 거래처가 아니면 buyerCode 아이디를 찾아 value 값으로 준다
	   if(fieldUcomm != current.fieldIndex){
		  $(ucommObj).val(value)
//		  $("input[name=ucommName]").val(value)
		  $("#ucomm-modalD").css('display', 'none')
	   }else{
	    detailProvider.setValue(row, field, value);
	    $("#ucomm-modalD").css('display', 'none')
	   }
  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	ucommDAssistGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // deptAssistGrid 속성값
	ucommDAssistGrid.header.height = 40;
	ucommDAssistGrid.footer.height = 40;
	ucommDAssistGrid.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	ucommDAssistGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : false
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  , enterToTab : true
	  ,forceAppend:true
	  , fitStyle : "even"	
  })
}


function start() {
  createucommDAssistGrid();
  ucommDAssistGrid.footer.visible = false;
}

window.onload = start();





