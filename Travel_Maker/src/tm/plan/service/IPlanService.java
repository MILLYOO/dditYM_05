package tm.plan.service;

import java.sql.SQLException;
import java.util.List;

import javax.print.attribute.standard.PDLOverrideSupported;

import tm.plan.vo.planDetailVO;
import tm.plan.vo.planVO;

public interface IPlanService {

	
	//일정 조회
	public List<planVO> selectAll(String tm_id);
	
	//일정삭제
	public int planDelete(String tm_plan_id);
	
	//일정상세삭제
	public int planDeleteDetail(String tm_plan_id);
	
	//일정 상세
	public List<planDetailVO> planDetail(String tm_plan_id);
	
	//일정 추가
	public int planInsert(planVO vo); 
	
	//일정디테일 추가
	public void planDInsert(planDetailVO vo);
}
