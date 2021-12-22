package kr.or.ddit.common.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.common.service.MypageService;

@Controller
public class MypageRetrieveController {
	@Inject
	private MypageService service;
	
	@RequestMapping("/common/MypageRetrieve.do")
	public String MypageRetrieve() {
		
		String viewName = "common/mypage";
		return viewName;
	}
	

}
