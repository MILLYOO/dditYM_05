/**
 * 출고지시서
 */

	var date = new Date();
	$("#dateStart").val(new Date(date.getFullYear(), date.getMonth(), 2).toISOString().substring(0, 10));
	$("#dateEnd").val(new Date().toISOString().substring(0, 10));
	
	var fields = [
		{
			fieldName : "reoYear",
			dataType : "text"
		},
		{
			fieldName : "reoMonth",
			dataType : "text"
		},
		{
			fieldName : "reoDay",
			dataType : "text"
		},
		{
			fieldName : "reoNum",
			dataType : "text"
		},
		{
			fieldName : "reoDate",
			dataType : "date"
		},
		{
			fieldName : "buyerCode",
			dataType : "text"
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
			fieldName : "reoVat",
			dataType : "text"
		},
		{
			fieldName : "reoResult",
			dataType : "text"
		},
		{
			fieldName : "reoFinish",
			dataType : "text"
		}
	];
	var columns = [
		
		{
			name : "reoYear",
			fieldName : "reoYear",
			type : "data",
			width : "40",
			header: {
				text : "년"
			}
		},
		{
			name : "reoMonth",
			fieldName : "reoMonth",
			type : "data",
			width : "30",
			header: {
				text : "월"
			}
		},
		{
			name : "reoDay",
			fieldName : "reoDay",
			type : "data",
			width : "30",
			header: {
				text : "일"
			}
		},
		{
			name : "reoNum",
			fieldName : "reoNum",
			type : "data",
			width : "100",
			header: {
				text : "출고지시번호"
			}
		
		},
		{
			name : "reoDate",
			fieldName : "reoDate",
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
			}
		    , "visible" : false
		},
		{
			name : "buyerName",
			fieldName : "buyerName",
			type : "data",
			width : "100",
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
			name : "reoVat",
			fieldName : "reoVat",
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
			name : "reoResult",
			fieldName : "reoResult",
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
			name : "reoFinish",
			fieldName : "reoFinish",
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
			fieldName : "reoNo",
			dataType : "number"
		},
		{
			fieldName : "reoNum",
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
			fieldName : "ropDate",
			dataType : "date"
		},
		{
			fieldName : "ropQty",
			dataType : "number"
		},
		{
			fieldName : "ropUprice",
			dataType : "number"
		},
		{
			fieldName : "ropSp",
			dataType : "number"
		},
		{
			fieldName : "ropVat",
			dataType : "number"
		},
		{
			fieldName : "ropSumcost",
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
			fieldName : "orbNum",
			dataType : "text"
		}
		];
	var columns1 = [
		
		{
			name : "reoNo",
			fieldName : "reoNo",
			type : "data",
			width : "60",
			header: {
				text : "순번"
			},
			"visible" : false
		},
		{
			name : "reoNum",
			fieldName : "reoNum",
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
			name : "ropDate",
			fieldName : "ropDate",
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
			name : "ropQty",
			fieldName : "ropQty",
			type : "data",
			width : "60",
			header: {
				text : "수량(단위:개)"
			},
			numberFormat: "#,##0"
			, footer: {
			      expression: "sum",
			      numberFormat: "#,##0",
			      styleName:"orange-column"
			    },
			    
		},
		{
			name : "ropUprice",
			fieldName : "ropUprice",
			type : "data",
			width : "60",
			header: {
				text : "단가"
			},
			numberFormat: "#,##0"
			,styleName: "right-column"
		},
		
		{
			name : "ropSp",
			fieldName : "ropSp",
			type : "data",
			width : "60",
			header: {
				text : "공급가액"
			},
		numberFormat: "#,##0"
			,styleName: "right-column"
		},
		{
			name : "ropVat",
			fieldName : "ropVat",
			type : "data",
			width : "60",
			header: {
				text : "부가세"
			},
			numberFormat: "#,##0"
				,styleName: "right-column"
		},
		{
			name : "ropSumcost",
			fieldName : "ropSumcost",
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
		    button : "action"
		},
		{
			name : "projName",
			fieldName : "projName",
			type : "data",
			width : "60",
			header: {
				text : "프로젝트"
			},
		    button : "action"
		},
		{
			name : "orbNum",
			fieldName : "orbNum",
			type : "data",
			width : "60",
			header: {
				text : "수주번호"
			}
		, "visible" : false
		}
		];
	
	
	
