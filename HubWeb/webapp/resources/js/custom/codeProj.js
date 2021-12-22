//모달 열기
$("#proj-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#proj-modal").css('display', 'none')
		$("#proj-modal").find(':input[name]').prop('value','');
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#proj-modal").css('display', 'none')
	$("#proj-modal").find(':input[name]').prop('value','');
})

//필드 컬럼 설정
var fieldsProj = [
		{ fieldName: "projCode", dataType: "text" }
	  , { fieldName: "projName", dataType: "text" }
	]
	
	var columnsProj = [
		{ name: "projCode", fieldName: "projCode", type: "data", width: "150", header: { text: "프로젝트 코드" } ,"visible" : false }
		, { name: "projName", fieldName: "projName", type: "data", width: "326", header: { text: "프로젝트명" } }
	]
	

//리얼그리드 공통부분
var projProvider, projAssistGrid;

/* 			기본 셋팅 projAssistGrid	 	*/
function createprojAssistGrid() {
	projProvider = new RealGrid.LocalDataProvider();
	projAssistGrid = new RealGrid.GridView("realgrid_proj");

	projAssistGrid.setDataSource(projProvider);
	projProvider.setFields(fieldsProj);
	projAssistGrid.setColumns(columnsProj);
	projAssistGrid.resetCurrent();
	projAssistGrid.setCheckBar({
	  visible: false
  });
  
	$("projSearchBtn").on("click",function(){
	  //코드도움창에서 검색
		$("#projSearchForm").ajaxForm({
		    url : $.getContextPath() + "/info1/projectRetrieve.do",
			method : 'post',
			dataType:"json",
			success:function(data){
				console.log(data);
				projProvider.fillJsonData(data, {fillMode: "set"});
			},
			error : function(xhr, errorResp, error){
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		}).submit();
	})
  
	
	if(detailGrid){
		//거래처의 field 를 찾아놓는다
		if(detailGrid.columnByName('projName')){
			  var fieldProj = detailGrid.columnByName('projName').dataIndex
		}
	}
	
	
  //코드도움창에서 더블클릭했을때
	projAssistGrid.onCellDblClicked = function (grid, clickData) {
	    var deptData = projAssistGrid.getValues(projAssistGrid.getCurrent().dataRow);
	   
	    var current = detailGrid.getCurrent();
	    var row = current.dataRow;
	    var field = current.fieldIndex;
	    var value = projAssistGrid.getValues(clickData.dataRow).projName;
	    
	    //현재 그리드의 커서가 프로젝트가 아니면 projName 찾아 value 값으로 준다
		   if(fieldProj != current.fieldIndex){
			  $(projObj).val(value);
//			  $("input[name=projName]").val(value)
			  $("#proj-modal").css('display', 'none')
		   }else{
			   detailProvider.setValue(row, field, value);
			    $("#proj-modal").css('display', 'none')
		   }
	}   
	    
	

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	projAssistGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // deptAssistGrid 속성값
	projAssistGrid.header.height = 40;
	projAssistGrid.footer.height = 40;
	projAssistGrid.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	projAssistGrid.setEditOptions({
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
  createprojAssistGrid();
  projAssistGrid.footer.visible = false;
}

window.onload = start();





