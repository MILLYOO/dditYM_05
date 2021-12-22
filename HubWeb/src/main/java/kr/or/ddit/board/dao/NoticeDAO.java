package kr.or.ddit.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.board.vo.NoticeVO;
import kr.or.ddit.board.vo.PagingVO;

@Repository
public interface NoticeDAO {

	/**
	 * 공지사항 총 글 갯수
	 * @return 
	 */
	public int selectTotalRecord(PagingVO<NoticeVO> pagingVO);
	
	/**
	 * 공지사항 목록
	 * @return
	 */
	public List<NoticeVO> selectNoticeList(PagingVO<NoticeVO> pagingVO);
	
	/**
	 * 메인 공지
	 * @return
	 */
	public List<NoticeVO> selectNoticeListMain();
	
	/**
	 * row count > 0 성공
	 * @param notice
	 * @return
	 */
	public int insertNotice(NoticeVO notice);
	
	/**
	 * 해당 게시글 번호 존재하지 않으면 null 반환
	 * @param notice에서 notiNum
	 * @return
	 */
	public NoticeVO selectNotice(NoticeVO notice);
	
	
	/**
	 * row count > 0 성공
	 * @param notice
	 * @return
	 */
	public int updateNotice(NoticeVO notice);
	
	/**
	 * row count > 0 성공
	 * @param notice에서 notiNum
	 * @return
	 */
	public int deleteNotice(NoticeVO notice);

	
	/**
	 * 조회수 증가
	 * @return
	 */
	public int updateNotinumCount(int notiNum);
}
