package kr.or.ddit.simple.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.SimpleList;
import kr.or.ddit.vo.SimpleVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/formtag/test")
@Slf4j
public class SimpleController {
	
	@ModelAttribute("simpleList")
	public SimpleVO simple() {
		return new SimpleVO();
	}
	
	@RequestMapping
	public String form() {
		return "simple/form";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String process(@ModelAttribute("simple") SimpleVO simple, Errors errors) {
		String viewName = null;
		if(errors.hasErrors()) {
			viewName = "simple/form";
		}else {
			log.info("객체 등록 : {}", simple.getSimpleList());
			viewName = "redirect:/";
		}
		return viewName;
	}
}













