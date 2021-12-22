package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

// /prod/prodUpdae.do viewName = prod/prodForm
@Slf4j
@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController {
	@Inject
	private ProdService service;
	@Value("${appInfo['pordImages']}")
	private String saveFolderURL;
	private File saveFolder;
	
	@Inject
	private WebApplicationContext context;
	
	
	public void init() {
		ServletContext application = context.getServletContext();
		saveFolder = new File(application.getRealPath(saveFolderURL));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	}
	
	
	@RequestMapping
	public String form(
			@RequestParam("what") String prodId,
			Model model) {
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		return "prod/prodForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String process(
			@Validated(UpdateGroup.class) @ModelAttribute("prod") ProdVO prod,
			BindingResult errors,
			Model model) throws IOException {
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyProd(prod);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/prod/prodView.do?what=" + prod.getProdId();
			}else {
				model.addAttribute("message", "서버 오류");
				viewName = "prod/prodForm";
			}
		}else { 
			viewName = "prod/prodForm";
		}
		return viewName;
	}
}
