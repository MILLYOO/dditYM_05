//모달 열기
$("#inOutReq-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#inOutReq-modal").css('display', 'none')
		$("#inOutReq-modal").find(':input[name]').prop('value','');
	}
})  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#inOutReq-modal").css('display', 'none')
	$("#inOutReq-modal").find(':input[name]').prop('value','');
})

//필드 컬럼 설정
var fieldsInOutReq = [
	   { fieldName: "stotaNo", dataType: "text" }
	   , { fieldName: "stotaDate", dataType: "text" }
	  , { fieldName: "warName", dataType: "text" }
	  , { fieldName: "prodName", dataType: "text" }
	  , { fieldName: "stotaQty", dataType: "text" }
	  , { fieldName: "stotaSend", dataType: "text" }
	  , { fieldName: "stotaRecv", dataType: "text" }
	  , { fieldName: "stotaMemo", dataType: "text" }
	]
	
	var columnsInOutReq = [
		 { name: "stotaNo", fieldName: "stotaNo", type: "data", width: "60", header: { text: "번호" }, visible:false }
		 , { name: "stotaDate", fieldName: "stotaDate", type: "data", width: "90", header: { text: "일시" } }
		, { name: "warName", fieldName: "warName", type: "data", width: "90", header: { text: "창고명" } }
		, { name: "prodName", fieldName: "prodName", type: "data", width: "405", header: { text: "품목명" } }
		, { name: "stotaQty", fieldName: "stotaQty", type: "data", width: "80", header: { text: "실사수량" } }
		, { name: "stotaSend", fieldName: "stotaSend", type: "data", width: "70", header: { text: "발신자" } }
		, { name: "stotaRecv", fieldName: "stotaRecv", type: "data", width: "70", header: { text: "수신자" } }
		, { name: "stotaMemo", fieldName: "stotaMemo", type: "data", width: "70", header: { text: "비고" } }
	]
	


//리얼그리드 공통부분
var inOutReqProvider, inOutReqGrid;

/* 			기본 셋팅 buyerAssistGrid	 	*/
function createinOutReqGrid() {
	inOutReqProvider = new RealGrid.LocalDataProvider();
	inOutReqGrid = new RealGrid.GridView("realgrid_inOutReq");

	inOutReqGrid.setDataSource(inOutReqProvider);
	inOutReqProvider.setFields(fieldsInOutReq);
	inOutReqGrid.setColumns(columnsInOutReq);
	inOutReqGrid.resetCurrent();
	inOutReqGrid.setCheckBar({
	  visible: true
  });
  
  
$("#deleteInOutReq").on("click",function(){
	var rows;
	var data;
	rows = inOutReqGrid.getCheckedItems();
	let checkedRows = inOutReqGrid.getCheckedRows();
	if(checkedRows.length > 0){
		if(rows){
			$("#inOutReq-modal").css('display', 'none')
			Swal.fire({
				title: "확인하셨습니까?",
				text: "내역을 다시 확인할 수 없습니다",
				icon: "warning",
				showCancelButton: true,
				confirmButtonColor: "#3085d6",
				cancelButtonColor: "#d33",
				confirmButtonText: "승인",
				cancelButtonText: "취소"
			}).then((result) => {
				if (result.isConfirmed) {
					var dataList = [];	// 체크된 행 데이터 넣을 배열
					for(var i = rows.length ; i >= 0 ; i--){
						if(inOutReqProvider.getRowState(rows[i]) == "created"){
							inOutReqProvider.removeRow(rows[i]);
							continue;
						}else {
							data = inOutReqGrid.getValues(checkedRows[i]);
							if(data == null) continue;
							dataList.push(data);
						}	// else
					}	// for
					let obj = {"commonList":dataList}
					$.ajax({
						url : $.getContextPath()+"/stock/stockTakingYNUpdate.do",
						data : JSON.stringify(obj),
						method : "post",
						dataType : "json",
						contentType : "application/json",
						success : function(resp) {
							if(resp.result == 'OK'){
								inOutReqProvider.removeRows(checkedRows);
								toastr.success(resp.message);
								$("#inOutReq-modal").css('flex', 'none')
							}else{
								for(var idx in resp.message){
									console.log(resp.message)
									toastr.error(resp.message[idx]);
								}
								$("#inOutReq-modal").css('flex', 'none')
							}	
						},
						error : function(xhr, errorResp, error) {
							console.log(xhr);
							console.log(errorResp);
							console.log(error);
						}
					});
				}//for문끝
			});
		};
	}else{
		Swal.fire("체크해주세요");
	}
})

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	inOutReqGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  });
  
  // buyerAssistGrid 속성값
	inOutReqGrid.header.height = 40;
	inOutReqGrid.footer.height = 40;
	inOutReqGrid.stateBar.visible = false;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	inOutReqGrid.setEditOptions({
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
  createinOutReqGrid();
  inOutReqGrid.footer.visible = false;
}

window.onload = start();




