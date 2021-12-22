package kr.or.ddit.produce.merterialrelease.service;

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
import kr.or.ddit.produce.merterialrelease.dao.MaterialReleaseDAO;
import kr.or.ddit.produce.productioninstruction.dao.ProductionInstructionDAO;
import kr.or.ddit.produce.vo.InstRawsVO;
import kr.or.ddit.produce.vo.InstrucprodVO;
import kr.or.ddit.produce.vo.MreleaseRawsVO;
import kr.or.ddit.produce.vo.MreleaseVO;
import kr.or.ddit.stock.vo.WarRawVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MaterialReleaseServiceImpl implements MaterialReleaseService {

	@Inject
	private MaterialReleaseDAO dao;
	
	@Resource(name="productionInstructionDAO")
	private ProductionInstructionDAO piDao;
	
	// 자재출고처리서 목록 조회
	@Override
	public List<MreleaseVO> retrieveMaterialReleaseList(HubSearchVO hubSearch) {
		return dao.selectMaterialReleaseList(hubSearch);
	}

	// 자재출고처리서 조회
	@Override
	public MreleaseVO retrieveMaterialRelease(MreleaseVO mreleaseVO) {
//		String mreNo = mreleaseVO.getMreNum();
//		if(StringUtils.isBlank(mreNo)) {
//			throw new PKNotFoundException(mreNo + " 에 해당하는 자재출고서 없음");
//		}
		return dao.selectMaterialRelease(mreleaseVO);
	}

	// 자재출고처리서 등록 및 수정
	@Transactional
	@Override
	public ServiceResult createOrUpdateMaterialRelease(List<MreleaseVO> mrelease) {
		ServiceResult result = null;
		int successCnt = 0;
		for(MreleaseVO mrVo : mrelease) {
			// 날짜 set
			String year = mrVo.getPdcYy();
			String month = mrVo.getPdcMm();
			String day = mrVo.getPdcDd();
			if(Integer.parseInt(month) < 10) {
				month = "0"+month;
			}else if(Integer.parseInt(day) < 10) {
				day = "0"+day;
			}
			String mreDate = year+month+day;
			mrVo.setMreDate(mreDate);
			
			// 문서번호여부로 등록 or 수정 여부
			if(StringUtils.isBlank(mrVo.getMreNum())){
				// 등록
				int cnt = dao.insertMaterialRelease(mrVo);
				if(cnt > 0) {
					// 새로운 문서 등록하면 체크하기 위한 매서드
					int cntCheck = dao.insertCheck(mrVo); 
					if (cntCheck > 0) {
						successCnt++;
					}
				}
			}else {
				// 수정
				MreleaseVO saved = dao.selectMaterialRelease(mrVo);
				String instNum = saved.getInstNum();
				if(StringUtils.isNotBlank(instNum)) {
					return ServiceResult.BADREQUEST;
				}
				int cnt = dao.updateMaterialRelease(mrVo);
				if(cnt > 0) {
					successCnt++;
				}
			}
		}
		if(successCnt == mrelease.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	// 자재출고처리서 삭제
	@Transactional
	@Override
	public ServiceResult deleteMaterialRelease(List<MreleaseVO> mreleaseList) {
		ServiceResult result = null;
		int successCnt = 0;
		for(MreleaseVO mrelease : mreleaseList) {

			if(StringUtils.isNotBlank(mrelease.getInstNum())) {
				InstrucprodVO inst = new InstrucprodVO();
				inst.setInstNum(mrelease.getInstNum());
				InstrucprodVO instVO = piDao.selectProductionInstruction(inst);
				if(instVO!=null) {
					List<MreleaseVO> piAppMrList = dao.selectPiAppMrList(mrelease);
					if(piAppMrList.size() == 1) {
						if(piAppMrList.get(0).getInstNum().equals(instVO.getInstNum())) {
							InstrucprodVO pi = new InstrucprodVO();
							pi.setInstNum(instVO.getInstNum());
							pi.setMrAppyn("N");
							int updateInst = dao.updateInstApplyYn(pi);
							if(updateInst <= 0) {
								successCnt--;
							}
						}
					}
				}
			}
			MreleaseRawsVO saved = new MreleaseRawsVO();
			saved.setMreNum(mrelease.getMreNum());
			List<MreleaseRawsVO> rawsList = dao.selectMaterialReleaseRawsList(saved);
			if(!rawsList.isEmpty()) {
				ServiceResult delRes = deleteRaws(rawsList);
				if(delRes.equals(ServiceResult.OK)) {
					successCnt++;
				}
			}else {
				int delCnt = dao.deleteMaterialRelease(mrelease);
				if(delCnt > 0) {
					successCnt++;
				}
			}
		}
		if(successCnt == mreleaseList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	// 자재출고처리서-원자재 목록 조회
	@Override
	public List<MreleaseRawsVO> retrieveMaterialReleaseRawsList(MreleaseRawsVO mreleaseRaws) {
//		MreleaseVO mreleaseVO = new MreleaseVO();
//		mreleaseVO.setMreNum(mreleaseRaws.getMreNum());
//		MreleaseVO saved = dao.selectMaterialRelease(mreleaseVO);
//		if(saved == null) {
//			throw new PKNotFoundException(mreleaseRaws.getMreNum() + " 에 해당하는 자재출고서 없음");
//		}
		return dao.selectMaterialReleaseRawsList(mreleaseRaws);
	}

	// 자재출고처리서-원자재 조회
	@Override
	public MreleaseRawsVO retrieveMaterialReleaseRaws(MreleaseRawsVO mreleaseRawsVO) {
//		Integer mreNo = mreleaseRawsVO.getMreNo();
//		if(mreNo == null) {
//			throw new PKNotFoundException(mreNo + " 에 해당하는 자재출고서-원자재 없음");
//		}
		return dao.selectMaterialReleaseRaw(mreleaseRawsVO);
	}

	// 자재출고처리서-원자재 등록 및 수정
	@Override
	@Transactional
	public ServiceResult createOrUpdateRaws(List<MreleaseRawsVO> mreleaseRawsList) {
		ServiceResult result = null;
		int successCnt = 0;
		
		for(MreleaseRawsVO mreleaseRaws : mreleaseRawsList) {
			
			String code = mreleaseRaws.getRawsCode();
			String icdName = mreleaseRaws.getIcodeName();
			String warName = mreleaseRaws.getWarName();
			if(StringUtils.isBlank(warName)) {
				List<String> warNameList = dao.selectWarName(code);
				warName = warNameList.get(0);
			}
			Integer mrQty = mreleaseRaws.getMrQty();
			WarRawVO warRaw = new WarRawVO();
			warRaw.setRawCode(code);
			warRaw.setWarName(warName);	
			warRaw.setMrQty(mrQty);

			Integer mreNo = mreleaseRaws.getMreNo();
			if(mreNo == null) {
				// 등록
				int cnt = dao.insertMaterialReleaseRaws(mreleaseRaws);
				if(cnt > 0) {
					// 자재출고서 등록 시 재고 감소
					if(!icdName.equals("제품")) {
						int rawUpdateCnt = 0;
						rawUpdateCnt += dao.updateRawsQtyMinus(mreleaseRaws);
						rawUpdateCnt += dao.updateWarQtyMinus(warRaw);
						if(rawUpdateCnt == 2) {
							successCnt++;
						}
					}else {
						successCnt++;
					}
				}
			}else {
				// 수정
				MreleaseRawsVO saved = dao.selectMaterialReleaseRaw(mreleaseRaws);
				Integer before = saved.getMrQty();
				int cnt = dao.updateMaterialReleaseRaws(mreleaseRaws);
				if(cnt > 0) {
					// 자재출고서 수정된 수량 만큼 재고 감소
					if(!icdName.equals("제품")) {
						warRaw.setMrQty(saved.getMrQty());
						int rawUpdateCnt = 0;
						int plusCnt = 0;
						plusCnt += dao.updateRawsQtyPlus(saved);
						plusCnt += dao.updateWarQtyPlus(warRaw);
						if(plusCnt == 2) {
							warRaw.setMrQty(mreleaseRaws.getMrQty());
							rawUpdateCnt += dao.updateRawsQtyMinus(mreleaseRaws);
							rawUpdateCnt += dao.updateWarQtyMinus(warRaw);
							if(rawUpdateCnt == 2) {
								successCnt++;
							}
						}
					}else {
						successCnt++;
					}
				}
			}
		}
		if(successCnt == mreleaseRawsList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	// 자재출고처리서-원자재 삭제
	@Transactional
	@Override
	public ServiceResult deleteRaws(List<MreleaseRawsVO> mreleaseRawsList) {
		
		ServiceResult result = null;
		int successCnt = 0;
		
		for(MreleaseRawsVO mreleaseRaws : mreleaseRawsList) {
			MreleaseRawsVO saved = dao.selectMaterialReleaseRaw(mreleaseRaws);
			if(saved != null) {
				int cnt = dao.deleteMaterialReleaseRaws(mreleaseRaws);
				if(cnt > 0) {
					// 자재출고서 삭제 시 재고 증가
					String code = mreleaseRaws.getRawsCode();
					String icdName = mreleaseRaws.getIcodeName();
					String warName = mreleaseRaws.getWarName();
					if(StringUtils.isBlank(warName)) {
						List<String> warNameList = dao.selectWarName(code);
						warName = warNameList.get(0);
					}
					if(!icdName.equals("제품")) {
						int rawUpdateCnt = 0;
						WarRawVO warRaw = new WarRawVO();
						warRaw.setRawCode(code);
						warRaw.setWarName(warName);
						warRaw.setMrQty(mreleaseRaws.getMrQty());
						rawUpdateCnt += dao.updateRawsQtyPlus(mreleaseRaws);
						rawUpdateCnt += dao.updateWarQtyPlus(warRaw);
						if(rawUpdateCnt == 2) {
							successCnt++;
						}
					}else {
						successCnt++;
					}
				}
			}else {
				result = ServiceResult.PKDUPLICATED;
			}
		}
		if(successCnt == mreleaseRawsList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	
	// 지시서적용
	@Transactional
	@Override
	public ServiceResult createPiApplyMrelease(List<InstrucprodVO> instProdVOList) {
		boolean flag = true;
		ServiceResult result = null;
		int mrCnt = 0;
		int mrRawCnt = 0;
		int rawUpdateCnt = 0;
		
		Calendar now = Calendar.getInstance();
		int yy = now.get(Calendar.YEAR);
		int mm = now.get(Calendar.MONTH)+1;
		int dd = now.get(Calendar.DATE);
		String mreDate = yy+"/"+mm+"/"+dd;
		
		for(int i = 0; i < instProdVOList.size(); i++) {
			List<InstRawsVO> piRawList = piDao.selectInstRawsList(instProdVOList.get(i));
			if(piRawList.isEmpty()) {
				flag = false;
			}
		}
		if(flag) {
			for(InstrucprodVO instVO : instProdVOList) {
				InstrucprodVO saved = piDao.selectProductionInstruction(instVO);	// 생산지시서 조회
				List<InstRawsVO> instRawsList = piDao.selectInstRawsList(instVO);	// 생산지시서-원자재 목록
				if(saved != null) {
					MreleaseVO mrVO = new MreleaseVO();
					mrVO.setProdCode(saved.getProdCode());
					mrVO.setIcodeName(saved.getIcodeName());
					mrVO.setProdName(saved.getProdName());
					mrVO.setGcommName(saved.getGcommName());
					mrVO.setMreDate(mreDate);
					mrVO.setInstNum(saved.getInstNum());
					int cnt = dao.insertMaterialRelease(mrVO);
					String mreNum = mrVO.getMreNum();
					if(cnt > 0) {
						// 자재출고서 등록 성공시 문서 체크
						int cntCheck = dao.insertCheck(mrVO); 
						for(InstRawsVO instRaws : instRawsList) {
							MreleaseRawsVO mrRaws = new MreleaseRawsVO();
							mrRaws.setMreNum(mreNum);
							mrRaws.setInstNum(instRaws.getInstNum());
							mrRaws.setRawsCode(instRaws.getRawsCode());
							mrRaws.setIcodeName(instRaws.getIcodeName());
							mrRaws.setRawsName(instRaws.getRawsName());
							mrRaws.setGcommName(instRaws.getGcommName());
							mrRaws.setUcommName(instRaws.getUcommName());
							mrRaws.setMrQty(instRaws.getPrQty());
							int rawCnt = dao.insertMaterialReleaseRaws(mrRaws);
							if(rawCnt > 0) {
								String icdName = instRaws.getIcodeName();
								String code = instRaws.getRawsCode();
								if(!icdName.equals("제품")) {
									WarRawVO warRaw = new WarRawVO();
									warRaw.setRawCode(code);
									List<String> warNameList = dao.selectWarName(code);
									warRaw.setWarName(warNameList.get(0));
									warRaw.setMrQty(mrRaws.getMrQty());
									rawUpdateCnt += dao.updateRawsQtyMinus(mrRaws);
									rawUpdateCnt += dao.updateWarQtyMinus(warRaw);
								}
								mrRawCnt++;
							}
						}
						if(instRawsList.size() == mrRawCnt) {
							instVO.setMrAppyn("Y");
							int applyYn = dao.updateInstApplyYn(instVO);
							if(applyYn > 0) {
								mrCnt++;
							}
						}
					}
				}else {
					return ServiceResult.PKDUPLICATED;
				}
			}
		}
		if(instProdVOList.size() == mrCnt) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	
	// BOM 전개
	@Transactional
	@Override
	public ServiceResult createBomApplyMrelease(List<BomRawsVO> bomRaws) {
		ServiceResult result = null;
		int successCnt = 0;
		int rawUpdateCnt = 0;
		
		for(BomRawsVO bom : bomRaws) {
			String icd = piDao.selectIcodeName(bom.getProdCode());
			List<BomRawsVO> bomList;
			if(icd.equals("반제품")) {
				bomList = piDao.selectBomRawByRaws(bom.getProdCode());
			}else {
				bomList = piDao.selectBomRawByProd(bom.getProdCode());
			}
			
			if(bomList.isEmpty()) {
				return ServiceResult.PKDUPLICATED;
			}else {
				MreleaseRawsVO mreleaseRaws = new MreleaseRawsVO();
				mreleaseRaws.setMreNum(bom.getMreNum());
				List<MreleaseRawsVO> mRawList = retrieveMaterialReleaseRawsList(mreleaseRaws);
				
				if(!mRawList.isEmpty()) {
					int delCnt = dao.deleteAllMaterialReleaseRaws(bom.getMreNum());
				}
				
				List<String> warNameList = null;
				String warName = null;
				String warNameRaw = null;
				for(BomRawsVO bomr : bomList) {
					warNameList = dao.selectWarName(bomr.getRawsCode());
					for(String war : warNameList) {
						if(war.contains("제품")) {
							warName = war;
							break;
						}else if(war.contains("원재료")) {
							warNameRaw = war;
							break;
						}
					}
					if(bomr.getIcodeName().equals("반제품")) {
						bomr.setWarName(warName);
					}else {
						bomr.setWarName(warNameRaw);
					}
					bomr.setInstNum(bom.getInstNum());
				}
				
				bom.setBomRawsList(bomList);
				int cnt = dao.insertMreleaseRawsByBom(bom);
				if(cnt > 0 ) {
					for(BomRawsVO bomR : bomList) {
						int mrQty = 0;
						if(bomR.getBrawLossrate()==0) {
							mrQty = bomR.getBrawRequireqty();
						}else {
							mrQty = (bomR.getBrawRequireqty()/bomR.getBrawLossrate())+bomR.getBrawRequireqty();
						}
						String codeRaw = bomR.getRawsCode();
						String icodeNameRaw = bomR.getIcodeName();
						if(!icodeNameRaw.equals("제품")) {
							WarRawVO warRaw = new WarRawVO();
							warRaw.setRawCode(codeRaw);
							warRaw.setWarName(warNameList.get(0));
							MreleaseRawsVO mrVo = new MreleaseRawsVO();
							mrVo.setRawsCode(codeRaw);
							mrVo.setMrQty(mrQty);
							warRaw.setMrQty(mrQty);
							rawUpdateCnt += dao.updateRawsQtyMinus(mrVo);
							rawUpdateCnt += dao.updateWarQtyMinus(warRaw);
						}
					}
					successCnt++;
				}
			}
		}
		if(successCnt == bomRaws.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	// 생산지시서 목록
	@Override
	public List<InstrucprodVO> retrieveInstAppList(HubSearchVO hubSearchVO) {
		return dao.selectInstApplyList(hubSearchVO);
	}

}
