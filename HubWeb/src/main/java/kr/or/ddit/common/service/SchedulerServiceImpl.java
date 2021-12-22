package kr.or.ddit.common.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.dao.SchedulerDAO;
import kr.or.ddit.common.vo.SchedulerVO;
import kr.or.ddit.enumpkg.ServiceResult;

@Service
public class SchedulerServiceImpl implements SchedulerService{

	@Inject
	private SchedulerDAO dao;
	
	@Override
	public List<SchedulerVO> retrieveSchedulerList() {
		List<SchedulerVO> scList = dao.selectSchedulerList();
		return scList;
	}

	@Override
	public ServiceResult createScheduler(SchedulerVO scVO) {
		ServiceResult result = null;
		String sDate = scVO.getScStart();
		String eDate = scVO.getScEnd();
		String scTitle = scVO.getScTitle();
		if(StringUtils.isBlank(sDate) || StringUtils.isBlank(scTitle)) {
			result = ServiceResult.FAILED;
		}
		sDate = sDate.substring(0,10);
		eDate = eDate.substring(0,10);
		if(!sDate.equals(eDate)) {
			scVO.setScStart(sDate+"T09:00:00");
			scVO.setScEnd(eDate+"T23:59:00");
		}
		int cnt = dao.insertScheduler(scVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result; 
	}

	@Override
	public int retrieveScNo(SchedulerVO scVO) {
		if(!scVO.getScStart().equals(scVO.getScEnd())) {
			String scStart = scVO.getScStart()+"T09:00:00";
			String scEnd = scVO.getScEnd()+"T23:59:00";
			scVO.setScStart(scStart);
			scVO.setScEnd(scEnd);
		}
		return dao.selectScNo(scVO);
	}

	@Override
	public ServiceResult modifySchedulter(SchedulerVO scVO) {
		ServiceResult result = null;
		String sDate = scVO.getScStart();
		String eDate = scVO.getScEnd();
		String scTitle = scVO.getScTitle();
		if(StringUtils.isBlank(sDate) || StringUtils.isBlank(scTitle)) {
			result = ServiceResult.FAILED;
		}
		sDate = sDate.substring(0,10);
		eDate = eDate.substring(0,10);
		if(!sDate.equals(eDate)) {
			scVO.setScStart(sDate+"T09:00:00");
			scVO.setScEnd(eDate+"T23:59:00");
		}
		int cnt = dao.updateSchedulter(scVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteScheduler(int scNo) {
		ServiceResult result = null;
		int cnt = dao.deleteScheduler(scNo);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
