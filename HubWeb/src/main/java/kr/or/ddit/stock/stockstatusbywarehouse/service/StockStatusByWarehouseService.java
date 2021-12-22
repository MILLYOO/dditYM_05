package kr.or.ddit.stock.stockstatusbywarehouse.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.vo.WarItemVO;

/**
 * 창고별재고현황
 */
public interface StockStatusByWarehouseService {

	//창고의 리스트를 가져온다
	//파라미터는 hubSearchVO, 결과값은 창고코드, 창고명, 합계
	List<WarItemVO> warList(HubSearchVO hubSearchVO);
	
	List<WarItemVO> warItemList(HubSearchVO hubSearchVO);
	
	WarItemVO stockTakingWarItem(HubSearchVO hubSearchVO);
}
