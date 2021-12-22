package kr.or.ddit.info2.itemmanage.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.info2.itemmanage.service.ItemManageService;
import kr.or.ddit.info2.vo.ItemVO;
import kr.or.ddit.validate.groups.DetailUpdateGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 품목 관리
 */
@Controller
public class ItemManageUpdateController {
	@Inject
	private ItemManageService service;
	@Inject
	private WebApplicationContext context;
	
	@ResponseBody
	@PostMapping(value = "/info2/itemUpdate.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> itemUpdate(
			@Validated(UpdateGroup.class) @ModelAttribute ItemVO item,
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if (!valid.checkError()) {
			valid.setResultMap(service.updateItem(item));
		}
		return valid.getResultMap();
	}
	
	@ResponseBody
	@PostMapping(value = "/info2/itemDetailUpdate.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> itemDetailUpdate(
			@Validated(DetailUpdateGroup.class) @ModelAttribute ItemVO item,
			Errors errors 
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if (!valid.checkError()) {
			valid.setResultMap(service.updateItemDetail(item));
		}
		return valid.getResultMap();
	}
}
