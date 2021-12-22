package kr.or.ddit.stock.defectiveitemstockstatus.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.vo.DefectiveStockVO;

/**
 * 불량품재고현황
 */
@Repository
public interface DefectiveItemStockStatusDAO {

	public List<DefectiveStockVO> selectDefectiveStock(HubSearchVO hubSearchVO);
}
