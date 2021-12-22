package kr.or.ddit.info2.itemmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info2.vo.ItemVO;

/**
 * 품목 관리 DAO
 */
@Repository
public interface ItemManageDAO {
	
	//원자재 TABLE
	public List<ItemVO> selectItemList(@Param("hubSearchVO")HubSearchVO hubSearch);

	//현재고총괄현황 조회
	public List<ItemVO> selectStockAllStatus(@Param("hubSearchVO")HubSearchVO hubSearch);
	
	public int insertItem(ItemVO item);
	
	public int updateItem(ItemVO item);
	
	public int updateItemDetail(ItemVO item);
	
	public int deleteItem(ItemVO item);
	
	public int insertItemWarehouse(ItemVO item);
}
