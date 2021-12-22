package kr.or.ddit.produce.merterialrelease.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.produce.merterialrelease.service.MaterialReleaseService;

/**
 * 자재출고처리
 */
@Controller
public class MaterialReleaseUpdateController {
	@Inject
	private MaterialReleaseService service;
	
	@RequestMapping("/produce/MaterialReleaseUpdate.do")
	public String MaterialReleaseUpdate() {
		
		String viewName = null;
		return viewName;
	}
}
