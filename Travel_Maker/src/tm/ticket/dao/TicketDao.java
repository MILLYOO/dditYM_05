package tm.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.CartVO;
import tm.comm.vo.PodVO;
import tm.comm.vo.ProdVO;

public class TicketDao implements ITicketDao{

private static ITicketDao dao;
	
	private SqlMapClient smc;
	
	private TicketDao() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ITicketDao getInstance() {
		
		if(dao == null) {
			dao = new TicketDao();
		}
		return dao;
	}
	
	
	@Override
	public List<ProdVO> ticketSearch(ProdVO vo) throws SQLException {
		return smc.queryForList("ticket.ticketSearch",vo);
	}

	@Override
	public List<ProdVO> prodSearch(String prodId) throws SQLException {
		return smc.queryForList("ticket.prodSearch",prodId);
	}

	@Override
	public int bascketInsert(CartVO vo) throws SQLException {
		return (int) smc.update("ticket.bascketInsert", vo);
	}

	@Override
	public List<CartVO> myCartList(String tmId) throws SQLException {
		return smc.queryForList("ticket.myCartList",tmId);
	}

	@Override
	public CartVO cartTotal(String tmId) throws SQLException {
		return (CartVO) smc.queryForObject("ticket.cartTotal",tmId);
	}

	@Override
	public int cartUpdate(CartVO vo) throws SQLException {
		return smc.update("ticket.cartUpdate",vo);
	}

	@Override
	public int cartDelete(CartVO vo) throws SQLException {
		return smc.delete("ticket.cartDelete",vo);
	}

	@Override
	public int payInfoInsert(PodVO vo) throws SQLException {
		return smc.update("ticket.payInfoInsert",vo);
	}

	@Override
	public int buyInfoInsert(String tmId) throws SQLException {
		return smc.update("ticket.buyInfoInsert",tmId);
	}

	@Override
	public int cartOxUpdate(CartVO vo) throws SQLException {
		return smc.update("ticket.cartOxUpdate",vo);
	}

	@Override
	public List<CartVO> myBuyList(String tmId) throws SQLException {
		return smc.queryForList("ticket.myBuyList",tmId);
	}

}
