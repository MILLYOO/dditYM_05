//모달 열기
$("#kcomm-modalM").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#kcomm-modalM").css('display', 'none')
		$("#kcomm-modalM").find(':input[name]').prop('value','');
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#kcomm-modalM").css('display', 'none')
	$("#kcomm-modalM").find(':input[name]').prop('value','');
})

//필드 컬럼 설정
var fieldsKcommM = [
		{ fieldName: "commCode", dataType: "text" }
	  , { fieldName: "commName", dataType: "text" }
	]
	
	var columnsKcommM = [
		{ name: "commCode", fieldName: "commCode", type: "data", width: "150", header: { text: "공정 코드" } ,"visible" : false }
		, { name: "commName", fieldName: "commName", type: "data", width: "326", header: { text: "공정명" } }
	]
	
	

//리얼그리드 공통부분
var kcommProviderM, kcommAssistGridM;

/* 			기본 셋팅 projAssistGrid	 	*/
function createkcommAssistGridM() {
	kcommProviderM = new RealGrid.LocalDataProvider();
	kcommAssistGridM = new RealGrid.GridView("realgrid_kcommM");

	kcommAssistGridM.setDataSource(kcommProviderM);
	kcommProviderM.setFields(fieldsKcommM);
	kcommAssistGridM.setColumns(columnsKcommM);
	kcommAssistGridM.resetCurrent();
	kcommAssistGridM.setCheckBar({
	  visible: false
  });
  
	if(masterGrid){
		//공정 field 를 찾아놓는다
		if(masterGrid.columnByName('kcommName')){
			  var fieldKcomm = masterGrid.columnByName('kcommName').dataIndex
		}
	}
	
  //코드도움창에서 더블클릭했을때
	kcommAssistGridM.onCellDblClicked = function (grid, clickData) {
	    var current = masterGrid.getCurrent();
	    var row = current.dataRow;
	    var field = current.fieldIndex;
	    var value = kcommAssistGridM.getValues(clickData.dataRow).commName;
	    
	    //현재 그리드의 커서가 거래처가 아니면 buyerCode 아이디를 찾아 value 값으로 준다
	   if(fieldKcomm != current.fieldIndex){
		   $(kcommObj).val(value)
//		  $("input[name=kcommName]").val(value)
		  $("#kcomm-modalM").css('display', 'none')
	   }else{
		   masterProvider.setValue(row, field, value);
		   $("#kcomm-modalM").css('display', 'none')
	   }
		   
  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	kcommAssistGridM.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // deptAssistGrid 속성값
	kcommAssistGridM.header.height = 40;
	kcommAssistGridM.footer.height = 40;
	kcommAssistGridM.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	kcommAssistGridM.setEditOptions({
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
  createkcommAssistGridM();
  kcommAssistGridM.footer.visible = false;
}

window.onload = start();





