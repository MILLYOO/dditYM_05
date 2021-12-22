package kr.or.ddit.board.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

/**
 * Attatch 테이블을 대상으로 한 CRD
 */
@Repository
public interface AttatchDAO {
	/**
	 * @param board
	 * @return Attatch파일의 갯수만큼 반환하면 OK, 아니면 Fail
	 */
	public int insertAttatches(BoardVO board); // 한쿼리를 통해서 3개의 인서트가 가능하다 InsertAll > BoardVO안에 있는 attatchList 를 통해서 넣자.
	
	/**
	 * 다운로드시 사용할 하나의 첨부파일에 대한 메타데이터 조회
	 * @param attNo
	 * @return
	 */
	public AttatchVO selectAttatch(int attNo);
	public int deleteAttatches(BoardVO board);
}
