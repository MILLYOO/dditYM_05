package kr.or.ddit.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.DocCheckVO;
import lombok.extern.slf4j.Slf4j;
/*
 	AlarmController
	By 이원균_211209(최종수정)
	left.jsp 에 History 및 new 체킹을 위해 AOP를 활용, 데이터를 가져오는 클래스입니다.\
	페이지 이동 시 계속해서 left메뉴에 데이터를 로딩하기 위해 AOP로 구현했습니다.
	
	1. 문서 저장 history 데이터 만들기
	2. new 체킹을 위한 확인 필요 문서 수 가져오기
 */

@Slf4j
@ControllerAdvice(basePackages="kr.or.ddit")
public class AlarmController {

	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	//1. 문서 저장 history 데이터 만들기
	@ModelAttribute("history")
	public List<DocCheckVO> alarmDataHistory() {
		List<DocCheckVO> history = docCheckDAO.selectHistory();
		return history;
	}
	
	//2. new 체킹을 위한 확인 필요 문서 수 가져오기
	@ModelAttribute("unitMap")
	public Map<String, Object> alarmDataUnit() {
		List<DocCheckVO> docUnit = docCheckDAO.selectDocunit();
		Map<String, Object> unitMap = new HashMap<>();
		for(int i=0; i<docUnit.size(); i++) {
			String chkNum = docUnit.get(i).getChkNum();
			Integer chkUnit = docUnit.get(i).getChkUnit();
			unitMap.put(chkNum, chkUnit);
		}
		return unitMap;
	}
}
