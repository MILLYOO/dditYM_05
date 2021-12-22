/**
 * 출고처리서
 */
	var date = new Date();
	$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEnd").val(new Date().toISOString().substring(0, 10));

	var fields = [
		{
			fieldName : "relYear",
			dataType : "text"
		},
		{
			fieldName : "relMonth",
			dataType : "text"
		},
		{
			fieldName : "relDay",
			dataType : "text"
		},
		{
			fieldName : "relNum",
			dataType : "text"
		},
		{
			fieldName : "relDate",
			dataType : "date"
		},
		{
			fieldName : "buyerCode",
			dataType : "number"
		},
		{
			fieldName : "buyerName",
			dataType : "text"
		},
		{
			fieldName : "deptName",
			dataType : "text"
		},
		{
			fieldName : "empName",
			dataType : "text"
		},
		{
			fieldName : "relVat",
			dataType : "text"
		},
		{
			fieldName : "relResult",
			dataType : "text"
		},
		{
			fieldName : "relFinish",
			dataType : "text"
		}
	];
	var columns = [
		
		{
			name : "relYear",
			fieldName : "relYear",
			type : "data",
			width : "60",
			header: {
				text : "년"
			}
		},
		{
			name : "relMonth",
			fieldName : "relMonth",
			type : "data",
			width : "60",
			header: {
				text : "월"
			}
		},
		{
			name : "relDay",
			fieldName : "relDay",
			type : "data",
			width : "60",
			header: {
				text : "일"
			}
		},
		{
			name : "relNum",
			fieldName : "relNum",
			type : "data",
			width : "60",
			header: {
				text : "출고처리번호"
			}
		
		},
		{
			name : "relDate",
			fieldName : "relDate",
			type : "data",
			width : "60",
			header: {
				text : "날짜"
			},
			editor: {type: "date", datetimeFormat: "yyyy/MM/dd"}
		    , "visible" : false
		},
		{
			name : "buyerCode",
			fieldName : "buyerCode",
			type : "data",
			width : "60",
			header: {
				text : "거래처코드"
			},
			numberFormat: "#,##0"
			, "visible" : false
		},
		{
			name : "buyerName",
			fieldName : "buyerName",
			type : "data",
			width : "60",
			header: {
				text : "거래처"
			},
			button : "action"
		},
		{
			name : "deptName",
			fieldName : "deptName",
			type : "data",
			width : "60",
			header: {
				text : "부서"
			},
			button : "action"
		},
		{
			name : "empName",
			fieldName : "empName",
			type : "data",
			width : "60",
			header: {
				text : "사원"
			},
			button : "action"
		},
		{
			name : "relVat",
			fieldName : "relVat",
			type : "data",
			width : "60",
			header: {
				text : "VAT여부"
			},
			"lookupDisplay": true,
			  "editor": {
				  "type": "dropdown",
		          "dropDownCount" : 2,
		          "domainOnly" : true,
		          "commitOnSelect" : true,
		          "dropDownWhenClick" : true,
		          "textReadOnly" : true
			  },
			  "values": ["Y", "N"],
			  "labels": ["여", "부"]
			  , "visible" : false
		},
		{
			name : "relResult",
			fieldName : "relResult",
			type : "data",
			width : "60",
			header: {
				text : "완료여부"
			},
			"lookupDisplay": true,
			  "editor": {
				  "type": "dropdown",
		          "dropDownCount" : 2,
		          "domainOnly" : true,
		          "commitOnSelect" : true,
		          "dropDownWhenClick" : true,
		          "textReadOnly" : true
			  },
			  "values": ["Y", "N"],
			  "labels": ["여", "부"]
			  , "visible" : false
		},
		{
			name : "relFinish",
			fieldName : "relFinish",
			type : "data",
			width : "60",
			header: {
				text : "마감여부"
			},
			"lookupDisplay": true,
			  "editor": {
				  "type": "dropdown",
		          "dropDownCount" : 2,
		          "domainOnly" : true,
		          "commitOnSelect" : true,
		          "dropDownWhenClick" : true,
		          "textReadOnly" : true
			  },
			  "values": ["Y", "N"],
			  "labels": ["여", "부"]
			  , "visible" : false
		}
	];
	var fields1 = [
		{
			fieldName : "relNo",
			dataType : "number"
		},
		{
			fieldName : "relNum",
			dataType : "text"
		},
		{
			fieldName : "icodeName",
			dataType : "text"
		},
		{
			fieldName : "prodCode",
			dataType : "text"
		},
		{
			fieldName : "prodName",
			dataType : "text"
		},
		{
			fieldName : "gcommName",
			dataType : "text"
		},
		{
			fieldName : "ucommName",
			dataType : "text"
		},
		{
			fieldName : "rpDate",
			dataType : "date"
		},
		{
			fieldName : "rpQty",
			dataType : "number"
		},
		{
			fieldName : "rpUprice",
			dataType : "number"
		},
		{
			fieldName : "rpSp",
			dataType : "number"
		},
		{
			fieldName : "rpVat",
			dataType : "number"
		},
		{
			fieldName : "rpSumcost",
			dataType : "number"
		},
		{
			fieldName : "warName",
			dataType : "text"
		},
		{
			fieldName : "projName",
			dataType : "text"
		},
		{
			fieldName : "reoNum",
			dataType : "text"
		}
		];
	var columns1 = [
		
		{
			name : "relNo",
			fieldName : "relNo",
			type : "data",
			width : "60",
			header: {
				text : "순번"
			},
			"visible" : false
		},
		{
			name : "relNum",
			fieldName : "relNum",
			type : "data",
			width : "60",
			header: {
				text : "출고지시번호"
			}, "visible" : false
		},
		{
			name : "icodeName",
			fieldName : "icodeName",
			type : "data",
			width : "60",
			header: {
				text : "품목계정"
			}
			, "editor": {
		          "type": "dropdown",
		          "dropDownCount" : 7,
		          "domainOnly" : true,
		          "commitOnSelect" : true,
		          "dropDownWhenClick" : true,
		          "values": ["상품", "원재료","부재료","제품","반제품","부산품","저장품"],
		          "labels": ["1.상품", "2.원재료","3.부재료","4.제품","5.반제품","6.부산품","7.저장품"]
	        } 
		},
		{
			name : "prodCode",
			fieldName : "prodCode",
			type : "data",
			width : "60",
			header: {
				text : "제품코드"
			}
		, button:"action"
		},
		{
			name : "prodName",
			fieldName : "prodName",
			type : "data",
			width : "60",
			header: {
				text : "품명"
			},
			styleName: "left-column"
//			editor: {type: "date", datetimeFormat: "yyyy.MM.dd"}
//			, "visible" : false
		},
		{
			name : "gcommName",
			fieldName : "gcommName",
			type : "data",
			width : "60",
			header: {
				text : "규격"
			}
			, button:"action"
		},
		{
			name : "ucommName",
			fieldName : "ucommName",
			type : "data",
			width : "60",
			header: {
				text : "단위"
			}
			, button:"action"
		},
		{
			name : "rpDate",
			fieldName : "rpDate",
			type : "data",
			width : "60",
			header: {
				text : "납기일"
			},editor: {type: "date", datetimeFormat: "yyyy/MM/dd"}
			, footer: {
	            "styles": {
	                "textAlignment": "far",
	                "font": "굴림,12",
	            },
	            "text": "합계 =>",
	            "groupText": "합계 =>",
	        }
		},
		{
			name : "rpQty",
			fieldName : "rpQty",
			type : "data",
			width : "70",
			header: {
				text : "수량(단위:개)"
			},
			numberFormat: "#,##0"
			, footer: {
			      expression: "sum",
			      numberFormat: "#,##0",
			      styleName:"orange-column"
			    },
			    styleName: "right-column"
		},
		{
			name : "rpUprice",
			fieldName : "rpUprice",
			type : "data",
			width : "60",
			header: {
				text : "단가"
			},
			numberFormat: "#,##0"
				,styleName: "right-column"
		},
		{
			name : "rpSp",
			fieldName : "rpSp",
			type : "data",
			width : "60",
			header: {
				text : "공급가액"
			},
		numberFormat: "#,##0"
			,styleName: "right-column"
		},
		{
			name : "rpVat",
			fieldName : "rpVat",
			type : "data",
			width : "60",
			header: {
				text : "부가세"
			},
			numberFormat: "#,##0"
				,styleName: "right-column"
		},
		{
			name : "rpSumcost",
			fieldName : "rpSumcost",
			type : "data",
			width : "60",
			header: {
				text : "합계금액"
			},numberFormat: "#,##0"
			,  footer: {
			      expression: "sum",
			      numberFormat: "#,##0",
			      styleName:"orange-column",
			    },
			    styleName: "right-column"
			
		},
		{
			name : "warName",
			fieldName : "warName",
			type : "data",
			width : "60",
			header: {
				text : "창고"
			},
			button:"action"
		},
		{
			name : "projName",
			fieldName : "projName",
			type : "data",
			width : "60",
			header: {
				text : "프로젝트"
			},
			button:"action"
		},
		{
			name : "reoNum",
			fieldName : "reoNum",
			type : "data",
			width : "60",
			header: {
				text : "출고지시번호"
			}
		, "visible" : false
		}
		];
	
	
	
	
	// ---------------------------------------출고지시서 적용--------------------------------------------//
	
	// 수주서 적용 모달 
	$("#orderBookApply").on("click",function(){
		var startDate = $("#dateStart").val();
		var endDate = $("#dateEnd").val();
//		var rows = masterProvider.getRows();
//		var index = rows.length-1;
//		var state = masterProvider.getRowState(index)
//		var day = masterProvider.getValue(index,2)
		$("#obdateStart").val(startDate);
		$("#obdateEnd").val(endDate);
		
		$("#orderbookApplyForPI-modal").css("display", "flex")
		
	});
	
	//x표 눌러서 모달 닫기
	$(".close-area").click(function(){
		$("#orderbookApplyForPI-modal").css("display", "none")
	});
	
	//-----------------------------------------------------------------------------------------------------//
	
