package kr.or.ddit.common.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.common.service.FindIdPwService;

@Controller
public class FindIdPwController {
	@Inject
	private FindIdPwService service;
	
	@RequestMapping("/common/FindIdPw.do")
	public String FindIdPw() {
		
		String viewName = "common/findIDPw";
		return viewName;
	}
}
