package kr.or.ddit.produce.productincoming.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.produce.merterialrelease.dao.MaterialReleaseDAO;
import kr.or.ddit.produce.productincoming.dao.ProductIncomingDAO;
import kr.or.ddit.produce.vo.InstrucprodVO;
import kr.or.ddit.produce.vo.MreleaseRawsVO;
import kr.or.ddit.produce.vo.ProdWareVO;
import kr.or.ddit.produce.vo.PrwaProdVO;
import kr.or.ddit.stock.vo.WarProdVO;
import kr.or.ddit.stock.vo.WarRawVO;

@Service
public class ProductIncomingServiceImpl implements ProductIncomingService {
	
	@Resource(name="productIncomingDAO")
	private ProductIncomingDAO dao;
	
	@Resource(name="materialReleaseDAO")
	private MaterialReleaseDAO mrdao;
	
	// 생산품입고처리서 목록 조회
	@Override
	public List<ProdWareVO> retrieveProductIncomingList(HubSearchVO hubSearch) {
		return dao.selectProdIncomingList(hubSearch);
	}

	// 생산품입고처리서 1건 조회
	@Override
	public ProdWareVO retrieveProductIncoming(ProdWareVO prodWareVO) {
		return dao.selectProdIncoming(prodWareVO);
	}

