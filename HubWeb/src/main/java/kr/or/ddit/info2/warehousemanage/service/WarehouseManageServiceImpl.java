package kr.or.ddit.info2.warehousemanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info2.itemmanage.dao.ItemManageDAO;
import kr.or.ddit.info2.vo.WarehouseVO;
import kr.or.ddit.info2.warehousemanage.dao.WarehouseManageDAO;

@Service
public class WarehouseManageServiceImpl implements WarehouseManageService {

	@Inject
	private ItemManageDAO itemDao;
	@Inject
	private WarehouseManageDAO warehouseDao;

	@Override
	public List<WarehouseVO> retrieveWarehouseList(HubSearchVO hubSearch) {
		List<WarehouseVO> warList = warehouseDao.selectWarList(hubSearch);
		return warList;
	}

	@Override
	@Transactional
	public ServiceResult createWarehouse(WarehouseVO warehouse) {
		ServiceResult result = null;
		HubSearchVO hubSearch = new HubSearchVO();
		hubSearch.setWarCode(warehouse.getWarCode());
			int rowcnt = warehouseDao.insertWarehouse(warehouse);
			if (rowcnt > 0) {
				result = ServiceResult.OK;
				warehouse.setItemList(itemDao.selectItemList(hubSearch));
				if (!warehouse.getItemList().isEmpty()) {
					rowcnt = warehouseDao.insertWarehouseItem(warehouse);
					if (rowcnt > 0) {
						result = ServiceResult.OK;
					} else {
						result = ServiceResult.FAILED;
					}
				}
			} else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult updateWarehouse(WarehouseVO warehouse) {
		ServiceResult result = null;
		int rowcnt = warehouseDao.updateWarehouse(warehouse);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult updateWarehouseDetail(WarehouseVO warehouse) {
		ServiceResult result = null;
		int rowcnt = warehouseDao.updateWarehouseDetail(warehouse);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteWarehouse(WarehouseVO warehouse) {
		ServiceResult result = null;
			int	rowcnt =  warehouseDao.selectWarehouseItemCount(warehouse);
			if( rowcnt > 0 ) {
				result = ServiceResult.CHECKANOTHERTABLE;
			}else {
				warehouseDao.deleteWarehouseItem(warehouse);
				rowcnt =  warehouseDao.deleteWarehouse(warehouse);
				if(rowcnt > 0) {
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAILED; 
				}
			}
		return result;
}

}