//	셀렉트처리
	$("#select1").click(function() {
		masterGrid.columnByName("buyerName").editable =  false;
		masterGrid.columnByName("deptName").editable =  false;
		masterGrid.columnByName("empName").editable =  false;
		masterGrid.columnByName("relNum").editable =  false;
		$("#searchForm").ajaxForm({
			url : $.getContextPath() + "/sales/ReleaseProcessingRetrieve.do",
			dataType : "json",
			success : function(data) {
				masterProvider.fillJsonData(data, {fillMode : "set"});
				let lenght = masterProvider.getRows().length;
				for(var i = 0; i<lenght; i++){
					data = masterGrid.getValues(i);
					let year = data.relDate.getFullYear();
					let month = data.relDate.getMonth()+1;
					let day = data.relDate.getDate();
					masterProvider.setValue(i,0,year);
					masterProvider.setValue(i,1,month);
					masterProvider.setValue(i,2,day);
					masterProvider.setRowState(i,"none");
					masterGrid.footer.visible = false;
				}
			},
			error : function(xhr, errorResp, error){
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		}).submit();
	});
	$("#select2").click(function() {
		masterGrid.columnByName("buyerName").editable =  false;
		masterGrid.columnByName("deptName").editable =  false;
		masterGrid.columnByName("empName").editable =  false;
		masterGrid.columnByName("relNum").editable =  false;
		$("#searchForm").ajaxForm({
			url : $.getContextPath() + "/sales/ReleaseProcessingRetrieve.do",
			dataType : "json",
			success : function(data) {
				masterProvider.fillJsonData(data, {fillMode : "set"});
				let lenght = masterProvider.getRows().length;
				for(var i = 0; i<lenght; i++){
					data = masterGrid.getValues(i);
					let year = data.relDate.getFullYear();
					let month = data.relDate.getMonth()+1;
					let day = data.relDate.getDate();
					masterProvider.setValue(i,0,year);
					masterProvider.setValue(i,1,month);
					masterProvider.setValue(i,2,day);
					masterProvider.setRowState(i,"none");
					masterGrid.footer.visible = false;
				}
			},
			error : function(xhr, errorResp, error){
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		}).submit();
	});
	
	
	//	저장하기
	$("#saved").on("click", function(){
		var dataList = []; // 보낼 데이터들 배열
		let checkedRows = masterGrid.getCheckedRows();
		if(checkedRows.length > 0){
		for(var checked = 0 ;  checked < checkedRows.length ; checked++){
			let index = checkedRows[checked];
			let data = masterGrid.getValues(index);
			let year = data.relYear;
			let month = data.relMonth*1;
			let day = data.relDay;
			let date = year + "/" + month + "/" + day;
			data.relDate = date;
			var state = masterProvider.getRowState(index);
			if (state == "created") {
				data.relNum = '';
			}
			dataList.push(data);
		} // for문 끝
		// 하위 품목이 존재 할때
//		let value = detailProvider.getRows().length;
//		if (value > 0) {
//			let checkedRows = detailGrid.getCheckedRows(); //디테일 그리드에서 체크된 행 수
//			 detailGrid.commit(); // 디테일 그리드 커밋
//			 if(checkedRows.length > 0){ // 체크된 행이 있을 경우
//				 var dataProdList = [];
//				 for(var checked = 0; checked < checkedRows.length; checked++){
//					 let dedata = detailGrid.getValues(checkedRows[checked]);
//					 let deyear = dedata.rpDate.getFullYear(); // 연도 변수
//					 let demonth = dedata.rpDate.getMonth() + 1; // 월 변수
//					 let deday = dedata.rpDate.getDate(); // 일 변수
//					 let dedate = deyear + "/" + demonth + "/" + deday; // 디테일 그리드에서 꺼낸 년,월,일을 저장하기 위해 다시 날짜 변수에 초기화
//					 dedata.rpDate = dedate; // 초기화한 날짜 변수를 vo에 넣기 위해 초기화
//					 let mstKey = dedata.relNum; // 마스터키에 디테일 그리드에서 가져온 수주번호값 넣어줌
//					 let Vat = dedata.rpVat;
//					 let qty = dedata.rpQty;
//					 Vat = dedata.rpSp*0.03*qty;
//					 dedata.rpVat = Vat;
//					 let scost = dedata.rpSp * qty + Vat;
//					 dedata.rpSumcost = scost;
//					 dataProdList.push(dedata);
//				 }
//			 }
//		}
//		var allData = {"dataList" : dataList, "dataProdList" : dataProdList}
		var obj = {"commonList":dataList}
		$.ajax({
			url : $.getContextPath() + "/sales/ReleaseProcessingInsert.do",
			data : JSON.stringify(obj),
			method : 'post',
			dataType : 'json',
			contentType : "application/json",
			success : function(resp){
				if(resp.result == 'OK'){
			    	toastr.success(resp.message);
				let searchForm = $("#searchForm").ajaxForm({
					url : $.getContextPath() + "/sales/ReleaseProcessingRetrieve.do",
					dataType : "json",
					success : function(resp){
						masterProvider.fillJsonData(resp, {fillMode: "set"});
						let lenght = masterProvider.getRows().length;
						for(var i = 0 ; i < lenght; i++){
							let data = masterGrid.getValues(i);
							let year = data.relDate.getFullYear();
							let month = data.relDate.getMonth() +1;
							let day = data.relDate.getDate();
							masterProvider.setValue(i,0,year);
							masterProvider.setValue(i,1,month);
							masterProvider.setValue(i,2,day);
							masterProvider.setRowState(i,"none");
						}
					}
				}).submit();
				}else{
		    		if (Array.isArray(resp.message)) {
						
						for(var idx in resp.message){
							toastr.error(resp.message[idx]);
						}
					}else{
						toastr.error(resp.message);
					}	
		    	}
			},
			error : function(xhr, errorResp, error){
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		})
	}else{
		toastr.error("체크하고 등록해 주세요.");
	     }
	});
	
	//	추가버튼
	$("#append").on("click", function() {
		var count = masterProvider.getRowStateCount("created");
		if(count > 0){
	         toastr.error("추가된 행을 저장해주세요");
	      }else{
	    	  var values = [];
		         for(var i = 0 ; i < fields.length ; i++){
		            values.push = "";
		         }
	         let today = new Date();
	         let year = today.getFullYear();
	         let month = today.getMonth()+1;
	         var values = [year, month, "", "", "", "", ""];      
	         masterProvider.addRow(values);   
	         let lastday = new Date(year,month,0).getDate();
	         
	       //유효성검사
	         masterGrid.onValidateColumn = function(grid, column, inserting, value) {
	          var error = {};
	          if (column.fieldName === "reoDay") {
	              if (value < 1) {
	                  error.level = "error";
	                  error.message = "1일 이전은 입력 할 수 없습니다.";
	              }else if(value > lastday){
	                  error.level = "warning";
	                  error.message = "현재 달의 마지막일을 확인해주세요.";
	              }
	          };
	          return error;
	         }
	      }
	})
	
	//	삭제버튼
	$("#delete").on("click", function(){
		let checkedRows = masterGrid.getCheckedRows(); 
		if(checkedRows.length > 0){ 
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
            		var dataList = []; 
            		for(var checked = 0 ;  checked < checkedRows.length ; checked++){
            			let index = checkedRows[checked];
            			let data = masterGrid.getValues(index);
            			dataList.push(data);
            		} // for문 끝
            		$.ajax({
            			url : $.getContextPath() + "/sales/ReleaseProcessingDelete.do",
            			data : JSON.stringify(dataList),
            			method : 'post',
            			dataType : 'json',
            			contentType : "application/json",
            			success : function(data){
            				toastr.success(data.message);
            				$("#searchForm").ajaxForm({ // 서치폼으로 에이작스폼으로 이동
            					url : $.getContextPath() + "/sales/ReleaseProcessingRetrieve.do",
            					dataType:"json",
            					success:function(data){
            						masterProvider.fillJsonData(data, {fillMode: "set"});
            						let lenght = masterProvider.getRows().length;
            						for(var i = 0 ; i < lenght; i++){
            							var data = masterGrid.getValues(i);
            							let year = data.relDate.getFullYear();
            							let month = data.relDate.getMonth() +1;
            							let day = data.relDate.getDate();
            							masterProvider.setValue(i,0,year);
            							masterProvider.setValue(i,1,month);
            							masterProvider.setValue(i,2,day);
            							masterProvider.setRowState(i,"none");
            						}
            					},
            					error : function(xhr, errorResp, error){
            						
            						console.log(xhr);
            						console.log(errorResp);
            						console.log(error);
            					}
            				}).submit();
            			},
            			error : function(xhr, errorResp, error){
            				console.log(xhr);
            				console.log(errorResp);
            				console.log(error);
            			}
            		});
            	} // if문 끝
            });
		}else{
			toastr.error("체크하고 삭제해주세요");
		}
	});
	 /********************************* 디테일 설정하기 ******************************/
	function detailGet(masterRow) {
		 detailProvider.clearRows();
		  if (masterRow >= 0) {
			var mstKey = masterProvider.getValue(masterRow, "relNum");
			$.getJSON($.getContextPath() + "/sales/ReleaseProcessingProdRetrieve.do?relNum=" + mstKey, function(data) {
				detailProvider.fillJsonData(data, {fillMode : "set"});
			})
		};
		
		  detailGrid.columnByName("icodeName").editable =  false;
		  detailGrid.commit();
		  detailGrid.columnByName("prodCode").editable =  false;
		  detailGrid.commit();
		  detailGrid.columnByName("prodName").editable =  false;
		  detailGrid.commit();
		  detailGrid.columnByName("gcommName").editable =  false;
		  detailGrid.commit();
		  detailGrid.columnByName("ucommName").editable =  false;
		  detailGrid.commit();
	 };
	 
	 //		저장하기
	 $("#saved1").on("click", function() {
		 var checkedRows = detailGrid.getCheckedRows(); //디테일 그리드에서 체크된 행 수
		 detailGrid.commit(); // 디테일 그리드 커밋
		 if(checkedRows.length > 0){ // 체크된 행이 있을 경우
			 var dataProdList = []; // 체크된 행 데이터 넣을 배열
			 for(var checked = 0; checked < checkedRows.length; checked++){
				 let data = detailGrid.getValues(checkedRows[checked]);
				 	if (data.rpDate) {
				 		let year = data.rpDate.getFullYear(); // 연도 변수
				 		let month = data.rpDate.getMonth() + 1; // 월 변수
				 		let day = data.rpDate.getDate(); // 일 변수
				 		let date = year + "/" + month + "/" + day; // 디테일 그리드에서 꺼낸 년,월,일을 저장하기 위해 다시 날짜 변수에 초기화
				 		data.rpDate = date; // 초기화한 날짜 변수를 vo에 넣기 위해 초기화
					}
				 let mstKey = data.relNum; // 마스터키에 디테일 그리드에서 가져온 수주번호값 넣어줌
//				 let Vat = data.rpVat;
//				 let qty = data.rpQty;
//				 Vat = data.rpSp*0.03*qty;
//				 data.rpVat = Vat;
//				 let scost = data.rpSp * qty + Vat;
//				 data.rpSumcost = scost;
				 dataProdList.push(data);
			 } // for문 끝
			 let obj = {"commonList":dataProdList}
			 $.ajax({ 
				 url : $.getContextPath() + "/sales/ReleaseProcessingProdInsert.do",
				 data : JSON.stringify(obj),
				 method : 'post',
				 dataType : 'json',
				 contentType : "application/json",
				 success : function(data){
					 if(data.result=="OK"){
						   toastr.success(data.message);
					 let mstKey = detailGrid.getValue(0,1);
					 $.getJSON( $.getContextPath() + "/sales/ReleaseProcessingProdRetrieve.do?relNum=" + mstKey, function(data){
						 detailProvider.fillJsonData(data, {fillMode: "set"});
					 }) 
					 }else{
						   if(Array.isArray(data.message)){
							   for(var idx in data.message){
								   toastr.error(data.message[idx]);
							   }
						   }else{
							   toastr.error(data.message);
						   }
					   }
				 },
				 error: function(xhr, errorResp, error){
					 console.log(xhr);
					 console.log(errorResp);
					 console.log(error);
				 }
			 });
		 }else{
			 toastr.error("체크하고 해주세요")
		 }
	});
	 
