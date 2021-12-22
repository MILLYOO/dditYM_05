//모달 열기
$("#emp-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#emp-modal").css('display', 'none')
		$("#emp-modal").find(':input[name]').prop('value','');
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#emp-modal").css('display', 'none')
	$("#emp-modal").find(':input[name]').prop('value','');
})

//필드 컬럼 설정
var fieldsEmp = [
		{ fieldName: "empCode", dataType: "text" }
	  , { fieldName: "empName", dataType: "text" }
	  , { fieldName: "deptName", dataType: "text" }
	]
	
	var columnsEmp = [
		{ name: "empCode", fieldName: "empCode", type: "data", width: "100", header: { text: "사원 코드" } ,"visible" : false }
		, { name: "empName", fieldName: "empName", type: "data", width: "210", header: { text: "사원명" } }
		, { name: "deptName", fieldName: "deptName", type: "data", width: "100", header: { text: "부서명" } }
	]
	


//리얼그리드 공통부분
var empProvider, empAssistGrid;

/* 			기본 셋팅 empAssistGrid	 	*/
function createempAssistGrid() {
  empProvider = new RealGrid.LocalDataProvider();
  empAssistGrid = new RealGrid.GridView("realgrid_emp");

  empAssistGrid.setDataSource(empProvider);
  empProvider.setFields(fieldsEmp);
  empAssistGrid.setColumns(columnsEmp);
  empAssistGrid.resetCurrent();
  empAssistGrid.setCheckBar({
	  visible: false
  });
  
  
  $("#empSearchForm").ajaxForm({
	  url : $.getContextPath() + "/info1/employeeManageRetrieve.do",
	  method : "post",
	  dataType:"json",
	  success:function(data){
		  empProvider.fillJsonData(data, {fillMode: "set"});
	  }
  }).submit();
  
  $("#empSearchBtn").on("click",function(){
	  
  //코드도움창에서 검색
	$("#empSearchForm").ajaxForm({
		url : $.getContextPath() + "/info1/employeeManageRetrieve.do",
		method : 'post',
		dataType:"json",
		success:function(data){
			console.log(data);
			empProvider.fillJsonData(data, {fillMode: "set"});
		},
		error : function(xhr, errorResp, error){
			console.log(xhr);
			console.log(errorResp);
			console.log(error);
		}
	}).submit();
  })
  
	
	if(masterGrid){
		//사원의 field 를 찾아놓는다
		if(masterGrid.columnByName('empName')){
			  var fieldEmp = masterGrid.columnByName('empName').dataIndex
			  
		}
	}
	
  //코드도움창에서 더블클릭했을때
	empAssistGrid.onCellDblClicked = function (grid, clickData) {
		var empSearch = $("#empSearch").length > 0 ? $(":input[name=empSearch]") : null;
		if(empSearch != null){
			console.log("empSearch:"+empSearch);
			var current = masterGrid.getCurrent();
			var row = current.dataRow;
			var field = current.fieldIndex;
			var value = empAssistGrid.getValues(clickData.dataRow).empName;

			//현재 그리드의 커서가 거래처가 아니면 buyerCode 아이디를 찾아 value 값으로 준다
			$(empObj).val(value)
			//  $("input[name=empName]").val(value)
			$("#emp-modal").css('display', 'none')
			gridSearch(value);
		}else{
			console.log("null일떄");
			var current = masterGrid.getCurrent();
			var row = current.dataRow;
			var field = current.fieldIndex;
			var value = empAssistGrid.getValues(clickData.dataRow).empName;

			//현재 그리드의 커서가 거래처가 아니면 buyerCode 아이디를 찾아 value 값으로 준다
			if(fieldEmp != current.fieldIndex){
				$(empObj).val(value)
//				$("input[name=empName]").val(value)
				$("#emp-modal").css('display', 'none');
			}else{
				masterProvider.setValue(row, field, value);
				$("#emp-modal").css('display', 'none');
			}

		}
  }

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
  empAssistGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // empAssistGrid 속성값
  empAssistGrid.header.height = 40;
  empAssistGrid.footer.height = 40;
  empAssistGrid.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
  empAssistGrid.setEditOptions({
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
  createempAssistGrid();
  empAssistGrid.footer.visible = false;
}

window.onload = start();





