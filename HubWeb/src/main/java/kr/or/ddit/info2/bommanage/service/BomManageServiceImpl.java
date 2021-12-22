package kr.or.ddit.info2.bommanage.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info2.bommanage.dao.BomManageDAO;
import kr.or.ddit.info2.vo.BomRawsVO;
import kr.or.ddit.info2.vo.BomVO;

@Service
public class BomManageServiceImpl implements BomManageService {
	@Inject
	private BomManageDAO dao;
	
	@Override
	public String selectBomCode(BomVO bom) {
		return dao.selectBomCode(bom);
	}

	@Override
	public List<BomRawsVO> bomRawsList(BomVO bom) {
		return dao.selectBomRawsList(bom);
	}

	@Override
	public BomVO selectBom(BomVO bom) {
		return dao.selectBom(bom);
	}
	
	@Override
	@Transactional
	public ServiceResult createBom(BomVO bom) {
		ServiceResult result = null;
		int rowcnt = dao.insertBom(bom);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	@Transactional
	public ServiceResult deleteBom(BomVO bom) {
		ServiceResult result = null;
		int rowcnt = dao.deleteBom(bom);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	@Transactional
	public ServiceResult bomRawsCreateOrUdate(CommonListVO<BomRawsVO> bomRaws) {
		ServiceResult result = null;
		int successCnt = 0;
		for(BomRawsVO bomRaw : bomRaws.getCommonList()) {
			if(bomRaw.getBomNo() == null) {
				int cnt = dao.insertBomRaws(bomRaw);
				if(cnt > 0) {
					successCnt++;
				}
			}else {
				int cnt = dao.updateBomRaws(bomRaw);
				if(cnt > 0) {
					successCnt++;
				}
			}
		}
		if(successCnt == bomRaws.getCommonList().size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	@Transactional
	public ServiceResult deleteRaws(CommonListVO<BomRawsVO> bomRaws) {
		ServiceResult result = null;
		int successCnt = 0;
		for(BomRawsVO bomRaw : bomRaws.getCommonList()) {
				int cnt = dao.deleteBomRaws(bomRaw);
				if(cnt > 0) {
					successCnt++;
				}
		}
		if(successCnt == bomRaws.getCommonList().size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}


}
