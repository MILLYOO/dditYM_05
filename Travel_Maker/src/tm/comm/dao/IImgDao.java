package tm.comm.dao;

import java.sql.SQLException;
import java.util.List;
import tm.comm.vo.ImgVO;

public interface IImgDao {
	

	/**
	 * 첨부파일 저장
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public int insertAtchFile(ImgVO imgVO) throws SQLException;
	
	/**
	 * 첨부파일 세부정보 저장
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public int insertAtchFileDetail(ImgVO imgVO) throws SQLException;
	
	/**
	 * 첨부파일 목록 조회
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public List<ImgVO> getAtchFileList(ImgVO imgVO) throws SQLException;
	
	/**
	 * 첨부파일 세부정보 조회 메서드
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public ImgVO getAtchFileDetail(ImgVO imgVO) throws SQLException;
	
	

}
