//모달 열기
$("#gcomm-modalM").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#gcomm-modalM").css('display', 'none')
		$("#gcomm-modalM").find(':input[name]').prop('value','');
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#gcomm-modalM").css('display', 'none')
	$("#gcomm-modalM").find(':input[name]').prop('value','');
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
var gcommMProvider, gcommMAssistGrid;

/* 			기본 셋팅 gcommMAssistGrid	 	*/
function creategcommMAssistGrid() {
	gcommMProvider = new RealGrid.LocalDataProvider();
	gcommMAssistGrid = new RealGrid.GridView("realgrid_gcommM");

	gcommMAssistGrid.setDataSource(gcommMProvider);
	gcommMProvider.setFields(fieldsGcomm);
	gcommMAssistGrid.setColumns(columnsGcomm);
	gcommMAssistGrid.resetCurrent();
	gcommMAssistGrid.setCheckBar({
	  visible: false
  });
  
	if(masterGrid){
		//규격 field 를 찾아놓는다
		if(masterGrid.columnByName('gcommName')){
			  var fieldGcomm = masterGrid.columnByName('gcommName').dataIndex
		}
	}
	
  //코드도움창에서 더블클릭했을때
	gcommMAssistGrid.onCellDblClicked = function (grid, clickData) {
		let current = masterGrid.getCurrent();
		let row = current.dataRow;
	    let field = current.fieldIndex;
	    let value = gcommMAssistGrid.getValues(clickData.dataRow).commName;
    	
	    //현재 그리드의 커서가 거래처가 아니면 buyerCode 아이디를 찾아 value 값으로 준다
	   if(fieldGcomm != current.fieldIndex){
		   $(gcommObj).val(value)
//		  $("input[name=gcommName]").val(value)
		  $("#gcomm-modalM").css('display', 'none')
	   }else{
		   masterProvider.setValue(row, field, value);
		    $("#gcomm-modalM").css('display', 'none')
	   }
  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	gcommMAssistGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // deptAssistGrid 속성값
	gcommMAssistGrid.header.height = 40;
	gcommMAssistGrid.footer.height = 40;
	gcommMAssistGrid.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	gcommMAssistGrid.setEditOptions({
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
  creategcommMAssistGrid();
  gcommMAssistGrid.footer.visible = false;
}

window.onload = start();





