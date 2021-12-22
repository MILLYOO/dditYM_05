/**
 * 생산품입고처리서 js 
 */
// 이달 1일 ~ 현재 날짜 세팅
var date = new Date();
$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
$("#dateEnd").val(new Date().toISOString().substring(0, 10));

var fields = [
    { fieldName: "pdcYy", dataType: "number" }
  , { fieldName: "pdcMm", dataType: "number" }
  , { fieldName: "pdcDd", dataType: "number" }
  , { fieldName: "arrNum", dataType: "text" }
  , { fieldName: "buyerCode", dataType: "number" }
  , { fieldName: "buyerName", dataType: "text" }
  , { fieldName: "deptName", dataType: "text" }
  , { fieldName: "empName", dataType: "text" }
];

var columns = [
    { name: "pdcYy", fieldName: "pdcYy", type: "data", width: "60", numberFormat: "###0", header: { text: "년" } }
  , { name: "pdcMm", fieldName: "pdcMm", type: "data", width: "30", numberFormat: "###0", header: { text: "월" } }
  , { name: "pdcDd", fieldName: "pdcDd", type: "data", width: "30", numberFormat: "###0", header: { text: "일" } }
  , { name: "arrNum", fieldName: "arrNum", type: "data", width: "150", header: { text: "생산입고번호" } , "editable" : false }
  , { name: "buyerCode", fieldName: "buyerCode", type: "data", width: "120", numberFormat: "###0", header: { text: "거래처코드" } , "editable" : false, "visible" : false }
  , { name: "buyerName", fieldName: "buyerName", type: "data", width: "120", header: { text: "거래처" } , button:"action" , "editable" : false }
  , { name: "deptName", fieldName: "deptName", type: "data", width: "120", header: { text: "부서" }, button:"action" , "editable" : false }  
  , { name: "empName", fieldName: "empName", type: "data", width: "120", header: { text: "사원" }, button:"action" , "editable" : false } 
];

var fields1 = [
    { fieldName: "arrNo", dataType: "number" }	
  , { fieldName: "arrNum", dataType: "text" }	
  , { fieldName: "icodeName", dataType: "text" }
  , { fieldName: "prodCode", dataType: "text" }
  , { fieldName: "prodName", dataType: "text" }
  , { fieldName: "gcommName", dataType: "text" }
  , { fieldName: "ppQty", dataType: "number" }
  , { fieldName: "kcommName", dataType: "text" }
  , { fieldName: "warName", dataType: "text" }
  , { fieldName: "projName", dataType: "text" }
  , { fieldName: "instNum", dataType: "text" }
  , { fieldName: "ppBadnum", dataType: "number" }
]

var columns1 = [
	  { name: "arrNo", fieldName: "arrNo", type: "data", width: "80", numberFormat: "#,##0", header: { text: "생산품입고처리서-제품 번호" } , "visible" : false }
	, { name: "arrNum", fieldName: "arrNum", type: "data", width: "80", header: { text: "생산입고번호" }, "editable" : false , "visible" : false }
	, { name: "icodeName", fieldName: "icodeName", type: "data", width: "60", header: { text: "품목계정" }, "editor": {
		"type": "dropdown",
		"dropDownCount" : 7,
		"domainOnly" : true,
		"commitOnSelect" : true,
		"dropDownWhenClick" : true,
		"lookupDisplay": true,
		"values": ["제품","반제품"],
		"labels": ["제품","반제품"]
	} }
	, { name: "prodCode", fieldName: "prodCode", type: "data", width: "60", header: { text: "품목코드" } , button:"action" , "editable" : false } 
	, { name: "prodName", fieldName: "prodName", type: "data", width: "300", header: { text: "품명" } , styleName: "left-column"}
	, { name: "gcommName", fieldName: "gcommName", type: "data", width: "120", header: { text: "규격" } , button:"action" , "editable" : false } 
	, { name: "ppQty", fieldName: "ppQty", type: "data", width: "50", numberFormat: "#,##0", header: { text: "생산량" }, footer: {expression : "sum", numberFormat: "#,##0" } , styleName: "right-column"}
	, { name: "kcommName", fieldName: "kcommName", type: "data", width: "80", header: { text: "공정" } , button:"action" , "editable" : false } 
	, { name: "warName", fieldName: "warName", type: "data", width: "120", header: { text: "입고창고" } , button:"action" , "editable" : false } 
	, { name: "projName", fieldName: "projName", type: "data", width: "160", header: { text: "프로젝트명" } , button:"action" , "editable" : false } 
	, { name: "instNum", fieldName: "instNum", type: "data", width: "100", header: { text: "생산지시서번호" } ,"editable" : false } 
	, { name: "ppBadNum", fieldName: "ppBadnum", type: "data", width: "60",numberFormat: "#,##0",  header: { text: "불량수" },footer: {expression : "sum", numberFormat: "#,##0" } , styleName: "right-column"}
]

