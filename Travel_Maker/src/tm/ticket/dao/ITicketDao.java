package tm.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import tm.comm.vo.CartVO;
import tm.comm.vo.PodVO;
import tm.comm.vo.ProdVO;

public interface ITicketDao {
	
	public List<ProdVO> ticketSearch(ProdVO vo) throws SQLException;
	
	// 상품id로 상품정보 조회
	public List<ProdVO> prodSearch(String prodId) throws SQLException;

	// 장바구니 등록
	public int bascketInsert(CartVO vo) throws SQLException;
	
	// 마이페이지 - 장바구니
	public List<CartVO> myCartList(String tmId) throws SQLException;
	
	public List<CartVO> myBuyList(String tmId) throws SQLException;
	
	
	public CartVO cartTotal(String tmId) throws SQLException;
	
	public int cartUpdate(CartVO vo) throws SQLException;
	
	public int cartDelete(CartVO vo) throws SQLException;
	
	// 결제정보, 내역 등록
	public int payInfoInsert(PodVO vo) throws SQLException;
	
	public int buyInfoInsert(String tmId) throws SQLException;
	
	public int cartOxUpdate(CartVO vo) throws SQLException;
	
}

