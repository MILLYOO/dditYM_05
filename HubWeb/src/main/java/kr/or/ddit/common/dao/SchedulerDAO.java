package kr.or.ddit.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.SchedulerVO;

@Repository
public interface SchedulerDAO {
	
	/**
	 * 스케쥴러리스트조회
	 * @return
	 */
	public List<SchedulerVO> selectSchedulerList ();
	
	/**
	 * 스케쥴러등록
	 * @param scVO
	 * @return
	 */
	public int insertScheduler(SchedulerVO scVO);
	
	
	/**
	 * scNo조회
	 * @param scVO
	 * @return
	 */
	public int selectScNo(SchedulerVO scVO);
	
	/**
	 * 스케쥴러수정
	 * @param scVO
	 * @return
	 */
	public int updateSchedulter(SchedulerVO scVO);
	
	/**
	 * 스케줄러 삭제
	 * @param scNo
	 * @return
	 */
	public int deleteScheduler(int scNo);
}
