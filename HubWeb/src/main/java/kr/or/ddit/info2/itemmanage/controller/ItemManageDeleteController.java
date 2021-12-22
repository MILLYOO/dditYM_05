package kr.or.ddit.info2.itemmanage.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.info2.itemmanage.service.ItemManageService;
import kr.or.ddit.info2.vo.ItemVO;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 품목 관리
 */
@Controller
public class ItemManageDeleteController {
	@Inject
	private ItemManageService service;
	@Inject
	private WebApplicationContext context;

	@RequestMapping("/info2/itemManageDelete.do")
	public Map<String, Object> ItemManageDelete(
			@Valid @RequestBody(required = true) ItemVO item,
			Errors errors) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if (!valid.checkError()) {
			valid.setResultMap(service.deleteItem(item));
		}
		return valid.getResultMap();
	}
}
