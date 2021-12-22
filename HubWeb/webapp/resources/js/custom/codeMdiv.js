//모달 열기
$("#mdiv-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#mdiv-modal").css('display', 'none')
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#mdiv-modal").css('display', 'none')
})

//필드 컬럼 설정
var fieldsMdiv = [
		{ fieldName: "divChild", dataType: "text" }
	  , { fieldName: "divName", dataType: "text" }
	]
	
var columnsMdiv = [
	{ name: "divChild", fieldName: "divChild", type: "data", width: "150", header: { text: "분류 코드" } ,"visible" : false }
	, { name: "divName", fieldName: "divName", type: "data", width: "326", header: { text: "중분류명" } }
]
	

//데이터 가져오기는 hubWeb_manage 에서 대분류가 입력되면 실시한다

//리얼그리드 공통부분
var mdivProvider, mdivAssistGrid;

/* 			기본 셋팅 gcommDAssistGrid	 	*/
function createmdivAssistGrid() {
	mdivProvider = new RealGrid.LocalDataProvider();
	mdivAssistGrid = new RealGrid.GridView("realgrid_mdiv");

	mdivAssistGrid.setDataSource(mdivProvider);
	mdivProvider.setFields(fieldsMdiv);
	mdivAssistGrid.setColumns(columnsMdiv);
	mdivAssistGrid.resetCurrent();
	mdivAssistGrid.setCheckBar({
	  visible: false
  });
  
	
  //코드도움창에서 더블클릭했을때
	mdivAssistGrid.onCellDblClicked = function (grid, clickData) {
	    let value1 = mdivAssistGrid.getValues(clickData.dataRow).divName;
	    let value2 = mdivAssistGrid.getValues(clickData.dataRow).divChild;
	    $(mdivObj).val(value1)
//	    $("input[name=mdivName]").val(value1)
	    $("input[name=divCodeM]").val(value2)
		$("#mdiv-modal").css("display", "none")
  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	mdivAssistGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // deptAssistGrid 속성값
	mdivAssistGrid.header.height = 40;
	mdivAssistGrid.footer.height = 40;
	mdivAssistGrid.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	mdivAssistGrid.setEditOptions({
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
  createmdivAssistGrid();
  mdivAssistGrid.footer.visible = false;
}

window.onload = start();





