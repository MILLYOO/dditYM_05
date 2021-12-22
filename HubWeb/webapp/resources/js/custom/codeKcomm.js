//모달 열기
$("#kcomm-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#kcomm-modal").css('display', 'none')
		$("#kcomm-modal").find(':input[name]').prop('value','');
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#kcomm-modal").css('display', 'none')
	$("#kcomm-modal").find(':input[name]').prop('value','');
})

//필드 컬럼 설정
var fieldsKcomm = [
		{ fieldName: "commCode", dataType: "text" }
	  , { fieldName: "commName", dataType: "text" }
	]
	
	var columnsKcomm = [
		{ name: "commCode", fieldName: "commCode", type: "data", width: "150", header: { text: "공정코드" } ,"visible" : false }
		, { name: "commName", fieldName: "commName", type: "data", width: "326", header: { text: "공정명" } }
	]
	
	

//리얼그리드 공통부분
var kcommProvider, kcommAssistGrid;

/* 			기본 셋팅 projAssistGrid	 	*/
function createkcommAssistGrid() {
	kcommProvider = new RealGrid.LocalDataProvider();
	kcommAssistGrid = new RealGrid.GridView("realgrid_kcomm");

	kcommAssistGrid.setDataSource(kcommProvider);
	kcommProvider.setFields(fieldsKcomm);
	kcommAssistGrid.setColumns(columnsKcomm);
	kcommAssistGrid.resetCurrent();
	kcommAssistGrid.setCheckBar({
	  visible: false
  });
  
	if(detailGrid){
		//공정 field 를 찾아놓는다
		if(detailGrid.columnByName('kcommName')){
			  var fieldKcomm = detailGrid.columnByName('kcommName').dataIndex
		}
	}
	
  //코드도움창에서 더블클릭했을때
	kcommAssistGrid.onCellDblClicked = function (grid, clickData) {
	    var current = detailGrid.getCurrent();
	    var row = current.dataRow;
	    var field = current.fieldIndex;
	    var value = kcommAssistGrid.getValues(clickData.dataRow).commName;
	    
    	//현재 그리드의 커서가 거래처가 아니면 buyerCode 아이디를 찾아 value 값으로 준다
	   if(fieldKcomm != current.fieldIndex){
		  $(kcommObj).val(value)
//		  $("input[name=kcommName]").val(value)
		  $("#kcomm-modal").css('display', 'none')
	   }else{
		   detailProvider.setValue(row, field, value);
		   $("#kcomm-modal").css('display', 'none')
	   }
  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	kcommAssistGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // deptAssistGrid 속성값
	kcommAssistGrid.header.height = 40;
	kcommAssistGrid.footer.height = 40;
	kcommAssistGrid.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	kcommAssistGrid.setEditOptions({
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
  createkcommAssistGrid();
  kcommAssistGrid.footer.visible = false;
}

window.onload = start();





