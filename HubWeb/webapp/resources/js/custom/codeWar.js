//모달 열기
$("#war-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#war-modal").css('display', 'none')
		$("#war-modal").find(':input[name]').prop('value','');
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#war-modal").css('display', 'none')
	$("#war-modal").find(':input[name]').prop('value','');
})

//필드 컬럼 설정
var fieldsWar = [
		{ fieldName: "warCode", dataType: "text" }
	  , { fieldName: "warName", dataType: "text" }
	]
	
	var columnsWar = [
		{ name: "warCode", fieldName: "warCode", type: "data", width: "100", header: { text: "창고 코드" } ,"visible" : false }
		, { name: "warName", fieldName: "warName", type: "data", width: "326", header: { text: "창고명" } }
	]
	



//리얼그리드 공통부분
var warProvider, warAssistGrid;

/* 			기본 셋팅 empAssistGrid	 	*/
function createwarAssistGrid() {
  warProvider = new RealGrid.LocalDataProvider();
  warAssistGrid = new RealGrid.GridView("realgrid_war");

  warAssistGrid.setDataSource(warProvider);
  warProvider.setFields(fieldsWar);
  warAssistGrid.setColumns(columnsWar);
  warAssistGrid.resetCurrent();
  warAssistGrid.setCheckBar({
	  visible: false
  });
  
  $("#warSearchBtn").on("click",function(){
  //코드도움창에서 검색
	$("#warSearchForm").ajaxForm({
		url : $.getContextPath() + "/info2/warehouseRetrieve.do",
		method : 'post',
		dataType:"json",
		success:function(data){
			console.log(data);
			warProvider.fillJsonData(data, {fillMode: "set"});
		},
		error : function(xhr, errorResp, error){
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	}).submit();
  })
  
	
	if(detailGrid){
		//창고 field 를 찾아놓는다
		if(detailGrid.columnByName('warName')){
			  var fieldWar = detailGrid.columnByName('warName').dataIndex
		}
	}
	
  //코드도움창에서 더블클릭했을때
  warAssistGrid.onCellDblClicked = function (grid, clickData) {
	    var current = detailGrid.getCurrent();
	    var row = current.dataRow;
	    var field = current.fieldIndex;
	    var value = warAssistGrid.getValues(clickData.dataRow).warName;
	    
	    //현재 그리드의 커서가 창고가 아니면 warName 아이디를 찾아 value 값으로 준다
		   if(fieldWar != current.fieldIndex){
			  $(warObj).val(value)
//			  $("input[name=warName]").val(value)
			  $("#war-modal").css('display', 'none')
			  return;
		   }else{
			   detailProvider.setValue(row, field, value);
			    $("#war-modal").css('display', 'none')
		   }

	    
	    // input태그로 창고 검색하기 위한 부분
	    var warNameflag = $("#warName");
	    if(warNameflag != null){
	    	warNameflag.val(value);
	    	$("#war-modal").css('display', 'none')
	    }
	    /////////////////////////////////////////////////
	    
	    detailProvider.setValue(row, field, value);
	    $("#war-modal").css('display', 'none')

  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
  warAssistGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // warAssistGrid 속성값
  warAssistGrid.header.height = 40;
  warAssistGrid.footer.height = 40;
  warAssistGrid.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
  warAssistGrid.setEditOptions({
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
  createwarAssistGrid();
  warAssistGrid.footer.visible = false;
}

window.onload = start();





