/*
 * codeBuyer.js
 * By 이원균_211202(최종수정)
 * 거래처 코드도움 모달창을 구성하는 js입니다.
 * 1. 모달창 열고 닫기
 * 2. 그리드 그리기
 * 3. 그리드 그리기 위한 객체 생성 및 설정
 * 4. 그리드 내부 이벤트
 * 5. 그리드 시작
 */

//========================================================================================
//1. 모달창 닫기

//모달 닫기(모달창 외에 화면 클릭 시)
$("#buyer-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#buyer-modal").css('display', 'none')
		$("#buyer-modal").find(':input[name]').prop('value','');
	}
})  
//모달 닫기(상단 X표 클릭 시)
$(".close-area").click(function(){
	$("#buyer-modal").css('display', 'none')
	$("#buyer-modal").find(':input[name]').prop('value','');
})


//========================================================================================
//2. 그리드 그리기
var fieldsBuyer = [
		{ fieldName: "buyerCode", dataType: "text" }
	  , { fieldName: "buyerName", dataType: "text" }
	  , { fieldName: "buyerRegnumber", dataType: "text" }
	  , { fieldName: "buyerCeo", dataType: "text" }
]
var columnsBuyer = [
	{ name: "buyerCode", fieldName: "buyerCode", type: "data", width: "100", header: { text: "거래처 코드" } ,"visible" : false }
	, { name: "buyerName", fieldName: "buyerName", type: "data", width: "210", header: { text: "거래처명" } }
	, { name: "buyerRegnumber", fieldName: "buyerRegnumber", type: "data", width: "187", header: { text: "사업자번호" } }
	, { name: "buyerCeo", fieldName: "buyerCeo", type: "data", width: "119", header: { text: "대표자" } }
]
	

//========================================================================================
//3. 그리드 그리기 위한 객체 생성 및 설정

// 그리드 변수 선언
var buyerProvider, buyerAssistGrid;
// 기본 셋팅 buyerAssistGrid
function createbuyerAssistGrid() {
	buyerProvider = new RealGrid.LocalDataProvider();
	buyerAssistGrid = new RealGrid.GridView("realgrid_buyer");

	buyerAssistGrid.setDataSource(buyerProvider);
	buyerProvider.setFields(fieldsBuyer);
	buyerAssistGrid.setColumns(columnsBuyer);
	buyerAssistGrid.resetCurrent();
	buyerAssistGrid.setCheckBar({
		visible : false
	});
	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	buyerAssistGrid.setDisplayOptions({
		emptyMessage : "표시할 데이타가 없습니다.",
		showEmptyMessage : true,
		rowHeight : 30
	});

	// buyerAssistGrid 속성값
	buyerAssistGrid.header.height = 40;
	buyerAssistGrid.footer.height = 40;
	buyerAssistGrid.stateBar.visible = false;

	// gridViewEditOptions 그리드뷰 edit 옵션
	buyerAssistGrid.setEditOptions({
		insertable : true,
		appendable : true,
		editable : false,
		deletable : true,
		confirmWhenDelete : true,
		deleteRowsMessage : "정말로 삭제 하시겠습니까?",
		enterToTab : true,
		forceAppend : true,
		fitStyle : "even"	
	})
  
// ========================================================================================
//4. 그리드 내부 이벤트
	// masterGrid 존재 시, 거래처의 field 를 찾아놓는다
	if (masterGrid) {
		if (masterGrid.columnByName("buyerName")) {
			var fieldBuyer = masterGrid.columnByName("buyerName").dataIndex
		}
	}
	// 코드도움창에서 더블클릭했을때
	buyerAssistGrid.onCellDblClicked = function(grid, clickData) {
		var current = masterGrid.getCurrent();
		var row = current.dataRow;
		var field = current.fieldIndex;
		var value1 = buyerAssistGrid.getValues(clickData.dataRow).buyerName;
		var value2 = buyerAssistGrid.getValues(clickData.dataRow).buyerCode;
		
		// 현재 그리드의 커서가 거래처가 아니면 buyerCode 아이디를 찾아 value 값으로 준다
		if (fieldBuyer != current.fieldIndex) {
			$(buyerObj).val(value1)
			// $("input[name=buyerName]").val(value1)
			$("#buyer-modal").css("display", "none")
		} else {
			masterProvider.setValue(row, field, value1);
			masterProvider.setValue(row, field - 1, value2);
			$("#buyer-modal").css('display', 'none')
		}
	}
	$("#buyerSearchForm").ajaxForm({
		url : $.getContextPath() + "/info1/buyerRetrieve.do",
		method : "post",
		dataType : "json",
		success : function(data) {
			buyerProvider.fillJsonData(data, {
				fillMode : "set"
			});
		}
	}).submit();
	
	// 코드도움창에서 검색
	$("#buyerSearchFormBtn").on("click", function() {
		$("#buyerSearchForm").ajaxForm({
			url : $.getContextPath() + "/info1/buyerRetrieve.do",
			method : 'post',
			dataType : "json",
			success : function(data) {
				buyerProvider.fillJsonData(data, {
					fillMode : "set"
				});
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		}).submit();
	})
}


//========================================================================================
//5. 그리드 시작
function start() {
  createbuyerAssistGrid();
  buyerAssistGrid.footer.visible = false;
}

window.onload = start();




