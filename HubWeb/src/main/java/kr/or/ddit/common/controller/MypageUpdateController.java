package kr.or.ddit.common.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.common.service.MypageService;

@Controller
public class MypageUpdateController {
	@Inject
	private MypageService service;
	
	@RequestMapping("/common/MypageUpdate.do")
	public String MypageUpdate() {
		
		String viewName = null;
		return viewName;
	}
}
