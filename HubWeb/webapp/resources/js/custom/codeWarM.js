//모달 열기
$("#war-modalM").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#war-modalM").css('display', 'none')
		$("#war-modalM").find(':input[name]').prop('value','');
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#war-modalM").css('display', 'none')
	$("#war-modalM").find(':input[name]').prop('value','');
})

//필드 컬럼 설정
var fieldsWarM = [
		{ fieldName: "warCode", dataType: "text" }
	  , { fieldName: "warName", dataType: "text" }
	]
	
	var columnsWarM = [
		{ name: "warCode", fieldName: "warCode", type: "data", width: "100", header: { text: "창고 코드" } ,"visible" : false }
		, { name: "warName", fieldName: "warName", type: "data", width: "326", header: { text: "창고명" } }
	]
	


//리얼그리드 공통부분
var warProviderM, warAssistGridM;

/* 			기본 셋팅 empAssistGrid	 	*/
function createwarAssistGridM() {
  warProviderM = new RealGrid.LocalDataProvider();
  warAssistGridM = new RealGrid.GridView("realgrid_warM");

  warAssistGridM.setDataSource(warProviderM);
  warProviderM.setFields(fieldsWarM);
  warAssistGridM.setColumns(columnsWarM);
  warAssistGridM.resetCurrent();
  warAssistGridM.setCheckBar({
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
			warProviderM.fillJsonData(data, {fillMode: "set"});
		},
		error : function(xhr, errorResp, error){
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	}).submit();
  })
	
	if(masterGrid){
		//창고 field 를 찾아놓는다
		if(masterGrid.columnByName('warName')){
			  var fieldWar = masterGrid.columnByName('warName').dataIndex
		}
	}
	
  //코드도움창에서 더블클릭했을때
  warAssistGridM.onCellDblClicked = function (grid, clickData) {
	    var current = masterGrid.getCurrent();
	    var row = current.dataRow;
	    var field = current.fieldIndex;
	    var value = warAssistGridM.getValues(clickData.dataRow).warName;
	    
	    //현재 그리드의 커서가 창고가 아니면 warName 아이디를 찾아 value 값으로 준다
		   if(fieldWar != current.fieldIndex){
			  $(warObj).val(value)
//			  $("input[name=warName]").val(value)
			  $("#war-modalM").css('display', 'none')
		   }else{
			   masterGrid.setValue(row, field, value);
			    $("#war-modalM").css('display', 'none')
		   }

	    
	    // input태그로 창고 검색하기 위한 부분
	    var warNameflag = $("#warName");
	    if(warNameflag != null){
	    	warNameflag.val(value);
	    	$("#war-modal").css('display', 'none')
	    }
	    /////////////////////////////////////////////////
	    
	    masterProvider.setValue(row, field, value);
	    $("#war-modalM").css('display', 'none')

  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
  warAssistGridM.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // warAssistGrid 속성값
  warAssistGridM.header.height = 40;
  warAssistGridM.footer.height = 40;
  warAssistGridM.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
  warAssistGridM.setEditOptions({
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
  createwarAssistGridM();
  warAssistGridM.footer.visible = false;
}

window.onload = start();





