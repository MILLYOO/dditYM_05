package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.NoticeVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.enumpkg.ServiceResult;

public interface NoticeService {
	
	/**
	 * 공지사항 게시글 등록
	 * 성공 , 실패
	 * @param notice
	 * @return
	 */
	public ServiceResult createNotice(NoticeVO notice);
	
	/**
	 * totalRecord 만들어줘
	 * @param pagingVO
	 * @return
	 */
	public List<NoticeVO> retrieveNoticeList(PagingVO<NoticeVO> pagingVO);
	
	/**
	 * 메인 공지
	 * @return
	 */
	public List<NoticeVO> retrieveNoticeListMain();
	
	/**
	 * 게시글 번호 삭제되고 없다면 PKNF발생
	 * @param notice에서 notiNum
	 * @return
	 */
	public NoticeVO retrieveNotice(NoticeVO notice);
	
	/**
	 * 게시글 존재 여부 PKNF
	 * @param notice
	 * @return
	 */
	public ServiceResult modifyNotice(NoticeVO notice);
	
	/**
	 * 게시글 존재 여부 PKNF
	 * @param notice
	 * @return
	 */
	public ServiceResult removeNotice(NoticeVO notice);
	
	/**
	 * 있거나, PKNF
	 * @param attatch에서 attNo
	 * @return
	 */
	public AttatchVO download(AttatchVO attatch);
}
