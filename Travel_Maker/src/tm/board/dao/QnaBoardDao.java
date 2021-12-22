package tm.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.comm.vo.ReplyVO;
import tm.member.vo.MemberVO;

public class QnaBoardDao implements IQnaBoardDao{
	
	SqlMapClient smc;
	private static IQnaBoardDao dao;
	
	private QnaBoardDao(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IQnaBoardDao getInstance() {
		if(dao == null) {
			dao = new QnaBoardDao();
		}
		return dao;
	}

	// 1:1 문의게시판 목록
	@Override
	public List<BoardVO> qnaAllList() throws SQLException {
		return smc.queryForList("admin.qnaAllList");
	}

	// 1:1 문의게시판 조회
	@Override
	public BoardVO qnaSelect(int tmBNo) throws SQLException {
		return (BoardVO) smc.queryForObject("admin.qnaSelect",tmBNo);
	}

	// 1:1 문의게시판 글 수정
	@Override
	public int qnaUpdate(BoardVO vo) throws SQLException {
		return smc.update("admin.qnaUpdate",vo);
	}

	// 1:1 문의게시판 글 삭제
	@Override
	public int qnaDelete(BoardVO vo) throws SQLException {
		return smc.delete("admin.qnaDelete",vo);
	}

	// 1:1 문의게시판 답변 조회
	@Override
	public List<ReplyVO> qnaReSelect(int tmBNo) throws SQLException {
		return smc.queryForList("admin.qnaReSelect",tmBNo);
	}

	// 1:1 문의게시판 답변 등록
	@Override
	public int qnaReInsert(ReplyVO vo) throws SQLException {
		return smc.update("admin.qnaReInsert",vo);
	}

	// 1:1 문의게시판 답변 수정
	@Override
	public int replyUpdate(ReplyVO vo) throws SQLException {
		return smc.update("admin.replyUpdate",vo);
	}

	// 1:1 문의게시판 답변 삭제
	@Override
	public int qnaReDelete(ReplyVO vo) throws SQLException {
		return smc.delete("admin.qnaReDelete",vo);
	}

	@Override
	public int qnaReOxUpdate(int tmBNo) throws SQLException {
		return smc.update("admin.qnaReOxUpdate",tmBNo);
	}

	@Override
	public int qnaReDelWith(int tmBNo) throws SQLException {
		return smc.delete("admin.qnaReDelWith",tmBNo);
	}

	@Override
	public int qnaReOxDel(int tmBno) throws SQLException {
		return smc.update("admin.qnaReOxDel",tmBno);
	}

	@Override
	public int qnaReCount(int tmBNo) throws SQLException {
		return (int) smc.queryForObject("admin.qnaReCount",tmBNo);
	}

	@Override
	public List<BoardVO> qnaSort(PagingVO pagingVO) throws SQLException {
		return smc.queryForList("admin.qnaSort",pagingVO);
	}

	@Override
	public int qnaAllCount() throws SQLException {
		return (int) smc.queryForObject("admin.qnaAllCount");
	}

	@Override
	public int qnaSearchCount(BoardVO boardVO) throws SQLException {
		return (int) smc.queryForObject("admin.qnaSearchCount",boardVO);
	}

	@Override
	public List<BoardVO> qnaSearch(MemberVO memberVO) throws SQLException {
		return smc.queryForList("admin.qnaSearch",memberVO);
	}

}
