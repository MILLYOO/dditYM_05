//모달 열기
$("#ldiv-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#ldiv-modal").css('display', 'none')
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#ldiv-modal").css('display', 'none')
})

//필드 컬럼 설정
var fieldsLdiv = [
		{ fieldName: "divChild", dataType: "text" }
	  , { fieldName: "divName", dataType: "text" }
	]
	
	var columnsLdiv = [
		{ name: "divChild", fieldName: "divChild", type: "data", width: "150", header: { text: "분류 코드" } ,"visible" : false }
		, { name: "divName", fieldName: "divName", type: "data", width: "326", header: { text: "대분류명" } }
	]
	
	

//리얼그리드 공통부분
var ldivProvider, ldivAssistGrid;

/* 			기본 셋팅 gcommDAssistGrid	 	*/
function createldivAssistGrid() {
	ldivProvider = new RealGrid.LocalDataProvider();
	ldivAssistGrid = new RealGrid.GridView("realgrid_ldiv");

	ldivAssistGrid.setDataSource(ldivProvider);
	ldivProvider.setFields(fieldsLdiv);
	ldivAssistGrid.setColumns(columnsLdiv);
	ldivAssistGrid.resetCurrent();
	ldivAssistGrid.setCheckBar({
	  visible: false
  });
  
	
  //코드도움창에서 더블클릭했을때
	ldivAssistGrid.onCellDblClicked = function (grid, clickData) {
	    let value1 = ldivAssistGrid.getValues(clickData.dataRow).divName;
	    let value2 = ldivAssistGrid.getValues(clickData.dataRow).divChild;
	    $(ldivObj).val(value1);
//	    $("input[name=ldivName]").val(value1)
	    $("input[name=divCodeL]").val(value2)
		$("#ldiv-modal").css("display", "none")
  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	ldivAssistGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // deptAssistGrid 속성값
	ldivAssistGrid.header.height = 40;
	ldivAssistGrid.footer.height = 40;
	ldivAssistGrid.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	ldivAssistGrid.setEditOptions({
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
  createldivAssistGrid();
  ldivAssistGrid.footer.visible = false;
}

window.onload = start();





