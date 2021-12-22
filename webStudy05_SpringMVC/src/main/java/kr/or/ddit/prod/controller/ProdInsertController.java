package kr.or.ddit.prod.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController {
	@Inject
	private ProdService service;
	
	

	@RequestMapping
	public String prodView() {
		return "prod/prodForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String process(@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod,
			Errors erros,
			Model model){
		log.info("{}",prod);
		String viewName = null;
		String message = null;
		if (!erros.hasErrors()) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case OK:
				viewName = "redirect:/prod/prodView.do?what=" + prod.getProdId();
				break;
			default:
				viewName = "prod/prodForm";
				message = "서버 오류";
			}
		} else {
			viewName = "prod/prodForm";
		}
		model.addAttribute("message", message);

		return viewName;
	}

}
