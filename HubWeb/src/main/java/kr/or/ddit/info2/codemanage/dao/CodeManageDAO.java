package kr.or.ddit.info2.codemanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.info2.vo.CommonVO;
import kr.or.ddit.info2.vo.DivisionVO;

/**
 * 규격/분류/단위/공정 관리 DAO
 */
@Repository
public interface CodeManageDAO {
	public List<CommonVO> selectCodeList(CommonVO common);
	
	public int insertCode(CommonVO common);
	
	public int updateCode(CommonVO common);
	
	public int delteCode(CommonVO common);
	
	public List<DivisionVO> selectDivList(DivisionVO division);
	
	public int insertDiv(DivisionVO division);
	
	public int updateDiv(DivisionVO division);
	
	public int deleteDiv(DivisionVO division);
}
