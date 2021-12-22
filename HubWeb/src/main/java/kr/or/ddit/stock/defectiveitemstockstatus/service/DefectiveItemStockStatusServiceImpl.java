package kr.or.ddit.stock.defectiveitemstockstatus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.defectiveitemstockstatus.dao.DefectiveItemStockStatusDAO;
import kr.or.ddit.stock.vo.DefectiveStockVO;

@Service
public class DefectiveItemStockStatusServiceImpl implements DefectiveItemStockStatusService {

	@Inject
	private DefectiveItemStockStatusDAO dao;

	@Override
	public List<DefectiveStockVO> selectDefectiveStock(HubSearchVO hubSearchVO) {
		List<DefectiveStockVO> defectiveStockList = dao.selectDefectiveStock(hubSearchVO);
		return defectiveStockList;
	}

}
