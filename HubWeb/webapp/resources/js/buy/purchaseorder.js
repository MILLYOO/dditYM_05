// 이달 1일 ~ 현재 날짜 세팅
	var date = new Date();
	$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEnd").val(new Date().toISOString().substring(0, 10));

	 var fields = [
		    { fieldName: "buyYy", dataType: "text" }
		  , { fieldName: "buyMm", dataType: "text" }
		  , { fieldName: "buyDd", dataType: "text" }		
		  , { fieldName: "ordNum", dataType: "text" }		
		  , { fieldName: "buyerCode", dataType: "text" }
		  , { fieldName: "buyerName", dataType: "text" }
		  , { fieldName: "deptName", dataType: "text" }
		  , { fieldName: "empName", dataType: "text" }
		  , { fieldName: "ordVat", dataType: "text" }
		  , { fieldName: "ordResult", dataType: "text" }
		  , { fieldName: "ordFinish", dataType: "text" }
		];

		var columns = [
		    { name: "buyYy", fieldName: "buyYy", type: "data", width: "60", numberFormat: "###0", header: { text: "년" } }
	      , { name: "buyMm", fieldName: "buyMm", type: "data", width: "30", numberFormat: "###0", header: { text: "월" } }
	      , { name: "buyDd", fieldName: "buyDd", type: "data", width: "30", numberFormat: "###0", header: { text: "일" } }	
	      , { name: "ordNum", fieldName: "ordNum", type: "data", width: "100", header: { text: "발주번호" }, "editable" : false}	
		  , { name: "buyerCode", fieldName: "buyerCode", type: "data", width: "150", header: { text: "견적처" }, button:"action", "editable" : false, "visible":false  }
		  , { name: "buyerName", fieldName: "buyerName", type: "data", width: "150", header: { text: "견적처" }, button:"action", "editable" : false  }
		  , { name: "deptName", fieldName: "deptName", type: "data", width: "60", header: { text: "부서" }, button:"action", "editable" : false  }
		  , { name: "empName", fieldName: "empName", type: "data", width: "60", header: { text: "사원" }, button:"action", "editable" : false }
		  , { name: "ordVat", fieldName: "ordVat", type: "data", width: "60", header: { text: "VAT여부" },"visible":false,"lookupDisplay": true, "editor": {
			  "type": "dropdown",
	          "dropDownCount" : 2,
	          "domainOnly" : true,
	          "commitOnSelect" : true,
	          "dropDownWhenClick" : true,
	          "textReadOnly" : true
	        },
	        "values": ["Y", "N"],
	        "labels": ["여", "부"] } /*lookupDisplay를 써줘야 value를 보내고 label을 보여주고 화면에도 그렇게 저장*/
		  , { name: "ordResult", fieldName: "ordResult", type: "data", width: "90", header: { text: "완료여부" },"visible":false, "lookupDisplay": true, "editor": {
			  "type": "dropdown",
	          "dropDownCount" : 2,
	          "domainOnly" : true,
	          "commitOnSelect" : true,
	          "dropDownWhenClick" : true,
	          "textReadOnly" : true
	        },
	        "values": ["Y", "N"],
	        "labels": ["여", "부"] }
		  , { name: "ordFinish", fieldName: "ordFinish", type: "data", width: "90", header: { text: "마감여부" },"visible":false, "lookupDisplay": true, "editor": {
			  "type": "dropdown",
	          "dropDownCount" : 2,
	          "domainOnly" : true,
	          "commitOnSelect" : true,
	          "dropDownWhenClick" : true,
	          "textReadOnly" : true
	        },
	        "values": ["Y", "N"],
	        "labels": ["여", "부"] }
		];
		
		var fields1 = [	
		    { fieldName: "ordNo", dataType: "text" }
		  , { fieldName: "ordNum", dataType: "text" }
	   	  , { fieldName: "icodeName", dataType: "text" }
	   	  , { fieldName: "rawsCode", dataType: "text" }
		  , { fieldName: "rawsName", dataType: "text" }
		  , { fieldName: "gcommName", dataType: "text" }
		  , { fieldName: "ucommName", dataType: "text" }
		  , { fieldName: "orrDate", dataType: "text" }
		  , { fieldName: "orrQty", dataType: "number" }
		  , { fieldName: "orrUprice", dataType: "number" }
		  , { fieldName: "orrSp", dataType: "number" }
		  , { fieldName: "orrVat", dataType: "number" }
		  , { fieldName: "orrSumcost", dataType: "number" }
		  , { fieldName: "projName", dataType: "text" }
		]

		var columns1 = [
			  { name: "ordNo", fieldName: "ordNo", type: "data", width: "0", header: { text: "순번" },"visible" : false }
			, { name: "ordNum", fieldName: "ordNum", type: "data", width: "120", header: { text: "발주번호" }, "editable" : false}
			, { name: "icodeName", fieldName: "icodeName", type: "data", width: "100", header: { text: "품목계정" }, "editor": {
		          "type": "dropdown",
		          "dropDownCount" : 1,
		          "domainOnly" : true,
		          "commitOnSelect" : true,
		          "dropDownWhenClick" : true,
		          "values": "원재료",
		          "labels": "원재료"
		       }, "visible":false  }
			, { name: "rawsCode", fieldName: "rawsCode", type: "data", width: "90", header: { text: "품목코드" }, button:"action"}
			, { name: "rawsName", fieldName: "rawsName", type: "data", width: "170", header: { text: "품명" }, styleName: "left-column"}
			, { name: "gcommName", fieldName: "gcommName", type: "data", width: "100", header: { text: "규격" }, button:"action" }
			, { name: "ucommName", fieldName: "ucommName", type: "data", width: "90", header: { text: "단위" }, button:"action" }
			, { name: "orrDate", fieldName: "orrDate", type: "data", width: "120", header: { text: "납기일" }, editor: {type: "date", datetimeFormat: "yyyy/MM/dd"}
			, footer: {"styles": {"textAlignment": "far","font": "굴림,12"}, "text": "합계 =>",numberFormat: "#,##0", styleName: "right-column" ,"groupText": "합계 =>"} }
			, { name: "orrQty", fieldName: "orrQty", type: "data", width: "100", numberFormat: "#,##0", styleName: "right-column" ,header: { text: "수량" }, footer: {expression : "sum", numberFormat: "#,##0", styleName: "right-column"} }
			, { name: "orrUprice", fieldName: "orrUprice", type: "data", width: "100", numberFormat: "#,##0", styleName: "right-column",header: { text: "단가" }, footer: {expression : "sum", numberFormat: "#,##0"}  }
			, { name: "orrSp", fieldName: "orrSp", type: "data", width: "100" , numberFormat: "#,##0",header: { text: "공급가액" }, styleName: "right-column" ,footer: {expression : "sum",styleName: "right-column", numberFormat: "#,##0"}  }
			, { name: "orrVat", fieldName: "orrVat", type: "data", width: "100" , numberFormat: "#,##0", styleName: "right-column",header: { text: "부가세" }, footer: {expression : "sum", numberFormat: "#,##0",styleName: "right-column"}  }
			, { name: "orrSumcost", fieldName: "orrSumcost", type: "data", numberFormat: "#,##0",width: "100",styleName: "right-column", header: { text: "합계금액" }, footer: {expression : "sum", styleName: "right-column",numberFormat: "#,##0"} }
			, { name: "projName", fieldName: "projName", type: "data", width: "100", header: { text: "프로젝트" }, button:"action" }
			]



		function doDisplay(){
			var con = document.getElementById("myDIV");
			if(con.style.display=='none'){
				con.style.display = 'block';
			}else{
				con.style.display = 'none';
			}
		}
		function doHide(){
			var hid = document.getElementById("select");
			var con = document.getElementById("myDIV");
			if(con.style.display=='none'){
				hid.style.display = 'block';
			}else{
				hid.style.display = 'none';
			}
		}	

  // 1. 각자 메뉴에 맞는 컬럼, 필드 만들기(해당 메뉴에 맞는 컬럼과 필드를 만들어야함, VO와 명칭도 일치해야함)
  // 2. 검색, 수정, 삭제시, 조건 알아서 넣기.
  // 
  // -> alert, confirm 나중에 모달로 변경해야함.
  /********************************* 마스터에 값 넣기 ******************************/
  // 비동기로 처리할 부분. 검색버튼같은거 만들어서 사용해야함. getJSON 말고 ajax 사용해도 가능
	