	// 생산품입고처리서 등록+수정
	@Transactional
	@Override
	public ServiceResult createOrUpdateProductIncoming(List<ProdWareVO> prodWareList) {
		ServiceResult result = null;
		int successCnt = 0;
		
		for(ProdWareVO prodWare : prodWareList) {
			// 날짜 set
			String year = prodWare.getPdcYy();
			String month = prodWare.getPdcMm();
			String day = prodWare.getPdcDd();
			if(Integer.parseInt(month) < 10) {
				month = "0"+month;
			}else if(Integer.parseInt(day) < 10) {
				day = "0"+day;
			}
			String arrDate = year+month+day;
			prodWare.setArrDate(arrDate);
			
			// 문서번호여부로 등록 or 수정 여부
			if(StringUtils.isBlank(prodWare.getArrNum())) {
				// 등록
				int cnt = dao.insertProdIncoming(prodWare);
				if(cnt > 0) {
					// 새로운 문서 등록시 체크하기 위한 매서드
					int cntCheck = dao.insertCheck(prodWare);
					if (cntCheck > 0) {
						successCnt++;
					}
				}
			}else {
				// 수정
				int cnt = dao.updateProdIncoming(prodWare);
				if(cnt > 0) {
					successCnt++;
				}
			}
		}
		if(successCnt == prodWareList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}


	// 생산품입고처리서 삭제
	@Transactional
	@Override
	public ServiceResult deleteProductIncoming(List<ProdWareVO> ProdWare) {
		ServiceResult result = null;
		int successCnt = 0;
		
		for(ProdWareVO pw : ProdWare) {
			ProdWareVO saved = retrieveProductIncoming(pw);
			List<PrwaProdVO> prwaList = retrieveProductIncomingProductList(saved);
			if(saved != null) {
				if(prwaList.isEmpty()) {
					int cnt = dao.deleteProductIncoming(saved);
					if(cnt > 0) {
						successCnt++;
					}
				}else {
					ServiceResult delPrwa = deleteIncomingProduct(prwaList);
					if(delPrwa.equals(ServiceResult.OK)) {
						int cnt = dao.deleteProductIncoming(saved);
						if(cnt > 0) {
							successCnt++;
						}
					}
				}
			}
		}
		if(successCnt == ProdWare.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	// 생산품입고처리서-품목 리스트 조회
	@Override
	public List<PrwaProdVO> retrieveProductIncomingProductList(ProdWareVO ProdWare) {
		List<PrwaProdVO> prwaList = dao.selectProdIncomingRawsList(ProdWare);
		for(PrwaProdVO prwa : prwaList) {
			if(StringUtils.isBlank(prwa.getProdCode())) {
				prwa.setProdCode(prwa.getRawsCode());
			}
		}
		return prwaList;
	}

	// 생산품입고처리서-품목 1건 조회
	@Override
	public PrwaProdVO retrieveProductIncomingProduct(PrwaProdVO prwaProdVO) {
		return dao.selectProdIncomingRaws(prwaProdVO);
	}

	// 생산품입고처리서-품목 등록+수정
	@Transactional	
	@Override
	public ServiceResult createOrUpdateIncomingProduct(List<PrwaProdVO> prwaProdList) {
		ServiceResult result = null;
		int successCnt = 0;
		for(PrwaProdVO prwa : prwaProdList) {
			String code = prwa.getProdCode();
			String warName = prwa.getWarName();
			WarRawVO warRawVO = new WarRawVO();
			warRawVO.setRawCode(code);
			warRawVO.setWarName(warName);
			WarProdVO warProdVO = new WarProdVO();
			warProdVO.setProdCode(code);
			warProdVO.setWarName(warName);
			Integer ppQty = prwa.getPpQty();
			String icdName = prwa.getIcodeName();
			
			Integer arrNo = prwa.getArrNo();
			if(arrNo == null) {
				// 등록
				if(!icdName.equals("제품") && !icdName.equals("반제품")) {
					return ServiceResult.FAILED;
				}
				if(icdName.equals("반제품") && StringUtils.isNotBlank(code)) {
					prwa.setProdCode("");
					prwa.setRawsCode(code);	//반제품일시 rawCode에 저장
				}
				int cnt = dao.insertProdIncomingRaws(prwa);
				if(cnt > 0) {
					// 생산품입고처리 시 수량 증가
					if(icdName.equals("반제품")) {
						code = prwa.getRawsCode();
						warRawVO.setRawCode(code);
						if(StringUtils.isBlank(warName)) {
							List<String> warNameList = mrdao.selectWarName(code);
							warName = warNameList.get(0);
							warRawVO.setWarName(warName);
						}
						warRawVO.setMrQty(ppQty);
						MreleaseRawsVO mr = new MreleaseRawsVO();
						mr.setMrQty(ppQty);
						mr.setRawsCode(code);
						int updateCnt = 0;
						updateCnt += mrdao.updateRawsQtyPlus(mr);
						updateCnt += mrdao.updateWarQtyPlus(warRawVO);
						if(updateCnt == 2) {
							successCnt++;
						}
					}else if(icdName.equals("제품")){
						if(StringUtils.isBlank(warName)) {
							List<String> warNameList = dao.selectWarNameForProduct(code);
							warName = warNameList.get(0);
							warProdVO.setWarName(warName);
						}
						warProdVO.setPpQty(ppQty);
						int updateCnt = 0;
						updateCnt += dao.updateProductQtyPlus(prwa);
						updateCnt += dao.updateProductWarQtyPlus(warProdVO);
						if(updateCnt == 2) {
							successCnt++;
						}
					}
				}else {
					result = ServiceResult.FAILED;
				}
			}else {
				// 수정
				PrwaProdVO saved = dao.selectProdIncomingRaws(prwa);
				String rawsCode = saved.getRawsCode();
				String prodCode = saved.getProdCode();
				String instNum = prwa.getInstNum();
				if(StringUtils.isNotBlank(instNum)) {
					if(StringUtils.isNotBlank(rawsCode)) {	//반제품일경우
						if(!rawsCode.equals(prwa.getProdCode())) {
							return ServiceResult.BADREQUEST;
						}
					}else if(StringUtils.isNotBlank(prodCode)) {	//제품일경우
						if(!prodCode.equals(prwa.getProdCode())) {
							return ServiceResult.BADREQUEST;
						}
					}
				}
				
				Integer before = saved.getPpQty();
				if(saved.getIcodeName().equals("제품") && prwa.getIcodeName().equals("제품")) {
					int cnt = dao.updateProdIncomingRaws(prwa);
					if(cnt > 0) {
						int updateCnt = 0;
						int plusCnt = 0;
						if(before!=null) {
							String sWarName = saved.getWarName();
							if(StringUtils.isBlank(sWarName)) {
								List<String> warNameList = dao.selectWarNameForProduct(saved.getProdCode());
								sWarName = warNameList.get(0);
							}
							warProdVO.setWarName(sWarName);
							warProdVO.setProdCode(saved.getProdCode());
							warProdVO.setPpQty(before);
							plusCnt += dao.updateProductQtyMinus(saved);
							plusCnt += dao.updateProductWarQtyMinus(warProdVO);
							if(plusCnt == 2) {
								if(ppQty!=null) {
									if(StringUtils.isBlank(warName)) {
										List<String> warNameL = dao.selectWarNameForProduct(code);
										warName = warNameL.get(0);
									}
									warProdVO.setWarName(warName);
									warProdVO.setProdCode(code);
									warProdVO.setPpQty(ppQty);
									updateCnt += dao.updateProductQtyPlus(prwa);
									updateCnt += dao.updateProductWarQtyPlus(warProdVO);
									if(updateCnt == 2) {
										successCnt++;
									}
								}
							}
						}
					}
				}else if(saved.getIcodeName().equals("제품") && prwa.getIcodeName().equals("반제품")) {
					prwa.setProdCode("");
					prwa.setRawsCode(code);
					int cnt = dao.updateProdIncomingRaws(prwa);
					if(cnt > 0) {
						int plusCnt = 0;
						if(before!=null) {
							String sWarName = saved.getWarName();
							if(StringUtils.isBlank(sWarName)) {
								List<String> warNameList = dao.selectWarNameForProduct(saved.getProdCode());
								sWarName = warNameList.get(0);
							}
							warProdVO.setWarName(sWarName);
							warProdVO.setProdCode(saved.getProdCode());
							warProdVO.setPpQty(before);
							plusCnt += dao.updateProductQtyMinus(saved);
							plusCnt += dao.updateProductWarQtyMinus(warProdVO);
							if(plusCnt == 2) {
								if(ppQty!=null) {
									if(StringUtils.isBlank(warName)) {
										List<String> warNameL = mrdao.selectWarName(code);
										warName = warNameL.get(0);
									}
									warRawVO.setWarName(warName);
									warRawVO.setRawCode(code);
									warRawVO.setMrQty(ppQty);
									int updateCnt = 0;
									MreleaseRawsVO mr = new MreleaseRawsVO();
									mr.setMrQty(ppQty);
									mr.setRawsCode(code);
									updateCnt += mrdao.updateRawsQtyPlus(mr);
									updateCnt += mrdao.updateWarQtyPlus(warRawVO);
									if(updateCnt == 2) {
										successCnt++;
									}
								}
							}
						}
					}
				}else if(saved.getIcodeName().equals("반제품") && prwa.getIcodeName().equals("반제품")) {
					prwa.setProdCode("");
					prwa.setRawsCode(code);
					int cnt = dao.updateProdIncomingRaws(prwa);
					if(cnt > 0) {
						int updateCnt = 0;
						int plusCnt = 0;
						if(before!=null) {
							String sWarName = saved.getWarName();
							if(StringUtils.isBlank(sWarName)) {
								List<String> warNameList = mrdao.selectWarName(saved.getRawsCode());
								sWarName = warNameList.get(0);
							}
							warRawVO.setWarName(sWarName);
							warRawVO.setRawCode(saved.getRawsCode());
							warRawVO.setMrQty(before);
							MreleaseRawsVO mr = new MreleaseRawsVO();
							mr.setMrQty(before);
							mr.setRawsCode(rawsCode);
							plusCnt += mrdao.updateRawsQtyMinus(mr);
							plusCnt += mrdao.updateWarQtyMinus(warRawVO);
							if(plusCnt == 2) {
								if(ppQty!=null) {
									if(StringUtils.isBlank(warName)) {
										List<String> warNameL = mrdao.selectWarName(code);
										warName = warNameL.get(0);
									}
									warRawVO.setWarName(warName);
									warRawVO.setRawCode(code);
									warRawVO.setMrQty(ppQty);
									mr.setMrQty(ppQty);
									mr.setRawsCode(prwa.getRawsCode());
									updateCnt += mrdao.updateRawsQtyPlus(mr);
									updateCnt += mrdao.updateWarQtyPlus(warRawVO);
									if(updateCnt == 2) {
										successCnt++;
									}
								}
							}
						}
					}
				}else if(saved.getIcodeName().equals("반제품") && prwa.getIcodeName().equals("제품")) {
					prwa.setRawsCode("");
					int cnt = dao.updateProdIncomingRaws(prwa);
					if(cnt > 0) {
						int updateCnt = 0;
						int plusCnt = 0;
						if(before!=null) {
							String sWarName = saved.getWarName();
							if(StringUtils.isBlank(sWarName)) {
								List<String> warNameList = mrdao.selectWarName(saved.getRawsCode());
								sWarName = warNameList.get(0);
							}
							warRawVO.setWarName(sWarName);
							warRawVO.setRawCode(saved.getRawsCode());
							warRawVO.setMrQty(before);
							MreleaseRawsVO mr = new MreleaseRawsVO();
							mr.setMrQty(before);
							mr.setRawsCode(rawsCode);
							plusCnt += mrdao.updateRawsQtyMinus(mr);
							plusCnt += mrdao.updateWarQtyMinus(warRawVO);
							if(plusCnt == 2) {
								if(ppQty!=null) {
									if(StringUtils.isBlank(warName)) {
										List<String> warNameL = dao.selectWarNameForProduct(code);
										warName = warNameL.get(0);
									}
									warProdVO.setWarName(warName);
									warProdVO.setProdCode(code);
									warProdVO.setPpQty(ppQty);
									updateCnt += dao.updateProductQtyPlus(prwa);
									updateCnt += dao.updateProductWarQtyPlus(warProdVO);
									if(updateCnt == 2) {
										successCnt++;
									}
								}
							}
						}
					}
				}
			}
		}
		if(successCnt == prwaProdList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	// 생산품입고처리-품목 삭제
	@Transactional
	@Override
	public ServiceResult deleteIncomingProduct(List<PrwaProdVO> prwaProd) {
		ServiceResult result = null;
		int successCnt = 0;
		for(PrwaProdVO prwa : prwaProd) {
			PrwaProdVO saved = retrieveProductIncomingProduct(prwa);
			String icdName = saved.getIcodeName();
			Integer ppQty = saved.getPpQty();
			if(saved != null) {
				
				if(StringUtils.isNotBlank(saved.getInstNum())) {
					InstrucprodVO inst = new InstrucprodVO();
					inst.setInstNum(prwa.getInstNum());
					inst.setPwAppyn("N");
					int instCnt = dao.updateInstPwAppyn(inst);
					if(instCnt <= 0) {
						successCnt--;
					}
				}
				
				int cnt = dao.deleteProductIncomingRaws(prwa);
				if(cnt > 0) {
					String warName = saved.getWarName();
					if(icdName.equals("제품")) {
						if(StringUtils.isBlank(warName)) {
							List<String> warNameList = dao.selectWarNameForProduct(saved.getProdCode());
							warName = warNameList.get(0);
						}
						WarProdVO warProd = new WarProdVO();
						warProd.setProdCode(saved.getProdCode());
						warProd.setWarName(warName);
						warProd.setPpQty(ppQty);
						int updateCnt = 0;
						updateCnt += dao.updateProductQtyMinus(prwa);
						updateCnt += dao.updateProductWarQtyMinus(warProd);
						if(updateCnt == 2) {
							successCnt++;
						}
					}else if(icdName.equals("반제품")) {
						if(StringUtils.isBlank(warName)) {
							List<String> warNameList = mrdao.selectWarName(saved.getRawsCode());
							warName = warNameList.get(0);
						}
						WarRawVO warRaw = new WarRawVO();
						warRaw.setRawCode(saved.getRawsCode());
						warRaw.setWarName(warName);
						warRaw.setMrQty(ppQty);
						MreleaseRawsVO mr = new MreleaseRawsVO();
						mr.setMrQty(ppQty);
						mr.setRawsCode(prwa.getRawsCode());
						int updateCnt = 0;
						updateCnt += mrdao.updateRawsQtyMinus(mr);
						updateCnt += mrdao.updateWarQtyMinus(warRaw);
						if(updateCnt == 2) {
							successCnt++;
						}
					}else {
						return ServiceResult.FAILED;
					}
				}
			}
		}
		if(successCnt == prwaProd.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	
	// 생산지시서적용
	@Transactional
	@Override
	public ServiceResult createPiApplyProdIncoming(List<InstrucprodVO> instProdVO) {
		// 생산지시서 적용 할 생산품입고처리 번호
		String arrNum = instProdVO.get(0).getArrNum();
		ProdWareVO prodWareVO = new ProdWareVO();
		prodWareVO.setArrNum(arrNum);
		ProdWareVO pwVO = retrieveProductIncoming(prodWareVO);
		if(pwVO == null) {
			return ServiceResult.PKDUPLICATED;
		}
		
		// 생산품입고처리-품목 등록하기 위한 리스트
		List<PrwaProdVO> prwaProdList = new ArrayList<>();
		for(InstrucprodVO inst : instProdVO) {
			if(inst.getArrNum() != null) {
				continue;
			}
			String icodeName = dao.selectIcodeName(inst.getProdCode());
			List<String> warNm = null;
			PrwaProdVO prwa = new PrwaProdVO();
			prwa.setArrNum(arrNum);
			prwa.setInstNum(inst.getInstNum());
			if(icodeName.equals("제품")) {
				prwa.setProdCode(inst.getProdCode());
				warNm = dao.selectWarNameForProduct(inst.getProdCode());
			}else if(icodeName.equals("반제품")) {
				prwa.setRawsCode(inst.getProdCode());
				warNm = mrdao.selectWarName(inst.getProdCode());
			}
			prwa.setProdName(inst.getProdName());
			prwa.setGcommName(inst.getGcommName());
			prwa.setPpQty(inst.getInstLeadqty());
			prwa.setKcommName(inst.getKcommName());
			prwa.setIcodeName(icodeName);
			prwa.setWarName(warNm.get(0));
			prwaProdList.add(prwa);
			
			inst.setPwAppyn("Y");
			int appYnCnt = dao.updateInstPwAppyn(inst);
			// 생산지시서 적용시 새로운 문서 체크
			int cntCheck = dao.insertCheck(pwVO);
		}
		ServiceResult res = createOrUpdateIncomingProduct(prwaProdList);
		return res;
	}
	
	// 생산지시서목록
	@Override
	public List<InstrucprodVO> retrievePiApplyList(HubSearchVO hubSearchVO) {
		return dao.selectPiApplyList(hubSearchVO);
	}
}