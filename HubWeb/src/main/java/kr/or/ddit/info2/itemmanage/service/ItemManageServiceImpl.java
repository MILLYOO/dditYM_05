package kr.or.ddit.info2.itemmanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info2.itemmanage.dao.ItemManageDAO;
import kr.or.ddit.info2.vo.ItemVO;
import kr.or.ddit.info2.warehousemanage.dao.WarehouseManageDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemManageServiceImpl implements ItemManageService {

	@Inject
	private ItemManageDAO itemDao;
	@Inject
	private WarehouseManageDAO warehouseDao;

	@Override
	public List<ItemVO> retrieveItemList(HubSearchVO hubSearch) {
		List<ItemVO> itemList = itemDao.selectItemList(hubSearch);
		return itemList;
	}

	@Override
	public List<ItemVO> retrieveStockAllStatus(HubSearchVO hubSearch) {
		List<ItemVO> itemList = itemDao.selectStockAllStatus(hubSearch);
		return itemList;
	}

	@Transactional
	@Override
	public ServiceResult createItem(CommonListVO<ItemVO> itemList) {
		ServiceResult result = null;
		HubSearchVO hubSearch = new HubSearchVO();
		int successCnt = 0;
		for (ItemVO item : itemList.getCommonList()) {
			hubSearch.setRawsCode(item.getRawsCode());
			if (itemDao.selectItemList(hubSearch).isEmpty()) {
				int rowcnt = itemDao.insertItem(item);
				if (rowcnt > 0) {
					item.setWarList(warehouseDao.selectWarList(hubSearch));
					if (!item.getWarList().isEmpty()) {
						rowcnt = itemDao.insertItemWarehouse(item);
						if (rowcnt > 0) {
							successCnt++;
						}
					}
				}
			} else {
				result = ServiceResult.PKDUPLICATED;
				return result;
			}
		}
		if(successCnt == itemList.getCommonList().size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult deleteItem(ItemVO item) {
		ServiceResult result = null;
		int rowcnt = itemDao.deleteItem(item);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult updateItem(ItemVO item) {
		ServiceResult result = null;
		int rowcnt = itemDao.updateItem(item);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult updateItemDetail(ItemVO item) {
		ServiceResult result = null;
		int rowcnt = itemDao.updateItemDetail(item);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
