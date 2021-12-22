package kr.or.ddit.produce.productioninstruction.service;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info2.vo.BomRawsVO;
import kr.or.ddit.info2.vo.ItemVO;
import kr.or.ddit.produce.productioninstruction.dao.ProductionInstructionDAO;
import kr.or.ddit.produce.vo.InstRawsVO;
import kr.or.ddit.produce.vo.InstrucprodVO;
import kr.or.ddit.sales.orderbook.dao.OrderBookDAO;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;

@Service
public class ProductionInstructionServiceImpl implements ProductionInstructionService {
	
	@Inject
	private ProductionInstructionDAO dao;
	
	@Resource(name="orderBookDAO")
	private OrderBookDAO obDao;

	// 생산지시서 목록 조회
	@Override
	public List<InstrucprodVO> retrieveProductionInstructionList(HubSearchVO hubSearch) {
		return dao.selectProductionInstructionList(hubSearch);
	}

	// 생산지시서 등록
	@Transactional
	@Override
	public ServiceResult createOrUpdateProductionInstruction(List<InstrucprodVO> instrucProdVOList) {
		ServiceResult result = null;
		int successCnt = 0;
		for(InstrucprodVO instrucProdVO : instrucProdVOList) {
			String year = instrucProdVO.getPdcYy();
			String month = instrucProdVO.getPdcMm();
			String day = instrucProdVO.getPdcDd();
			
			if(Integer.parseInt(month) < 10) {
				month = "0"+month;
			}else if(Integer.parseInt(day) < 10) {
				day = "0"+day;
			}
			String instDate = year+month+day;
			instrucProdVO.setInstDate(instDate);
			
			String instFin = instrucProdVO.getInstFinish();
			if(StringUtils.isNotBlank(instFin)) {
				instFin = instFin.toUpperCase();	// Y,N 대문자로 변경
				instrucProdVO.setInstFinish(instFin);
			}
			
			int instDateNum = Integer.parseInt(instDate);
			String instCom = instrucProdVO.getInstComplete();
			if(StringUtils.isNotBlank(instCom)) {
				String[] com = instCom.split("/");
				instCom = com[0]+com[1]+com[2];
				int instcomNum = Integer.parseInt(instCom);
				
				if(instDateNum > instcomNum) {
					return ServiceResult.FAILED;
				}
			}
			
			String instNum = instrucProdVO.getInstNum();
			if(StringUtils.isBlank(instNum)) {
				// 등록
				int cnt = dao.insertProductionInstruction(instrucProdVO);
				if(cnt > 0) {
					// 새로운 생산지시서 등록 체크 위한 매서드
					int cntCheck = dao.insertCheck(instrucProdVO);
					if (cntCheck > 0) {
						successCnt++;
					}
				}
			}else {
				// 수정
				InstrucprodVO saved = dao.selectProductionInstruction(instrucProdVO);
				String orbNum = saved.getOrbNum();
				if(StringUtils.isNotBlank(orbNum)) {	// 수주서 적용된 지시서
					if(!saved.getProdCode().equals(instrucProdVO.getProdCode())) {
						return ServiceResult.BADREQUEST;
					}
				}
				Integer leadQty = instrucProdVO.getInstLeadqty();
				if(leadQty > saved.getInstLeadqty()) {
					InstRawsVO rawsVO = new InstRawsVO();
					rawsVO.setInstNum(instrucProdVO.getInstNum());
					rawsVO.setPrQty(leadQty);
					int upCnt = dao.updatePrQtyByLeadQty(rawsVO);
					List<InstRawsVO> rawList = dao.selectInstRawsList(instrucProdVO);
					if(upCnt != rawList.size()) {
						successCnt--;
					}
				}
				int cnt = dao.updateProductionInstruction(instrucProdVO);
				if(cnt > 0) {
					successCnt++;
				}
			}
		}
		if(successCnt == instrucProdVOList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	// 생산지시서 삭제
	@Transactional
	@Override
	public ServiceResult deleteProductionInstruction(List<InstrucprodVO> instrucProdVOList) {
		int allcnt = 0;
		ServiceResult result = null;
		for(InstrucprodVO instrucProdVO : instrucProdVOList) {
			
			if(StringUtils.isNotBlank(instrucProdVO.getOrbNum())) {
				OrderBookVO ob = new OrderBookVO();
				ob.setOrbNum(instrucProdVO.getOrbNum());
				OrderBookVO selectOb = dao.selectOrderBookforPI(ob);
				if(selectOb!=null) {
					List<InstrucprodVO> instAppObList = dao.selectObApplyPi(instrucProdVO);
					if(instAppObList.size() == 1) {
						if(instAppObList.get(0).getOrbNum().equals(selectOb.getOrbNum())) {
							OrderBookVO obVO = new OrderBookVO();
							obVO.setOrbNum(selectOb.getOrbNum());
							obVO.setOrbAppyn("N");
							int updateOb = dao.updateOderBookApplyYn(obVO);
							if(updateOb <= 0) {
								allcnt--;
							}
						}
					}
				}
			}
			
			List<InstRawsVO> rawsList =  dao.selectInstRawsList(instrucProdVO);
			if(rawsList.size() > 0) {	// 지시서의 품목이 존재하면
				int cnt = dao.deleteAllProdRaws(instrucProdVO.getInstNum()); 	// 품목존재 시 먼저 전체 삭제
				if(cnt > 0) {
					int delCnt = dao.deleteProductionInstruction(instrucProdVO);
					if(delCnt > 0) {
						allcnt++;
					}
				}
			}else {
				int delCnt = dao.deleteProductionInstruction(instrucProdVO);
				if(delCnt > 0) {
					allcnt++;
				}
			}
		}
		if(allcnt == instrucProdVOList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	// 원자재 목록 조회
	@Override
	public List<InstRawsVO> retrieveInstRawsList(InstrucprodVO instrucProdVO) {
		// 선택한 생산지시서의 번호가 존재하지 않을 때
//		InstrucprodVO saved = dao.selectProductionInstruction(instrucProdVO);
//		if(saved == null) {
//			throw new PKNotFoundException(instrucProdVO.getInstNum() + "에 해당하는 생산지시서 없음");
//		}
		return dao.selectInstRawsList(instrucProdVO);
	}

	// 원자재 등록 및 수정
	@Transactional
	@Override
	public ServiceResult createOrUpdateProduct(List<InstRawsVO> instRawsList) {
		ServiceResult result = null;
		int successCnt = 0;
		
		for(InstRawsVO instRaws : instRawsList) {
			Integer prQty = instRaws.getPrQty();
			if(prQty!=null) {
				InstrucprodVO inst = new InstrucprodVO();
				inst.setInstNum(instRaws.getInstNum());
				InstrucprodVO savedInst = dao.selectProductionInstruction(inst);
				if(prQty < savedInst.getInstLeadqty()) {
					return ServiceResult.FAILED;
				}
			}
			Integer instNo = instRaws.getInstNo();
			if(instNo == null) {
				// 등록
				int cnt = dao.insertProductionRaws(instRaws);
				if(cnt > 0) {
					successCnt++;
				}
			}else {
				// 수정
				int cnt = dao.updateProductRaws(instRaws);
				if(cnt > 0) {
					successCnt++;
				}
			}
		}
		if(successCnt == instRawsList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	// 원자재 삭제
	@Transactional
	@Override
	public ServiceResult deleteProduct(List<InstRawsVO> instRawsList) {
		ServiceResult result = null;
		int successCnt = 0;
		
		for(InstRawsVO instRaws : instRawsList) {
			InstRawsVO saved = dao.selectProductionRaws(instRaws);
			if(saved != null) {
				int cnt = dao.deleteProducRaws(instRaws);
				if(cnt > 0) {
					successCnt++;
				}
			}else {
				result = ServiceResult.PKDUPLICATED;
			}
		}
		if(successCnt == instRawsList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public InstrucprodVO retrieveProductionInstruction(InstrucprodVO instrucProdVO) {
		return dao.selectProductionInstruction(instrucProdVO);
	}

	@Override
	public InstRawsVO retrieveProductionRaws(InstRawsVO instRaws) {
		return dao.selectProductionRaws(instRaws);
	}
	
	// --------------------------수주서 적용-------------------------------
	// 수주서 목록 조회
	@Override
	public List<OrderBookVO> retrieveOrderBookListForPI(HubSearchVO hubSearch) {
		return dao.selectOrderBookListForPI(hubSearch);
	}

	// 수주서-제품 목록 조회
	@Override
	public List<OrderBookProdVO> retrieveOrderBookProdListforPI(OrderBookVO orderBookVO) {
//		OrderBookVO saved = dao.selectOrderBookforPI(orderBookVO);
//		if(saved == null) {
//			throw new PKNotFoundException(orderBookVO.getOrbNum() + "에 해당하는 제품 없음");
//		}
		return dao.selectOrderBookProdListforPI(orderBookVO); 
	}

	// 수주서 적용
	@Transactional
	@Override
	public ServiceResult insertInstProdByOb(List<OrderBookVO> orderBookVOList) {
		ServiceResult result = null;
		int successCnt = 0;
		int odbpdCnt = 0;
		boolean flag = true;
		
		Calendar now = Calendar.getInstance();
		int yy = now.get(Calendar.YEAR);
		int mm = now.get(Calendar.MONTH)+1;
		int dd = now.get(Calendar.DATE);
		String instDate = yy+"/"+mm+"/"+dd;
		
		for(int i = 0; i < orderBookVOList.size(); i++) {
			List<OrderBookProdVO> obpdVO = obDao.selectOrderbookProdList(orderBookVOList.get(i));
			if(obpdVO.isEmpty()) {
				flag = false;
			}
		}
		
		if(flag) {
			for(OrderBookVO order : orderBookVOList) {
				OrderBookVO saved = obDao.selectOrderbook(order);	// 수주서
				List<OrderBookProdVO> obProd = dao.selectOrderBookProdListforPI(saved);//수주서-품목
				if(saved != null) {	// 수주서 존재 시
					for(OrderBookProdVO odb : obProd) {
						if(odb.getIcodeName().equals("제품") || odb.getIcodeName().equals("반제품")) {
							InstrucprodVO instVO = new InstrucprodVO();
							instVO.setInstDate(instDate);
							instVO.setProdCode(odb.getProdCode());
							instVO.setProdName(odb.getProdName());
							instVO.setGcommName(odb.getGcommName());
							instVO.setInstLeadqty(odb.getOpQty());
							instVO.setIcodeName(odb.getIcodeName());
							instVO.setOrbNum(odb.getOrbNum());
							int cnt = dao.insertProductionInstruction(instVO);
							if(cnt > 0) {
								
								// 생산지시서 추가시 문서체크
								int cntCheck = dao.insertCheck(instVO);
								
								//BOM전개 추가!!!!
								String icd = dao.selectIcodeName(instVO.getProdCode());
								List<BomRawsVO> selectBomRawsList;
								if(icd.equals("반제품")) {
									selectBomRawsList = dao.selectBomRawByRaws(instVO.getProdCode());
								}else {
									selectBomRawsList = dao.selectBomRawByProd(instVO.getProdCode());
								}
								if(!selectBomRawsList.isEmpty()) {
									Integer leadQty = instVO.getInstLeadqty();
									if(leadQty == null) {
										leadQty = 1;
									}
									for(BomRawsVO bomRaw : selectBomRawsList) {
										bomRaw.setInstLeadqty(leadQty);
										bomRaw.setInstNum(instVO.getInstNum());
										bomRaw.setBomRawsList(selectBomRawsList);
										int bomCnt = dao.insertInstRawsByBomApply(bomRaw);
//										if(bomCnt > 0) {
//											odbpdCnt++;
//										}
									}
								}
								order.setOrbAppyn("Y");
								int applyYn = dao.updateOderBookApplyYn(order);
								if(applyYn > 0) {
									odbpdCnt++;
								}
							}else {
								return ServiceResult.FAILED;
							}
						}else {
							return ServiceResult.FAILED;
						}
					}
					if(obProd.size() == odbpdCnt) {
						successCnt++;
					}
				}else {
					return ServiceResult.PKDUPLICATED;
				}
			}
		}
		if(orderBookVOList.size() == successCnt) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	
	
	//-------------------------------BOM전개----------------------------

	@Override
	@Transactional
	public ServiceResult createInstRawsByBomApply(List<BomRawsVO> bomRawsList) {
		ServiceResult result = null;
		int successCnt = 0;
		
		for(BomRawsVO bomRawsVO : bomRawsList) {
			// 지시수량 구하기 위함...
			InstrucprodVO instProd = new InstrucprodVO();
			instProd.setInstNum(bomRawsVO.getInstNum());
			InstrucprodVO saved = dao.selectProductionInstruction(instProd);
			
			Integer leadQty = saved.getInstLeadqty();
			if(leadQty == null) {
				leadQty = 1;
			}
			bomRawsVO.setInstLeadqty(leadQty);	// 지시수량 set
			
			// bom 원재료 목록 조회
			List<BomRawsVO> selectBomRawsList;

			String icd = dao.selectIcodeName(bomRawsVO.getProdCode());
			if(icd.equals("반제품")) {
				selectBomRawsList = dao.selectBomRawByRaws(bomRawsVO.getProdCode());
			}else {
				selectBomRawsList = dao.selectBomRawByProd(bomRawsVO.getProdCode());
			}
			
			if(selectBomRawsList.isEmpty()) {
				result = ServiceResult.PKDUPLICATED;
			}else {
				InstrucprodVO instrucProdVO = new InstrucprodVO();
				instrucProdVO.setInstNum(bomRawsVO.getInstNum());
				List<InstRawsVO> irList = retrieveInstRawsList(instrucProdVO);
				
				if(!irList.isEmpty()) {
					// BOM전개적용을위한 기존데이터삭제
					int delCnt = dao.deleteAllProdRaws(bomRawsVO.getInstNum());
				}
				for(int i = 0; i < selectBomRawsList.size(); i++) {
					selectBomRawsList.get(i).setInstNum(bomRawsVO.getInstNum());
				}
				bomRawsVO.setBomRawsList(selectBomRawsList);
				int cnt = dao.insertInstRawsByBomApply(bomRawsVO);
				
				if(cnt > 0 ) {
					successCnt++;
				}
			}
		}
		if(successCnt == bomRawsList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	// 현재고
	@Override
	public List<ItemVO> retrieveStockStatus(HubSearchVO hubSearchVO) {
		return dao.selectStockStatus(hubSearchVO);
	}

}