// ---------------------------------------------수주서적용 --------------------------------------
	
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
	
// ---------------------------------------------수주서적용 끝--------------------------------------
	
	
	
	//	셀렉트처리
	$("#select1").click(function() {
		masterGrid.columnByName("buyerName").editable =  false;
		masterGrid.columnByName("deptName").editable =  false;
		masterGrid.columnByName("empName").editable =  false;
		$("#searchForm").ajaxForm({
			url : $.getContextPath() + "/sales/ReleaseOrderRetrieve.do",
			dataType : "json",
			success : function(data) {
				masterProvider.fillJsonData(data, {fillMode : "set"});
				let lenght = masterProvider.getRows().length;
				for(var i = 0; i<lenght; i++){
					data = masterGrid.getValues(i);
					let year = data.reoDate.getFullYear();
					let month = data.reoDate.getMonth()+1;
					let day = data.reoDate.getDate();
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
		$("#searchForm").ajaxForm({
			url : $.getContextPath() + "/sales/ReleaseOrderRetrieve.do",
			dataType : "json",
			success : function(data) {
				masterProvider.fillJsonData(data, {fillMode : "set"});
				let lenght = masterProvider.getRows().length;
				for(var i = 0; i<lenght; i++){
					data = masterGrid.getValues(i);
					let year = data.reoDate.getFullYear();
					let month = data.reoDate.getMonth()+1;
					let day = data.reoDate.getDate();
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
			let year = data.reoYear;
			let month = data.reoMonth*1;
			let day = data.reoDay;
			let date = year + "/" + month + "/" + day;
			data.reoDate = date;
			var state = masterProvider.getRowState(index);
			if (state == "created") {
				data.reoNum = '';
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
//					 let deyear = dedata.ropDate.getFullYear(); // 연도 변수
//					 let demonth = dedata.ropDate.getMonth() + 1; // 월 변수
//					 let deday = dedata.ropDate.getDate(); // 일 변수
//					 let dedate = deyear + "/" + demonth + "/" + deday; // 디테일 그리드에서 꺼낸 년,월,일을 저장하기 위해 다시 날짜 변수에 초기화
//					 dedata.ropDate = dedate; // 초기화한 날짜 변수를 vo에 넣기 위해 초기화
//					 let mstKey = dedata.reoNum; // 마스터키에 디테일 그리드에서 가져온 수주번호값 넣어줌
//					 let Vat = dedata.ropVat;
//					 let qty = dedata.ropQty;
//					 Vat = dedata.ropSp*0.03*qty;
//					 dedata.ropVat = Vat;
//					 let scost = dedata.ropSp * qty + Vat;
//					 dedata.ropSumcost = scost;
//					 dataProdList.push(dedata);
//				 }
//			 }
//		}
//		var allData = {"dataList" : dataList, "dataProdList" : dataProdList}
		var obj = {"commonList":dataList}
		$.ajax({
			url : $.getContextPath() + "/sales/ReleaseOrderInsert.do",
			data : JSON.stringify(obj),
			method : 'post',
			dataType : 'json',
			contentType : "application/json",
			success : function(resp){
				if(resp.result == 'OK'){
					toastr.success(resp.message);
				let searchForm = $("#searchForm").ajaxForm({
					url : $.getContextPath() + "/sales/ReleaseOrderRetrieve.do",
					dataType : "json",
					success : function(resp){
						masterProvider.fillJsonData(resp, {fillMode: "set"});
						let lenght = masterProvider.getRows().length;
						for(var i = 0 ; i < lenght; i++){
							let data = masterGrid.getValues(i);
							let year = data.reoDate.getFullYear();
							let month = data.reoDate.getMonth() +1;
							let day = data.reoDate.getDate();
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
		let checkedRows = masterGrid.getCheckedRows(); // 마스터 그리드에서 체크된 행 수를 가져온다.
		if(checkedRows.length > 0){ // 체크된 행의 크기(수)가 0보다 크면 체크된 항목이 있다는 소리
			Swal.fire({ // 취소 모달창
                title: "정말로 삭제하시겠습니까?",
                text: "다시 한 번 확인해주세요",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "승인",
                cancelButtonText: "취소"
            }).then((result) => { // 람다식 모달창에서 결과값으로 아래 이프문 실행
            	if (result.isConfirmed) { // 취소 모달창에서 결과값의 확인을 눌렀을때
            		var dataList = []; // 체크된 행 데이터 넣을 배열
            		for(var checked = 0 ;  checked < checkedRows.length ; checked++){
            			let index = checkedRows[checked];
            			let data = masterGrid.getValues(index);
            			dataList.push(data);
            		} // for문 끝
            		$.ajax({
            			url : $.getContextPath() + "/sales/ReleaseOrderDelete.do",
            			data : JSON.stringify(dataList),
            			method : 'post',
            			dataType : 'json',
            			contentType : "application/json",
            			success : function(data){
            				toastr.success(data.message);
//            				Swal.fire(data.message);
            				$("#searchForm").ajaxForm({ // 서치폼으로 에이작스폼으로 이동
            					url : $.getContextPath() + "/sales/ReleaseOrderRetrieve.do",
            					dataType:"json",
            					success:function(data){
            						masterProvider.fillJsonData(data, {fillMode: "set"});
            						let lenght = masterProvider.getRows().length;
            						for(var i = 0 ; i < lenght; i++){
            							data = masterGrid.getValues(i);
            							let year = data.reoDate.getFullYear();
            							let month = data.reoDate.getMonth() +1;
            							let day = data.reoDate.getDate();
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
			Swal.fire("체크하고 삭제해주세요");
		}
	});
	
	  /********************************* 디테일 설정하기 ******************************/
//	마스터 그리드에서 선택한 셀 하위 품목 디테일 그리드에 표시해주는 함수
	 function detailGet(masterRow) {
		 detailProvider.clearRows();
		  if (masterRow >= 0) {
			var mstKey = masterProvider.getValue(masterRow, "reoNum");
			$.getJSON($.getContextPath() + "/sales/ReleaseOrderProdRetrieve.do?reoNum=" + mstKey, function(data) {
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
		  detailGrid.columnByName("warName").editable =  false;
		  detailGrid.commit();
		  detailGrid.columnByName("projName").editable =  false;
		  detailGrid.commit();
	 };
	
	 //	저장하기
	 $("#saved1").on("click", function() {
		 var checkedRows = detailGrid.getCheckedRows(); //디테일 그리드에서 체크된 행 수
		 detailGrid.commit(); // 디테일 그리드 커밋
		 if(checkedRows.length > 0){ // 체크된 행이 있을 경우
			 var dataProdList = []; // 체크된 행 데이터 넣을 배열
			 for(var checked = 0; checked < checkedRows.length; checked++){
				 let data = detailGrid.getValues(checkedRows[checked]);
				 if (data.ropDate) {
					 let year = data.ropDate.getFullYear(); // 연도 변수
					 let month = data.ropDate.getMonth() + 1; // 월 변수
					 let day = data.ropDate.getDate(); // 일 변수
					 let date = year + "/" + month + "/" + day; // 디테일 그리드에서 꺼낸 년,월,일을 저장하기 위해 다시 날짜 변수에 초기화
					 data.ropDate = date; // 초기화한 날짜 변수를 vo에 넣기 위해 초기화
				}
				 let mstKey = data.reoNum; // 마스터키에 디테일 그리드에서 가져온 수주번호값 넣어줌
//				 let Vat = data.ropVat;
//				 let qty = data.ropQty;
//				 Vat = data.ropSp*0.03*qty;
//				 data.ropVat = Vat;
//				 let scost = data.ropSp * qty + Vat;
//				 data.ropSumcost = scost;
				 dataProdList.push(data);
			 } // for문 끝
			 let obj = {"commonList":dataProdList}
			 $.ajax({ // 클라이언트에서 데이터 정돈하여 서버로 보내는 에이작스
				 url : $.getContextPath() + "/sales/ReleaseOrderProdInsert.do",
				 data : JSON.stringify(obj),
				 method : 'post',
				 dataType : 'json',
				 contentType : "application/json",
				 success : function(data){
					 if(data.result=="OK"){
						 toastr.success(data.message);
					 let mstKey = detailGrid.getValue(0,1);
					 $.getJSON( $.getContextPath() + "/sales/ReleaseOrderProdRetrieve.do?reoNum=" + mstKey, function(data){
						 console.log(data);
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
			 Swal.fire("체크하고 해주세요")
		 }
	});
	
//	추가버튼
	 $("#append1").on("click", function(){
		 var values = []; // 추가할 행의 배열
		 for(var i = 0 ; i < fields1.length ; i++){ // 포문을 통해 배열에 빈값 배치
	           values.push = "";
	        }
		 
		 	var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow); // 마스터 그리드에서 선택한 데이터를 추가 버튼 누르면 가져온다.
	        values[1] = masterData.reoNum; // 가져온 데이터중 2번째 행의 수주번호를 가져온다.
	        detailProvider.addRow(values); // 가져온 데이터를 디테일 그리드에 추가
	        var row = detailGrid.getItemCount();
	        detailProvider.setValue(row-1,2,'제품'); // 품목은 제품으로 한정하기 위한 코드
	        detailGrid.commit(); // 그리드 커밋 편집 상태 해제
			detailGrid.showEditor();
			detailGrid.setFocus();
			
			//유효성검사
	        detailGrid.onValidateColumn = function(grid, column, inserting, value) {
	         var error = {}; // 유효성 검사 에러 메세지 담기
	         if (column.fieldName === "ropSp") {
	             if (value < 0) {
	                 error.level = "error";
	                 error.message = "0보다 작을 수 없습니다.";
	             }
	         }else if(column.fieldName === "ropVat"){
	        	 if (value < 0) {
	                 error.level = "error";
	                 error.message = "0보다 작을 수 없습니다.";
	             }
	         }else if(column.fieldName === "ropScost"){
	        	 if (value < 0) {
	                 error.level = "error";
	                 error.message = "0보다 작을 수 없습니다.";
	             }
	         }else if(column.fieldName === "ropQty "){
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
				 if (result.isConfirmed) { // 확인 버튼 눌렀을때 실행
					 var dataProdList = []; // 체크된 데이터 넣을 행
					 for(var checked = 0 ;  checked < checkedRows.length ; checked++){ // 체크된 행 수만큼 포문 시행
						 let data = detailGrid.getValues(checkedRows[checked]); // 디테일 그리드에서 체크된 데이터 가져온다.
						 dataProdList.push(data);
					 	} // for문 끝
					 $.ajax({ // 클라이언트에서 수집한 데이터 서버로 보내는 에이작스
						 url : $.getContextPath() + "/sales/ReleaseOrderProdDelete.do",
						 data : JSON.stringify(dataProdList), // 데이터는 위에서 만든 디테일 그리드 데이터
						 method : 'post', 
						 dataType : "json",
						 contentType : "application/json",
						 success : function(data){
							 toastr.success(data.message);
//							 Swal.fire(data.message); // 성공시 데이터 메시지 출력
							 var masterData = masterGrid.getValues(masterGrid.getCurrent().dataRow);
							 let mstKey = masterData.reoNum; // 마스터키로 마스터 데이터에서 수주번호 꺼내기
							 $.getJSON( $.getContextPath() + "/sales/ReleaseOrderProdRetrieve.do?reoNum=" + mstKey, function(data){
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
				$.getJSON( $.getContextPath() + "/sales/ReleaseOrderProdRetrieve.do?reoNum=" + mstKey, function(data){
					console.log(data);
					detailProvider.fillJsonData(data, {fillMode: "set"});
				})
		 	}
	 });
	 
	 