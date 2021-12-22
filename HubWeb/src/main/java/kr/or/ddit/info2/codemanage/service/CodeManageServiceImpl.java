package kr.or.ddit.info2.codemanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info2.codemanage.dao.CodeManageDAO;
import kr.or.ddit.info2.vo.CommonVO;
import kr.or.ddit.info2.vo.DivisionVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CodeManageServiceImpl implements CodeManageService {
	@Inject
	private CodeManageDAO dao;
	
	@Override
	public List<CommonVO> retrieveCodeList(CommonVO common) {
		return dao.selectCodeList(common);
	}
	
	@Transactional
	@Override
	public ServiceResult createCode(CommonVO common) {
		ServiceResult result = null;
		int rowCnt = dao.insertCode(common);
		if(rowCnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	
	@Transactional
	@Override
	public ServiceResult updateCode(CommonVO common) {
		ServiceResult result = null;
		int rowCnt = dao.updateCode(common);
		if(rowCnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult deleteCode(CommonVO common) {
		ServiceResult result = null;
		int rowCnt = dao.delteCode(common);
		if(rowCnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<DivisionVO> retrieveDivList(DivisionVO division) {
		return dao.selectDivList(division);
	}

	@Override
	public ServiceResult createDiv(DivisionVO division) {
		ServiceResult result = null;
		int rowCnt = dao.insertDiv(division);
		if(rowCnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult updateDiv(DivisionVO division) {
		ServiceResult result = null;
		int rowCnt = dao.updateDiv(division);
		if(rowCnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteDiv(DivisionVO division) {
		ServiceResult result = null;
		int rowCnt = dao.deleteDiv(division);
		if(rowCnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
