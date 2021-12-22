package kr.or.ddit.stock.defectiveitemstockstatus.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.vo.DefectiveStockVO;

/**
 * 불량품재고현황
 */
public interface DefectiveItemStockStatusService {
	//조회
	public List<DefectiveStockVO> selectDefectiveStock(HubSearchVO hubSearchVO);
}
