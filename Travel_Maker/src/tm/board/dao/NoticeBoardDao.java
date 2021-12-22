package tm.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.search.vo.SearchVO;

public class NoticeBoardDao implements INoticeBoardDao{

	SqlMapClient smc;
	private static INoticeBoardDao dao;
	
	private NoticeBoardDao(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static INoticeBoardDao getInstance() {
		if(dao == null) {
			dao = new NoticeBoardDao();
		}
		return dao;
	}
	
	// 공지사항 목록
	@Override
	public List<BoardVO> noticeAllList() throws SQLException {
		return smc.queryForList("admin.noticeAllList");
	}
	
	// 공지사항 조회
	@Override
	public BoardVO noticeSelect(int tmBNo) throws SQLException {
		return (BoardVO) smc.queryForObject("admin.noticeSelect",tmBNo);
	}
	
	
	// 공지사항 게시글 등록 (관리자)
	@Override
	public int noticeInsert(BoardVO vo) throws SQLException {
		return (int)smc.update("admin.noticeInsert",vo);
	}

	// 공지사항 게시글 수정 (관리자)
	@Override
	public int noticeUpdate(BoardVO vo) throws SQLException {
		return (int)smc.update("admin.noticeUpdate",vo);
	}

	// 공지사항 게시글 삭제 (관리자)
	@Override
	public int noticeDelete(BoardVO vo) throws SQLException {
		return (int)smc.delete("admin.noticeDelete",vo);
	}

	// 공지사항 글 개수
	@Override
	public int getAllNoticeCount() throws SQLException {
		return (int) smc.queryForObject("admin.getAllNoticeCount");
	}

	// 조회수 
	@Override
	public int noticeHitUpdate(int tmBNo) throws SQLException {
		return (int)smc.update("admin.noticeHitUpdate",tmBNo);
	}

	@Override
	public List<BoardVO> selectByPage(PagingVO pagingvo) throws SQLException {
		return smc.queryForList("admin.selectByPage", pagingvo);
	}

	@Override
	public int checkNotUser(String tm_id) throws SQLException {
		return (int) smc.queryForObject("admin.checkNotUser",tm_id);
	}

	@Override
	public List<SearchVO> bestDestList() throws SQLException {
		return smc.queryForList("admin.bestSortLike");
	}


}
