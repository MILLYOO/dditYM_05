package tm.comm.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.ImgVO;


public class ImgDaoImpl implements IImgDao{

	private SqlMapClient smc;
	private static IImgDao dao;
	
	private ImgDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IImgDao getInstance() {
		
		if(dao == null) {
			dao = new ImgDaoImpl();
		}
		
		return dao;
	}
	//--------------------------------------------------------------

	@Override
	public int insertAtchFile(ImgVO imgVO) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("img.insertAtchFile", imgVO);
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
		
	}

	@Override
	public int insertAtchFileDetail(ImgVO imgVO) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.update("img.insertAtchFileDetail", imgVO);
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public List<ImgVO> getAtchFileList(ImgVO imgVO) throws SQLException {
		
		List<ImgVO> atchFileList 
		= smc.queryForList("img.getAtchFileList", imgVO);
	
	return atchFileList;
	}

	@Override
	public ImgVO getAtchFileDetail(ImgVO imgVO) throws SQLException {
		
		ImgVO fileVO = 
				(ImgVO) smc.queryForObject(
						"img.getAtchFileDetail", imgVO);
		return fileVO;
	}
	


}
