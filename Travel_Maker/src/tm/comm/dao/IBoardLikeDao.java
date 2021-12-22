package tm.comm.dao;

import java.sql.SQLException;
import java.util.HashMap;

import tm.comm.vo.LikeHateVO;

public interface IBoardLikeDao {
	
		// 좋아요 추가
	public int addLike(LikeHateVO vo) throws SQLException;	
	
		// 좋아요 취소
	public int cancelLike(LikeHateVO vo) throws SQLException;
	
		// 싫어요 추가
	public int addHate(LikeHateVO vo) throws SQLException;
	
		// 싫어요 취소
	public int cancelHate(LikeHateVO vo) throws SQLException;
	
		// 좋아요 개수 조회
	public int getLikeCount(LikeHateVO vo) throws SQLException;
	
		// 싫어요 개수 조회
	public int getHateCount(LikeHateVO vo) throws SQLException;
	
	
		// 좋아요 여부 조회
	public int chkLike(LikeHateVO vo) throws SQLException;
	
		// 싫어요 여부 조회
	public int chkHate(LikeHateVO vo) throws SQLException;

}
