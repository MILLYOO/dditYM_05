package kr.or.ddit.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buy.purchclose.dao.PurchCloseDAO;
import kr.or.ddit.buy.vo.PurchCloseVO;
import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.dao.SalesEmpDAO;
import kr.or.ddit.common.vo.DocCheckVO;
import kr.or.ddit.sales.salesclose.dao.SalesCloseDAO;
import kr.or.ddit.sales.vo.RelProdVO;
import kr.or.ddit.sales.vo.SalesRankVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class mainController {
	// 하이차트
	//매출데이터
	@Resource(name="salesCloseDAO")
	private SalesCloseDAO salesCloseDAO;
	//매입데이터
	@Resource(name="purchCloseDAO")
	private PurchCloseDAO purchCloseDAO;
	//사원별매출현황
	@Resource(name="salesEmpDAO")
	private SalesEmpDAO salesEmpDAO;
	
	@RequestMapping("/common/main.do")
	public String main(Model model) {

	//그래프만들기
		//매출데이터 가져오기
		List<RelProdVO> relProdVO = salesCloseDAO.selectSalesGraph();
		//매입데이터 가져오기
		List<PurchCloseVO> purchCloseVO = purchCloseDAO.selectBuyGraph();
		//사원별 매출데이터 가져오기
		List<SalesRankVO> salesRankVO = salesEmpDAO.selectSalesEmpMonth();
		log.info("salesRankVO:{}",salesRankVO);
		//데이터 세팅
		model.addAttribute("sale",relProdVO);
		model.addAttribute("buy",purchCloseVO);
		model.addAttribute("rank",salesRankVO);
		
		//이동
		String viewName = "common/main";
		return viewName;
	}
}
