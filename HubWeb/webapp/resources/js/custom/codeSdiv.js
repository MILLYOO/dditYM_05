//모달 열기
$("#sdiv-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#sdiv-modal").css('display', 'none')
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#sdiv-modal").css('display', 'none')
})

//필드 컬럼 설정
var fieldsSdiv = [
		{ fieldName: "divChild", dataType: "text" }
	  , { fieldName: "divName", dataType: "text" }
	]
	
var columnsSdiv = [
	{ name: "divChild", fieldName: "divChild", type: "data", width: "150", header: { text: "분류 코드" } ,"visible" : false }
	, { name: "divName", fieldName: "divName", type: "data", width: "326", header: { text: "소분류명" } }
]
	

//데이터 가져오기는 hubWeb_manage 에서 대분류가 입력되면 실시한다

//리얼그리드 공통부분
var sdivProvider, sdivAssistGrid;

/* 			기본 셋팅 gcommDAssistGrid	 	*/
function createsdivAssistGrid() {
	sdivProvider = new RealGrid.LocalDataProvider();
	sdivAssistGrid = new RealGrid.GridView("realgrid_sdiv");

	sdivAssistGrid.setDataSource(sdivProvider);
	sdivProvider.setFields(fieldsSdiv);
	sdivAssistGrid.setColumns(columnsSdiv);
	sdivAssistGrid.resetCurrent();
	sdivAssistGrid.setCheckBar({
	  visible: false
  });
  
	
  //코드도움창에서 더블클릭했을때
	sdivAssistGrid.onCellDblClicked = function (grid, clickData) {
		let current = sdivAssistGrid.getCurrent();
		let row = current.dataRow;
	    let field = current.fieldIndex;
	    let value = sdivAssistGrid.getValues(clickData.dataRow).divName;
	    $(sdivObj).val(value)
//	    $("input[name=sdivName]").val(value)
		$("#sdiv-modal").css("display", "none")
  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	sdivAssistGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // deptAssistGrid 속성값
	sdivAssistGrid.header.height = 40;
	sdivAssistGrid.footer.height = 40;
	sdivAssistGrid.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	sdivAssistGrid.setEditOptions({
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
  createsdivAssistGrid();
  sdivAssistGrid.footer.visible = false;
}

window.onload = start();





