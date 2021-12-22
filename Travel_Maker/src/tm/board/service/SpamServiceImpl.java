package tm.board.service;

import java.sql.SQLException;
import java.util.List;

import tm.board.dao.ISpamDao;
import tm.board.dao.SpamDaoImpl;
import tm.comm.vo.BoardVO;



public class SpamServiceImpl implements ISpamService{
	
	private ISpamDao dao;
	private static ISpamService service;
	
	private SpamServiceImpl() {
		dao = SpamDaoImpl.getInstance();
	}
	
	public static ISpamService getInstance() {
		if(service == null) {
			service = new SpamServiceImpl();
		}
		return service;
	}
	

	@Override
	public List<BoardVO> spamAllList() {
		List<BoardVO> list = null;
		try {
			list = dao.spamAllList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BoardVO spamSelect(BoardVO vo) {
		BoardVO boardVO = null;
		try {
			boardVO = dao.spamSelect(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardVO;
	}

	@Override
	public int spamUpdate(BoardVO vo) {
		int res = 0;
		try {
			res = dao.spamUpdate(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int spamDelete(BoardVO vo) {
		int res = 0;
		try {
			res = dao.spamDelete(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int spamDeleteWithLike(BoardVO vo) {
		int res = 0;
		try {
			res = dao.spamDeleteWithLike(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
