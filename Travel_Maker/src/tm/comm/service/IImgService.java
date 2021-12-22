package tm.comm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import tm.comm.vo.ImgVO;

//첨부파일 처리를 위한 공통 서비스용 인터페이스
public interface IImgService {

	
	/**
	 * 첨부파일 목록을 저장하기 위한 메서드
	 * @param fileItemMap 저장할 FileItem을 담은 맵객체
	 * @return 저장한 첨부파일 정보
	 */
	public ImgVO saveAtchFileList(Map<String, Object[]> fileItemMap);
	
	/**
	 * 첨부파일 목록 조회
	 * @param atchFileVO
	 * @return
	 */
	public List<ImgVO> getAtchFileList(ImgVO imgVO);
	
	/**
	 * 첨부파일 세부정보 조회하기
	 * @param atchFileVO
	 * @return
	 */
	public ImgVO getAtchFileDetail(ImgVO imgVO);

	
}
