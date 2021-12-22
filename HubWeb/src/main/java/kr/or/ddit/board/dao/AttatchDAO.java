package kr.or.ddit.board.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.NoticeVO;

@Repository
public interface AttatchDAO {
	
	/**
	 * 
	 * @param NoticeVO notice (NoticeVO의 attatchList)
	 * @return attatchList의 size
	 */
	public int insertAttatches(NoticeVO notice);
	
	/**
	 * 다운로드 시 사용할 하나의 첨부파일에 대한 메타데이터 조회
	 * @param attatchVO의 attNo
	 * @return
	 */
	public AttatchVO selectAttatch(AttatchVO attatchVO);
	
	
	/**
	 * @param notice
	 * @return 삭제한 첨부파일 수
	 */
	public int deleteAttatches(NoticeVO notice);

}
