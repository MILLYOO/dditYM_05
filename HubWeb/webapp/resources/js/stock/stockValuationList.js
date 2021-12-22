/**
 * 1. 재고평가 리스트 띄우기
 * 2. 리스트에서 클릭 시, 해당 정보가 masterGrid에 반영된다.
 * 3. 리스트는 stockValuation-modal 에 띄워준다.
 * 4. grid 이름은 stockValGrid 로 한다
 */
//재고평가 리스트 그리드 모달
//모달 열기
$("#stockValuation-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#stockValuation-modal").css("display", "none")
	}
});  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#stockValuation-modal").css('display', 'none')
})

//재고평가 진행시 날짜입력하는 모달
//모달 열기
$("#stockValInsert-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#stockValInsert-modal").css("display", "none")
	}
});  

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#stockValInsert-modal").css('display', 'none')
})

//필드 컬럼 설정
var fieldsStockVal = [
		{ fieldName: "stvNum", dataType: "text" }
	  ,	{ fieldName: "stvStart", dataType: "date" }
	  , { fieldName: "stvEnd", dataType: "date" }
	  , { fieldName: "stvDate", dataType: "date" }
	  , { fieldName: "deptName", dataType: "text" }
	  , { fieldName: "empName", dataType: "text" }
	  , { fieldName: "stvCheck", dataType: "text" }
	]
	
var columnsStockVal = [
	  { name: "stvNum", fieldName: "stvNum", type: "data", width: "230", header: { text: "재고평가번호" }, visible:false }
	, { name: "stvStart", fieldName: "stvStart", type: "data", width: "230", header: { text: "시작년월" }
		, editor: {datetimeFormat: "yyyy-MM-dd"} }
	, { name: "stvEnd", fieldName: "stvEnd", type: "data", width: "160", header: { text: "마감년월" }
		, editor: {datetimeFormat: "yyyy-MM-dd"} }
	, { name: "stvDate", fieldName: "stvDate", type: "data", width: "160", header: { text: "평가일" }
		, editor: {datetimeFormat: "yyyy-MM-dd"} }
	, { name: "deptName", fieldName: "deptName", type: "data", width: "160", header: { text: "부서명" } }
	, { name: "empName", fieldName: "empName", type: "data", width: "160", header: { text: "담당자" } }
	, { name: "stvCheck", fieldName: "stvCheck", type: "data", width: "160", header: { text: "평가진행상태" } }
	]


//리얼그리드 공통부분
var stockValProvider, stockValGrid;

/* 			기본 셋팅 obApplyMaster	 	*/
function createstockValProviderGrid() {
	
	stockValProvider = new RealGrid.LocalDataProvider();
	stockValGrid = new RealGrid.GridView("realgrid_stockVal");
	stockValGrid.setCheckBar({visible: false});
	stockValGrid.setDataSource(stockValProvider);
	stockValProvider.setFields(fieldsStockVal);
	stockValGrid.setColumns(columnsStockVal);
	stockValGrid.resetCurrent();
	stockValGrid.stateBar.visible = false;

  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	stockValGrid.setDisplayOptions({
	  emptyMessage : "표시할 데이타가 없습니다."
	  , showEmptyMessage: true
	  , rowHeight : 30
	  , fitStyle : "even"
	  });
  
  // obApplyMasterGrid 속성값
	stockValGrid.header.height = 40;
	stockValGrid.footer.height = 40;
	stockValGrid.stateBar.width = 10;
  
  // gridViewEditOptions 그리드뷰 edit 옵션
	stockValGrid.setEditOptions({
	  insertable : true
	  , appendable : true
	  , editable : false
	  , deletable: true
	  , confirmWhenDelete : true
	  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  , enterToTab : true
	  ,forceAppend:true
  });
  
}  



function start() {
	createstockValProviderGrid();
}

window.onload = start();



//---------------------------------------------------------------------------


	//재고평가 조회를 위해 날짜 입력 후 조회 클릭 시 모달창이 뜬다.
	//모달창에는 입력한 날짜를 바탕으로 조회된다.
	$("#searchValuModal").on("click",function(){
		var startDateModal = $("#dateStartValuModal").val();
		var endDateModal = $("#dateEndValuModal").val();
		
		// 날짜 지정하지 않았을 때
		if(startDateModal.replace(/\s/g,"").length==0){
			Swal.fire("날짜를 선택하세요");
			return false;
		}
		
		if(endDateModal >= startDateModal){
			let searchForm = $("#searchForm").ajaxForm({
				url : $.getContextPath() + "/stock/inventoryValuationRetrieveList.do",
				method : "post",
				dataType : "json",
				success : function(resp) {
					stockValProvider.fillJsonData(resp, {fillMode: "set"});
				}
			}).submit();
		}else{
			alert("종료일이 시작일보다 이전 날짜 입니다.");
			return false;
		}
		
	});

	
	//코드도움창에서 더블클릭했을때
	stockValGrid.onCellDblClicked = function (grid, clickData) {
		//선택한 재고평가 문서의 번호를 추출, 
		var stvNum = stockValGrid.getValues(clickData.dataRow).stvNum;
		console.log(grid)
		console.log(clickData)
		console.log(stvNum)
		//문서번호를 이용해 데이터를 가져온다
		$.ajax({
			url : $.getContextPath()+ "/stock/inventoryValuationItemRetrieve.do",
			data : {"stvNum" : stvNum},
			method : "post",
			dataType : "json",
			success : function(resp) {
				//재고평가서-품목 정보를 끌어와 데이터를 넣어준다. 마스터그리드에 결과 적용
				console.log("resp"+resp)
				$("#stvNum").val(resp.stvNum);
			    masterProvider.fillJsonData(resp.valuationList, {fillMode: "set"});
				//모달창 끄기
			    $("#stockValuation-modal").css('display', 'none')
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	}
	
	





