package tm.my.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import tm.comm.vo.BoardVO;

public interface IMyBoardDao {

	//나의 글 전체 조회
		public List<BoardVO> selectAll(Map<String,Object> map) throws SQLException;
		
		

		// 전체 글 개수 가져오는거
		public int getTotalCount() throws SQLException;
}
