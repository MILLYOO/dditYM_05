package tm.board.service;

import java.util.List;
import java.util.Map;

import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.search.vo.SearchVO;

public interface INoticeBoardService {
	
		// 베스트 여행지 목록
		public List<SearchVO> bestDestList();

		// 공지사항 목록 
		public List<BoardVO> noticeAllList();
		
		// 공지사항 조회
		public BoardVO noticeSelect(int tmBNo);
		
		// 공지사항 추가 (관리자)
		public int noticeInsert(BoardVO vo);
		
		// 공지사항 수정 (관리자)
		public int noticeUpdate(BoardVO vo);
		
		// 공지사항 삭제 (관리자)
		public int noticeDelete(BoardVO vo);
	
		// 공지사항 글 개수
		public int getAllNoticeCount();
		
		// 조회수
		public int noticeHitUpdate(int tmBNo);
		
		// 페이지 별 리스트
		public List<BoardVO> selectByPage(PagingVO pagingvo);
		
		//일반회원 체크
		public int checkNotUser(String tm_id);
}
