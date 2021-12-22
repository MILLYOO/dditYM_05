<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<html>
  <head>
  <style>
.rgsample-blue-column {
  color: blue;
}

.rgsample-bold-column {
  font-weight: bold;
}

.alternate-row {
  background: #f8f8f8;
}

.blue-column {
  color: blue;
}

.bold-cell {
  font-weight: bold;
  background-color: greenyellow;
}

#actions {
  height: 50px;
}

#realgrid {
  width: 100%;
  height: 450px; /* for WebKit */
}

.test-style {
  background: linear-gradient(to right, #96f58f, red);
}

.right-column {
  text-align: right;
}

.left-column {
  text-align: left;
}

.red-bar-solid .rg-bar-renderer-bar {
  background: #ff0000;
}

.green-bar-solid .rg-bar-renderer-bar-right {
  background: #00ff00;
}

.blue-bar-solid .rg-bar-renderer-bar-top {
  background: #0000ff;
}

.yellow-bar-solid .rg-bar-renderer-bar-bottom {
  background: #ffff00;
}

.green-hidden span {
  right: 12% !important;
  transform: translate(0%, -50%) !important;
  display: none !important;
}

.yellow-column {
  background: #ffff00
}

.cursor {
  cursor: crosshair;
}

.orange-column{
  background: #ff8b00;
}
</style>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        
    <!-- RealGrid -->
    <link href="<%=request.getContextPath()%>/resources/js/realgrid/realgrid-style.css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/realgrid/realgrid-lic.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/realgrid/realgrid.min.js"></script>

    <!-- Custom -->
    <link href="./index.css" rel="stylesheet" />
    <script src="./index.js"></script>
  </head>

  <body>
  	<form>
	  	<input type="text" id="com"/>
	  	<input type="button" id="saved" value="저장"/>
	  	<input type="button" id="append" value="추가"/>
	  	<input type="button" id="delete" value="삭제"/>
  	</form>
    <div id="realgrid"></div>
  </body>
  <script type="text/javascript">
  var fields = [
	  {
	    fieldName: "memId",
	    dataType: "text"
	  },
	  {
	    fieldName: "memName",
	    dataType: "text"
	  },
	  {
	    fieldName: "memHp",
	    dataType: "text"
	  },
	  {
	    fieldName: "memAdd1",
	    dataType: "text"
	  },
	  {
	    fieldName: "memMileage",
	    dataType: "text"
	  },
	  {
	    fieldName: "memMail",
	    dataType: "text"
	  }
	];

	var columns = [
	  {
	    name: "memId",
	    fieldName: "memId",
	    type: "data",
	    width: "60",
	    header: {
	      text: "아이디"
	    }
	  },
	  {
	    name: "memName",
	    fieldName: "memName",
	    type: "data",
	    width: "40",
	    header: {
	      text: "이름"
	    }
	  },
	  {
	    name: "memHp",
	    fieldName: "memHp",
	    type: "data",
	    width: "40",
	    header: {
	      text: "번호"
	    }
	  },
	  {
	    name: "memAdd1",
	    fieldName: "memAdd1",
	    type: "data",
	    width: "100",
	    header: {
	      text: "주소",
	    }
	  },
	  {
	    name: "memMileage",
	    fieldName: "memMileage",
	    type: "data",
	    width: "120",
	    header: {
	      text: "마일리지",
	    }
	  },
	  {
	    name: "memMail",
	    fieldName: "memMail",
	    type: "data",
	    width: "100",
	    header: {
	      text: "이메일",
	    }
	  }
	];

	var dataProvider, gridContainer, grid;
	/* 		기본 셋팅	 	*/
	function createGrid(container) {
	  
	  dataProvider = new RealGrid.LocalDataProvider();
	  gridView = new RealGrid.GridView(container);
	
	  gridView.setDataSource(dataProvider);
	  dataProvider.setFields(fields);
	  gridView.setColumns(columns);

	  // gridViewDisplayOptions 그리드뷰 디스플레이 설정
	  gridView.setDisplayOptions({
		  emptyMessage : "표시할 데이타가 없습니다."
		  , showEmptyMessage: true
		  , rowHeight : 36
		  });
	  // gridView 속성값
	  gridView.header.height = 40;
	  gridView.footer.height = 40;
	  gridView.stateBar.width = 16;
	  
	  // gridViewEditOptions 그리드뷰 edit 옵션
	  //
	  gridView.setEditOptions({
		  insertable : true
		  , appendable : true
		  , editable : true
		  , deletable: true
		  , confirmWhenDelete : true
		  , deleteRowsMessage : "정말로 삭제 하시겠습니까?"
	  })
	  // 클릭한 곳의 데이터를 가지고 위한 clicked 이벤트
	  gridView.onCellClicked = function (grid, clickData) {
//     	console.log(clickData);
		console.log(gridView.getValues(clickData.dataRow)); 
	  }


	  // 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능
	  $.getJSON("${cPath}/member/memberList.do", function(data){
		  console.log(data);
		  dataProvider.fillJsonData(data.dataList, {fillMode: "set"});
	  }) 
	}

	function start() {
	  createGrid("realgrid");
	}

	// $.document.ready(start);
	window.onload = start;
	// domloaded를 대신 써도 됩니다.
	
	// 저장버튼을 사용하기 위한 설정
	// 백단에서 기본키가 되는 code가 없으면 insert, 있으면 update
	$("#saved").on("click", function(){
		let checkedRows = gridView.getCheckedRows();
		gridView.commit();
		if(checkedRows.length > 0){
			console.log(checkedRows);
			for(var checked = 0 ;  checked < checkedRows.length ; checked++){
				data = checkedRows[checked];
				console.log(data)
				console.log(gridView.getValues(data));
			} 
		}else{
			alert("체크하고 해주세요");
		}
	});
	
	// 추가버튼
	$("#append").on("click", function(){
		var values = ["", "", "", "", "", "", ""];
		dataProvider.addRow(values);
	})
	
	// 삭제버튼
	$("#delete").on("click", function(){
		console.log("삭제");
		let checkedRows = gridView.getCheckedRows();
		
// 		gridView.deleteSelection(false);
		var result = window.confirm("정말로 삭제하시겠습니까?");
		if(result === true){
		dataProvider.removeRows(checkedRows);
		}
	})
	
	// 초기화
	window.onunload = function() {
	  dataProvider.clearRows();

	  gridView.destroy();
	  dataProvider.destroy();

	  gridView = null;
	  dataProvider = null;
	}
  </script>
</html>