//		추가버튼
	 $("#append1").on("click", function(){
		 var values = []; // 추가할 행의 배열
		 for(var i = 0 ; i < fields1.length ; i++){ // 포문을 통해 배열에 빈값 배치
	           values.push = "";
	        }
		 
		 	var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow); // 마스터 그리드에서 선택한 데이터를 추가 버튼 누르면 가져온다.
	        values[1] = masterData.relNum; // 가져온 데이터중 2번째 행의 수주번호를 가져온다.
	        detailProvider.addRow(values); // 가져온 데이터를 디테일 그리드에 추가
	        var row = detailGrid.getItemCount();
	        detailProvider.setValue(row-1,2,'제품'); // 품목은 제품으로 한정하기 위한 코드
	        detailGrid.commit(); // 그리드 커밋 편집 상태 해제
			detailGrid.showEditor();
			detailGrid.setFocus();
			
			//유효성검사
	        detailGrid.onValidateColumn = function(grid, column, inserting, value) {
	         var error = {}; // 유효성 검사 에러 메세지 담기
	         if (column.fieldName === "rpSp") {
	             if (value < 0) {
	                 error.level = "error";
	                 error.message = "0보다 작을 수 없습니다.";
	             }
	         }else if(column.fieldName === "rpVat"){
	        	 if (value < 0) {
	                 error.level = "error";
	                 error.message = "0보다 작을 수 없습니다.";
	             }
	         }else if(column.fieldName === "rpScost"){
	        	 if (value < 0) {
	                 error.level = "error";
	                 error.message = "0보다 작을 수 없습니다.";
	             }
	         }else if(column.fieldName === "rpQty "){
	        	 if (value < 0) {
	                 error.level = "error";
	                 error.message = "0보다 작을 수 없습니다.";
	             }
	         }
	         return error;
	        }
	 })
	 
	 //	 삭제버튼
	 $("#delete1").on("click", function(){ // 디테일 그리드 삭제 기능
		 let checkedRows = detailGrid.getCheckedRows(); // 디테일 그리드에서 체크한 행 수
		 if(checkedRows.length > 0){
			 Swal.fire({ // 삭제 모달창 출력
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
					 var dataProdList = []; 
					 for(var checked = 0 ;  checked < checkedRows.length ; checked++){ // 체크된 행 수만큼 포문 시행
						 let data = detailGrid.getValues(checkedRows[checked]); // 디테일 그리드에서 체크된 데이터 가져온다.
						 dataProdList.push(data);
					 	} // for문 끝
					 $.ajax({ 
						 url : $.getContextPath() + "/sales/ReleaseProcessingProdDelete.do",
						 data : JSON.stringify(dataProdList), // 데이터는 위에서 만든 디테일 그리드 데이터
						 method : 'post', 
						 dataType : "json",
						 contentType : "application/json",
						 success : function(data){
							 toastr.success(data.message); // 성공시 데이터 메시지 출력
							 var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
							 let mstKey = masterData.relNum; // 마스터키로 마스터 데이터에서 수주번호 꺼내기
							 $.getJSON( $.getContextPath() + "/sales/ReleaseProcessingProdRetrieve.do?relNum=" + mstKey, function(data){
								 detailProvider.fillJsonData(data, {fillMode: "set"});
							 }) 
						 },
						 error: function(xhr, errorResp, error){
							 console.log(xhr);
							 console.log(errorResp);
							 console.log(error);
						 }
					 });
				 	}
			 	})
		 	}else{
		 		var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
				console.log(masterData);
				let mstKey = masterData.reoNum;
				$.getJSON( $.getContextPath() + "/sales/ReleaseProcessingProdRetrieve.do?relNum=" + mstKey, function(data){
					console.log(data);
					detailProvider.fillJsonData(data, {fillMode: "set"});
				})
		 	}
	 });
	 
	 
	 
	 
	 
	 