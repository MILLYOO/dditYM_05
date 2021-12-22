package kr.or.ddit.info2.warehousemanage.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info2.vo.WarehouseVO;

/**
 * 창고 service
 */
public interface WarehouseManageService {

	/**
	 * 창고 리스트 조회
	 * @return
	 */
	public List<WarehouseVO> retrieveWarehouseList(HubSearchVO hubSearch);
	
	/**
	 * 창고 등록
	 * @param warehouse
	 * @return
	 */
	public ServiceResult createWarehouse(WarehouseVO warehouse);
	
	/**
	 * 창고 수정
	 * @param warehouse
	 * @return
	 */
	public ServiceResult updateWarehouse(WarehouseVO warehouse);
	
	/**
	 * 
	 * @param warehouse
	 * @return
	 */
	public ServiceResult updateWarehouseDetail(WarehouseVO warehouse);
	
	/**
	 * 창고 삭제
	 * @param warehouse
	 * @return
	 */
	public ServiceResult deleteWarehouse(WarehouseVO warehouse);
}
