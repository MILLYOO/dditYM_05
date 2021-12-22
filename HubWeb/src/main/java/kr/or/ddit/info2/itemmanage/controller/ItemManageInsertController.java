package kr.or.ddit.info2.itemmanage.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.info2.itemmanage.service.ItemManageService;
import kr.or.ddit.info2.vo.ItemVO;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 품목 관리
 */
@Controller
public class ItemManageInsertController {
	@Inject
	private ItemManageService service;
	
	@Inject 
	private WebApplicationContext context;
	
	@RequestMapping(value="/info2/itemManageInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> ItemManageInsert(
			@Validated(InsertGroup.class) @RequestBody CommonListVO<ItemVO> item,
			Errors errors
			) {
		System.out.println("들어옴~~~~~~~~~~~~~~~~~~");
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(item ,errors, context);
		if (!valid.checkError()) {
			valid.setResultMap(service.createItem(item));
		}
		return valid.getResultMap();
	}
}
