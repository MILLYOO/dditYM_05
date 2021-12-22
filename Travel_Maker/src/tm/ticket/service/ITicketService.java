package tm.ticket.service;

import java.util.List;

import tm.comm.vo.CartVO;
import tm.comm.vo.PodVO;
import tm.comm.vo.ProdVO;

public interface ITicketService {

	
	public List<ProdVO> ticketSearch(ProdVO vo);
	
	// 상품id로 상품정보 조회
	public List<ProdVO> prodSearch(String prodId);
	
	// 장바구니 등록
	public int bascketInsert(CartVO vo);
	
	// 마이페이지 - 장바구니
	public List<CartVO> myCartList(String tmId);
	
	public List<CartVO> myBuyList(String tmId);
	
	public CartVO cartTotal(String tmId);
	
	public int cartUpdate(CartVO vo);
	
	public int cartDelete(CartVO vo);
	
	// 결제정보, 내역 등록
	public int payInfoInsert(PodVO vo);
	
	public int buyInfoInsert(String tmId);
	
	public int cartOxUpdate(CartVO vo);
}
