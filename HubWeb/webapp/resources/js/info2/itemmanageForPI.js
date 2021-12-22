/**
 * 엑셀 임포트 적용 모달
 */

//excel Import Modal======================================================================
	$("#excelImportBtn").on("click",function(){
		//모달 보여지게 하기
		$("#excelImport-modal").css('display', 'flex')
	})
	
	//모달 닫기
	$("#excelImport-modal").click(function(e){
		const evTarget = e.target
		if(evTarget.classList.contains("modal-overlay")) {
			$("#excelImport-modal").css('display', 'none')
		}
	})  
	
	//x표 눌러서 모달 닫기
	$(".close-area").click(function(){
		$("#excelImport-modal").css('display', 'none')
	})
	

var excelImportProvider, excelImportGrid;

var excelImportfields = [ 
	{fieldName : "icodeName",dataType : "text"},
	{fieldName : "rawsCode", dataType : "text"},
	{fieldName : "rawsName",dataType : "text"}
];

var excelImportcolumns = [ 
	    { name: "icodeName", fieldName: "icodeName", type: "data", width: "30", header: { text: "품목계정" } }
	  , { name: "rawsCode", fieldName: "rawsCode", type: "data", width: "30", header: { text: "품목코드" } }
	  , { name: "rawsName", fieldName: "rawsName", type: "data", width: "30", header: { text: "품목명" } }
];
	
function createExcelImportGrid() {
	excelImportProvider = new RealGrid.LocalDataProvider();
	excelImportGrid = new RealGrid.GridView("realgrid_excelImport");

	excelImportGrid.setDataSource(excelImportProvider);
	excelImportProvider.setFields(excelImportfields);
	excelImportGrid.setColumns(excelImportcolumns);
	
	excelImportGrid.displayOptions.fitStyle = "even";
	excelImportGrid.footer.visible = false;
	// gridViewDisplayOptions 그리드뷰 디스플레이 설정
	excelImportGrid.setDisplayOptions({
		emptyMessage : "표시할 데이타가 없습니다.",
		showEmptyMessage : true,
		rowHeight : 36,
		
	});

	// masterGrid 속성값
	excelImportGrid.header.height = 40;
	excelImportGrid.footer.height = 40;
	excelImportGrid.stateBar.width = 10;
	
	excelImportGrid.setCheckBar({
		  visible: true
	  });
	excelImportGrid.setStateBar({
		  visible: false
	  });
	// gridViewEditOptions 그리드뷰 edit 옵션
	excelImportGrid.setEditOptions({
		insertable : true,
		appendable : true,
		editable : true,
		deletable : true,
		confirmWhenDelete : true,
		deleteRowsMessage : "정말로 삭제 하시겠습니까?",
		enterToTab : true,
		displayEmptyEditRow : true,
		commitWhenExitLast : true,
		commitByCell : true
	})

}

function start() {
	$("#xlf").bind("change", handleXlsFile);
	createExcelImportGrid();
}

window.onload = start();

//<--------------------------------엑셀 임포트--------------------------------------->//


	///////////////////////////////////////////////////////////////////////////////
	function fixdata(data) {
	  var o = "", l = 0, w = 10240;
	  for (; l < data.byteLength / w; ++l) o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w)));
	  o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w)));
	  return o;
	}

	function handleXlsFile(e) {
	  var files = e.target.files;
	  var i, f;
	  for (i = 0, f = files[i]; i != files.length; ++i) {
	      var reader = new FileReader();
	      var name = f.name;
	      reader.onload = function (e) {
	          var data = e.target.result;

	          //var workbook = XLSX.read(data, { type: 'binary' });
	          var arr = fixdata(data);
	          workbook = XLSX.read(btoa(arr), { type: 'base64' });

	          process_wb(workbook);
	          /* DO SOMETHING WITH workbook HERE */
	      };
	      //reader.readAsBinaryString(f);
	      reader.readAsArrayBuffer(f);

	  }
	}

	function process_wb(wb) {
	  var output = "";

	  output = to_json(wb);

	  var sheetNames = Object.keys(output);

	  if (sheetNames.length > 0) {
	      var colsObj = output[sheetNames[0]];
	      if (colsObj) {
	    	  var data = []; 
	    	  for(var idx in colsObj){
	    		  data.push([colsObj[idx].품목계정, colsObj[idx].품목코드, colsObj[idx].품목명]);
	    	  }
	    	  console.log(data)
	    	  excelImportProvider.setRows(data);
	      }
	  }
	}
	
	function to_json(workbook) {
		  var result = {};
		  workbook.SheetNames.forEach(function (sheetName) {
		      var roa = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName], {});
		      if (roa.length > 0) {
		          result[sheetName] = roa;
		      }
		  });
		  return result;
		}
	
	
	// 엑셀 데이터 추가
	$("#addExcel").on("click", function(){
		excelImportGrid.commit();
		var dataList = [];
		let checkedRows = excelImportGrid.getCheckedRows();
		if(checkedRows.length > 0){
			for (var checked = 0; checked < checkedRows.length; checked++) {
				var data = excelImportGrid.getValues(checkedRows[checked]);
				dataList.push(data);
			}
			var obj = {"commonList" : dataList}
			$.ajax({
				url : $.getContextPath() + "/info2/itemManageInsert.do",
				data : JSON.stringify(obj),
				method : "post",
				dataType : "json",
				contentType : "application/json",
				success : function(resp) {
					if(resp.result == 'OK'){
						toastr.success(resp.message);
						$("#excelImport-modal").css('display', 'none')
						init();
						
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
			toastr.error("체크하고 적용해주세요");
		}
	})	