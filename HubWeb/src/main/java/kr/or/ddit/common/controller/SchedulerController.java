package kr.or.ddit.common.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.service.SchedulerService;
import kr.or.ddit.common.vo.SchedulerVO;
import kr.or.ddit.enumpkg.ServiceResult;

@Controller
public class SchedulerController {
	@Inject
	private SchedulerService service;
	
	
	// 스케줄러 목록
	@ResponseBody
	@RequestMapping(value="/common/schedulerList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.GET)
	public List<SchedulerVO> schedulerList() {
		List<SchedulerVO> scList = service.retrieveSchedulerList();
		return scList;
	}
	
	// 스케쥴러 저장
	@ResponseBody
	@RequestMapping(value="/common/schedulerInsert.do",method=RequestMethod.POST)
	public String saveScheduler(@ModelAttribute SchedulerVO scVO) {
		String resultMessage = null;
		ServiceResult result = null;
		
		Integer scNo = scVO.getScNo();
		if(scNo != null) {
			result = service.modifySchedulter(scVO);
		}else {
			result = service.createScheduler(scVO);
		}
		switch (result) {
		case OK:
			resultMessage = "SUCCESS";
			break;
		default:
			resultMessage = "FAIL";
			break;
		}
		return resultMessage;
	}
	
	// 기본키구하기
	@ResponseBody
	@RequestMapping(value="/common/schedulerGetNumber.do",method=RequestMethod.POST)
	public int findScNoScheduler(@ModelAttribute SchedulerVO scVO) {
		return service.retrieveScNo(scVO);
	}
	
	// 스케쥴러 삭제
	@ResponseBody
	@RequestMapping(value="/common/schedulerDelete.do",method=RequestMethod.POST)
	public String deleteScheduler(@RequestParam("scNo") int scNo) {
		ServiceResult result = service.deleteScheduler(scNo);
		String resultMessage = null;
		switch (result) {
		case OK:
			resultMessage = "SUCCESS";
			break;
		default:
			resultMessage = "FAIL";
			break;
		}
		return resultMessage;
	}
}
