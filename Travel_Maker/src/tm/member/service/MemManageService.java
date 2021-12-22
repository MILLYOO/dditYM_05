package tm.member.service;

import java.sql.SQLException;
import java.util.List;

import tm.comm.vo.PagingVO;
import tm.member.dao.IMemManageDao;
import tm.member.dao.MemManageDao;
import tm.member.vo.MemberVO;

public class MemManageService implements IMemManageService{
	
	private IMemManageDao dao;
	private static IMemManageService service;

	private MemManageService() {
		dao = MemManageDao.getDao();
	}
	
	public static IMemManageService getservice() {  
		if(service == null) service = new MemManageService();
		
		return service;
	}

	@Override
	public List<MemberVO> memAllList(PagingVO pagingVO) {
		List<MemberVO> list = null;
		try {
			list = dao.memAllList(pagingVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public MemberVO memSelect(String id) {
		MemberVO vo = null;
		try {
			vo = dao.memSelect(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public List<MemberVO> memSearch(MemberVO vo) {
		List<MemberVO> list = null;
		try {
			list = dao.memSearch(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int memUpdate(MemberVO vo) {
		int res = 0;
		try {
			res = dao.memUpdate(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int memDelete(String id) {
		int res = 0;
		try {
			res = dao.memDelete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int memAllCount() {
		int res = 0;
		try {
			res = dao.memAllCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int memSearchCount(MemberVO memvo) {
		int res = 0;
		try {
			res = dao.memSearchCount(memvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
