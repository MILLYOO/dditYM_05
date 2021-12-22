package kr.or.ddit.common.service;

import java.util.List;

import kr.or.ddit.common.vo.SchedulerVO;
import kr.or.ddit.enumpkg.ServiceResult;

public interface SchedulerService {
	
	/**
	 * 스케쥴러 목록 조회
	 * @return
	 */
	public List<SchedulerVO> retrieveSchedulerList();
	
	/**
	 * 스케쥴러 등록
	 * @param scVO
	 * @return
	 */
	public ServiceResult createScheduler(SchedulerVO scVO);
	
	/**
	 * scNo찾기
	 * @param scVO
	 * @return
	 */
	public int retrieveScNo(SchedulerVO scVO);
	
	/**
	 * 스케줄러수정
	 * @param scVO
	 * @return
	 */
	public ServiceResult modifySchedulter(SchedulerVO scVO);
	
	/**
	 * 스케줄러 삭제
	 * @param scNo
	 * @return
	 */
	public ServiceResult deleteScheduler(int scNo);

}
