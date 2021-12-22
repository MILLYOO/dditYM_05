package tm.comm.service;

import java.util.HashMap;

import tm.comm.vo.LikeHateVO;

public interface IBoardLikeService {

		// 좋아요 추가
	public int addLike(LikeHateVO vo);	
	
		// 좋아요 취소
	public int cancelLike(LikeHateVO vo);
	
		// 싫어요 추가
	public int addHate(LikeHateVO vo);
	
		// 싫어요 취소
	public int cancelHate(LikeHateVO vo);
	
		// 좋아요 개수 조회
	public int getLikeCount(LikeHateVO vo);
	
		// 싫어요 개수 조회
	public int getHateCount(LikeHateVO vo);
	
	
		// 좋아요 여부 조회
	public int chkLike(LikeHateVO vo);
	
		// 싫어요 여부 조회
	public int chkHate(LikeHateVO vo);
	
}