//		// 거래처 검색 모달 띄우기
//		$("#buyerName").focus(function(e){
//			$("#buyer-modal").css('display', 'flex')
//		});
//		
//		// 부서 검색 모달 띄우기
//		$("#deptName").focus(function(e){
//		    $("#dept-modal").css('display', 'flex')
//		});
//		
//		// 사원 검색 모달 띄우기
//		$("#empName").focus(function(e){
//			$("#emp-modal").css('display', 'flex')
//		});		
		
		// 목록 조회
		$("#select1").on("click",function(){
			masterGrid.commit()
			masterGrid.columnByName("ordNum").editable =  false;
			var startDate = $("#dateStart").val();
			var endDate = $("#dateEnd").val();
			
			// 날짜 지정하지 않았을 때
			if(startDate.replace(/\s/g,"").length==0){
				toastr.error("날짜를 입력하세요.");
				return false;
			}
			
			if(endDate >= startDate){
				let searchForm = $("#searchForm").ajaxForm({
					url : $.getContextPath() + "/buy/orderList.do",
					method : "post",
					dataType : "json",
					success : function(resp) {
						masterProvider.fillJsonData(resp, {fillMode: "set"});
					}
				}).submit();
			}else{
				toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
				return false;
			}
			
			masterGrid.columnByName("buyerName").editable =  false;
			masterGrid.columnByName("deptName").editable =  false;
			masterGrid.columnByName("empName").editable =  false;
		});
		
		// 목록 조회
		$("#select2").on("click",function(){
			masterGrid.commit()
			masterGrid.columnByName("ordNum").editable =  false;
			var startDate = $("#dateStart").val();
			var endDate = $("#dateEnd").val();
			
			// 날짜 지정하지 않았을 때
			if(startDate.replace(/\s/g,"").length==0){
				toastr.error("날짜를 입력하세요.");
				return false;
			}
			
			if(endDate >= startDate){
				let searchForm = $("#searchForm").ajaxForm({
					url : $.getContextPath() + "/buy/orderList.do",
					method : "post",
					dataType : "json",
					success : function(resp) {
						masterProvider.fillJsonData(resp, {fillMode: "set"});
					}
				}).submit();
			}else{
				toastr.error("종료일이 시작일보다 이전 날짜 입니다.");
				return false;
			}
			
			masterGrid.columnByName("buyerName").editable =  false;
			masterGrid.columnByName("deptName").editable =  false;
			masterGrid.columnByName("empName").editable =  false;
		});
		
		// 저장버튼을 사용하기 위한 설정
		$("#saved").on("click", function(){
			 masterGrid.commit();
		      let checkedRows = masterGrid.getCheckedRows();
		      if(checkedRows.length > 0){
		      masterProvider.setRowStates(checkedRows, "none", true);
		      
		      var dataList = [];	// 체크된 행 데이터 넣을 배열
		      
		         for(var checked = 0 ;  checked < checkedRows.length ; checked++){
		            data = masterGrid.getValues(checkedRows[checked]);
		            var ddData = data.buyDd;
		            if(ddData < 10){
		            	ddData = "0"+ddData;
		            }
		            var lastDate = new Date(data.buyYy, data.buyMm, 0).getDate();
	    			
	    			if(data.buyYy >= 2023 || data.buyYy <= 2020){
	    				toastr.error("해당 연도 입력 불가");
	    				return false;
	    			}else if(data.buyMm < 1 || data.buyMm > 12){
	    				toastr.error("입력할 수 없는 달입니다");
	    				return false;
	    			}else if(data.buyDd < 1 || data.buyDd > lastDate){
	    				toastr.error("입력한 달의 마지막 일을 확인하세요");
	    				return false;
	    			}
		            dataList.push(data);
		         } //for문 끝
		         let obj = {"commonList":dataList}
						$.ajax({
							url : $.getContextPath()+"/buy/orderInsert.do",
							data : JSON.stringify(obj),
							method : "post",
							dataType : "json",
							contentType : "application/json",
							success : function(resp) {
								if(resp.result == 'OK'){
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
											console.log(resp.message)
											toastr.error(resp.message[idx]);
										}
									}else{
										toastr.error(message);
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
		
		// 추가버튼
		$("#append").on("click", function(){
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
		         
		         masterProvider.addRow(values);
		      }
		});
	
	// 삭제버튼
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
	    						data = masterGrid.getValues(rows[i]);
	    						if(data == null) continue;
	    			            dataList.push(data);
	    					} // else
	    				} // for
    						$.ajax({
    							url : $.getContextPath()+"/buy/orderDelete.do",
    							data : JSON.stringify(dataList),
    							method : "post",
    							dataType : "text",
    							contentType : "application/json",
    							success : function(resp) {
    								if(resp=="삭제되었습니다."){
    									Swal.fire(
    											"삭제가 완료되었습니다.",
    											"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
    											"success"
    									);
    									for(var i = rows.length-1 ; i >= 0 ; i--){
    										masterProvider.removeRow(rows[i]);
    									}
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
	
  /********************************* 디테일 설정하기 ******************************/
 
	
  function detailGet(masterRow) {
	  detailProvider.clearRows();
	  console.log(masterRow);
	  if (masterRow >= 0) {
	      var mstKey = masterProvider.getValue(masterRow, "ordNum");
	     $.ajax({
			url : $.getContextPath()+"/buy/orderRawsList.do",
			data : {"ordNum" : mstKey},
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

	// 저장버튼을 사용하기 위한 설정
	$("#saved1").on("click", function(){
		
		detailGrid.commit();
		let checkedRows = detailGrid.getCheckedRows();
		
		if(checkedRows.length > 0){
			detailProvider.setRowStates(checkedRows, "none", true);
			
			var dataList = [];	// 체크된 행 데이터 넣을 배열
			
			for(var checked = 0 ;  checked < checkedRows.length ; checked++){
				data = checkedRows[checked];
				
				var insertData = detailGrid.getValues(data);
				
				var rawscd = insertData.rawsCode;
				var ordNum = insertData.ordNum;
				var icdName = insertData.icodeName;
				
				if(rawscd == null){
					toastr.error("품목코드 입력 후 저장 해주세요");
					return false;
				}else if(ordNum == null){
					toastr.error("발주번호가 없습니다");
				}else if(icdName == null){
					toastr.error("품목계정은 필수값입니다.");
				}					
				
				dataList.push(insertData);
				
			}
			let obj = {"commonList":dataList}
					$.ajax({
						url : $.getContextPath()+"/buy/orderRawsInsert.do",
						data : JSON.stringify(obj),
						method : "post",
						dataType : "json",
						contentType : "application/json",
						success : function(resp) {
							if(resp.result == 'OK'){
								toastr.success(resp.message);
							}else{
								if(Array.isArray(resp.message)){
									for(var idx in resp.message){
										console.log(resp.message)
										toastr.error(resp.message[idx]);
									}
								}else{
									toastr.error(resp.message)
								}
							}	
							let searchForm = $(".searchForm").ajaxForm({
								dataType : "json",
								success : function(resp) {
									masterProvider.fillJsonData(resp, {fillMode: "set"});
								}
							}).submit();
							
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

	// 추가버튼
	$("#append1").on("click", function(){
		var values = [];
        for(var i = 0 ; i < fields1.length ; i++){
           values.push = "";
        }
        var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
        values[1] = masterData.ordNum;
        detailProvider.addRow(values);

        detailGrid.commit();
		detailGrid.showEditor();
		detailGrid.setFocus();
		
	});
	
	// 삭제버튼
	$("#delete1").on("click", function(){
		detailGrid.commit();
		var rows;
		var data;
		rows = detailGrid.getCheckedItems();
		
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
	    				for(var i = rows.length ; i >= 0 ; i--){
	    					if(detailProvider.getRowState(rows[i]) == "created"){
	    						detailProvider.removeRow(rows[i]);
	    						continue;
	    					}else {
	    						data = detailGrid.getValues(rows[i]);
	    						if(data == null) continue;
	    						dataList.push(data);
	    						
	    						ordNum = data.ordNum;
	    					} // else
	    				} // for
	    						$.ajax({
	    							url : $.getContextPath()+"/buy/orderRawsDelete.do",
	    							data : JSON.stringify(dataList),
	    							method : "post",
	    							dataType : "text",
	    							contentType : "application/json",
	    							success : function(resp) {
	    								if(resp=="삭제되었습니다."){
	    									Swal.fire(
	    											"삭제가 완료되었습니다.",
	    											"",/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
	    											"success"
	    									);
	    									  $.ajax({
	  											url : $.getContextPath()+"/buy/orderRawsList.do",
	  											data : {"ordNum" : ordNum},
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

//	window.onload = function() {
// 		detailGrid.columnByName("ordNum").editable =  false;
//	};

	pdfIcon = function(){
		let checkedRows = masterGrid.getCheckedRows();
		if(checkedRows.length != 1){
			toastr.error("한개만 체크해 주세요");
		}else{
			let index = checkedRows[0];
			var data = masterGrid.getValues(index);
			$.ajax({
				url : $.getContextPath() + "/buy/pdfDownload.do",
				data : data,
				method : 'post',
				dataType : 'text',
				success : function(resp){
					console.log(resp);
				},
				error: function(xhr, errorResp, error){
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			})
		}
	}	
	