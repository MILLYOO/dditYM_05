package tm.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import tm.board.dao.ITravelBoardDao;
import tm.board.dao.TravelBoardDao;
import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;

public class TravelBoardService implements ITravelBoardService{

	//변수선언
	private ITravelBoardDao dao;
	private static ITravelBoardService service;
	
	//생성자
	private TravelBoardService() {
		dao = TravelBoardDao.getInstance();
	}
	
	//getService()
	public static ITravelBoardService getService() {
		if(service == null) {
			service = new TravelBoardService();
		}
		return service;
	}
	
	
	//==============================================================================	
		@Override
		public List<BoardVO> selectAll() {
			List<BoardVO> list = null;
			
			try {
				list = dao.selectAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return list;
		}

		@Override
		public List<BoardVO> selectByPage(Map<String, Integer> map) {
			List<BoardVO> list = null;
			
			try {
				list = dao.selectByPage(map);
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
			return list;
		}

		@Override
		public int getTotalCount() {
			int cnt = 0;
			
			try {
				cnt = dao.getTotalCount();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return cnt;
		}

		@Override
		public int insertBoard(BoardVO vo) {
			int seq = 0;
			
			try {
				seq = dao.insertBoard(vo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return seq;
		}

		@Override
		public int replySave(ReplyVO vo) {
			
			int cnt = 0;
			
			try {
				cnt = dao.replySave(vo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return cnt;
		}

		@Override
		public List<ReplyVO> replyList(int TM_B_NO) {
			List<ReplyVO> list = null;
			
			try {
				list = dao.replyList(TM_B_NO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return list;
		}

		@Override
		public int replyModify(ReplyVO vo) {
			int res = 0;
			
			try {
				res = dao.replyModify(vo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			return res;
		}

		@Override
		public int replyDelete(int TM_BC_NO) {
			int res = 0;
			
			try {
				res = dao.replyDelete(TM_BC_NO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return res;
		}

//		@Override
//		public int hitUpdate(int seq) {
//			
//			int res = 0;
//			
//			try {
//				res = dao.hitUpdate(seq);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//			return res;
//		}
		
		

		/*@Override
		public int ModifyBoard(BoardVO vo) {
			int res = 0;
			
			try {
				res = dao.ModifyBoard(vo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return res;
		}

		@Override
		public int DeleteBoard(int TM_B_NO) {
			int res = 0;
			
			try {
				res = dao.DeleteBoard(TM_B_NO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return res;
		}*/

		@Override
		public BoardVO getBoard(int TM_B_NO) {
			BoardVO vo = null;
			
			try {
				vo = dao.getBoard(TM_B_NO);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			return vo;
		}

		

		@Override
		public List<BoardVO> selectByPageID(Map<String, Object> map) {
			List<BoardVO> list = null;
			
			try {
				list = dao.selectByPageID(map);
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
			return list;
		}
		
		@Override
		public String checkBoardWriter(int tm_b_no) {
			
			String tm_id = null;
			
			try {
				tm_id = dao.checkBoardWriter(tm_b_no);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return tm_id;
		}

		@Override
		public String checkReWriter(int tm_bc_no) {
			
			String tm_bc_writer = null;
			
			try {
				tm_bc_writer = dao.checkReWriter(tm_bc_no);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return tm_bc_writer;
		}

		@Override
		public int checkNotUser(String tm_id) {

			int tm_author = 0;
			
			try {
				tm_author = dao.checkNotUser(tm_id);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
					
			return tm_author;
		}

		@Override
		public int updateHit(int tm_b_no) {
			
			int hit = 0;
			
			try {
				hit = dao.updateHit(tm_b_no);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return hit;
		}

		@Override
		public int updateBoard(BoardVO boardVO) {
			
			int cnt = 0;
			
			try {
				cnt = dao.updateBoard(boardVO);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return cnt;
		}

		@Override
		public int deleteBoard(BoardVO boardVO) {
			
			int cnt = 0;
			
			try {
				cnt = dao.deleteBoard(boardVO);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return cnt;
		}

		@Override
		public int deleteReplyAll(int tm_b_no) {
			
			int cnt = 0;
			
			try {
				cnt = dao.deleteReplyAll(tm_b_no);
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			return cnt;
		}
	}