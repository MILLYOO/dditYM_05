package kr.or.ddit.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainSearchController {
	
	@RequestMapping("/common/mainSearch.do")
	public String mainSearch(
			@RequestParam("mainSearchStr") String mainSearchStr){
		
		String str = mainSearchStr.replace(" ", "");
		String viewName="";
		
		switch(str) {
		//입고
	    case "발주서": viewName = "buy/order";
	    break;
	    case "입고처리서": viewName = "buy/incoming";
	    break;
	    case "매입마감서": viewName = "buy/purchclose";
	    break;
	    case "매입순위표": viewName = "buy/rankPurch";
	    break;
	    //출고
	    case "견적서": viewName = "sales/estimate";
	    break;
	    case "수주서": viewName = "sales/orderbook";
	    break;
	    case "출고지시서": viewName = "sales/reord";
	    break;
	    case "출고처리서": viewName = "sales/release";
	    break;
	    case "매출마감서": viewName = "sales/salesclose";
	    break;
	    case "매출순위표": viewName = "sales/rankSales";
	    break;
	    case "매출이익현황": viewName = "sales/statusSalesProfit";
	    break;
	    //생산
	    case "생산지시서": viewName = "produce/instrucItem";
	    break;
	    case "자재출고": viewName = "produce/mrelease";
	    break;
	    case "생산품입고": viewName = "produce/itemware";
	    break;
	    //재고
	    case "품목등록현황": viewName = "stock/statusStockRegister";
	    break;
	    case "현재고총괄현황": viewName = "stock/statusCurrentTotalStock";
	    break;
	    case "재고자산수불부현황": viewName = "stock/statusStockAsset";
	    break;
	    case "입출고조정": viewName = "stock/adjustment";
	    break;
	    case "재고이동": viewName = "stock/stockmove";
	    break;
	    case "재고평가": viewName = "stock/stovalu";
	    break;
	    case "불량품재고현황": viewName = "stockPoorStock";
	    break;
	    case "창고별재고현황": viewName = "stock/statusPerWarehouseStock";
	    break;
	    case "재고실사": viewName = "stock/stockTakingIndex";
	    break;
	    //기초정보관리
	    case "품목마감후이월": viewName = "info/prodCloseForward";
	    break;
	    case "BOM등록": viewName = "info/BOMRegister";
	    break;
	    case "규격등록": viewName = "info/codeRegister";
	    break;
	    case "공정등록": viewName = "info/codeRegister";
	    break;
	    case "분류등록": viewName = "info/codeRegister";
	    break;
	    case "단위등록": viewName = "info/codeRegister";
	    break;
	    case "품목등록": viewName = "info/itemRegister";
	    break;
	    case "부서등록": viewName = "info/departmentRegister";
	    break;
	    case "사원등록": viewName = "info/employeeRegister";
	    break;
	    case "권한설정": viewName = "info/authmanage";
	    break;
	    case "창고등록": viewName = "info/warehouseRegister";
	    break;
	    case "프로젝트등록": viewName = "info/projectRegister";
	    break;
	    case "거래처등록" : viewName = "info/buyerRegister";
	    break;
	    
	    case "공지사항" : viewName = "board/noticeList";
	    break;
	    
	    default: viewName = "common/main";
	    break;
		}
		return viewName;
	}
}