//---------------------------------------------지시서적용 --------------------------------------

arrNumData = null;

//지시서 적용 모달 나와라~!
$("#piApply").on("click",function(){
	masterGrid.commit();
	initPiAppPw();
    let checkedRows = masterGrid.getCheckedRows();
    if(checkedRows.length == 1){
    	masterProvider.setRowStates(checkedRows, "none", true);
        arrNumData = masterGrid.getValues(checkedRows[0]);
    	$("#piApplyForPW-modal").css("display", "flex")
    }else if (checkedRows.length > 1){
    	toastr.error("하나만 선택해주세요");
    }else{
    	toastr.error("체크해주세요");
    }
});

//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#piApplyForPW-modal").css("display", "none")
	$("#stockForPI-modal").css("display", "none")
});

//---------------------------------------------지시서적용 끝--------------------------------------

//현재고 모달
$("#selectStockStatus").on("click",function(){
	$(function () {
		$("#stockForPI-modal").css("display", "flex")
	});
});

//검색 모달 띄우기
//거래처 
$("#buyerName").focus(function(e){
 $("#buyer-modal").css("display", "flex")
 buyerObj = this;
});

//부서 검색 모달 띄우기
$("#deptName").focus(function(e){
 $("#dept-modal").css("display", "flex")
 deptObj = this;
});

//사원 검색 모달 띄우기
$("#empName").focus(function(e){
	$("#emp-modal").css("display", "flex")
	empObj = this;
});


