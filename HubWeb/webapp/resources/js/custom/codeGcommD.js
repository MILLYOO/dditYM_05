//모달 열기
$("#gcomm-modalD").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#gcomm-modalD").css('display', 'none')
		$("#gcomm-modalD").find(':input[name]').prop('value','');
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#gcomm-modalD").css('display', 'none')
	$("#gcomm-modalD").find(':input[name]').prop('value','');
})

//필드 컬럼 설정
var fieldsGcomm = [
		{ fieldName: "commCode", dataType: "text" }
	  , { fieldName: "commName", dataType: "text" }
	]
	
	var columnsGcomm = [
		{ name: "commCode", fieldName: "commCode", type: "data", width: "100", header: { text: "규격 코드" } ,"visible" : false }
		, { name: "commName", fieldName: "commName", type: "data", width: "326", header: { text: "규격명" } }
	]
	

//리얼그리드 공통부분
var gcommDProvider, gcommDAssistGrid;

/* 			기본 셋팅 gcommDAssistGrid	 	*/
function creategcommDAssistGrid() {
	gcommDProvider = new RealGrid.LocalDataProvider();
	gcommDAssistGrid = new RealGrid.GridView("realgrid_gcommD");

	gcommDAssistGrid.setDataSource(gcommDProvider);
	gcommDProvider.setFields(fieldsGcomm);
	gcommDAssistGrid.setColumns(columnsGcomm);
	gcommDAssistGrid.resetCurrent();
	gcommDAssistGrid.setCheckBar({
	  visible: false
  });
  
	if(detailGrid){
		//규격 field 를 찾아놓는다
		if(detailGrid.columnByName('gcommName')){
			  var fieldGcommD = detailGrid.columnByName('gcommName').dataIndex
		}
	}
  //코드도움창에서 더블클릭했을때
	gcommDAssistGrid.onCellDblClicked = function (grid, clickData) {
		let current = detailGrid.getCurrent();
		let row = current.dataRow;
	    let field = current.fieldIndex;
	    let value = gcommDAssistGrid.getValues(clickData.dataRow).commName;
	    
    	//현재 그리드의 커서가 거래처가 아니면 buyerCode 아이디를 찾아 value 값으로 준다
	   if(fieldGcommD != current.fieldIndex){
		   $(gcommObj).val(value)
		  $("input[name=gcommName]").val(value)
		  $("#gcomm-modalD").css('display', 'none')
	   }else{
		   detailProvider.setValue(row, field, value);
		   $("#gcomm-modalD").css('display', 'none')
	   }
	    
  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	gcommDAssistGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // deptAssistGrid 속성값
	gcommDAssistGrid.header.height = 40;
	gcommDAssistGrid.footer.height = 40;
	gcommDAssistGrid.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	gcommDAssistGrid.setEditOptions({
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
  creategcommDAssistGrid();
  gcommDAssistGrid.footer.visible = false;
}

window.onload = start();





