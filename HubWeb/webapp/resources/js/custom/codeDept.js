//모달 열기
$("#dept-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#dept-modal").css('display', 'none')
		$("#dept-modal").find(':input[name]').prop('value','');
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#dept-modal").css('display', 'none')
	$("#dept-modal").find(':input[name]').prop('value','');
})

//필드 컬럼 설정
var fieldsDept = [
		{ fieldName: "deptCode", dataType: "text" }
	  , { fieldName: "deptName", dataType: "text" }
	]
	
	var columnsDept = [
		{ name: "deptCode", fieldName: "deptCode", type: "data", width: "100", header: { text: "부서 코드" } ,"visible" : false }
		, { name: "deptName", fieldName: "deptName", type: "data", width: "326", header: { text: "부서명" } }
	]
	
	

//리얼그리드 공통부분
var deptProvider, deptAssistGrid;

/* 			기본 셋팅 deptAssistGrid	 	*/
function createdeptAssistGrid() {
  deptProvider = new RealGrid.LocalDataProvider();
  deptAssistGrid = new RealGrid.GridView("realgrid_dept");

  deptAssistGrid.setDataSource(deptProvider);
  deptProvider.setFields(fieldsDept);
  deptAssistGrid.setColumns(columnsDept);
  deptAssistGrid.resetCurrent();
  deptAssistGrid.setCheckBar({
	  visible: false
  });

  $("#deptSearchForm").ajaxForm({
	  url : $.getContextPath() + "/info1/departmentManageRetrieve.do",
	  method : "post",
	  dataType:"json",
	  success:function(data){
		  deptProvider.fillJsonData(data, {fillMode: "set"});
	  }
  }).submit();	
  
  $("#deptSearchBtn").on("click",function(){
  //코드도움창에서 검색
	$("#deptSearchForm").ajaxForm({
		url : $.getContextPath() + "/info1/departmentManageRetrieve.do",
		method : 'post',
		dataType:"json",
		success:function(data){
			console.log(data);
			deptProvider.fillJsonData(data, {fillMode: "set"});
		},
		error : function(xhr, errorResp, error){
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	}).submit();
  })
	
	if(masterGrid){
		//부서 field 를 찾아놓는다
		if(masterGrid.columnByName('deptName')){
			var fieldDept = masterGrid.columnByName('deptName').dataIndex
		}
	}
  //코드도움창에서 더블클릭했을때
  deptAssistGrid.onCellDblClicked = function (grid, clickData) {
	    console.log("onCellDblClicked: " + JSON.stringify(clickData));
	    console.log(deptAssistGrid.getValues(clickData.dataRow).deptName);
	    console.log(deptAssistGrid.getValues(clickData.dataRow));
	    var deptData = deptAssistGrid.getValues(deptAssistGrid.getCurrent().dataRow);
	   
	    var current = masterGrid.getCurrent();
	    var row = current.dataRow;
	    var field = current.fieldIndex;
	    var value = deptAssistGrid.getValues(clickData.dataRow).deptName;

	    
	    if(fieldDept != current.fieldIndex){
	    	$(deptObj).val(value)
//			  $("input[name=deptName]").val(value)
			  $("#dept-modal").css('display', 'none')
		}else{
		    masterProvider.setValue(row, field, value);
		    $("#dept-modal").css('display', 'none')
		}

  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
  deptAssistGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // deptAssistGrid 속성값
  deptAssistGrid.header.height = 40;
  deptAssistGrid.footer.height = 40;
  deptAssistGrid.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
  deptAssistGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : false
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  , enterToTab : true
	  , forceAppend : true
	  , fitStyle : "even"	
  })
}


function start() {
  createdeptAssistGrid();
  deptAssistGrid.footer.visible = false;
}

window.onload = start();