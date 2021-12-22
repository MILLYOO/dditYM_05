package kr.or.ddit.info2.warehousemanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info2.vo.WarehouseVO;

/**
 * 창고관리 DAO
 */
@Repository
public interface WarehouseManageDAO {
	
	//창고 리스트 검색
	public List<WarehouseVO> selectWarList(@Param("hubSearchVO")HubSearchVO hubSearch);
	
	//창고 등록
	public int insertWarehouse(WarehouseVO warehouse); 
	
	//창고 수정
	public int updateWarehouse(WarehouseVO warehouse);

	//창고 디테일 수정
	public int updateWarehouseDetail(WarehouseVO warehouse);
	
	//창고-아이템 갯수 구하기(삭제용)
	public int selectWarehouseItemCount(WarehouseVO warehouse);
	
	//창고-아이템 등록
	public int insertWarehouseItem(WarehouseVO warehouse);
	
	public int deleteWarehouseItem(WarehouseVO warehouse);
	
	//창고 삭제
	public int deleteWarehouse(WarehouseVO warehouse);
}