//생산품입고처리서 목록 조회
$("#select1").on("click",function(){
	masterGrid.cancel();
	
	var startDate = $("#dateStart").val();
	var endDate = $("#dateEnd").val();
	
	// 날짜 지정하지 않았을 때
	if(startDate.replace(/\s/g,"").length==0){
		toastr.error("날짜를 선택하세요");
		return false;
	}
	
	if(endDate >= startDate){
		let searchForm = $("#searchForm").ajaxForm({
			dataType : "json",
			success : function(resp) {
				masterProvider.fillJsonData(resp, {fillMode: "set"});
			}
		}).submit();
	}else{
		toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
});
$("#select3").on("click",function(){
	masterGrid.cancel();
	
	var startDate = $("#dateStart").val();
	var endDate = $("#dateEnd").val();
	
	// 날짜 지정하지 않았을 때
	if(startDate.replace(/\s/g,"").length==0){
		toastr.error("날짜를 선택하세요");
		return false;
	}
	
	if(endDate >= startDate){
		let searchForm = $("#searchForm").ajaxForm({
			dataType : "json",
			success : function(resp) {
				masterProvider.fillJsonData(resp, {fillMode: "set"});
			}
		}).submit();
	}else{
		toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
		return false;
	}
});

//생산품입고처리서-제품 목록 조회 (생산품입고처리서 클릭 시)	
function detailGet(masterRow) {
	  detailProvider.clearRows();
	  if (masterRow >= 0) {
	      var mstKey = masterProvider.getValue(masterRow, "arrNum");
	     $.ajax({
			url : $.getContextPath()+"/produce/productIncomingRawsList.do",
			data : {"arrNum":mstKey},
			method : "post",
			dataType : "json",
			success : function(resp) {
			  detailProvider.fillJsonData(resp, {fillMode: "set"});
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	  };
};

//생산품입고처리서 추가
$("#append").on("click", function(){
	masterGrid.cancel();
	var date = new Date();
	var count = masterProvider.getRowStateCount("created");
      if(count > 0){
         toastr.error("추가된 행을 저장해주세요");
      }else{
         var values = [];
         for(var i = 0 ; i < fields.length ; i++){
            values.push = "";
         }
         values[0] = date.getFullYear();
         values[1] = date.getMonth()+1;
         values[2] = date.getDate();
         masterProvider.addRow(values);
      }
});

//생산품입고처리서-원자재 추가
$("#append1").on("click", function(){
	detailGrid.cancel();
	masterGrid.cancel();
	var values = [];
    for(var i = 0 ; i < fields1.length ; i++){
       values.push = "";
    }
    var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
    values[1] = masterData.arrNum;
    detailProvider.addRow(values);
	detailGrid.commit();
	detailGrid.showEditor();
	detailGrid.setFocus();
});


// 생산품입고처리서 저장(등록+수정)
$("#saved").on("click", function(){
	masterGrid.commit();
    let checkedRows = masterGrid.getCheckedRows();
    if(checkedRows.length > 0){
    	masterProvider.setRowStates(checkedRows, "none", true);
    	var dataList = [];	// 체크된 행 데이터 넣을 배열
	    for(var checked = 0 ;  checked < checkedRows.length ; checked++){
            data = masterGrid.getValues(checkedRows[checked]);
            
			var lastDate = new Date(data.pdcYy, data.pdcMm, 0).getDate();
			
			if(data.pdcYy >= 2023 || data.pdcYy <= 2020){
				toastr.error("해당 연도 입력 불가");
				return false;
			}else if(data.pdcMm < 1 || data.pdcMm > 12){
				toastr.error("입력할 수 없는 달입니다");
				return false;
			}else if(data.pdcDd < 1 || data.pdcDd > lastDate){
				toastr.error("입력한 달의 마지막 일을 확인하세요");
				return false;
			}
			
            dataList.push(data);
	    }
	    let obj = {"commonList":dataList}
    	$.ajax({
			url : $.getContextPath()+"/produce/productIncomingInsert.do",
			data : JSON.stringify(obj),
			method : "post",
			dataType : "json",
			contentType : "application/json",
			success : function(resp) {
				if(resp.result == "OK"){
					toastr.success(resp.message);
					let searchForm = $("#searchForm").ajaxForm({
						dataType : "json",
						success : function(resp) {
							masterProvider.fillJsonData(resp, {fillMode: "set"});
						}
					}).submit();
				}else{
					if(Array.isArray(resp.message)){
						for(var idx in resp.message){
							toastr.error(resp.message[idx]);
						}
					}else{
						toastr.error(resp.message);
					}
				}	
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
    }else{
		toastr.error("체크해주세요");
	}	// 체크된 행 갯수 if문 끝
});


// 생산품입고처리서-품목 저장
$("#saved1").on("click", function(){
	detailGrid.commit();
	let checkedRows = detailGrid.getCheckedRows();
	if(checkedRows.length > 0){
		detailProvider.setRowStates(checkedRows, "none", true);
		var dataList = [];	// 체크된 행 데이터 넣을 배열
		for(var checked = 0 ;  checked < checkedRows.length ; checked++){
			data = checkedRows[checked];
			var insertData = detailGrid.getValues(data);
			var icdName = insertData.icodeName;
			var arrNum = insertData.arrNum;
			if(icdName != "제품" && icdName != "반제품"){
				toastr.error("제품 및 반제품만 등록 가능합니다.");
				return false;
			}
			dataList.push(insertData);
		}
		let obj = {"commonList":dataList}
		$.ajax({
			url : $.getContextPath()+"/produce/productIncomingRawsInsert.do",
			data : JSON.stringify(obj),
			method : "post",
			dataType : "json",
			contentType : "application/json",
			success : function(resp) {
				if(resp.result == "OK"){
					toastr.success(resp.message);
					$.ajax({
						url : $.getContextPath()+"/produce/productIncomingRawsList.do",
						data : {"arrNum":arrNum},
						method : "post",
						dataType : "json",
						success : function(resp) {
							detailProvider.fillJsonData(resp, {fillMode: "set"});
						}
					});
				}else{
					if(Array.isArray(resp.message)){
						for(var idx in resp.message){
							toastr.error(resp.message[idx]);
						}
					}else{
						toastr.error(resp.message);
					}
				}	
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		});
	}else{
		toastr.error("체크해주세요");
	}
});


// 생산품입고처리서 삭제
$("#delete").on("click", function(){
	masterGrid.commit();
	var rows;
	var data;
	rows = masterGrid.getCheckedItems();
	let checkedRows = masterGrid.getCheckedRows();
    if(checkedRows.length > 0){
    	if(rows){
    		Swal.fire({
    			title: "정말로 삭제하시겠습니까?",
    			text: "다시 한 번 확인해주세요",
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
    					if(masterProvider.getRowState(rows[i]) == "created"){
    						masterProvider.removeRow(rows[i]);
    						continue;
    					}else {
    						data = masterGrid.getValues(checkedRows[i]);
    						if(data == null) continue;
    			            dataList.push(data);
    					}	// else
    				}	// for
					$.ajax({
						url : $.getContextPath()+"/produce/productIncomingDelete.do",
						data : JSON.stringify(dataList),
						method : "post",
						dataType : "text",
						contentType : "application/json",
						success : function(resp) {
							if(resp=="성공"){
								Swal.fire(
										"삭제가 완료되었습니다.",
										"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
										"success"
								);
								let searchForm = $("#searchForm").ajaxForm({
									dataType : "json",
									success : function(resp) {
										masterProvider.fillJsonData(resp, {fillMode: "set"});
									}
								}).submit();
							}else{
								toastr.error(resp);
							}
						},
						error : function(xhr, errorResp, error) {
							console.log(xhr);
							console.log(errorResp);
							console.log(error);
						}
					});
    			}	//if 
    		})	//Swal.fire
    	}
    }else{
		toastr.error("체크해주세요");
	}
});


// 생산품입고처리-품목 삭제
$("#delete1").on("click", function(){
	detailGrid.commit();
	var rows = detailGrid.getCheckedItems();
	var data;
	let checkedRows = detailGrid.getCheckedRows();
    if(checkedRows.length > 0){
    	if(rows){
    		Swal.fire({
    			title: "정말로 삭제하시겠습니까?",
    			text: "다시 한 번 확인해주세요",
    			icon: "warning",
    			showCancelButton: true,
    			confirmButtonColor: "#3085d6",
    			cancelButtonColor: "#d33",
    			confirmButtonText: "승인",
    			cancelButtonText: "취소"
    		}).then((result) => {
    			if (result.isConfirmed) {
    				var dataList = [];	// 체크된 행 데이터 넣을 배열
    				for(var i = rows.length-1 ; i >= 0 ; i--){
    					if(detailProvider.getRowState(rows[i]) == "created"){
    						detailProvider.removeRow(rows[i]);
    						continue;
    					}else {
    						data = detailGrid.getValues(checkedRows[i]);
    						if(data == null) continue;
    						dataList.push(data);
    						arrNum = data.arrNum;
    					}	// else
    				}	// for
					$.ajax({
						url : $.getContextPath()+"/produce/productIncomingRawsDelete.do",
						data : JSON.stringify(dataList),
						method : "post",
						dataType : "text",
						contentType : "application/json",
						success : function(resp) {
							if(resp=="성공"){
								Swal.fire(
										"삭제가 완료되었습니다.",
										"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
										"success"
								);
							    $.ajax({
										url : $.getContextPath()+"/produce/productIncomingRawsList.do",
										data : {"arrNum":arrNum},
										method : "post",
										dataType : "json",
										success : function(resp) {
										  detailProvider.fillJsonData(resp, {fillMode: "set"});
										},
										error : function(xhr, errorResp, error) {
											console.log(xhr);
											console.log(errorResp);
											console.log(error);
										}
								});
							}else{
								toastr.error(resp);
							}
						},
						error : function(xhr, errorResp, error) {
							console.log(xhr);
							console.log(errorResp);
							console.log(error);
						}
					});	//ajax
    			}	// if
    		})	// swal
    	} // if
    }else{
		toastr.error("체크해주세요");
	}
});

$(function(){
	masterGrid.stateBar.visible = false;
	detailGrid.stateBar.visible = false;
	masterGrid.footer.visible = false;
});