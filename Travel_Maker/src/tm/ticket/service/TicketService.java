package tm.ticket.service;

import java.sql.SQLException;
import java.util.List;

import tm.comm.vo.CartVO;
import tm.comm.vo.PodVO;
import tm.comm.vo.ProdVO;
import tm.ticket.dao.ITicketDao;
import tm.ticket.dao.TicketDao;

public class TicketService implements ITicketService{
	
	private static ITicketService service;
	
	private ITicketDao dao;
	
	private TicketService() {
		dao = TicketDao.getInstance();
	}
	
	public static ITicketService getInstance() {
		
		if(service == null) {
			service = new TicketService();
		}
		return service;
	}

	@Override
	public List<ProdVO> ticketSearch(ProdVO vo) {
		List<ProdVO> list = null;
		try {
			list = dao.ticketSearch(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProdVO> prodSearch(String prodId) {
		List<ProdVO> list = null;
		try {
			list = dao.prodSearch(prodId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int bascketInsert(CartVO vo) {
		int res = 0;
		try {
			res = dao.bascketInsert(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<CartVO> myCartList(String tmId) {
		List<CartVO> list = null;
		try {
			list = dao.myCartList(tmId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public CartVO cartTotal(String tmId) {
		CartVO vo = null;
		try {
			vo = dao.cartTotal(tmId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int cartUpdate(CartVO vo) {
		int res = 0;
		try {
			res = dao.cartUpdate(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int cartDelete(CartVO vo) {
		int res = 0;
		try {
			res = dao.cartDelete(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int payInfoInsert(PodVO vo) {
		int res = 0;
		try {
			res = dao.payInfoInsert(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int buyInfoInsert(String tmId) {
		int res = 0;
		try {
			res = dao.buyInfoInsert(tmId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int cartOxUpdate(CartVO vo) {
		int res = 0;
		try {
			res = dao.cartOxUpdate(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<CartVO> myBuyList(String tmId) {
		List<CartVO> list = null;
		try {
			list = dao.myBuyList(tmId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
