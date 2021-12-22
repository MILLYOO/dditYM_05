package kr.or.ddit.stock.stockassetpaymentstatus.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.stockassetpaymentstatus.dao.StockAssetPaymentStatusDAO;
import kr.or.ddit.stock.vo.StockAssetVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockAssetPaymentStatusServiceImpl implements StockAssetPaymentStatusService {
	@Inject
	private StockAssetPaymentStatusDAO dao;

	@Override
	public List<StockAssetVO> retrieveStockAssetList(HubSearchVO hubSearchVO){
		List<StockAssetVO> stockAssetList = dao.selectStockAssest(hubSearchVO);
		//stockAssetList 에 있는 데이터는 재고에 대한 부분이 없음
		//-> 재고에 대한 데이터를 만들어서 return 한다
		Map<String, Integer> qtyMap = new HashMap<>();
		Map<String, Integer> costMap = new HashMap<>();
		for(StockAssetVO stockAsset : stockAssetList) {
			//입고,출고,재고 수량---------------------------------------------------
			//꺼낸 객체의 품목코드와 재고수량, 입고 출고 수량을 받아놓는다
			String prodCode = stockAsset.getProdCode();
			Integer enterQty = stockAsset.getEnterQty();
			Integer outQty = stockAsset.getOutQty();
			Integer stoQty = enterQty - outQty;
			//입고 출고 재고금액을 받아놓는다
			Integer enterCost = stockAsset.getEnterCost();
			Integer outCost = stockAsset.getOutCost();
			Integer stoCost = enterCost - outCost;
			
			if(!qtyMap.containsKey(prodCode)) {//map에 key 가 없다면??
				stockAsset.setStoQty(stoQty); //재고수량을 입력한다.
				qtyMap.put(prodCode, stoQty); //map에 key(품목코드)와 재고수량을 넣는다
				
				stockAsset.setStoCost(stoCost);//재고금액을 입력한다.
				costMap.put(prodCode, stoCost); //map에 품목코드와 재고금액을 넣는다
				
				stockAsset.setStoUcost(stoCost/stoQty);//재고단가 입력
			}else {//map에 key가 존재한다면?
				//객체에서 입고 또는 출고 수량을 꺼낸 후, 저장되어있는 재고수와 합산하여 결과를 도출, map에 또다시 저장한다
				Integer stoQtyMap = qtyMap.get(prodCode); //map에 저장되어있는 재고 값을 가져온다
				Integer stoQtyRes = stoQtyMap+enterQty-outQty;
				stockAsset.setStoQty(stoQtyRes); //재고수량을 입력한다.
				qtyMap.put(prodCode, stoQtyRes);
				
				Integer stoCostMap = costMap.get(prodCode);
				Integer stoCostRes = stoCostMap+enterCost-outCost;
				stockAsset.setStoCost(stoCostRes); //재고금액을 입력한다.
				costMap.put(prodCode, stoCostRes); 
				
				stockAsset.setStoUcost(stoCost/stoQtyRes);//재고단가 입력
			}
		}
		log.info("stockAssetList:{}",stockAssetList);
		return stockAssetList;
	}
	
	
}
